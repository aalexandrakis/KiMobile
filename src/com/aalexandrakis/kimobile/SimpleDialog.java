package com.aalexandrakis.kimobile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

@SuppressLint("ValidFragment")
public class SimpleDialog extends DialogFragment{
		String title = "";
		String message = "";
		
		public SimpleDialog(){
		}
		
		SimpleDialog(String title, String message){
			this.title = title;
			this.message = message;
		}
		
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        return new AlertDialog.Builder(getActivity()).setTitle(title)
	                .setMessage(message).setPositiveButton("Ok", null)
	                .create();
	    }

	
}
