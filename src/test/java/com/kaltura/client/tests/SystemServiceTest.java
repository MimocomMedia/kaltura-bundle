package com.kaltura.client.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaltura.client.services.KalturaSystemService;

public class SystemServiceTest extends KalturaTestBase {
	
	private Logger logger = LoggerFactory.getLogger(SystemServiceTest.class);
			
	public void testPing() {
		logger.info("Starting ping test");

		startUserSession();
		
		boolean exceptionThrown = false;
		try {
			KalturaSystemService systemService = this.client.getSystemService();
			boolean result = systemService.ping();
			assertTrue(result);
		} catch (Exception e) {
			exceptionThrown = true;
		}
		
		assertFalse(exceptionThrown);
		
	}
		
}
