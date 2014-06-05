package com.aalexandrakis.kimobile;

import java.io.IOException;

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

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import static com.aalexandrakis.kimobile.CommonMethods.*;
public class ResetPassword extends Activity {
	EditText txtUserEmail;
	Button btnResetPassword;
	ResetPassword resetPassword = this;
	
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

 class AsyncTaskResetPassword extends AsyncTask<String, String[], String[]>  {
	 
	ResetPassword resetPassword;
	public static final String METHOD = "resetPassword";
	boolean error = false;
	ProgressDialog pg;

	AsyncTaskResetPassword(ResetPassword register){
		this.resetPassword = register;
	}
	 @SuppressLint("ShowToast")
	@Override
	protected void onPostExecute(String[] result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pg.dismiss();
		if (error == true || result == null || result[0].equals("40")){
			showErrorDialog(resetPassword.getString(R.string.resetPasswordError), resetPassword.getString(R.string.youCanntConnect),resetPassword);
		} else if (result[0].equals("10")){
			showErrorDialog(resetPassword.getString(R.string.resetPasswordError), resetPassword.getString(R.string.emailNotFound), resetPassword);
			resetPassword.txtUserEmail.requestFocus();			
		} else if (result[0].equals("00")){
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
	 protected String[] doInBackground(String... params) {
		String userEmail = params[0];
		// TODO Auto-generated method stub
		  try {
	       // SoapEnvelop.1VER11 is SOAP Version 1.1 constant
	       SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	              SoapObject request = new SoapObject(Constants.NAMESPACE, METHOD);
	              request.addProperty("userEmail", userEmail);
	              
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


