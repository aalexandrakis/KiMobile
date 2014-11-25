package com.aalexandrakis.kimobile;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.util.Log;
import com.aalexandrakis.kimobile.pojos.BetsArchive;
import com.aalexandrakis.kimobile.pojos.Draw;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.*;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.TimeZone;

import static com.aalexandrakis.kimobile.Constants.HOST;


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
			rtnDate = new SimpleDateFormat("ddMMyyyy").format(date);
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
		bet.setDrawNumbers(jsonChildNode.optString("drawNumbers"));

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
	
	static boolean checkPlayServices(Activity activity) {
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
	    if (resultCode != ConnectionResult.SUCCESS) {
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
	                    Constants.PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } else {
	            Log.i("checkPlayServices", "This device is not supported.");
	            activity.finish();
	        }
	        return false;
	    }
	    return true;
	}
	
	static String httpGetCall(String url){
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		HttpGet httpGet = new HttpGet(url);

		try {
			response = httpclient.execute(httpGet);
			
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				System.out.println(out.toString());
				return out.toString();
			} else {
				// Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 *
	 */
	public static SSLContext getSSLContext(Context context) throws Exception{
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		InputStream caInput = context.getResources().openRawResource(R.raw.my);
		java.security.cert.Certificate ca = null;
		try {
			ca = cf.generateCertificate(caInput);
			System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
		} catch (CertificateException e) {
			e.printStackTrace();
		} finally {
			caInput.close();
		}

		// Create a KeyStore containing our trusted CAs
		String keyStoreType = KeyStore.getDefaultType();
		KeyStore keyStore = KeyStore.getInstance(keyStoreType);
		keyStore.load(null, null);
		keyStore.setCertificateEntry("ca", ca);

		// Create a TrustManager that trusts the CAs in our KeyStore
		String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
		tmf.init(keyStore);

		// Create an SSLContext that uses our TrustManager
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, tmf.getTrustManagers(), null);

		return sslContext;

	}

	/**
	 *
	 * @param method GET, POST...
	 * @param route nodejs route signIn, singUp...
	 * @param parameters if method is GET then param1/param2 else JSONObject
	 * @param authHeader
	 * @param activityContext
	 * @return
	 */
	public static JSONObject httpsUrlConnection(String method, String route, String parameters, String authHeader, Context activityContext){
		try {
			URL url = new URL(Constants.REST_URL + route);
			JSONObject jsonParameters = null;
			if (method.equals("GET")){
				url = new URL(Constants.REST_URL + route + (parameters != null ? "/" + parameters : ""));
			} else {
				jsonParameters = new JSONObject(parameters);
			}
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(CommonMethods.getSSLContext(activityContext).getSocketFactory());
			conn.setHostnameVerifier(CommonMethods.hostnameVerifier);
			conn.setRequestMethod(method);
			conn.setRequestProperty("Host", HOST);
			conn.setRequestProperty("User-Agent", "");
			conn.setRequestProperty("Accept", "application/json, text/plain, */*");
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
			if (authHeader != null) {
				conn.setRequestProperty("Authorization", "Basic " + authHeader);
			}
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			if (!method.equals("GET")) {
				conn.setRequestProperty("Content-Length", String.valueOf(jsonParameters.toString().length()));
				conn.getOutputStream().write(jsonParameters.toString().getBytes("UTF-8"));
				conn.getOutputStream().close();
			}
			int responseCode = conn.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuilder stringBuilder = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				stringBuilder.append(inputLine);
			in.close();
			return new JSONObject(stringBuilder.toString());
		} catch (IOException e){
			e.printStackTrace();
			try {
				return new JSONObject().put("message", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				return new JSONObject().put("message", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}

		}

		return null;

	}
	// Create an HostnameVerifier that hardwires the expected hostname.
    // Note that is different than the URL's hostname:
    // example.com versus example.org
	public static HostnameVerifier hostnameVerifier = new HostnameVerifier() {
		@Override
		public boolean verify(String hostname, SSLSession session) {

//			WARNING REMOVE IT ONLY FOR TEST
//			HostnameVerifier hv =
//					HttpsURLConnection.getDefaultHostnameVerifier();
//			return hv.verify(Constants.HOST, session);
			return true;
		}
	};

	//TODO review this method against the betDateTime and next_draw
	public static String convertSqlDateStringToEuroDate(String sqlDate){
		DateFormat dfInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		dfInput.setTimeZone(TimeZone.getTimeZone("UTC"));
		DateFormat dfOutput = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try {
			return dfOutput.format(dfInput.parse(sqlDate));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Gets the current registration ID for application on GCM service.
	 * <p>
	 * If result is empty, the app needs to register.
	 *
	 * @return registration ID, or empty string if there is no existing
	 *         registration ID.
	 */
	//private String getRegistrationId(Context context) {
	    //final SharedPreferences prefs = getGCMPreferences(context);
	    //String registrationId = prefs.getString(Constants.PROPERTY_REG_ID, "");
	    //if (registrationId.isEmpty()) {
	    //    Log.i(TAG, "Registration not found.");
	    //    return "";
	    //}
	    // Check if app was updated; if so, it must clear the registration ID
	    // since the existing regID is not guaranteed to work with the new
	    // app version.
//	    int registeredVersion = prefs.getInt(Constants.PROPERTY_APP_VERSION, Integer.MIN_VALUE);
//	    int currentVersion = getAppVersion(context);
//	    if (registeredVersion != currentVersion) {
//	        Log.i(TAG, "App version changed.");
//	        return "";
//	    }
//	    return registrationId;
	//}

	/**
	 * @return Application's version code from the {@code PackageManager}.
	 */
//	private static int getAppVersion(Context context) {
//	    try {
//	        PackageInfo packageInfo = context.getPackageManager()
//	                .getPackageInfo(context.getPackageName(), 0);
//	        return packageInfo.versionCode;
//	    } catch (NameNotFoundException e) {
//	        // should never happen
//	        throw new RuntimeException("Could not get package name: " + e);
//	    }
//	}
}
