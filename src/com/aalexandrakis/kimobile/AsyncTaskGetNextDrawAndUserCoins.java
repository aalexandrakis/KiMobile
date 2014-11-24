package com.aalexandrakis.kimobile;

import android.os.AsyncTask;
import org.json.JSONObject;

public class AsyncTaskGetNextDrawAndUserCoins extends AsyncTask<String, JSONObject, JSONObject>{
	ActivityMain activity;
	
	AsyncTaskGetNextDrawAndUserCoins(ActivityMain activity){
		this.activity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(JSONObject jsonResponse) {
		// TODO Auto-generated method stub
		super.onPostExecute(jsonResponse);

		activity.txtNextDrawAt.setText(CommonMethods.convertSqlDateStringToEuroDate(jsonResponse.optString("nextDraw")));
		activity.txtCoins.setText(jsonResponse.optString("userCoins"));

	}

	@Override
	protected JSONObject doInBackground(String... params) {
		// TODO Auto-generated method stub
		String userId = params[0];

		JSONObject jsonResponse = CommonMethods.httpsUrlConnection("GET", "info",  userId, null, activity);
		return jsonResponse;
	}
	
}

