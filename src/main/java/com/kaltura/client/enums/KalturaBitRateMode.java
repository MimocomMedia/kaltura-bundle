package com.kaltura.client.enums;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaBitRateMode {
    CBR (1),
    VBR (2);

    int hashCode;

    KalturaBitRateMode(int hashCode) {
        this.hashCode = hashCode;
    }

    public int getHashCode() {
        return this.hashCode;
    }

    public static KalturaBitRateMode get(int hashCode) {
        switch(hashCode) {
            case 1: return CBR;
            case 2: return VBR;
            default: return CBR;
        }
    }
}
