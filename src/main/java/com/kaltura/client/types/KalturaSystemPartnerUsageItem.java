package com.kaltura.client.types;

import java.util.IllegalFormatException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.kaltura.client.KalturaObjectBase;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaObjectFactory;
import com.kaltura.client.enums.KalturaPartnerStatus;


/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class KalturaSystemPartnerUsageItem extends KalturaObjectBase {
    public int partnerId = Integer.MIN_VALUE;
    public String partnerName;
    public KalturaPartnerStatus partnerStatus;
    public int partnerPackage = Integer.MIN_VALUE;
    public int partnerCreatedAt = Integer.MIN_VALUE;
    public int views = Integer.MIN_VALUE;
    public int plays = Integer.MIN_VALUE;
    public int entriesCount = Integer.MIN_VALUE;
    public int totalEntriesCount = Integer.MIN_VALUE;
    public int videoEntriesCount = Integer.MIN_VALUE;
    public int imageEntriesCount = Integer.MIN_VALUE;
    public int audioEntriesCount = Integer.MIN_VALUE;
    public int mixEntriesCount = Integer.MIN_VALUE;
    public float bandwidth = Float.MIN_VALUE;
    public float totalStorage = Float.MIN_VALUE;
    public float storage = Float.MIN_VALUE;

    public KalturaSystemPartnerUsageItem() {
    }

    public KalturaSystemPartnerUsageItem(Element node) throws KalturaApiException {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String txt = aNode.getTextContent();
            String nodeName = aNode.getNodeName();
            if (false) {
                // noop
            } else if (nodeName.equals("partnerId")) {
                try {
                    if (!txt.equals("")) this.partnerId = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("partnerName")) {
                this.partnerName = txt;
                continue;
            } else if (nodeName.equals("partnerStatus")) {
                try {
                    if (!txt.equals("")) this.partnerStatus = KalturaPartnerStatus.get(Integer.parseInt(txt));
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("partnerPackage")) {
                try {
                    if (!txt.equals("")) this.partnerPackage = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("partnerCreatedAt")) {
                try {
                    if (!txt.equals("")) this.partnerCreatedAt = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("views")) {
                try {
                    if (!txt.equals("")) this.views = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("plays")) {
                try {
                    if (!txt.equals("")) this.plays = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("entriesCount")) {
                try {
                    if (!txt.equals("")) this.entriesCount = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("totalEntriesCount")) {
                try {
                    if (!txt.equals("")) this.totalEntriesCount = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("videoEntriesCount")) {
                try {
                    if (!txt.equals("")) this.videoEntriesCount = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("imageEntriesCount")) {
                try {
                    if (!txt.equals("")) this.imageEntriesCount = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("audioEntriesCount")) {
                try {
                    if (!txt.equals("")) this.audioEntriesCount = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("mixEntriesCount")) {
                try {
                    if (!txt.equals("")) this.mixEntriesCount = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("bandwidth")) {
                try {
                    if (!txt.equals("")) this.bandwidth = Float.parseFloat(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("totalStorage")) {
                try {
                    if (!txt.equals("")) this.totalStorage = Float.parseFloat(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("storage")) {
                try {
                    if (!txt.equals("")) this.storage = Float.parseFloat(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } 

        }
    }

    public KalturaParams toParams() {
        KalturaParams kparams = super.toParams();
        kparams.setString("objectType", "KalturaSystemPartnerUsageItem");
        kparams.addIntIfNotNull("partnerId", this.partnerId);
        kparams.addStringIfNotNull("partnerName", this.partnerName);
        if (partnerStatus != null) kparams.addIntIfNotNull("partnerStatus", this.partnerStatus.getHashCode());
        kparams.addIntIfNotNull("partnerPackage", this.partnerPackage);
        kparams.addIntIfNotNull("partnerCreatedAt", this.partnerCreatedAt);
        kparams.addIntIfNotNull("views", this.views);
        kparams.addIntIfNotNull("plays", this.plays);
        kparams.addIntIfNotNull("entriesCount", this.entriesCount);
        kparams.addIntIfNotNull("totalEntriesCount", this.totalEntriesCount);
        kparams.addIntIfNotNull("videoEntriesCount", this.videoEntriesCount);
        kparams.addIntIfNotNull("imageEntriesCount", this.imageEntriesCount);
        kparams.addIntIfNotNull("audioEntriesCount", this.audioEntriesCount);
        kparams.addIntIfNotNull("mixEntriesCount", this.mixEntriesCount);
        kparams.addFloatIfNotNull("bandwidth", this.bandwidth);
        kparams.addFloatIfNotNull("totalStorage", this.totalStorage);
        kparams.addFloatIfNotNull("storage", this.storage);
        return kparams;
    }
}

