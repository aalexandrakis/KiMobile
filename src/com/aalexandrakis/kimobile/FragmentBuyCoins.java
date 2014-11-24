package com.aalexandrakis.kimobile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.paypal.android.MEP.CheckoutButton;
import com.paypal.android.MEP.PayPal;
import com.paypal.android.MEP.PayPalPayment;

import java.math.BigDecimal;


public class FragmentBuyCoins extends Fragment {
		
	EditText coinsToBuy;
	SharedPreferences sharedPreferences;
	
	public static String resultTitle;
	public static String resultInfo;
	public static String resultExtra;
	public static String payKey;
	
	CheckoutButton launchPayPalButton;
	
	public FragmentBuyCoins() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_buy_coins, container, false);
		
		sharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREFERENCES, Activity.MODE_PRIVATE);
		
		coinsToBuy = (EditText) view.findViewById(R.id.coinsToBuy);
//		coinsToBuy.setText(1);
		
		//Paypal instatiation
		PayPal pp = PayPal.getInstance();

//		if (pp == null) {  // Test to see if the library is already initialized
	
			// This main initialization call takes your Context, AppID, and target server
			pp = PayPal.initWithAppID(getActivity(), "APP-80W284485P519543T", PayPal.ENV_SANDBOX);
	
			// Required settings:
	
			// Set the language for the library
			pp.setLanguage("en_US");
			
			// Generate the PayPal checkout button and save it for later use
			launchPayPalButton = pp.getCheckoutButton(getActivity(), PayPal.BUTTON_278x43, CheckoutButton.TEXT_PAY);

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
					if (coinsToBuy.getText().toString().equals("")){
						CommonMethods.showErrorDialog(getString(R.string.paymentError), getString(R.string.amount_must_not_be_empty),  getActivity());
						launchPayPalButton.updateButton();
					} else {
						payment.setSubtotal(new BigDecimal(coinsToBuy.getText().toString()));
	
						// Set the payment type--his can be PAYMENT_TYPE_GOODS,
						// PAYMENT_TYPE_SERVICE, PAYMENT_TYPE_PERSONAL, or PAYMENT_TYPE_NONE
						payment.setPaymentType(PayPal.PAYMENT_TYPE_SERVICE);
						payment.setMerchantName("KiMobile Application");
						payment.setCustomID(sharedPreferences.getString("userId", ""));
						// PayPalInvoiceData can contain tax and shipping amounts, and an
						// ArrayList of PayPalInvoiceItem that you can fill out.
						// These are not required for any transaction.
//						PayPalInvoiceData invoice = new PayPalInvoiceData();
	
						// Set the tax amount
//						invoice.setTax(new BigDecimal("23"));
						Intent paypalIntent = PayPal.getInstance().checkout(payment, getActivity(), new ResultDelegate(sharedPreferences.getString("userId", "")));
						startActivityForResult(paypalIntent, 1);
					}
				}
			});
			
			// Add the listener to the layout
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LayoutParams.WRAP_CONTENT,
			LayoutParams.WRAP_CONTENT);
//			params.addRule(LinearLayout.ALIGN_PARENT_BOTTOM);
			params.topMargin = 20;
			launchPayPalButton.setLayoutParams(params);
			launchPayPalButton.setId(10001);
			
			((LinearLayout) view.findViewById(R.id.buyCoinsLayout)).addView(launchPayPalButton);
			((LinearLayout) view.findViewById(R.id.buyCoinsLayout)).setGravity(Gravity.CENTER_HORIZONTAL);

//		}

		
		return view;
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		PayPalActivityResult(requestCode, resultCode, data);
	}

	public void PayPalActivityResult(int requestCode, int resultCode, Intent intent) {
	    if(requestCode != 1) {
	    	return;
	    } else {
//		    Toast.makeText(getActivity() ,resultTitle , Toast.LENGTH_SHORT).show();
	    	if (resultTitle.equals("SUCCESS")){
	    		CommonMethods.showErrorDialog(getString(R.string.paymentOk), getString(R.string.payment_thank_you), getActivity());
	    		launchPayPalButton.updateButton();
	    		getActivity().finish();
	    	} else {
	    		CommonMethods.showErrorDialog(getString(R.string.paymentError), resultInfo + "\n" + resultExtra, getActivity());
	    		launchPayPalButton.updateButton();
	    	}	
//		    System.out.println("PayKey " + payKey);
//		    System.out.println("Request Code  " + requestCode);
		 }
	}

	@Override
	public void onResume() {
        /**
         * The CheckoutButton has to be updated each time the Activity is
         * resumed, otherwise the onClickListener of CheckoutButton will not work
         **/
        if (launchPayPalButton != null && (launchPayPalButton instanceof CheckoutButton))
        	launchPayPalButton.updateButton();
        super.onResume();
    }
}
