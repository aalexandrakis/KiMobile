package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;
import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.aalexandrakis.kimobile.pojos.ActiveBets;
import com.aalexandrakis.kimobile.pojos.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class FragmentViewBets extends ListFragment {
	SharedPreferences sharedPreferences;
	FragmentViewBets viewBets = this;
	List<ActiveBets> bets = new ArrayList<ActiveBets>();
	public FragmentViewBets() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity();
		sharedPreferences = getActivity().getSharedPreferences(
				SHARED_PREFERENCES, FragmentActivity.MODE_PRIVATE);

		// AsyncTaskGetBets getBets = new AsyncTaskGetBets(viewBets);
		// getBets.execute(sharedPreferences.getString("userId", "0"),
		// "2014-06-08");
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		HttpPost httpPost = new HttpPost(Constants.REST_URL + "getUserActiveBetsByDate");
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("userIdString", sharedPreferences.getString("userId", "0")));
		parameters.add(new BasicNameValuePair("date", "2014-06-08"));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(parameters));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			response = httpclient.execute(httpPost);
			
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				String responseString = out.toString();
				JSONArray jsonBets = new JSONArray(responseString);
				ActiveBets bet;
				for (int i = 0 ; i < jsonBets.length() ; i++){
					bet = new ActiveBets();
					bet.setBetId(new BigInteger(jsonBets.getJSONObject(i).getString("betId")));
					bet.setBetDateTime(jsonBets.getJSONObject(i).getString("betDateTime"));
					bet.setUserId(new BigInteger(jsonBets.getJSONObject(i).getString("userId")));
					bet.setRepeatedDraws(Integer.valueOf(jsonBets.getJSONObject(i).getString("repeatedDraws")));
					bet.setRandomChoice(Integer.valueOf(jsonBets.getJSONObject(i).getString("randomChoice")));
					bet.setGameType(Integer.valueOf(jsonBets.getJSONObject(i).getString("gameType")));
					bet.setBetCoins(Float.valueOf(jsonBets.getJSONObject(i).getString("betCoins")));
					bet.setMultiplier(Integer.valueOf(jsonBets.getJSONObject(i).getString("multiplier")));
					bet.setBetNumber1(Integer.valueOf(jsonBets.getJSONObject(i).getString("betNumber1")));
					bet.setBetNumber2(Integer.valueOf(jsonBets.getJSONObject(i).getString("betNumber2")));
					bet.setBetNumber3(Integer.valueOf(jsonBets.getJSONObject(i).getString("betNumber3")));
					bet.setBetNumber4(Integer.valueOf(jsonBets.getJSONObject(i).getString("betNumber4")));
					bet.setBetNumber5(Integer.valueOf(jsonBets.getJSONObject(i).getString("betNumber5")));
					bet.setBetNumber6(Integer.valueOf(jsonBets.getJSONObject(i).getString("betNumber6")));
					bet.setBetNumber7(Integer.valueOf(jsonBets.getJSONObject(i).getString("betNumber7")));
					bet.setBetNumber8(Integer.valueOf(jsonBets.getJSONObject(i).getString("betNumber8")));
					bet.setBetNumber9(Integer.valueOf(jsonBets.getJSONObject(i).getString("betNumber9")));
					bet.setBetNumber10(Integer.valueOf(jsonBets.getJSONObject(i).getString("betNumber10")));
					bet.setBetNumber11(Integer.valueOf(jsonBets.getJSONObject(i).getString("betNumber11")));
					bet.setBetNumber12(Integer.valueOf(jsonBets.getJSONObject(i).getString("betNumber12")));
					bets.add(bet);
					Log.d("bet", String.valueOf(i));
				}
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

		// TODO Auto-generated method stub
		
		
 		AdapterBets adapter = new AdapterBets(inflater.getContext(), bets);

		/** Setting the list adapter for the ListFragment */
		setListAdapter(adapter);
		return super.onCreateView(inflater, container, savedInstanceState);

	}
}

