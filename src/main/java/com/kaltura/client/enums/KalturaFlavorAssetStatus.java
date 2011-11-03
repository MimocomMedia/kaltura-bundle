package com.kaltura.client.enums;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaFlavorAssetStatus {
    ERROR (-1),
    QUEUED (0),
    CONVERTING (1),
    READY (2),
    DELETED (3),
    NOT_APPLICABLE (4),
    TEMP (5);

    int hashCode;

    KalturaFlavorAssetStatus(int hashCode) {
        this.hashCode = hashCode;
    }

    public int getHashCode() {
        return this.hashCode;
    }

    public static KalturaFlavorAssetStatus get(int hashCode) {
        switch(hashCode) {
            case -1: return ERROR;
            case 0: return QUEUED;
            case 1: return CONVERTING;
            case 2: return READY;
            case 3: return DELETED;
            case 4: return NOT_APPLICABLE;
            case 5: return TEMP;
            default: return ERROR;
        }
    }
}
