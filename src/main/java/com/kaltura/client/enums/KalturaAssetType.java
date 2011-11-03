package com.kaltura.client.enums;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaAssetType {
    FLAVOR ("1"),
    THUMBNAIL ("2"),
    DOCUMENT ("document.Document"),
    SWF ("document.SWF"),
    PDF ("document.PDF");

    String hashCode;

    KalturaAssetType(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public static KalturaAssetType get(String hashCode) {
        if (hashCode.equals("1"))
        {
           return FLAVOR;
        }
        else 
        if (hashCode.equals("2"))
        {
           return THUMBNAIL;
        }
        else 
        if (hashCode.equals("document.Document"))
        {
           return DOCUMENT;
        }
        else 
        if (hashCode.equals("document.SWF"))
        {
           return SWF;
        }
        else 
        if (hashCode.equals("document.PDF"))
        {
           return PDF;
        }
        else 
        {
           return FLAVOR;
        }
    }
}
