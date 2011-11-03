package com.kaltura.client;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.types.KalturaBaseRestriction;
import com.kaltura.client.types.KalturaAccessControl;
import com.kaltura.client.types.KalturaFilter;
import com.kaltura.client.types.KalturaAccessControlFilter;
import com.kaltura.client.types.KalturaFilterPager;
import com.kaltura.client.types.KalturaAccessControlListResponse;
import com.kaltura.client.types.KalturaUser;
import com.kaltura.client.types.KalturaAdminUser;
import com.kaltura.client.types.KalturaDynamicEnum;
import com.kaltura.client.types.KalturaBaseEntry;
import com.kaltura.client.types.KalturaBaseEntryFilter;
import com.kaltura.client.types.KalturaBaseEntryListResponse;
import com.kaltura.client.types.KalturaModerationFlag;
import com.kaltura.client.types.KalturaModerationFlagListResponse;
import com.kaltura.client.types.KalturaEntryContextDataParams;
import com.kaltura.client.types.KalturaEntryContextDataResult;
import com.kaltura.client.types.KalturaBulkUploadPluginData;
import com.kaltura.client.types.KalturaBulkUploadResult;
import com.kaltura.client.types.KalturaBulkUpload;
import com.kaltura.client.types.KalturaBulkUploadListResponse;
import com.kaltura.client.types.KalturaCategory;
import com.kaltura.client.types.KalturaCategoryFilter;
import com.kaltura.client.types.KalturaCategoryListResponse;
import com.kaltura.client.types.KalturaCropDimensions;
import com.kaltura.client.types.KalturaConversionProfile;
import com.kaltura.client.types.KalturaConversionProfileFilter;
import com.kaltura.client.types.KalturaConversionProfileListResponse;
import com.kaltura.client.types.KalturaDataEntry;
import com.kaltura.client.types.KalturaDataEntryFilter;
import com.kaltura.client.types.KalturaDataListResponse;
import com.kaltura.client.types.KalturaDocumentEntry;
import com.kaltura.client.types.KalturaConversionAttribute;
import com.kaltura.client.types.KalturaDocumentEntryFilter;
import com.kaltura.client.types.KalturaDocumentListResponse;
import com.kaltura.client.types.KalturaEmailIngestionProfile;
import com.kaltura.client.types.KalturaPlayableEntry;
import com.kaltura.client.types.KalturaMediaEntry;
import com.kaltura.client.types.KalturaAsset;
import com.kaltura.client.types.KalturaFlavorAsset;
import com.kaltura.client.types.KalturaAssetFilter;
import com.kaltura.client.types.KalturaFlavorAssetListResponse;
import com.kaltura.client.types.KalturaString;
import com.kaltura.client.types.KalturaAssetParams;
import com.kaltura.client.types.KalturaFlavorParams;
import com.kaltura.client.types.KalturaFlavorAssetWithParams;
import com.kaltura.client.types.KalturaAssetParamsFilter;
import com.kaltura.client.types.KalturaFlavorParamsFilter;
import com.kaltura.client.types.KalturaFlavorParamsListResponse;
import com.kaltura.client.types.KalturaLiveStreamBitrate;
import com.kaltura.client.types.KalturaLiveStreamEntry;
import com.kaltura.client.types.KalturaLiveStreamAdminEntry;
import com.kaltura.client.types.KalturaPlayableEntryFilter;
import com.kaltura.client.types.KalturaMediaEntryFilter;
import com.kaltura.client.types.KalturaLiveStreamEntryFilter;
import com.kaltura.client.types.KalturaLiveStreamListResponse;
import com.kaltura.client.types.KalturaSearch;
import com.kaltura.client.types.KalturaSearchResult;
import com.kaltura.client.types.KalturaMediaListResponse;
import com.kaltura.client.types.KalturaMixEntry;
import com.kaltura.client.types.KalturaMixEntryFilter;
import com.kaltura.client.types.KalturaMixListResponse;
import com.kaltura.client.types.KalturaClientNotification;
import com.kaltura.client.types.KalturaKeyValue;
import com.kaltura.client.types.KalturaPartner;
import com.kaltura.client.types.KalturaPartnerUsage;
import com.kaltura.client.types.KalturaPermissionItemFilter;
import com.kaltura.client.types.KalturaPermissionItemListResponse;
import com.kaltura.client.types.KalturaPermission;
import com.kaltura.client.types.KalturaPermissionFilter;
import com.kaltura.client.types.KalturaPermissionListResponse;
import com.kaltura.client.types.KalturaMediaEntryFilterForPlaylist;
import com.kaltura.client.types.KalturaPlaylist;
import com.kaltura.client.types.KalturaPlaylistFilter;
import com.kaltura.client.types.KalturaPlaylistListResponse;
import com.kaltura.client.types.KalturaReportInputFilter;
import com.kaltura.client.types.KalturaReportGraph;
import com.kaltura.client.types.KalturaReportTotal;
import com.kaltura.client.types.KalturaReportTable;
import com.kaltura.client.types.KalturaSearchResultResponse;
import com.kaltura.client.types.KalturaSearchAuthData;
import com.kaltura.client.types.KalturaStartWidgetSessionResponse;
import com.kaltura.client.types.KalturaStatsEvent;
import com.kaltura.client.types.KalturaStatsKmcEvent;
import com.kaltura.client.types.KalturaCEError;
import com.kaltura.client.types.KalturaBaseSyndicationFeedFilter;
import com.kaltura.client.types.KalturaBaseSyndicationFeedListResponse;
import com.kaltura.client.types.KalturaSyndicationFeedEntryCount;
import com.kaltura.client.types.KalturaThumbAsset;
import com.kaltura.client.types.KalturaThumbParams;
import com.kaltura.client.types.KalturaThumbAssetListResponse;
import com.kaltura.client.types.KalturaThumbParamsFilter;
import com.kaltura.client.types.KalturaThumbParamsListResponse;
import com.kaltura.client.types.KalturaUiConf;
import com.kaltura.client.types.KalturaUiConfFilter;
import com.kaltura.client.types.KalturaUiConfListResponse;
import com.kaltura.client.types.KalturaUiConfTypeInfo;
import com.kaltura.client.types.KalturaUploadResponse;
import com.kaltura.client.types.KalturaUploadToken;
import com.kaltura.client.types.KalturaUploadTokenFilter;
import com.kaltura.client.types.KalturaUploadTokenListResponse;
import com.kaltura.client.types.KalturaUserRole;
import com.kaltura.client.types.KalturaUserRoleFilter;
import com.kaltura.client.types.KalturaUserRoleListResponse;
import com.kaltura.client.types.KalturaUserFilter;
import com.kaltura.client.types.KalturaUserListResponse;
import com.kaltura.client.types.KalturaWidget;
import com.kaltura.client.types.KalturaWidgetFilter;
import com.kaltura.client.types.KalturaWidgetListResponse;
import com.kaltura.client.types.KalturaMetadataFilter;
import com.kaltura.client.types.KalturaMetadata;
import com.kaltura.client.types.KalturaMetadataListResponse;
import com.kaltura.client.types.KalturaMetadataProfileFilter;
import com.kaltura.client.types.KalturaMetadataProfile;
import com.kaltura.client.types.KalturaMetadataProfileListResponse;
import com.kaltura.client.types.KalturaMetadataProfileField;
import com.kaltura.client.types.KalturaMetadataProfileFieldListResponse;
import com.kaltura.client.types.KalturaPartnerFilter;
import com.kaltura.client.types.KalturaStorageProfile;
import com.kaltura.client.types.KalturaStorageProfileListResponse;
import com.kaltura.client.types.KalturaSystemPartnerUsageFilter;
import com.kaltura.client.types.KalturaSystemPartnerUsageItem;
import com.kaltura.client.types.KalturaSystemPartnerUsageListResponse;
import com.kaltura.client.types.KalturaPartnerListResponse;
import com.kaltura.client.types.KalturaSystemPartnerConfiguration;
import com.kaltura.client.types.KalturaSystemPartnerPackage;
import com.kaltura.client.types.KalturaFlavorParamsOutputFilter;
import com.kaltura.client.types.KalturaFlavorParamsOutput;
import com.kaltura.client.types.KalturaFlavorParamsOutputListResponse;
import com.kaltura.client.types.KalturaThumbParamsOutputFilter;
import com.kaltura.client.types.KalturaThumbParamsOutput;
import com.kaltura.client.types.KalturaThumbParamsOutputListResponse;
import com.kaltura.client.types.KalturaMediaInfoFilter;
import com.kaltura.client.types.KalturaMediaInfo;
import com.kaltura.client.types.KalturaMediaInfoListResponse;
import com.kaltura.client.types.KalturaTrackEntry;
import com.kaltura.client.types.KalturaTrackEntryListResponse;
import com.kaltura.client.types.KalturaUiConfAdmin;
import com.kaltura.client.types.KalturaUiConfAdminListResponse;
import com.kaltura.client.types.KalturaInternalToolsSession;
import com.kaltura.client.types.KalturaAuditTrailFilter;
import com.kaltura.client.types.KalturaAuditTrail;
import com.kaltura.client.types.KalturaAuditTrailListResponse;
import com.kaltura.client.types.KalturaVirusScanProfileFilter;
import com.kaltura.client.types.KalturaVirusScanProfile;
import com.kaltura.client.types.KalturaVirusScanProfileListResponse;
import com.kaltura.client.types.KalturaDistributionThumbDimensions;
import com.kaltura.client.types.KalturaDistributionProfileFilter;
import com.kaltura.client.types.KalturaDistributionProfileListResponse;
import com.kaltura.client.types.KalturaEntryDistribution;
import com.kaltura.client.types.KalturaEntryDistributionFilter;
import com.kaltura.client.types.KalturaEntryDistributionListResponse;
import com.kaltura.client.types.KalturaDistributionProviderFilter;
import com.kaltura.client.types.KalturaDistributionProviderListResponse;
import com.kaltura.client.types.KalturaGenericDistributionProvider;
import com.kaltura.client.types.KalturaGenericDistributionProviderFilter;
import com.kaltura.client.types.KalturaGenericDistributionProviderListResponse;
import com.kaltura.client.types.KalturaGenericDistributionProviderAction;
import com.kaltura.client.types.KalturaGenericDistributionProviderActionFilter;
import com.kaltura.client.types.KalturaGenericDistributionProviderActionListResponse;
import com.kaltura.client.types.KalturaAnnotationFilter;
import com.kaltura.client.types.KalturaAnnotation;
import com.kaltura.client.types.KalturaAnnotationListResponse;
import com.kaltura.client.types.KalturaShortLinkFilter;
import com.kaltura.client.types.KalturaShortLink;
import com.kaltura.client.types.KalturaShortLinkListResponse;
import com.kaltura.client.types.KalturaCountryRestriction;
import com.kaltura.client.types.KalturaDirectoryRestriction;
import com.kaltura.client.types.KalturaIpAddressRestriction;
import com.kaltura.client.types.KalturaSessionRestriction;
import com.kaltura.client.types.KalturaPreviewRestriction;
import com.kaltura.client.types.KalturaSiteRestriction;
import com.kaltura.client.types.KalturaSearchCondition;
import com.kaltura.client.types.KalturaSearchComparableCondition;
import com.kaltura.client.types.KalturaSearchOperator;
import com.kaltura.client.types.KalturaBaseJobFilter;
import com.kaltura.client.types.KalturaBatchJobFilter;
import com.kaltura.client.types.KalturaControlPanelCommandFilter;
import com.kaltura.client.types.KalturaMailJobFilter;
import com.kaltura.client.types.KalturaNotificationFilter;
import com.kaltura.client.types.KalturaBatchJobFilterExt;
import com.kaltura.client.types.KalturaAssetParamsOutputFilter;
import com.kaltura.client.types.KalturaFlavorAssetFilter;
import com.kaltura.client.types.KalturaMediaFlavorParamsFilter;
import com.kaltura.client.types.KalturaMediaFlavorParamsOutputFilter;
import com.kaltura.client.types.KalturaThumbAssetFilter;
import com.kaltura.client.types.KalturaLiveStreamAdminEntryFilter;
import com.kaltura.client.types.KalturaAdminUserFilter;
import com.kaltura.client.types.KalturaGoogleVideoSyndicationFeedFilter;
import com.kaltura.client.types.KalturaITunesSyndicationFeedFilter;
import com.kaltura.client.types.KalturaTubeMogulSyndicationFeedFilter;
import com.kaltura.client.types.KalturaYahooSyndicationFeedFilter;
import com.kaltura.client.types.KalturaApiActionPermissionItemFilter;
import com.kaltura.client.types.KalturaApiParameterPermissionItemFilter;
import com.kaltura.client.types.KalturaGenericSyndicationFeedFilter;
import com.kaltura.client.types.KalturaGenericXsltSyndicationFeedFilter;
import com.kaltura.client.types.KalturaAssetParamsOutput;
import com.kaltura.client.types.KalturaMediaFlavorParamsOutput;
import com.kaltura.client.types.KalturaMediaFlavorParams;
import com.kaltura.client.types.KalturaApiActionPermissionItem;
import com.kaltura.client.types.KalturaApiParameterPermissionItem;
import com.kaltura.client.types.KalturaGenericSyndicationFeed;
import com.kaltura.client.types.KalturaGenericXsltSyndicationFeed;
import com.kaltura.client.types.KalturaGoogleVideoSyndicationFeed;
import com.kaltura.client.types.KalturaITunesSyndicationFeed;
import com.kaltura.client.types.KalturaTubeMogulSyndicationFeed;
import com.kaltura.client.types.KalturaYahooSyndicationFeed;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class KalturaObjectFactory {
    public static Object create(Element xmlElement) throws KalturaApiException {
        NodeList objectTypeNodes = xmlElement.getElementsByTagName("objectType");
        Node objectTypeNode = objectTypeNodes.item(0);
        String objectType = objectTypeNode.getTextContent();
        if (false) {
        	// noop
        }
        else if (objectType.equals("KalturaBaseRestriction")) {
            return new KalturaBaseRestriction(xmlElement);
        }
        else if (objectType.equals("KalturaAccessControl")) {
            return new KalturaAccessControl(xmlElement);
        }
        else if (objectType.equals("KalturaFilter")) {
            return new KalturaFilter(xmlElement);
        }
        else if (objectType.equals("KalturaAccessControlFilter")) {
            return new KalturaAccessControlFilter(xmlElement);
        }
        else if (objectType.equals("KalturaFilterPager")) {
            return new KalturaFilterPager(xmlElement);
        }
        else if (objectType.equals("KalturaAccessControlListResponse")) {
            return new KalturaAccessControlListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaUser")) {
            return new KalturaUser(xmlElement);
        }
        else if (objectType.equals("KalturaAdminUser")) {
            return new KalturaAdminUser(xmlElement);
        }
        else if (objectType.equals("KalturaDynamicEnum")) {
            return new KalturaDynamicEnum(xmlElement);
        }
        else if (objectType.equals("KalturaBaseEntry")) {
            return new KalturaBaseEntry(xmlElement);
        }
        else if (objectType.equals("KalturaBaseEntryFilter")) {
            return new KalturaBaseEntryFilter(xmlElement);
        }
        else if (objectType.equals("KalturaBaseEntryListResponse")) {
            return new KalturaBaseEntryListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaModerationFlag")) {
            return new KalturaModerationFlag(xmlElement);
        }
        else if (objectType.equals("KalturaModerationFlagListResponse")) {
            return new KalturaModerationFlagListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaEntryContextDataParams")) {
            return new KalturaEntryContextDataParams(xmlElement);
        }
        else if (objectType.equals("KalturaEntryContextDataResult")) {
            return new KalturaEntryContextDataResult(xmlElement);
        }
        else if (objectType.equals("KalturaBulkUploadPluginData")) {
            return new KalturaBulkUploadPluginData(xmlElement);
        }
        else if (objectType.equals("KalturaBulkUploadResult")) {
            return new KalturaBulkUploadResult(xmlElement);
        }
        else if (objectType.equals("KalturaBulkUpload")) {
            return new KalturaBulkUpload(xmlElement);
        }
        else if (objectType.equals("KalturaBulkUploadListResponse")) {
            return new KalturaBulkUploadListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaCategory")) {
            return new KalturaCategory(xmlElement);
        }
        else if (objectType.equals("KalturaCategoryFilter")) {
            return new KalturaCategoryFilter(xmlElement);
        }
        else if (objectType.equals("KalturaCategoryListResponse")) {
            return new KalturaCategoryListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaCropDimensions")) {
            return new KalturaCropDimensions(xmlElement);
        }
        else if (objectType.equals("KalturaConversionProfile")) {
            return new KalturaConversionProfile(xmlElement);
        }
        else if (objectType.equals("KalturaConversionProfileFilter")) {
            return new KalturaConversionProfileFilter(xmlElement);
        }
        else if (objectType.equals("KalturaConversionProfileListResponse")) {
            return new KalturaConversionProfileListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaDataEntry")) {
            return new KalturaDataEntry(xmlElement);
        }
        else if (objectType.equals("KalturaDataEntryFilter")) {
            return new KalturaDataEntryFilter(xmlElement);
        }
        else if (objectType.equals("KalturaDataListResponse")) {
            return new KalturaDataListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaDocumentEntry")) {
            return new KalturaDocumentEntry(xmlElement);
        }
        else if (objectType.equals("KalturaConversionAttribute")) {
            return new KalturaConversionAttribute(xmlElement);
        }
        else if (objectType.equals("KalturaDocumentEntryFilter")) {
            return new KalturaDocumentEntryFilter(xmlElement);
        }
        else if (objectType.equals("KalturaDocumentListResponse")) {
            return new KalturaDocumentListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaEmailIngestionProfile")) {
            return new KalturaEmailIngestionProfile(xmlElement);
        }
        else if (objectType.equals("KalturaPlayableEntry")) {
            return new KalturaPlayableEntry(xmlElement);
        }
        else if (objectType.equals("KalturaMediaEntry")) {
            return new KalturaMediaEntry(xmlElement);
        }
        else if (objectType.equals("KalturaAsset")) {
            return new KalturaAsset(xmlElement);
        }
        else if (objectType.equals("KalturaFlavorAsset")) {
            return new KalturaFlavorAsset(xmlElement);
        }
        else if (objectType.equals("KalturaAssetFilter")) {
            return new KalturaAssetFilter(xmlElement);
        }
        else if (objectType.equals("KalturaFlavorAssetListResponse")) {
            return new KalturaFlavorAssetListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaString")) {
            return new KalturaString(xmlElement);
        }
        else if (objectType.equals("KalturaAssetParams")) {
            return new KalturaAssetParams(xmlElement);
        }
        else if (objectType.equals("KalturaFlavorParams")) {
            return new KalturaFlavorParams(xmlElement);
        }
        else if (objectType.equals("KalturaFlavorAssetWithParams")) {
            return new KalturaFlavorAssetWithParams(xmlElement);
        }
        else if (objectType.equals("KalturaAssetParamsFilter")) {
            return new KalturaAssetParamsFilter(xmlElement);
        }
        else if (objectType.equals("KalturaFlavorParamsFilter")) {
            return new KalturaFlavorParamsFilter(xmlElement);
        }
        else if (objectType.equals("KalturaFlavorParamsListResponse")) {
            return new KalturaFlavorParamsListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaLiveStreamBitrate")) {
            return new KalturaLiveStreamBitrate(xmlElement);
        }
        else if (objectType.equals("KalturaLiveStreamEntry")) {
            return new KalturaLiveStreamEntry(xmlElement);
        }
        else if (objectType.equals("KalturaLiveStreamAdminEntry")) {
            return new KalturaLiveStreamAdminEntry(xmlElement);
        }
        else if (objectType.equals("KalturaPlayableEntryFilter")) {
            return new KalturaPlayableEntryFilter(xmlElement);
        }
        else if (objectType.equals("KalturaMediaEntryFilter")) {
            return new KalturaMediaEntryFilter(xmlElement);
        }
        else if (objectType.equals("KalturaLiveStreamEntryFilter")) {
            return new KalturaLiveStreamEntryFilter(xmlElement);
        }
        else if (objectType.equals("KalturaLiveStreamListResponse")) {
            return new KalturaLiveStreamListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaSearch")) {
            return new KalturaSearch(xmlElement);
        }
        else if (objectType.equals("KalturaSearchResult")) {
            return new KalturaSearchResult(xmlElement);
        }
        else if (objectType.equals("KalturaMediaListResponse")) {
            return new KalturaMediaListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaMixEntry")) {
            return new KalturaMixEntry(xmlElement);
        }
        else if (objectType.equals("KalturaMixEntryFilter")) {
            return new KalturaMixEntryFilter(xmlElement);
        }
        else if (objectType.equals("KalturaMixListResponse")) {
            return new KalturaMixListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaClientNotification")) {
            return new KalturaClientNotification(xmlElement);
        }
        else if (objectType.equals("KalturaKeyValue")) {
            return new KalturaKeyValue(xmlElement);
        }
        else if (objectType.equals("KalturaPartner")) {
            return new KalturaPartner(xmlElement);
        }
        else if (objectType.equals("KalturaPartnerUsage")) {
            return new KalturaPartnerUsage(xmlElement);
        }
        else if (objectType.equals("KalturaPermissionItemFilter")) {
            return new KalturaPermissionItemFilter(xmlElement);
        }
        else if (objectType.equals("KalturaPermissionItemListResponse")) {
            return new KalturaPermissionItemListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaPermission")) {
            return new KalturaPermission(xmlElement);
        }
        else if (objectType.equals("KalturaPermissionFilter")) {
            return new KalturaPermissionFilter(xmlElement);
        }
        else if (objectType.equals("KalturaPermissionListResponse")) {
            return new KalturaPermissionListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaMediaEntryFilterForPlaylist")) {
            return new KalturaMediaEntryFilterForPlaylist(xmlElement);
        }
        else if (objectType.equals("KalturaPlaylist")) {
            return new KalturaPlaylist(xmlElement);
        }
        else if (objectType.equals("KalturaPlaylistFilter")) {
            return new KalturaPlaylistFilter(xmlElement);
        }
        else if (objectType.equals("KalturaPlaylistListResponse")) {
            return new KalturaPlaylistListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaReportInputFilter")) {
            return new KalturaReportInputFilter(xmlElement);
        }
        else if (objectType.equals("KalturaReportGraph")) {
            return new KalturaReportGraph(xmlElement);
        }
        else if (objectType.equals("KalturaReportTotal")) {
            return new KalturaReportTotal(xmlElement);
        }
        else if (objectType.equals("KalturaReportTable")) {
            return new KalturaReportTable(xmlElement);
        }
        else if (objectType.equals("KalturaSearchResultResponse")) {
            return new KalturaSearchResultResponse(xmlElement);
        }
        else if (objectType.equals("KalturaSearchAuthData")) {
            return new KalturaSearchAuthData(xmlElement);
        }
        else if (objectType.equals("KalturaStartWidgetSessionResponse")) {
            return new KalturaStartWidgetSessionResponse(xmlElement);
        }
        else if (objectType.equals("KalturaStatsEvent")) {
            return new KalturaStatsEvent(xmlElement);
        }
        else if (objectType.equals("KalturaStatsKmcEvent")) {
            return new KalturaStatsKmcEvent(xmlElement);
        }
        else if (objectType.equals("KalturaCEError")) {
            return new KalturaCEError(xmlElement);
        }
        else if (objectType.equals("KalturaBaseSyndicationFeedFilter")) {
            return new KalturaBaseSyndicationFeedFilter(xmlElement);
        }
        else if (objectType.equals("KalturaBaseSyndicationFeedListResponse")) {
            return new KalturaBaseSyndicationFeedListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaSyndicationFeedEntryCount")) {
            return new KalturaSyndicationFeedEntryCount(xmlElement);
        }
        else if (objectType.equals("KalturaThumbAsset")) {
            return new KalturaThumbAsset(xmlElement);
        }
        else if (objectType.equals("KalturaThumbParams")) {
            return new KalturaThumbParams(xmlElement);
        }
        else if (objectType.equals("KalturaThumbAssetListResponse")) {
            return new KalturaThumbAssetListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaThumbParamsFilter")) {
            return new KalturaThumbParamsFilter(xmlElement);
        }
        else if (objectType.equals("KalturaThumbParamsListResponse")) {
            return new KalturaThumbParamsListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaUiConf")) {
            return new KalturaUiConf(xmlElement);
        }
        else if (objectType.equals("KalturaUiConfFilter")) {
            return new KalturaUiConfFilter(xmlElement);
        }
        else if (objectType.equals("KalturaUiConfListResponse")) {
            return new KalturaUiConfListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaUiConfTypeInfo")) {
            return new KalturaUiConfTypeInfo(xmlElement);
        }
        else if (objectType.equals("KalturaUploadResponse")) {
            return new KalturaUploadResponse(xmlElement);
        }
        else if (objectType.equals("KalturaUploadToken")) {
            return new KalturaUploadToken(xmlElement);
        }
        else if (objectType.equals("KalturaUploadTokenFilter")) {
            return new KalturaUploadTokenFilter(xmlElement);
        }
        else if (objectType.equals("KalturaUploadTokenListResponse")) {
            return new KalturaUploadTokenListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaUserRole")) {
            return new KalturaUserRole(xmlElement);
        }
        else if (objectType.equals("KalturaUserRoleFilter")) {
            return new KalturaUserRoleFilter(xmlElement);
        }
        else if (objectType.equals("KalturaUserRoleListResponse")) {
            return new KalturaUserRoleListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaUserFilter")) {
            return new KalturaUserFilter(xmlElement);
        }
        else if (objectType.equals("KalturaUserListResponse")) {
            return new KalturaUserListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaWidget")) {
            return new KalturaWidget(xmlElement);
        }
        else if (objectType.equals("KalturaWidgetFilter")) {
            return new KalturaWidgetFilter(xmlElement);
        }
        else if (objectType.equals("KalturaWidgetListResponse")) {
            return new KalturaWidgetListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaMetadataFilter")) {
            return new KalturaMetadataFilter(xmlElement);
        }
        else if (objectType.equals("KalturaMetadata")) {
            return new KalturaMetadata(xmlElement);
        }
        else if (objectType.equals("KalturaMetadataListResponse")) {
            return new KalturaMetadataListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaMetadataProfileFilter")) {
            return new KalturaMetadataProfileFilter(xmlElement);
        }
        else if (objectType.equals("KalturaMetadataProfile")) {
            return new KalturaMetadataProfile(xmlElement);
        }
        else if (objectType.equals("KalturaMetadataProfileListResponse")) {
            return new KalturaMetadataProfileListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaMetadataProfileField")) {
            return new KalturaMetadataProfileField(xmlElement);
        }
        else if (objectType.equals("KalturaMetadataProfileFieldListResponse")) {
            return new KalturaMetadataProfileFieldListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaPartnerFilter")) {
            return new KalturaPartnerFilter(xmlElement);
        }
        else if (objectType.equals("KalturaStorageProfile")) {
            return new KalturaStorageProfile(xmlElement);
        }
        else if (objectType.equals("KalturaStorageProfileListResponse")) {
            return new KalturaStorageProfileListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaSystemPartnerUsageFilter")) {
            return new KalturaSystemPartnerUsageFilter(xmlElement);
        }
        else if (objectType.equals("KalturaSystemPartnerUsageItem")) {
            return new KalturaSystemPartnerUsageItem(xmlElement);
        }
        else if (objectType.equals("KalturaSystemPartnerUsageListResponse")) {
            return new KalturaSystemPartnerUsageListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaPartnerListResponse")) {
            return new KalturaPartnerListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaSystemPartnerConfiguration")) {
            return new KalturaSystemPartnerConfiguration(xmlElement);
        }
        else if (objectType.equals("KalturaSystemPartnerPackage")) {
            return new KalturaSystemPartnerPackage(xmlElement);
        }
        else if (objectType.equals("KalturaFlavorParamsOutputFilter")) {
            return new KalturaFlavorParamsOutputFilter(xmlElement);
        }
        else if (objectType.equals("KalturaFlavorParamsOutput")) {
            return new KalturaFlavorParamsOutput(xmlElement);
        }
        else if (objectType.equals("KalturaFlavorParamsOutputListResponse")) {
            return new KalturaFlavorParamsOutputListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaThumbParamsOutputFilter")) {
            return new KalturaThumbParamsOutputFilter(xmlElement);
        }
        else if (objectType.equals("KalturaThumbParamsOutput")) {
            return new KalturaThumbParamsOutput(xmlElement);
        }
        else if (objectType.equals("KalturaThumbParamsOutputListResponse")) {
            return new KalturaThumbParamsOutputListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaMediaInfoFilter")) {
            return new KalturaMediaInfoFilter(xmlElement);
        }
        else if (objectType.equals("KalturaMediaInfo")) {
            return new KalturaMediaInfo(xmlElement);
        }
        else if (objectType.equals("KalturaMediaInfoListResponse")) {
            return new KalturaMediaInfoListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaTrackEntry")) {
            return new KalturaTrackEntry(xmlElement);
        }
        else if (objectType.equals("KalturaTrackEntryListResponse")) {
            return new KalturaTrackEntryListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaUiConfAdmin")) {
            return new KalturaUiConfAdmin(xmlElement);
        }
        else if (objectType.equals("KalturaUiConfAdminListResponse")) {
            return new KalturaUiConfAdminListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaInternalToolsSession")) {
            return new KalturaInternalToolsSession(xmlElement);
        }
        else if (objectType.equals("KalturaAuditTrailFilter")) {
            return new KalturaAuditTrailFilter(xmlElement);
        }
        else if (objectType.equals("KalturaAuditTrail")) {
            return new KalturaAuditTrail(xmlElement);
        }
        else if (objectType.equals("KalturaAuditTrailListResponse")) {
            return new KalturaAuditTrailListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaVirusScanProfileFilter")) {
            return new KalturaVirusScanProfileFilter(xmlElement);
        }
        else if (objectType.equals("KalturaVirusScanProfile")) {
            return new KalturaVirusScanProfile(xmlElement);
        }
        else if (objectType.equals("KalturaVirusScanProfileListResponse")) {
            return new KalturaVirusScanProfileListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaDistributionThumbDimensions")) {
            return new KalturaDistributionThumbDimensions(xmlElement);
        }
        else if (objectType.equals("KalturaDistributionProfileFilter")) {
            return new KalturaDistributionProfileFilter(xmlElement);
        }
        else if (objectType.equals("KalturaDistributionProfileListResponse")) {
            return new KalturaDistributionProfileListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaEntryDistribution")) {
            return new KalturaEntryDistribution(xmlElement);
        }
        else if (objectType.equals("KalturaEntryDistributionFilter")) {
            return new KalturaEntryDistributionFilter(xmlElement);
        }
        else if (objectType.equals("KalturaEntryDistributionListResponse")) {
            return new KalturaEntryDistributionListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaDistributionProviderFilter")) {
            return new KalturaDistributionProviderFilter(xmlElement);
        }
        else if (objectType.equals("KalturaDistributionProviderListResponse")) {
            return new KalturaDistributionProviderListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaGenericDistributionProvider")) {
            return new KalturaGenericDistributionProvider(xmlElement);
        }
        else if (objectType.equals("KalturaGenericDistributionProviderFilter")) {
            return new KalturaGenericDistributionProviderFilter(xmlElement);
        }
        else if (objectType.equals("KalturaGenericDistributionProviderListResponse")) {
            return new KalturaGenericDistributionProviderListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaGenericDistributionProviderAction")) {
            return new KalturaGenericDistributionProviderAction(xmlElement);
        }
        else if (objectType.equals("KalturaGenericDistributionProviderActionFilter")) {
            return new KalturaGenericDistributionProviderActionFilter(xmlElement);
        }
        else if (objectType.equals("KalturaGenericDistributionProviderActionListResponse")) {
            return new KalturaGenericDistributionProviderActionListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaAnnotationFilter")) {
            return new KalturaAnnotationFilter(xmlElement);
        }
        else if (objectType.equals("KalturaAnnotation")) {
            return new KalturaAnnotation(xmlElement);
        }
        else if (objectType.equals("KalturaAnnotationListResponse")) {
            return new KalturaAnnotationListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaShortLinkFilter")) {
            return new KalturaShortLinkFilter(xmlElement);
        }
        else if (objectType.equals("KalturaShortLink")) {
            return new KalturaShortLink(xmlElement);
        }
        else if (objectType.equals("KalturaShortLinkListResponse")) {
            return new KalturaShortLinkListResponse(xmlElement);
        }
        else if (objectType.equals("KalturaCountryRestriction")) {
            return new KalturaCountryRestriction(xmlElement);
        }
        else if (objectType.equals("KalturaDirectoryRestriction")) {
            return new KalturaDirectoryRestriction(xmlElement);
        }
        else if (objectType.equals("KalturaIpAddressRestriction")) {
            return new KalturaIpAddressRestriction(xmlElement);
        }
        else if (objectType.equals("KalturaSessionRestriction")) {
            return new KalturaSessionRestriction(xmlElement);
        }
        else if (objectType.equals("KalturaPreviewRestriction")) {
            return new KalturaPreviewRestriction(xmlElement);
        }
        else if (objectType.equals("KalturaSiteRestriction")) {
            return new KalturaSiteRestriction(xmlElement);
        }
        else if (objectType.equals("KalturaSearchCondition")) {
            return new KalturaSearchCondition(xmlElement);
        }
        else if (objectType.equals("KalturaSearchComparableCondition")) {
            return new KalturaSearchComparableCondition(xmlElement);
        }
        else if (objectType.equals("KalturaSearchOperator")) {
            return new KalturaSearchOperator(xmlElement);
        }
        else if (objectType.equals("KalturaBaseJobFilter")) {
            return new KalturaBaseJobFilter(xmlElement);
        }
        else if (objectType.equals("KalturaBatchJobFilter")) {
            return new KalturaBatchJobFilter(xmlElement);
        }
        else if (objectType.equals("KalturaControlPanelCommandFilter")) {
            return new KalturaControlPanelCommandFilter(xmlElement);
        }
        else if (objectType.equals("KalturaMailJobFilter")) {
            return new KalturaMailJobFilter(xmlElement);
        }
        else if (objectType.equals("KalturaNotificationFilter")) {
            return new KalturaNotificationFilter(xmlElement);
        }
        else if (objectType.equals("KalturaBatchJobFilterExt")) {
            return new KalturaBatchJobFilterExt(xmlElement);
        }
        else if (objectType.equals("KalturaAssetParamsOutputFilter")) {
            return new KalturaAssetParamsOutputFilter(xmlElement);
        }
        else if (objectType.equals("KalturaFlavorAssetFilter")) {
            return new KalturaFlavorAssetFilter(xmlElement);
        }
        else if (objectType.equals("KalturaMediaFlavorParamsFilter")) {
            return new KalturaMediaFlavorParamsFilter(xmlElement);
        }
        else if (objectType.equals("KalturaMediaFlavorParamsOutputFilter")) {
            return new KalturaMediaFlavorParamsOutputFilter(xmlElement);
        }
        else if (objectType.equals("KalturaThumbAssetFilter")) {
            return new KalturaThumbAssetFilter(xmlElement);
        }
        else if (objectType.equals("KalturaLiveStreamAdminEntryFilter")) {
            return new KalturaLiveStreamAdminEntryFilter(xmlElement);
        }
        else if (objectType.equals("KalturaAdminUserFilter")) {
            return new KalturaAdminUserFilter(xmlElement);
        }
        else if (objectType.equals("KalturaGoogleVideoSyndicationFeedFilter")) {
            return new KalturaGoogleVideoSyndicationFeedFilter(xmlElement);
        }
        else if (objectType.equals("KalturaITunesSyndicationFeedFilter")) {
            return new KalturaITunesSyndicationFeedFilter(xmlElement);
        }
        else if (objectType.equals("KalturaTubeMogulSyndicationFeedFilter")) {
            return new KalturaTubeMogulSyndicationFeedFilter(xmlElement);
        }
        else if (objectType.equals("KalturaYahooSyndicationFeedFilter")) {
            return new KalturaYahooSyndicationFeedFilter(xmlElement);
        }
        else if (objectType.equals("KalturaApiActionPermissionItemFilter")) {
            return new KalturaApiActionPermissionItemFilter(xmlElement);
        }
        else if (objectType.equals("KalturaApiParameterPermissionItemFilter")) {
            return new KalturaApiParameterPermissionItemFilter(xmlElement);
        }
        else if (objectType.equals("KalturaGenericSyndicationFeedFilter")) {
            return new KalturaGenericSyndicationFeedFilter(xmlElement);
        }
        else if (objectType.equals("KalturaGenericXsltSyndicationFeedFilter")) {
            return new KalturaGenericXsltSyndicationFeedFilter(xmlElement);
        }
        else if (objectType.equals("KalturaAssetParamsOutput")) {
            return new KalturaAssetParamsOutput(xmlElement);
        }
        else if (objectType.equals("KalturaMediaFlavorParamsOutput")) {
            return new KalturaMediaFlavorParamsOutput(xmlElement);
        }
        else if (objectType.equals("KalturaMediaFlavorParams")) {
            return new KalturaMediaFlavorParams(xmlElement);
        }
        else if (objectType.equals("KalturaApiActionPermissionItem")) {
            return new KalturaApiActionPermissionItem(xmlElement);
        }
        else if (objectType.equals("KalturaApiParameterPermissionItem")) {
            return new KalturaApiParameterPermissionItem(xmlElement);
        }
        else if (objectType.equals("KalturaGenericSyndicationFeed")) {
            return new KalturaGenericSyndicationFeed(xmlElement);
        }
        else if (objectType.equals("KalturaGenericXsltSyndicationFeed")) {
            return new KalturaGenericXsltSyndicationFeed(xmlElement);
        }
        else if (objectType.equals("KalturaGoogleVideoSyndicationFeed")) {
            return new KalturaGoogleVideoSyndicationFeed(xmlElement);
        }
        else if (objectType.equals("KalturaITunesSyndicationFeed")) {
            return new KalturaITunesSyndicationFeed(xmlElement);
        }
        else if (objectType.equals("KalturaTubeMogulSyndicationFeed")) {
            return new KalturaTubeMogulSyndicationFeed(xmlElement);
        }
        else if (objectType.equals("KalturaYahooSyndicationFeed")) {
            return new KalturaYahooSyndicationFeed(xmlElement);
        }
        else {
            throw new KalturaApiException("Invalid object type");
        }
        return null;
    }
}
