package com.kaltura.client.enums;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaStorageServePriority {
    KALTURA_ONLY (1),
    KALTURA_FIRST (2),
    EXTERNAL_FIRST (3),
    EXTERNAL_ONLY (4);

    int hashCode;

    KalturaStorageServePriority(int hashCode) {
        this.hashCode = hashCode;
    }

    public int getHashCode() {
        return this.hashCode;
    }

    public static KalturaStorageServePriority get(int hashCode) {
        switch(hashCode) {
            case 1: return KALTURA_ONLY;
            case 2: return KALTURA_FIRST;
            case 3: return EXTERNAL_FIRST;
            case 4: return EXTERNAL_ONLY;
            default: return KALTURA_ONLY;
        }
    }
}
