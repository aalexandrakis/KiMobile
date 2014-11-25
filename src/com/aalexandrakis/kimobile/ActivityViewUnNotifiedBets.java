package com.aalexandrakis.kimobile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.aalexandrakis.kimobile.pojos.BetsArchive;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.aalexandrakis.kimobile.CommonMethods.convertJsonToBetsArchive;
import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;

//TODO refresh adapter and list view
@SuppressLint("DefaultLocale")
public class ActivityViewUnNotifiedBets extends Activity {
	SharedPreferences sharedPreferences;
	ActivityViewUnNotifiedBets unNotifiedBets = this;
	ListView lstArchiveBets;
	Button btnLogin;
	public ActivityViewUnNotifiedBets() {
		super();
	}
	

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_unnotified_bets);
		sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, FragmentActivity.MODE_PRIVATE);
		lstArchiveBets = (ListView) findViewById(R.id.listBetsArchive);
		btnLogin = (Button) findViewById(R.id.btnLoginToPlayAgain);
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),
						ActivityLogin.class);
				startActivity(intent);
				finish();
			}
		});
		
		
		///TODO REPEATED CODE    ////////////////////////////////////////////////////////
		AsyncTaskUnNotifiedBets getUnNotifiedBets = new AsyncTaskUnNotifiedBets(unNotifiedBets);
		getUnNotifiedBets.execute(sharedPreferences.getString("token", ""));
		////////////////////////////////////////////////////////////////////////////////

	}


	
	
	
}

class AsyncTaskUnNotifiedBets extends AsyncTask<String, List<BetsArchive>, List<BetsArchive>>{
	ProgressDialog pg;
	ActivityViewUnNotifiedBets unNotifiedBets;
	List<BetsArchive> bets = new ArrayList<BetsArchive>();

	public AsyncTaskUnNotifiedBets(ActivityViewUnNotifiedBets unnotifiedBets) {
		// TODO Auto-generated constructor stub
		this.unNotifiedBets = unnotifiedBets;
	}
	@Override
	protected void onPostExecute(List<BetsArchive> bets) {
		// TODO Auto-generated method stub
		super.onPostExecute(bets);
		if (bets != null){
			if (bets.isEmpty()){
				Toast.makeText(unNotifiedBets, unNotifiedBets.getString(R.string.toastNoOldBetsFound), Toast.LENGTH_LONG).show();
			}
			AdapterArchiveBets adapter = new AdapterArchiveBets(unNotifiedBets, bets);
			unNotifiedBets.lstArchiveBets.setAdapter(adapter);
			pg.dismiss();

		}

	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pg = new ProgressDialog(unNotifiedBets);
		pg.setTitle(unNotifiedBets.getString(R.string.loading));
		pg.setMessage(unNotifiedBets.getString(R.string.pleasWaitArchiveBets));
		pg.show();

	}

	@Override
	protected  List<BetsArchive> doInBackground(String... params) {
		// TODO Auto-generated method stub
		JSONObject bets =  CommonMethods.httpsUrlConnection("GET", "viewOldBets", "unNotifiedBets", params[0], unNotifiedBets);
		List<BetsArchive> archiveBets = new ArrayList<BetsArchive>();
		JSONArray jsonBets = bets.optJSONArray("bets");
		try {
			for (int i = 0; i < jsonBets.length(); i++) {
				archiveBets.add(convertJsonToBetsArchive(jsonBets.getJSONObject(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return archiveBets;

	}
	
}