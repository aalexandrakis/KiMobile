package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;
import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.aalexandrakis.kimobile.pojos.User;
public class ActivityLogin extends Activity {
	EditText txtUserName;
	EditText txtUserPassword;
	Button btnConnect;
	Button btnRegister;
	Button btnForgotPassword;
	ActivityLogin login = this;
	SharedPreferences sharedPreferences;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sharedPreferences  = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE); 
		txtUserName = (EditText) findViewById(R.id.txtUserName);
		txtUserName.requestFocus();
		txtUserPassword = (EditText) findViewById(R.id.txtUserPassword);
		btnConnect = (Button) findViewById(R.id.btnConnect);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnForgotPassword = (Button) findViewById(R.id.btnForgotPassword);
		final Activity activityLogin = this;
		//TODO only for test
				txtUserName.setText("aalexand");
				txtUserPassword.setText("b");
				
		
		btnConnect.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activityLogin)){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection), activityLogin);
					return;
				}
				if (txtUserName.length() == 0){
					showErrorDialog(getString(R.string.credentialsError), getString(R.string.userNameMissing), activityLogin);
					txtUserName.requestFocus();
					return;
				}
				if (txtUserPassword.length() == 0){
					showErrorDialog(getString(R.string.credentialsError), getString(R.string.userPasswordMissing), activityLogin);
					txtUserPassword.requestFocus();
					return;
				}
				AsyncTaskLogin loginTask;
				
				loginTask = new AsyncTaskLogin(login); 
				loginTask.execute(txtUserName.getText().toString(), txtUserPassword.getText().toString());
			}
		});
		
		btnRegister.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent register = new Intent("com.aalexandrakis.kimobile.ActivityRegister");
				startActivity(register);
			}
		});
		
		btnForgotPassword.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activityLogin)){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection), activityLogin);
					return;
				}
				
				Intent register = new Intent("com.aalexandrakis.kimobile.ResetPassword");
				startActivity(register);
			}
		});
		
	}

}


 class AsyncTaskLogin extends AsyncTask<String, User, User>  {
	 
	ActivityLogin login;
	public static final String METHOD = "login";
	boolean error = false;
	ProgressDialog pg;
	String password;
	AsyncTaskLogin(ActivityLogin login){
		this.login = login;
	}
	 @Override
	protected void onPostExecute(User user) {
		// TODO Auto-generated method stub
		super.onPostExecute(user);
		pg.dismiss();
		if (error){
			showErrorDialog(login.getString(R.string.credentialsError), login.getString(R.string.youCanntConnect), login);
		} else if (user == null){
			showErrorDialog(login.getString(R.string.credentialsError), login.getString(R.string.credentialsAreError), login);
		} else {
			SharedPreferences.Editor editor = login.sharedPreferences.edit();
			editor.clear();
			editor.putString("userId", user.getUserId().toString());
			editor.putString("userName", user.getUserName());
			editor.putString("userEmail", user.getUserEmail());
			editor.putString("userPassword", password);
			editor.putString("userCoins", user.getUserCoins().toString());
			editor.putInt("userLevel", user.getUserLevel());
			editor.commit();
			Intent mainMenu = new Intent("com.aalexandrakis.kimobile.ActivityMain");
			login.startActivity(mainMenu);
			login.finish();
		}
	}


	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pg = new ProgressDialog(login);
		pg.setTitle(login.getString(R.string.logingIn));
		pg.setMessage(login.getString(R.string.waitToConfirmCredentials));
		pg.show();
	}


	@Override
	 protected User doInBackground(String... params) {
		String userName = params[0];
		String encryptedPassword = CommonMethods.encryptPassword(params[1]);
		password = params[1];
		User user;
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		HttpPost httpPost = new HttpPost(Constants.REST_URL + "login");
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("userName", userName));
		parameters.add(new BasicNameValuePair("password", encryptedPassword));
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
                user = new User();
                /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
                user.setUserId(new BigInteger(jsonResponse.optString("userId")));
                user.setUserName(jsonResponse.optString("userName"));
                user.setUserEmail(jsonResponse.optString("userEmail"));
                user.setUserCoins(Float.valueOf(jsonResponse.optString("userCoins")));
                user.setUserLevel(Integer.valueOf(jsonResponse.optString("userLevel")));
                return user;
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
		}
	 	return null;
	 }	

}


