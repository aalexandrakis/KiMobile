package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;
import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityMain extends FragmentActivity {
	SharedPreferences sharedPreferences;
	Button btnPlayNow;
	Button btnMyBets;
	Button btnDrawHistory;
	Button btnUpdateAccount;
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
		setContentView(R.layout.activity_main);
		
		btnPlayNow = (Button) findViewById(R.id.btnPlayNow);
		btnMyBets = (Button) findViewById(R.id.btnMyBets);
		btnDrawHistory = (Button) findViewById(R.id.btnDrawHistory);
		btnUpdateAccount = (Button) findViewById(R.id.btnUpdateAccount);
		final Activity activity = this;
		btnPlayNow.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection), activity);
					return;
				}
				showErrorDialog("Test", "PlayNow", activity);
			}
		});
		
		btnMyBets.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection), activity);
					return;
				}

				showErrorDialog("Test", "MyBets", activity);
			}
		});
		
		btnDrawHistory.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection), activity);
					return;
				}
				
				showErrorDialog("Test", "DrawHistory", activity);
			}
		});
		
		btnUpdateAccount.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection), activity);
					return;
				}
				
//				Intent updateAccount = new Intent("com.aalexandrakis.kimobile.UpdateAccount");
//				startActivity(updateAccount);
			

			}
		});
		
		
    }
    

	
}
