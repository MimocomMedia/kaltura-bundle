package com.kaltura.client.types;

import java.util.IllegalFormatException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.kaltura.client.KalturaObjectBase;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaObjectFactory;
import com.kaltura.client.enums.KalturaNullableBoolean;


/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class KalturaConversionProfile extends KalturaObjectBase {
    public int id = Integer.MIN_VALUE;
    public int partnerId = Integer.MIN_VALUE;
    public String name;
    public String description;
    public int createdAt = Integer.MIN_VALUE;
    public String flavorParamsIds;
    public KalturaNullableBoolean isDefault;
    public KalturaCropDimensions cropDimensions;
    public int clipStart = Integer.MIN_VALUE;
    public int clipDuration = Integer.MIN_VALUE;

    public KalturaConversionProfile() {
    }

    public KalturaConversionProfile(Element node) throws KalturaApiException {
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
            } else if (nodeName.equals("partnerId")) {
                try {
                    if (!txt.equals("")) this.partnerId = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("name")) {
                this.name = txt;
                continue;
            } else if (nodeName.equals("description")) {
                this.description = txt;
                continue;
            } else if (nodeName.equals("createdAt")) {
                try {
                    if (!txt.equals("")) this.createdAt = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("flavorParamsIds")) {
                this.flavorParamsIds = txt;
                continue;
            } else if (nodeName.equals("isDefault")) {
                try {
                    if (!txt.equals("")) this.isDefault = KalturaNullableBoolean.get(Integer.parseInt(txt));
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("cropDimensions")) {
                this.cropDimensions = (KalturaCropDimensions)KalturaObjectFactory.create((Element)aNode);
                continue;
            } else if (nodeName.equals("clipStart")) {
                try {
                    if (!txt.equals("")) this.clipStart = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("clipDuration")) {
                try {
                    if (!txt.equals("")) this.clipDuration = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } 

        }
    }

    public KalturaParams toParams() {
        KalturaParams kparams = super.toParams();
        kparams.setString("objectType", "KalturaConversionProfile");
        kparams.addStringIfNotNull("name", this.name);
        kparams.addStringIfNotNull("description", this.description);
        kparams.addStringIfNotNull("flavorParamsIds", this.flavorParamsIds);
        if (isDefault != null) kparams.addIntIfNotNull("isDefault", this.isDefault.getHashCode());
        kparams.addObjectIfNotNull("cropDimensions", this.cropDimensions);
        kparams.addIntIfNotNull("clipStart", this.clipStart);
        kparams.addIntIfNotNull("clipDuration", this.clipDuration);
        return kparams;
    }
}

