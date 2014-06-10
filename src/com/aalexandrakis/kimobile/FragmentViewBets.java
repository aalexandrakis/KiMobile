package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aalexandrakis.kimobile.pojos.ActiveBets;

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

		ProgressDialog pg = new ProgressDialog(getActivity());
		pg.setTitle(getString(R.string.loading));
		pg.setMessage(getString(R.string.pleasWaitActiveBets));
		pg.show();
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		HttpPost httpPost = new HttpPost(Constants.REST_URL + "getUserActiveBetsByDate");
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("userIdString", sharedPreferences.getString("userId", "0")));
//		parameters.add(new BasicNameValuePair("date", "2014-06-08"));
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
				ActiveBets bet;
				/****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                JSONObject jsonResponse = new JSONObject(responseString);
                  
                /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
                /*******  Returns null otherwise.  *******/
                JSONArray jsonMainNode = jsonResponse.optJSONArray("bets");
                  
                /*********** Process each JSON Node ************/

                int lengthJsonArr = jsonMainNode.length(); 

                for(int i=0; i < lengthJsonArr; i++) {
					bet = new ActiveBets();
					 /****** Get Object for each JSON node.***********/
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                      
                    /******* Fetch node values **********/
                   
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
		} finally {
			pg.dismiss();
		}

		// TODO Auto-generated method stub
		
		
 		AdapterBets adapter = new AdapterBets(inflater.getContext(), bets);

		/** Setting the list adapter for the ListFragment */
		setListAdapter(adapter);
		return super.onCreateView(inflater, container, savedInstanceState);

	}
}