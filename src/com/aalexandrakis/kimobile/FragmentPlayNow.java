package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.numberClicked;
import static com.aalexandrakis.kimobile.CommonMethods.showErrorDialog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;


public class FragmentPlayNow extends Fragment {
	NumberButton numberButton1;
	NumberButton numberButton2;
	NumberButton numberButton3;
	NumberButton numberButton4;
	NumberButton numberButton5;
	NumberButton numberButton6;
	NumberButton numberButton7;
	NumberButton numberButton8;
	NumberButton numberButton9;
	NumberButton numberButton10;
	
	NumberButton numberButton11;
	NumberButton numberButton12;
	NumberButton numberButton13;
	NumberButton numberButton14;
	NumberButton numberButton15;
	NumberButton numberButton16;
	NumberButton numberButton17;
	NumberButton numberButton18;
	NumberButton numberButton19;
	NumberButton numberButton20;
	
	NumberButton numberButton21;
	NumberButton numberButton22;
	NumberButton numberButton23;
	NumberButton numberButton24;
	NumberButton numberButton25;
	NumberButton numberButton26;
	NumberButton numberButton27;
	NumberButton numberButton28;
	NumberButton numberButton29;
	NumberButton numberButton30;
	
	NumberButton numberButton31;
	NumberButton numberButton32;
	NumberButton numberButton33;
	NumberButton numberButton34;
	NumberButton numberButton35;
	NumberButton numberButton36;
	NumberButton numberButton37;
	NumberButton numberButton38;
	NumberButton numberButton39;
	NumberButton numberButton40;
	
	NumberButton numberButton41;
	NumberButton numberButton42;
	NumberButton numberButton43;
	NumberButton numberButton44;
	NumberButton numberButton45;
	NumberButton numberButton46;
	NumberButton numberButton47;
	NumberButton numberButton48;
	NumberButton numberButton49;
	NumberButton numberButton50;
	
	NumberButton numberButton51;
	NumberButton numberButton52;
	NumberButton numberButton53;
	NumberButton numberButton54;
	NumberButton numberButton55;
	NumberButton numberButton56;
	NumberButton numberButton57;
	NumberButton numberButton58;
	NumberButton numberButton59;
	NumberButton numberButton60;
	
	NumberButton numberButton61;
	NumberButton numberButton62;
	NumberButton numberButton63;
	NumberButton numberButton64;
	NumberButton numberButton65;
	NumberButton numberButton66;
	NumberButton numberButton67;
	NumberButton numberButton68;
	NumberButton numberButton69;
	NumberButton numberButton70;
	
	NumberButton numberButton71;
	NumberButton numberButton72;
	NumberButton numberButton73;
	NumberButton numberButton74;
	NumberButton numberButton75;
	NumberButton numberButton76;
	NumberButton numberButton77;
	NumberButton numberButton78;
	NumberButton numberButton79;
	NumberButton numberButton80;
	
	CheckBox chkRandomChoice;
	EditText txtMultiplier;
	EditText txtRepeatedDraws;
	EditText txtGameType;
	Button btnBetNow;
	SharedPreferences sharedPreferences;
	FrameLayout secondFragment;
	FragmentPlayNow playNow = this;
	List<Integer> numberList = new ArrayList<Integer>();
	public FragmentPlayNow() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_play_now, container, false);
		
		sharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREFERENCES, Activity.MODE_PRIVATE);
		secondFragment = (FrameLayout) getActivity().findViewById(R.id.secondFragment);
		chkRandomChoice = (CheckBox) view.findViewById(R.id.chkRandomChoice);
		txtMultiplier = (EditText) view.findViewById(R.id.txtMultiplier);
		txtRepeatedDraws = (EditText) view.findViewById(R.id.txtRepeatedDraws);
		txtGameType = (EditText) view.findViewById(R.id.txtGameType);
		
        numberButton1 = (NumberButton) view.findViewById(R.id.btnNumber1);
        numberButton1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton1, getActivity());
			}
        });
        numberButton2 = (NumberButton) view.findViewById(R.id.btnNumber2);
        numberButton2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton2, getActivity());
			}
        });
        numberButton3 = (NumberButton) view.findViewById(R.id.btnNumber3);
        numberButton3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton3, getActivity());
			}
        });
        numberButton4 = (NumberButton) view.findViewById(R.id.btnNumber4);
        numberButton4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton4, getActivity());
			}
        });
        numberButton5 = (NumberButton) view.findViewById(R.id.btnNumber5);
        numberButton5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton5, getActivity());
			}
        });
        numberButton6 = (NumberButton) view.findViewById(R.id.btnNumber6);
        numberButton6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton6, getActivity());
			}
        });
        numberButton7 = (NumberButton) view.findViewById(R.id.btnNumber7);
        numberButton7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton7, getActivity());
			}
        });
        numberButton8 = (NumberButton) view.findViewById(R.id.btnNumber8);
        numberButton8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton8, getActivity());
			}
        });
        numberButton9 = (NumberButton) view.findViewById(R.id.btnNumber9);
        numberButton9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton9, getActivity());
			}
        });
        numberButton10 = (NumberButton) view.findViewById(R.id.btnNumber10);
        numberButton10.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton10, getActivity());
			}
        });
        numberButton11 = (NumberButton) view.findViewById(R.id.btnNumber11);
        numberButton11.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton11, getActivity());
			}
        });
        numberButton12 = (NumberButton) view.findViewById(R.id.btnNumber12);
        numberButton12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton12, getActivity());
			}
        });
        numberButton13 = (NumberButton) view.findViewById(R.id.btnNumber13);
        numberButton13.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton13, getActivity());
			}
        });
        numberButton14 = (NumberButton) view.findViewById(R.id.btnNumber14);
        numberButton14.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton14, getActivity());
			}
        });
        numberButton15 = (NumberButton) view.findViewById(R.id.btnNumber15);
        numberButton15.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton15, getActivity());
			}
        });
        numberButton16 = (NumberButton) view.findViewById(R.id.btnNumber16);
        numberButton16.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton16, getActivity());
			}
        });
        numberButton17 = (NumberButton) view.findViewById(R.id.btnNumber17);
        numberButton17.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton17, getActivity());
			}
        });
        numberButton18 = (NumberButton) view.findViewById(R.id.btnNumber18);
        numberButton18.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton18, getActivity());
			}
        });
        numberButton19 = (NumberButton) view.findViewById(R.id.btnNumber19);
        numberButton19.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton19, getActivity());
			}
        });
        numberButton20 = (NumberButton) view.findViewById(R.id.btnNumber20);
        numberButton20.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton20, getActivity());
			}
        });
        numberButton21 = (NumberButton) view.findViewById(R.id.btnNumber21);
        numberButton21.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton21, getActivity());
			}
        });
        numberButton22 = (NumberButton) view.findViewById(R.id.btnNumber22);
        numberButton22.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton22, getActivity());
			}
        });
        numberButton23 = (NumberButton) view.findViewById(R.id.btnNumber23);
        numberButton23.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton23, getActivity());
			}
        });
        numberButton24 = (NumberButton) view.findViewById(R.id.btnNumber24);
        numberButton24.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton24, getActivity());
			}
        });
        numberButton25 = (NumberButton) view.findViewById(R.id.btnNumber25);
        numberButton25.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton25, getActivity());
			}
        });
        numberButton26 = (NumberButton) view.findViewById(R.id.btnNumber26);
        numberButton26.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton26, getActivity());
			}
        });
        numberButton27 = (NumberButton) view.findViewById(R.id.btnNumber27);
        numberButton27.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton27, getActivity());
			}
        });
        numberButton28 = (NumberButton) view.findViewById(R.id.btnNumber28);
        numberButton28.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton28, getActivity());
			}
        });
        numberButton29 = (NumberButton) view.findViewById(R.id.btnNumber29);
        numberButton29.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton29, getActivity());
			}
        });
        numberButton30 = (NumberButton) view.findViewById(R.id.btnNumber30);
        numberButton30.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton30, getActivity());
			}
        });
        numberButton31 = (NumberButton) view.findViewById(R.id.btnNumber31);
        numberButton31.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton31, getActivity());
			}
        });
        numberButton32 = (NumberButton) view.findViewById(R.id.btnNumber32);
        numberButton32.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton32, getActivity());
			}
        });
        numberButton33 = (NumberButton) view.findViewById(R.id.btnNumber33);
        numberButton33.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton33, getActivity());
			}
        });
        numberButton34 = (NumberButton) view.findViewById(R.id.btnNumber34);
        numberButton34.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton34, getActivity());
			}
        });
        numberButton35 = (NumberButton) view.findViewById(R.id.btnNumber35);
        numberButton35.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton35, getActivity());
			}
        });
        numberButton36 = (NumberButton) view.findViewById(R.id.btnNumber36);
        numberButton36.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton36, getActivity());
			}
        });
        numberButton37 = (NumberButton) view.findViewById(R.id.btnNumber37);
        numberButton37.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton37, getActivity());
			}
        });
        numberButton38 = (NumberButton) view.findViewById(R.id.btnNumber38);
        numberButton38.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton38, getActivity());
			}
        });
        numberButton39 = (NumberButton) view.findViewById(R.id.btnNumber39);
        numberButton39.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton39, getActivity());
			}
        });
        numberButton40 = (NumberButton) view.findViewById(R.id.btnNumber40);
        numberButton40.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton40, getActivity());
			}
        });
        numberButton41 = (NumberButton) view.findViewById(R.id.btnNumber41);
        numberButton41.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton41, getActivity());
			}
        });
        numberButton42 = (NumberButton) view.findViewById(R.id.btnNumber42);
        numberButton42.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton42, getActivity());
			}
        });
        numberButton43 = (NumberButton) view.findViewById(R.id.btnNumber43);
        numberButton43.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton43, getActivity());
			}
        });
        numberButton44 = (NumberButton) view.findViewById(R.id.btnNumber44);
        numberButton44.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton44, getActivity());
			}
        });
        numberButton45 = (NumberButton) view.findViewById(R.id.btnNumber45);
        numberButton45.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton45, getActivity());
			}
        });
        numberButton46 = (NumberButton) view.findViewById(R.id.btnNumber46);
        numberButton46.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton46, getActivity());
			}
        });
        numberButton47 = (NumberButton) view.findViewById(R.id.btnNumber47);
        numberButton47.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton47, getActivity());
			}
        });
        numberButton48 = (NumberButton) view.findViewById(R.id.btnNumber48);
        numberButton48.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton48, getActivity());
			}
        });
        numberButton49 = (NumberButton) view.findViewById(R.id.btnNumber49);
        numberButton49.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton49, getActivity());
			}
        });
        numberButton50 = (NumberButton) view.findViewById(R.id.btnNumber50);
        numberButton50.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton50, getActivity());
			}
        });
        numberButton51 = (NumberButton) view.findViewById(R.id.btnNumber51);
        numberButton51.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton51, getActivity());
			}
        });
        numberButton52 = (NumberButton) view.findViewById(R.id.btnNumber52);
        numberButton52.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton52, getActivity());
			}
        });
        numberButton53 = (NumberButton) view.findViewById(R.id.btnNumber53);
        numberButton53.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton53, getActivity());
			}
        });
        numberButton54 = (NumberButton) view.findViewById(R.id.btnNumber54);
        numberButton54.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton54, getActivity());
			}
        });
        numberButton55 = (NumberButton) view.findViewById(R.id.btnNumber55);
        numberButton55.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton55, getActivity());
			}
        });
        numberButton56 = (NumberButton) view.findViewById(R.id.btnNumber56);
        numberButton56.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton56, getActivity());
			}
        });
        numberButton57 = (NumberButton) view.findViewById(R.id.btnNumber57);
        numberButton57.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton57, getActivity());
			}
        });
        numberButton58 = (NumberButton) view.findViewById(R.id.btnNumber58);
        numberButton58.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton58, getActivity());
			}
        });
        numberButton59 = (NumberButton) view.findViewById(R.id.btnNumber59);
        numberButton59.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton59, getActivity());
			}
        });
        numberButton60 = (NumberButton) view.findViewById(R.id.btnNumber60);
        numberButton60.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton60, getActivity());
			}
        });
        numberButton61 = (NumberButton) view.findViewById(R.id.btnNumber61);
        numberButton61.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton61, getActivity());
			}
        });
        numberButton62 = (NumberButton) view.findViewById(R.id.btnNumber62);
        numberButton62.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton62, getActivity());
			}
        });
        numberButton63 = (NumberButton) view.findViewById(R.id.btnNumber63);
        numberButton63.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton63, getActivity());
			}
        });
        numberButton64 = (NumberButton) view.findViewById(R.id.btnNumber64);
        numberButton64.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton64, getActivity());
			}
        });
        numberButton65 = (NumberButton) view.findViewById(R.id.btnNumber65);
        numberButton65.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton65, getActivity());
			}
        });
        numberButton66 = (NumberButton) view.findViewById(R.id.btnNumber66);
        numberButton66.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton66, getActivity());
			}
        });
        numberButton67 = (NumberButton) view.findViewById(R.id.btnNumber67);
        numberButton67.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton67, getActivity());
			}
        });
        numberButton68 = (NumberButton) view.findViewById(R.id.btnNumber68);
        numberButton68.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton68, getActivity());
			}
        });
        numberButton69 = (NumberButton) view.findViewById(R.id.btnNumber69);
        numberButton69.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton69, getActivity());
			}
        });
        numberButton70 = (NumberButton) view.findViewById(R.id.btnNumber70);
        numberButton70.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton70, getActivity());
			}
        });
        numberButton71 = (NumberButton) view.findViewById(R.id.btnNumber71);
        numberButton71.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton71, getActivity());
			}
        });
        numberButton72 = (NumberButton) view.findViewById(R.id.btnNumber72);
        numberButton72.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton72, getActivity());
			}
        });
        numberButton73 = (NumberButton) view.findViewById(R.id.btnNumber73);
        numberButton73.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton73, getActivity());
			}
        });
        numberButton74 = (NumberButton) view.findViewById(R.id.btnNumber74);
        numberButton74.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton74, getActivity());
			}
        });
        numberButton75 = (NumberButton) view.findViewById(R.id.btnNumber75);
        numberButton75.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton75, getActivity());
			}
        });
        numberButton76 = (NumberButton) view.findViewById(R.id.btnNumber76);
        numberButton76.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton76, getActivity());
			}
        });
        numberButton77 = (NumberButton) view.findViewById(R.id.btnNumber77);
        numberButton77.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton77, getActivity());
			}
        });
        numberButton78 = (NumberButton) view.findViewById(R.id.btnNumber78);
        numberButton78.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton78, getActivity());
			}
        });
        numberButton79 = (NumberButton) view.findViewById(R.id.btnNumber79);
        numberButton79.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton79, getActivity());
			}
        });
        numberButton80 = (NumberButton) view.findViewById(R.id.btnNumber80);
        numberButton80.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			      numberClicked(numberButton80, getActivity());
			      
			}
        });
        
        numberButton1.setNumber(1);
        numberButton2.setNumber(2);
        numberButton3.setNumber(3);
        numberButton4.setNumber(4);
        numberButton5.setNumber(5);
        numberButton6.setNumber(6);
        numberButton7.setNumber(7);
        numberButton8.setNumber(8);
        numberButton9.setNumber(9);
        numberButton10.setNumber(10);
        

        numberButton11.setNumber(11);
        numberButton12.setNumber(12);
        numberButton13.setNumber(13);
        numberButton14.setNumber(14);
        numberButton15.setNumber(15);
        numberButton16.setNumber(16);
        numberButton17.setNumber(17);
        numberButton18.setNumber(18);
        numberButton19.setNumber(19);
        numberButton20.setNumber(20);
        

        numberButton21.setNumber(21);
        numberButton22.setNumber(22);
        numberButton23.setNumber(23);
        numberButton24.setNumber(24);
        numberButton25.setNumber(25);
        numberButton26.setNumber(26);
        numberButton27.setNumber(27);
        numberButton28.setNumber(28);
        numberButton29.setNumber(29);
        numberButton30.setNumber(30);
        
        numberButton31.setNumber(31);
        numberButton32.setNumber(32);
        numberButton33.setNumber(33);
        numberButton34.setNumber(34);
        numberButton35.setNumber(35);
        numberButton36.setNumber(36);
        numberButton37.setNumber(37);
        numberButton38.setNumber(38);
        numberButton39.setNumber(39);
        numberButton40.setNumber(40);
       
        numberButton41.setNumber(41);
        numberButton42.setNumber(42);
        numberButton43.setNumber(43);
        numberButton44.setNumber(44);
        numberButton45.setNumber(45);
        numberButton46.setNumber(46);
        numberButton47.setNumber(47);
        numberButton48.setNumber(48);
        numberButton49.setNumber(49);
        numberButton50.setNumber(50);
        
        numberButton51.setNumber(51);
        numberButton52.setNumber(52);
        numberButton53.setNumber(53);
        numberButton54.setNumber(54);
        numberButton55.setNumber(55);
        numberButton56.setNumber(56);
        numberButton57.setNumber(57);
        numberButton58.setNumber(58);
        numberButton59.setNumber(59);
        numberButton60.setNumber(60);
        
        numberButton61.setNumber(61);
        numberButton62.setNumber(62);
        numberButton63.setNumber(63);
        numberButton64.setNumber(64);
        numberButton65.setNumber(65);
        numberButton66.setNumber(66);
        numberButton67.setNumber(67);
        numberButton68.setNumber(68);
        numberButton69.setNumber(69);
        numberButton70.setNumber(70);
        
        numberButton71.setNumber(71);
        numberButton72.setNumber(72);
        numberButton73.setNumber(73);
        numberButton74.setNumber(74);
        numberButton75.setNumber(75);
        numberButton76.setNumber(76);
        numberButton77.setNumber(77);
        numberButton78.setNumber(78);
        numberButton79.setNumber(79);
        numberButton80.setNumber(80);
        
        btnBetNow = (Button) view.findViewById(R.id.btnBetNow);
        btnBetNow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				NumberButton[] numberButtons = {numberButton1, numberButton2, numberButton3, numberButton4, numberButton5, numberButton6, numberButton8, numberButton9, numberButton10 
						  , numberButton11, numberButton12, numberButton13, numberButton14, numberButton15, numberButton16, numberButton17, numberButton18, numberButton19, numberButton20 
						  , numberButton21, numberButton22, numberButton23, numberButton24, numberButton25, numberButton26, numberButton27, numberButton28, numberButton29, numberButton30
						  , numberButton31, numberButton32, numberButton33, numberButton34, numberButton35, numberButton36, numberButton37, numberButton38, numberButton39, numberButton40
						  , numberButton41, numberButton42, numberButton43, numberButton44, numberButton45, numberButton46, numberButton47, numberButton48, numberButton49, numberButton50
						  , numberButton51, numberButton52, numberButton53, numberButton54, numberButton55, numberButton56, numberButton57, numberButton58, numberButton59, numberButton60
						  , numberButton61, numberButton62, numberButton63, numberButton64, numberButton65, numberButton66, numberButton67, numberButton68, numberButton69, numberButton70
						  , numberButton71, numberButton72, numberButton73, numberButton74, numberButton75, numberButton76, numberButton77, numberButton78, numberButton79, numberButton80};
				
				  int selected = 0;
				  numberList.clear();
			      for (NumberButton curButton : numberButtons){
			    	  if (curButton.isSelected){
			    		  ++selected;
			    		  numberList.add(Integer.valueOf(curButton.getNumber()));
			    	  }
			      }
			      
			      for (int i = numberList.size(); i < 12 ; ++i){
			    	  numberList.add(0);
			      }
			      
			      if (Integer.valueOf(txtMultiplier.getText().toString()) > 20 || Integer.valueOf(txtMultiplier.getText().toString()) < 1){
			    	  showErrorDialog(getString(R.string.betError), getString(R.string.multiplierError), getActivity());
			    	  txtMultiplier.requestFocus();
			    	  return;
			      }
			      
			      if (Integer.valueOf(txtRepeatedDraws.getText().toString()) > 20 || Integer.valueOf(txtRepeatedDraws.getText().toString()) < 1){
			    	  showErrorDialog(getString(R.string.betError), getString(R.string.repeatedDrawsError), getActivity());
			    	  txtRepeatedDraws.requestFocus();
			    	  return;
			      }
			      
			      if (Integer.valueOf(txtGameType.getText().toString()) > 12 || Integer.valueOf(txtGameType.getText().toString()) < 1){
			    	  showErrorDialog(getString(R.string.betError), getString(R.string.gameTypeRangeError), getActivity());
			    	  txtRepeatedDraws.requestFocus();
			    	  return;
			      }
			      
			      if (!Integer.valueOf(txtGameType.getText().toString()).equals(selected)){
			    	  showErrorDialog(getString(R.string.betError), getString(R.string.gameTypeError), getActivity());
			    	  txtRepeatedDraws.requestFocus();
			    	  return;
			      }
			      //TODO select random numbers 
			      
			      String userId = sharedPreferences.getString("userId", "0");
			      String repeatedDraws = txtRepeatedDraws.getText().toString();
			      String randomChoice = chkRandomChoice.isChecked() ? "1" : "0";
			      String gameType = txtGameType.getText().toString();
			      String multiplier = txtMultiplier.getText().toString();
			      String nbr1 = numberList.get(0).toString();
			      String nbr2 = numberList.get(1).toString();
			      String nbr3 = numberList.get(2).toString();
			      String nbr4 = numberList.get(3).toString();
			      String nbr5 = numberList.get(4).toString();
			      String nbr6 = numberList.get(5).toString();
			      String nbr7 = numberList.get(6).toString();
			      String nbr8 = numberList.get(7).toString();
			      String nbr9 = numberList.get(8).toString();
			      String nbr10 = numberList.get(9).toString();
			      String nbr11 = numberList.get(10).toString();
			      String nbr12 = numberList.get(11).toString();
			      
			      AsyncTaskSaveBet saveBet = new AsyncTaskSaveBet(playNow);
			      saveBet.execute(userId, repeatedDraws, randomChoice, gameType, multiplier, nbr1, nbr2, nbr3, nbr4, nbr5, nbr6, nbr7, nbr8, nbr9, nbr10, nbr11, nbr12);
			}
        });
		return view;
	}
	
	
	protected void reset(){
		numberButton1.reset();
		numberButton2.reset();
		numberButton3.reset();
		numberButton4.reset();
		numberButton5.reset();
		numberButton6.reset();
		numberButton7.reset();
		numberButton8.reset();
		numberButton9.reset();
		numberButton10.reset();
		
		numberButton11.reset();
		numberButton12.reset();
		numberButton13.reset();
		numberButton14.reset();
		numberButton15.reset();
		numberButton16.reset();
		numberButton17.reset();
		numberButton18.reset();
		numberButton19.reset();
		numberButton20.reset();

		numberButton21.reset();
		numberButton22.reset();
		numberButton23.reset();
		numberButton24.reset();
		numberButton25.reset();
		numberButton26.reset();
		numberButton27.reset();
		numberButton28.reset();
		numberButton29.reset();
		numberButton30.reset();

		numberButton31.reset();
		numberButton32.reset();
		numberButton33.reset();
		numberButton34.reset();
		numberButton35.reset();
		numberButton36.reset();
		numberButton37.reset();
		numberButton38.reset();
		numberButton39.reset();
		numberButton40.reset();

		numberButton41.reset();
		numberButton42.reset();
		numberButton43.reset();
		numberButton44.reset();
		numberButton45.reset();
		numberButton46.reset();
		numberButton47.reset();
		numberButton48.reset();
		numberButton49.reset();
		numberButton50.reset();

		numberButton51.reset();
		numberButton52.reset();
		numberButton53.reset();
		numberButton54.reset();
		numberButton55.reset();
		numberButton56.reset();
		numberButton57.reset();
		numberButton58.reset();
		numberButton59.reset();
		numberButton60.reset();

		numberButton61.reset();
		numberButton62.reset();
		numberButton63.reset();
		numberButton64.reset();
		numberButton65.reset();
		numberButton66.reset();
		numberButton67.reset();
		numberButton68.reset();
		numberButton69.reset();
		numberButton70.reset();

		numberButton71.reset();
		numberButton72.reset();
		numberButton73.reset();
		numberButton74.reset();
		numberButton75.reset();
		numberButton76.reset();
		numberButton77.reset();
		numberButton78.reset();
		numberButton79.reset();
		numberButton80.reset();

	}
}


class AsyncTaskSaveBet extends AsyncTask<String, String, String>  {
	 
	FragmentPlayNow playNow;
	public static final String METHOD = "saveBet";
	boolean error = false;
	ProgressDialog pg;
	AsyncTaskSaveBet(FragmentPlayNow playNow){
		this.playNow = playNow;
	}
	 @SuppressLint("ShowToast")
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pg.dismiss();
		if (error == true || result == null || result.equals("40")){
			showErrorDialog(playNow.getString(R.string.betError), playNow.getString(R.string.youCanntConnect), playNow.getFragmentManager());
		} else if (result.equals("10")){
			showErrorDialog(playNow.getString(R.string.betError), playNow.getString(R.string.gameTypeError), playNow.getFragmentManager());
			playNow.txtGameType.requestFocus();			
		} else if (result.equals("11")){
			showErrorDialog(playNow.getString(R.string.betError), playNow.getString(R.string.notEnoughCoins), playNow.getFragmentManager());
			playNow.txtGameType.requestFocus();			
		} else if (result.equals("00")){
			Toast.makeText(playNow.getActivity(), playNow.getString(R.string.toastBetAddedSuccessfully), Toast.LENGTH_LONG).show();
			playNow.reset();
		}
		
		
	}


	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pg = new ProgressDialog(playNow.getActivity());
		pg.setTitle(playNow.getString(R.string.betting));
		pg.setMessage(playNow.getString(R.string.waitToSaveYourBet));
		pg.show();
	}


	@Override
	 protected String doInBackground(String... params) {
		String userId = params[0];
		Integer repeatedDraws = Integer.valueOf(params[1]);
		Integer randomChoice = Integer.valueOf(params[2]);
		Integer gameType = Integer.valueOf(params[3]);
		Integer multiplier = Integer.valueOf(params[4]);
		Integer number1 = Integer.valueOf(params[5]);
		Integer number2 = Integer.valueOf(params[6]);
		Integer number3 = Integer.valueOf(params[7]);
		Integer number4 = Integer.valueOf(params[8]);
		Integer number5 = Integer.valueOf(params[9]);
		Integer number6 = Integer.valueOf(params[10]);
		Integer number7 = Integer.valueOf(params[11]);
		Integer number8 = Integer.valueOf(params[12]);
		Integer number9 = Integer.valueOf(params[13]);
		Integer number10 = Integer.valueOf(params[14]);
		Integer number11 = Integer.valueOf(params[15]);
		Integer number12 = Integer.valueOf(params[16]);
		// TODO implement random choice
         HttpClient httpclient = new DefaultHttpClient();
 		HttpResponse response;
 		HttpPost httpPost = new HttpPost(Constants.REST_URL + "saveBet");
 		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
 		parameters.add(new BasicNameValuePair("userIdString", userId));
 		parameters.add(new BasicNameValuePair("repeatedDraws", repeatedDraws.toString()));
 		parameters.add(new BasicNameValuePair("randomChoice", randomChoice.toString()));
 		parameters.add(new BasicNameValuePair("gameType", gameType.toString()));
 		parameters.add(new BasicNameValuePair("multiplier", multiplier.toString()));
 		parameters.add(new BasicNameValuePair("betNumber1", number1.toString()));
 		parameters.add(new BasicNameValuePair("betNumber2", number2.toString()));
 		parameters.add(new BasicNameValuePair("betNumber3", number3.toString()));
 		parameters.add(new BasicNameValuePair("betNumber4", number4.toString()));
 		parameters.add(new BasicNameValuePair("betNumber5", number5.toString()));
 		parameters.add(new BasicNameValuePair("betNumber6", number6.toString()));
 		parameters.add(new BasicNameValuePair("betNumber7", number7.toString()));
 		parameters.add(new BasicNameValuePair("betNumber8", number8.toString()));
 		parameters.add(new BasicNameValuePair("betNumber9", number9.toString()));
 		parameters.add(new BasicNameValuePair("betNumber10", number10.toString()));
 		parameters.add(new BasicNameValuePair("betNumber11", number11.toString()));
 		parameters.add(new BasicNameValuePair("betNumber12", number12.toString()));

 		try {
 			httpPost.setEntity(new UrlEncodedFormEntity(parameters));
 		} catch (UnsupportedEncodingException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
 		try {
 			response = httpclient.execute(httpPost);
 			
 			StatusLine statusLine = response.getStatusLine();
 			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
 				ByteArrayOutputStream out = new ByteArrayOutputStream();
 				response.getEntity().writeTo(out);
 				out.close();
 				String responseString = out.toString();
 				/****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                 JSONObject jsonResponse = new JSONObject(responseString);
                 return jsonResponse.optString("responseCode");
 			} else {
 				// Closes the connection.
 				response.getEntity().getContent().close();
 				throw new IOException(statusLine.getReasonPhrase());
 			}
 		} catch (ClientProtocolException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			error = false;
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			error = false;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			error = false;
 		}

	 	return null;
	 }	
}
 

