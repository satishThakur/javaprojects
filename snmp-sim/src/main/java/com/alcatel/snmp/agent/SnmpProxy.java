package com.alcatel.snmp.agent;




import org.snmp4j.*;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.transport.UdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;
 
import java.io.IOException;
 
public class SnmpProxy {
 
   private Snmp m_session;
 
   public SnmpProxy(int localPort, final int timeout, final int retries) throws IOException {
 
       final MultiThreadedMessageDispatcher dispatcher = new MultiThreadedMessageDispatcher(ThreadPool.create("SNMP daemon", 10), new MessageDispatcherImpl());
 
       //final MessageDispatcherImpl dispatcher = new MessageDispatcherImpl();
 
       dispatcher.addMessageProcessingModel(new MPv2c());
 
       dispatcher.addMessageProcessingModel(new MPv1());
       //SecurityProtocols.getInstance().addDefaultProtocols();
       m_session = new Snmp(dispatcher);
       dispatcher.addCommandResponder(new CommandResponder() {
           public void processPdu(CommandResponderEvent commandResponderEvent) {
               commandResponderEvent.getPeerAddress();
               PDU pdu = commandResponderEvent.getPDU();
               System.out.println("received :" + pdu);
              
           }
       }
       );
       UdpTransportMapping transportMapping = new DefaultUdpTransportMapping(new UdpAddress(localPort));
       m_session.addTransportMapping(transportMapping);
       transportMapping.listen();
   }
 
   public static synchronized void main(String[] args) throws IOException, InterruptedException {
       //LogFactory.setLogFactory(new Log4jLogFactory());
      
       int retries = 0;       
       int timeout = 2000; 
       new SnmpProxy(16500, timeout, retries); 
 
       synchronized (SnmpProxy.class) {
           SnmpProxy.class.wait();
       }
   }
 
}