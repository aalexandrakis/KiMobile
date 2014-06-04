package com.aalexandrakis.kimobile;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.aalexandrakis.kimobile.pojos.User;

public class MainActivity extends FragmentActivity {
	static final String SHARED_PREFERENCES = "KiMobilePrefs";
	final static String URL = "http://192.168.1.2:8080/KimoBackEnd/services/KimoDb?wsdl";
	static final String NAMESPACE = "http://kimo.aalexandrakis.com";
	static final String SOAP_ACTION_PREFIX = "/";
	
	
	SharedPreferences sharedPreferences;
	
    User logedInUser;
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//TODO check if this code working
		sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
		setContentView(R.layout.activity_main);
	
		
		
    }
    
//	void showErrorDialog(String title, String message){
//		new AlertDialog.Builder(this)
//		.setTitle(title)
//		.setMessage(message)
//		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// TODO Auto-generated method stub
//			}
//		})
//		.show();
//	}
//	
//	
//	boolean  checkConnectivity(){
//		try {
//		    ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//		    return conMgr.getActiveNetworkInfo().isConnected();
//		}
//		catch (java.lang.NullPointerException ex) 
//		{
//			return false;
//		}
//	}

	
}
