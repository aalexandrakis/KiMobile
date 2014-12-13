package com.aalexandrakis.kimobile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
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
import org.json.JSONException;
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
//		txtUserName.setText("aalexand");
//		txtUserEmail.setText("aalexandrakis@hotmail.com");
//		txtUserPassword.setText("b");
//		txtRepeatPassword.setText("b");
		
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

 class AsyncTaskRegister extends AsyncTask<String, JSONObject, JSONObject>  {
	 
	ActivityRegister register;
	public static final String METHOD = "saveUser";
	boolean error = false;
	ProgressDialog pg;

	AsyncTaskRegister(ActivityRegister register){
		this.register = register;
	}
	 @SuppressLint("ShowToast")
	@Override
	protected void onPostExecute(JSONObject jsonResponse) {
		// TODO Auto-generated method stub
		super.onPostExecute(jsonResponse);
		pg.dismiss();
		if (error == true){
			showErrorDialog(register.getString(R.string.registerError), register.getString(R.string.youCanntConnect), register);
		} else if (jsonResponse.optString("status") != "00"){
			showErrorDialog(register.getString(R.string.registerError), jsonResponse.optString("message"), register);
			register.txtUserEmail.requestFocus();			
		} else if (jsonResponse.optString("status") == "00"){
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
	 protected JSONObject doInBackground(String... params) {
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

		try {
			JSONObject jsonParams = new JSONObject();
			jsonParams.put("userName", params[0]);
			jsonParams.put("email", params[1]);
			jsonParams.put("password", params[2]);
			jsonParams.put("regId", regId);
			/****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
			return CommonMethods.httpsUrlConnection("POST", "signUp", jsonParams.toString(), null, register);

		}  catch (JSONException e) {
			error = true;
			e.printStackTrace();
		} catch (Exception e){
			error = true;
			e.printStackTrace();
		}
	 	return null;
	 }	

}


