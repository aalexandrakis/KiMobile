package com.aalexandrakis.kimobile;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ActivityViewDraws extends FragmentActivity {
	public ActivityViewDraws() {
		super();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            FragmentViewDraws viewDraws = new FragmentViewDraws();
            viewDraws.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, viewDraws).commit();
        }

	}

}


 

