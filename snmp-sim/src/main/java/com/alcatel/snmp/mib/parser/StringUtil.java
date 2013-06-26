package com.alcatel.snmp.mib.parser;

public class StringUtil {
	
	public static String removeWhiteSpaces(String str) {
		return str.replaceAll("\\s", "");
	}
	//IpAddress: 127.0.0.1
	public static String extractIpPart(String str) {
		return str.split(":")[1].trim();
	}
	
	//Timeticks: (0) 0:00:00
	public static Long getTicks(String str) {
		return Long.parseLong(str.split("\\(")[1].split("\\)")[0].trim());
	}
	
	public static void main(String[] args) {
		System.out.println(removeWhiteSpaces(" 00 15 34 24 56 " +
				"\n" +
				" 99"));
		System.out.println(extractIpPart("IpAddress: 127.0.0.1"));
		System.out.println(getTicks("Timeticks: (5666) 0:00:00"));
		
		 String[] strs = "\"ASAM\" Hex: 41 53 41 4D".split("\"");
		 System.out.println(strs[1]);
	}
	
	

}
