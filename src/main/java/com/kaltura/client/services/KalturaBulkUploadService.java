package com.kaltura.client.services;

import java.io.File;

import org.w3c.dom.Element;

import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaClient;
import com.kaltura.client.KalturaFile;
import com.kaltura.client.KalturaFiles;
import com.kaltura.client.KalturaObjectFactory;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaServiceBase;
import com.kaltura.client.types.KalturaBulkUpload;
import com.kaltura.client.types.KalturaBulkUploadListResponse;
import com.kaltura.client.types.KalturaFilterPager;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class KalturaBulkUploadService extends KalturaServiceBase {
    public KalturaBulkUploadService(KalturaClient client) {
        this.kalturaClient = client;
    }

    public KalturaBulkUpload add(int conversionProfileId, File csvFileData) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.addIntIfNotNull("conversionProfileId", conversionProfileId);
        KalturaFiles kfiles = new KalturaFiles();
        kfiles.put("csvFileData", new KalturaFile(csvFileData));
        this.kalturaClient.queueServiceCall("bulkupload", "add", kparams, kfiles);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaBulkUpload)KalturaObjectFactory.create(resultXmlElement);
    }

    public KalturaBulkUpload get(int id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.addIntIfNotNull("id", id);
        this.kalturaClient.queueServiceCall("bulkupload", "get", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaBulkUpload)KalturaObjectFactory.create(resultXmlElement);
    }

    public KalturaBulkUploadListResponse list() throws KalturaApiException {
        return this.list(null);
    }

    public KalturaBulkUploadListResponse list(KalturaFilterPager pager) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        if (pager != null) kparams.add("pager", pager.toParams());
        this.kalturaClient.queueServiceCall("bulkupload", "list", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return (KalturaBulkUploadListResponse)KalturaObjectFactory.create(resultXmlElement);
    }
}
