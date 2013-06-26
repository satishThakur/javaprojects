package com.alcatel.snmp.mib.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsmiparser.smi.SmiMib;
import org.jsmiparser.smi.SmiPrimitiveType;
import org.jsmiparser.smi.SmiVariable;

public class ValidateWalkFile {
	
	private static Pattern hexLine = Pattern.compile("[A-F|0-9]{2}.*");
	
	private static Set<SmiPrimitiveType> alltypes = new HashSet<SmiPrimitiveType>();

	public static void validateFile() throws IOException{
		File file = new File("C:\\satish\\Java Projects\\snmp-sim\\oidmapping.txt");
		 
		
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		SmiMib mib = MibParser.parseMib();
		
		
		
		
		FileInputStream fstream = new FileInputStream("C:\\satish\\Java Projects\\snmp-sim\\iacm.walk");

		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
			validateLine(strLine,bw,mib);
		}

		//Close the input stream
		in.close();
		bw.close();
		
		
		System.out.println(alltypes);
	}

	private static void validateLine(String strLine, BufferedWriter bw, SmiMib mib) throws IOException {
		if(strLine.startsWith("#") || strLine.trim().isEmpty()) {
			System.out.println(strLine);
		}else if(hexLine.matcher(strLine).matches()){
			System.out.println(strLine);
		}else {
			String[] strings = strLine.split("=");
			String oid = strings[0].trim();
			System.out.println(oid);
			String[] oidParts = oid.split("\\.");
			System.out.println(oidParts.length);
			
			int numIndex = getNumericIndex(oidParts);
			String objectType = oidParts[numIndex -1];

			System.out.println(oidParts[numIndex -1]);
			
			SmiVariable variable = mib.getVariables().find(objectType);
			String oidStr = variable.getOidStr();
			SmiPrimitiveType type = variable.getPrimitiveType();
			
			alltypes.add(type);
			
			
			System.out.println(oidStr);
			bw.write(objectType + " = " + type + " = " + oidStr + "\n");
		}

	}

	private static int getNumericIndex(String[] oidParts) {
		for(int i = 0; i < oidParts.length ; i ++) {
			try {
				Integer.parseInt(oidParts[i]);
				return i;
			}catch(NumberFormatException ex) {
				
			}
		}
		return oidParts.length -1;
	}

	public static void main(String[] args) throws IOException {
		validateFile();
	}

}
