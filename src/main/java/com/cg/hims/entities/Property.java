package com.cg.hims.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Property {

	@Id
	private int property_id;
	private int marketValue;
	private int yearBuilt;
	private int squareFootage;
	private double dwellingStyle;
	private String roofMaterial ;
	private String garageType;
	private int fullBathCount;
	private int halfBathCount;
	private boolean hasSwimmingPool;
	@OneToOne
	@JoinColumn(name="quote_id")
	private Quote quote;
	public Property()
	{}
	public Quote getQuote() {
		return quote;
	}
	public void setQuote(Quote quote) {
		this.quote = quote;
	}
	public int getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(int marketValue) {
		this.marketValue = marketValue;
	}
	public int getYearBuilt() {
		return yearBuilt;
	}
	public void setYearBuilt(int yearBuilt) {
		this.yearBuilt = yearBuilt;
	}
	public int getSquareFootage() {
		return squareFootage;
	}
	public void setSquareFootage(int squareFootage) {
		this.squareFootage = squareFootage;
	}
	public double getDwellingStyle() {
		return dwellingStyle;
	}
	public void setDwellingStyle(double dwellingStyle) {
		this.dwellingStyle = dwellingStyle;
	}
	public String getRoofMaterial() {
		return roofMaterial;
	}
	public void setRoofMaterial(String roofMaterial) {
		this.roofMaterial = roofMaterial;
	}
	public String getGarageType() {
		return garageType;
	}
	public void setGarageType(String garageType) {
		this.garageType = garageType;
	}
	public int getFullBathCount() {
		return fullBathCount;
	}
	public void setFullBathCount(int fullBathCount) {
		this.fullBathCount = fullBathCount;
	}
	public int getHalfBathCount() {
		return halfBathCount;
	}
	public void setHalfBathCount(int halfBathCount) {
		this.halfBathCount = halfBathCount;
	}
	public boolean isHasSwimmingPool() {
		return hasSwimmingPool;
	}
	public void setHasSwimmingPool(boolean hasSwimmingPool) {
		this.hasSwimmingPool = hasSwimmingPool;
	}
	

	
	
	
	
	
	
	
}
