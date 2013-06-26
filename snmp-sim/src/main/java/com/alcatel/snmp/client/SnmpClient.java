package com.alcatel.snmp.client;

import java.io.IOException;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SnmpClient implements CommandResponder {

	Snmp m_snmp;

	public SnmpClient() throws IOException {
		init();
	}
	
	public void sendPdu() throws IOException {
		PDU pdu = new PDU();
		pdu.add(new VariableBinding(new OID("1.3"))); // sysDescr
		pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.2.1"))); // ifNumber
		
		pdu.setType(PDU.GETNEXT);
		
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString("public"));
		Address targetAddress = GenericAddress.parse("udp:135.249.34.42/15500");
		//Address targetAddress = GenericAddress.parse("udp:135.249.33.142/161");
		target.setAddress(targetAddress);
		target.setVersion(SnmpConstants.version2c);
		target.setRetries(1);
		// set timeout to 500 milliseconds -> 2*500ms = 1s total timeout
		target.setTimeout(1000);
		
		ResponseEvent event = m_snmp.send(pdu, target);
		
		if(event.getResponse() != null) {
			System.out.println("success" + event.getResponse());
		}else {
			System.out.println("failure");
		}
	}

	private void init() throws IOException {	
		TransportMapping<?> tm = new DefaultUdpTransportMapping();
		m_snmp = new Snmp(tm);
		m_snmp.listen();
		//m_snmp.addCommandResponder(this);
	}

	@Override
	public void processPdu(CommandResponderEvent event) {
		
		
	}
	
	public static void main(String[] args) throws IOException {
		SnmpClient client = new SnmpClient();
		client.init();
		client.sendPdu();
		
		boolean flag = true;
		while(flag) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				flag = false;
				e.printStackTrace();
			}
		}
		
	}
}
