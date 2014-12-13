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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;
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
//				txtUserName.setText("aalexand");
//				txtUserPassword.setText("b");
				
		
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


 class AsyncTaskLogin extends AsyncTask<String, JSONObject, JSONObject>  {
	 
	ActivityLogin login;
	public static final String METHOD = "login";
	boolean error = false;
	ProgressDialog httpPost;
	String password;
	AsyncTaskLogin(ActivityLogin login){
		this.login = login;
	}
	 @Override
	protected void onPostExecute(JSONObject jsonResponse) {
		// TODO Auto-generated method stub
		super.onPostExecute(jsonResponse);
		httpPost.dismiss();
		if (error || jsonResponse == null){
			showErrorDialog(login.getString(R.string.credentialsError), login.getString(R.string.youCanntConnect), login);
		} else if (jsonResponse.optString("message") != ""){
			showErrorDialog(login.getString(R.string.credentialsError), jsonResponse.optString("message"), login);
		} else {
			SharedPreferences.Editor editor = login.sharedPreferences.edit();
			editor.clear();
			editor.putString("userId", jsonResponse.optString("userId"));
			editor.putString("userName", jsonResponse.optString("userName"));
			editor.putString("userEmail", jsonResponse.optString("userEmail"));
			editor.putString("userPassword", jsonResponse.optString("userPassword"));
			editor.putString("userCoins", jsonResponse.optString("userCoins"));
			editor.putInt("userLevel", Integer.valueOf(jsonResponse.optString("userLevel")));
			try {
				editor.putString("token", Base64.encodeToString((jsonResponse.optString("userName") + ":" + jsonResponse.optString("userPassword")).getBytes("UTF-8"), Base64.NO_WRAP));
			} catch (UnsupportedEncodingException e){
				e.printStackTrace();
			}
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
	 protected JSONObject doInBackground(String... params) {
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
			JSONObject jsonParams = new JSONObject();
			jsonParams.put("regId", regId);
			String authorization = Base64.encodeToString((userName + ":" + encryptedPassword).getBytes("UTF-8"), Base64.NO_WRAP);
			/****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
			return CommonMethods.httpsUrlConnection("POST", "signIn", jsonParams.toString(), authorization, login);

		} catch (UnsupportedEncodingException e) {
			error = true;
			e.printStackTrace();
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


