package com.aalexandrakis.kimobile;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends CommonActivity {

	EditText txtUserName;
	EditText txtUserPassword;
	Button btnConnect;
	Button btnRegister;
	Button btnForgotPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		txtUserName = (EditText) findViewById(R.id.txtUserName);
		txtUserName.requestFocus();
		txtUserPassword = (EditText) findViewById(R.id.txtUserPassword);
		btnConnect = (Button) findViewById(R.id.btnConnect);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnForgotPassword = (Button) findViewById(R.id.btnForgotPassword);
		
		btnConnect.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (txtUserName.length() == 0){
					showErrorDialog(getString(R.string.credentialsError), getString(R.string.userNameMissing));
					return;
				}
				if (txtUserPassword.length() == 0){
					showErrorDialog(getString(R.string.credentialsError), getString(R.string.userPasswordMissing));
					return;
				}
			}
		});
	}
}
