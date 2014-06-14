package com.aalexandrakis.kimobile.pojos;

import java.io.Serializable;
import java.math.BigInteger;


public class ActiveBets implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BigInteger id;
	BigInteger betId;
	String betDateTime;
	BigInteger userId;
	Integer repeatedDraws;
	Integer randomChoice;
	Integer gameType;
	Float betCoins;
	Integer multiplier;
	Integer betNumber1;
	Integer betNumber2;
	Integer betNumber3;
	Integer betNumber4;
	Integer betNumber5;
	Integer betNumber6;
	Integer betNumber7;
	Integer betNumber8;
	Integer betNumber9;
	Integer betNumber10;
	Integer betNumber11;
	Integer betNumber12;
	String drawTimeStamp;
	Integer matches;
	Float returnRate;
	Integer draws;
	
	public ActiveBets() {
		super();
	}

	public ActiveBets(BigInteger userId, Integer repeatedDraws,
			Integer randomChoice, Integer gameType, 
			Integer multiplier, Integer betNumber1, Integer betNumber2,
			Integer betNumber3, Integer betNumber4, Integer betNumber5,
			Integer betNumber6, Integer betNumber7, Integer betNumber8,
			Integer betNumber9, Integer betNumber10, Integer betNumber11,
			Integer betNumber12) {
		super();
		this.userId = userId;
		this.repeatedDraws = repeatedDraws;
		this.randomChoice = randomChoice;
		this.gameType = gameType;
		this.multiplier = multiplier;
		this.betNumber1 = betNumber1;
		this.betNumber2 = betNumber2;
		this.betNumber3 = betNumber3;
		this.betNumber4 = betNumber4;
		this.betNumber5 = betNumber5;
		this.betNumber6 = betNumber6;
		this.betNumber7 = betNumber7;
		this.betNumber8 = betNumber8;
		this.betNumber9 = betNumber9;
		this.betNumber10 = betNumber10;
		this.betNumber11 = betNumber11;
		this.betNumber12 = betNumber12;
		this.draws = 0;
	}

	public BigInteger getBetId() {
		return betId;
	}

	
	public String getBetDateTime() {
		return betDateTime;
	}

	public void setBetDateTime(String betDateTime) {
		this.betDateTime = betDateTime;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public Integer getRepeatedDraws() {
		return repeatedDraws;
	}

	public void setRepeatedDraws(Integer repeatedDraws) {
		this.repeatedDraws = repeatedDraws;
	}

	public Integer getRandomChoice() {
		return randomChoice;
	}

	public void setRandomChoice(Integer randomChoice) {
		this.randomChoice = randomChoice;
	}

	public Integer getGameType() {
		return gameType;
	}

	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}

	public Float getBetCoins() {
		return betCoins;
	}

	public void setBetCoins(Float betCoins) {
		this.betCoins = betCoins;
	}

	public Integer getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(Integer multiplier) {
		this.multiplier = multiplier;
	}

	public Integer getBetNumber1() {
		return betNumber1;
	}

	public void setBetNumber1(Integer betNumber1) {
		this.betNumber1 = betNumber1;
	}

	public Integer getBetNumber2() {
		return betNumber2;
	}

	public void setBetNumber2(Integer betNumber2) {
		this.betNumber2 = betNumber2;
	}

	public Integer getBetNumber3() {
		return betNumber3;
	}

	public void setBetNumber3(Integer betNumber3) {
		this.betNumber3 = betNumber3;
	}

	public Integer getBetNumber4() {
		return betNumber4;
	}

	public void setBetNumber4(Integer betNumber4) {
		this.betNumber4 = betNumber4;
	}

	public Integer getBetNumber5() {
		return betNumber5;
	}

	public void setBetNumber5(Integer betNumber5) {
		this.betNumber5 = betNumber5;
	}

	public Integer getBetNumber6() {
		return betNumber6;
	}

	public void setBetNumber6(Integer betNumber6) {
		this.betNumber6 = betNumber6;
	}

	public Integer getBetNumber7() {
		return betNumber7;
	}

	public void setBetNumber7(Integer betNumber7) {
		this.betNumber7 = betNumber7;
	}

	public Integer getBetNumber8() {
		return betNumber8;
	}

	public void setBetNumber8(Integer betNumber8) {
		this.betNumber8 = betNumber8;
	}

	public Integer getBetNumber9() {
		return betNumber9;
	}

	public void setBetNumber9(Integer betNumber9) {
		this.betNumber9 = betNumber9;
	}

	public Integer getBetNumber10() {
		return betNumber10;
	}

	public void setBetNumber10(Integer betNumber10) {
		this.betNumber10 = betNumber10;
	}

	public Integer getBetNumber11() {
		return betNumber11;
	}

	public void setBetNumber11(Integer betNumber11) {
		this.betNumber11 = betNumber11;
	}

	public Integer getBetNumber12() {
		return betNumber12;
	}

	public void setBetNumber12(Integer betNumber12) {
		this.betNumber12 = betNumber12;
	}

	public String getDrawTimeStamp() {
		return drawTimeStamp;
	}

	public void setDrawTimeStamp(String drawTimeStamp) {
		this.drawTimeStamp = drawTimeStamp;
	}

	public Integer getMatches() {
		return matches;
	}

	public void setMatches(Integer matches) {
		this.matches = matches;
	}

	public Float getReturnRate() {
		return returnRate;
	}

	public void setReturnRate(Float returnRate) {
		this.returnRate = returnRate;
	}

	
	
	public Integer getDraws() {
		return draws;
	}

	public void setDraws(Integer draws) {
		this.draws = draws;
	}

	
	public void setBetId(BigInteger betId) {
		this.betId = betId;
	}

	@Override
	public String toString(){
		return betId.toString() + betDateTime.toString() + userId.toString();
	}
	
}
