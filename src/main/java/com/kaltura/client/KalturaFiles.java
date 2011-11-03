package com.kaltura.client;

import java.util.HashMap;

/**
 * Helper class that provides a collection of Files.
 * 
 * @author jpotts
 * @author azeckoski
 */
public class KalturaFiles extends HashMap<String, KalturaFile> {

    private static final long serialVersionUID = -5838275045069221834L;

    public void add(KalturaFiles files) {
        for (String key : files.keySet()) {
            this.put(key, files.get(key));
        }
    }

}
