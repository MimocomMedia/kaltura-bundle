package com.kaltura.client.enums;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaConversionEngineType {
    KALTURA_COM ("0"),
    ON2 ("1"),
    FFMPEG ("2"),
    MENCODER ("3"),
    ENCODING_COM ("4"),
    EXPRESSION_ENCODER3 ("5"),
    FFMPEG_VP8 ("98"),
    FFMPEG_AUX ("99"),
    PDF2SWF ("201"),
    PDF_CREATOR ("202"),
    QUICK_TIME_PLAYER_TOOLS ("quickTimeTools.QuickTimeTools"),
    FAST_START ("fastStart.FastStart"),
    EXPRESSION_ENCODER ("expressionEncoder.ExpressionEncoder"),
    AVIDEMUX ("avidemux.Avidemux"),
    SEGMENTER ("segmenter.Segmenter"),
    INLET_ARMADA ("inletArmada.InletArmada");

    String hashCode;

    KalturaConversionEngineType(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public static KalturaConversionEngineType get(String hashCode) {
        if (hashCode.equals("0"))
        {
           return KALTURA_COM;
        }
        else 
        if (hashCode.equals("1"))
        {
           return ON2;
        }
        else 
        if (hashCode.equals("2"))
        {
           return FFMPEG;
        }
        else 
        if (hashCode.equals("3"))
        {
           return MENCODER;
        }
        else 
        if (hashCode.equals("4"))
        {
           return ENCODING_COM;
        }
        else 
        if (hashCode.equals("5"))
        {
           return EXPRESSION_ENCODER3;
        }
        else 
        if (hashCode.equals("98"))
        {
           return FFMPEG_VP8;
        }
        else 
        if (hashCode.equals("99"))
        {
           return FFMPEG_AUX;
        }
        else 
        if (hashCode.equals("201"))
        {
           return PDF2SWF;
        }
        else 
        if (hashCode.equals("202"))
        {
           return PDF_CREATOR;
        }
        else 
        if (hashCode.equals("quickTimeTools.QuickTimeTools"))
        {
           return QUICK_TIME_PLAYER_TOOLS;
        }
        else 
        if (hashCode.equals("fastStart.FastStart"))
        {
           return FAST_START;
        }
        else 
        if (hashCode.equals("expressionEncoder.ExpressionEncoder"))
        {
           return EXPRESSION_ENCODER;
        }
        else 
        if (hashCode.equals("avidemux.Avidemux"))
        {
           return AVIDEMUX;
        }
        else 
        if (hashCode.equals("segmenter.Segmenter"))
        {
           return SEGMENTER;
        }
        else 
        if (hashCode.equals("inletArmada.InletArmada"))
        {
           return INLET_ARMADA;
        }
        else 
        {
           return KALTURA_COM;
        }
    }
}
