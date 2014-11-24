package com.aalexandrakis.kimobile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.aalexandrakis.kimobile.CommonMethods.checkConnectivity;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;

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
//		txtUserPassword.setText(sharedPreferences.getString("userPassword", ""));
		
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
				updateAccountTask.execute(sharedPreferences.getString("userId", "0"), txtUserName.getText().toString(),
						txtUserEmail.getText().toString(), CommonMethods.encryptPassword(txtUserPassword.getText().toString()), sharedPreferences.getString("token", ""));
			}
		});
		return view;
	}
}


 class AsyncTaskUpdateAccount extends AsyncTask<String, JSONObject, JSONObject>  {
	 
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
	protected void onPostExecute(JSONObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pg.dismiss();
		if (error == true || result == null || result.optString("message") != ""){
			showErrorDialog(updateAccount.getString(R.string.updateAccountError), result.optString("message"), updateAccount.getFragmentManager());
			updateAccount.txtUserName.requestFocus();
		} else {
			JSONObject userJsonObject = result.optJSONObject("user");
			Toast.makeText(updateAccount.getActivity(), updateAccount.getString(R.string.toastUserUpdatedSuccesfully), Toast.LENGTH_LONG).show();
			SharedPreferences.Editor editor = updateAccount.sharedPreferences.edit();
			editor.putString("userName", userJsonObject.optString("userName"));
			editor.putString("userEmail", userJsonObject.optString("userEmail"));
			editor.putString("userPassword", userJsonObject.optString("userPassword"));
			editor.putString("userCoins", userJsonObject.optString("userCoins"));
			editor.putInt("userLevel", userJsonObject.optInt("userLevel"));
			try {
				editor.putString("token", Base64.encodeToString((userJsonObject.optString("userName") + ":" + userJsonObject.optString("userPassword")).getBytes("UTF-8"), Base64.NO_WRAP));
			} catch (UnsupportedEncodingException e){
				e.printStackTrace();
			}
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
	 protected JSONObject doInBackground(String... params) {
		JSONObject jsonParams = new JSONObject();
		try {
			jsonParams.put("userId", params[0]);
			jsonParams.put("userName", params[1]);
			jsonParams.put("email", params[2]);
			jsonParams.put("password", params[3]);

			return CommonMethods.httpsUrlConnection("PUT", "myAccount", jsonParams.toString(), params[4], updateAccount.getActivity());
		} catch (JSONException e){
			e.printStackTrace();
			return null;
		}
	 }

}


