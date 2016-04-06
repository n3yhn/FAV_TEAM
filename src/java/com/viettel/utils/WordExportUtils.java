/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.utils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.viettel.common.util.Constants;
import com.viettel.utils.WordExportUtils.HeaderFooter;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAOHibernate.VoAttachsDAOHE;
import com.viettel.vsaadmin.database.BO.Roles;
import com.viettel.convert.service.Converter_Service;
import com.viettel.convert.service.PdfDocxFile;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import org.docx4j.XmlUtils;
import org.docx4j.convert.in.xhtml.XHTMLImporter;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.docx4j.wml.Tc;

///
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javax.xml.transform.stream.StreamResult;

import org.docx4j.convert.out.html.AbstractHtmlExporter;
import org.docx4j.convert.out.html.HtmlExporterNG2;
import org.docx4j.convert.out.html.AbstractHtmlExporter.HtmlSettings;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.FooterReference;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.HeaderReference;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.SectPr;

///
/**
 *
 * @author Administrator
 */
public class WordExportUtils {

    private static ObjectFactory objectFactory = new ObjectFactory();
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WordExportUtils.class);

    public static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
        List<Object> result = new ArrayList<Object>();
        if (obj instanceof JAXBElement) {
            obj = ((JAXBElement<?>) obj).getValue();
        }
        if (obj.getClass().equals(toSearch)) {
            result.add(obj);
        } else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObject(child, toSearch));
            }
        }
        return result;
    }

    public void replacePlaceholder(WordprocessingMLPackage template, String name, String placeholder) {
        try {
            List<Object> rs = getAllElementFromObject(template.getMainDocumentPart(), P.class);
            ObjectFactory factory = Context.getWmlObjectFactory();
            for (Object r : rs) {
                P run = (P) r;
                List<Object> texts = getAllElementFromObject(run, Text.class);
                for (Object text : texts) {
                    try {
                        Text textElement = (Text) text;
                        if (textElement.getValue() != null) {
                            if (textElement.getValue().equals(placeholder)) {
                                if (name != null) {
                                    textElement.setValue(name);
                                    String value = SuperScript.convertToSuperSrcipt((String) name);
                                    if (name.contains("^(") == true || name.contains("_(") == true) {
                                        textElement.setValue("");
                                        run.getContent().add(App.createParagraph(factory, name));
                                    } else {
                                        textElement.setValue(value);
                                    }
                                } else {
                                    textElement.setValue("");
                                }
                            }
                        }
                    } catch (Exception en) {
                        Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, en);
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void createHeaderPart(
            WordprocessingMLPackage wordprocessingMLPackage, String footerContent)
            throws InvalidFormatException {
        HeaderPart headerPart = new HeaderPart();

        headerPart.setJaxbElement(getHdr(footerContent));
        Relationship relationship = wordprocessingMLPackage.getMainDocumentPart()
                .addTargetPart(headerPart);

        SectPr sectPr = objectFactory.createSectPr();

        HeaderReference headerReference = objectFactory.createHeaderReference();
        headerReference.setId(relationship.getId());
        headerReference.setType(HdrFtrRef.DEFAULT);
        sectPr.getEGHdrFtrReferences().add(headerReference);// add header or

        wordprocessingMLPackage.getMainDocumentPart().addObject(sectPr);
    }

    public void createFooterPart(
            WordprocessingMLPackage wordprocessingMLPackage, String footerContent)
            throws InvalidFormatException {
        FooterPart footerPart = new FooterPart();

        footerPart.setJaxbElement(getFtr(footerContent));
        Relationship relationship = wordprocessingMLPackage.getMainDocumentPart()
                .addTargetPart(footerPart);

        SectPr sectPr = objectFactory.createSectPr();

        FooterReference footerReference = objectFactory.createFooterReference();
        footerReference.setId(relationship.getId());
        footerReference.setType(HdrFtrRef.DEFAULT);
        sectPr.getEGHdrFtrReferences().add(footerReference);// add header or

        wordprocessingMLPackage.getMainDocumentPart().addObject(sectPr);
    }

    public Hdr getHdr(String footerContent) {

        Hdr hdr = objectFactory.createHdr();

        hdr.getEGBlockLevelElts().add(getP(footerContent));
        return hdr;

    }

    public Ftr getFtr(String footerContent) {

        Ftr ftr = objectFactory.createFtr();

        ftr.getEGBlockLevelElts().add(getP(footerContent));
        return ftr;

    }

    public P getP(String footerContent) {
        P headerP = objectFactory.createP();
        R run1 = objectFactory.createR();
        Text text = objectFactory.createText();
        text.setValue(footerContent);
        run1.getRunContent().add(text);
        headerP.getParagraphContent().add(run1);
        return headerP;
    }

    public void createFooter(WordprocessingMLPackage wmp) {
        try {
            Relationship relationship = createFooterPart();
            createHeaderReference(wmp, relationship);
        } catch (Exception ex) {

        }
    }

    public Relationship createFooterPart(
            WordprocessingMLPackage wordprocessingMLPackage)
            throws Exception {

        FooterPart footerPart = new FooterPart();
        Relationship rel = wordprocessingMLPackage.getMainDocumentPart()
                .addTargetPart(footerPart);

        // After addTargetPart, so image can be added properly
        footerPart.setJaxbElement(getFtr());

        return rel;
    }

    public void createHeaderReference(
            WordprocessingMLPackage wordprocessingMLPackage,
            Relationship relationship)
            throws InvalidFormatException {
        List<SectionWrapper> sections = wordprocessingMLPackage.getDocumentModel().getSections();

        SectPr sectPr = sections.get(sections.size() - 1).getSectPr();
        // There is always a section wrapper, but it might not contain a sectPr
        if (sectPr == null) {
            sectPr = objectFactory.createSectPr();
            wordprocessingMLPackage.getMainDocumentPart().addObject(sectPr);
            sections.get(sections.size() - 1).setSectPr(sectPr);
        }

        HeaderReference headerReference = objectFactory.createHeaderReference();
        headerReference.setId(relationship.getId());
        headerReference.setType(HdrFtrRef.DEFAULT);
        sectPr.getEGHdrFtrReferences().add(headerReference);// add header or
        // footer references

    }

    public Ftr getFtr() throws Exception {

        Ftr hdr = objectFactory.createFtr();

        hdr.getContent().add("sss");
        return hdr;

    }

    public void replacePlaceholder(WordprocessingMLPackage template, HashMap<String, Object> map) {
        try {
            List<Object> rs = getAllElementFromObject(template.getMainDocumentPart(), P.class);
            ObjectFactory factory = Context.getWmlObjectFactory();
            for (Object r : rs) {
                P run = (P) r;
                List<Object> texts = getAllElementFromObject(run, Text.class);
                for (Object text : texts) {
                    try {
                        Text textElement = (Text) text;

                        if (textElement.getValue() != null) {
                            if (textElement.getValue().startsWith("${") && textElement.getValue().endsWith("}")) {
                                String attribute = textElement.getValue().replace("${", "").replace("}", "");
                                String[] lstAttribute = attribute.split("\\.");
                                Object mainObj = map.get(lstAttribute[0]);
                                for (int i = 1; i < lstAttribute.length; i++) {
                                    mainObj = getAttributeObjByName(mainObj, lstAttribute[i]);
                                }
                                if (mainObj != null) {
                                    String source = (String) mainObj;
                                    source = source.replace("\b", "");
                                    if (source.contains("\n")) {
                                        String[] chars = source.split("\n");
                                        textElement.setValue("");
                                        for (int i = 0; i < chars.length; i++) {
                                            String value = SuperScript.convertToSuperSrcipt(chars[i]);
                                            source = value;
                                            if (source.contains("^(") == true || source.contains("_(") == true) {
                                                run.getContent().remove(run);
                                                run.getContent().add(App.createR(factory, source));
                                            } else {
                                                run.getContent().remove(run);
                                                run.getContent().add(App.createR(factory, source));
                                                if (i < chars.length - 1) {
                                                    run.getContent().add(App.createBr(factory));
                                                }
                                                //textElement.setValue(value);
                                            }
                                        }
                                    } else {
                                        String value = SuperScript.convertToSuperSrcipt((String) mainObj);
                                        if (source.contains("^(") == true || source.contains("_(") == true) {
                                            textElement.setValue("");
                                            run.getContent().remove(run);
                                            run.getContent().add(App.createR(factory, source));
                                        } else {
                                            textElement.setValue(value);
                                        }
                                    }
                                } else {
                                    textElement.setValue("");
                                }
                            }
                        } else {
                            System.out.println("null");
                        }
                    } catch (Exception ex) {
                        log.error(ex.getMessage());
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void replaceTable(String[] placeholders, List<Map<String, String>> textToAdd,
            WordprocessingMLPackage template) throws Docx4JException, JAXBException {
        List<Object> tables = getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);

        // 1. find the table
        Tbl tempTable = getTemplateTable(tables, placeholders[0]);
        List<Object> rows = getAllElementFromObject(tempTable, Tr.class);

        // first row is header, second row is content
        if (rows.size() == 2) {
            // this is our template row
            Tr templateRow = (Tr) rows.get(1);

            for (Map<String, String> replacements : textToAdd) {
                // 2 and 3 are done in this method
                addRowToTable(tempTable, templateRow, replacements);
            }

            // 4. remove the template row
            tempTable.getContent().remove(templateRow);
        }
    }

    public void replaceTable(WordprocessingMLPackage template, int iTable, List lstReplacement) throws Docx4JException, JAXBException {
        List<Object> tables = getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);

        // 1. find the table
        Tbl tempTable = getTemplateTable(tables, iTable);
        List<Object> rows = getAllElementFromObject(tempTable, Tr.class);
        addRowToTable(tempTable, (Tr) rows.get(1), lstReplacement);
    }

    public Tbl getTemplateTable(List<Object> tables, int i) throws Docx4JException, JAXBException {
        return (Tbl) tables.get(i);
    }

    public Tbl getTemplateTable(List<Object> tables, String templateKey) throws Docx4JException, JAXBException {
        for (Iterator<Object> iterator = tables.iterator(); iterator.hasNext();) {
            Object tbl = iterator.next();
            List<?> textElements = getAllElementFromObject(tbl, Text.class);
            for (Object text : textElements) {
                Text textElement = (Text) text;
                if (textElement.getValue() != null && textElement.getValue().equals(templateKey)) {
                    return (Tbl) tbl;
                }
            }
        }
        return null;
    }

    public Object getAttributeObjByName(Object obj, String fieldName) {
        Object value = "";
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            value = field.get(obj);
        } catch (Exception ex) {
            /* bo ghi log loi k quan tam - binhnt53
             Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex);
             */
        }
        return value;
    }

    public String getAttributeOfObjByName(Object obj, String fieldName) {
        String value = "";
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            value = (String) field.get(obj);
        } catch (Exception ex) {
            Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    public void addRowToTable(Tbl reviewtable, Tr templateRow, List replacements) throws IllegalArgumentException {
        if (replacements != null) {
            ObjectFactory factory = Context.getWmlObjectFactory();
            for (int i = 0; i < replacements.size(); i++) {
                Tr workingRow = (Tr) XmlUtils.deepCopy(templateRow);
                List<Object> colElements = getAllElementFromObject(workingRow, Tc.class);
                for (Object ob : colElements) {
                    Tc col = (Tc) ob;
                    List<?> textElements = getAllElementFromObject(col, Text.class);
                    for (Object object : textElements) {
                        Text text = (Text) object;
                        String attribute = text.getValue();
                        if (attribute.equals("index")) {
                            text.setValue(String.valueOf(i + 1));
                        } else {
                            Object obj = replacements.get(i);
                            String value = getAttributeOfObjByName(obj, attribute);
                            if (value != null) {
                                if (value.contains("^(") == true || value.contains("_(") == true) {
                                    text.setValue("");
                                    col.getContent().remove(colElements);
                                    col.getContent().add(App.createParagraph(factory, value));
                                } else {
                                    value = value.replace('\b', ' ');
                                    text.setValue(value);
                                }
                            } else {
                                text.setValue("");
                            }
                        }
                    }
                }
                reviewtable.getContent().add(workingRow);
            }
        }
        reviewtable.getContent().remove(templateRow);
    }

    public void addRowToTable(Tbl reviewtable, Tr templateRow, Map<String, String> replacements) {
        Tr workingRow = (Tr) XmlUtils.deepCopy(templateRow);
        List<?> textElements = getAllElementFromObject(workingRow, Text.class);
        for (Object object : textElements) {
            Text text = (Text) object;
            String replacementValue = (String) replacements.get(text.getValue());
            replacementValue = SuperScript.convertToSuperSrcipt(replacementValue);
            if (replacementValue != null) {
                text.setValue(replacementValue);
            }
        }
        reviewtable.getContent().add(workingRow);
    }

    public void writeDocxToStream(WordprocessingMLPackage template, HttpServletResponse res) throws IOException, Docx4JException {
        Date newDate = new Date();
        String fileName = newDate.getTime() + ".docx";
        res.setContentType("application/vnd.ms-word");

        res.setHeader("Content-Disposition", "attachment; filename=report_" + fileName);
        res.setHeader("Content-Type", "application/vnd.ms-word");

        (new SaveToZipFile(template)).save(res.getOutputStream());
    }

    public byte[] readAttachLabel(Long fileId, byte[] inputData, String fileCode, byte[] barCode, Boolean flagReadLabels, Boolean flagPageNumber) {
        try {
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            // Add label page
            // step 1
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, outStream);

            // Add footer
            if (flagPageNumber) {
                HeaderFooter event = new HeaderFooter("MA HO SO: " + fileCode, barCode);
                writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
                writer.setPageEvent(event);
            } else {
                HeaderFooterNoPageNumber event = new HeaderFooterNoPageNumber("MA HO SO: " + fileCode, barCode);
                writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
                writer.setPageEvent(event);
            }

            // step 3
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            PdfImportedPage page = null;

            PdfReader reader = null;
            if (inputData != null && inputData.length > 0) {
                // Add current pages
                reader = new PdfReader(inputData);
                int pageNumberFound = reader.getNumberOfPages();
                for (int i = 1; i <= pageNumberFound; i++) {
                    page = writer.getImportedPage(reader, i);
                    document.newPage();
                    cb.addTemplate(page, 0, 0);
                }
            }

            // Add label
            PdfReader readerLabel = null;
            if (flagReadLabels) {
                byte[] data = null;
                int pageNumberLabel = 0;
                ResourceBundle rb = ResourceBundle.getBundle("config");
                String dir = rb.getString("directory");
                List<VoAttachs> lstLabels = daoHe.getAttachsByObject(fileId, Constants.OBJECT_TYPE.FILES_LABEL);
                for (int i = 0; i < lstLabels.size(); i++) {
                    try {
                        // Read file
                        Path path = Paths.get(dir + lstLabels.get(i).getAttachPath());
                        data = Files.readAllBytes(path);
                        readerLabel = new PdfReader(data);
                        pageNumberLabel = readerLabel.getNumberOfPages();

                        // Add current pages
                        for (int j = 1; j <= pageNumberLabel; j++) {
                            page = writer.getImportedPage(readerLabel, j);
                            /*
                             float pageSizeY = readerLabel.getPageSize(j).getHeight();
                             float pageSizeX = readerLabel.getPageSize(j).getWidth();

                             if (pageSizeY > 1200f || pageSizeX > 1200f) {
                             Image img = Image.getInstance(page);
                             float rate = 0f;
                             if (pageSizeY > 1200f){
                             rate = (pageSizeY - 1200)/pageSizeY;
                             document.setPageSize(new Rectangle(pageSizeX - pageSizeX*rate,1200));
                             }
                             else
                             {
                             rate = (pageSizeX - 1200)/pageSizeX;
                             document.setPageSize(new Rectangle(1200,pageSizeY - pageSizeY*rate));
                             }
                             img.scalePercent(100 - rate*100);
                             img.scaleAbsolute(50, 50);
                                
                             document.newPage();
                             document.add(img);
                             } else {
                             document.setPageSize(readerLabel.getPageSize(j));
                             document.newPage();
                             cb.addTemplate(page, 0, 0);
                             }
                             */
                            document.setPageSize(readerLabel.getPageSize(j));
                            document.newPage();
                            cb.addTemplate(page, 0, 0);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex.getMessage());
                    }
                    pageNumberLabel = 0;
                    data = null;
                }
            }
            document.close();
            byte[] a = outStream.toByteArray();
            outStream.close();
            if (reader != null) {
                reader.close();
            }
            if (readerLabel != null) {
                readerLabel.close();
            }
            writer.close();
            return a;

        } catch (IOException | DocumentException ex) {
            Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return null;
    }

    public void writePDFToStream(WordprocessingMLPackage template, HttpServletResponse res, Long fileId, String fileCode, byte[] barCode, Boolean flagReadLabels) throws IOException, Docx4JException {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String PATH = rb.getString("ConvertService");
            Date newDate = new Date();
            String fileName = newDate.getTime() + ".docx";
            File f = new File(PATH + File.separatorChar + fileName);
            template.save(f);

            PdfDocxFile input = new PdfDocxFile();
            input.setContent(HandleFile.getByteFromFile(f));
            input.setFilename(f.getName());
            input.setConvertType("docx2pdf");

            Converter_Service service = new Converter_Service();
            com.viettel.convert.service.Converter con = service.getConverterPort();
            PdfDocxFile output = con.convert(input);
            res.setContentType("application/pdf");
            res.setHeader("Content-Disposition", "attachment; filename=report_" + output.getFilename());
            res.setHeader("Content-Type", "application/pdf");

            OutputStream of = res.getOutputStream();
            of.write(readAttachLabel(fileId, output.getContent(), fileCode, barCode, flagReadLabels, true));
            of.flush();
            of.close();
        } catch (IOException ex) {
            Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean writePDFToStreamSign(WordprocessingMLPackage template, HttpServletResponse res, Long fileId, String fileCode, byte[] barCode, String signType, Boolean flagReadLabels, Integer signNumberFile, Boolean flagPageNumber) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String PATH = rb.getString("ConvertService");
            Date newDate = new Date();
            String fileName = newDate.getTime() + ".docx";
            File f = new File(PATH + File.separatorChar + fileName);
            template.save(f);

            PdfDocxFile input = new PdfDocxFile();
            input.setContent(HandleFile.getByteFromFile(f));
            input.setFilename(f.getName());
            input.setConvertType("docx2pdf");

            FileOutputStream fop = null;
            File file;
            ResourceBundle rb1 = ResourceBundle.getBundle("config");
            String PATH1 = rb1.getString("file_sign_link");
            // Get user Info
            BaseDAO baseDao = new BaseDAO();
            List<Roles> lstRole = baseDao.getListRolesByUser();
            String code = "";
            if (lstRole != null && lstRole.size() > 0) {
                for (Integer i = 0; i < lstRole.size(); i++) {
                    code = lstRole.get(i).getRoleCode();
                    if (!"".equals(code) && (code.equals("voffice_vtb") || code.equals(Constants.ROLES.LEAD_MONITOR_ROLE) || code.equals(Constants.ROLES.DIRECTOR_ROLE) || code.equals(Constants.ROLES.LEAD_UNIT))) {
                        if (code.equals("voffice_vtb")) {
                            code = "VT";
                        } else if (code.equals(Constants.ROLES.LEAD_MONITOR_ROLE) || code.equals(Constants.ROLES.DIRECTOR_ROLE) || code.equals(Constants.ROLES.LEAD_UNIT)) {
                            code = "LD";
                        }
                        break;
                    } else {
                        code = "";
                    }
                }
            }
            if ("".equals(code)) {
                Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, "Ký không thành công, có lỗi trong quá trình lưu file trên server: Không xác định RoleCode");
                return false;
            }
            // Create file name
            String fileOut = "";
            if (signNumberFile == 0) {
                fileOut = PATH1 + code + "_" + baseDao.getUserId().toString() + "_" + fileId.toString() + "_" + signType + ".pdf";
            } else {
                fileOut = PATH1 + code + "_" + baseDao.getUserId().toString() + "_" + fileId.toString() + "_" + signType + "_" + signNumberFile + ".pdf";
            }

            // Try conect 5 times
            Converter_Service service = null;
            com.viettel.convert.service.Converter con = null;
            PdfDocxFile output = null;

            Integer count = 1;
            while (count <= 5) {
                try {
                    service = new Converter_Service();
                    con = service.getConverterPort();
                    output = con.convert(input);
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex);
                    // Wait 1s
                    Thread.sleep(1000);
                    count++;
                    if (count > 5) {
                        return false;
                    }
                }
            }

            res.setContentType("application/bak");
            res.setHeader("Content-Disposition", "attachment; filename=" + fileOut);
            res.setHeader("Content-Type", "application/bak");

            file = new File(fileOut);
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            file.createNewFile();

            // get the content in bytes
            if (output == null || output.getContent() == null || output.getContent().length == 0) {
                fop.flush();
                fop.close();
                return false;
            }
            byte[] contentInBytes = readAttachLabel(fileId, output.getContent(), fileCode, barCode, flagReadLabels, flagPageNumber);

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

//            OutputStream of = res.getOutputStream();
//            of.write(new byte[1]);
//            of.flush();
//            of.close();
            return true;
        } catch (Docx4JException | InterruptedException | IOException ex) {
            Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //hieptq update 101214
    public Boolean writePDFToStreamSignDownload(WordprocessingMLPackage template, HttpServletResponse res, Long fileId, String fileCode, byte[] barCode, String signType, Boolean flagReadLabels) throws IOException, Docx4JException {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String PATH = rb.getString("ConvertService");
        Date newDate = new Date();
        String fileName = newDate.getTime() + ".docx";
        File f = new File(PATH + File.separatorChar + fileName);
        template.save(f);
        PdfDocxFile input = new PdfDocxFile();
        input.setContent(HandleFile.getByteFromFile(f));
        input.setFilename(f.getName());
        input.setConvertType("docx2pdf");
        //FileOutputStream fop = null;
        //File file;
        //ResourceBundle rb1 = ResourceBundle.getBundle("config");
        //String PATH1 = rb1.getString("file_sign_link");
        BaseDAO baseDao = new BaseDAO();
        List<Roles> lstRole = baseDao.getListRolesByUser();
        String code = "";
        if (lstRole != null && lstRole.size() > 0) {
            for (Integer i = 0; i < lstRole.size(); i++) {
                code = lstRole.get(i).getRoleCode();
                if (!"".equals(code) && (code.equals("voffice_vtb") || code.equals(Constants.ROLES.LEAD_MONITOR_ROLE) || code.equals(Constants.ROLES.DIRECTOR_ROLE) || code.equals(Constants.ROLES.LEAD_UNIT))) {
                    if (code.equals("voffice_vtb")) {
                        code = "VT";
                    } else if (code.equals(Constants.ROLES.LEAD_MONITOR_ROLE) || code.equals(Constants.ROLES.DIRECTOR_ROLE) || code.equals(Constants.ROLES.LEAD_UNIT)) {
                        code = "LD";
                    }
                    break;
                } else {
                    code = "";
                }
            }
        }
        if ("".equals(code)) {
            Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, "Ký không thành công, có lỗi trong quá trình lưu file trên server: Không xác định RoleCode");
            return false;
        }
        //String fileOut = PATH1 + code + "_" + baseDao.getUserId().toString() + "_" + fileId.toString() + "_" + signType + ".pdf";
        String fileOut = fileCode + "_PDHS" + ".pdf";
        try {
            Converter_Service service = new Converter_Service();
            com.viettel.convert.service.Converter con = service.getConverterPort();
            PdfDocxFile output = con.convert(input);
            res.setContentType("application/pdf");
            res.setHeader("Content-Disposition", "attachment; filename=" + fileOut);
            res.setHeader("Content-Type", "application/pdf");
            OutputStream of = res.getOutputStream();
            of.write(readAttachLabel(fileId, output.getContent(), fileCode, barCode, flagReadLabels, true));
            //of.write(output.getContent());
            of.flush();
            of.close();
        } catch (IOException iOException) {
            Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, iOException.getMessage());
            return false;
        }
        return true;
    }

    public void toHTML(WordprocessingMLPackage template, HttpServletResponse res) throws IOException, Docx4JException, Exception {
        Date newDate = new Date();
        String fileName = newDate.getTime() + ".docx";
        res.setContentType("application/vnd.ms-word");

        res.setHeader("Content-Disposition", "attachment; filename=report_" + fileName);
        res.setHeader("Content-Type", "application/vnd.ms-word");
//        File f = new File(getRequest().getRealPath("/WEB-INF/template/download/")+fileName);
//        template.save(f);
        AbstractHtmlExporter exporter = new HtmlExporterNG2();
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String inputFilePath = rb.getString("tempDirectory");

        HtmlSettings htmlSettings = new HtmlSettings();
        htmlSettings.setImageDirPath(inputFilePath + "_files");
        htmlSettings.setImageTargetUri(inputFilePath + "_files");
        OutputStream outputStream = res.getOutputStream();

        StreamResult result = new StreamResult(outputStream);
        exporter.html(template, result, htmlSettings);

        //(new SaveToZipFile(template)).save(res.getOutputStream());
    }

    public void toHTML(String inputPath, String path) throws IOException, Docx4JException, Exception {
//        File f = new File(getRequest().getRealPath("/WEB-INF/template/download/")+fileName);
//        template.save(f);
        AbstractHtmlExporter exporter = new HtmlExporterNG2();
        WordprocessingMLPackage wmp = null;
        wmp = WordprocessingMLPackage.load(new FileInputStream(inputPath));
        String inputFilePath = path;

        HtmlSettings htmlSettings = new HtmlSettings();
        htmlSettings.setImageDirPath(inputFilePath + "_files");
        htmlSettings.setImageTargetUri(inputFilePath + "_files");
        OutputStream outputStream = new java.io.FileOutputStream(inputFilePath + ".html");

        StreamResult result = new StreamResult(outputStream);
        exporter.html(wmp, result, htmlSettings);

        //(new SaveToZipFile(template)).save(res.getOutputStream());
    }

    public void htmlToDocx(String html, String docXPath) throws InvalidFormatException, JAXBException, Docx4JException {
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

        NumberingDefinitionsPart ndp = new NumberingDefinitionsPart();
        wordMLPackage.getMainDocumentPart().addTargetPart(ndp);
        ndp.unmarshalDefaultNumbering();

        wordMLPackage.getMainDocumentPart().getContent()
                .addAll(XHTMLImporter.convert(html, null, wordMLPackage));

//        System.out.println(XmlUtils.marshaltoString(wordMLPackage
//                .getMainDocumentPart().getJaxbElement(), true, true));
        wordMLPackage.save(new java.io.File(docXPath));
    }

    private PdfDocxFile convert(PdfDocxFile input) {
        Converter_Service service = new Converter_Service();
        com.viettel.convert.service.Converter port = service.getConverterPort();
        return port.convert(input);
    }

    private Relationship createFooterPart() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String replacePh(String base, String placeHolder, String value) {

        if (value == null) {
            value = "";
        }

        if (!base.contains(placeHolder)) {

            return base;

        }

        return base.replaceAll(placeHolder, value);

    }

    private void replaceHeader(HeaderPart headerPart, String placeholder, String newValue) throws JAXBException {

        if (headerPart != null) {

            String xml = XmlUtils.marshaltoString(headerPart.getJaxbElement(), true);

            xml = replacePh(xml, placeholder, newValue);

            Object obj = XmlUtils.unmarshallFromTemplate(xml, null);

//change JaxbElement
            headerPart.setJaxbElement((org.docx4j.wml.Hdr) obj);

        }

    }

    private void replaceFooter(FooterPart footerPart, String placeholder, String newValue) throws JAXBException {

        if (footerPart != null) {

            String xml = XmlUtils.marshaltoString(footerPart.getJaxbElement(), true);

            xml = replacePh(xml, placeholder, newValue);

            Object obj = XmlUtils.unmarshallFromTemplate(xml, null);

//change JaxbElement
            footerPart.setJaxbElement((org.docx4j.wml.Ftr) obj);

        }

    }

    /**
     * The different epochs.
     */
    public static final String[] EPOCH
            = {"Forties", "Fifties", "Sixties", "Seventies", "Eighties",
                "Nineties", "Twenty-first Century"};
    /**
     * The fonts for the title.
     */
    public static final Font[] FONT = new Font[4];

    static {
        FONT[0] = new Font(FontFamily.HELVETICA, 24);
        FONT[1] = new Font(FontFamily.HELVETICA, 18);
        FONT[2] = new Font(FontFamily.HELVETICA, 14);
        FONT[3] = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
    }

    /**
     * Inner class to add a header and a footer.
     */
    class HeaderFooter extends PdfPageEventHelper {

        /**
         * Alternating phrase for the header.
         */
        Phrase[] header = new Phrase[2];
        byte[] imageBarCode;
        /**
         * Current page number (will be reset for every chapter).
         */
        int pagenumber;

        private HeaderFooter(String fileCode, byte[] barCode) {
            header[1] = new Phrase(fileCode);
            imageBarCode = barCode;
        }

        /**
         * Initialize one of the headers.
         *
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
         * com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onOpenDocument(PdfWriter writer, Document document) {
            //header[1] = new Phrase("Movie history");
        }

        /**
         * Initialize one of the headers, based on the chapter title; reset the
         * page number.
         *
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onChapter(
         * com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document, float,
         * com.itextpdf.text.Paragraph)
         */
        public void onChapter(PdfWriter writer, Document document,
                float paragraphPosition, Paragraph title) {
            header[1] = new Phrase(title.getContent());
            pagenumber = 1;
        }

        /**
         * Increase the page number.
         *
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onStartPage(
         * com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onStartPage(PdfWriter writer, Document document) {
            pagenumber++;
        }

        /**
         * Adds the header and the footer.
         *
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
         * com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                Rectangle rect = writer.getBoxSize("art");
                /*
                 switch(writer.getPageNumber()) {
                 case 0:
                 ColumnText.showTextAligned(writer.getDirectContent(),
                 Element.ALIGN_RIGHT, header[0],
                 rect.getRight(), rect.getTop(), 0);
                 break;
                 case 1:
                 ColumnText.showTextAligned(writer.getDirectContent(),
                 Element.ALIGN_LEFT, header[1],
                 rect.getLeft(), rect.getBottom() - 18, 0);
                 break;
                 }

                 ColumnText.showTextAligned(writer.getDirectContent(),
                 Element.ALIGN_CENTER, new Phrase(String.format("Trang %d", pagenumber)),
                 (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
                 */

                ColumnText.showTextAligned(writer.getDirectContent(),
                        Element.ALIGN_LEFT, header[1],
                        rect.getLeft(), rect.getBottom() - 18, 0);

                ColumnText.showTextAligned(writer.getDirectContent(),
                        Element.ALIGN_CENTER, new Phrase(String.format("Trang %d", pagenumber)),
                        rect.getRight() - 25, rect.getBottom() - 18, 0);
            } catch (Exception ex) {
                Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                // Add Image
                if (imageBarCode != null && imageBarCode.length > 0) {
                    PdfPTable foot = new PdfPTable(1);
                    //Rectangle page = document.getPageSize();
                    Image jpg = Image.getInstance(imageBarCode);
                    jpg.setAlignment(Image.ALIGN_CENTER);
                    jpg.scaleAbsolute(60, 60);
                    jpg.setBorder(0);
                    jpg.setWidthPercentage(200);

                    foot.addCell(jpg);
                    foot.setTotalWidth(60);
                    foot.writeSelectedRows(0, -1, 0, document.getPageSize().getHeight(),
                            writer.getDirectContent());
                    /*
                     PdfPCell cell = new PdfPCell();
                     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                     cell.setBorder(0);
                     foot.addCell(cell);

                     foot.addCell("This place is to write your footer text ");
                     foot.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());

                     foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(),
                     writer.getDirectContent());
                     */
                }
            } catch (BadElementException | IOException ex) {
                Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // hieptq update 131015
    public String writePDFToStreamSignPlugin(WordprocessingMLPackage template, HttpServletResponse res, Long fileId, String fileCode, byte[] barCode, String signType, Boolean flagReadLabels, Integer signNumberFile, Boolean flagPageNumber) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String PATH = rb.getString("ConvertService");
            Date newDate = new Date();
            String fileName = newDate.getTime() + ".docx";
            File f = new File(PATH + File.separatorChar + fileName);
            template.save(f);

            PdfDocxFile input = new PdfDocxFile();
            input.setContent(HandleFile.getByteFromFile(f));
            input.setFilename(f.getName());
            input.setConvertType("docx2pdf");

            FileOutputStream fop = null;
            File file;
            ResourceBundle rb1 = ResourceBundle.getBundle("config");
            String PATH1 = rb1.getString("file_sign_link");
            // Get user Info
            BaseDAO baseDao = new BaseDAO();
            List<Roles> lstRole = baseDao.getListRolesByUser();
            String code = "";
            if (lstRole != null && lstRole.size() > 0) {
                for (Integer i = 0; i < lstRole.size(); i++) {
                    code = lstRole.get(i).getRoleCode();
                    if (!"".equals(code) && (code.equals("voffice_vtb") || code.equals(Constants.ROLES.LEAD_MONITOR_ROLE) || code.equals(Constants.ROLES.DIRECTOR_ROLE) || code.equals(Constants.ROLES.LEAD_UNIT))) {
                        if (code.equals("voffice_vtb")) {
                            code = "VT";
                        } else if (code.equals(Constants.ROLES.LEAD_MONITOR_ROLE) || code.equals(Constants.ROLES.DIRECTOR_ROLE) || code.equals(Constants.ROLES.LEAD_UNIT)) {
                            code = "LD";
                        }
                        break;
                    } else {
                        code = "";
                    }
                }
            }
            if ("".equals(code)) {
                Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, "Ký không thành công, có lỗi trong quá trình lưu file trên server: Không xác định RoleCode");
                return "false";
            }
            // Create file name
            String fileOut = "";
            if (signNumberFile == 0) {
                fileOut = PATH1 + code + "_" + baseDao.getUserId().toString() + "_" + fileId.toString() + "_" + signType + ".pdf";
            } else {
                fileOut = PATH1 + code + "_" + baseDao.getUserId().toString() + "_" + fileId.toString() + "_" + signType + "_" + signNumberFile + ".pdf";
            }

            // Try conect 5 times
            Converter_Service service = null;
            com.viettel.convert.service.Converter con = null;
            PdfDocxFile output = null;

            Integer count = 1;
            while (count <= 5) {
                try {
                    service = new Converter_Service();
                    con = service.getConverterPort();
                    output = con.convert(input);
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex);
                    // Wait 1s
                    Thread.sleep(1000);
                    count++;
                    if (count > 5) {
                        return "false";
                    }
                }
            }

            res.setContentType("application/bak");
            res.setHeader("Content-Disposition", "attachment; filename=" + fileOut);
            res.setHeader("Content-Type", "application/bak");

            file = new File(fileOut);
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            file.createNewFile();

            // get the content in bytes
            if (output == null || output.getContent() == null || output.getContent().length == 0) {
                fop.flush();
                fop.close();
                return "false";
            }
            byte[] contentInBytes = readAttachLabel(fileId, output.getContent(), fileCode, barCode, flagReadLabels, flagPageNumber);
            fop.write(contentInBytes);
           //fop.write(output.getContent());
            fop.flush();
            fop.close();
            return file.getPath() + ";" + file.getName();
        } catch (Docx4JException | InterruptedException | IOException ex) {
            Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex);
            return "false";
        }
    }

    /**
     * Inner class to add a header and a footer.
     */
    class HeaderFooterNoPageNumber extends PdfPageEventHelper {

        /**
         * Alternating phrase for the header.
         */
        Phrase[] header = new Phrase[2];
        byte[] imageBarCode;
        /**
         * Current page number (will be reset for every chapter).
         */
        int pagenumber;

        private HeaderFooterNoPageNumber(String fileCode, byte[] barCode) {
            header[1] = new Phrase(fileCode);
            imageBarCode = barCode;
        }

        /**
         * Initialize one of the headers.
         *
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
         * com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onOpenDocument(PdfWriter writer, Document document) {
            //header[1] = new Phrase("Movie history");
        }

        /**
         * Initialize one of the headers, based on the chapter title; reset the
         * page number.
         *
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onChapter(
         * com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document, float,
         * com.itextpdf.text.Paragraph)
         */
        public void onChapter(PdfWriter writer, Document document,
                float paragraphPosition, Paragraph title) {
            header[1] = new Phrase(title.getContent());
            pagenumber = 1;
        }

        /**
         * Increase the page number.
         *
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onStartPage(
         * com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onStartPage(PdfWriter writer, Document document) {
            pagenumber++;
        }

        /**
         * Adds the header and the footer.
         *
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
         * com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                Rectangle rect = writer.getBoxSize("art");
                /*
                 switch(writer.getPageNumber()) {
                 case 0:
                 ColumnText.showTextAligned(writer.getDirectContent(),
                 Element.ALIGN_RIGHT, header[0],
                 rect.getRight(), rect.getTop(), 0);
                 break;
                 case 1:
                 ColumnText.showTextAligned(writer.getDirectContent(),
                 Element.ALIGN_LEFT, header[1],
                 rect.getLeft(), rect.getBottom() - 18, 0);
                 break;
                 }

                 ColumnText.showTextAligned(writer.getDirectContent(),
                 Element.ALIGN_CENTER, new Phrase(String.format("Trang %d", pagenumber)),
                 (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
                 */

                ColumnText.showTextAligned(writer.getDirectContent(),
                        Element.ALIGN_LEFT, header[1],
                        rect.getLeft(), rect.getBottom() - 18, 0);
                /*
                 ColumnText.showTextAligned(writer.getDirectContent(),
                 Element.ALIGN_CENTER, new Phrase(String.format("Trang %d", pagenumber)),
                 rect.getRight() - 25, rect.getBottom() - 18, 0);
                 */
            } catch (Exception ex) {
                Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                // Add Image
                if (imageBarCode != null && imageBarCode.length > 0) {
                    PdfPTable foot = new PdfPTable(1);
                    //Rectangle page = document.getPageSize();
                    Image jpg = Image.getInstance(imageBarCode);
                    jpg.setAlignment(Image.ALIGN_CENTER);
                    jpg.scaleAbsolute(60, 60);
                    jpg.setBorder(0);
                    jpg.setWidthPercentage(200);

                    foot.addCell(jpg);
                    foot.setTotalWidth(60);
                    foot.writeSelectedRows(0, -1, 0, document.getPageSize().getHeight(),
                            writer.getDirectContent());
                    /*
                     PdfPCell cell = new PdfPCell();
                     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                     cell.setBorder(0);
                     foot.addCell(cell);

                     foot.addCell("This place is to write your footer text ");
                     foot.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());

                     foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(),
                     writer.getDirectContent());
                     */
                }
            } catch (BadElementException | IOException ex) {
                Logger.getLogger(WordExportUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
