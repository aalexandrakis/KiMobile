package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;
import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;

import java.math.BigDecimal;

import com.paypal.android.MEP.CheckoutButton;
import com.paypal.android.MEP.PayPal;
import com.paypal.android.MEP.PayPalInvoiceData;
import com.paypal.android.MEP.PayPalPayment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
		
		//Paypal instatiation
		PayPal pp = PayPal.getInstance();

		if (pp == null) {  // Test to see if the library is already initialized
	
			// This main initialization call takes your Context, AppID, and target server
			pp = PayPal.initWithAppID(this, "APP-80W284485P519543T", PayPal.ENV_SANDBOX);
	
			// Required settings:
	
			// Set the language for the library
			pp.setLanguage("en_US");
		}

		// Generate the PayPal checkout button and save it for later use
		CheckoutButton launchPayPalButton = pp.getCheckoutButton(this, PayPal.BUTTON_278x43, CheckoutButton.TEXT_PAY);

		// The OnClick listener for the checkout button
		launchPayPalButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Create a basic PayPal payment
				PayPalPayment payment = new PayPalPayment();

				// Set the currency type
				payment.setCurrencyType("EUR");

				// Set the recipient for the payment (can be a phone number)
				payment.setRecipient("aalexandrakis@hotmail.com");

				// Set the payment amount, excluding tax and shipping costs
				payment.setSubtotal(new BigDecimal("10"));

				// Set the payment type--his can be PAYMENT_TYPE_GOODS,
				// PAYMENT_TYPE_SERVICE, PAYMENT_TYPE_PERSONAL, or PAYMENT_TYPE_NONE
				payment.setPaymentType(PayPal.PAYMENT_TYPE_GOODS);

				// PayPalInvoiceData can contain tax and shipping amounts, and an
				// ArrayList of PayPalInvoiceItem that you can fill out.
				// These are not required for any transaction.
				PayPalInvoiceData invoice = new PayPalInvoiceData();

				// Set the tax amount
				invoice.setTax(new BigDecimal("23"));
				
				 Intent paypalIntent = PayPal.getInstance().checkout(payment, activity);
				 startActivityForResult(paypalIntent, 1);
			}
		});

		// Add the listener to the layout
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LayoutParams.WRAP_CONTENT,
		LayoutParams.WRAP_CONTENT);
//		params.addRule(LinearLayout.ALIGN_PARENT_BOTTOM);
		params.topMargin = 20;
		launchPayPalButton.setLayoutParams(params);
		launchPayPalButton.setId(10001);
		((LinearLayout) findViewById(R.id.LinearLayout1)).addView(launchPayPalButton);
		((LinearLayout) findViewById(R.id.LinearLayout1)).setGravity(Gravity.CENTER_HORIZONTAL);
		
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

