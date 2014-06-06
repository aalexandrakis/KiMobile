package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.*;
import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class ActivityMain extends FragmentActivity {
	SharedPreferences sharedPreferences;
	Button btnPlayNow;
	Button btnMyBets;
	Button btnDrawHistory;
	Button btnUpdateAccount;
	Activity activity = this;
	FrameLayout secondFragment;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
		setContentView(R.layout.activity_main);
		secondFragment = (FrameLayout)findViewById(R.id.secondFragment);
		if (secondFragment != null){
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			FragmentPlayNow playNowFragment = new FragmentPlayNow();
			fragmentTransaction.add(R.id.secondFragment, playNowFragment);
			fragmentTransaction.commit();
		}
		
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
				if (secondFragment != null){
					FragmentManager fragmentManager = getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					FragmentPlayNow playNowFragment = new FragmentPlayNow();
					fragmentTransaction.add(R.id.secondFragment, playNowFragment);
					fragmentTransaction.commit();
				} else {
					Intent intent = new Intent();
		            intent.setClass(getApplicationContext(), ActivityPlayNow.class);
					startActivity(intent);
				}
			
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
				
				if (isLarge(activity)){
					    // on a large screen device ...
//						FragmentManager fragmentManager = getSupportFragmentManager();
//						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//						FragmentUpdateAccount updateAccount = new FragmentUpdateAccount();
//						fragmentTransaction.replace
				} else {
						
				}
//				Intent updateAccount = new Intent("com.aalexandrakis.kimobile.UpdateAccount");
//				startActivity(updateAccount);
			

			}
		});
		
		
    }
    

    
	
}
