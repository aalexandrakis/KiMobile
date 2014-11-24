package com.aalexandrakis.kimobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.aalexandrakis.kimobile.pojos.User;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;
import static com.aalexandrakis.kimobile.Constants.HOST;
import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;

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
				
				Intent register = new Intent("com.aalexandrakis.kimobile.ActivityResetPassword");
				startActivity(register);
			}
		});
		
	}

}


 class AsyncTaskLogin extends AsyncTask<String, User, User>  {
	 
	ActivityLogin login;
	public static final String METHOD = "login";
	boolean error = false;
	ProgressDialog httpPost;
	String password;
	AsyncTaskLogin(ActivityLogin login){
		this.login = login;
	}
	 @Override
	protected void onPostExecute(User user) {
		// TODO Auto-generated method stub
		super.onPostExecute(user);
		httpPost.dismiss();
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
			CommonMethods.checkPlayServices(login);
			login.finish();
		}
	}


	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		httpPost = new ProgressDialog(login);
		httpPost.setTitle(login.getString(R.string.logingIn));
		httpPost.setMessage(login.getString(R.string.waitToConfirmCredentials));
		httpPost.show();
	}


	@Override
	 protected User doInBackground(String... params) {
		String userName = params[0];
		String encryptedPassword = CommonMethods.encryptPassword(params[1]);
		password = params[1];
		User user;
		/************ Get Reg id ***************/
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(login);
		String regId = null;
		try {
			regId = gcm.register(Constants.SENDER_ID);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			URL url = new URL(Constants.REST_URL + "signIn");
			JSONObject jsonParams = new JSONObject();
			jsonParams.put("regId", regId);
			String authorization = Base64.encodeToString((userName + ":" + encryptedPassword).getBytes("UTF-8"), Base64.NO_WRAP);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(CommonMethods.getSSLContext(login.getBaseContext()).getSocketFactory());
			conn.setHostnameVerifier(CommonMethods.hostnameVerifier);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Host", HOST);
			conn.setRequestProperty("User-Agent", "");
			conn.setRequestProperty("Accept", "application/json, text/plain, */*");
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
			conn.setRequestProperty("Authorization", "Basic " + authorization);
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.setRequestProperty("Content-Length", String.valueOf(jsonParams.toString().length()));
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.getOutputStream().write(jsonParams.toString().getBytes("UTF-8"));
			conn.getOutputStream().close();
			int responseCode = conn.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuilder stringBuilder = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				stringBuilder.append(inputLine);
			in.close();
			String responseString = stringBuilder.toString();
			/****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
			JSONObject jsonResponse = new JSONObject(responseString.toString());
			user = new User();
			/***** Returns the value mapped by name if it exists and is a JSONArray. ***/
			user.setUserId(new BigInteger(jsonResponse.optString("userId")));
			user.setUserName(jsonResponse.optString("userName"));
			user.setUserEmail(jsonResponse.optString("userEmail"));
			user.setUserCoins(Float.valueOf(jsonResponse.optString("userCoins")));
			user.setUserLevel(Integer.valueOf(jsonResponse.optString("userLevel")));

			return user;

		} catch (MalformedURLException e) {
			error = true;
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			error = true;
			e.printStackTrace();
		} catch (IOException e) {
			error = true;
			e.printStackTrace();
		} catch (JSONException e) {
			error = true;
			e.printStackTrace();
		} catch (Exception e){
			error = true;
			e.printStackTrace();
		}

		return null;
	}

}


