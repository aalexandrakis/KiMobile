package com.aalexandrakis.kimobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class CommonActivity extends Activity {

	
	void showErrorDialog(String title, String message){
		new AlertDialog.Builder(this)
		.setTitle(title)
		.setMessage(message)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		})
		.show();
	}
}
