/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAO;

import com.viettel.common.util.LogUtil;
import java.lang.reflect.Method;
import java.util.Comparator;

/**
 *
 * @author sytv
 */
public class VoComparator<T> implements Comparator<T> {

    //private Class<T> type;
    private String methodName;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(VoComparator.class);

    public VoComparator(String getMethodName) {
        //this.type = type;
        this.methodName = getMethodName;
    }

    @Override
    public int compare(T o1, T o2) {
        try {
            Class cls1 = o1.getClass();
            Class cls2 = o2.getClass();
            Method getMethod;
            String getMethodName = "get" + UpcaseFirst(methodName);
            getMethod = cls1.getMethod(getMethodName);
            String getName1 = getMethod.invoke(o1).toString();
            getMethod = cls2.getMethod(getMethodName);
            String getName2 = getMethod.invoke(o2).toString();
            getName1 = removeVienameseCharacter(getName1).toUpperCase();
            getName2 = removeVienameseCharacter(getName2).toUpperCase();
            return getName1.compareTo(getName2);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String removeVienameseCharacter(String source) {
        char[] lowerVietnameseChar = {'à', 'á', 'ạ', 'ả', 'â', 'ầ', 'ấ', 'ẩ', 'ậ', 'ă', 'ằ', 'ắ', 'ặ', 'ẳ', 'đ', 'è', 'é', 'ẹ', 'ẻ', 'ê', 'ề', 'ế', 'ệ', 'ể', 'ì', 'í', 'ị', 'ỉ',
            'ò', 'ó', 'ọ', 'ỏ', 'ô', 'ồ', 'ố', 'ộ', 'ổ', 'ù', 'ú', 'ụ', 'ủ', 'ư', 'ừ', 'ứ', 'ự', 'ử'};
        char[] upperVietnameseChar = {'À', 'Á', 'Ạ', 'Ả', 'Â', 'Ầ', 'Ấ', 'Ẩ', 'Ậ', 'Ă', 'Ằ', 'Ắ', 'Ặ', 'Ẳ', 'Đ', 'È', 'É', 'Ẹ', 'Ẻ', 'Ê', 'Ề', 'Ế', 'Ệ', 'Ể', 'Ì', 'Í', 'Ị', 'Ỉ',
            'Ò', 'Ó', 'Ọ', 'Ỏ', 'Ô', 'Ồ', 'Ố', 'Ộ', 'Ổ', 'Ù', 'Ú', 'Ụ', 'Ủ', 'Ư', 'Ừ', 'Ứ', 'Ự', 'Ử'};
        char[] toChar = {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'd', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'i', 'i', 'i', 'i',
            'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'};
        String des = "";
        if (source == null || source.equals("")) {
        } else {
            for (int i = 0; i < source.length(); i++) {
                char addChar = source.charAt(i);
                for (int j = 0; j < lowerVietnameseChar.length; j++) {
                    if (lowerVietnameseChar[j] == source.charAt(i)) {
                        addChar = toChar[j];
                        break;
                    }
                    if (upperVietnameseChar[j] == source.charAt(i)) {
                        addChar = toChar[j];
                        break;
                    }
                }
                des = des + addChar;
            }
        }
        return des;
    }

    public String UpcaseFirst(String str) {
        String first = str.substring(0, 1);
        String concat = str.substring(1);
        return first.toUpperCase() + concat;
    }
}
