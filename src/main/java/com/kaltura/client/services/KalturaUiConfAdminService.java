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

public class KalturaUiConfAdminService extends KalturaServiceBase {
    public KalturaUiConfAdminService(KalturaClient client) {
        this.kalturaClient = client;
    }

    public KalturaUiConfAdmin add(KalturaUiConfAdmin uiConf) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        if (uiConf != null) kparams.add("uiConf", uiConf.toParams());
        this.kalturaClient.queueServiceCall("adminconsole_uiconfadmin", "add", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaUiConfAdmin)KalturaObjectFactory.create(resultXmlElement);
    }

    public KalturaUiConfAdmin update(int id, KalturaUiConfAdmin uiConf) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.addIntIfNotNull("id", id);
        if (uiConf != null) kparams.add("uiConf", uiConf.toParams());
        this.kalturaClient.queueServiceCall("adminconsole_uiconfadmin", "update", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaUiConfAdmin)KalturaObjectFactory.create(resultXmlElement);
    }

    public KalturaUiConfAdmin get(int id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.addIntIfNotNull("id", id);
        this.kalturaClient.queueServiceCall("adminconsole_uiconfadmin", "get", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaUiConfAdmin)KalturaObjectFactory.create(resultXmlElement);
    }

    public void delete(int id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.addIntIfNotNull("id", id);
        this.kalturaClient.queueServiceCall("adminconsole_uiconfadmin", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return;
        Element resultXmlElement = this.kalturaClient.doQueue();
    }

    public KalturaUiConfAdminListResponse list() throws KalturaApiException {
        return this.list(null);
    }

    public KalturaUiConfAdminListResponse list(KalturaUiConfFilter filter) throws KalturaApiException {
        return this.list(filter, null);
    }

    public KalturaUiConfAdminListResponse list(KalturaUiConfFilter filter, KalturaFilterPager pager) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        if (filter != null) kparams.add("filter", filter.toParams());
        if (pager != null) kparams.add("pager", pager.toParams());
        this.kalturaClient.queueServiceCall("adminconsole_uiconfadmin", "list", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaUiConfAdminListResponse)KalturaObjectFactory.create(resultXmlElement);
    }
}
