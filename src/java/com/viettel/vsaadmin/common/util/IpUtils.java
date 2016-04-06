/*     */ package com.viettel.vsaadmin.common.util;
/*     */ 
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public final class IpUtils
/*     */ {
/*     */   private static final String NO_CHECK_IP = "\\b(?:\\d{1,3}.){3}\\d{1,3}\\b";
/*     */   private static final String OR_OPERATOR = "|";
/*     */ 
/*     */   public static String setUnchekIp(String ip)
/*     */   {
/*  22 */     String result = null;
/*     */ 
/*  24 */     if ((ip == null) || (ip.length() == 0))
/*  25 */       result = "\\b(?:\\d{1,3}.){3}\\d{1,3}\\b";
/*  26 */     else if (ip.indexOf("\\b(?:\\d{1,3}.){3}\\d{1,3}\\b") >= 0)
/*  27 */       result = ip;
/*     */     else {
/*  29 */       result = "\\b(?:\\d{1,3}.){3}\\d{1,3}\\b|" + ip;
/*     */     }
/*     */ 
/*  32 */     return result;
/*     */   }
/*     */ 
/*     */   public static String setChekIp(String ip) {
/*  36 */     if ((ip == null) || (ip.length() == 0)) {
/*  37 */       return ip;
/*     */     }
/*  39 */     String result = ip;
/*  40 */     int pos = result.indexOf("\\b(?:\\d{1,3}.){3}\\d{1,3}\\b");
/*  41 */     while (pos >= 0) {
/*  42 */       int pos_end = pos + "\\b(?:\\d{1,3}.){3}\\d{1,3}\\b".length();
/*  43 */       if ((pos_end < result.length()) && (result.charAt(pos_end) == '|')) {
/*  44 */         pos_end++;
/*     */       }
/*  46 */       result = result.substring(0, pos) + result.substring(pos_end);
/*  47 */       pos = result.indexOf("\\b(?:\\d{1,3}.){3}\\d{1,3}\\b");
/*     */     }
/*  49 */     return result;
/*     */   }
/*     */ 
/*     */   public static boolean isValidIp(String ip, String ipRegex)
/*     */   {
/*  59 */     String[] regexes = ipRegex.split("\\|");
/*  60 */     for (int i = 0; i < regexes.length; i++) {
/*  61 */       String regex = regexes[i];
/*  62 */       String[] octets = regex.split("\\.");
/*  63 */       String[] ots = ip.split("\\.");
/*  64 */       if ((ots[0] + ots[1] + ots[2]).equalsIgnoreCase(octets[0] + octets[1] + octets[2])) {
/*  65 */         String octet = octets[3];
/*  66 */         if ("*".equalsIgnoreCase(octet))
/*  67 */           return true;
/*  68 */         if ((octet.charAt(0) == '[') && (octet.charAt(octet.length() - 1) == ']')) {
/*  69 */           octet = octet.substring(1, octet.length() - 1);
/*  70 */           int pos = octet.indexOf("-");
/*  71 */           if (pos >= 0) {
/*  72 */             String[] nums = octet.split("-");
/*     */             try {
/*  74 */               int begin = Integer.parseInt(nums[0]);
/*  75 */               int end = Integer.parseInt(nums[1]);
/*  76 */               int value = Integer.parseInt(ots[3]);
/*  77 */               if ((value >= begin) && (value <= end))
/*  78 */                 return true;
/*     */             }
/*     */             catch (Exception e) {
/*  81 */               return false;
/*     */             }
/*     */           } else {
/*  84 */             String[] nums = octet.split(",");
/*  85 */             for (int j = 0; j < nums.length; j++) {
/*  86 */               String num = nums[j];
/*  87 */               if (num.equalsIgnoreCase(ots[3]))
/*  88 */                 return true;
/*     */             }
/*     */           }
/*     */         }
/*  92 */         else if (octet.equalsIgnoreCase(ots[3])) {
/*  93 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isValidIpRegex(String ipRegex)
/*     */   {
/* 106 */     if (!isValidCharacters(ipRegex)) {
/* 107 */       return false;
/*     */     }
/* 109 */     String[] regexes = ipRegex.split("\\|");
/* 110 */     for (int i = 0; i < regexes.length; i++) {
/* 111 */       String regex = regexes[i];
/* 112 */       if ((regex.charAt(0) == '.') || (regex.charAt(regex.length() - 1) == '.')) {
/* 113 */         return false;
/*     */       }
/* 115 */       String[] octets = regex.split("\\.");
/* 116 */       if (octets.length != 4) {
/* 117 */         return false;
/*     */       }
/* 119 */       if ((!isValidOctet(octets[0])) || (!isValidOctet(octets[1])) || (!isValidOctet(octets[2]))) {
/* 120 */         return false;
/*     */       }
/* 122 */       String octet = octets[3];
/* 123 */       if (!octet.equalsIgnoreCase("*")) {
/* 124 */         if ((octet.charAt(0) == '[') && (octet.charAt(octet.length() - 1) == ']')) {
/* 125 */           octet = octet.substring(1, octet.length() - 1);
/* 126 */           int pos = octet.indexOf("-");
/* 127 */           if (pos >= 0) {
/* 128 */             if ((octet.charAt(0) == '-') || (octet.charAt(octet.length() - 1) == '-')) {
/* 129 */               return false;
/*     */             }
/* 131 */             String[] nums = octet.split("-");
/* 132 */             if (nums.length != 2) {
/* 133 */               return false;
/*     */             }
/* 135 */             if ((!isValidOctet(nums[0])) || (!isValidOctet(nums[1]))) {
/* 136 */               return false;
/*     */             }
/*     */             try
/*     */             {
/* 140 */               int begin = Integer.parseInt(nums[0]);
/* 141 */               int end = Integer.parseInt(nums[1]);
/* 142 */               if (begin >= end)
/* 143 */                 return false;
/*     */             }
/*     */             catch (Exception e) {
/* 146 */               return false;
/*     */             }
/*     */           } else {
/* 149 */             if ((octet.charAt(0) == ',') || (octet.charAt(octet.length() - 1) == ',')) {
/* 150 */               return false;
/*     */             }
/* 152 */             String[] nums = octet.split(",");
/* 153 */             if (nums.length < 2) {
/* 154 */               return false;
/*     */             }
/* 156 */             for (int j = 0; j < nums.length; j++) {
/* 157 */               String num = nums[j];
/* 158 */               if (!isValidOctet(num))
/* 159 */                 return false;
/*     */             }
/*     */           }
/*     */         }
/* 163 */         else if (!isValidOctet(octet)) {
/* 164 */           return false;
/*     */         }
/*     */       }
/*     */     }
/* 168 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean isValidCharacters(String ipRegex)
/*     */   {
/* 178 */     String expression = "^[0-9\\.,*\\[\\]\\|-]*$";
/* 179 */     Pattern pattern = Pattern.compile(expression);
/* 180 */     Matcher matcher = pattern.matcher(ipRegex);
/* 181 */     return matcher.matches();
/*     */   }
/*     */ 
/*     */   public static boolean isValidOctet(String octet)
/*     */   {
/* 190 */     if ((octet == null) || (octet.length() == 0)) {
/* 191 */       return false;
/*     */     }
/* 193 */     if ((octet.length() > 1) && (octet.charAt(0) == '0')) {
/* 194 */       return false;
/*     */     }
/*     */ 
/* 197 */     int value = -1;
/*     */     try {
/* 199 */       value = Integer.parseInt(octet);
/*     */     } catch (Exception e) {
/* 201 */       value = -1;
/*     */     }
/*     */ 
/* 205 */     return (value >= 0) && (value <= 255);
/*     */   }
/*     */ }

/* Location:           C:\Program Files\Apache Software Foundation\TomcatVSA\webapps\vsaadminv3\WEB-INF\classes\
 * Qualified Name:     com.viettel.vsaadmin.common.util.IpUtils
 * JD-Core Version:    0.6.0
 */