package de.biohazard.securemessage.client.gui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.bio.hazard.securemessage.client.service.Global;

public class StartUp {

	
	ApplicationContext lcContext = null;
	
	public static void main(String[] pStrings) {
		StartUp lcStartUp = new StartUp();
	}
	
	public StartUp() {
		lcContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
}
