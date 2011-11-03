package com.kaltura.client.enums;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaDistributionProviderType {
    GENERIC ("1"),
    SYNDICATION ("2"),
    YOUTUBE_API ("youtubeApiDistribution.YOUTUBE_API"),
    DAILYMOTION ("dailymotionDistribution.DAILYMOTION"),
    MSN ("msnDistribution.MSN"),
    VERIZON ("verizonDistribution.VERIZON"),
    COMCAST ("comcastDistribution.COMCAST"),
    YOUTUBE ("youTubeDistribution.YOUTUBE");

    String hashCode;

    KalturaDistributionProviderType(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public static KalturaDistributionProviderType get(String hashCode) {
        if (hashCode.equals("1"))
        {
           return GENERIC;
        }
        else 
        if (hashCode.equals("2"))
        {
           return SYNDICATION;
        }
        else 
        if (hashCode.equals("youtubeApiDistribution.YOUTUBE_API"))
        {
           return YOUTUBE_API;
        }
        else 
        if (hashCode.equals("dailymotionDistribution.DAILYMOTION"))
        {
           return DAILYMOTION;
        }
        else 
        if (hashCode.equals("msnDistribution.MSN"))
        {
           return MSN;
        }
        else 
        if (hashCode.equals("verizonDistribution.VERIZON"))
        {
           return VERIZON;
        }
        else 
        if (hashCode.equals("comcastDistribution.COMCAST"))
        {
           return COMCAST;
        }
        else 
        if (hashCode.equals("youTubeDistribution.YOUTUBE"))
        {
           return YOUTUBE;
        }
        else 
        {
           return GENERIC;
        }
    }
}
