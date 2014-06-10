package com.aalexandrakis.kimobile;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentManager;



public class CommonMethods {

	public static String encryptPassword(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	public static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}	

	
	static void showErrorDialog(String title, String message, Context context){
		new AlertDialog.Builder(context)
		.setTitle(title)
		.setMessage(message)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		})
		.show();
	}

	
	static void showErrorDialog(String title, String message, FragmentManager manager){
		new SimpleDialog(title, message).show(manager, "MyDialog");
	}
	
	
	static boolean  checkConnectivity(Activity activity){
		try {
		    ConnectivityManager conMgr = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		    return conMgr.getActiveNetworkInfo().isConnected();
		}
		catch (java.lang.NullPointerException ex) 
		{
			return false;
		}
	}
	
	static boolean isLarge(Activity activity){
    	if ((activity.getResources().getConfiguration().screenLayout & 
			    Configuration.SCREENLAYOUT_SIZE_MASK) == 
			        Configuration.SCREENLAYOUT_SIZE_LARGE) {
    		return true;
    	} else {
    		return false;
    	}
    }
	
	static void numberClicked(NumberButton button, Activity activity){
		    if (button.isSelected){
		    	button.setSelected(false);
		    } else {
		    	button.setSelected(true);
		    }
	}
	
	@SuppressLint("SimpleDateFormat")
	static String isValidDate(String strDate, String format){
		String rtnDate = null;
		DateFormat df = new SimpleDateFormat(format);
		try {
			Date date = df.parse(strDate);
			rtnDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rtnDate;
	}
}
