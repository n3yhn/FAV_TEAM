package com.viettel.utils;

import java.math.BigInteger;
import org.docx4j.wml.Br;
import org.docx4j.wml.CTVerticalAlignRun;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STVerticalAlignRun;
import org.docx4j.wml.Style;
import org.docx4j.wml.Text;
import org.docx4j.wml.U;
import org.docx4j.wml.UnderlineEnumeration;

/**
 * Demonstrate exponent expression with docx4j
 *
 * @author binhnt53
 *
 */
public class App {

    //private static final String OUTPUT_DOCX_FILE_PATH = "helloworl1.docx";
//    public static void main(String[] args) throws Docx4JException {
//        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
//        ObjectFactory factory = Context.getWmlObjectFactory();
//
//        /* Non-issues-related thinks */
//        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", "binhnt53 love maria!");
//        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subtitle", "Văn bản thực nghiệm lỗi (Both upper and lower?)");
//
//        /**
//         * Demo paragraph with upper
//         */
//        wordMLPackage.getMainDocumentPart().addObject(createParagraph(factory));
//
//        wordMLPackage.save(new File(OUTPUT_DOCX_FILE_PATH));
//    }
    public static P createParagraph(ObjectFactory factory, String content) {
        content = content.replace('\b', ' ');
        P paragraph = factory.createP();
        String n;
//        String sup = "";
//        String sub = "";
//        int i = 0;
//        int ib = 0;
//        int ip = 0;
        int supindex;
        int subindex;
        int lastindex;
        int firstindex;
//        int nindex = 0;
        supindex = content.indexOf("^(");
        int type;//  = 0 is normal; = 1 is sup; = 2 is sub
        String value;
        while (!content.isEmpty()) {
            type = 0;
            firstindex = 0;
            supindex = content.indexOf("^(");
            subindex = content.indexOf("_(");
            if (supindex == 0) {
                type = 1;
                firstindex = 2;
                lastindex = content.indexOf("^)");
            } else if (subindex == 0) {
                type = 2;
                firstindex = 2;
                lastindex = content.indexOf("_)");
            } else {
                lastindex = content.length();
                if (supindex >= 0) {
                    lastindex = supindex;
                }

                if (subindex >= 0 && subindex < lastindex) {
                    lastindex = subindex;
                }
            }
            if (lastindex < 0) {
                lastindex = content.length();
            }
            value = content.substring(firstindex, lastindex);
            value = value.replaceAll("\\^\\(", "");
            value = value.replaceAll("\\^\\)", "");
            value = value.replaceAll("\\_\\(", "");
            value = value.replaceAll("\\_\\)", "");
            if (value != null && !value.isEmpty()) {
                if (type == 1) {
                    paragraph.getContent().add(createSuperscriptText(factory, value));
                    lastindex++;
                } else if (type == 2) {
                    lastindex++;
                    paragraph.getContent().add(createSubscriptText(factory, value));
                } else {
                    paragraph.getContent().add(createNormalText(factory, value));
                    lastindex -= 1;
                }
            }
            if (lastindex < content.length()) {
                content = content.substring(lastindex + 1);
            } else {
                content = "";
            }
        }
        return paragraph;
        //loc tu dau den cuoi chuoi
        /*
         while (i < content.length() - 1) {
         if (content.substring(nindex).contains("_(") == false && content.substring(nindex).contains("^(") == false) {
         paragraph.getContent().add(createNormalText(factory, content.substring(nindex)));
         break;
         } else {
         //kiem tra chuoi co chua ki tu danh dau k
         if (content.substring(nindex).contains("^(")) {
         //kiem tra ki tu thu i co phai la ^ k
         if (content.charAt(i) == '^' && content.charAt(i + 1) == '(') {
         n = content.substring(nindex, i);
         paragraph.getContent().add(createNormalText(factory, n));
         n = "";
         ib = i;
         supindex = ib + 2;
         for (int j = supindex; j < content.length(); j++) {
         if (j == content.length() - 1) {
         sup = content.substring(supindex, j);
         paragraph.getContent().add(createSuperscriptText(factory, sup));
         sup = "";
         ib = j + 2;
         nindex = ib;
         break;
         } else {
         if (content.charAt(j) == '^' && content.charAt(j + 1) == ')') {
         sup = content.substring(supindex, j);
         paragraph.getContent().add(createSuperscriptText(factory, sup));
         sup = "";
         nindex = j + 2;
         break;
         }
         }
         ib++;
         }
         }
         } else {
         //kiem tra ki tu thu i co phai la ^ k
         if (content.substring(nindex).contains("_(")) {
         if (content.charAt(i) == '_' && content.charAt(i + 1) == '(') {
         n = content.substring(nindex, i);
         paragraph.getContent().add(createNormalText(factory, n));
         n = "";
         ip = i;
         supindex = ip + 2;
         for (int j = supindex; j < content.length(); j++) {
         if (j == content.length() - 1) {
         sub = content.substring(supindex, j);
         paragraph.getContent().add(createSubscriptText(factory, sub));
         sub = "";
         ip = j + 2;
         nindex = ip;
         break;
         } else {
         if (content.charAt(j) == '_' && content.charAt(j + 1) == ')') {
         sub = content.substring(supindex, j);
         paragraph.getContent().add(createSubscriptText(factory, sub));
         sub = "";
         nindex = j + 2;
         break;
         }
         }
         ip++;
         }
         }
         }
         }
         }
         i++;
         }
        
         return paragraph;
         */
    }

    /**
     * Normal text
     */
    private static R createNormalText(ObjectFactory factory, String message) {
        Text normalText = factory.createText();
        normalText.setValue(message);
        normalText.setSpace("preserve");
        R normalRun = factory.createR();
        RPr normalRunProperties = factory.createRPr();
        changeFontToTimeNewRoman(normalRunProperties);
        normalRun.getContent().add(normalText);
        setFontSize(normalRunProperties, "26");
        normalRun.setRPr(normalRunProperties);
        return normalRun;
    }

    /**
     * Superscript text
     */
    private static R createSuperscriptText(ObjectFactory factory, String message) {
        Text superscriptText = factory.createText();
        superscriptText.setValue(message);
        superscriptText.setSpace("preserve");
        R superscriptRun = factory.createR();
        RPr superscriptRunProperties = factory.createRPr();
        superscriptRun.getContent().add(superscriptText);
        setFontSize(superscriptRunProperties, "26");
        changeFontToTimeNewRoman(superscriptRunProperties);
        addSuperScript(superscriptRunProperties);
        superscriptRun.setRPr(superscriptRunProperties);
        return superscriptRun;
    }

    /**
     * Subscript text
     */
    private static R createSubscriptText(ObjectFactory factory, String message) {
        Text superscriptText = factory.createText();
        superscriptText.setValue(message);
        superscriptText.setSpace("preserve");
        R subscriptRun = factory.createR();
        RPr subscriptRunProperties = factory.createRPr();
        subscriptRun.getContent().add(superscriptText);
        setFontSize(subscriptRunProperties, "26");
        changeFontToTimeNewRoman(subscriptRunProperties);
        addSubScript(subscriptRunProperties);
        subscriptRun.setRPr(subscriptRunProperties);
        return subscriptRun;
    }

    /**
     * In this method we're going to add the font size information to the run
     * properties. First we'll create a half-point measurement. Then we'll set
     * the fontSize as the value of this measurement. Finally we'll set the
     * non-complex and complex script font sizes, sz and szCs respectively.
     */
    private static void setFontSize(RPr runProperties, String fontSize) {
        HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger(fontSize));
        runProperties.setSz(size);
        runProperties.setSzCs(size);
    }

    private static void addSuperScript(RPr runProperties) {
        CTVerticalAlignRun vert = new CTVerticalAlignRun();
        vert.setVal(STVerticalAlignRun.SUPERSCRIPT);
        runProperties.setVertAlign(vert);
    }

    private static void addSubScript(RPr runProperties) {
        CTVerticalAlignRun vert = new CTVerticalAlignRun();
        vert.setVal(STVerticalAlignRun.SUBSCRIPT);
        runProperties.setVertAlign(vert);
    }

    private static void changeFontToArial(RPr runProperties) {
        RFonts runFont = new RFonts();
        runFont.setAscii("Arial");
        runFont.setHAnsi("Arial");
        runProperties.setRFonts(runFont);
    }

    private static void changeFontToTimeNewRoman(RPr runProperties) {
        RFonts runFont = new RFonts();
        runFont.setAscii("Times New Roman");
        runFont.setHAnsi("Times New Roman");
        runProperties.setRFonts(runFont);
    }

    private static void addUnderline(RPr runProperties) {
        U underline = new U();
        underline.setVal(UnderlineEnumeration.SINGLE);
        runProperties.setU(underline);
    }

    private static void removeBoldStyle(RPr runProperties) {
        runProperties.getB().setVal(false);
    }

    private static void removeThemeFontInformation(RPr runProperties) {
        runProperties.getRFonts().setAsciiTheme(null);
        runProperties.getRFonts().setHAnsiTheme(null);
    }

    private static void changeFontSize(RPr runProperties, int fontSize) {
        HpsMeasure size = new HpsMeasure();
        size.setVal(BigInteger.valueOf(fontSize));
        runProperties.setSz(size);
    }

    private static RPr getRunPropertiesAndRemoveThemeInfo(Style style) {
        // We only want to change some settings, so we get the existing run
        // properties from the style.
        RPr rpr = style.getRPr();
        removeThemeFontInformation(rpr);
        return rpr;
    }

    private static void alterHeading2Style(Style style) {
        RPr rpr = getRunPropertiesAndRemoveThemeInfo(style);
        removeBoldStyle(rpr);
        changeFontSize(rpr, 24);
        addUnderline(rpr);
    }

    public static Br createBr(ObjectFactory factory) {
        Br br = factory.createBr(); // this Br element is used break the current and go for next line
        return br;
    }

    public static R createR(ObjectFactory factory, String content) {
        R paragraph = factory.createR();
        String n;
        String sup;
        String sub;
        int i = 0;
        int ib;
        int ip;
        int supindex;
//        int subindex = 0;
        int nindex = 0;
        //loc tu dau den cuoi chuoi
        while (i < content.length() - 1) {
            if (content.substring(nindex).contains("_(") == false && content.substring(nindex).contains("^(") == false) {
                paragraph.getContent().add(createNormalText(factory, content.substring(nindex)));
                break;
            } else {
                //kiem tra chuoi co chua ki tu danh dau k
                if (content.substring(nindex).contains("^(")) {
                    //kiem tra ki tu thu i co phai la ^ k
                    if (content.charAt(i) == '^' && content.charAt(i + 1) == '(') {
                        n = content.substring(nindex, i);
                        paragraph.getContent().add(createNormalText(factory, n));
//                        n = "";
                        ib = i;
                        supindex = ib + 2;
                        for (int j = supindex; j < content.length(); j++) {
                            if (j == content.length() - 1) {
                                sup = content.substring(supindex, j);
                                paragraph.getContent().add(createSuperscriptText(factory, sup));
//                                sup = "";
                                ib = j + 2;
                                nindex = ib;
                                break;
                            } else {
                                if (content.charAt(j) == '^' && content.charAt(j + 1) == ')') {
                                    sup = content.substring(supindex, j);
                                    paragraph.getContent().add(createSuperscriptText(factory, sup));
//                                    sup = "";
                                    nindex = j + 2;
                                    break;
                                }
                            }
                            ib++;
                        }
                    }
                } else {
                    //kiem tra ki tu thu i co phai la ^ k
                    if (content.substring(nindex).contains("_(")) {
                        if (content.charAt(i) == '_' && content.charAt(i + 1) == '(') {
                            n = content.substring(nindex, i);
                            paragraph.getContent().add(createNormalText(factory, n));
//                            n = "";
                            ip = i;
                            supindex = ip + 2;
                            for (int j = supindex; j < content.length(); j++) {
                                if (j == content.length() - 1) {
                                    sub = content.substring(supindex, j);
                                    paragraph.getContent().add(createSubscriptText(factory, sub));
//                                    sub = "";
                                    ip = j + 2;
                                    nindex = ip;
                                    break;
                                } else {
                                    if (content.charAt(j) == '_' && content.charAt(j + 1) == ')') {
                                        sub = content.substring(supindex, j);
                                        paragraph.getContent().add(createSubscriptText(factory, sub));
//                                        sub = "";
                                        nindex = j + 2;
                                        break;
                                    }
                                }
                                ip++;
                            }
                        }
                    }
                }
            }
            i++;
        }
        return paragraph;
    }
}
