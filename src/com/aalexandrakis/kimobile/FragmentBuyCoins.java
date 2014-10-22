package com.aalexandrakis.kimobile;

import java.math.BigDecimal;

import android.annotation.SuppressLint;
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

		if (pp == null) {  // Test to see if the library is already initialized
	
			// This main initialization call takes your Context, AppID, and target server
			pp = PayPal.initWithAppID(getActivity(), "APP-80W284485P519543T", PayPal.ENV_SANDBOX);
	
			// Required settings:
	
			// Set the language for the library
			pp.setLanguage("en_US");
			
			// Generate the PayPal checkout button and save it for later use
			launchPayPalButton = pp.getCheckoutButton(getActivity(), PayPal.BUTTON_278x43, CheckoutButton.TEXT_PAY);

			// The OnClick listener for the checkout button
			launchPayPalButton.setOnClickListener(new OnClickListener() {
				
				@SuppressLint("NewApi")
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
					if (coinsToBuy.getText().toString().isEmpty()){
						CommonMethods.showErrorDialog(getString(R.string.paymentError), getString(R.string.amount_must_not_be_empty),  getActivity());
						launchPayPalButton.updateButton();
					} else {
						payment.setSubtotal(new BigDecimal(coinsToBuy.getText().toString()));
	
						// Set the payment type--his can be PAYMENT_TYPE_GOODS,
						// PAYMENT_TYPE_SERVICE, PAYMENT_TYPE_PERSONAL, or PAYMENT_TYPE_NONE
						payment.setPaymentType(PayPal.PAYMENT_TYPE_GOODS);
	
						// PayPalInvoiceData can contain tax and shipping amounts, and an
						// ArrayList of PayPalInvoiceItem that you can fill out.
						// These are not required for any transaction.
//						PayPalInvoiceData invoice = new PayPalInvoiceData();
	
						// Set the tax amount
//						invoice.setTax(new BigDecimal("23"));
						 Intent paypalIntent = PayPal.getInstance().checkout(payment, getActivity(), new ResultDelegate());
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

		}

		
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
		    CommonMethods.showErrorDialog(getString(R.string.paymentError), resultTitle, getActivity());
		    System.out.println("PayKey " + payKey);
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



//class AsyncTaskBuyCoins extends AsyncTask<String, String, String>  {
//	 
//	FragmentBuyCoins buyCoins;
//	public static final String METHOD = "saveBet";
//	boolean error = false;
//	ProgressDialog pg;
//	AsyncTaskBuyCoins(FragmentBuyCoins buyCoins){
//		this.buyCoins = buyCoins;
//	}
//	 @SuppressLint("ShowToast")
//	@Override
//	protected void onPostExecute(String result) {
//		// TODO Auto-generated method stub
//		super.onPostExecute(result);
//		pg.dismiss();
//		if (error == true || result == null || result.equals("40")){
//			showErrorDialog(buyCoins.getString(R.string.betError), buyCoins.getString(R.string.youCanntConnect), buyCoins.getFragmentManager());
//		} else if (result.equals("10")){
//			showErrorDialog(buyCoins.getString(R.string.betError), buyCoins.getString(R.string.gameTypeError), buyCoins.getFragmentManager());
//			buyCoins.txtGameType.requestFocus();			
//		} else if (result.equals("11")){
//			showErrorDialog(buyCoins.getString(R.string.betError), buyCoins.getString(R.string.notEnoughCoins), buyCoins.getFragmentManager());
//			buyCoins.txtGameType.requestFocus();			
//		} else if (result.equals("00")){
//			Toast.makeText(buyCoins.getActivity(), buyCoins.getString(R.string.toastBetAddedSuccessfully), Toast.LENGTH_LONG).show();
//			buyCoins.reset();
//		}
//		
//		
//	}
//
//
//	@Override
//	protected void onPreExecute() {
//		// TODO Auto-generated method stub
//		super.onPreExecute();
//		pg = new ProgressDialog(buyCoins.getActivity());
//		pg.setTitle(buyCoins.getString(R.string.betting));
//		pg.setMessage(buyCoins.getString(R.string.waitToSaveYourBet));
//		pg.show();
//	}
//
//
//	@Override
//	 protected String doInBackground(String... params) {
//		String userId = params[0];
//		Integer repeatedDraws = Integer.valueOf(params[1]);
//		Integer randomChoice = Integer.valueOf(params[2]);
//		Integer gameType = Integer.valueOf(params[3]);
//		Integer multiplier = Integer.valueOf(params[4]);
//		Integer number1 = Integer.valueOf(params[5]);
//		Integer number2 = Integer.valueOf(params[6]);
//		Integer number3 = Integer.valueOf(params[7]);
//		Integer number4 = Integer.valueOf(params[8]);
//		Integer number5 = Integer.valueOf(params[9]);
//		Integer number6 = Integer.valueOf(params[10]);
//		Integer number7 = Integer.valueOf(params[11]);
//		Integer number8 = Integer.valueOf(params[12]);
//		Integer number9 = Integer.valueOf(params[13]);
//		Integer number10 = Integer.valueOf(params[14]);
//		Integer number11 = Integer.valueOf(params[15]);
//		Integer number12 = Integer.valueOf(params[16]);
//		// TODO implement random choice
//         HttpClient httpclient = new DefaultHttpClient();
// 		HttpResponse response;
// 		HttpPost httpPost = new HttpPost(Constants.REST_URL + "saveBet");
// 		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
// 		parameters.add(new BasicNameValuePair("userIdString", userId));
// 		parameters.add(new BasicNameValuePair("repeatedDraws", repeatedDraws.toString()));
// 		parameters.add(new BasicNameValuePair("randomChoice", randomChoice.toString()));
// 		parameters.add(new BasicNameValuePair("gameType", gameType.toString()));
// 		parameters.add(new BasicNameValuePair("multiplier", multiplier.toString()));
// 		parameters.add(new BasicNameValuePair("betNumber1", number1.toString()));
// 		parameters.add(new BasicNameValuePair("betNumber2", number2.toString()));
// 		parameters.add(new BasicNameValuePair("betNumber3", number3.toString()));
// 		parameters.add(new BasicNameValuePair("betNumber4", number4.toString()));
// 		parameters.add(new BasicNameValuePair("betNumber5", number5.toString()));
// 		parameters.add(new BasicNameValuePair("betNumber6", number6.toString()));
// 		parameters.add(new BasicNameValuePair("betNumber7", number7.toString()));
// 		parameters.add(new BasicNameValuePair("betNumber8", number8.toString()));
// 		parameters.add(new BasicNameValuePair("betNumber9", number9.toString()));
// 		parameters.add(new BasicNameValuePair("betNumber10", number10.toString()));
// 		parameters.add(new BasicNameValuePair("betNumber11", number11.toString()));
// 		parameters.add(new BasicNameValuePair("betNumber12", number12.toString()));
//
// 		try {
// 			httpPost.setEntity(new UrlEncodedFormEntity(parameters));
// 		} catch (UnsupportedEncodingException e1) {
// 			// TODO Auto-generated catch block
// 			e1.printStackTrace();
// 		}
// 		try {
// 			response = httpclient.execute(httpPost);
// 			
// 			StatusLine statusLine = response.getStatusLine();
// 			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
// 				ByteArrayOutputStream out = new ByteArrayOutputStream();
// 				response.getEntity().writeTo(out);
// 				out.close();
// 				String responseString = out.toString();
// 				/****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
//                 JSONObject jsonResponse = new JSONObject(responseString);
//                 return jsonResponse.optString("responseCode");
// 			} else {
// 				// Closes the connection.
// 				response.getEntity().getContent().close();
// 				throw new IOException(statusLine.getReasonPhrase());
// 			}
// 		} catch (ClientProtocolException e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 			error = false;
// 		} catch (IOException e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 			error = false;
// 		} catch (Exception e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 			error = false;
// 		}
//
//	 	return null;
//	 }	
//}
// 

