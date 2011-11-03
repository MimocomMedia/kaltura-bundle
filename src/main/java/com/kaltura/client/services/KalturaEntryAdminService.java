package com.kaltura.client.services;

import org.w3c.dom.Element;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaClient;
import com.kaltura.client.KalturaObjectFactory;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaServiceBase;
import com.kaltura.client.utils.XmlUtils;
import com.kaltura.client.enums.*;
import com.kaltura.client.types.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import com.kaltura.client.KalturaFiles;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class KalturaEntryAdminService extends KalturaServiceBase {
    public KalturaEntryAdminService(KalturaClient client) {
        this.kalturaClient = client;
    }

    public KalturaBaseEntry get(String entryId) throws KalturaApiException {
        return this.get(entryId, -1);
    }

    public KalturaBaseEntry get(String entryId, int version) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.addStringIfNotNull("entryId", entryId);
        kparams.addIntIfNotNull("version", version);
        this.kalturaClient.queueServiceCall("adminconsole_entryadmin", "get", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaBaseEntry)KalturaObjectFactory.create(resultXmlElement);
    }

    public KalturaBaseEntry getByFlavorId(String flavorId) throws KalturaApiException {
        return this.getByFlavorId(flavorId, -1);
    }

    public KalturaBaseEntry getByFlavorId(String flavorId, int version) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.addStringIfNotNull("flavorId", flavorId);
        kparams.addIntIfNotNull("version", version);
        this.kalturaClient.queueServiceCall("adminconsole_entryadmin", "getByFlavorId", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaBaseEntry)KalturaObjectFactory.create(resultXmlElement);
    }

    public KalturaTrackEntryListResponse getTracks(String entryId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.addStringIfNotNull("entryId", entryId);
        this.kalturaClient.queueServiceCall("adminconsole_entryadmin", "getTracks", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaTrackEntryListResponse)KalturaObjectFactory.create(resultXmlElement);
    }
}
