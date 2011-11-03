package com.kaltura.client.types;

import java.util.IllegalFormatException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.kaltura.client.KalturaObjectBase;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaObjectFactory;
import com.kaltura.client.enums.KalturaTrackEntryEventType;


/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class KalturaTrackEntry extends KalturaObjectBase {
    public int id = Integer.MIN_VALUE;
    public KalturaTrackEntryEventType trackEventType;
    public String psVersion;
    public String context;
    public int partnerId = Integer.MIN_VALUE;
    public String entryId;
    public String hostName;
    public String userId;
    public String changedProperties;
    public String paramStr1;
    public String paramStr2;
    public String paramStr3;
    public String ks;
    public String description;
    public int createdAt = Integer.MIN_VALUE;
    public int updatedAt = Integer.MIN_VALUE;
    public String userIp;

    public KalturaTrackEntry() {
    }

    public KalturaTrackEntry(Element node) throws KalturaApiException {
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
            } else if (nodeName.equals("trackEventType")) {
                try {
                    if (!txt.equals("")) this.trackEventType = KalturaTrackEntryEventType.get(Integer.parseInt(txt));
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("psVersion")) {
                this.psVersion = txt;
                continue;
            } else if (nodeName.equals("context")) {
                this.context = txt;
                continue;
            } else if (nodeName.equals("partnerId")) {
                try {
                    if (!txt.equals("")) this.partnerId = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("entryId")) {
                this.entryId = txt;
                continue;
            } else if (nodeName.equals("hostName")) {
                this.hostName = txt;
                continue;
            } else if (nodeName.equals("userId")) {
                this.userId = txt;
                continue;
            } else if (nodeName.equals("changedProperties")) {
                this.changedProperties = txt;
                continue;
            } else if (nodeName.equals("paramStr1")) {
                this.paramStr1 = txt;
                continue;
            } else if (nodeName.equals("paramStr2")) {
                this.paramStr2 = txt;
                continue;
            } else if (nodeName.equals("paramStr3")) {
                this.paramStr3 = txt;
                continue;
            } else if (nodeName.equals("ks")) {
                this.ks = txt;
                continue;
            } else if (nodeName.equals("description")) {
                this.description = txt;
                continue;
            } else if (nodeName.equals("createdAt")) {
                try {
                    if (!txt.equals("")) this.createdAt = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("updatedAt")) {
                try {
                    if (!txt.equals("")) this.updatedAt = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("userIp")) {
                this.userIp = txt;
                continue;
            } 

        }
    }

    public KalturaParams toParams() {
        KalturaParams kparams = super.toParams();
        kparams.setString("objectType", "KalturaTrackEntry");
        kparams.addIntIfNotNull("id", this.id);
        if (trackEventType != null) kparams.addIntIfNotNull("trackEventType", this.trackEventType.getHashCode());
        kparams.addStringIfNotNull("psVersion", this.psVersion);
        kparams.addStringIfNotNull("context", this.context);
        kparams.addIntIfNotNull("partnerId", this.partnerId);
        kparams.addStringIfNotNull("entryId", this.entryId);
        kparams.addStringIfNotNull("hostName", this.hostName);
        kparams.addStringIfNotNull("userId", this.userId);
        kparams.addStringIfNotNull("changedProperties", this.changedProperties);
        kparams.addStringIfNotNull("paramStr1", this.paramStr1);
        kparams.addStringIfNotNull("paramStr2", this.paramStr2);
        kparams.addStringIfNotNull("paramStr3", this.paramStr3);
        kparams.addStringIfNotNull("ks", this.ks);
        kparams.addStringIfNotNull("description", this.description);
        kparams.addIntIfNotNull("createdAt", this.createdAt);
        kparams.addIntIfNotNull("updatedAt", this.updatedAt);
        kparams.addStringIfNotNull("userIp", this.userIp);
        return kparams;
    }
}

