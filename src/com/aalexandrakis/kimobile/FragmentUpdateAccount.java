package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class FragmentUpdateAccount extends Fragment {
	EditText txtUserName;
	EditText txtUserEmail;
	EditText txtUserPassword;
	EditText txtRepeatPassword;
	Button btnUpdateAccount;
	FragmentUpdateAccount updateAccount = this;
	SharedPreferences sharedPreferences;
	FrameLayout secondFragment; 
	
	public FragmentUpdateAccount() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_update_account, container, false);
		secondFragment = (FrameLayout) getActivity().findViewById(R.id.secondFragment);
		sharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREFERENCES, Activity.MODE_PRIVATE);
		txtUserName = (EditText) view.findViewById(R.id.txtUserName);
		txtUserEmail = (EditText) view.findViewById(R.id.txtUserEmail);
		txtUserPassword = (EditText) view.findViewById(R.id.txtUserPassword);
		txtRepeatPassword = (EditText) view.findViewById(R.id.txtRepeatPassword);
		btnUpdateAccount = (Button) view.findViewById(R.id.btnUpdateAccount);
		
		
		txtUserName.setText(sharedPreferences.getString("userName", ""));
		txtUserEmail.setText(sharedPreferences.getString("userEmail", ""));
		txtUserPassword.setText(sharedPreferences.getString("userPassword", ""));
		
		btnUpdateAccount.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(updateAccount.getActivity())){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection), getFragmentManager());
					return;
				}
				if (txtUserName.length() == 0){
					showErrorDialog(getString(R.string.updateAccountError), getString(R.string.userNameMissing), getFragmentManager());
					txtUserName.requestFocus();
					return;
				}
				if (txtUserEmail.length() == 0){
					showErrorDialog(getString(R.string.updateAccountError), getString(R.string.userEmailIsMissing), getFragmentManager());
					txtUserEmail.requestFocus();
					return;
				}
				
				if (!txtUserEmail.getText().toString().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")){
					showErrorDialog(getString(R.string.updateAccountError), getString(R.string.userEmailErrorformat), getFragmentManager());
					txtUserEmail.requestFocus();
					return;
				}
				
				if (txtUserPassword.length() == 0){
					showErrorDialog(getString(R.string.updateAccountError), getString(R.string.userPasswordMissing), getFragmentManager());
					txtUserPassword.requestFocus();
					return;
				}
				

				if (!txtUserPassword.getText().toString().equals(txtRepeatPassword.getText().toString())){
					showErrorDialog(getString(R.string.updateAccountError), getString(R.string.passwordNotEqualWithRepeated), getFragmentManager());
					txtRepeatPassword.requestFocus();
					return;
				}
				
				AsyncTaskUpdateAccount updateAccountTask = new AsyncTaskUpdateAccount(updateAccount);
				updateAccountTask.execute(sharedPreferences.getString("userId", "0"), txtUserName.getText().toString(), txtUserEmail.getText().toString(), CommonMethods.encryptPassword(txtUserPassword.getText().toString()));
			}
		});
		return view;
	}
}


 class AsyncTaskUpdateAccount extends AsyncTask<String, String, String>  {
	 
	FragmentUpdateAccount updateAccount;
	public static final String METHOD = "saveUser";
	boolean error = false;
	ProgressDialog pg;
	String userId;
	String userName;
	String userEmail;
	String password;
	AsyncTaskUpdateAccount(FragmentUpdateAccount updateAccount){
		this.updateAccount = updateAccount;
	}
	 @SuppressLint("ShowToast")
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pg.dismiss();
		if (error == true || result == null || result.equals("40")){
			showErrorDialog(updateAccount.getString(R.string.updateAccountError), updateAccount.getString(R.string.youCanntConnect), updateAccount.getFragmentManager());
		} else if (result.equals("10")){
			showErrorDialog(updateAccount.getString(R.string.updateAccountError), updateAccount.getString(R.string.userEmailExists), updateAccount.getFragmentManager());
			updateAccount.txtUserEmail.requestFocus();			
		} else if (result.equals("11")){
			showErrorDialog(updateAccount.getString(R.string.updateAccountError), updateAccount.getString(R.string.userNameExists), updateAccount.getFragmentManager());
			updateAccount.txtUserName.requestFocus();
		} else if (result.equals("00")){
			Toast.makeText(updateAccount.getActivity(), updateAccount.getString(R.string.toastUserUpdatedSuccesfully), Toast.LENGTH_LONG).show();
			SharedPreferences.Editor editor = updateAccount.sharedPreferences.edit();
			editor.putString("userName", userName);
			editor.putString("userEmail", userEmail);
			editor.putString("userPassword", password);
			editor.commit();
			
			if (updateAccount.secondFragment == null){
				updateAccount.getActivity().finish();
			}
		}
		
	}


	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pg = new ProgressDialog(updateAccount.getActivity());
		pg.setTitle(updateAccount.getString(R.string.updating));
		pg.setMessage(updateAccount.getString(R.string.waitToUpdateAccount));
		pg.show();
	}


	@Override
	 protected String doInBackground(String... params) {
		String userId = params[0];
		String userName = params[1];
		String userEmail = params[2];
		String password = params[3];
		// TODO Auto-generated method stub
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		HttpPost httpPost = new HttpPost(Constants.REST_URL + "saveUser");
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("userIdString", userId));
		parameters.add(new BasicNameValuePair("userName", userName));
		parameters.add(new BasicNameValuePair("userEmail", userEmail));
		parameters.add(new BasicNameValuePair("userPassword", password));
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
				/****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                JSONObject jsonResponse = new JSONObject(responseString);
                return jsonResponse.optString("responseCode");
			} else {
				// Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;
		}
	 	return null;
	 }	

}


