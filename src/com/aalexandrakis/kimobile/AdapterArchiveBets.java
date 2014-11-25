package com.aalexandrakis.kimobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.aalexandrakis.kimobile.pojos.BetsArchive;
import com.aalexandrakis.kimobile.pojos.Draw;

import java.util.Arrays;
import java.util.List;

import static com.aalexandrakis.kimobile.CommonMethods.convertSqlDateStringToEuroDate;
import static com.aalexandrakis.kimobile.CommonMethods.getDraw;

public class AdapterArchiveBets extends ArrayAdapter<BetsArchive>{
	private final Context context;
	private final List<BetsArchive> values;

	public AdapterArchiveBets(Context context, List<BetsArchive> values) {
		super(context,  R.layout.bet_archive_item, values);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.values = values;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.bet_archive_item, parent, false);
		TextView txtBetId = (TextView) rowView.findViewById(R.id.txtBetId);
		TextView txtBetDate = (TextView) rowView.findViewById(R.id.txtBetDate);
		TextView txtDrawDate = (TextView) rowView.findViewById(R.id.txtDrawDate);
		TextView txtEarnings = (TextView) rowView.findViewById(R.id.txtEarnings);
    	TextView txtMatches = (TextView) rowView.findViewById(R.id.txtMatches);
    	TextView txtDraw = (TextView) rowView.findViewById(R.id.txtDraw);
    	TextView txtRepeatedDraws = (TextView) rowView.findViewById(R.id.txtRepeatedDraws);
    	TextView txtMultiplier = (TextView) rowView.findViewById(R.id.txtMultiplier);
    	
    	NumberButton number1 = (NumberButton) rowView.findViewById(R.id.btnNumber1);
    	NumberButton number2 = (NumberButton) rowView.findViewById(R.id.btnNumber2);
    	NumberButton number3 = (NumberButton) rowView.findViewById(R.id.btnNumber3);
    	NumberButton number4 = (NumberButton) rowView.findViewById(R.id.btnNumber4);
    	NumberButton number5 = (NumberButton) rowView.findViewById(R.id.btnNumber5);
    	NumberButton number6 = (NumberButton) rowView.findViewById(R.id.btnNumber6);
    	NumberButton number7 = (NumberButton) rowView.findViewById(R.id.btnNumber7);
    	NumberButton number8 = (NumberButton) rowView.findViewById(R.id.btnNumber8);
    	NumberButton number9 = (NumberButton) rowView.findViewById(R.id.btnNumber9);
    	NumberButton number10 = (NumberButton) rowView.findViewById(R.id.btnNumber10);
    	NumberButton number11 = (NumberButton) rowView.findViewById(R.id.btnNumber11);
    	NumberButton number12 = (NumberButton) rowView.findViewById(R.id.btnNumber12);
    	
		BetsArchive bet = values.get(position);
		List<String> curDraw = Arrays.asList(bet.getDrawNumbers().split(","));
		txtBetId.setText(bet.getBetId().toString().trim());
		txtBetDate.setText(convertSqlDateStringToEuroDate(bet.getBetDateTime()));
		txtDrawDate.setText(convertSqlDateStringToEuroDate(bet.getDrawTimeStamp()));
		txtDraw.setText(bet.getDraws().toString());
		txtRepeatedDraws.setText(bet.getRepeatedDraws().toString());
		txtMultiplier.setText(bet.getMultiplier().toString());
		number1.setNumber(bet.getBetNumber1());
		number1.setInDraw(curDraw.contains(bet.getBetNumber1().toString()));
		
		number2.setNumber(bet.getBetNumber2());
		number2.setInDraw(curDraw.contains(bet.getBetNumber2().toString()));

		number3.setNumber(bet.getBetNumber3());
		number3.setInDraw(curDraw.contains(bet.getBetNumber3().toString()));

		number4.setNumber(bet.getBetNumber4());
		number4.setInDraw(curDraw.contains(bet.getBetNumber4().toString()));

		number5.setNumber(bet.getBetNumber5());
		number5.setInDraw(curDraw.contains(bet.getBetNumber5().toString()));

		number6.setNumber(bet.getBetNumber6());
		number6.setInDraw(curDraw.contains(bet.getBetNumber6().toString()));

		number7.setNumber(bet.getBetNumber7());
		number7.setInDraw(curDraw.contains(bet.getBetNumber7().toString()));

		number8.setNumber(bet.getBetNumber8());
		number8.setInDraw(curDraw.contains(bet.getBetNumber8().toString()));

		number9.setNumber(bet.getBetNumber9());
		number9.setInDraw(curDraw.contains(bet.getBetNumber9().toString()));

		number10.setNumber(bet.getBetNumber10());
		number10.setInDraw(curDraw.contains(bet.getBetNumber10().toString()));

		number11.setNumber(bet.getBetNumber11());
		number11.setInDraw(curDraw.contains(bet.getBetNumber11().toString()));

		number12.setNumber(bet.getBetNumber12());
		number12.setInDraw(curDraw.contains(bet.getBetNumber12().toString()));

		String earnings = "0";
		if (bet.getReturnRate() != null){
			earnings = String.valueOf(bet.getBetCoins() * (bet.getReturnRate() / bet.getRepeatedDraws()));
		}
    	txtEarnings.setText(earnings);
    	String matches = "0";
    	if (bet.getMatches() != null){
    		matches = bet.getMatches().toString();
    	}
    	txtMatches.setText(matches);
    	
		return rowView;
	}
}
