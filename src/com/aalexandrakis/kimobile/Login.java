package com.aalexandrakis.kimobile;

import java.io.IOException;
import java.math.BigInteger;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.aalexandrakis.kimobile.pojos.User;

public class Login extends CommonActivity {
	EditText txtUserName;
	EditText txtUserPassword;
	Button btnConnect;
	Button btnRegister;
	Button btnForgotPassword;
	Login login = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		txtUserName = (EditText) findViewById(R.id.txtUserName);
		txtUserName.requestFocus();
		txtUserPassword = (EditText) findViewById(R.id.txtUserPassword);
		btnConnect = (Button) findViewById(R.id.btnConnect);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnForgotPassword = (Button) findViewById(R.id.btnForgotPassword);
		
		btnConnect.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity()){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection));
					return;
				}
				if (txtUserName.length() == 0){
					showErrorDialog(getString(R.string.credentialsError), getString(R.string.userNameMissing));
					return;
				}
				if (txtUserPassword.length() == 0){
					showErrorDialog(getString(R.string.credentialsError), getString(R.string.userPasswordMissing));
					return;
				}
				
				AsyncTaskRunner loginTask = new AsyncTaskRunner(login);
				loginTask.execute(txtUserName.getText().toString(), txtUserPassword.getText().toString());
			}
		});
	}
}

//TODO check connectivity before login
//TODO if error show alert dialog
//TODO store data to shared preferences

 class AsyncTaskRunner extends AsyncTask<String, User, User>  {
	 
	Login login;
	public final static String URL = "http://192.168.1.2:8080/KimoBackEnd/services/KimoDb?wsdl";
	public static final String NAMESPACE = "http://kimo.aalexandrakis.com";
	public static final String SOAP_ACTION_PREFIX = "/";
	public static final String METHOD = "login";
	ProgressDialog pg;

	AsyncTaskRunner(Login login){
		this.login = login;
	}
	 @Override
	protected void onPostExecute(User user) {
		// TODO Auto-generated method stub
		super.onPostExecute(user);
		pg.dismiss();
		if (user == null){
			login.showErrorDialog(login.getString(R.string.credentialsError), login.getString(R.string.credentialsAreError));
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
		String password = params[1];
	 	// TODO Auto-generated method stub
		  try {
	       // SoapEnvelop.VER11 is SOAP Version 1.1 constant
	       SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	              SoapObject request = new SoapObject(NAMESPACE, METHOD);
	              request.addProperty("userName", userName);
	              request.addProperty("password", password);
	       //bodyOut is the body object to be sent out with this envelope
	       envelope.bodyOut = request;
	       HttpTransportSE transport = new HttpTransportSE(URL);
	       try {
	    	 transport.call(NAMESPACE + SOAP_ACTION_PREFIX + METHOD, envelope);
	       } catch (IOException e) {
	         e.printStackTrace();
	       } catch (XmlPullParserException e) {
	         e.printStackTrace();
	       }
		   //bodyIn is the body object received with this envelope
		   if (envelope.bodyIn != null) {
		     //getProperty() Returns a specific property at a certain index.
		     SoapObject resultSOAP = (SoapObject) envelope.getResponse();
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
			 
			 login.logedInUser = user;
		     return user;
		   }
		 } catch (Exception e) {
		   e.printStackTrace();
		   return null;
		 }
	 	return null;
	 }	

}


