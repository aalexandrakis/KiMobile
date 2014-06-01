package com.aalexandrakis.kimobile;

import java.io.IOException;
import java.math.BigInteger;

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

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.aalexandrakis.kimobile.pojos.User;

public class MainMenu extends CommonActivity {
	Button btnPlayNow;
	Button btnMyBets;
	Button btnDrawHistory;
	Button btnUpdateAccount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		btnPlayNow = (Button) findViewById(R.id.btnPlayNow);
		btnMyBets = (Button) findViewById(R.id.btnMyBets);
		btnDrawHistory = (Button) findViewById(R.id.btnDrawHistory);
		btnUpdateAccount = (Button) findViewById(R.id.btnUpdateAccount);
		
		btnPlayNow.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity()){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection));
					return;
				}
				showErrorDialog("Test", "PlayNow");
			}
		});
		
		btnMyBets.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity()){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection));
					return;
				}

				showErrorDialog("Test", "MyBets");
			}
		});
		
		btnDrawHistory.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity()){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection));
					return;
				}
				
				showErrorDialog("Test", "DrawHistory");
			}
		});
		
		btnUpdateAccount.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity()){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection));
					return;
				}
				
				Intent updateAccount = new Intent("com.aalexandrakis.kimobile.UpdateAccount");
				startActivity(updateAccount);

			}
		});
	}
	
}