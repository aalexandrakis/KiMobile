package com.aalexandrakis.kimobile;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ActivityViewBets extends FragmentActivity {
	public ActivityViewBets() {
		super();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            FragmentViewBets viewBets = new FragmentViewBets();
            viewBets.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, viewBets).commit();
        }

	}

}


 

