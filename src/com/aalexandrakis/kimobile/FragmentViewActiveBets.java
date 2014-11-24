package com.aalexandrakis.kimobile;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.aalexandrakis.kimobile.pojos.ActiveBets;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;

public class FragmentViewActiveBets extends ListFragment {
	SharedPreferences sharedPreferences;
	FragmentViewActiveBets viewBets = this;
	public FragmentViewActiveBets() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//getActivity();
		sharedPreferences = getActivity().getSharedPreferences(
				SHARED_PREFERENCES, FragmentActivity.MODE_PRIVATE);
		FragmentViewActiveBets listFragment = this;

		// TODO Auto-generated method stub
		AsyncTaskActiveBets getActiveBets = new AsyncTaskActiveBets(listFragment);
		getActiveBets.execute(sharedPreferences.getString("token", ""));
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}

class AsyncTaskActiveBets extends AsyncTask<String, List<ActiveBets>, List<ActiveBets>>{
	FragmentViewActiveBets listFragment;
	ProgressDialog pg;
	
	AsyncTaskActiveBets(FragmentViewActiveBets listFragment){
		this.listFragment = listFragment;
	}
	
	
	@Override
	protected void onPostExecute(List<ActiveBets> bets) {
		// TODO Auto-generated method stub
		super.onPostExecute(bets);
		pg.dismiss();
		if (bets.isEmpty() ){
			Toast.makeText(listFragment.getActivity(), listFragment.getString(R.string.toastNoActiveBetsFound), Toast.LENGTH_LONG).show();
		} else {
			AdapterActiveBets adapter = new AdapterActiveBets(listFragment.getActivity(), bets);
			/** Setting the list adapter for the ListFragment */
			listFragment.setListAdapter(adapter);

		}

	}


	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pg = new ProgressDialog(listFragment.getActivity());
		pg.setTitle(listFragment.getString(R.string.loading));
		pg.setMessage(listFragment.getString(R.string.pleasWaitActiveBets));
		pg.show();
		
	}


	@Override
	protected List<ActiveBets> doInBackground(String... params) {
		// TODO Auto-generated method stub
			JSONObject bets =  CommonMethods.httpsUrlConnection("GET", "viewActiveBets", null, params[0], listFragment.getActivity());
			List<ActiveBets> activeBets = new ArrayList<ActiveBets>();
			JSONArray jsonBets = bets.optJSONArray("bets");
			try {
				for (int i = 0; i < jsonBets.length(); i++) {
					JSONObject jsonBet = jsonBets.getJSONObject(i);
					ActiveBets bet = new ActiveBets();
					bet.setBetId(new BigInteger(jsonBet.optString("betId")));
					bet.setBetDateTime(CommonMethods.convertSqlDateStringToEuroDate(jsonBet.optString("betDateTime")));
					bet.setUserId(new BigInteger(jsonBet.optString("userId")));
					bet.setRepeatedDraws(Integer.valueOf(jsonBet.optString("repeatedDraws")));
					bet.setRandomChoice(Integer.valueOf(jsonBet.optString("randomChoice")));
					bet.setGameType(Integer.valueOf(jsonBet.optString("gameType")));
					bet.setBetCoins(Float.valueOf(jsonBet.optString("betCoins")));
					bet.setMultiplier(Integer.valueOf(jsonBet.optString("multiplier")));
					bet.setBetNumber1(Integer.valueOf(jsonBet.optString("betNumber1")));
					bet.setBetNumber2(Integer.valueOf(jsonBet.optString("betNumber2")));
					bet.setBetNumber3(Integer.valueOf(jsonBet.optString("betNumber3")));
					bet.setBetNumber4(Integer.valueOf(jsonBet.optString("betNumber4")));
					bet.setBetNumber5(Integer.valueOf(jsonBet.optString("betNumber5")));
					bet.setBetNumber6(Integer.valueOf(jsonBet.optString("betNumber6")));
					bet.setBetNumber7(Integer.valueOf(jsonBet.optString("betNumber7")));
					bet.setBetNumber8(Integer.valueOf(jsonBet.optString("betNumber8")));
					bet.setBetNumber9(Integer.valueOf(jsonBet.optString("betNumber9")));
					bet.setBetNumber10(Integer.valueOf(jsonBet.optString("betNumber10")));
					bet.setBetNumber11(Integer.valueOf(jsonBet.optString("betNumber11")));
					bet.setBetNumber12(Integer.valueOf(jsonBet.optString("betNumber12")));
					bet.setDraws(Integer.valueOf(jsonBet.optString("draws")));
					activeBets.add(bet);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		return activeBets;
	}


	}
