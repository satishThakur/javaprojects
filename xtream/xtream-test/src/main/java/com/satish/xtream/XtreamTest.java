package com.satish.xtream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.XppDomDriver;

public class XtreamTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XStream xstream = new XStream( new XppDomDriver());
		Object testObject = TestObject.getTestObject();

		System.out.println(testObject);

		String xmlString  = xstream.toXML(testObject);
		System.out.println(xmlString);

		Object returnedObj = xstream.fromXML(xmlString);

		System.out.println(returnedObj);


		System.out.println("-----------------JASON TEST----------------------");

		XStream jason  = new XStream(new JettisonMappedXmlDriver());
		jason.setMode(XStream.NO_REFERENCES);
		
		xstream.alias("testObject", TestJasonObj.class);
		String jasonString = jason.toXML(new TestJasonObj()); 
		System.out.println(jasonString);
		
		Object jasonReturn = jason.fromXML(jasonString);
		System.out.println(jasonReturn);
	}

}
