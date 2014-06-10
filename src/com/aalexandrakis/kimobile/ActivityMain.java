package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;
import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;
import android.app.Activity;
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

public class ActivityMain extends FragmentActivity {
	SharedPreferences sharedPreferences;
	Button btnPlayNow;
	Button btnMyActiveBets;
	Button btnDrawHistory;
	Button btnUpdateAccount;
	Activity activity = this;
	FrameLayout secondFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sharedPreferences = getSharedPreferences(SHARED_PREFERENCES,
				MODE_PRIVATE);
		setContentView(R.layout.activity_main);
		secondFragment = (FrameLayout) findViewById(R.id.secondFragment);
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
		btnDrawHistory = (Button) findViewById(R.id.btnDrawHistory);
		btnUpdateAccount = (Button) findViewById(R.id.btnUpdateAccount);
		final Activity activity = this;
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

	}

}
