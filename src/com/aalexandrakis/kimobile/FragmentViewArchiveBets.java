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
import com.aalexandrakis.kimobile.pojos.Draw;
//TODO date picker
public class FragmentViewArchiveBets extends Fragment {
	AdapterArchiveBets adapter;
	SharedPreferences sharedPreferences;
	FragmentViewArchiveBets viewBets = this;
	List<BetsArchive> bets = new ArrayList<BetsArchive>();
	List<Draw> draws = new ArrayList<Draw>();
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
					getBetList(date, bets, draws);
				}
				// TODO Auto-generated method stub
				if (bets.isEmpty()){
					Toast.makeText(getActivity(), getString(R.string.toastNoArchiveBetsFound), Toast.LENGTH_LONG).show();
					getActivity().finish();
				}
				adapter = new AdapterArchiveBets(getActivity(), bets, draws);
		 		adapter.notifyDataSetChanged();
				lstArchiveBets.setAdapter(adapter);
			}
		});
		return view;
		
	}
	
	void getBetList(String date, List<BetsArchive> bets, List<Draw> draws ){

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

				JSONObject jsonResponse = new JSONObject(responseString);
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
		
	}
}