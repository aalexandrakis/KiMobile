package com.aalexandrakis.kimobile;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ActivityUpdateAccount extends FragmentActivity {
	public ActivityUpdateAccount() {
		super();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            FragmentUpdateAccount updateAccount = new FragmentUpdateAccount();
            updateAccount.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, updateAccount).commit();
        }

	}

}


 

