package com.aalexandrakis.kimobile;

import java.io.Serializable;

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
