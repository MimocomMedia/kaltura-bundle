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

public class KalturaSystemPartnerService extends KalturaServiceBase {
    public KalturaSystemPartnerService(KalturaClient client) {
        this.kalturaClient = client;
    }

    public KalturaPartner get(int partnerId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.addIntIfNotNull("partnerId", partnerId);
        this.kalturaClient.queueServiceCall("systempartner_systempartner", "get", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaPartner)KalturaObjectFactory.create(resultXmlElement);
    }

    public KalturaSystemPartnerUsageListResponse getUsage() throws KalturaApiException {
        return this.getUsage(null);
    }

    public KalturaSystemPartnerUsageListResponse getUsage(KalturaPartnerFilter partnerFilter) throws KalturaApiException {
        return this.getUsage(partnerFilter, null);
    }

    public KalturaSystemPartnerUsageListResponse getUsage(KalturaPartnerFilter partnerFilter, KalturaSystemPartnerUsageFilter usageFilter) throws KalturaApiException {
        return this.getUsage(partnerFilter, usageFilter, null);
    }

    public KalturaSystemPartnerUsageListResponse getUsage(KalturaPartnerFilter partnerFilter, KalturaSystemPartnerUsageFilter usageFilter, KalturaFilterPager pager) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        if (partnerFilter != null) kparams.add("partnerFilter", partnerFilter.toParams());
        if (usageFilter != null) kparams.add("usageFilter", usageFilter.toParams());
        if (pager != null) kparams.add("pager", pager.toParams());
        this.kalturaClient.queueServiceCall("systempartner_systempartner", "getUsage", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaSystemPartnerUsageListResponse)KalturaObjectFactory.create(resultXmlElement);
    }

    public KalturaPartnerListResponse list() throws KalturaApiException {
        return this.list(null);
    }

    public KalturaPartnerListResponse list(KalturaPartnerFilter filter) throws KalturaApiException {
        return this.list(filter, null);
    }

    public KalturaPartnerListResponse list(KalturaPartnerFilter filter, KalturaFilterPager pager) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        if (filter != null) kparams.add("filter", filter.toParams());
        if (pager != null) kparams.add("pager", pager.toParams());
        this.kalturaClient.queueServiceCall("systempartner_systempartner", "list", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaPartnerListResponse)KalturaObjectFactory.create(resultXmlElement);
    }

    public void updateStatus(int partnerId, KalturaPartnerStatus status) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.addIntIfNotNull("partnerId", partnerId);
        kparams.addIntIfNotNull("status", status.getHashCode());
        this.kalturaClient.queueServiceCall("systempartner_systempartner", "updateStatus", kparams);
        if (this.kalturaClient.isMultiRequest())
            return;
        Element resultXmlElement = this.kalturaClient.doQueue();
    }

    public String getAdminSession(int partnerId) throws KalturaApiException {
        return this.getAdminSession(partnerId, null);
    }

    public String getAdminSession(int partnerId, String userId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.addIntIfNotNull("partnerId", partnerId);
        kparams.addStringIfNotNull("userId", userId);
        this.kalturaClient.queueServiceCall("systempartner_systempartner", "getAdminSession", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = XmlUtils.getTextValue(resultXmlElement, "result");
        return resultText;
    }

    public void updateConfiguration(int partnerId, KalturaSystemPartnerConfiguration configuration) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.addIntIfNotNull("partnerId", partnerId);
        if (configuration != null) kparams.add("configuration", configuration.toParams());
        this.kalturaClient.queueServiceCall("systempartner_systempartner", "updateConfiguration", kparams);
        if (this.kalturaClient.isMultiRequest())
            return;
        Element resultXmlElement = this.kalturaClient.doQueue();
    }

    public KalturaSystemPartnerConfiguration getConfiguration(int partnerId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.addIntIfNotNull("partnerId", partnerId);
        this.kalturaClient.queueServiceCall("systempartner_systempartner", "getConfiguration", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaSystemPartnerConfiguration)KalturaObjectFactory.create(resultXmlElement);
    }

    public List<KalturaSystemPartnerPackage> getPackages() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("systempartner_systempartner", "getPackages", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        List<KalturaSystemPartnerPackage> list = new ArrayList<KalturaSystemPartnerPackage>();
        for(int i = 0; i < resultXmlElement.getChildNodes().getLength(); i++) {
            Element node = (Element)resultXmlElement.getChildNodes().item(i);
            list.add((KalturaSystemPartnerPackage)KalturaObjectFactory.create(node));
        }
        return list;
    }
}
