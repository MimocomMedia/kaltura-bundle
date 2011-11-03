package com.kaltura.client.types;

import java.util.IllegalFormatException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.kaltura.client.KalturaObjectBase;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaObjectFactory;
import com.kaltura.client.enums.KalturaBitRateMode;
import com.kaltura.client.enums.KalturaBitRateMode;


/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class KalturaMediaInfo extends KalturaObjectBase {
    public int id = Integer.MIN_VALUE;
    public String flavorAssetId;
    public int fileSize = Integer.MIN_VALUE;
    public String containerFormat;
    public String containerId;
    public String containerProfile;
    public int containerDuration = Integer.MIN_VALUE;
    public int containerBitRate = Integer.MIN_VALUE;
    public String videoFormat;
    public String videoCodecId;
    public int videoDuration = Integer.MIN_VALUE;
    public int videoBitRate = Integer.MIN_VALUE;
    public KalturaBitRateMode videoBitRateMode;
    public int videoWidth = Integer.MIN_VALUE;
    public int videoHeight = Integer.MIN_VALUE;
    public float videoFrameRate = Float.MIN_VALUE;
    public float videoDar = Float.MIN_VALUE;
    public int videoRotation = Integer.MIN_VALUE;
    public String audioFormat;
    public String audioCodecId;
    public int audioDuration = Integer.MIN_VALUE;
    public int audioBitRate = Integer.MIN_VALUE;
    public KalturaBitRateMode audioBitRateMode;
    public int audioChannels = Integer.MIN_VALUE;
    public int audioSamplingRate = Integer.MIN_VALUE;
    public int audioResolution = Integer.MIN_VALUE;
    public String writingLib;
    public String rawData;
    public String multiStreamInfo;
    public int scanType = Integer.MIN_VALUE;
    public String multiStream;

    public KalturaMediaInfo() {
    }

    public KalturaMediaInfo(Element node) throws KalturaApiException {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String txt = aNode.getTextContent();
            String nodeName = aNode.getNodeName();
            if (false) {
                // noop
            } else if (nodeName.equals("id")) {
                try {
                    if (!txt.equals("")) this.id = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("flavorAssetId")) {
                this.flavorAssetId = txt;
                continue;
            } else if (nodeName.equals("fileSize")) {
                try {
                    if (!txt.equals("")) this.fileSize = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("containerFormat")) {
                this.containerFormat = txt;
                continue;
            } else if (nodeName.equals("containerId")) {
                this.containerId = txt;
                continue;
            } else if (nodeName.equals("containerProfile")) {
                this.containerProfile = txt;
                continue;
            } else if (nodeName.equals("containerDuration")) {
                try {
                    if (!txt.equals("")) this.containerDuration = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("containerBitRate")) {
                try {
                    if (!txt.equals("")) this.containerBitRate = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("videoFormat")) {
                this.videoFormat = txt;
                continue;
            } else if (nodeName.equals("videoCodecId")) {
                this.videoCodecId = txt;
                continue;
            } else if (nodeName.equals("videoDuration")) {
                try {
                    if (!txt.equals("")) this.videoDuration = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("videoBitRate")) {
                try {
                    if (!txt.equals("")) this.videoBitRate = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("videoBitRateMode")) {
                try {
                    if (!txt.equals("")) this.videoBitRateMode = KalturaBitRateMode.get(Integer.parseInt(txt));
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("videoWidth")) {
                try {
                    if (!txt.equals("")) this.videoWidth = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("videoHeight")) {
                try {
                    if (!txt.equals("")) this.videoHeight = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("videoFrameRate")) {
                try {
                    if (!txt.equals("")) this.videoFrameRate = Float.parseFloat(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("videoDar")) {
                try {
                    if (!txt.equals("")) this.videoDar = Float.parseFloat(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("videoRotation")) {
                try {
                    if (!txt.equals("")) this.videoRotation = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("audioFormat")) {
                this.audioFormat = txt;
                continue;
            } else if (nodeName.equals("audioCodecId")) {
                this.audioCodecId = txt;
                continue;
            } else if (nodeName.equals("audioDuration")) {
                try {
                    if (!txt.equals("")) this.audioDuration = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("audioBitRate")) {
                try {
                    if (!txt.equals("")) this.audioBitRate = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("audioBitRateMode")) {
                try {
                    if (!txt.equals("")) this.audioBitRateMode = KalturaBitRateMode.get(Integer.parseInt(txt));
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("audioChannels")) {
                try {
                    if (!txt.equals("")) this.audioChannels = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("audioSamplingRate")) {
                try {
                    if (!txt.equals("")) this.audioSamplingRate = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("audioResolution")) {
                try {
                    if (!txt.equals("")) this.audioResolution = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("writingLib")) {
                this.writingLib = txt;
                continue;
            } else if (nodeName.equals("rawData")) {
                this.rawData = txt;
                continue;
            } else if (nodeName.equals("multiStreamInfo")) {
                this.multiStreamInfo = txt;
                continue;
            } else if (nodeName.equals("scanType")) {
                try {
                    if (!txt.equals("")) this.scanType = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("multiStream")) {
                this.multiStream = txt;
                continue;
            } 

        }
    }

    public KalturaParams toParams() {
        KalturaParams kparams = super.toParams();
        kparams.setString("objectType", "KalturaMediaInfo");
        kparams.addStringIfNotNull("flavorAssetId", this.flavorAssetId);
        kparams.addIntIfNotNull("fileSize", this.fileSize);
        kparams.addStringIfNotNull("containerFormat", this.containerFormat);
        kparams.addStringIfNotNull("containerId", this.containerId);
        kparams.addStringIfNotNull("containerProfile", this.containerProfile);
        kparams.addIntIfNotNull("containerDuration", this.containerDuration);
        kparams.addIntIfNotNull("containerBitRate", this.containerBitRate);
        kparams.addStringIfNotNull("videoFormat", this.videoFormat);
        kparams.addStringIfNotNull("videoCodecId", this.videoCodecId);
        kparams.addIntIfNotNull("videoDuration", this.videoDuration);
        kparams.addIntIfNotNull("videoBitRate", this.videoBitRate);
        if (videoBitRateMode != null) kparams.addIntIfNotNull("videoBitRateMode", this.videoBitRateMode.getHashCode());
        kparams.addIntIfNotNull("videoWidth", this.videoWidth);
        kparams.addIntIfNotNull("videoHeight", this.videoHeight);
        kparams.addFloatIfNotNull("videoFrameRate", this.videoFrameRate);
        kparams.addFloatIfNotNull("videoDar", this.videoDar);
        kparams.addIntIfNotNull("videoRotation", this.videoRotation);
        kparams.addStringIfNotNull("audioFormat", this.audioFormat);
        kparams.addStringIfNotNull("audioCodecId", this.audioCodecId);
        kparams.addIntIfNotNull("audioDuration", this.audioDuration);
        kparams.addIntIfNotNull("audioBitRate", this.audioBitRate);
        if (audioBitRateMode != null) kparams.addIntIfNotNull("audioBitRateMode", this.audioBitRateMode.getHashCode());
        kparams.addIntIfNotNull("audioChannels", this.audioChannels);
        kparams.addIntIfNotNull("audioSamplingRate", this.audioSamplingRate);
        kparams.addIntIfNotNull("audioResolution", this.audioResolution);
        kparams.addStringIfNotNull("writingLib", this.writingLib);
        kparams.addStringIfNotNull("rawData", this.rawData);
        kparams.addStringIfNotNull("multiStreamInfo", this.multiStreamInfo);
        kparams.addIntIfNotNull("scanType", this.scanType);
        kparams.addStringIfNotNull("multiStream", this.multiStream);
        return kparams;
    }
}

