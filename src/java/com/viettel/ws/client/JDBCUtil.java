/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.ws.client;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author HuyND6@Viettel.com.vn
 * @version 3.0
 */
public class JDBCUtil {

    /** Logger.*/
    private static final Log logger = LogFactory.getLog(JDBCUtil.class);

    private JDBCUtil() {
    }

    public static String convertToOrigin(String s) {
        String newStr = "";
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c) && Character.isUpperCase(c)) {
                newStr += ("_" + String.valueOf(c));
            } else if (!Character.isDigit(c) && Character.isLowerCase(c)) {
                newStr += String.valueOf(c).toUpperCase();
            } else {
                newStr += String.valueOf(c);
            }
        }
        return newStr;
    }

    public static String UpcaseFirst(String str) {
        String first = str.substring(0, 1);
        String concat = str.substring(1);
        return first.toUpperCase() + concat;
    }

    public static Element createRowElement(Object obj, Document doc) {
        Element row = doc.createElement("Row");
        Class cls = obj.getClass();
        Field[] fieldArr = cls.getDeclaredFields();
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < fieldArr.length; i++) {
            try {
                String fieldName = fieldArr[i].getName();
                String getMethodName = "get" + UpcaseFirst(fieldName);
                Method getMethod = cls.getMethod(getMethodName);

                Object value = getMethod.invoke(obj);
                if (fieldArr[i].getType().equals(Date.class)) {
                    value = fm.format(value);
                }

                Element node = doc.createElement(convertToOrigin(fieldName));
                node.appendChild(doc.createTextNode(value.toString()));
                row.appendChild(node);

            } catch (Exception ex) {
                continue;
            }
        }
        return row;
    }

    /**
     * Create xml string - fastest, but may have encoding issues
     * @param rs - a Result set
     * @return - XML string of a result set
     * @throws SQLException - If error when read data from database
     */
    public static String toXML(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        StringBuilder xml = new StringBuilder();
        xml.append("<Results>");

        while (rs.next()) {
            xml.append("<Row>");

            for (int i = 1; i <= colCount; i++) {
                String columnName = rsmd.getColumnName(i);
                Object value = rs.getObject(i);
                xml.append("<").append(columnName).append(">");

                if (value != null) {
                    xml.append(value.toString().trim());
                }
                xml.append("</").append(columnName).append(">");
            }
            xml.append("</Row>");
        }

        xml.append("</Results>");

        return xml.toString();
    }

    /**
     * Create document using DOM api
     * @param rs a result set
     * @return A document of a result set
     * @throws ParserConfigurationException - If error when parse string
     * @throws SQLException - If error when read data from database
     */
    public static Document toDocument(ResultSet rs)
            throws ParserConfigurationException, SQLException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element results = doc.createElement("Results");
        doc.appendChild(results);

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();

        while (rs.next()) {
            Element row = doc.createElement("Row");
            results.appendChild(row);

            for (int i = 1; i <= colCount; i++) {
                String columnName = rsmd.getColumnName(i);
                Object value = rs.getObject(i);

                Element node = doc.createElement(columnName);
                node.appendChild(doc.createTextNode(value.toString()));
                row.appendChild(node);
            }
        }
        return doc;
    }

    /**
     * Create Empty Document
     * @return A empty document
     * @throws ParserConfigurationException - If error when create document
     */
    public static Document createDocument()
            throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element results = doc.createElement("Results");
        doc.appendChild(results);
        return doc;
    }

    /**
     * Create document using DOM api
     * @param rs a result set
     * @param doc a input document for append content
     * @param rsName name of the appended element
     * @return a document after append content
     * @throws ParserConfigurationException If error when parse XML string
     * @throws SQLException If error when read data from database
     */
    public static Document add2Document1(ResultSet rs1, ResultSet rs2, Document doc, String rsName)
            throws ParserConfigurationException, SQLException {

        if (rs1 == null && rs2 == null) {
            return doc;
        }

        //Get root element
        Element root = doc.getDocumentElement();


        Element rsElement = doc.createElement(rsName);
        root.appendChild(rsElement);

        if (rs1 != null) {
            ResultSetMetaData rsmd = rs1.getMetaData();
            int colCount = rsmd.getColumnCount();

            while (rs1.next()) {
                Element row = doc.createElement("Row");
                rsElement.appendChild(row);
                try {
                    for (int i = 1; i <= colCount; i++) {
                        String columnName = rsmd.getColumnName(i);
                        Object value = rs1.getObject(i);
                        if (value == null) {
                            value = "";
                        }

                        Element node = doc.createElement(columnName);
                        node.appendChild(doc.createTextNode(value.toString()));
                        row.appendChild(node);
                    }
                } catch (Exception e) {
                    logger.error(e, e);
                }
            }
        }

        if (rs2 != null) {
            ResultSetMetaData rsmd = rs2.getMetaData();
            int colCount = rsmd.getColumnCount();

            while (rs2.next()) {
                Element row = doc.createElement("Row");
                rsElement.appendChild(row);
                try {
                    for (int i = 1; i <= colCount; i++) {
                        String columnName = rsmd.getColumnName(i);
                        Object value = rs2.getObject(i);
                        if (value == null) {
                            value = "";
                        }

                        Element node = doc.createElement(columnName);
                        node.appendChild(doc.createTextNode(value.toString()));
                        row.appendChild(node);
                    }
                } catch (Exception e) {
                    logger.error(e, e);
                }
            }
        }
        return doc;

    }

    /**
     * Create document using DOM api
     * @param rs a result set
     * @param doc a input document for append content
     * @param rsName name of the appended element
     * @return a document after append content
     * @throws ParserConfigurationException If error when parse XML string
     * @throws SQLException If error when read data from database
     */
    public static Document add2Document(List<Object> rs, Document doc, String rsName)
            throws ParserConfigurationException, SQLException {

        if (rs == null) {
            return doc;
        }

        //Get root element
        Element root = doc.getDocumentElement();
        Element rsElement = doc.createElement(rsName);
        root.appendChild(rsElement);
        
        for (Object object : rs) {
            Element row = createRowElement(object, doc);
            rsElement.appendChild(row);
        }
        
        return doc;
    }

    /**
     * Convert a document to string
     * @param doc a input document
     * @return a string of document
     * @throws IOException If error when convert
     */
    public static String serialize(Document doc) throws IOException {
        StringWriter writer = new StringWriter();
        OutputFormat format = new OutputFormat();

        format.setIndenting(true);

        XMLSerializer serializer = new XMLSerializer(writer, format);
        serializer.serialize(doc);

        return writer.getBuffer().toString();
    }

    /**
     * Create document from xml string - slower than using DOM api
     * @param rs a result set
     * @return a document
     * @throws SQLException If error when read data from database
     * @throws ParserConfigurationException If error when create document
     * @throws SAXException If error when create document
     * @throws IOException If error when create document
     */
    public static Document toDoc(ResultSet rs)
            throws
            SQLException,
            ParserConfigurationException,
            SAXException,
            IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        String xml = toXML(rs);
        StringReader reader = new StringReader(xml);
        InputSource source = new InputSource(reader);
        return builder.parse(source);
    }
}
