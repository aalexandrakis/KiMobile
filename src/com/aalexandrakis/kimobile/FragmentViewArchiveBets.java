package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.convertJsonToBetsArchive;
import static com.aalexandrakis.kimobile.CommonMethods.convertJsonToDraw;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;
import static com.aalexandrakis.kimobile.Constants.SHARED_PREFERENCES;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;





//TODO refresh adapter and list view
import com.aalexandrakis.kimobile.pojos.BetsArchive;
import com.aalexandrakis.kimobile.pojos.Draw;
@SuppressLint("DefaultLocale")
public class FragmentViewArchiveBets extends Fragment {
	AdapterArchiveBets adapter;
	SharedPreferences sharedPreferences;
	FragmentViewArchiveBets archiveBets = this;
	TextView txtFilterDate;
	ListView lstArchiveBets;
	
	public FragmentViewArchiveBets() {
		super();
	}

	@SuppressLint({ "SimpleDateFormat", "DefaultLocale" })
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_view_archive_bets, container, false);
		Date date = new Date();
		String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
		sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, FragmentActivity.MODE_PRIVATE);
		txtFilterDate = (TextView) view.findViewById(R.id.txtFilterDate);
		final DatePickerDialog.OnDateSetListener setDateListener = new DatePickerDialog.OnDateSetListener() {
			
			@SuppressLint("DefaultLocale")
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				String strDay = String.format("%02d", dayOfMonth);
				String strMonth = String.format("%02d", monthOfYear + 1);
				String strYear = String.format("%04d", year);
				txtFilterDate.setText(new StringBuilder().append(strDay).append("-")
						.append(strMonth).append("-")
						.append(strYear).toString());
				if (!checkConnectivity(getActivity())) {
					showErrorDialog(getString(R.string.networkError),
							getString(R.string.noInternetConnection), getActivity());
					return;
				}
				String date = CommonMethods.isValidDate(txtFilterDate.getText().toString(), "dd-MM-yyyy");
				if (date != null){
					AsyncTaskArchivebets getArchiveBets = new AsyncTaskArchivebets(archiveBets);
					getArchiveBets.execute(sharedPreferences.getString("userId", "0"), date);
				}
			}
		};

		lstArchiveBets = (ListView) view.findViewById(R.id.listBetsArchive);
		
		txtFilterDate.setText(currentDate);
		txtFilterDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] d = txtFilterDate.getText().toString().split("-");
				DatePickerDialog dialog = new DatePickerDialog(getActivity(), setDateListener, 
												Integer.valueOf(d[2]), 
												Integer.valueOf(d[1]) - 1, 
												Integer.valueOf(d[0]));
				dialog.show();
			}
		});

		///TODO REPEATED CODE    ////////////////////////////////////////////////////////
		String strDate = CommonMethods.isValidDate(txtFilterDate.getText().toString(), "dd-MM-yyyy");
		AsyncTaskArchivebets getArchiveBets = new AsyncTaskArchivebets(archiveBets);
		getArchiveBets.execute(sharedPreferences.getString("userId", "0"), strDate);
		////////////////////////////////////////////////////////////////////////////////
		return view;
		
	}
	
	
}

class AsyncTaskArchivebets extends AsyncTask<String, String, String>{
	ProgressDialog pg;
	FragmentViewArchiveBets archiveBets;
	List<BetsArchive> bets = new ArrayList<BetsArchive>();
	List<Draw> draws = new ArrayList<Draw>();

	public AsyncTaskArchivebets(FragmentViewArchiveBets archiveBets) {
		// TODO Auto-generated constructor stub
		this.archiveBets = archiveBets;
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
				Toast.makeText(archiveBets.getActivity(), archiveBets.getString(R.string.toastNoOldBetsFound), Toast.LENGTH_LONG).show();
			}
			AdapterArchiveBets adapter = new AdapterArchiveBets(archiveBets.getActivity(), bets, draws);
	 		archiveBets.lstArchiveBets.setAdapter(adapter);
			pg.dismiss();
			
		} 

	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pg = new ProgressDialog(archiveBets.getActivity());
		pg.setTitle(archiveBets.getString(R.string.loading));
		pg.setMessage(archiveBets.getString(R.string.pleasWaitArchiveBets));
		pg.show();

	}

	@Override
	protected  String doInBackground(String... params) {
		// TODO Auto-generated method stub
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		HttpPost httpPost = new HttpPost(Constants.REST_URL + "getUserArchiveBetsByDate");
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("userIdString", params[0]));
		parameters.add(new BasicNameValuePair("date", params[1]));
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