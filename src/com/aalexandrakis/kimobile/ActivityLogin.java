package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;
import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;

import java.io.*;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.util.Base64;
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
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.net.HttpURLConnection;

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
			HttpURLConnection httpPost = (HttpURLConnection) url.openConnection();
			httpPost.setRequestMethod("POST");
			httpPost.setRequestProperty("host", "192.168.1.2:3000");
			httpPost.setRequestProperty("connection", "keep-alive");
			httpPost.setRequestProperty("accept", "application/json, text/plain, */*");
			httpPost.setRequestProperty("origin", "http://192.168.1.2:3000");
//			httpPost.setRequestProperty("'user-agent': 'Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.65 Safari/537.36',
			httpPost.setRequestProperty("content-type", "charset=UTF-8");
			httpPost.setRequestProperty("referer", "http://192.168.1.2:3000/");
			httpPost.setRequestProperty("accept-encoding", "gzip, deflate");
			httpPost.setRequestProperty("accept-language", "en-US,en;q=0.8");
			httpPost.setRequestProperty("Content-Length", Integer.toString(regId.length() + 6));
			httpPost.setRequestProperty("Authorization", "Basic " + new String(Base64.encode((userName + ":" + encryptedPassword).getBytes(), Base64.DEFAULT)));
			httpPost.setDoOutput(true);
			httpPost.setDoInput(true);
			DataOutputStream wr = new DataOutputStream(httpPost.getOutputStream());
			wr.write(("regId=" + regId).getBytes("UTF-8"));
			wr.flush();
			wr.close();
			httpPost.setInstanceFollowRedirects(false);
			httpPost.setConnectTimeout(3000);
			httpPost.connect();
			int responseCode = httpPost.getResponseCode();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(httpPost.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			httpPost.disconnect();
			/****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
			JSONObject jsonResponse = new JSONObject(response.toString());
			user = new User();
			/***** Returns the value mapped by name if it exists and is a JSONArray. ***/
			user.setUserId(new BigInteger(jsonResponse.optString("userId")));
			user.setUserName(jsonResponse.optString("userName"));
			user.setUserEmail(jsonResponse.optString("userEmail"));
			user.setUserCoins(Float.valueOf(jsonResponse.optString("userCoins")));
			user.setUserLevel(Integer.valueOf(jsonResponse.optString("userLevel")));

			return user;
		} catch (MalformedURLException e){
			e.printStackTrace();
			error = true;
		} catch (IOException e){
			e.printStackTrace();
			error = true;
		}catch (JSONException e){
			e.printStackTrace();
			error = true;
		}

		return null;

	 }	

}


