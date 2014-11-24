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

import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;
public class ActivityResetPassword extends Activity {
	EditText txtUserEmail;
	Button btnResetPassword;
	ActivityResetPassword resetPassword = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_password);
		txtUserEmail = (EditText) findViewById(R.id.txtUserEmail);
		btnResetPassword = (Button) findViewById(R.id.btnResetPassword);
		
		//TODO only for test
		txtUserEmail.setText("aalexandrakis@hotmail.com");
		
		btnResetPassword.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(resetPassword)){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection), resetPassword);
					return;
				}
				if (txtUserEmail.length() == 0){
					showErrorDialog(getString(R.string.resetPasswordError), getString(R.string.userEmailIsMissing), resetPassword);
					txtUserEmail.requestFocus();
					return;
				}
				
				AsyncTaskResetPassword loginTask = new AsyncTaskResetPassword(resetPassword);
				loginTask.execute(txtUserEmail.getText().toString());
			}
		});
	}
}

//TODO store data to shared preferences

 class AsyncTaskResetPassword extends AsyncTask<String, String, String>  {
	 
	ActivityResetPassword resetPassword;
	public static final String METHOD = "resetPassword";
	boolean error = false;
	ProgressDialog pg;

	AsyncTaskResetPassword(ActivityResetPassword register){
		this.resetPassword = register;
	}
	 @SuppressLint("ShowToast")
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pg.dismiss();
		if (error == true || result == null || result.equals("40")){
			showErrorDialog(resetPassword.getString(R.string.resetPasswordError), resetPassword.getString(R.string.youCanntConnect),resetPassword);
		} else if (result.equals("10")){
			showErrorDialog(resetPassword.getString(R.string.resetPasswordError), resetPassword.getString(R.string.emailNotFound), resetPassword);
			resetPassword.txtUserEmail.requestFocus();			
		} else if (result.equals("00")){
			Toast.makeText(resetPassword, resetPassword.getString(R.string.passwordChangeSuccessfully), Toast.LENGTH_LONG).show();
			resetPassword.finish();
		}
		
	}


	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pg = new ProgressDialog(resetPassword);
		pg.setTitle(resetPassword.getString(R.string.resetingPassword));
		pg.setMessage(resetPassword.getString(R.string.waitToResetyourPassword));
		pg.show();
	}


	@Override
	 protected String doInBackground(String... params) {
		String userEmail = params[0];
		// TODO Auto-generated method stub
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		HttpPost httpPost = new HttpPost(Constants.REST_URL + "resetPassword");
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("email", userEmail));
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


