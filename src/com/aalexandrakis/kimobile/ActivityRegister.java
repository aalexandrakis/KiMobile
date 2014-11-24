package com.aalexandrakis.kimobile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.aalexandrakis.kimobile.CommonMethods.*;
public class ActivityRegister extends Activity {
	EditText txtUserName;
	EditText txtUserEmail;
	EditText txtUserPassword;
	EditText txtRepeatPassword;
	Button btnRegister;
	ActivityRegister register = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		txtUserName = (EditText) findViewById(R.id.txtUserName);
		txtUserEmail = (EditText) findViewById(R.id.txtUserEmail);
		txtUserPassword = (EditText) findViewById(R.id.txtUserPassword);
		txtRepeatPassword = (EditText) findViewById(R.id.txtRepeatPassword);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		
		//TODO only for test
		txtUserName.setText("aalexand");
		txtUserEmail.setText("aalexandrakis@hotmail.com");
		txtUserPassword.setText("b");
		txtRepeatPassword.setText("b");
		
		btnRegister.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(register)){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection), register);
					return;
				}
				if (txtUserName.length() == 0){
					showErrorDialog(getString(R.string.registerError), getString(R.string.userNameMissing), register);
					txtUserName.requestFocus();
					return;
				}
				if (txtUserEmail.length() == 0){
					showErrorDialog(getString(R.string.registerError), getString(R.string.userEmailIsMissing), register);
					txtUserEmail.requestFocus();
					return;
				}
				
				if (!txtUserEmail.getText().toString().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")){
					showErrorDialog(getString(R.string.registerError), getString(R.string.userEmailErrorformat), register);
					txtUserEmail.requestFocus();
					return;
				}
				
				if (txtUserPassword.length() == 0){
					showErrorDialog(getString(R.string.registerError), getString(R.string.userPasswordMissing), register);
					txtUserPassword.requestFocus();
					return;
				}
				

				if (!txtUserPassword.getText().toString().equals(txtRepeatPassword.getText().toString())){
					showErrorDialog(getString(R.string.registerError), getString(R.string.passwordNotEqualWithRepeated), register);
					txtRepeatPassword.requestFocus();
					return;
				}
				
				AsyncTaskRegister registerTask = new AsyncTaskRegister(register);
				registerTask.execute(txtUserName.getText().toString(), txtUserEmail.getText().toString(), encryptPassword(txtUserPassword.getText().toString()));
			}
		});
	}
}

//TODO store data to shared preferences

 class AsyncTaskRegister extends AsyncTask<String, String, String>  {
	 
	ActivityRegister register;
	public static final String METHOD = "saveUser";
	boolean error = false;
	ProgressDialog pg;

	AsyncTaskRegister(ActivityRegister register){
		this.register = register;
	}
	 @SuppressLint("ShowToast")
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pg.dismiss();
		if (error == true || result == null || result.equals("40")){
			showErrorDialog(register.getString(R.string.registerError), register.getString(R.string.youCanntConnect), register);
		} else if (result.equals("10")){
			showErrorDialog(register.getString(R.string.registerError), register.getString(R.string.userEmailExists), register);
			register.txtUserEmail.requestFocus();			
		} else if (result.equals("11")){
			showErrorDialog(register.getString(R.string.registerError), register.getString(R.string.userNameExists), register);
			register.txtUserName.requestFocus();
		} else if (result.equals("00")){
			Toast.makeText(register, register.getString(R.string.toastUserAddedSuccesfully), Toast.LENGTH_LONG).show();
			register.finish();
		}
		
	}


	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pg = new ProgressDialog(register);
		pg.setTitle(register.getString(R.string.registering));
		pg.setMessage(register.getString(R.string.waitToValidateYourInfo));
		pg.show();
	}


	@Override
	 protected String doInBackground(String... params) {
		String userName = params[0];
		String userEmail = params[1];
		String password = params[2];
		// TODO Auto-generated method stub
		/************ Get Reg id ***************/
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(register);
        String regId = null;
		try {
			regId = gcm.register(Constants.SENDER_ID);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		HttpPost httpPost = new HttpPost(Constants.REST_URL + "signUp");
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("userIdString", "0"));
		parameters.add(new BasicNameValuePair("userName", userName));
		parameters.add(new BasicNameValuePair("userEmail", userEmail));
		parameters.add(new BasicNameValuePair("userPassword", password));
		parameters.add(new BasicNameValuePair("regId", regId));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(parameters));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			response = httpclient.execute(httpPost);
			
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				String responseString = out.toString();
				/****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                JSONObject jsonResponse = new JSONObject(responseString);
                return jsonResponse.optString("responseCode");
			} else {
				// Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;
		}
	 	return null;
	 }	

}


