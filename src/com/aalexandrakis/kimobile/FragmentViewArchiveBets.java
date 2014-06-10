package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;
import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
//TODO refresh adapter and list view
import com.aalexandrakis.kimobile.pojos.BetsArchive;
//TODO date picker
public class FragmentViewArchiveBets extends Fragment {
	AdapterArchiveBets adapter;
	SharedPreferences sharedPreferences;
	FragmentViewArchiveBets viewBets = this;
	List<BetsArchive> bets = new ArrayList<BetsArchive>();
	EditText txtFilterDate;
	ListView lstArchiveBets;
	Button btnGetBets;
	
	public FragmentViewArchiveBets() {
		super();
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_view_archive_bets, container, false);
		Date date = new Date();
		String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
		sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, FragmentActivity.MODE_PRIVATE);
		txtFilterDate = (EditText) view.findViewById(R.id.txtFilterDate);
		lstArchiveBets = (ListView) view.findViewById(R.id.listBetsArchive);
		btnGetBets = (Button) view.findViewById(R.id.btnGetBets);
		
		txtFilterDate.setText(currentDate);
		btnGetBets.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(getActivity())) {
					showErrorDialog(getString(R.string.networkError),
							getString(R.string.noInternetConnection), getActivity());
					return;
				}
				//lstArchiveBets.removeAllViews();
				String date = CommonMethods.isValidDate(txtFilterDate.getText().toString(), "dd-MM-yyyy");
				if (date != null){
					bets = getBetList(date);
				}
				// TODO Auto-generated method stub
				if (bets.isEmpty()){
					Toast.makeText(getActivity(), getString(R.string.toastNoArchiveBetsFound), Toast.LENGTH_LONG).show();
					getActivity().finish();
				}
				adapter = new AdapterArchiveBets(getActivity(), bets);
		 		adapter.notifyDataSetChanged();
				lstArchiveBets.setAdapter(adapter);
			}
		});
		return view;
		
	}
	
	List<BetsArchive> getBetList(String date){

		ProgressDialog pg = new ProgressDialog(getActivity());
		pg.setTitle(getString(R.string.loading));
		pg.setMessage(getString(R.string.pleasWaitArchiveBets));
		pg.show();
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		HttpPost httpPost = new HttpPost(Constants.REST_URL + "getUserArchiveBetsByDate");
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("userIdString", sharedPreferences.getString("userId", "0")));
		parameters.add(new BasicNameValuePair("date", date));
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
				BetsArchive bet;
				/****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                JSONObject jsonResponse = new JSONObject(responseString);
                  
                /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
                /*******  Returns null otherwise.  *******/
                JSONArray jsonMainNode = jsonResponse.optJSONArray("bets");
                  
                /*********** Process each JSON Node ************/

                int lengthJsonArr = jsonMainNode.length(); 

                for(int i=0; i < lengthJsonArr; i++) {
					bet = new BetsArchive();
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
					bet.setMatches(Integer.valueOf(jsonChildNode.optString("matches")));
					bet.setReturnRate(Float.valueOf(jsonChildNode.optString("returnRate")));
					bet.setDrawTimeStamp(jsonChildNode.optString("drawTimeStamp"));
					bets.add(bet);
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
		return bets;
	}
}