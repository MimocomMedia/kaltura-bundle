package com.kaltura.client.utils;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * XML parsing utilities for the Kaltura Java Client
 * 
 * AZ Refactored this class on Thu 23 Jun 2011 11:33:10 EDT
 * 
 * @author Aaron Zeckoski (azeckoski @ vt.edu) (azeckoski @ unicon.net)
 */
public class XmlUtils {
    public static Element parseXml(String xml) {
        Element root = null;
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse( new InputSource(new StringReader(xml)) );
            root = doc.getDocumentElement();
        } catch(ParserConfigurationException pce) {
            //pce.printStackTrace();
        } catch(SAXException se) {
            //se.printStackTrace();
        } catch(IOException ioe) {
            //ioe.printStackTrace();
        }
        return root;
    }

    /**
     * Finds the text value of the first tag within a given element
     * 
     * @param element the XML element
     * @param tagName the tag name
     * @return the value in the tag OR null if the tagName is not set, element is not set, tagName is not found
     */
    public static  String getTextValue(Element element, String tagName) {
        String textVal = null;

        /* removed this because it depends on com.sun.org.apache.xpath.internal.XPathAPI
         * this is a very bad practice - http://stackoverflow.com/questions/446315/which-xpathapi-should-i-use-in-java-1-5
         * Commented on Thu 23 Jun 2011 15:36:21 EDT
         * 
		try {
			NodeIterator ni = XPathApi.selectNodeIterator(ele, "//" + tagName);

			if (ni != null) {
				Element el = (Element)ni.nextNode();
				if (el != null)	{
					Node child = el.getFirstChild();
					if (child != null) textVal = child.getNodeValue();
				}
			}

        } catch (TransformerException e) {
            e.printStackTrace();
        }
         */

        // Added on Thu 23 Jun 2011 17:27:10 EDT
        if (element != null && element.hasChildNodes()) {
            // only elements with child nodes will have any content
            if (element.getTagName().equals(tagName)) {
                // we found our tag at this element
                textVal = element.getFirstChild().getNodeValue();
            } else {
                // search the tag hierarchy instead
                NodeList nl = element.getElementsByTagName(tagName);
                for (int i = 0; i < nl.getLength(); i++) {
                    Element el = (Element) nl.item(i);
                    if (el != null) {
                        Node child = el.getFirstChild();
                        if (child != null) {
                            textVal = child.getNodeValue();
                            break; // I assume we want to return the first matched result and not the last as the old code did? -AZ
                        }
                    }
                }
            }
        }

        return textVal;
    }

    /**
     * @param e
     * @return
     * @deprecated Do not use this, it is currently unused -AZ
     */
    public static boolean hasChildren (Element e) {
        // This is weird... why not use element.hasChildNodes() ?? -AZ
        if (e == null) return false;
        return e.getFirstChild() != null;
    }

    /**
     * Finds an element within and element based on an xpath expression
     * NOTE: this will fail if more than one element is matched by the xPathExpression
     * 
     * @param element the XML element to search for elements within
     * @param xPathExpression string representing an xpath filter (e.g. /xml/result)
     * @return the element found by this xpath expression
     * @throws XPathExpressionException if more than one element is found or the expression is invalid or other xpath failure occurs
     */
    public static Element getElementByXPath(Element element, String xPathExpression) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        Element foundElement = (Element)xPath.evaluate(xPathExpression, element, XPathConstants.NODE);
        return foundElement;
    }
}
