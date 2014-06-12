package com.aalexandrakis.kimobile;

import static com.aalexandrakis.kimobile.CommonMethods.getDraw;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aalexandrakis.kimobile.pojos.BetsArchive;
import com.aalexandrakis.kimobile.pojos.Draw;

public class AdapterArchiveBets extends ArrayAdapter<BetsArchive>{
	private final Context context;
	private final List<BetsArchive> values;
	private final List<Draw> draws;
	
	public AdapterArchiveBets(Context context, List<BetsArchive> values, List<Draw> draws) {
		super(context,  R.layout.bet_archive_item, values);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.values = values;
		this.draws = draws;
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
		Draw curDraw = getDraw(draws, bet.getDrawTimeStamp());
		txtBetId.setText(bet.getBetId().toString().trim());
		txtBetDate.setText(bet.getBetDateTime().substring(0, 19));
		txtDrawDate.setText(bet.getDrawTimeStamp().substring(0, 19));
		
		number1.setNumber(bet.getBetNumber1());
		number1.setInDraw(curDraw.toList().contains(bet.getBetNumber1()));
		
		number2.setNumber(bet.getBetNumber2());
		number2.setInDraw(curDraw.toList().contains(bet.getBetNumber2()));

		number3.setNumber(bet.getBetNumber3());
		number3.setInDraw(curDraw.toList().contains(bet.getBetNumber3()));

		number4.setNumber(bet.getBetNumber4());
		number4.setInDraw(curDraw.toList().contains(bet.getBetNumber4()));

		number5.setNumber(bet.getBetNumber5());
		number5.setInDraw(curDraw.toList().contains(bet.getBetNumber5()));

		number6.setNumber(bet.getBetNumber6());
		number6.setInDraw(curDraw.toList().contains(bet.getBetNumber6()));

		number7.setNumber(bet.getBetNumber7());
		number7.setInDraw(curDraw.toList().contains(bet.getBetNumber7()));

		number8.setNumber(bet.getBetNumber9());
		number8.setInDraw(curDraw.toList().contains(bet.getBetNumber8()));

		number9.setNumber(bet.getBetNumber9());
		number9.setInDraw(curDraw.toList().contains(bet.getBetNumber9()));

		number10.setNumber(bet.getBetNumber10());
		number10.setInDraw(curDraw.toList().contains(bet.getBetNumber10()));

		number11.setNumber(bet.getBetNumber11());
		number11.setInDraw(curDraw.toList().contains(bet.getBetNumber11()));

		number12.setNumber(bet.getBetNumber12());
		number12.setInDraw(curDraw.toList().contains(bet.getBetNumber12()));

		String earnings = "0";
		if (bet.getReturnRate() != null){
			earnings = String.valueOf(bet.getBetCoins() * bet.getReturnRate());
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
