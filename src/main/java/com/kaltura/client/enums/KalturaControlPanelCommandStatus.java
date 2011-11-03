package com.kaltura.client.enums;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaControlPanelCommandStatus {
    PENDING (1),
    HANDLED (2),
    DONE (3),
    FAILED (4);

    int hashCode;

    KalturaControlPanelCommandStatus(int hashCode) {
        this.hashCode = hashCode;
    }

    public int getHashCode() {
        return this.hashCode;
    }

    public static KalturaControlPanelCommandStatus get(int hashCode) {
        switch(hashCode) {
            case 1: return PENDING;
            case 2: return HANDLED;
            case 3: return DONE;
            case 4: return FAILED;
            default: return PENDING;
        }
    }
}
