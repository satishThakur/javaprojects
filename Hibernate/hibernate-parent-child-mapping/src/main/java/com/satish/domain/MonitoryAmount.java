package com.satish.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

import com.satish.currencyconverter.CurrencyRate;

public class MonitoryAmount implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal m_amount;
	
	private Currency m_currency;	
	
	public MonitoryAmount(BigDecimal bigDecimal, Currency currency) {
		setAmount(bigDecimal);
		setCurrency(currency);
	}
	
	public MonitoryAmount(BigDecimal bigDecimal, String currencyCode) {
		setAmount(bigDecimal);
		setCurrency(Currency.getInstance(currencyCode));
	}

	public void setAmount(BigDecimal amount) {
		m_amount = amount;
	}

	public BigDecimal getAmount() {
		return m_amount;
	}

	public void setCurrency(Currency currency) {
		m_currency = currency;
	}

	public Currency getCurrency() {
		return m_currency;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(! (obj instanceof MonitoryAmount) )
			return false;
		MonitoryAmount amount = (MonitoryAmount)obj;
		return this.m_amount.equals(amount.m_amount) && 
		this.m_currency.getCurrencyCode().equals(amount.m_currency.getCurrencyCode());
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result*31 + m_amount.hashCode();
		result = result*31 + m_currency.hashCode();
		return result;
	}
	@Override
	public String toString() {
		return "Amount: " + m_amount + " Currency: " + m_currency;
	}

	public MonitoryAmount convertTo(String currencyCode) {
		BigDecimal amountInUsd = m_amount.divide(new BigDecimal(CurrencyRate.getCurrencyRate(m_currency.getCurrencyCode())));
		BigDecimal resultAmount = amountInUsd.multiply(new BigDecimal(CurrencyRate.getCurrencyRate(currencyCode)));
		return new MonitoryAmount(resultAmount, currencyCode);
	}

}
