package com.viettel.vsaadmin.common.util;

import com.viettel.common.util.LogUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class IpUtils {

    private static final String NO_CHECK_IP = "\\b(?:\\d{1,3}.){3}\\d{1,3}\\b";
    private static final String OR_OPERATOR = "|";

    public static String setUnchekIp(String ip) {
        String result;

        if ((ip == null) || (ip.length() == 0)) /*  25 */ {
            result = "\\b(?:\\d{1,3}.){3}\\d{1,3}\\b";
        } else if (ip.indexOf("\\b(?:\\d{1,3}.){3}\\d{1,3}\\b") >= 0) /*  27 */ {
            result = ip;
        } else {
            result = "\\b(?:\\d{1,3}.){3}\\d{1,3}\\b|" + ip;
        }

        return result;
    }

    public static String setChekIp(String ip) {
        if ((ip == null) || (ip.length() == 0)) {
            return ip;
        }
        String result = ip;
        int pos = result.indexOf("\\b(?:\\d{1,3}.){3}\\d{1,3}\\b");
        while (pos >= 0) {
            int pos_end = pos + "\\b(?:\\d{1,3}.){3}\\d{1,3}\\b".length();
            if ((pos_end < result.length()) && (result.charAt(pos_end) == '|')) {
                pos_end++;
            }
            result = result.substring(0, pos) + result.substring(pos_end);
            pos = result.indexOf("\\b(?:\\d{1,3}.){3}\\d{1,3}\\b");
        }
        return result;
    }

    public static boolean isValidIp(String ip, String ipRegex) {
        String[] regexes = ipRegex.split("\\|");
        for (int i = 0; i < regexes.length; i++) {
            String regex = regexes[i];
            String[] octets = regex.split("\\.");
            String[] ots = ip.split("\\.");
            if ((ots[0] + ots[1] + ots[2]).equalsIgnoreCase(octets[0] + octets[1] + octets[2])) {
                String octet = octets[3];
                if ("*".equalsIgnoreCase(octet)) /*  67 */ {
                    return true;
                }
                if ((octet.charAt(0) == '[') && (octet.charAt(octet.length() - 1) == ']')) {
                    octet = octet.substring(1, octet.length() - 1);
                    int pos = octet.indexOf("-");
                    if (pos >= 0) {
                        String[] nums = octet.split("-");
                        try {
                            int begin = Integer.parseInt(nums[0]);
                            int end = Integer.parseInt(nums[1]);
                            int value = Integer.parseInt(ots[3]);
                            if ((value >= begin) && (value <= end)) /*  78 */ {
                                return true;
                            }
                        } catch (Exception ex) {
                            LogUtil.addLog(ex);//binhnt sonar a160901
                            return false;
                        }
                    } else {
                        String[] nums = octet.split(",");
                        for (int j = 0; j < nums.length; j++) {
                            String num = nums[j];
                            if (num.equalsIgnoreCase(ots[3])) /*  88 */ {
                                return true;
                            }
                        }
                    }
                } else if (octet.equalsIgnoreCase(ots[3])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValidIpRegex(String ipRegex) {
        if (!isValidCharacters(ipRegex)) {
            return false;
        }
        String[] regexes = ipRegex.split("\\|");
        for (int i = 0; i < regexes.length; i++) {
            String regex = regexes[i];
            if ((regex.charAt(0) == '.') || (regex.charAt(regex.length() - 1) == '.')) {
                return false;
            }
            String[] octets = regex.split("\\.");
            if (octets.length != 4) {
                return false;
            }
            if ((!isValidOctet(octets[0])) || (!isValidOctet(octets[1])) || (!isValidOctet(octets[2]))) {
                return false;
            }
            String octet = octets[3];
            if (!octet.equalsIgnoreCase("*")) {
                if ((octet.charAt(0) == '[') && (octet.charAt(octet.length() - 1) == ']')) {
                    octet = octet.substring(1, octet.length() - 1);
                    int pos = octet.indexOf("-");
                    if (pos >= 0) {
                        if ((octet.charAt(0) == '-') || (octet.charAt(octet.length() - 1) == '-')) {
                            return false;
                        }
                        String[] nums = octet.split("-");
                        if (nums.length != 2) {
                            return false;
                        }
                        if ((!isValidOctet(nums[0])) || (!isValidOctet(nums[1]))) {
                            return false;
                        }
                        try {
                            int begin = Integer.parseInt(nums[0]);
                            int end = Integer.parseInt(nums[1]);
                            if (begin >= end) /* 143 */ {
                                return false;
                            }
                        } catch (Exception ex) {
                            LogUtil.addLog(ex);//binhnt sonar a160901
                            return false;
                        }
                    } else {
                        if ((octet.charAt(0) == ',') || (octet.charAt(octet.length() - 1) == ',')) {
                            return false;
                        }
                        String[] nums = octet.split(",");
                        if (nums.length < 2) {
                            return false;
                        }
                        for (int j = 0; j < nums.length; j++) {
                            String num = nums[j];
                            if (!isValidOctet(num)) /* 159 */ {
                                return false;
                            }
                        }
                    }
                } else if (!isValidOctet(octet)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValidCharacters(String ipRegex) {
        String expression = "^[0-9\\.,*\\[\\]\\|-]*$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(ipRegex);
        return matcher.matches();
    }

    public static boolean isValidOctet(String octet) {
        if ((octet == null) || (octet.length() == 0)) {
            return false;
        }
        if ((octet.length() > 1) && (octet.charAt(0) == '0')) {
            return false;
        }
        int value = -1;
        try {
            value = Integer.parseInt(octet);
        } catch (Exception e) {
            value = -1;
        }

        return (value >= 0) && (value <= 255);
    }
}

/* Location:           C:\Program Files\Apache Software Foundation\TomcatVSA\webapps\vsaadminv3\WEB-INF\classes\
 * Qualified Name:     com.viettel.vsaadmin.common.util.IpUtils
 * JD-Core Version:    0.6.0
 */
