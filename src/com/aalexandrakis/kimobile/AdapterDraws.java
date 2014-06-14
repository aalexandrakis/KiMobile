package com.aalexandrakis.kimobile;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aalexandrakis.kimobile.pojos.Draw;

public class AdapterDraws extends ArrayAdapter<Draw>{
	private final Context context;
	private final List<Draw> values;
	
	public AdapterDraws(Context context, List<Draw> values) {
		super(context,  R.layout.draw_item, values);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.values = values;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.draw_item, parent, false);
    	TextView txtDrawDate = (TextView) rowView.findViewById(R.id.txtDrawDate);
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
    	NumberButton number13 = (NumberButton) rowView.findViewById(R.id.btnNumber13);
    	NumberButton number14 = (NumberButton) rowView.findViewById(R.id.btnNumber14);
    	NumberButton number15 = (NumberButton) rowView.findViewById(R.id.btnNumber15);
    	NumberButton number16 = (NumberButton) rowView.findViewById(R.id.btnNumber16);
    	NumberButton number17 = (NumberButton) rowView.findViewById(R.id.btnNumber17);
    	NumberButton number18 = (NumberButton) rowView.findViewById(R.id.btnNumber18);
    	NumberButton number19 = (NumberButton) rowView.findViewById(R.id.btnNumber19);
    	NumberButton number20 = (NumberButton) rowView.findViewById(R.id.btnNumber20);

    	
		Draw draw = values.get(position);
		txtDrawDate.setText(draw.getDrawDateTime().replaceFirst("([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}).*", "$1"));
		number1.setNumber(draw.getDrawNumber1());
		number2.setNumber(draw.getDrawNumber2());
		number3.setNumber(draw.getDrawNumber3());
		number4.setNumber(draw.getDrawNumber4());
		number5.setNumber(draw.getDrawNumber5());
		number6.setNumber(draw.getDrawNumber6());
		number7.setNumber(draw.getDrawNumber7());
		number8.setNumber(draw.getDrawNumber8());
		number9.setNumber(draw.getDrawNumber9());
		number10.setNumber(draw.getDrawNumber10());
		number11.setNumber(draw.getDrawNumber11());
		number12.setNumber(draw.getDrawNumber12());
		number13.setNumber(draw.getDrawNumber13());
		number14.setNumber(draw.getDrawNumber14());
		number15.setNumber(draw.getDrawNumber15());
		number16.setNumber(draw.getDrawNumber16());
		number17.setNumber(draw.getDrawNumber17());
		number18.setNumber(draw.getDrawNumber18());
		number19.setNumber(draw.getDrawNumber19());
		number20.setNumber(draw.getDrawNumber20());

		return rowView;
	}
}
