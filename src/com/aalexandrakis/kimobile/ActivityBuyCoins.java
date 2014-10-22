package com.aalexandrakis.kimobile;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ActivityBuyCoins extends FragmentActivity {
	public ActivityBuyCoins() {
		super();
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            FragmentBuyCoins buyCoins = new FragmentBuyCoins();
            buyCoins.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, buyCoins).commit();
            
		}
	}
	
	

}


 

