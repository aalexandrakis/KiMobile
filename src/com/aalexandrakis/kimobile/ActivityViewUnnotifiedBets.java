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
import com.aalexandrakis.kimobile.pojos.Draw;
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
import java.util.ArrayList;
import java.util.List;

import static com.aalexandrakis.kimobile.CommonMethods.convertJsonToBetsArchive;
import static com.aalexandrakis.kimobile.CommonMethods.convertJsonToDraw;
import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;

//TODO refresh adapter and list view
@SuppressLint("DefaultLocale")
public class ActivityViewUnnotifiedBets extends Activity {
	AdapterArchiveBets adapter;
	SharedPreferences sharedPreferences;
	ActivityViewUnnotifiedBets unnotifiedBets = this;
	ListView lstArchiveBets;
	Button btnLogin;
	public ActivityViewUnnotifiedBets() {
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
		AsyncTaskUnnotifiedBets getUnnotifiedBets = new AsyncTaskUnnotifiedBets(unnotifiedBets);
		getUnnotifiedBets.execute(sharedPreferences.getString("userId", "0"));
		////////////////////////////////////////////////////////////////////////////////

	}


	
	
	
}

class AsyncTaskUnnotifiedBets extends AsyncTask<String, String, String>{
	ProgressDialog pg;
	ActivityViewUnnotifiedBets unnotifiedBets;
	List<BetsArchive> bets = new ArrayList<BetsArchive>();
	List<Draw> draws = new ArrayList<Draw>();

	public AsyncTaskUnnotifiedBets(ActivityViewUnnotifiedBets unnotifiedBets) {
		// TODO Auto-generated constructor stub
		this.unnotifiedBets = unnotifiedBets;
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result != null){
			JSONObject jsonResponse;
			try {
				jsonResponse = new JSONObject(result);
				System.out.println(jsonResponse.toString());
		        JSONArray jsonMainNode = jsonResponse.optJSONArray("bets");
		        int lengthJsonArr = jsonMainNode.length(); 
		        for(int i=0; i < lengthJsonArr; i++) {
		        	bets.add(convertJsonToBetsArchive(jsonMainNode.getJSONObject(i)));
				}
		        jsonMainNode = jsonResponse.optJSONArray("draws");
		        lengthJsonArr = jsonMainNode.length(); 
		        for(int i=0; i < lengthJsonArr; i++) {
		        	draws.add(convertJsonToDraw(jsonMainNode.getJSONObject(i)));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (bets.isEmpty()){
				Toast.makeText(unnotifiedBets, unnotifiedBets.getString(R.string.toastNoOldBetsFound), Toast.LENGTH_LONG).show();
			}
			AdapterArchiveBets adapter = new AdapterArchiveBets(unnotifiedBets, bets, draws);
			unnotifiedBets.lstArchiveBets.setAdapter(adapter);
			pg.dismiss();
			
		} 

	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pg = new ProgressDialog(unnotifiedBets);
		pg.setTitle(unnotifiedBets.getString(R.string.loading));
		pg.setMessage(unnotifiedBets.getString(R.string.pleasWaitArchiveBets));
		pg.show();

	}

	@Override
	protected  String doInBackground(String... params) {
		// TODO Auto-generated method stub
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		HttpPost httpPost = new HttpPost(Constants.REST_URL + "/viewOldBets/unNotifiedBets");
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("userIdString", params[0]));
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
				return out.toString();
				
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
		
		}
		
		return null;
		 
	}
	
}