package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;
import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
//TODO display remaining time for the draw, user coins and user level
//TODO hide keyboard on click
public class ActivityMain extends FragmentActivity {
	SharedPreferences sharedPreferences;
	Button btnPlayNow;
	Button btnMyActiveBets;
	Button btnMyArchiveBets;
	Button btnDrawHistory;
	Button btnUpdateAccount;
	Button btnBuyCoins;
	FrameLayout secondFragment;
	TextView txtNextDrawAt;
	TextView txtCoins;
	
	final ActivityMain activity = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sharedPreferences = getSharedPreferences(SHARED_PREFERENCES,
				MODE_PRIVATE);
		setContentView(R.layout.activity_main);
		secondFragment = (FrameLayout) findViewById(R.id.secondFragment);
		txtNextDrawAt = (TextView) findViewById(R.id.txtNextDraw);
		txtCoins = (TextView) findViewById(R.id.txtCoins);
	
		if (secondFragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			FragmentPlayNow playNowFragment = new FragmentPlayNow();
			fragmentTransaction.replace(R.id.secondFragment, playNowFragment);
			fragmentTransaction.commit();
		}

		btnPlayNow = (Button) findViewById(R.id.btnPlayNow);
		btnMyActiveBets = (Button) findViewById(R.id.btnMyActiveBets);
		btnMyArchiveBets = (Button) findViewById(R.id.btnMyArchiveBets);
		btnDrawHistory = (Button) findViewById(R.id.btnDrawHistory);
		btnUpdateAccount = (Button) findViewById(R.id.btnUpdateAccount);
		btnBuyCoins = (Button) findViewById(R.id.btnBuyCoins);

		btnPlayNow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)) {
					showErrorDialog(getString(R.string.networkError),
							getString(R.string.noInternetConnection), activity);
					return;
				}
				if (secondFragment != null) {
					FragmentManager fragmentManager = getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					FragmentPlayNow playNowFragment = new FragmentPlayNow();
					fragmentTransaction.replace(R.id.secondFragment,
							playNowFragment);
					fragmentTransaction.commit();
				} else {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(),
							ActivityPlayNow.class);
					startActivity(intent);
				}

			}
		});

		btnMyActiveBets.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)) {
					showErrorDialog(getString(R.string.networkError),
							getString(R.string.noInternetConnection), activity);
					return;
				}
				if (secondFragment != null) {
					FragmentManager fragmentManager = getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					FragmentViewActiveBets viewBetsFragment = new FragmentViewActiveBets();
					fragmentTransaction.replace(R.id.secondFragment,
							viewBetsFragment);
					fragmentTransaction.commit();
				} else {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(),
							ActivityViewActiveBets.class);
					startActivity(intent);
				}

			}
		});

		btnMyArchiveBets.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)) {
					showErrorDialog(getString(R.string.networkError),
							getString(R.string.noInternetConnection), activity);
					return;
				}
				if (secondFragment != null) {
					FragmentManager fragmentManager = getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					FragmentViewArchiveBets viewArchiveBetsFragment = new FragmentViewArchiveBets();
					fragmentTransaction.replace(R.id.secondFragment,
							viewArchiveBetsFragment);
					fragmentTransaction.commit();
				} else {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(),
							ActivityViewArchiveBets.class);
					startActivity(intent);
				}

			}
		});


		btnDrawHistory.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)) {
					showErrorDialog(getString(R.string.networkError),
							getString(R.string.noInternetConnection), activity);
					return;
				}

				if (secondFragment != null) {
					FragmentManager fragmentManager = getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					FragmentViewDraws viewDrawsFragment = new FragmentViewDraws();
					fragmentTransaction.replace(R.id.secondFragment,
							viewDrawsFragment);
					fragmentTransaction.commit();
				} else {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(),
							ActivityViewDraws.class);
					startActivity(intent);
				}
			}
		});

		btnUpdateAccount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)) {
					showErrorDialog(getString(R.string.networkError),
							getString(R.string.noInternetConnection), activity);
					return;
				}

				if (secondFragment != null) {
					FragmentManager fragmentManager = getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					FragmentUpdateAccount updateAccountFragment = new FragmentUpdateAccount();
					fragmentTransaction.replace(R.id.secondFragment,
							updateAccountFragment);
					fragmentTransaction.commit();
				} else {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(),
							ActivityUpdateAccount.class);
					startActivity(intent);
				}

			}
		});

		btnBuyCoins.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)) {
					showErrorDialog(getString(R.string.networkError),
							getString(R.string.noInternetConnection), activity);
					return;
				}

				if (secondFragment != null) {
					FragmentManager fragmentManager = getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					FragmentBuyCoins buyCoinsFragment = new FragmentBuyCoins();
					fragmentTransaction.replace(R.id.secondFragment,
							buyCoinsFragment);
					fragmentTransaction.commit();
				} else {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(),
							ActivityBuyCoins.class);
					startActivity(intent);
				}

			}
		});
		
		AsyncTaskGetNextDrawAndUserCoins getInfo = new AsyncTaskGetNextDrawAndUserCoins(this);
		getInfo.execute(sharedPreferences.getString("userId", "0"));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		AsyncTaskGetNextDrawAndUserCoins getInfo = new AsyncTaskGetNextDrawAndUserCoins(this);
		getInfo.execute(sharedPreferences.getString("userId", "0"));
	}

	
	
}

