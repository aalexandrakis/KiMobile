package com.aalexandrakis.kimobile;


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import org.json.JSONObject;

import com.aalexandrakis.kimobile.pojos.BetsArchive;
import com.aalexandrakis.kimobile.pojos.Draw;

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
	
	static BetsArchive convertJsonToBetsArchive(JSONObject jsonChildNode){
		BetsArchive bet = new BetsArchive();
		bet.setBetId(new BigInteger(jsonChildNode.optString("betId")));
		bet.setBetDateTime(jsonChildNode.optString("betDateTime"));
		bet.setUserId(new BigInteger(jsonChildNode.optString("userId")));
		bet.setRepeatedDraws(Integer.valueOf(jsonChildNode.optString("repeatedDraws")));
		bet.setRandomChoice(Integer.valueOf(jsonChildNode.optString("randomChoice")));
		bet.setGameType(Integer.valueOf(jsonChildNode.optString("gameType")));
		bet.setBetCoins(Float.valueOf(jsonChildNode.optString("betCoins")));
		bet.setMultiplier(Integer.valueOf(jsonChildNode.optString("multiplier")));
		bet.setBetNumber1(Integer.valueOf(jsonChildNode.optString("betNumber1")));
		bet.setBetNumber2(Integer.valueOf(jsonChildNode.optString("betNumber2")));
		bet.setBetNumber3(Integer.valueOf(jsonChildNode.optString("betNumber3")));
		bet.setBetNumber4(Integer.valueOf(jsonChildNode.optString("betNumber4")));
		bet.setBetNumber5(Integer.valueOf(jsonChildNode.optString("betNumber5")));
		bet.setBetNumber6(Integer.valueOf(jsonChildNode.optString("betNumber6")));
		bet.setBetNumber7(Integer.valueOf(jsonChildNode.optString("betNumber7")));
		bet.setBetNumber8(Integer.valueOf(jsonChildNode.optString("betNumber8")));
		bet.setBetNumber9(Integer.valueOf(jsonChildNode.optString("betNumber9")));
		bet.setBetNumber10(Integer.valueOf(jsonChildNode.optString("betNumber10")));
		bet.setBetNumber11(Integer.valueOf(jsonChildNode.optString("betNumber11")));
		bet.setBetNumber12(Integer.valueOf(jsonChildNode.optString("betNumber12")));
		bet.setDraws(Integer.valueOf(jsonChildNode.optString("draws")));
		bet.setMatches(Integer.valueOf(jsonChildNode.optString("matches")));
		bet.setReturnRate(Float.valueOf(jsonChildNode.optString("returnRate")));
		bet.setDrawTimeStamp(jsonChildNode.optString("drawTimeStamp"));
	
		return bet;
	}
	
	static Draw convertJsonToDraw(JSONObject jsonChildNode){
		Draw draw = new Draw();
		draw.setDrawDateTime(jsonChildNode.optString("drawDateTime"));
		draw.setDrawNumber1(Integer.valueOf(jsonChildNode.optString("drawNumber1")));
		draw.setDrawNumber2(Integer.valueOf(jsonChildNode.optString("drawNumber2")));
		draw.setDrawNumber3(Integer.valueOf(jsonChildNode.optString("drawNumber3")));
		draw.setDrawNumber4(Integer.valueOf(jsonChildNode.optString("drawNumber4")));
		draw.setDrawNumber5(Integer.valueOf(jsonChildNode.optString("drawNumber5")));
		draw.setDrawNumber6(Integer.valueOf(jsonChildNode.optString("drawNumber6")));
		draw.setDrawNumber7(Integer.valueOf(jsonChildNode.optString("drawNumber7")));
		draw.setDrawNumber8(Integer.valueOf(jsonChildNode.optString("drawNumber8")));
		draw.setDrawNumber9(Integer.valueOf(jsonChildNode.optString("drawNumber9")));
		draw.setDrawNumber10(Integer.valueOf(jsonChildNode.optString("drawNumber10")));
		draw.setDrawNumber11(Integer.valueOf(jsonChildNode.optString("drawNumber11")));
		draw.setDrawNumber12(Integer.valueOf(jsonChildNode.optString("drawNumber12")));
		draw.setDrawNumber13(Integer.valueOf(jsonChildNode.optString("drawNumber13")));
		draw.setDrawNumber14(Integer.valueOf(jsonChildNode.optString("drawNumber14")));
		draw.setDrawNumber15(Integer.valueOf(jsonChildNode.optString("drawNumber15")));
		draw.setDrawNumber16(Integer.valueOf(jsonChildNode.optString("drawNumber16")));
		draw.setDrawNumber17(Integer.valueOf(jsonChildNode.optString("drawNumber17")));
		draw.setDrawNumber18(Integer.valueOf(jsonChildNode.optString("drawNumber18")));
		draw.setDrawNumber19(Integer.valueOf(jsonChildNode.optString("drawNumber19")));
		draw.setDrawNumber20(Integer.valueOf(jsonChildNode.optString("drawNumber20")));
		return draw;
	}
	
	static Draw getDraw(List<Draw> draws, String timeStamp){
		for (Draw draw:draws){
			if (draw.getDrawDateTime().substring(0, 19).equals(timeStamp.substring(0, 19))){
				return draw;
			}
		}
		return null;
	}
}
