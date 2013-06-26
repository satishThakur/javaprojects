package com.alcatel.snmp.mib.parser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsmiparser.parser.SmiDefaultParser;
import org.jsmiparser.phase.file.FileParserPhase;
import org.jsmiparser.smi.SmiMib;
import org.jsmiparser.smi.SmiOidValue;
import org.jsmiparser.smi.SmiVariable;

public class MibParser {
	
	public static SmiMib parseMib() throws IOException {
		SmiDefaultParser parser = new SmiDefaultParser();
		FileParserPhase fileParserPhase = parser.getFileParserPhase();	
		
		URL url = (new java.io.File("C:\\satish\\Java Projects\\snmp-sim\\rfc1212.smi")).toURI().toURL();
		URL url1 = (new java.io.File("C:\\satish\\Java Projects\\snmp-sim\\RFC1155-SMI.mib")).toURI().toURL();
		URL url2 = (new java.io.File("C:\\satish\\Java Projects\\snmp-sim\\rfc1215.smi")).toURI().toURL();
		URL url3 = (new java.io.File("C:\\satish\\Java Projects\\snmp-sim\\TOTAL_MIB.txt")).toURI().toURL();		
		
		List<URL> urls = new ArrayList<URL>();
		urls.add(url);
		urls.add(url1);
		urls.add(url2);
		urls.add(url3);
		fileParserPhase.setInputUrls(urls);
		SmiMib mib = parser.parse();
		return mib;
	}
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		SmiDefaultParser parser = new SmiDefaultParser();
		FileParserPhase fileParserPhase = parser.getFileParserPhase();	
		
		URL url = (new java.io.File("C:\\satish\\Java Projects\\snmp-sim\\rfc1212.smi")).toURI().toURL();
		URL url1 = (new java.io.File("C:\\satish\\Java Projects\\snmp-sim\\RFC1155-SMI.mib")).toURI().toURL();
		URL url2 = (new java.io.File("C:\\satish\\Java Projects\\snmp-sim\\rfc1215.smi")).toURI().toURL();
		URL url3 = (new java.io.File("C:\\satish\\Java Projects\\snmp-sim\\TOTAL_MIB.txt")).toURI().toURL();		
		
		List<URL> urls = new ArrayList<URL>();
		urls.add(url);
		urls.add(url1);
		urls.add(url2);
		urls.add(url3);
		fileParserPhase.setInputUrls(urls);
		SmiMib mib = parser.parse();
		System.out.println(mib.getOidValues().find("sysDescr").getOidStr());
		
		
		SmiVariable variable = mib.getVariables().find("ifIndex");
		System.out.println(variable.getOidStr());
		System.out.println(variable.getType());
		System.out.println(variable.getPrimitiveType());
		
		
		/*SmiOidValue variable1 = mib.getOidValues().find("enterprises");
		System.out.println(variable1);
		System.out.println(variable1.getOidStr());*/
		//System.out.println(variable1.getPrimitiveType());
		
		System.out.println("Done parsing MIB!!!");
		
		
		
	}

}
