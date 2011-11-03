package com.kaltura.client.types;

import java.util.IllegalFormatException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.kaltura.client.KalturaObjectBase;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaObjectFactory;
import com.kaltura.client.enums.KalturaSessionType;


/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class KalturaInternalToolsSession extends KalturaObjectBase {
    public int partner_id = Integer.MIN_VALUE;
    public int valid_until = Integer.MIN_VALUE;
    public String partner_pattern;
    public KalturaSessionType type;
    public String error;
    public int rand = Integer.MIN_VALUE;
    public String user;
    public String privileges;

    public KalturaInternalToolsSession() {
    }

    public KalturaInternalToolsSession(Element node) throws KalturaApiException {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String txt = aNode.getTextContent();
            String nodeName = aNode.getNodeName();
            if (false) {
                // noop
            } else if (nodeName.equals("partner_id")) {
                try {
                    if (!txt.equals("")) this.partner_id = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("valid_until")) {
                try {
                    if (!txt.equals("")) this.valid_until = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("partner_pattern")) {
                this.partner_pattern = txt;
                continue;
            } else if (nodeName.equals("type")) {
                try {
                    if (!txt.equals("")) this.type = KalturaSessionType.get(Integer.parseInt(txt));
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("error")) {
                this.error = txt;
                continue;
            } else if (nodeName.equals("rand")) {
                try {
                    if (!txt.equals("")) this.rand = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("user")) {
                this.user = txt;
                continue;
            } else if (nodeName.equals("privileges")) {
                this.privileges = txt;
                continue;
            } 

        }
    }

    public KalturaParams toParams() {
        KalturaParams kparams = super.toParams();
        kparams.setString("objectType", "KalturaInternalToolsSession");
        kparams.addIntIfNotNull("partner_id", this.partner_id);
        kparams.addIntIfNotNull("valid_until", this.valid_until);
        kparams.addStringIfNotNull("partner_pattern", this.partner_pattern);
        if (type != null) kparams.addIntIfNotNull("type", this.type.getHashCode());
        kparams.addStringIfNotNull("error", this.error);
        kparams.addIntIfNotNull("rand", this.rand);
        kparams.addStringIfNotNull("user", this.user);
        kparams.addStringIfNotNull("privileges", this.privileges);
        return kparams;
    }
}

