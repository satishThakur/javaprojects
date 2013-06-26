package com.satish.currencyconverter;

import java.util.HashMap;

public class CurrencyRate {
	
	private static HashMap<String, Double> m_currencyValue = new HashMap<String, Double>();
	
	static {
		m_currencyValue.put("INR", 55.0);
		m_currencyValue.put("USD", 1.0);
	}
	
	public static Double getCurrencyRate(String currencyCode) {
		return m_currencyValue.get(currencyCode);
		
	}
	

}
