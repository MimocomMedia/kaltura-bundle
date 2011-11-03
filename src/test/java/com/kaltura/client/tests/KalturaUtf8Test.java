package com.kaltura.client.tests;

import java.io.File;
import java.net.URL;

import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaClient;
import com.kaltura.client.KalturaMultiResponse;
import com.kaltura.client.enums.KalturaMediaType;
import com.kaltura.client.types.KalturaMediaEntry;
import com.kaltura.client.types.KalturaMediaListResponse;
import com.kaltura.client.types.KalturaPartner;

public class KalturaUtf8Test extends KalturaTestBase {

    public static String CHINESE = "AZ test - 吞下玻璃";
    public static String I18N = "Iñtërnâtiônàlizætiøn";
    public static String KEID = "1_vyxqbgo5"; //"1_4ydjapab";

    public void testUTF8() {
        System.out.println("-- UTF8 test start");
        startAdminSession();
        KalturaMediaEntry kme = null;
        /*
        list();
        multiResponse();
        add();
        */
        kme = exists(KEID);
        kme = update(KEID, "AZ i18n", "");
        assertEquals("AZ i18n", kme.name);
        assertEquals("", kme.description);

        kme = exists(KEID);
        assertNotSame(CHINESE, kme.name);
        assertNotSame(I18N, kme.description);

        kme = update(KEID, CHINESE, I18N);
        assertEquals(CHINESE, kme.name);
        assertEquals(I18N, kme.description);

        System.out.println("-- UTF8 test complete");
    }

    public KalturaMediaEntry exists(String entryId)
    {
        assertNotNull(entryId);
        KalturaMediaEntry kme = null;
        try {
            KalturaClient client = this.client; //getKalturaClient(KalturaTestConfig.PARTNER_ID, KalturaTestConfig.ADMIN_SECRET, true);
            kme = client.getMediaService().get(entryId);
            assertNotNull(kme);
            System.out.println("Entry ("+kme.id+") exists with name ("+kme.name+")");
        } catch (KalturaApiException e) {
            e.printStackTrace();
            fail("Exception: "+e);
        }
        return kme;
    }

    public KalturaMediaEntry update(String entryId, String name, String desc)
    {
        assertNotNull(entryId);
        assertNotNull(name);
        assertNotNull(desc);
        KalturaMediaEntry kme = null;
        try {
            KalturaClient client = this.client; //getKalturaClient(KalturaTestConfig.PARTNER_ID, KalturaTestConfig.ADMIN_SECRET, true);
            KalturaMediaEntry kmeUp = new KalturaMediaEntry();
            kmeUp.name = name;
            kmeUp.description = desc;
            System.out.println("Updating entry ("+entryId+") to name ("+ kmeUp.name +")");
            kme = client.getMediaService().update(entryId, kmeUp);
            assertNotNull(kme);
            System.out.println("Updated entry ("+kme.id+") to name ("+ kme.name +")");
        } catch (KalturaApiException e) {
            e.printStackTrace();
            fail("Exception: "+e);
        }
        return kme;
    }

    public void list()
    {
        try {
            KalturaClient client = this.client; //getKalturaClient(KalturaTestConfig.PARTNER_ID, KalturaTestConfig.ADMIN_SECRET, true);			
            //Should not call Base directly - this is an Abstract!
            //KalturaBaseEntryListResponse list = client.getBaseEntryService().list();
            KalturaMediaListResponse list = client.getMediaService().list();
            int counter = 0;
            for (KalturaMediaEntry entry : list.objects) {
                counter++;
                System.out.println(counter + ": list entry ("+entry.id+"): (" + entry.name + ")");
            }
        } catch (KalturaApiException e) {
            e.printStackTrace();
            fail("Exception: "+e);
        }		
    }

    public void multiResponse()
    {
        try {
            KalturaClient client = this.client; //getKalturaClient(KalturaTestConfig.PARTNER_ID, KalturaTestConfig.ADMIN_SECRET, true);
            client.setMultiRequest(true);
            client.getBaseEntryService().count();
            client.getPartnerService().getInfo();
            client.getPartnerService().getUsage(2010);	
            KalturaMultiResponse multi = client.doMultiRequest();
            KalturaPartner partner = (KalturaPartner)multi.get(1);
            System.out.print("\nGot Admin User email: " + partner.adminEmail);
        } catch (KalturaApiException e) {
            e.printStackTrace();
            fail("Exception: "+e);
        }		
    }

    public void add()
    {
        try {
            System.out.print("\nUploading test video...");
            KalturaClient client = this.client; //getKalturaClient(KalturaTestConfig.PARTNER_ID, KalturaTestConfig.SECRET, false);			
            //File up = new File(KalturaTestConfig.UPLOAD_FILE);
            URL uploadFileURL = KalturaTestBase.class.getClassLoader().getResource(DEFAULT_UPLOAD_FILE);
            File file = new File(uploadFileURL.toURI());
            String token = client.getBaseEntryService().upload(file);
            KalturaMediaEntry entry = new KalturaMediaEntry();
            entry.name = "my upload entry";
            entry.mediaType = KalturaMediaType.VIDEO;
            KalturaMediaEntry newEntry = client.getMediaService().addFromUploadedFile(entry, token);
            System.out.print("\nUploaded a new Video entry " + newEntry.id);
            client.getMediaService().delete(newEntry.id);
            try {
                entry = null;
                entry = client.getMediaService().get(newEntry.id);
            } catch (KalturaApiException exApi) {
                if (entry == null) {
                    System.out.print("\nDeleted the entry ("+newEntry.id+") successfully!");
                }
            }
        } catch (KalturaApiException e) {
            System.out.println("Kaltura API failure: " + e);
            e.printStackTrace();
            fail("Upload Kaltura failure: " + e);
        } catch (Exception e) {
            System.out.println("Trouble uploading: " + e);
            e.printStackTrace();
            fail("Upload failure: " + e);
        }
    }
}
