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
import com.kaltura.client.enums.KalturaContainerFormat;


/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public abstract class KalturaAssetParamsBaseFilter extends KalturaFilter {
    public KalturaNullableBoolean isSystemDefaultEqual;
    public String tagsEqual;
    public KalturaContainerFormat formatEqual;

    public KalturaAssetParamsBaseFilter() {
    }

    public KalturaAssetParamsBaseFilter(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String txt = aNode.getTextContent();
            String nodeName = aNode.getNodeName();
            if (false) {
                // noop
            } else if (nodeName.equals("isSystemDefaultEqual")) {
                try {
                    if (!txt.equals("")) this.isSystemDefaultEqual = KalturaNullableBoolean.get(Integer.parseInt(txt));
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("tagsEqual")) {
                this.tagsEqual = txt;
                continue;
            } else if (nodeName.equals("formatEqual")) {
                try {
                    if (!txt.equals("")) this.formatEqual = KalturaContainerFormat.get(txt);
                } catch (IllegalFormatException ife) {}
                continue;
            } 

        }
    }

    public KalturaParams toParams() {
        KalturaParams kparams = super.toParams();
        kparams.setString("objectType", "KalturaAssetParamsBaseFilter");
        if (isSystemDefaultEqual != null) kparams.addIntIfNotNull("isSystemDefaultEqual", this.isSystemDefaultEqual.getHashCode());
        kparams.addStringIfNotNull("tagsEqual", this.tagsEqual);
        if (formatEqual != null) kparams.addStringIfNotNull("formatEqual", this.formatEqual.getHashCode());
        return kparams;
    }
}

