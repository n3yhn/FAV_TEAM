/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.common.util;

/*     */ import com.viettel.vsaadmin.database.BO.EventLogBO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Date;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.hibernate.Session;
/*     */
/*     */ public class LogUtil
/*     */ {
/*     */   public static void setIP(EventLogBO log, HttpServletRequest request)
/*     */   {
/*  27 */     log.setIp((String)request.getSession().getAttribute("VTS-IP"));
/*  28 */     log.setWan(request.getRemoteAddr());
/*     */   }
/*     */
/*     */   public static void editUser(Session session, HttpServletRequest request, String actor, String user, String description) {
/*  32 */     EventLogBO el = new EventLogBO();
/*  33 */     setIP(el, request);
/*  34 */     el.setAction("EDIT_USER");
/*  35 */     el.setActor(actor);
/*  36 */     el.setEventDate(new Date());
/*  37 */     el.setUserName(user);
/*  38 */     el.setDescription(description);
/*  39 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void createUser(Session session, HttpServletRequest request, String actor, String user, String description) {
/*  43 */     EventLogBO el = new EventLogBO();
/*  44 */     setIP(el, request);
/*  45 */     el.setAction("CREATE_USER");
/*  46 */     el.setActor(actor);
/*  47 */     el.setEventDate(new Date());
/*  48 */     el.setUserName(user);
/*  49 */     el.setDescription(description);
/*  50 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void deleteUser(Session session, HttpServletRequest request, String actor, String user, String description) {
/*  54 */     EventLogBO el = new EventLogBO();
/*  55 */     setIP(el, request);
/*  56 */     el.setAction("DELETE_USER");
/*  57 */     el.setActor(actor);
/*  58 */     el.setEventDate(new Date());
/*  59 */     el.setUserName(user);
/*  60 */     el.setDescription(description);
/*  61 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void lockUser(Session session, HttpServletRequest request, String actor, String user, String description) {
/*  65 */     EventLogBO el = new EventLogBO();
/*  66 */     setIP(el, request);
/*  67 */     el.setAction("LOCK_USER");
/*  68 */     el.setActor(actor);
/*  69 */     el.setEventDate(new Date());
/*  70 */     el.setUserName(user);
/*  71 */     el.setDescription(description);
/*  72 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void unLockUser(Session session, HttpServletRequest request, String actor, String user, String description) {
/*  76 */     EventLogBO el = new EventLogBO();
/*  77 */     setIP(el, request);
/*  78 */     el.setAction("UNLOCK_USER");
/*  79 */     el.setActor(actor);
/*  80 */     el.setEventDate(new Date());
/*  81 */     el.setUserName(user);
/*  82 */     el.setDescription(description);
/*  83 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void resetPassword(Session session, HttpServletRequest request, String actor, String user, String description) {
/*  87 */     System.out.println("start log reset password");
/*  88 */     EventLogBO el = new EventLogBO();
/*  89 */     setIP(el, request);
/*  90 */     el.setAction("RESET_PASSWORD");
/*  91 */     el.setActor(actor);
/*  92 */     el.setEventDate(new Date());
/*  93 */     el.setUserName(user);
/*  94 */     el.setDescription(description);
/*  95 */     session.save(el);
/*  96 */     System.out.println("end log reset password");
/*     */   }
/*     */
/*     */   public static void createRole(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String description) {
/* 100 */     EventLogBO el = new EventLogBO();
/* 101 */     setIP(el, request);
/* 102 */     el.setAction("CREATE_ROLE");
/* 103 */     el.setActor(actor);
/* 104 */     el.setEventDate(new Date());
/* 105 */     el.setRoleCode(roleCode);
/* 106 */     el.setRoleName(roleName);
/* 107 */     el.setDescription(description);
/* 108 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void deleteRole(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String description) {
/* 112 */     EventLogBO el = new EventLogBO();
/* 113 */     setIP(el, request);
/* 114 */     el.setAction("DELETE_ROLE");
/* 115 */     el.setActor(actor);
/* 116 */     el.setEventDate(new Date());
/* 117 */     el.setRoleCode(roleCode);
/* 118 */     el.setRoleName(roleName);
/* 119 */     el.setDescription(description);
/* 120 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void lockRole(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String description) {
/* 124 */     EventLogBO el = new EventLogBO();
/* 125 */     setIP(el, request);
/* 126 */     el.setAction("LOCK_ROLE");
/* 127 */     el.setActor(actor);
/* 128 */     el.setEventDate(new Date());
/* 129 */     el.setRoleCode(roleCode);
/* 130 */     el.setRoleName(roleName);
/* 131 */     el.setDescription(description);
/* 132 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void unLockRole(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String description) {
/* 136 */     EventLogBO el = new EventLogBO();
/* 137 */     setIP(el, request);
/* 138 */     el.setAction("UNLOCK_ROLE");
/* 139 */     el.setActor(actor);
/* 140 */     el.setEventDate(new Date());
/* 141 */     el.setRoleCode(roleCode);
/* 142 */     el.setRoleName(roleName);
/* 143 */     el.setDescription(description);
/* 144 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void addFunctionToRole(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String functionName, String appCode, String appName, String description) {
/* 148 */     EventLogBO el = new EventLogBO();
/* 149 */     setIP(el, request);
/* 150 */     el.setAction("ADD_FUNCTION_TO_ROLE");
/* 151 */     el.setActor(actor);
/* 152 */     el.setEventDate(new Date());
/* 153 */     el.setRoleCode(roleCode);
/* 154 */     el.setRoleName(roleName);
/* 155 */     el.setObjectName(functionName);
/* 156 */     el.setAppCode(appCode);
/* 157 */     el.setAppName(appName);
/* 158 */     el.setDescription(description);
/* 159 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void removeFunctionFromRole(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String functionName, String appCode, String appName, String description) {
/* 163 */     EventLogBO el = new EventLogBO();
/* 164 */     setIP(el, request);
/* 165 */     el.setAction("REMOVE_FUNCTION_FROM_ROLE");
/* 166 */     el.setActor(actor);
/* 167 */     el.setEventDate(new Date());
/* 168 */     el.setRoleCode(roleCode);
/* 169 */     el.setRoleName(roleName);
/* 170 */     el.setObjectName(functionName);
/* 171 */     el.setAppCode(appCode);
/* 172 */     el.setAppName(appName);
/* 173 */     el.setDescription(description);
/* 174 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void lockFunctionInRole(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String functionName, String appCode, String appName, String description) {
/* 178 */     EventLogBO el = new EventLogBO();
/* 179 */     setIP(el, request);
/* 180 */     el.setAction("LOCK_FUNCTION_IN_ROLE");
/* 181 */     el.setActor(actor);
/* 182 */     el.setEventDate(new Date());
/* 183 */     el.setRoleCode(roleCode);
/* 184 */     el.setRoleName(roleName);
/* 185 */     el.setObjectName(functionName);
/* 186 */     el.setAppCode(appCode);
/* 187 */     el.setAppName(appName);
/* 188 */     el.setDescription(description);
/* 189 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void unLockFunctionInRole(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String functionName, String appCode, String appName, String description) {
/* 193 */     EventLogBO el = new EventLogBO();
/* 194 */     setIP(el, request);
/* 195 */     el.setAction("UNLOCK_FUNCTION_IN_ROLE");
/* 196 */     el.setActor(actor);
/* 197 */     el.setEventDate(new Date());
/* 198 */     el.setRoleCode(roleCode);
/* 199 */     el.setRoleName(roleName);
/* 200 */     el.setObjectName(functionName);
/* 201 */     el.setAppCode(appCode);
/* 202 */     el.setAppName(appName);
/* 203 */     el.setDescription(description);
/* 204 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void addRoleToAdmin(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String user, String description) {
/* 208 */     EventLogBO el = new EventLogBO();
/* 209 */     setIP(el, request);
/* 210 */     el.setAction("ADD_ROLE_TO_ADMIN");
/* 211 */     el.setActor(actor);
/* 212 */     el.setEventDate(new Date());
/* 213 */     el.setRoleCode(roleCode);
/* 214 */     el.setRoleName(roleName);
/* 215 */     el.setUserName(user);
/* 216 */     el.setDescription(description);
/* 217 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void removeRoleFromAdmin(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String user, String description) {
/* 221 */     EventLogBO el = new EventLogBO();
/* 222 */     setIP(el, request);
/* 223 */     el.setAction("REMOVE_ROLE_FROM_ADMIN");
/* 224 */     el.setActor(actor);
/* 225 */     el.setEventDate(new Date());
/* 226 */     el.setRoleCode(roleCode);
/* 227 */     el.setRoleName(roleName);
/* 228 */     el.setUserName(user);
/* 229 */     el.setDescription(description);
/* 230 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void lockRoleAdmin(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String user, String description) {
/* 234 */     EventLogBO el = new EventLogBO();
/* 235 */     setIP(el, request);
/* 236 */     el.setAction("LOCK_ROLE_ADMIN");
/* 237 */     el.setActor(actor);
/* 238 */     el.setEventDate(new Date());
/* 239 */     el.setRoleCode(roleCode);
/* 240 */     el.setRoleName(roleName);
/* 241 */     el.setUserName(user);
/* 242 */     el.setDescription(description);
/* 243 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void unLockRoleAdmin(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String user, String description) {
/* 247 */     EventLogBO el = new EventLogBO();
/* 248 */     setIP(el, request);
/* 249 */     el.setAction("UNLOCK_ROLE_ADMIN");
/* 250 */     el.setActor(actor);
/* 251 */     el.setEventDate(new Date());
/* 252 */     el.setRoleCode(roleCode);
/* 253 */     el.setRoleName(roleName);
/* 254 */     el.setUserName(user);
/* 255 */     el.setDescription(description);
/* 256 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void assignRoleToUser(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String user, String description) {
/* 260 */     EventLogBO el = new EventLogBO();
/* 261 */     setIP(el, request);
/* 262 */     el.setAction("ASSIGN_ROLE_TO_USER");
/* 263 */     el.setActor(actor);
/* 264 */     el.setEventDate(new Date());
/* 265 */     el.setRoleCode(roleCode);
/* 266 */     el.setRoleName(roleName);
/* 267 */     el.setUserName(user);
/* 268 */     el.setDescription(description);
/* 269 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void removeRoleFromUser(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String user, String description) {
/* 273 */     EventLogBO el = new EventLogBO();
/* 274 */     setIP(el, request);
/* 275 */     el.setAction("REMOVE_ROLE_FROM_USER");
/* 276 */     el.setActor(actor);
/* 277 */     el.setEventDate(new Date());
/* 278 */     el.setRoleCode(roleCode);
/* 279 */     el.setRoleName(roleName);
/* 280 */     el.setUserName(user);
/* 281 */     el.setDescription(description);
/* 282 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void lockRoleInUser(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String user, String description) {
/* 286 */     EventLogBO el = new EventLogBO();
/* 287 */     setIP(el, request);
/* 288 */     el.setAction("LOCK_ROLE_IN_USER");
/* 289 */     el.setActor(actor);
/* 290 */     el.setEventDate(new Date());
/* 291 */     el.setRoleCode(roleCode);
/* 292 */     el.setRoleName(roleName);
/* 293 */     el.setUserName(user);
/* 294 */     el.setDescription(description);
/* 295 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void unLockRoleInUser(Session session, HttpServletRequest request, String actor, String roleCode, String roleName, String user, String description) {
/* 299 */     EventLogBO el = new EventLogBO();
/* 300 */     setIP(el, request);
/* 301 */     el.setAction("UNLOCK_ROLE_IN_USER");
/* 302 */     el.setActor(actor);
/* 303 */     el.setEventDate(new Date());
/* 304 */     el.setRoleCode(roleCode);
/* 305 */     el.setRoleName(roleName);
/* 306 */     el.setUserName(user);
/* 307 */     el.setDescription(description);
/* 308 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void createApp(Session session, HttpServletRequest request, String actor, String appCode, String appName, String description) {
/* 312 */     EventLogBO el = new EventLogBO();
/* 313 */     setIP(el, request);
/* 314 */     el.setAction("CREATE_APP");
/* 315 */     el.setActor(actor);
/* 316 */     el.setEventDate(new Date());
/* 317 */     el.setAppCode(appCode);
/* 318 */     el.setAppName(appName);
/* 319 */     el.setDescription(description);
/* 320 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void deleteApp(Session session, HttpServletRequest request, String actor, String appCode, String appName, String description) {
/* 324 */     EventLogBO el = new EventLogBO();
/* 325 */     setIP(el, request);
/* 326 */     el.setAction("DELETE_APP");
/* 327 */     el.setActor(actor);
/* 328 */     el.setEventDate(new Date());
/* 329 */     el.setAppCode(appCode);
/* 330 */     el.setAppName(appName);
/* 331 */     el.setDescription(description);
/* 332 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void lockApp(Session session, HttpServletRequest request, String actor, String appCode, String appName, String description) {
/* 336 */     EventLogBO el = new EventLogBO();
/* 337 */     setIP(el, request);
/* 338 */     el.setAction("LOCK_APP");
/* 339 */     el.setActor(actor);
/* 340 */     el.setEventDate(new Date());
/* 341 */     el.setAppCode(appCode);
/* 342 */     el.setAppName(appName);
/* 343 */     el.setDescription(description);
/* 344 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void unLockApp(Session session, HttpServletRequest request, String actor, String appCode, String appName, String description) {
/* 348 */     EventLogBO el = new EventLogBO();
/* 349 */     setIP(el, request);
/* 350 */     el.setAction("UNLOCK_APP");
/* 351 */     el.setActor(actor);
/* 352 */     el.setEventDate(new Date());
/* 353 */     el.setAppCode(appCode);
/* 354 */     el.setAppName(appName);
/* 355 */     el.setDescription(description);
/* 356 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void createFunction(Session session, HttpServletRequest request, String actor, String functionName, String appCode, String appName, String description) {
/* 360 */     EventLogBO el = new EventLogBO();
/* 361 */     setIP(el, request);
/* 362 */     el.setAction("CREATE_FUNCTION");
/* 363 */     el.setActor(actor);
/* 364 */     el.setEventDate(new Date());
/* 365 */     el.setObjectName(functionName);
/* 366 */     el.setAppCode(appCode);
/* 367 */     el.setAppName(appName);
/* 368 */     el.setDescription(description);
/* 369 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void deleteFunction(Session session, HttpServletRequest request, String actor, String functionName, String appCode, String appName, String description) {
/* 373 */     EventLogBO el = new EventLogBO();
/* 374 */     setIP(el, request);
/* 375 */     el.setAction("DELETE_FUNCTION");
/* 376 */     el.setActor(actor);
/* 377 */     el.setEventDate(new Date());
/* 378 */     el.setObjectName(functionName);
/* 379 */     el.setAppCode(appCode);
/* 380 */     el.setAppName(appName);
/* 381 */     el.setDescription(description);
/* 382 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void lockFunction(Session session, HttpServletRequest request, String actor, String functionName, String appCode, String appName, String description) {
/* 386 */     EventLogBO el = new EventLogBO();
/* 387 */     setIP(el, request);
/* 388 */     el.setAction("LOCK_FUNCTION");
/* 389 */     el.setActor(actor);
/* 390 */     el.setEventDate(new Date());
/* 391 */     el.setObjectName(functionName);
/* 392 */     el.setAppCode(appCode);
/* 393 */     el.setAppName(appName);
/* 394 */     el.setDescription(description);
/* 395 */     session.save(el);
/*     */   }
/*     */
/*     */   public static void unLockFunction(Session session, HttpServletRequest request, String actor, String functionName, String appCode, String appName, String description) {
/* 399 */     EventLogBO el = new EventLogBO();
/* 400 */     setIP(el, request);
/* 401 */     el.setAction("UNLOCK_FUNCTION");
/* 402 */     el.setActor(actor);
/* 403 */     el.setEventDate(new Date());
/* 404 */     el.setObjectName(functionName);
/* 405 */     el.setAppCode(appCode);
/* 406 */     el.setAppName(appName);
/* 407 */     el.setDescription(description);
/* 408 */     session.save(el);
/*     */   }
/*     */   public static void setCheckIpUser(Session session, HttpServletRequest request, String actor, String user, String description) {
/* 411 */     EventLogBO el = new EventLogBO();
/* 412 */     setIP(el, request);
/* 413 */     el.setAction("SET_CHECK_IP");
/* 414 */     el.setActor(actor);
/* 415 */     el.setEventDate(new Date());
/* 416 */     el.setUserName(user);
/* 417 */     el.setDescription(description);
/* 418 */     session.save(el);
/*     */   }
/*     */   public static void setUncheckIpUser(Session session, HttpServletRequest request, String actor, String user, String description) {
/* 421 */     EventLogBO el = new EventLogBO();
/* 422 */     setIP(el, request);
/* 423 */     el.setAction("SET_UNCHECK_IP");
/* 424 */     el.setActor(actor);
/* 425 */     el.setEventDate(new Date());
/* 426 */     el.setUserName(user);
/* 427 */     el.setDescription(description);
/* 428 */     session.save(el);
/*     */   }
/*     */   public static void updateIpUser(Session session, HttpServletRequest request, String actor, String user, String description) {
/* 431 */     EventLogBO el = new EventLogBO();
/* 432 */     setIP(el, request);
/* 433 */     el.setAction("UPDATE_IP");
/* 434 */     el.setActor(actor);
/* 435 */     el.setEventDate(new Date());
/* 436 */     el.setUserName(user);
/* 437 */     el.setDescription(description);
/* 438 */     session.save(el);
/*     */   }
/*     */   public static void errorSendSms(Session session, HttpServletRequest request, String actor, String user, String description) {
/* 441 */     EventLogBO el = new EventLogBO();
/* 442 */     setIP(el, request);
/* 443 */     el.setAction("ERROR_SEND_SMS");
/* 444 */     el.setActor(actor);
/* 445 */     el.setEventDate(new Date());
/* 446 */     el.setUserName(user);
/* 447 */     el.setDescription(description);
/* 448 */     session.save(el);
/*     */   }
/*     */   public static void successSendSms(Session session, HttpServletRequest request, String actor, String user, String description) {
/* 451 */     EventLogBO el = new EventLogBO();
/* 452 */     setIP(el, request);
/* 453 */     el.setAction("SUCCESS_SEND_SMS");
/* 454 */     el.setActor(actor);
/* 455 */     el.setEventDate(new Date());
/* 456 */     el.setUserName(user);
/* 457 */     el.setDescription(description);
/* 458 */     session.save(el);
/*     */   }
/*     */ }
