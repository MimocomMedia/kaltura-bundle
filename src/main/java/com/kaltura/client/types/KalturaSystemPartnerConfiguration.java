package com.kaltura.client.types;

import java.util.IllegalFormatException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.kaltura.client.KalturaObjectBase;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaObjectFactory;
import com.kaltura.client.enums.KalturaStorageServePriority;


/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class KalturaSystemPartnerConfiguration extends KalturaObjectBase {
    public String partnerName;
    public String description;
    public String adminName;
    public String adminEmail;
    public String host;
    public String cdnHost;
    public int maxBulkSize = Integer.MIN_VALUE;
    public int partnerPackage = Integer.MIN_VALUE;
    public int monitorUsage = Integer.MIN_VALUE;
    public boolean liveStreamEnabled;
    public boolean moderateContent;
    public String rtmpUrl;
    public boolean storageDeleteFromKaltura;
    public KalturaStorageServePriority storageServePriority;
    public int kmcVersion = Integer.MIN_VALUE;
    public boolean enableAnalyticsTab;
    public boolean enableSilverLight;
    public boolean enableVast;
    public boolean enable508Players;
    public boolean enableMetadata;
    public boolean enableContentDistribution;
    public boolean enableAuditTrail;
    public boolean enableAnnotation;
    public boolean enableMobileFlavors;
    public boolean enablePs2PermissionValidation;
    public int defThumbOffset = Integer.MIN_VALUE;
    public int adminLoginUsersQuota = Integer.MIN_VALUE;
    public int userSessionRoleId = Integer.MIN_VALUE;
    public int adminSessionRoleId = Integer.MIN_VALUE;
    public String alwaysAllowedPermissionNames;

    public KalturaSystemPartnerConfiguration() {
    }

    public KalturaSystemPartnerConfiguration(Element node) throws KalturaApiException {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String txt = aNode.getTextContent();
            String nodeName = aNode.getNodeName();
            if (false) {
                // noop
            } else if (nodeName.equals("partnerName")) {
                this.partnerName = txt;
                continue;
            } else if (nodeName.equals("description")) {
                this.description = txt;
                continue;
            } else if (nodeName.equals("adminName")) {
                this.adminName = txt;
                continue;
            } else if (nodeName.equals("adminEmail")) {
                this.adminEmail = txt;
                continue;
            } else if (nodeName.equals("host")) {
                this.host = txt;
                continue;
            } else if (nodeName.equals("cdnHost")) {
                this.cdnHost = txt;
                continue;
            } else if (nodeName.equals("maxBulkSize")) {
                try {
                    if (!txt.equals("")) this.maxBulkSize = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("partnerPackage")) {
                try {
                    if (!txt.equals("")) this.partnerPackage = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("monitorUsage")) {
                try {
                    if (!txt.equals("")) this.monitorUsage = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("liveStreamEnabled")) {
                if (!txt.equals("")) this.liveStreamEnabled = ((txt.equals("0")) ? false : true);
                continue;
            } else if (nodeName.equals("moderateContent")) {
                if (!txt.equals("")) this.moderateContent = ((txt.equals("0")) ? false : true);
                continue;
            } else if (nodeName.equals("rtmpUrl")) {
                this.rtmpUrl = txt;
                continue;
            } else if (nodeName.equals("storageDeleteFromKaltura")) {
                if (!txt.equals("")) this.storageDeleteFromKaltura = ((txt.equals("0")) ? false : true);
                continue;
            } else if (nodeName.equals("storageServePriority")) {
                try {
                    if (!txt.equals("")) this.storageServePriority = KalturaStorageServePriority.get(Integer.parseInt(txt));
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("kmcVersion")) {
                try {
                    if (!txt.equals("")) this.kmcVersion = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("enableAnalyticsTab")) {
                if (!txt.equals("")) this.enableAnalyticsTab = ((txt.equals("0")) ? false : true);
                continue;
            } else if (nodeName.equals("enableSilverLight")) {
                if (!txt.equals("")) this.enableSilverLight = ((txt.equals("0")) ? false : true);
                continue;
            } else if (nodeName.equals("enableVast")) {
                if (!txt.equals("")) this.enableVast = ((txt.equals("0")) ? false : true);
                continue;
            } else if (nodeName.equals("enable508Players")) {
                if (!txt.equals("")) this.enable508Players = ((txt.equals("0")) ? false : true);
                continue;
            } else if (nodeName.equals("enableMetadata")) {
                if (!txt.equals("")) this.enableMetadata = ((txt.equals("0")) ? false : true);
                continue;
            } else if (nodeName.equals("enableContentDistribution")) {
                if (!txt.equals("")) this.enableContentDistribution = ((txt.equals("0")) ? false : true);
                continue;
            } else if (nodeName.equals("enableAuditTrail")) {
                if (!txt.equals("")) this.enableAuditTrail = ((txt.equals("0")) ? false : true);
                continue;
            } else if (nodeName.equals("enableAnnotation")) {
                if (!txt.equals("")) this.enableAnnotation = ((txt.equals("0")) ? false : true);
                continue;
            } else if (nodeName.equals("enableMobileFlavors")) {
                if (!txt.equals("")) this.enableMobileFlavors = ((txt.equals("0")) ? false : true);
                continue;
            } else if (nodeName.equals("enablePs2PermissionValidation")) {
                if (!txt.equals("")) this.enablePs2PermissionValidation = ((txt.equals("0")) ? false : true);
                continue;
            } else if (nodeName.equals("defThumbOffset")) {
                try {
                    if (!txt.equals("")) this.defThumbOffset = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("adminLoginUsersQuota")) {
                try {
                    if (!txt.equals("")) this.adminLoginUsersQuota = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("userSessionRoleId")) {
                try {
                    if (!txt.equals("")) this.userSessionRoleId = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("adminSessionRoleId")) {
                try {
                    if (!txt.equals("")) this.adminSessionRoleId = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("alwaysAllowedPermissionNames")) {
                this.alwaysAllowedPermissionNames = txt;
                continue;
            } 

        }
    }

    public KalturaParams toParams() {
        KalturaParams kparams = super.toParams();
        kparams.setString("objectType", "KalturaSystemPartnerConfiguration");
        kparams.addStringIfNotNull("partnerName", this.partnerName);
        kparams.addStringIfNotNull("description", this.description);
        kparams.addStringIfNotNull("adminName", this.adminName);
        kparams.addStringIfNotNull("adminEmail", this.adminEmail);
        kparams.addStringIfNotNull("host", this.host);
        kparams.addStringIfNotNull("cdnHost", this.cdnHost);
        kparams.addIntIfNotNull("maxBulkSize", this.maxBulkSize);
        kparams.addIntIfNotNull("partnerPackage", this.partnerPackage);
        kparams.addIntIfNotNull("monitorUsage", this.monitorUsage);
        kparams.addBoolIfNotNull("liveStreamEnabled", this.liveStreamEnabled);
        kparams.addBoolIfNotNull("moderateContent", this.moderateContent);
        kparams.addStringIfNotNull("rtmpUrl", this.rtmpUrl);
        kparams.addBoolIfNotNull("storageDeleteFromKaltura", this.storageDeleteFromKaltura);
        if (storageServePriority != null) kparams.addIntIfNotNull("storageServePriority", this.storageServePriority.getHashCode());
        kparams.addIntIfNotNull("kmcVersion", this.kmcVersion);
        kparams.addBoolIfNotNull("enableAnalyticsTab", this.enableAnalyticsTab);
        kparams.addBoolIfNotNull("enableSilverLight", this.enableSilverLight);
        kparams.addBoolIfNotNull("enableVast", this.enableVast);
        kparams.addBoolIfNotNull("enable508Players", this.enable508Players);
        kparams.addBoolIfNotNull("enableMetadata", this.enableMetadata);
        kparams.addBoolIfNotNull("enableContentDistribution", this.enableContentDistribution);
        kparams.addBoolIfNotNull("enableAuditTrail", this.enableAuditTrail);
        kparams.addBoolIfNotNull("enableAnnotation", this.enableAnnotation);
        kparams.addBoolIfNotNull("enableMobileFlavors", this.enableMobileFlavors);
        kparams.addBoolIfNotNull("enablePs2PermissionValidation", this.enablePs2PermissionValidation);
        kparams.addIntIfNotNull("defThumbOffset", this.defThumbOffset);
        kparams.addIntIfNotNull("adminLoginUsersQuota", this.adminLoginUsersQuota);
        kparams.addIntIfNotNull("userSessionRoleId", this.userSessionRoleId);
        kparams.addIntIfNotNull("adminSessionRoleId", this.adminSessionRoleId);
        kparams.addStringIfNotNull("alwaysAllowedPermissionNames", this.alwaysAllowedPermissionNames);
        return kparams;
    }
}

