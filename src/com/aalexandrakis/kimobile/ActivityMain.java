package com.aalexandrakis.kimobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import static com.aalexandrakis.kimobile.Constants.*;

public class ActivityMain extends FragmentActivity {
	SharedPreferences sharedPreferences;
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
		setContentView(R.layout.activity_main);
		
		FragmentManager fragmentManager = this.getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		FragmentUpdateAccount fragment = new FragmentUpdateAccount();
		fragmentTransaction.add(R.id.fragment_container, fragment);
		fragmentTransaction.commit();
    }
    

	
}
