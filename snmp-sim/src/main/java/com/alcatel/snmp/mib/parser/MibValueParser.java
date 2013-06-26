package com.alcatel.snmp.mib.parser;

import org.jsmiparser.smi.SmiPrimitiveType;
import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.Counter64;
import org.snmp4j.smi.Gauge32;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.IpAddress;
import org.snmp4j.smi.UnsignedInteger32;
import org.snmp4j.smi.Variable;

public class MibValueParser {

	public static Variable parse(SmiPrimitiveType type, String value) {


		switch (type) {
		case INTEGER:			
		case INTEGER_32:
			return new Integer32(Integer.parseInt(value));
		case COUNTER_32:
			return new Counter32(Long.parseLong(value));
		case COUNTER_64:
			return new Counter64(Long.parseLong(value));
		case UNSIGNED_32:
			return new UnsignedInteger32(Integer.parseInt(value));
		case GAUGE_32:
			return new Gauge32(Long.parseLong(value));
		case OCTET_STRING:
		case IP_ADDRESS:
			return new IpAddress(value);
		case TIME_TICKS:
		case OBJECT_IDENTIFIER:
		case ENUM :
			return null;
			


		}
		return null;



	}

}
