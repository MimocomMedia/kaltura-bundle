package com.kaltura.client.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionServiceTest extends KalturaTestBase {

	private Logger logger = LoggerFactory.getLogger(SessionServiceTest.class);
	
	public void testSession() {
	    String sessionId = this.startUserSession();
    	assertNotNull(sessionId);
    	logger.info("User SessionId:" + sessionId);

        sessionId = this.startAdminSession();
        assertNotNull(sessionId);
        logger.info("Admin SessionId:" + sessionId);
	}
}
