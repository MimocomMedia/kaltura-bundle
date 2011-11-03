package com.kaltura.client.enums;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaTrackEntryEventType {
    UPLOADED_FILE (1),
    WEBCAM_COMPLETED (2),
    IMPORT_STARTED (3),
    ADD_ENTRY (4),
    UPDATE_ENTRY (5),
    DELETED_ENTRY (6);

    int hashCode;

    KalturaTrackEntryEventType(int hashCode) {
        this.hashCode = hashCode;
    }

    public int getHashCode() {
        return this.hashCode;
    }

    public static KalturaTrackEntryEventType get(int hashCode) {
        switch(hashCode) {
            case 1: return UPLOADED_FILE;
            case 2: return WEBCAM_COMPLETED;
            case 3: return IMPORT_STARTED;
            case 4: return ADD_ENTRY;
            case 5: return UPDATE_ENTRY;
            case 6: return DELETED_ENTRY;
            default: return UPLOADED_FILE;
        }
    }
}
