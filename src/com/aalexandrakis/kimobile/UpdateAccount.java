package com.aalexandrakis.kimobile;

import java.io.IOException;
import java.math.BigInteger;

import android.annotation.SuppressLint;
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

public class UpdateAccount extends CommonActivity {
	EditText txtUserName;
	EditText txtUserEmail;
	EditText txtUserPassword;
	EditText txtRepeatPassword;
	Button btnUpdateAccount;
	UpdateAccount updateAccount = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_account);
		txtUserName = (EditText) findViewById(R.id.txtUserName);
		txtUserEmail = (EditText) findViewById(R.id.txtUserEmail);
		txtUserPassword = (EditText) findViewById(R.id.txtUserPassword);
		txtRepeatPassword = (EditText) findViewById(R.id.txtRepeatPassword);
		btnUpdateAccount = (Button) findViewById(R.id.btnUpdateAccount);
		
		
		txtUserName.setText(sharedPreferences.getString("userName", ""));
		txtUserEmail.setText(sharedPreferences.getString("userEmail", ""));
		txtUserPassword.setText(sharedPreferences.getString("userPassword", ""));
		
		btnUpdateAccount.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity()){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection));
					return;
				}
				if (txtUserName.length() == 0){
					showErrorDialog(getString(R.string.updateAccountError), getString(R.string.userNameMissing));
					txtUserName.requestFocus();
					return;
				}
				if (txtUserEmail.length() == 0){
					showErrorDialog(getString(R.string.updateAccountError), getString(R.string.userEmailIsMissing));
					txtUserEmail.requestFocus();
					return;
				}
				
				if (!txtUserEmail.getText().toString().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")){
					showErrorDialog(getString(R.string.updateAccountError), getString(R.string.userEmailErrorformat));
					txtUserEmail.requestFocus();
					return;
				}
				
				if (txtUserPassword.length() == 0){
					showErrorDialog(getString(R.string.updateAccountError), getString(R.string.userPasswordMissing));
					txtUserPassword.requestFocus();
					return;
				}
				

				if (!txtUserPassword.getText().toString().equals(txtRepeatPassword.getText().toString())){
					showErrorDialog(getString(R.string.updateAccountError), getString(R.string.passwordNotEqualWithRepeated));
					txtRepeatPassword.requestFocus();
					return;
				}
				
				AsyncTaskUpdateAccount updateAccountTask = new AsyncTaskUpdateAccount(updateAccount);
				updateAccountTask.execute(sharedPreferences.getString("userId", "0"), txtUserName.getText().toString(), txtUserEmail.getText().toString(), encryptPassword(txtUserPassword.getText().toString()));
			}
		});
	}
}


 class AsyncTaskUpdateAccount extends AsyncTask<String, String[], String[]>  {
	 
	UpdateAccount updateAccount;
	public static final String METHOD = "saveUser";
	boolean error = false;
	ProgressDialog pg;

	AsyncTaskUpdateAccount(UpdateAccount updateAccount){
		this.updateAccount = updateAccount;
	}
	 @SuppressLint("ShowToast")
	@Override
	protected void onPostExecute(String[] result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pg.dismiss();
		if (error == true || result == null || result[0].equals("40")){
			updateAccount.showErrorDialog(updateAccount.getString(R.string.updateAccountError), updateAccount.getString(R.string.youCanntConnect));
		} else if (result[0].equals("10")){
			updateAccount.showErrorDialog(updateAccount.getString(R.string.updateAccountError), updateAccount.getString(R.string.userEmailExists));
			updateAccount.txtUserEmail.requestFocus();			
		} else if (result[0].equals("11")){
			updateAccount.showErrorDialog(updateAccount.getString(R.string.updateAccountError), updateAccount.getString(R.string.userNameExists));
			updateAccount.txtUserName.requestFocus();
		} else if (result[0].equals("00")){
			Toast.makeText(updateAccount, updateAccount.getString(R.string.toastUserUpdatedSuccesfully), Toast.LENGTH_LONG).show();
			updateAccount.finish();
		}
		
	}


	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pg = new ProgressDialog(updateAccount);
		pg.setTitle(updateAccount.getString(R.string.updating));
		pg.setMessage(updateAccount.getString(R.string.waitToUpdateAccount));
		pg.show();
	}


	@Override
	 protected String[] doInBackground(String... params) {
		BigInteger userId = new BigInteger(params[0]);
		String userName = params[1];
		String userEmail = params[2];
		String password = params[3];
		// TODO Auto-generated method stub
		  try {
	       // SoapEnvelop.1VER11 is SOAP Version 1.1 constant
	       SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	              SoapObject request = new SoapObject(CommonActivity.NAMESPACE, METHOD);
	              request.addProperty("userId", userId);
	              request.addProperty("userName", userName);
	              request.addProperty("userEmail", userEmail);
	              request.addProperty("userPassword", password);
	       //bodyOut is the body object to be sent out with this envelope
	       envelope.bodyOut = request;
	       HttpTransportSE transport = new HttpTransportSE(CommonActivity.URL);
	       try {
	    	 transport.call(CommonActivity.NAMESPACE + CommonActivity.SOAP_ACTION_PREFIX + METHOD, envelope);
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


