/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.common.util;

import java.util.List;
import com.viettel.voffice.database.BO.Process;
import java.util.ArrayList;

/**
 *
 * @author sytv
 */
public class ProcessUtils {

    public interface PROCESS_TYPE {

        public static Long MAIN = 1L;
        public static Long COORDINATE = 0L;
        public static Long SELF = -1L;
        public static Long COMMENT = 2L;
    }

    public interface PROCESS_STATUS {

        public static Long NEW = 0L;
        public static Long ASSIGNED = 3L;
        public static Long FINISH = 4L;
        public static Long RETURN = 5L;
    }

    public interface USER_TYPE {

        public static Long OFFICE_LEADER = 1l; // lanh dao van phong
        public static Long LEADER = 2l; // lanh dao don vi
        public static Long OFFICE_PROCESS = 3l; // don vi xu ly
        public static Long MONITER = 4l; // Phong xu ly(Phong giam sat xu ly)
    }
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ProcessUtils.class);

    // main office
    public static Process getMainDeptProcessDocument(List<Process> lst, Long deptId, Long userId) {
        try {
            if (lst == null) {
                return null;
            }
            Process bo = new Process();
            for (Process p : lst) {
                //p.processType=1 and p.receiveGroupId=? and (p.receiveUserId is null or p.receiveUserId <> ?)  and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2)
                if (PROCESS_TYPE.MAIN.equals(p.getProcessType()) && deptId.equals(p.getReceiveGroupId()) && !userId.equals(p.getReceiveUserId())
                        && !USER_TYPE.OFFICE_LEADER.equals(p.getReceiveUserType()) && !USER_TYPE.LEADER.equals(p.getReceiveUserType())) {
                    bo = p;
                    break;
                }
            }
            if (bo.getProcessId() != null) {
                return bo;
            } else {
                return null;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    // main staff
    public static Process getMainStaffProcessDocument(List<Process> lst, Long deptId, Long userId) {
        try {
            if (lst == null) {
                return null;
            }
            Process bo = new Process();
            for (Process p : lst) {
                // p.processType=1 and p.sendGroupId=? and p.receiveUserId is not null and p.receiveUserId <>? 
                // and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2)
                if (PROCESS_TYPE.MAIN.equals(p.getProcessType()) && deptId.equals(p.getSendGroupId())
                        && p.getReceiveUserId() != null && !userId.equals(p.getReceiveUserId())
                        && (p.getReceiveUserType() == null || (!USER_TYPE.OFFICE_LEADER.equals(p.getReceiveUserType()) && !USER_TYPE.LEADER.equals(p.getReceiveUserType())))) {
                    bo = p;
                    break;
                }
            }
            if (bo.getProcessId() != null) {
                return bo;
            } else {
                return null;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    // 
    public static Process getMainOfficeProcess(List<Process> lst, Long deptId, Long userId) {
        try {
            if (lst == null) {
                return null;
            }
            Process bo = new Process();
            int length = lst.size();
            while (length-- > 0) {
                Process p = lst.get(length);
                // p.status!=5 and p.processType=1 and p.sendGroupId=? 
                // and (p.receiveUserId is null or p.receiveUserId <> ?)  and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2))
                if (!PROCESS_STATUS.RETURN.equals(p.getStatus()) && PROCESS_TYPE.MAIN.equals(p.getProcessType()) && deptId.equals(p.getSendGroupId())
                        && !userId.equals(p.getReceiveUserId()) && !USER_TYPE.OFFICE_LEADER.equals(p.getReceiveUserType()) && !USER_TYPE.LEADER.equals(p.getReceiveUserType())) {
                    bo = p;
                    break;
                }
            }

            if (bo.getProcessId() != null) {
                return bo;
            } else {
                return null;
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }
    // don vi xu ly cuoi cung 

    public static Process getMainOfficeProcessDesc(List<Process> lst) {
        try {
            if (lst == null) {
                return null;
            }
            Process bo = new Process();
            int length = lst.size();
            while (length-- > 0) {
                Process p = lst.get(length);
                // p.processType in (-1,1) and p.status != 5
                // and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2))
                if ((PROCESS_TYPE.MAIN.equals(p.getProcessType()) || PROCESS_TYPE.SELF.equals(p.getProcessType())) && !PROCESS_STATUS.RETURN.equals(p.getStatus())
                        && (p.getReceiveUserType() == null || (!USER_TYPE.OFFICE_LEADER.equals(p.getReceiveUserType()) && !USER_TYPE.LEADER.equals(p.getReceiveUserType())))) {
                    bo = p;
                    break;
                }
            }

            if (bo.getProcessId() != null) {
                return bo;
            } else {
                return null;
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    // nguoi xu ly cuoi cung
    public static Process getMainStaffProcessDesc(List<Process> lst) {
        try {
            if (lst == null) {
                return null;
            }
            Process bo = new Process();
            int length = lst.size();
            while (length-- > 0) {
                Process p = lst.get(length);
                // p.processType=1 and p.status !=5 
                // and p.receiveUserId is not null and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2))
                if ((PROCESS_TYPE.MAIN.equals(p.getProcessType()) || PROCESS_TYPE.SELF.equals(p.getProcessType())) && !PROCESS_STATUS.RETURN.equals(p.getStatus())
                        && p.getReceiveUserId() != null && (p.getReceiveUserType() == null || (!USER_TYPE.OFFICE_LEADER.equals(p.getReceiveUserType()) && !USER_TYPE.LEADER.equals(p.getReceiveUserType())))) {
                    bo = p;
                    break;
                }
            }

            if (bo.getProcessId() != null) {
                return bo;
            } else {
                return null;
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public static Process getProcess(List<Process> lst, Long deptId, Long userId) {
        try {
            if (lst == null) {
                return null;
            }
            Process bo = new Process();
            int length = lst.size();
            while (length-- > 0) {
                Process p = lst.get(length);
                //(p.receiveUserId = ? or (p.receiveGroupId = ? and p.receiveUserId is null)) 
                // and (p.processType = ? or p.processType=?)
                if ((userId.equals(p.getReceiveUserId()) || (deptId.equals(p.getReceiveGroupId()) && p.getReceiveUserId() == null))
                        && (PROCESS_TYPE.MAIN.equals(p.getProcessType()) || PROCESS_TYPE.COORDINATE.equals(p.getProcessType()))) {
                    bo = p;
                    break;
                }
            }
            if (bo.getProcessId() != null) {
                return bo;
            } else {
                return null;
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public static Process getProcess(List<Process> lst, Long userId) {
        try {
            if (lst == null) {
                return null;
            }
            Process bo = new Process();
            int length = lst.size();
            while (length-- > 0) {
                Process p = lst.get(length);
                // p.processType not in (?,?) and p.processType is not null
                // and p.receiveUserId = ? 
                if (p.getProcessType() != null && !PROCESS_TYPE.SELF.equals(p.getProcessType()) && !PROCESS_TYPE.COMMENT.equals(p.getProcessType())
                        && userId.equals(p.getReceiveUserId())) {
                    bo = p;
                    break;
                }
            }
            if (bo.getProcessId() != null) {
                return bo;
            } else {
                return null;
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public static Process getProcessOffice(List<Process> lst, Long deptId) {
        try {
            if (lst == null) {
                return null;
            }
            Process bo = new Process();
            int length = lst.size();
            while (length-- > 0) {
                Process p = lst.get(length);
                //p.receiveGroupId = ? and p.receiveUserId is null
                if (deptId.equals(p.getReceiveGroupId()) && p.getReceiveUserId() == null) {
                    bo = p;
                    break;
                }
            }
            if (bo.getProcessId() != null) {
                return bo;
            } else {
                return null;
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public static Process getProcessOfOffice(List<Process> lst, Long deptId) {
        try {
            if (lst == null) {
                return null;
            }
            Process bo = new Process();
            for (Process p : lst) {
                //p.receiveGroupId = ? and p.receiveUserId is null and p.processType not in (?,?)
                if (deptId.equals(p.getReceiveGroupId()) && p.getReceiveUserId() == null
                        && !PROCESS_TYPE.SELF.equals(p.getProcessType()) && !PROCESS_TYPE.COMMENT.equals(p.getProcessType())) {
                    bo = p;
                    break;
                }
            }
            if (bo.getProcessId() != null) {
                return bo;
            } else {
                return null;
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    // chanh van phong -- lanh dao -- phong giam sat
    public static List<Process> getLeaderProcess(List<Process> lst, Long deptId, Long receiveUserType) {
        try {
            if (lst == null) {
                return null;
            }
            List<Process> lstProcess = new ArrayList<Process>();
            for (Process p : lst) {
                // p.sendGroupId = ? and p.receiveUserType = ?
                if (deptId.equals(p.getSendGroupId()) && receiveUserType.equals(p.getReceiveUserType())) {
                    lstProcess.add(p);
                }
            }
            if (!lstProcess.isEmpty()) {
                return lstProcess;
            } else {
                return null;
            }

        } catch (Exception e) {
        }
        return null;
    }

    // don vi xu ly - phoi hop
    public static List<Process> getOfficeProcess(List<Process> lst, Long deptId, Long processType) {
        try {
            if (lst == null) {
                return null;
            }
            List<Process> lstProcess = new ArrayList<Process>();
            for (Process p : lst) {
                // p.sendGroupId = ? and p.status!=5 and p.processType= ? 
                // and (p.receiveUserType is null or p.receiveUserType =3) 
                if (deptId.equals(p.getSendGroupId()) && !PROCESS_STATUS.RETURN.equals(p.getStatus()) && processType.equals(p.getProcessType())
                        && (p.getReceiveUserType() == null || USER_TYPE.OFFICE_PROCESS.equals(p.getReceiveUserType()))) {
                    lstProcess.add(p);
                }
            }
            if (!lstProcess.isEmpty()) {
                return lstProcess;
            } else {
                return null;
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    // nguoi xu ly chinh
    public static Process getMainStaffProcess(List<Process> lst, Long deptId) {
        try {
            if (lst == null) {
                return null;
            }
            Process bo = new Process();
            for (Process p : lst) {
                // p.processType=1 and p.sendGroupId=? and p.receiveUserId is not null 
                // and (p.receiveUserType is null or (p.receiveUserType <> 1 and p.receiveUserType <> 2))
                if (PROCESS_TYPE.MAIN.equals(p.getProcessType()) && deptId.equals(p.getSendGroupId()) && p.getReceiveUserId() != null
                        && (p.getReceiveUserType() == null || (!USER_TYPE.OFFICE_LEADER.equals(p.getReceiveUserType()) && !USER_TYPE.LEADER.equals(p.getReceiveUserType())))) {
                    bo = p;
                    break;
                }
            }
            if (bo.getProcessId() != null) {
                return bo;
            } else {
                return null;
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    // nguoi dong xu ly
    public static List<Process> getCoorStaffProcess(List<Process> lst, Long deptId) {
        try {
            if (lst == null) {
                return null;
            }
            List<Process> lstProcess = new ArrayList<Process>();
            for (Process p : lst) {
                // p.processType=0 and p.sendGroupId=? and p.receiveUserId is not null 
                // and (p.receiveUserType is null or p.receiveUserType not in (1,2,4))
                if (PROCESS_TYPE.COORDINATE.equals(p.getProcessType()) && deptId.equals(p.getSendGroupId()) && p.getReceiveUserId() != null
                        && (p.getReceiveUserType() == null || (!USER_TYPE.OFFICE_LEADER.equals(p.getReceiveUserType()) && !USER_TYPE.LEADER.equals(p.getReceiveUserType())) && !USER_TYPE.MONITER.equals(p.getReceiveUserType()))) {
                    lstProcess.add(p);
                }
            }
            if (!lstProcess.isEmpty()) {
                return lstProcess;
            } else {
                return null;
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }
}
