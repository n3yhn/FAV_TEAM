/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAO;

import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAOHibernate.VoAttachsDAOHE;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFImageWriter;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.docx4j.convert.out.Containerization;
import org.docx4j.convert.out.html.AbstractHtmlExporter;
import org.docx4j.convert.out.html.HtmlExporterNG2;
import org.docx4j.convert.out.html.SdtWriter;
import org.docx4j.convert.out.html.TagClass;
import org.docx4j.convert.out.html.TagSingleBox;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author dungnt78
 */
public class ViewFileDAO extends BaseDAO {
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ViewFileDAO.class);
    private FileInputStream inputStream;
    String htmloutbody;
    
    public String prepare() {
        return "view";
    }

    /**
     * mo file trong voattach
     *
     * @return
     */
    public String openFile() {
        String[] fileTypes = {"jpg", "gif", "img", "png"};
        String PATH = getRequest().getRealPath("/") + "share/viewFile/";
        String linkFile = "";
        String htmloutbody = "";
        String fileType = "";
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String dir = rb.getString("directory");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            Long id = Long.parseLong(getRequest().getParameter("attachId"));

            String idSession = (String) getRequest().getSession().getAttribute("idSession");

            if (idSession != null) {
                String[] ids = idSession.split(";");
                for (int i = 0; i < ids.length; i++) {
                    if (ids[i].equals(String.valueOf(id))) {
                        try {
                            VoAttachs bo = daoHe.findById(id, false);
                            linkFile = bo.getAttachPath();
                            linkFile = dir + linkFile;
                            File file = new File(linkFile);
                            if (file.getName().toLowerCase().endsWith("docx")) {
                                convertFiletoHTML(file);
                                File in = new File(PATH + file.getName().replace("docx", "html"));
                                Document doc = Jsoup.parse(in, null);
                                htmloutbody = doc.body().html();
                                getRequest().setAttribute("docContent", htmloutbody);
                                fileType = "docx";
                            } else if (file.getName().toLowerCase().endsWith("pdf")) {
                                int pageNum = convertPDFFiletoImage(file);
                                if (pageNum != 0) {
                                    String imagename = "";
                                    for (int j = 1; j <= pageNum; j++) {
                                        String image = "share/viewFile/ImagePDF/" + file.getName().substring(file.getName().lastIndexOf("/") + 1) + j + ".jpg";
                                        htmloutbody += "<img src = '" + image + "'/><br/>";
                                        imagename = getRequest().getRealPath("/") + "share/viewFile/ImagePDF/" + file.getName().substring(file.getName().lastIndexOf("/") + 1) + j + ".jpg";

                                    }
                                    getRequest().setAttribute("docContent", htmloutbody);
                                    BufferedImage bimg = ImageIO.read(new File(imagename));
                                    int width = bimg.getWidth();
                                    getRequest().setAttribute("width", width);
                                    fileType = "pdf";
                                }
                            } else if (file.getName().toLowerCase().endsWith("xls")) {
                                htmloutbody = convertXlsToHtml(file);
                                getRequest().setAttribute("docContent", htmloutbody);
                                fileType = "xls";
                            } else if (file.getName().toLowerCase().endsWith("xlsx")) {
                                htmloutbody = convertXlsxToHtml(file);
                                getRequest().setAttribute("docContent", htmloutbody);
                                fileType = "xlsx";
                            } else if (file.getName().toLowerCase().endsWith("ppt")) {
                                htmloutbody = convertPptToHtml(file);
                                getRequest().setAttribute("docContent", htmloutbody);
                                fileType = "ppt";
                                if (htmloutbody != null) {
                                    BufferedImage bimg = ImageIO.read(new File(PATH + "ppt/" + file.getName().toLowerCase().replace(".ppt", "1.png")));
                                    int width = bimg.getWidth();
                                    getRequest().setAttribute("width", width);
                                }
                            } else if (file.getName().toLowerCase().endsWith("pptx")) {
                                htmloutbody = convertPptxToHtml(file);
                                getRequest().setAttribute("docContent", htmloutbody);
                                fileType = "pptx";
                                if (htmloutbody != null) {
                                    BufferedImage bimg = ImageIO.read(new File(PATH + "pptx/" + file.getName().toLowerCase().replace(".pptx", "1.png")));
                                    int width = bimg.getWidth();
                                    getRequest().setAttribute("width", width);
                                }
                            } else {
                                for (String s : fileTypes) {
                                    if (file.getName().toLowerCase().endsWith(s)) {
                                        htmloutbody = "<img src = 'uploadiframe!openFile.do?attachId=" + id + "'/>";
                                        getRequest().setAttribute("docContent", htmloutbody);
                                        BufferedImage bimg = ImageIO.read(file);
                                        int width = bimg.getWidth();
                                        getRequest().setAttribute("width", width);
                                        fileType = "image";
                                    }
                                }
                            }

                        } catch (Exception ex) {
                            log.error(ex.getMessage());
                        }
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        getRequest().setAttribute("fileType", fileType);
        return "view";
    }

    /**
     * convert file docx to html
     *
     * @param input file docx
     * @return
     */
    private boolean convertFiletoHTML(File input) {
        String PATH = getRequest().getRealPath("/") + "share/viewFile/";
        boolean result = false;
        try {
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(input);
            AbstractHtmlExporter exporter = new HtmlExporterNG2();
            AbstractHtmlExporter.HtmlSettings htmlSettings = new AbstractHtmlExporter.HtmlSettings();
            htmlSettings.setWmlPackage(wordMLPackage);
            htmlSettings.setImageDirPath(PATH + input.getName() + "_files");
            htmlSettings.setImageTargetUri("share/viewFile/" + input.getName().substring(input.getName().lastIndexOf("/") + 1)
                    + "_files");
            SdtWriter.registerTagHandler("@class", new TagClass());
            SdtWriter.registerTagHandler(Containerization.TAG_BORDERS, new TagSingleBox());
            SdtWriter.registerTagHandler(Containerization.TAG_SHADING, new TagSingleBox());
            SdtWriter.registerTagHandler(Containerization.TAG_RPR, new TagSingleBox());
            OutputStream os = new java.io.FileOutputStream(PATH + input.getName().replace("docx", "html"));
            javax.xml.transform.stream.StreamResult sresult = new javax.xml.transform.stream.StreamResult(os);
            // OK, do it
            exporter.html(wordMLPackage, sresult, htmlSettings);
            result = true;
            return result;
        } catch (Exception ex) {
            return result;
        }

    }

    public String getHtmloutbody() {
        return htmloutbody;
    }

    public void setHtmloutbody(String htmloutbody) {
        this.htmloutbody = htmloutbody;
    }

    private int convertPDFFiletoImage(File file) {
        int num = 0;
        try {
            File folder = new File(getRequest().getRealPath("/") + "share/viewFile/ImagePDF/");
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String outputPrefix = getRequest().getRealPath("/") + "share/viewFile/ImagePDF/" + file.getName().substring(file.getName().lastIndexOf("/") + 1);

            String password = "";
            String imageFormat = "jpg";
            int startPage = 1;
            PDDocument document = PDDocument.load(file);
            PDFImageWriter imageWriter = new PDFImageWriter();
            imageWriter.writeImage(document, imageFormat, password, startPage, document.getNumberOfPages(), outputPrefix);
            num = document.getNumberOfPages();
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        return num;
    }

    private String convertXlsToHtml(File file) {
        try {
//            ClassLoader classloader =
//                    org.apache.poi.poifs.filesystem.POIFSFileSystem.class.getClassLoader();
//            URL res = classloader.getResource(
//                    "org/apache/poi/poifs/filesystem/POIFSFileSystem.class");
//            String paths = res.getPath();
//            System.out.println("Core POI came from " + paths);

            String path = getRequest().getRealPath("/") + "share/viewFile/xls/";
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            org.w3c.dom.Document doc = ExcelToHtmlConverter.process(file);

            FileWriter out = new FileWriter(path + file.getName().toLowerCase().replace("xls", "html"));
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(out);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            serializer.transform(domSource, streamResult);
            out.close();
            File in = new File(path + file.getName().toLowerCase().replace("xls", "html"));
            Document html = Jsoup.parse(in, null);
            html.body().append(html.getElementsByTag("style").outerHtml());
            return html.body().html();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    private String convertXlsxToHtml(File file) {
        try {
            String path = getRequest().getRealPath("/") + "share/viewFile/xlsx/";
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            ToHTML toHtml = ToHTML.create(file.getAbsolutePath(), new PrintWriter(new FileWriter(path + file.getName().toLowerCase().replace("xlsx", "html"))));

            toHtml.setCompleteHTML(true);
            toHtml.printPage();
            File in = new File(path + file.getName().toLowerCase().replace("xlsx", "html"));
            Document html = Jsoup.parse(in, null);
            return html.body().html();

        } catch (IOException ex) {
            log.error(ex.getMessage()); 
            return null;
        }


    }

    private String convertPptToHtml(File file) {
        FileInputStream is = null;
        String output = "";
        try {
            String path = getRequest().getRealPath("/") + "share/viewFile/ppt/";
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            is = new FileInputStream(file);
            SlideShow ppt = new SlideShow(is);
            is.close();
            Dimension pgsize = ppt.getPageSize();
            Slide[] slide = ppt.getSlides();
            for (int i = 0; i < slide.length; i++) {
                BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
                slide[i].draw(graphics);
                FileOutputStream out = new FileOutputStream(path + file.getName().toLowerCase().replace(".ppt", (i + 1) + ".png"));
                javax.imageio.ImageIO.write(img, "png", out);
                out.close();
                String image = "share/viewFile/ppt/" + file.getName().toLowerCase().replace(".ppt", (i + 1) + ".png");
                output += "<img src = '" + image + "'/><br/>";
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            try {
                is.close();
                return output;
            } catch (IOException ex) {
                log.error(ex.getMessage());
                return null;
            }
        }
    }

    private String convertPptxToHtml(File file) {
        FileInputStream is = null;
        String output = "";
        try {
            String path = getRequest().getRealPath("/") + "share/viewFile/pptx/";
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            is = new FileInputStream(file);
            XMLSlideShow pptx = new XMLSlideShow(is);
            Dimension pgsize = pptx.getPageSize();
            XSLFSlide[] slides = pptx.getSlides();
            for (int i = 0; i < slides.length; i++) {
                BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
                slides[i].draw(graphics);
                FileOutputStream out = new FileOutputStream(path + file.getName().toLowerCase().replace(".pptx", (i + 1) + ".png"));
                javax.imageio.ImageIO.write(img, "png", out);
                out.close();
                String image = "share/viewFile/pptx/" + file.getName().toLowerCase().replace(".pptx", (i + 1) + ".png");
                output += "<img src = '" + image + "'/><br/>";
            }
            is.close();
            return output;
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
