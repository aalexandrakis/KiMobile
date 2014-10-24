package com.aalexandrakis.kimobile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.paypal.android.MEP.PayPalResultDelegate;

public class ResultDelegate implements PayPalResultDelegate, Serializable {

	private static final long serialVersionUID = 10001L;

	/**
	 * Notification that the payment has been completed successfully.
	 * 
	 * @param payKey
	 *            the pay key for the payment
	 * @param paymentStatus
	 *            the status of the transaction
	 */
	public void onPaymentSucceeded(String payKey, String paymentStatus) {
		FragmentBuyCoins.resultTitle = "SUCCESS";
		FragmentBuyCoins.resultInfo = "You have successfully completed your transaction.";
		FragmentBuyCoins.resultExtra = "Key: " + payKey;
		FragmentBuyCoins.payKey = payKey;
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		HttpGet httpGet = new HttpGet(Constants.REST_URL + "paypalVerification/" + payKey);

		try {
			response = httpclient.execute(httpGet);
			
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				String responseString = out.toString();
				System.out.println(responseString);
			} else {
				// Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }	

	/**
	 * Notification that the payment has failed.
	 * 
	 * @param paymentStatus
	 *            the status of the transaction
	 * @param correlationID
	 *            the correlationID for the transaction failure
	 * @param payKey
	 *            the pay key for the payment
	 * @param errorID
	 *            the ID of the error that occurred
	 * @param errorMessage
	 *            the error message for the error that occurred
	 */
	public void onPaymentFailed(String paymentStatus, String correlationID,
			String payKey, String errorID, String errorMessage) {
		FragmentBuyCoins.resultTitle = "FAILURE";
		FragmentBuyCoins.resultInfo = errorMessage;
		FragmentBuyCoins.resultExtra = "Error ID: " + errorID
				+ "\nCorrelation ID: " + correlationID + "\nPay Key: " + payKey;
	}

	/**
	 * Notification that the payment was canceled.
	 * 
	 * @param paymentStatus
	 *            the status of the transaction
	 */
	public void onPaymentCanceled(String paymentStatus) {
		FragmentBuyCoins.resultTitle = "CANCELED";
		FragmentBuyCoins.resultInfo = "The transaction has been cancelled.";
		FragmentBuyCoins.resultExtra = "";
	}
}
