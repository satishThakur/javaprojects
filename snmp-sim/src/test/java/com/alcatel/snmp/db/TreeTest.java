package com.alcatel.snmp.db;

import org.junit.Test;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.Variable;

public class TreeTest {
	
	@Test
	
	public void testTree() {
		
		int[] oid = {1,3,6,1,2,1,2,2,1,1,1};
		Variable oidVal = new Integer32(1);
		
		int[] oid1 = {1,3,6,1,2,1,2,2,1,1,2};
		Variable oidVal1 = new Integer32(2);
		
		int[] oid2 = {1,3,6,1,2,1,2,2,1,2,1};
		Variable oidVal2 = new Integer32(3);
		
		int[] oid3 = {1,3,6,1,2,1,2,2,1,2,1};
		Variable oidVal3 = new Integer32(4);
		
		
		
	}

}
