package com.kaltura.client.enums;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaVirusScanEngineType {
    SYMANTEC_SCAN_ENGINE ("symantecScanEngine.SymantecScanEngine");

    String hashCode;

    KalturaVirusScanEngineType(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public static KalturaVirusScanEngineType get(String hashCode) {
        if (hashCode.equals("symantecScanEngine.SymantecScanEngine"))
        {
           return SYMANTEC_SCAN_ENGINE;
        }
        else 
        {
           return SYMANTEC_SCAN_ENGINE;
        }
    }
}
