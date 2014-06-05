package com.aalexandrakis.kimobile;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import static com.aalexandrakis.kimobile.CommonMethods.*;
import static com.aalexandrakis.kimobile.CommonMethods.*;
public class Register extends Activity {
	EditText txtUserName;
	EditText txtUserEmail;
	EditText txtUserPassword;
	EditText txtRepeatPassword;
	Button btnRegister;
	Register register = this;
	
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
		txtUserPassword.setText("a");
		txtRepeatPassword.setText("a");
		
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
				
				AsyncTaskRegister loginTask = new AsyncTaskRegister(register);
				loginTask.execute(txtUserName.getText().toString(), txtUserEmail.getText().toString(), encryptPassword(txtUserPassword.getText().toString()));
			}
		});
	}
}

//TODO store data to shared preferences

 class AsyncTaskRegister extends AsyncTask<String, String[], String[]>  {
	 
	Register register;
	public static final String METHOD = "saveUser";
	boolean error = false;
	ProgressDialog pg;

	AsyncTaskRegister(Register register){
		this.register = register;
	}
	 @SuppressLint("ShowToast")
	@Override
	protected void onPostExecute(String[] result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pg.dismiss();
		if (error == true || result == null || result[0].equals("40")){
			showErrorDialog(register.getString(R.string.registerError), register.getString(R.string.youCanntConnect), register);
		} else if (result[0].equals("10")){
			showErrorDialog(register.getString(R.string.registerError), register.getString(R.string.userEmailExists), register);
			register.txtUserEmail.requestFocus();			
		} else if (result[0].equals("11")){
			showErrorDialog(register.getString(R.string.registerError), register.getString(R.string.userNameExists), register);
			register.txtUserName.requestFocus();
		} else if (result[0].equals("00")){
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
	 protected String[] doInBackground(String... params) {
		String userName = params[0];
		String userEmail = params[1];
		String password = params[2];
		// TODO Auto-generated method stub
		  try {
	       // SoapEnvelop.1VER11 is SOAP Version 1.1 constant
	       SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	              SoapObject request = new SoapObject(Constants.NAMESPACE, METHOD);
	              request.addProperty("userId", null);
	              request.addProperty("userName", userName);
	              request.addProperty("userEmail", userEmail);
	              request.addProperty("userPassword", password);
	       //bodyOut is the body object to be sent out with this envelope
	       envelope.bodyOut = request;
	       HttpTransportSE transport = new HttpTransportSE(Constants.URL);
	       try {
	    	 transport.call(Constants.NAMESPACE + Constants.SOAP_ACTION_PREFIX + METHOD, envelope);
	       } catch (IOException e) {
	         e.printStackTrace();
	       } catch (XmlPullParserException e) {
	         e.printStackTrace();
	       }
		   //bodyIn is the body object received with this envelope
		   if (envelope.bodyIn != null) {
		     //getProperty() Returns a specific property at a certain index.
			 SoapObject object = (SoapObject)  envelope.bodyIn;
		     String[] result = {object.getProperty(0).toString(), object.getProperty(1).toString()}; 
		     Log.d("Add User response to string", result[0] + "-" + result[1]);
			    
			 return result;
		   }
		 } catch (Exception e) {
		   e.printStackTrace();
		   error = true;
		   return null;
		 }
	 	return null;
	 }	

}


