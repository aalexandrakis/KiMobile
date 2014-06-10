package com.aalexandrakis.kimobile;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aalexandrakis.kimobile.pojos.BetsArchive;

public class AdapterArchiveBets extends ArrayAdapter<BetsArchive>{
	private final Context context;
	private final List<BetsArchive> values;
	
	public AdapterArchiveBets(Context context, List<BetsArchive> values) {
		super(context,  R.layout.bet_archive_item, values);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.values = values;
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
    	
		BetsArchive bet = values.get(position);
		txtBetId.setText(bet.getBetId().toString().trim());
		txtBetDate.setText(bet.getBetDateTime().substring(0, 19));
		txtDrawDate.setText(bet.getDrawTimeStamp().substring(0, 19));
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
