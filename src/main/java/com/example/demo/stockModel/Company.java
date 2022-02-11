package com.example.demo.stockModel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Company {
	
	@Id
	private String companyCode;
	private String companyName;
	private String companyCeo;
	private int turnOver;
	private String companyWebsite;
	private String stockExchange;
	private double stockPrice;
	
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Company(String companyCode, String companyName, String companyCeo, int turnOver, String companyWebsite,
			String stockExchange) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCeo = companyCeo;
		this.turnOver = turnOver;
		this.companyWebsite = companyWebsite;
		this.stockExchange = stockExchange;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCeo() {
		return companyCeo;
	}
	public void setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
	}
	public int getTurnOver() {
		return turnOver;
	}
	public void setTurnOver(int turnOver) {
		this.turnOver = turnOver;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	public double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}

}
