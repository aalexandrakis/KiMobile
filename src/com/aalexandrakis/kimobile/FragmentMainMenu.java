package com.aalexandrakis.kimobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import static com.aalexandrakis.kimobile.CommonMethods.*;

public class FragmentMainMenu extends Fragment{
	Button btnPlayNow;
	Button btnMyBets;
	Button btnDrawHistory;
	Button btnUpdateAccount;
	Fragment uaf;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
		
		
		btnPlayNow = (Button) view.findViewById(R.id.btnPlayNow);
		btnMyBets = (Button) view.findViewById(R.id.btnMyBets);
		btnDrawHistory = (Button) view.findViewById(R.id.btnDrawHistory);
		btnUpdateAccount = (Button) view.findViewById(R.id.btnUpdateAccount);
		final Activity activity = this.getActivity();
		btnPlayNow.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection), getFragmentManager());
					return;
				}
				showErrorDialog("Test", "PlayNow", getFragmentManager());
			}
		});
		
		btnMyBets.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection), getFragmentManager());
					return;
				}

				showErrorDialog("Test", "MyBets", getFragmentManager());
			}
		});
		
		btnDrawHistory.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection), getFragmentManager());
					return;
				}
				
				showErrorDialog("Test", "DrawHistory", getFragmentManager());
			}
		});
		
		btnUpdateAccount.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!checkConnectivity(activity)){
					showErrorDialog(getString(R.string.networkError), getString(R.string.noInternetConnection), getFragmentManager());
					return;
				}
				
//				Intent updateAccount = new Intent("com.aalexandrakis.kimobile.UpdateAccount");
//				startActivity(updateAccount);
			

			}
		});
		
		return view;
	}

	
}
