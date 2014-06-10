package com.aalexandrakis.kimobile;

import java.util.List;

import com.aalexandrakis.kimobile.pojos.ActiveBets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterBets extends ArrayAdapter<ActiveBets>{
	private final Context context;
	private final List<ActiveBets> values;
	
	public AdapterBets(Context context, List<ActiveBets> values) {
		super(context,  R.layout.bet_list_item, values);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.bet_list_item, parent, false);
		TextView txtBetId = (TextView) rowView.findViewById(R.id.txtBetId);
		TextView txtBetDate = (TextView) rowView.findViewById(R.id.txtBetDate);
		TextView txtBetNumber1 = (TextView) rowView.findViewById(R.id.betNumber1);
		TextView txtBetNumber2 = (TextView) rowView.findViewById(R.id.betNumber2);
		TextView txtBetNumber3 = (TextView) rowView.findViewById(R.id.betNumber3);
		TextView txtBetNumber4 = (TextView) rowView.findViewById(R.id.betNumber4);
		TextView txtBetNumber5 = (TextView) rowView.findViewById(R.id.betNumber5);
		TextView txtBetNumber6 = (TextView) rowView.findViewById(R.id.betNumber6);
		TextView txtBetNumber7 = (TextView) rowView.findViewById(R.id.betNumber7);
		TextView txtBetNumber8 = (TextView) rowView.findViewById(R.id.betNumber8);
		TextView txtBetNumber9 = (TextView) rowView.findViewById(R.id.betNumber9);
		TextView txtBetNumber10 = (TextView) rowView.findViewById(R.id.betNumber10);
		TextView txtBetNumber11 = (TextView) rowView.findViewById(R.id.betNumber11);
		TextView txtBetNumber12 = (TextView) rowView.findViewById(R.id.betNumber12);
		TextView txtRemainingDraws = (TextView) rowView.findViewById(R.id.txtRemainingDraws);
 
		ActiveBets bet = values.get(position);
		txtBetId.setText(bet.getBetId().toString().trim());
		txtBetDate.setText(bet.getBetDateTime());
		txtBetNumber1.setText(bet.getBetNumber1().toString());
		txtBetNumber2.setText(bet.getBetNumber2().toString());
		txtBetNumber3.setText(bet.getBetNumber3().toString());
		txtBetNumber4.setText(bet.getBetNumber4().toString());
		txtBetNumber5.setText(bet.getBetNumber5().toString());
		txtBetNumber6.setText(bet.getBetNumber6().toString());
		txtBetNumber7.setText(bet.getBetNumber7().toString());
		txtBetNumber8.setText(bet.getBetNumber8().toString());
		txtBetNumber9.setText(bet.getBetNumber9().toString());
		txtBetNumber10.setText(bet.getBetNumber10().toString());
		txtBetNumber11.setText(bet.getBetNumber11().toString());
		txtBetNumber12.setText(bet.getBetNumber12().toString());
		txtRemainingDraws.setText(String.valueOf(bet.getRepeatedDraws() - bet.getDraws()));
		return rowView;
	}
}
