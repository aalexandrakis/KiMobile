package com.aalexandrakis.kimobile.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Draw implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String drawDateTime;

	int drawNumber1;

	int drawNumber2;

	int drawNumber3;

	int drawNumber4;

	int drawNumber5;

	int drawNumber6;

	int drawNumber7;

	int drawNumber8;

	int drawNumber9;

	int drawNumber10;

	int drawNumber11;

	int drawNumber12;

	int drawNumber13;

	int drawNumber14;

	int drawNumber15;

	int drawNumber16;

	int drawNumber17;

	int drawNumber18;

	int drawNumber19;

	int drawNumber20;

	public Draw() {
		super();
	}

	public Draw(String drawDateTime, int drawNumber1, int drawNumber2,
			int drawNumber3, int drawNumber4, int drawNumber5, int drawNumber6,
			int drawNumber7, int drawNumber8, int drawNumber9,
			int drawNumber10, int drawNumber11, int drawNumber12,
			int drawNumber13, int drawNumber14, int drawNumber15,
			int drawNumber16, int drawNumber17, int drawNumber18,
			int drawNumber19, int drawNumber20) {
		super();
		this.drawDateTime = drawDateTime;
		this.drawNumber1 = drawNumber1;
		this.drawNumber2 = drawNumber2;
		this.drawNumber3 = drawNumber3;
		this.drawNumber4 = drawNumber4;
		this.drawNumber5 = drawNumber5;
		this.drawNumber6 = drawNumber6;
		this.drawNumber7 = drawNumber7;
		this.drawNumber8 = drawNumber8;
		this.drawNumber9 = drawNumber9;
		this.drawNumber10 = drawNumber10;
		this.drawNumber11 = drawNumber11;
		this.drawNumber12 = drawNumber12;
		this.drawNumber13 = drawNumber13;
		this.drawNumber14 = drawNumber14;
		this.drawNumber15 = drawNumber15;
		this.drawNumber16 = drawNumber16;
		this.drawNumber17 = drawNumber17;
		this.drawNumber18 = drawNumber18;
		this.drawNumber19 = drawNumber19;
		this.drawNumber20 = drawNumber20;
	}

	public String getDrawDateTime() {
		return drawDateTime;
	}

	public void setDrawDateTime(String drawDateTime) {
		this.drawDateTime = drawDateTime;
	}

	public int getDrawNumber1() {
		return drawNumber1;
	}

	public void setDrawNumber1(int drawNumber1) {
		this.drawNumber1 = drawNumber1;
	}

	public int getDrawNumber2() {
		return drawNumber2;
	}

	public void setDrawNumber2(int drawNumber2) {
		this.drawNumber2 = drawNumber2;
	}

	public int getDrawNumber3() {
		return drawNumber3;
	}

	public void setDrawNumber3(int drawNumber3) {
		this.drawNumber3 = drawNumber3;
	}

	public int getDrawNumber4() {
		return drawNumber4;
	}

	public void setDrawNumber4(int drawNumber4) {
		this.drawNumber4 = drawNumber4;
	}

	public int getDrawNumber5() {
		return drawNumber5;
	}

	public void setDrawNumber5(int drawNumber5) {
		this.drawNumber5 = drawNumber5;
	}

	public int getDrawNumber6() {
		return drawNumber6;
	}

	public void setDrawNumber6(int drawNumber6) {
		this.drawNumber6 = drawNumber6;
	}

	public int getDrawNumber7() {
		return drawNumber7;
	}

	public void setDrawNumber7(int drawNumber7) {
		this.drawNumber7 = drawNumber7;
	}

	public int getDrawNumber8() {
		return drawNumber8;
	}

	public void setDrawNumber8(int drawNumber8) {
		this.drawNumber8 = drawNumber8;
	}

	public int getDrawNumber9() {
		return drawNumber9;
	}

	public void setDrawNumber9(int drawNumber9) {
		this.drawNumber9 = drawNumber9;
	}

	public int getDrawNumber10() {
		return drawNumber10;
	}

	public void setDrawNumber10(int drawNumber10) {
		this.drawNumber10 = drawNumber10;
	}

	public int getDrawNumber11() {
		return drawNumber11;
	}

	public void setDrawNumber11(int drawNumber11) {
		this.drawNumber11 = drawNumber11;
	}

	public int getDrawNumber12() {
		return drawNumber12;
	}

	public void setDrawNumber12(int drawNumber12) {
		this.drawNumber12 = drawNumber12;
	}

	public int getDrawNumber13() {
		return drawNumber13;
	}

	public void setDrawNumber13(int drawNumber13) {
		this.drawNumber13 = drawNumber13;
	}

	public int getDrawNumber14() {
		return drawNumber14;
	}

	public void setDrawNumber14(int drawNumber14) {
		this.drawNumber14 = drawNumber14;
	}

	public int getDrawNumber15() {
		return drawNumber15;
	}

	public void setDrawNumber15(int drawNumber15) {
		this.drawNumber15 = drawNumber15;
	}

	public int getDrawNumber16() {
		return drawNumber16;
	}

	public void setDrawNumber16(int drawNumber16) {
		this.drawNumber16 = drawNumber16;
	}

	public int getDrawNumber17() {
		return drawNumber17;
	}

	public void setDrawNumber17(int drawNumber17) {
		this.drawNumber17 = drawNumber17;
	}

	public int getDrawNumber18() {
		return drawNumber18;
	}

	public void setDrawNumber18(int drawNumber18) {
		this.drawNumber18 = drawNumber18;
	}

	public int getDrawNumber19() {
		return drawNumber19;
	}

	public void setDrawNumber19(int drawNumber19) {
		this.drawNumber19 = drawNumber19;
	}

	public int getDrawNumber20() {
		return drawNumber20;
	}

	public void setDrawNumber20(int drawNumber20) {
		this.drawNumber20 = drawNumber20;
	}

	public List<Integer> toList(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(this.drawNumber1);
		list.add(this.drawNumber2);
		list.add(this.drawNumber3);
		list.add(this.drawNumber4);
		list.add(this.drawNumber5);
		list.add(this.drawNumber6);
		list.add(this.drawNumber7);
		list.add(this.drawNumber8);
		list.add(this.drawNumber9);
		list.add(this.drawNumber10);
		list.add(this.drawNumber11);
		list.add(this.drawNumber12);
		list.add(this.drawNumber13);
		list.add(this.drawNumber14);
		list.add(this.drawNumber15);
		list.add(this.drawNumber16);
		list.add(this.drawNumber17);
		list.add(this.drawNumber18);
		list.add(this.drawNumber19);
		list.add(this.drawNumber20);
		return list;
	}
}
