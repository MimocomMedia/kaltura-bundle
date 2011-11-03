/**
 * Copyright 2011 Unicon (R) Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package net.unicon.kaltura.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sakaiproject.nakamura.api.files.FilesConstants;


/**
 * Unit testing for the kaltura service
 * 
 * @author Aaron Zeckoski (azeckoski @ vt.edu)
 */
public class KalturaServiceTest {

    private KalturaService service;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        service = new KalturaService();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        service = null;
    }

    @Test
    public void testIsFileVideo() {
        assertTrue( service.isFileVideo(".mp4", "") );
        assertTrue( service.isFileVideo("", "video/mpeg") );
        assertTrue( service.isFileVideo(".mov", "video/quicktime") );

        assertFalse( service.isFileVideo(".xls", "") );
        assertFalse( service.isFileVideo("", "text/html") );
        assertFalse( service.isFileVideo(".txt", "text/plain") );
        assertFalse( service.isFileVideo("", "") );
        assertFalse( service.isFileVideo(null, null) );

        // NOTE: cannot throw exception
    }

    @Test
    public void testIsFileAudio() {
        assertTrue( service.isFileAudio(".mp3", "") );
        assertTrue( service.isFileAudio("", "audio/mpeg") );
        assertTrue( service.isFileAudio(".aif", "audio/x-aiff") );

        assertFalse( service.isFileAudio(".xls", "") );
        assertFalse( service.isFileAudio("", "text/html") );
        assertFalse( service.isFileAudio(".txt", "text/plain") );
        assertFalse( service.isFileAudio("", "") );
        assertFalse( service.isFileAudio(null, null) );

        // NOTE: cannot throw exception
    }

    @Test
    public void testMakeKalturaTitle() {
        String title = null;
        Map<String, Object> contentProperties = new HashMap<String, Object>();
        contentProperties.put(FilesConstants.POOLED_CONTENT_FILENAME, "AZ-title");
        title = service.makeKalturaTitle(contentProperties, 0);
        assertNotNull(title);
        assertEquals("AZ-title - 1", title);

        contentProperties.clear();
        title = service.makeKalturaTitle(contentProperties, 0);
        assertNotNull(title);
        assertEquals("title - 1", title);
    }

    @Test
    public void testMakeKalturaTags() {
        String tags = null;
        Map<String, Object> contentProperties = new HashMap<String, Object>();
        contentProperties.put(FilesConstants.SAKAI_TAGS, new String[] {"az","bz"});
        tags = service.makeKalturaTags(contentProperties);
        assertNotNull(tags);
        assertEquals("az,bz", tags);
        
        contentProperties.clear();
        tags = service.makeKalturaTags(contentProperties);
        assertNotNull(tags);
        assertEquals("", tags);
    }

}
