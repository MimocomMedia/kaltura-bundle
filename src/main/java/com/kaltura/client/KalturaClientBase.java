package com.kaltura.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.ProxyHost;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.PartSource;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import com.kaltura.client.enums.KalturaSessionType;
import com.kaltura.client.utils.XmlUtils;

/**
 * Contains non-generated client logic. Includes the doQueue method which is responsible for
 * making HTTP calls to the Kaltura server.
 * 
 * @author jpotts
 * @author azeckoski
 */
abstract public class KalturaClientBase {

    protected KalturaConfiguration kalturaConfiguration;
    protected String sessionId;
    protected List<KalturaServiceActionCall> callsQueue;
    protected boolean isMultiRequest;
    protected KalturaParams multiRequestParamsMap;

    private static Logger logger = LoggerFactory.getLogger(KalturaClientBase.class);

    public KalturaClientBase() {
    }

    public KalturaClientBase(KalturaConfiguration config) {
        this.kalturaConfiguration = config;
        this.callsQueue = new ArrayList<KalturaServiceActionCall>();
        this.multiRequestParamsMap = new KalturaParams();
    }

    abstract String getApiVersion();

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isMultiRequest() {
        return isMultiRequest;
    }

    public void setMultiRequest(boolean isMultiRequest) {
        this.isMultiRequest = isMultiRequest;
    }

    public void setKalturaConfiguration(KalturaConfiguration kalturaConfiguration) {
        this.kalturaConfiguration = kalturaConfiguration;
    }

    public void queueServiceCall(String service, String action, KalturaParams kparams) {
        this.queueServiceCall(service, action, kparams, new KalturaFiles());
    }

    public void queueServiceCall(String service, String action, KalturaParams kparams, KalturaFiles kfiles) {
        // in start session partner id is optional (default -1). if partner id was not set, use the one in the config
        if (!kparams.containsKey("partnerId"))
            kparams.addIntIfNotNull("partnerId", this.kalturaConfiguration.getPartnerId());

        if (kparams.get("partnerId").equals("-1"))
            kparams.addIntIfNotNull("partnerId", this.kalturaConfiguration.getPartnerId());

        kparams.addStringIfNotNull("ks", this.sessionId);

        KalturaServiceActionCall call = new KalturaServiceActionCall(service, action, kparams, kfiles);
        this.callsQueue.add(call);
    }

    public Element doQueue() throws KalturaApiException {
        if (this.callsQueue.isEmpty()) return null;

        if (logger.isDebugEnabled()) {
            logger.debug("service url: [" + this.kalturaConfiguration.getEndpoint() + "]");
        }

        KalturaParams kparams = new KalturaParams();
        KalturaFiles kfiles = new KalturaFiles();

        // append the basic params
        kparams.put("apiVersion", this.getApiVersion());
        kparams.put("clientTag", this.kalturaConfiguration.getClientTag());
        kparams.addIntIfNotNull("format", this.kalturaConfiguration.getServiceFormat().getHashCode());

        String url = this.kalturaConfiguration.getEndpoint() + "/api_v3/index.php?service=";

        if (isMultiRequest) {
            url += "multirequest";
            int i = 1;
            for (KalturaServiceActionCall call : this.callsQueue) {
                KalturaParams callParams = call.getParamsForMultiRequest(i++);
                kparams.add(callParams);
                kfiles.add(call.getFiles());
            }

            // map params
            for (String key : this.multiRequestParamsMap.keySet()) {
                String requestParam = key;
                String resultParam = this.multiRequestParamsMap.get(key);

                if (kparams.containsKey(requestParam)) {
                    kparams.put(requestParam, resultParam);
                }
            }
        } else {
            KalturaServiceActionCall call = this.callsQueue.get(0);
            url += call.getService() + "&action=" + call.getAction();
            kparams.add(call.getParams());
            kfiles.add(call.getFiles());
        }

        // cleanup
        this.callsQueue.clear();
        this.isMultiRequest = false;
        this.multiRequestParamsMap.clear();

        kparams.put("sig", this.signature(kparams));

        if (logger.isDebugEnabled()) {
            logger.debug("full reqeust url: [" + url + "?" + kparams.toQueryString() + "]");
        }

        // build request
        HttpClient client = new HttpClient();
        // added by Unicon to handle proxy hosts
        String proxyHost = System.getProperty( "http.proxyHost" );
        if ( proxyHost != null ) {
            int proxyPort = -1;
            String proxyPortStr = System.getProperty( "http.proxyPort" );
            if (proxyPortStr != null) {
                try {
                    proxyPort = Integer.parseInt( proxyPortStr );
                } catch (NumberFormatException e) {
                    logger.warn("Invalid number for system property http.proxyPort ("+proxyPortStr+"), using default port instead");
                }
            }
            ProxyHost proxy = new ProxyHost( proxyHost, proxyPort );
            client.getHostConfiguration().setProxyHost( proxy );
        }
        // added by Unicon to force encoding to UTF-8
        String utf8CharSet = "UTF-8";
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, utf8CharSet);
        client.getParams().setParameter(HttpMethodParams.HTTP_ELEMENT_CHARSET, utf8CharSet);
        client.getParams().setParameter(HttpMethodParams.HTTP_URI_CHARSET, utf8CharSet);

        HttpConnectionManagerParams connParams = client.getHttpConnectionManager().getParams();
        connParams.setSoTimeout(this.kalturaConfiguration.getTimeout());
        client.getHttpConnectionManager().setParams(connParams);

        PostMethod method = new PostMethod(url);
        method.setRequestHeader("Accept","text/xml,application/xml,*/*");
        method.setRequestHeader("Accept-Charset","utf-8,ISO-8859-1;q=0.7,*;q=0.5");

        if (!kfiles.isEmpty()) {
            method = this.getPostMultiPartWithFiles(method, kparams, kfiles);           
        } else {
            method = this.addParams(method, kparams);            
        }

        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler (3, false));

        String responseString = "";
        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            Header[] headers = method.getRequestHeaders();
            if (logger.isDebugEnabled()) {
                for (Header header : headers) {
                    logger.debug("Header [" + header.getName() + " value [" + header.getValue() + "]");
                }
            }

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println ( "Method failed: " + method.getStatusLine ( ) );
            }

            // Read the response body.
            byte[] responseBody = method.getResponseBody ( );

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            responseString = new String(responseBody, utf8CharSet); // Unicon: this MUST be set to UTF-8 charset -AZ
            if (logger.isDebugEnabled()) {
                logger.debug(responseString);
            }

        } catch ( HttpException e ) {
            System.err.println ( "Fatal protocol violation: " + e.getMessage ( ) );
            e.printStackTrace ( );
        } catch ( IOException e ) {
            System.err.println ( "Fatal transport error: " + e.getMessage ( ) );
            e.printStackTrace ( );
        } finally {
            // Release the connection.
            method.releaseConnection ( );
        }

        Element responseXml = XmlUtils.parseXml(responseString);

        this.validateXmlResult(responseXml);

        Element resultXml = null;
        try {
            resultXml = XmlUtils.getElementByXPath(responseXml, "/xml/result");
        } catch (XPathExpressionException xee) {
            throw new KalturaApiException("XPath expression exception evaluating result");
        }

        this.throwExceptionOnAPIError(resultXml);

        return resultXml;
    }

    public void startMultiRequest() {
        isMultiRequest = true;
    }


    public KalturaMultiResponse doMultiRequest() throws KalturaApiException 
    {
        Element multiRequestResult = doQueue();

        KalturaMultiResponse multiResponse = new KalturaMultiResponse();

        for(int i = 0; i < multiRequestResult.getChildNodes().getLength(); i++) 
        {
            Element arrayNode = (Element)multiRequestResult.getChildNodes().item(i);
            
            KalturaApiException exception = getExceptionOnAPIError(arrayNode);
            if (exception != null)
            {
            	multiResponse.add(exception);
            }
            else if (arrayNode.getElementsByTagName("objectType").getLength() == 0)
            {
                multiResponse.add(arrayNode.getTextContent());
            }
            else
            {
                multiResponse.add(KalturaObjectFactory.create(arrayNode));
            }
        }
        return multiResponse;
    }


    public void mapMultiRequestParam(int resultNumber, int requestNumber, String requestParamName) {
        this.mapMultiRequestParam(resultNumber, null, requestNumber, requestParamName);
    }

    public void mapMultiRequestParam(int resultNumber, String resultParamName, int requestNumber, String requestParamName) {
        String resultParam = "{" + resultNumber + ":result";
        if (resultParamName != null && resultParamName != "") resultParam += resultParamName;
        resultParam += "}";

        String requestParam = requestNumber + ":" + requestParamName;

        this.multiRequestParamsMap.put(requestParam, resultParam);
    }

    private String signature(KalturaParams kparams) {
        String str = "";
        for (String key : kparams.keySet()) {
            str += (key + kparams.get(key));
        }

        MessageDigest mdEnc = null;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        mdEnc.update(str.getBytes(), 0, str.length());
        String md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string

        return md5;
    }

    private void validateXmlResult(Element resultXml) throws KalturaApiException {

        Element resultElement = null;
        try {
            resultElement = XmlUtils.getElementByXPath(resultXml, "/xml/result");
        } catch (XPathExpressionException xee) {
            throw new KalturaApiException("XPath expression exception evaluating result");
        }

        if (resultElement != null) {
            return;            
        } else {
            throw new KalturaApiException("Invalid result");
        }
    }

    private KalturaApiException getExceptionOnAPIError(Element result) throws KalturaApiException {
    	try {
	    	Element errorElement = XmlUtils.getElementByXPath(result, "error");
	    	if (errorElement == null)
	    	{
	    		return null;
	    	}
	    	
	    	Element messageElement = XmlUtils.getElementByXPath(errorElement, "message");
	    	Element codeElement = XmlUtils.getElementByXPath(errorElement, "code");
	    	if (messageElement == null || codeElement == null)
	    	{
	    		return null;
	    	}
	    	
	    	return new KalturaApiException(messageElement.getTextContent(), codeElement.getTextContent());
    	} catch (XPathExpressionException xee) {
    		throw new KalturaApiException("XPath expression exception evaluating result");
    	}
    }

    private void throwExceptionOnAPIError(Element result) throws KalturaApiException {

        Element resultElement = null;
        try {
            resultElement = XmlUtils.getElementByXPath(result, "error");
        } catch (XPathExpressionException xee) {
            throw new KalturaApiException("XPath expression exception evaluating result");
        }

        if (resultElement != null) {
            throw new KalturaApiException(resultElement.getTextContent());
        }
    }

    private PostMethod getPostMultiPartWithFiles(PostMethod postMethod, KalturaParams kparams, KalturaFiles kfiles) {

        String boundary = "---------------------------" + System.currentTimeMillis();
        List <Part> parts = new ArrayList<Part>();
        parts.add(new StringPart (HttpMethodParams.MULTIPART_BOUNDARY, boundary));

        for (String key : kparams.keySet()) {
            parts.add(new StringPart (key,kparams.get(key)));       
        }

        for (String key : kfiles.keySet()) {
            final KalturaFile kFile = kfiles.get(key);
            parts.add(new StringPart (key, "filename="+kFile.getName()));
            if (kFile.getFile() != null) {
                // use the file
                File file = kFile.getFile();
                try {
                    parts.add(new FilePart(key, file));
                } catch (FileNotFoundException e) {
                    // TODO this sort of leaves the submission in a weird state... -AZ
                    logger.error("Exception while iterating over kfiles", e);          
                }
            } else {
                // use the input stream
                PartSource fisPS = new PartSource() {
                    public long getLength() {
                        return kFile.getSize();
                    }
                    public String getFileName() {
                        return kFile.getName();
                    }
                    public InputStream createInputStream() throws IOException {
                        return kFile.getInputStream();
                    }
                };
                parts.add(new FilePart(key, fisPS));
            }
        }

        Part allParts[] = new Part[parts.size()];
        int i=0;
        for (Part p : parts) {
            allParts[i] = p;
            ++i;
        }

        postMethod.setRequestEntity(new MultipartRequestEntity(allParts, postMethod.getParams()));

        return postMethod;
    }

    private PostMethod addParams(PostMethod method, KalturaParams kparams) {

        for (String key : kparams.keySet()) {
            method.addParameter(key, kparams.get(key));
        }

        return method;

    }

    /* Gonen fix - UTF8PostMethod instead of PostMethod - 20110803
     * Unicon: not using this for now -AZ
    public static class UTF8PostMethod extends PostMethod {
        public UTF8PostMethod(String url) {
            super(url);
        }

        @Override
        public String getRequestCharSet() {
            // return super.getRequestCharSet();
            return "UTF-8";
        }
    }
    */

    public String generateSession(String adminSecretForSigning) throws Exception
    {
        return this.generateSession(adminSecretForSigning, "");
    }
    
    public String generateSession(String adminSecretForSigning, String userId) throws Exception
    {
        return this.generateSession(adminSecretForSigning, userId, KalturaSessionType.USER);
    }
    
    public String generateSession(String adminSecretForSigning, String userId, KalturaSessionType type) throws Exception
    {
        return this.generateSession(adminSecretForSigning, userId, type, -1);
    }
    
    public String generateSession(String adminSecretForSigning, String userId, KalturaSessionType type, int partnerId) throws Exception
    {
        return this.generateSession(adminSecretForSigning, userId, type, partnerId, 86400);
    }
    
    public String generateSession(String adminSecretForSigning, String userId, KalturaSessionType type, int partnerId, int expiry) throws Exception
    {
        return this.generateSession(adminSecretForSigning, userId, type, partnerId, expiry, "");
    }

    public String generateSession(String adminSecretForSigning, String userId, KalturaSessionType type, int partnerId, int expiry, String privileges) throws Exception
    {
        try
        {
            // initialize required values
            int rand = (int)(Math.random() * 32000);
            expiry = (int)(System.currentTimeMillis() / 1000) + 86400;
            
            // build info string
            StringBuilder sbInfo = new StringBuilder();
            sbInfo.append(this.kalturaConfiguration.partnerId).append(";"); // index 0 - partner ID
            sbInfo.append(this.kalturaConfiguration.partnerId).append(";"); // index 1 - partner pattern - using partner ID
            sbInfo.append(expiry).append(";"); // index 2 - expiration timestamp
            sbInfo.append(type.getHashCode()).append(";"); // index 3 - session type
            sbInfo.append(rand).append(";"); // index 4 - random number
            sbInfo.append(userId).append(";"); // index 5 - user ID
            sbInfo.append(privileges); // index 6 - privileges
            
            // sign info with SHA1 algorithm
            MessageDigest algorithm = MessageDigest.getInstance("SHA1");
            algorithm.reset();
            algorithm.update(adminSecretForSigning.getBytes());
            algorithm.update(sbInfo.toString().getBytes());
            byte infoSignature[] = algorithm.digest();
            
            // convert signature to hex:
            String signature = this.convertToHex(infoSignature);
            
            // build final string to base64 encode
            StringBuilder sbToEncode = new StringBuilder();
            sbToEncode.append(signature.toString()).append("|").append(sbInfo.toString());
            // NOTE: do not use sun classes
            //BASE64Encoder encoder = new BASE64Encoder();
            //String hashedString = encoder.encode(sbToEncode.toString().getBytes());

            // encode the signature and info with base64
            String hashedString = Base64.encodeBase64String(sbToEncode.toString().getBytes());

            // remove line breaks in the session string
            String ks = hashedString.replace("\n", "");
            // return the generated session key (KS)
            return ks;
        } catch (NoSuchAlgorithmException ex)
        {
            throw new Exception(ex);
        }
    }

    // new function to convert byte array to Hex
    private String convertToHex(byte[] data) { 
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) { 
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do { 
                if ((0 <= halfbyte) && (halfbyte <= 9)) 
                    buf.append((char) ('0' + halfbyte));
                else 
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        } 
        return buf.toString();
    } 

}
