package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;

import java.io.IOException;
import java.math.BigInteger;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.aalexandrakis.kimobile.pojos.User;
import static com.aalexandrakis.kimobile.Constants.*;
public class ActivityLogin extends Activity {
	EditText txtUserName;
	EditText txtUserPassword;
	Button btnConnect;
	Button btnRegister;
	Button btnForgotPassword;
	ActivityLogin login = this;
	AsyncTaskLogin loginTask;
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
				
				loginTask = new AsyncTaskLogin(login); 
				loginTask.execute(txtUserName.getText().toString(), txtUserPassword.getText().toString());
			}
		});
		
		btnRegister.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent register = new Intent("com.aalexandrakis.kimobile.Register");
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
			editor.putInt("userLeve", user.getUserLevel());
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
		// TODO Auto-generated method stub
		  try {
	       // SoapEnvelop.1VER11 is SOAP Version 1.1 constant
	       SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	              SoapObject request = new SoapObject(Constants.NAMESPACE, METHOD);
	              request.addProperty("userName", userName);
	              request.addProperty("password", encryptedPassword);
	       //bodyOut is the body object to be sent out with this envelope
	       envelope.bodyOut = request;
	       HttpTransportSE transport = new HttpTransportSE(Constants.URL);
	       try {
	    	 transport.call(Constants.NAMESPACE + Constants.SOAP_ACTION_PREFIX + METHOD, envelope);
	       } catch (IOException e) {
	         e.printStackTrace();
	         error = true;
	       } catch (XmlPullParserException e) {
	         e.printStackTrace();
	         error = true;
	       }
		   //bodyIn is the body object received with this envelope
		   if (envelope.bodyIn != null) {
		     //getProperty() Returns a specific property at a certain index.
		     SoapObject resultSOAP = (SoapObject) envelope.getResponse();
		     if (resultSOAP != null){
				 User user = new User();
				 user.setUserId(new BigInteger(resultSOAP.getProperty(2).toString()));
				 user.setUserEmail(resultSOAP.getProperty(1).toString());
				 user.setUserName(resultSOAP.getProperty(4).toString());
				 user.setUserLevel(Integer.valueOf(resultSOAP.getProperty(3).toString()));
				 user.setUserCoins(new BigInteger(resultSOAP.getProperty(0).toString()));
				 user.setUserPassword(resultSOAP.getProperty(5).toString());
				 
				 Log.d("User Id", user.getUserId().toString());
				 Log.d("User email", user.getUserEmail());
				 Log.d("User Name", user.getUserName());
				 Log.d("User Level", user.getUserLevel().toString());
				 Log.d("User Coins", user.getUserCoins().toString());
				 Log.d("User Password", user.getUserPassword());
				 
				 return user;
		     }
		   }
		 } catch (Exception e) {
		   e.printStackTrace();
		   error = true;
		   return null;
		 }
	 	return null;
	 }	

}


