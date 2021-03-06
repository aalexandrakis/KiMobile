package com.aalexandrakis.kimobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.aalexandrakis.kimobile.pojos.ActiveBets;

import java.util.List;

public class AdapterActiveBets extends ArrayAdapter<ActiveBets>{
	private final Context context;
	private final List<ActiveBets> values;
	
	public AdapterActiveBets(Context context, List<ActiveBets> values) {
		super(context,  R.layout.bet_active_item, values);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.values = values;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.bet_active_item, parent, false);
		TextView txtBetId = (TextView) rowView.findViewById(R.id.txtBetId);
		TextView txtBetDate = (TextView) rowView.findViewById(R.id.txtBetDate);
		NumberButton txtBetNumber1 = (NumberButton) rowView.findViewById(R.id.btnNumber1);
		NumberButton txtBetNumber2 = (NumberButton) rowView.findViewById(R.id.btnNumber2);
		NumberButton txtBetNumber3 = (NumberButton) rowView.findViewById(R.id.btnNumber3);
		NumberButton txtBetNumber4 = (NumberButton) rowView.findViewById(R.id.btnNumber4);
		NumberButton txtBetNumber5 = (NumberButton) rowView.findViewById(R.id.btnNumber5);
		NumberButton txtBetNumber6 = (NumberButton) rowView.findViewById(R.id.btnNumber6);
		NumberButton txtBetNumber7 = (NumberButton) rowView.findViewById(R.id.btnNumber7);
		NumberButton txtBetNumber8 = (NumberButton) rowView.findViewById(R.id.btnNumber8);
		NumberButton txtBetNumber9 = (NumberButton) rowView.findViewById(R.id.btnNumber9);
		NumberButton txtBetNumber10 = (NumberButton) rowView.findViewById(R.id.btnNumber10);
		NumberButton txtBetNumber11 = (NumberButton) rowView.findViewById(R.id.btnNumber11);
		NumberButton txtBetNumber12 = (NumberButton) rowView.findViewById(R.id.btnNumber12);
		TextView txtRemainingDraws = (TextView) rowView.findViewById(R.id.txtRemainingDraws);
 
		ActiveBets bet = values.get(position);
		txtBetId.setText(bet.getBetId().toString().trim());
		txtBetDate.setText(bet.getBetDateTime().replaceFirst("([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}).*", "$1"));
		txtBetNumber1.setNumber(bet.getBetNumber1());
		txtBetNumber2.setNumber(bet.getBetNumber2());
		txtBetNumber3.setNumber(bet.getBetNumber3());
		txtBetNumber4.setNumber(bet.getBetNumber4());
		txtBetNumber5.setNumber(bet.getBetNumber5());
		txtBetNumber6.setNumber(bet.getBetNumber6());
		txtBetNumber7.setNumber(bet.getBetNumber7());
		txtBetNumber8.setNumber(bet.getBetNumber8());
		txtBetNumber9.setNumber(bet.getBetNumber9());
		txtBetNumber10.setNumber(bet.getBetNumber10());
		txtBetNumber11.setNumber(bet.getBetNumber11());
		txtBetNumber12.setNumber(bet.getBetNumber12());
		
		txtRemainingDraws.setText(String.valueOf(bet.getRepeatedDraws() - bet.getDraws()));
		return rowView;
	}
}
