package com.kaltura.client.tests;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaClient;
import com.kaltura.client.KalturaConfiguration;
import com.kaltura.client.enums.KalturaEntryStatus;
import com.kaltura.client.enums.KalturaEntryType;
import com.kaltura.client.enums.KalturaMediaType;
import com.kaltura.client.enums.KalturaSessionType;
import com.kaltura.client.services.KalturaMediaService;
import com.kaltura.client.services.KalturaSessionService;
import com.kaltura.client.types.KalturaMediaEntry;

public class KalturaTestBase extends TestCase {

    private Logger logger = LoggerFactory.getLogger(KalturaTestBase.class);

    public static final String KEY_PARTNER_ID = "kaltura.partnerid";
    public static final String KEY_SECRET = "kaltura.secret";
    public static final String KEY_ADMIN_SECRET = "kaltura.adminsecret";
    public static final String KEY_ENDPOINT = "kaltura.endpoint";

    public static final String DEFAULT_ENDPOINT = "http://www.kaltura.com";
    public static final String DEFAULT_TEST_URL = "http://www.kaltura.org/demos/videos/DemoVideo.flv";
    public static final String DEFAULT_UPLOAD_FILE = "video/DemoVideo.flv";

    public KalturaConfiguration kalturaConfig = new KalturaConfiguration();

    protected KalturaClient client;

    // keeps track of test vids we upload so they can be cleaned up at the end
    protected List<String> testIds = new ArrayList<String>();

    protected boolean doCleanup = true;

    /**
     * Reads the settings.properties file from the test/resources directory into a map OR fails
     * SAMPLE: settings.properties:
kaltura.partnerid=0
kaltura.secret=sssssssss
kaltura.adminsecret=aaaaaaaaa
kaltura.endpoint=http://www.kaltura.com/
     *
     * @return the map of configuration settings
     */
    private Map<String, String> loadConfig() {
        Map<String, String> m = new HashMap<String, String>();
        try {
            InputStream is = KalturaTestBase.class.getClassLoader().getResourceAsStream("settings.properties");
            if (is == null) {
                throw(new Error("Please create a settings.properties file in test/resources with your "+KEY_PARTNER_ID+", "+KEY_SECRET+", "+KEY_ADMIN_SECRET+", "+KEY_ENDPOINT));
            }
            Properties props = new Properties();
            props.load(is);
            for (Entry<Object, Object> entry : props.entrySet()) {
                m.put((String)entry.getKey(), (String)entry.getValue());
            }
        } catch (IOException e) {
            // Fail!
            e.printStackTrace();
        }
        return m;
    }

    @Override
    protected void setUp() throws Exception {
        Map<String, String> config = loadConfig();
        if (config == null || config.isEmpty()) {
            throw(new Error("Please create a settings.properties file with your "+KEY_PARTNER_ID+", "+KEY_SECRET+", "+KEY_ADMIN_SECRET+", "+KEY_ENDPOINT));
        }
        if (StringUtils.isBlank(config.get(KEY_PARTNER_ID))) {
            throw(new Error("Please fill in the settings.properties file with your "+KEY_PARTNER_ID));
        }
        if (StringUtils.isBlank(config.get(KEY_SECRET))) {
            throw(new Error("Please fill in the settings.properties file with your "+KEY_SECRET));
        }
        if (StringUtils.isBlank(config.get(KEY_ADMIN_SECRET))) {
            throw(new Error("Please fill in the settings.properties file with your "+KEY_ADMIN_SECRET));
        }
        if (StringUtils.isBlank(config.get(KEY_ENDPOINT))) {
            config.put(KEY_ENDPOINT, DEFAULT_ENDPOINT);
        }
        this.kalturaConfig.setPartnerId( Integer.valueOf(config.get(KEY_PARTNER_ID)) );
        this.kalturaConfig.setSecret(config.get(KEY_SECRET));
        this.kalturaConfig.setAdminSecret(config.get(KEY_ADMIN_SECRET));
        this.kalturaConfig.setEndpoint(config.get(KEY_ENDPOINT));
        this.client = new KalturaClient(this.kalturaConfig);
    }

    /**
     * @return the sessionId if connected or NULL if failed
     */
    protected String startUserSession() {
        String sessionId = null;
        try {
            KalturaSessionService sessionService = this.client.getSessionService();
            assertNotNull(sessionService);
            sessionId = sessionService.start(
                    this.kalturaConfig.getSecret(),
                    "admin",
                    KalturaSessionType.USER,
                    this.kalturaConfig.getPartnerId(),
                    86400,
                    ""
            );
            logger.debug("Session id:" + sessionId);
            this.client.setSessionId(sessionId);
        } catch (Exception kae) {
            logger.error("Caught exception during setup", kae);
        }
        return sessionId;
    }

    /**
     * @return the sessionId if connected or NULL if failed
     */
    protected String startAdminSession() {
        String sessionId = null;
        try {
            KalturaSessionService sessionService = this.client.getSessionService();
            assertNotNull(sessionService);
            sessionId = sessionService.start(
                    this.kalturaConfig.getAdminSecret(),
                    "admin",
                    KalturaSessionType.ADMIN,
                    this.kalturaConfig.getPartnerId(),
                    86400,
                    ""
            );
            logger.debug("Session id:" + sessionId);
            this.client.setSessionId(sessionId);
        } catch (Exception kae) {
            logger.error("Caught exception during setup", kae);
        }
        return sessionId;
    }

    protected KalturaMediaEntry addClip(String name) {

        KalturaMediaEntry entry = new KalturaMediaEntry();

        entry.name = name;
        entry.type = KalturaEntryType.MEDIA_CLIP;
        entry.mediaType = KalturaMediaType.VIDEO;

        KalturaMediaEntry addedEntry = null;
        try {
            KalturaMediaService mediaService = this.client.getMediaService();
            addedEntry = mediaService.addFromUrl(entry, DEFAULT_TEST_URL);
        } catch (KalturaApiException kae) {
            logger.error("Caught exception during add from url", kae);
        }

        if (addedEntry != null) {
            this.testIds.add(addedEntry.id);
        }

        return addedEntry;
    }

    protected KalturaMediaEntry getProcessedClip(String id) throws Exception {
        return getProcessedClip(id, false);
    }

    protected KalturaMediaEntry getProcessedClip(String id, Boolean checkReady) throws Exception {
        int maxTries = 30;
        int sleepInterval = 300000;
        int counter = 0;
        KalturaMediaEntry retrievedEntry = null;		
        try {
            KalturaMediaService mediaService = this.client.getMediaService();
            retrievedEntry = mediaService.get(id);
            while (checkReady && retrievedEntry.status != KalturaEntryStatus.READY) {

                counter++;

                if (counter >= maxTries) {
                    throw new Exception("Max retries (" + maxTries + ") when retrieving entry:" + id);
                } else {
                    logger.info("On try: " + counter + ", clip not ready. waiting "+(sleepInterval/60000)+" minutes...");
                    try {
                        Thread.sleep(sleepInterval);
                    } catch (InterruptedException ie) {							
                    }
                }

                retrievedEntry = mediaService.get(id);				

            } //wend

        } catch (KalturaApiException kae) {
            logger.error("Problem retrieving entry: " + kae.getLocalizedMessage());
        } 

        return retrievedEntry;
    }

    @Override
    protected void tearDown() {

        if (!doCleanup) return;

        logger.info("Cleaning up test entries after test");

        KalturaMediaService mediaService = this.client.getMediaService();
        for (String id : this.testIds) {
            logger.info("Deleting " + id);
            try {
                getProcessedClip(id);
                mediaService.delete(id);			
            } catch (Exception e) {
                logger.error("Couldn't delete " + id, e);
            }
        } //next id
    }
}
