/**
 * Copyright 2011 Unicon (R) Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package net.unicon.kaltura;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kaltura.client.enums.KalturaMediaType;
import com.kaltura.client.types.KalturaBaseEntry;
import com.kaltura.client.types.KalturaMediaEntry;
import com.kaltura.client.types.KalturaMixEntry;

/**
 * This is a kaltura media item, it represents a kaltura item
 * 
 * @author Aaron Zeckoski (azeckoski @ gmail.com)
 */
public class MediaItem implements Serializable {

    private static final long serialVersionUID = 2L;

    private static Log log = LogFactory.getLog(MediaItem.class);

    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_AUDIO = "audio";
    public static final String TYPE_VIDEO = "video";

    private static final int defaultWidgetWidth = 480;
    private static final int defaultWidgetHeight = 360;

    private static int kalturaPlayerImageWidth = MediaItem.defaultWidgetWidth;
    private static int kalturaPlayerImageHeight = MediaItem.defaultWidgetHeight;
    private static int kalturaPlayerAudioWidth = MediaItem.defaultWidgetWidth;
    private static int kalturaPlayerAudioHeight = 30;
    private static int kalturaPlayerVideoWidth = MediaItem.defaultWidgetWidth;
    private static int kalturaPlayerVideoHeight = MediaItem.defaultWidgetHeight;

    public static void setDefaultSizes(
            int kalturaPlayerImageWidth, int kalturaPlayerImageHeight,
            int kalturaPlayerAudioWidth, int kalturaPlayerAudioHeight,
            int kalturaPlayerVideoWidth, int kalturaPlayerVideoHeight
            ) {
        MediaItem.kalturaPlayerImageWidth = kalturaPlayerImageWidth;
        MediaItem.kalturaPlayerImageHeight = kalturaPlayerImageHeight;
        MediaItem.kalturaPlayerAudioWidth = kalturaPlayerAudioWidth;
        MediaItem.kalturaPlayerAudioHeight = kalturaPlayerAudioHeight;
        MediaItem.kalturaPlayerVideoWidth = kalturaPlayerVideoWidth;
        MediaItem.kalturaPlayerVideoHeight = kalturaPlayerVideoHeight;
    }
    
    private Long id; // internal id
    private String locationId; // Sakai site or group reference
    private String kalturaId; // kaltura identifier
    private String ownerId; // Sakai userId
    private String creatorId; // Sakai userId (assumed to be ownerId if not set)
    private Date dateCreated;
    private Date dateModified;
    private String type = TYPE_VIDEO;

    // non-persistent
    transient int kalturaPartnerId = 1111111;
    transient boolean media = false;
    transient boolean mix = false;

    // truncation settings
    transient int shortMaxName = 16;
    transient int shortMaxDesc = 50;
    transient int shortSpace = 3;

    // PERM - done

    public void setKalturaPartnerId(int kalturaPartnerId) {
        this.kalturaPartnerId = kalturaPartnerId;
    }

    transient String kalturaCDN = "http://cdn.kaltura.com";

    public void setKalturaCDN(String kalturaCDN) {
        this.kalturaCDN = kalturaCDN;
    }

    transient KalturaBaseEntry kalturaItem;

    public KalturaBaseEntry getKalturaItem() {
        return kalturaItem;
    }

    public void setKalturaItem(KalturaBaseEntry kalturaItem) {
        this.kalturaItem = kalturaItem;
        if (this.type == null) {
            this.type = findType();
        }
        if (kalturaItem != null) {
            this.kalturaId = kalturaItem.id;
            this.kalturaPartnerId = kalturaItem.partnerId;
            if (kalturaItem instanceof KalturaMixEntry) {
                media = false;
                mix = true;
            } else if (kalturaItem instanceof KalturaMediaEntry) {
                // assume KalturaMediaEntry
                media = true;
                mix = false;
            } else {
                // assume KalturaBaseEntry
                media = true;
                mix = false;
                log.warn("kaltura item (" + kalturaItem.id + "," + kalturaItem.name
                        + ") is not mix or media: " + kalturaItem.getClass().getName());
            }
        }
    }

    public boolean isPopulated() {
        return kalturaItem != null;
    }

    public boolean isMedia() {
        return media;
    }

    public boolean isMix() {
        // if (kalturaItem != null && kalturaItem.type != null) {
        // mix = KalturaEntryType.MIX.equals(kalturaItem.type);
        // }
        return mix;
    }

    public String getLocation() {
        // if (this.locationId == null && this.collection != null) {
        // return this.collection.getLocationId(); // lazy load most likely
        // }
        return this.locationId;
    }

    public String getIdStr() {
        if (this.id != null) {
            return this.id.toString();
        }
        return null;
    }

    public String getName() {
        String name = this.kalturaId;
        if (kalturaItem != null && kalturaItem.name != null) {
            name = kalturaItem.name;
        }
        return name;
    }

    public String getShortName() {
        return MediaItem.truncateText(getName(), shortMaxName, shortSpace);
    }

    public String getDesc() {
        String desc = "";
        if (kalturaItem != null && kalturaItem.description != null) {
            desc = kalturaItem.description;
        }
        return desc;
    }

    public String getShortDesc() {
        return MediaItem.truncateText(getDesc(), shortMaxDesc, shortSpace);
    }

    public int getDuration() {
        int duration = 0;
        if (kalturaItem != null) {
            if (kalturaItem instanceof KalturaMediaEntry) {
                duration = ((KalturaMediaEntry) kalturaItem).duration;
            } else if (kalturaItem instanceof KalturaMixEntry) {
                duration = ((KalturaMixEntry) kalturaItem).duration;
            }
            if (duration < 0) {
                duration = 0;
            }
        }
        return duration;
    }

    public Date getDate() {
        Date d = new Date();
        if (kalturaItem != null && kalturaItem.createdAt > 0) {
            long time = (long) kalturaItem.createdAt * 1000l;
            d = new Date(time);
        }
        return d;
    }

    public int getWidth() {
        int width = MediaItem.defaultWidgetWidth;
        if (kalturaItem != null) {
            if (kalturaItem instanceof KalturaMediaEntry) {
                width = ((KalturaMediaEntry) kalturaItem).width;
            } else if (kalturaItem instanceof KalturaMixEntry) {
                width = ((KalturaMixEntry) kalturaItem).width;
            }
            if (width <= 0) {
                String type = findType();
                if (TYPE_AUDIO.equals(type)) {
                    width = MediaItem.kalturaPlayerAudioWidth;
                } else if (TYPE_IMAGE.equals(type)) {
                    width = MediaItem.kalturaPlayerImageWidth;
                } else {
                    width = MediaItem.kalturaPlayerVideoWidth;
                }
            }
        }
        return width;
    }

    public int getHeight() {
        int height = MediaItem.defaultWidgetHeight;
        if (kalturaItem != null) {
            if (kalturaItem instanceof KalturaMediaEntry) {
                height = ((KalturaMediaEntry) kalturaItem).height;
            } else if (kalturaItem instanceof KalturaMixEntry) {
                height = ((KalturaMixEntry) kalturaItem).height;
            }
            if (height <= 0) {
                String type = findType();
                if (TYPE_AUDIO.equals(type)) {
                    height = MediaItem.kalturaPlayerAudioHeight;
                } else if (TYPE_IMAGE.equals(type)) {
                    height = MediaItem.kalturaPlayerImageHeight;
                } else {
                    height = MediaItem.kalturaPlayerVideoHeight;
                }
            }
        }
        return height;
    }

    public String findType() {
        String type = TYPE_VIDEO;
        if (kalturaItem != null) {
            if (kalturaItem instanceof KalturaMediaEntry) {
                KalturaMediaEntry kme = (KalturaMediaEntry) kalturaItem;
                if (KalturaMediaType.AUDIO.equals(kme.mediaType)) {
                    type = TYPE_AUDIO;
                } else if (KalturaMediaType.IMAGE.equals(kme.mediaType)) {
                    type = TYPE_IMAGE;
                }
            }
        } else {
            if (this.type != null) {
                type = this.type;
            }
        }
        return type;
    }

    public String getMediaType() {
        return findType();
    }

    public String getThumbnail() {
        String url = this.kalturaCDN + "/p/" + this.kalturaPartnerId
                + "/thumbnail/width/120/height/90/entry_id/" + this.kalturaId;
        if (kalturaItem != null && kalturaItem.thumbnailUrl != null) {
            url = kalturaItem.thumbnailUrl;
        }
        return url;
    }

    /**
     * @return the URL which can be used to download the media content
     */
    public String getDownloadURL() {
        /*
         * Instructions from Kaltura Download Button: currently the URL (which I guess you take from
         * the 'get' response) is something like: ����
         * http://cdnbakmi.kaltura.com/p/166762/sp/16676200/raw/entry_id/1_nkkzp8z4/version/0
         * concatenating '/file_name/1_nkkzp8z4' so the URL is actually adding the 'file_name'
         * parameter to the request which simply adds 'content-disposition' header to the response.
         * the 'content-disposition' is what forces the browser to open the 'open/save as' dialog.
         * after the concatenation the URL would look like: ����
         * http://cdnbakmi.kaltura.com/p/166762
         * /sp/16676200/raw/entry_id/1_nkkzp8z4/version/0/file_name/1_nkkzp8z4
         */
        String suffix = "/file_name/" + this.kalturaId; // this forces the mime-type from kaltura
                                                        // server to be correct for download
        String url = this.kalturaCDN + "/p/" + this.kalturaPartnerId + "/raw/entry_id/"
                + this.kalturaId + suffix;
        if (kalturaItem != null && kalturaItem.thumbnailUrl != null) {
            url = kalturaItem.downloadUrl + suffix;
        }
        return url;
    }

    public String[] getTags() {
        String[] tags = new String[0];
        if (kalturaItem != null && kalturaItem.tags != null) {
            tags = StringUtils.split(kalturaItem.tags, ", ");
        }
        return tags;
    }

    /**
     * @return the creator user id for this media item
     */
    public String getCreatorUserId() {
        String creator = this.creatorId;
        if (creator == null) {
            creator = this.ownerId;
        }
        return creator;
    }

    /**
     * Default constructor
     */
    public MediaItem() {
        this.dateCreated = new Date();
        this.dateModified = this.dateCreated;
    }

    public MediaItem(KalturaBaseEntry kbe, String ownerId) {
        this();
        this.ownerId = ownerId;
        this.creatorId = ownerId;
        this.setKalturaItem(kbe);
    }

    public MediaItem(String locationId, String kalturaId, String ownerId) {
        this();
        this.locationId = locationId;
        this.kalturaId = kalturaId;
        this.ownerId = ownerId;
        this.creatorId = ownerId;
    }

    public static class MediaItemNameComparator implements Comparator<MediaItem>, Serializable {

        static private final long serialVersionUID = 31L;

        public int compare(MediaItem o1, MediaItem o2) {
            int comparison = 0;
            if (o1.getName() != null && o2.getName() != null) {
                comparison = o1.getName().compareTo(o2.getName());
            }
            if (comparison == 0 && o1.getId() != null && o2.getId() != null) {
                comparison = o1.getId().compareTo(o2.getId());
            }
            return comparison;
        }
    }

    private static String truncateSuffix = "...";

    public static String truncateText(String text, int maxChars, int reverseSpaceChars) {
        String truncated = text;
        if (maxChars <= 0) {
            maxChars = 50;
        }
        if (text != null && text.length() > maxChars) {
            boolean reverseFind = false;
            if (reverseSpaceChars > 0) {
                int pos = text.lastIndexOf(' ', maxChars);
                if (pos > (maxChars - reverseSpaceChars)) {
                    truncated = text.substring(0, pos);
                    reverseFind = true;
                }
            }
            if (!reverseFind) {
                truncated = text.substring(0, maxChars - 3);
            }
            truncated = truncated + truncateSuffix;
        }
        return truncated;
    }

    @Override
    public String toString() {
        return "{MI:" + id  
                + ":t=" + type 
                + ":o=" + ownerId
                + ":c=" + creatorId
                + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MediaItem other = (MediaItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * Getters and Setters
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKalturaId() {
        return kalturaId;
    }

    public void setKalturaId(String kalturaId) {
        this.kalturaId = kalturaId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

}
