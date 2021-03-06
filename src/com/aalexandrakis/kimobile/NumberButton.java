package com.aalexandrakis.kimobile;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

public class NumberButton extends Button {
	boolean isSelected = false;
	boolean isInDraw = false;
	boolean isInBet = false;
	boolean isMatched = false;
	int number = 0;

	public NumberButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public NumberButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public NumberButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	
	public int getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
		if (number != null){
			this.setText(String.valueOf(number));
		} else {
			this.setText("0");
		}
	}

	public boolean isSelected() {
		return isSelected;
	}


	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
		 if (!isSelected){
		    	this.setTextColor(Color.YELLOW);
		    	this.setBackgroundColor(Color.BLACK);
		    } else {
		    	this.setTextColor(Color.WHITE);
		    	this.setBackgroundColor(Color.GREEN);
		    }
	}


	public boolean isInDraw() {
		return isInDraw;
	}


	public void setInDraw(boolean isInDraw) {
		this.isInDraw = isInDraw;
		 if (!isInDraw){
		    	this.setTextColor(Color.YELLOW);
		    	this.setBackgroundColor(Color.BLACK);
		    } else {
		    	this.setTextColor(Color.WHITE);
		    	this.setBackgroundColor(Color.BLUE);
		    }
	}


	public boolean isInBet() {
		return isInBet;
	}


	public void setInBet(boolean isInBet) {
		this.isInBet = isInBet;
	}


	public boolean isMatched() {
		return isMatched;
	}


	public void setMatched(boolean isMatched) {
		this.isMatched = isMatched;
	}

    public void reset(){
    	this.isSelected = false;
    	this.isInBet = false;
    	this.isInDraw = false;
    	this.isMatched = false;
    	this.setTextColor(Color.YELLOW);
    	this.setBackgroundColor(Color.BLACK);
    }
	
	
}
