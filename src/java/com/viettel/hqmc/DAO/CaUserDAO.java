/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.hqmc.BO.Business;
import com.viettel.hqmc.BO.CaUser;
import com.viettel.hqmc.DAOHE.BusinessDAOHE;
import com.viettel.hqmc.DAOHE.CaUserDAOHE;
import com.viettel.hqmc.FORM.CaUserForm;
import com.viettel.voffice.common.util.CommonUtils;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.VoAttachsDAOHE;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import org.hibernate.Query;

/**
 *
 * @author BINHNT53
 */
public class CaUserDAO extends BaseDAO {

//    private String forwardPage = "caUserPage";
    private final String businessRegisterCAPage = "businessRegisterCA.Page";
    private CaUserForm searchForm;
    private CaUserForm createForm;
    CaUserDAOHE caUserDao = new CaUserDAOHE();
    private List<CaUserForm> lstItemOnGrid;
    /*
     * toShowPage
     * show data perpare after show page
     */
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CaUserDAO.class);

    public String prepare() {
        try {
            //todo code here
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return businessRegisterCAPage;
    }
    
    /**
     *
     * @return
     */
    public String onSearch() {
        getGridInfo();
        CaUserDAOHE cahe = new CaUserDAOHE();
        GridResult gr = cahe.findCaUser(searchForm, start, count, sortField, getUserLogin());
        jsonDataGrid.setItems(gr.getLstResult());
        jsonDataGrid.setTotalRows(gr.getnCount().intValue());
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String deleteCA() {
        List resultMessage = new ArrayList();
        if (createForm != null) {
            try {
                CaUser caUserBo = new CaUser();

                String contentSigned = createForm.getContentSigned();
                KeyInfo keyInfo = CommonUtils.validateContentSigned(contentSigned);
                String serialNumber = null;
                if (keyInfo != null) {
                    try {
                        //validate serial
                        serialNumber = CommonUtils.getSerial(keyInfo);
                        CaUserDAOHE caUserDao = new CaUserDAOHE();
                        caUserBo = caUserDao.findById(createForm.getCaUserId());
                        if (caUserDao.checkCaSerial(serialNumber)) {
                            caUserBo.setStatus(0);
                            getSession().saveOrUpdate(caUserBo);
                            CaUserDAOHE cdhe = new CaUserDAOHE();
                            List<CaUser> caCheck = cdhe.findCaUserBySerial(serialNumber);
                            int check = 0;
                            for (int i = 0; i < caCheck.size(); i++) {
                                CaUser causer = caCheck.get(i);
                                if (causer.getStatus() == 0) {
                                    check++;
                                }
                            }

                            if (check == caCheck.size()) {
                                String b = " update Business b set b.isCa = 0 where b.businessId = ? and b.isActive = 1";
                                Query query1 = getSession().createQuery(b);
                                query1.setParameter(0, getBusinessId());
                                query1.executeUpdate();
                            }

                            resultMessage.add("1");
                            resultMessage.add("Xóa thành công");
                            jsonDataGrid.setItems(resultMessage);
                            return "gridData";
                        } else {
                            resultMessage.add("4");
                            resultMessage.add("Usb không chính xác");
                            jsonDataGrid.setItems(resultMessage);
                            return "gridData";
                        }

                    } catch (CertificateExpiredException expiredEx) {
                        log.error(expiredEx);
                    } catch (CertificateNotYetValidException notYetValidEx) {
                        log.error(notYetValidEx);
                    }
                } else {
                    resultMessage.add("0");
                    resultMessage.add("Phê duyệt không thành công. Dữ liệu ký không hợp lệ");
                    jsonDataGrid.setItems(resultMessage);
                    return "gridData";
                }

            } catch (Exception ex) {
                resultMessage.add("3");
                resultMessage.add("Lỗi");
                log.error(ex.getMessage());
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lỗi");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    /**
     *
     * @return
     */
    public String registerCA() {
        List resultMessage = new ArrayList();
        if (createForm != null) {
            try {
                CaUser caUserBo = new CaUser();
                String contentSigned = createForm.getContentSigned();
                KeyInfo keyInfo = CommonUtils.validateContentSigned(contentSigned);
                String serialNumber = null;
                if (keyInfo != null) {
                    try {
                        //validate serial
                        serialNumber = CommonUtils.getSerial(keyInfo);
                        CaUserDAOHE caUserDao = new CaUserDAOHE();
                        if (caUserDao.checkCaSerial(serialNumber)) {
                            resultMessage.add("4");
                            resultMessage.add("Trùng Serial");
                            jsonDataGrid.setItems(resultMessage);
                            return "gridData";
                        }

                    } catch (CertificateExpiredException expiredEx) {
                    } catch (CertificateNotYetValidException notYetValidEx) {
                    }
                } else {
                    resultMessage.add("0");
                    resultMessage.add("Phê duyệt không thành công. Dữ liệu ký không hợp lệ");
                    jsonDataGrid.setItems(resultMessage);
                    return "gridData";
                }
                caUserBo.setCaSerial(serialNumber);
                caUserBo.setUserName(getUserLogin());
                caUserBo.setStatus(1);
                caUserBo.setBusinessId(getBusinessId());
                getSession().saveOrUpdate(caUserBo);

                BusinessDAOHE bdhe = new BusinessDAOHE();
                Business bus = bdhe.findById(getBusinessId());
                bus.setIsCa(1l);
                getSession().update(bus);
                resultMessage.add("1");
                resultMessage.add("Đăng kí thành công");
            } catch (Exception ex) {
                resultMessage.add("3");
                resultMessage.add("Lỗi");
                log.error(ex.getMessage());
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lỗi");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String updateCAUser() {
        List resultMessage = new ArrayList();
        if (createForm != null) {
            try {
                CaUserDAOHE cudaohe = new CaUserDAOHE();
                CaUser caUserBo = cudaohe.findById(createForm.getCaUserId());
                caUserBo.setCommand(createForm.getCommand());
                caUserBo.setPosition(createForm.getPosition());
                caUserBo.setName(createForm.getName());
                caUserBo.setUpdatedAt(getSysdate());
                //a 16 07 29
                VoAttachsDAOHE vadaohe = new VoAttachsDAOHE();
                VoAttachs vobo = vadaohe.findById(createForm.getUploadId());
                if (vobo != null) {
                    caUserBo.setStamper(vobo.getAttachPath());
                    caUserBo.setSignature(vobo.getAttachPath());
                }
                //!a 16 07 29
                getSession().saveOrUpdate(caUserBo);
                resultMessage.add("1");
                resultMessage.add("Cập nhật chữ ký sô thành công");
            } catch (Exception ex) {
                resultMessage.add("3");
                resultMessage.add("Lỗi");
                log.error(ex.getMessage());
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lỗi");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public CaUserForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(CaUserForm searchForm) {
        this.searchForm = searchForm;
    }

    public CaUserForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(CaUserForm createForm) {
        this.createForm = createForm;
    }

    public List<CaUserForm> getLstItemOnGrid() {
        return lstItemOnGrid;
    }

    public void setLstItemOnGrid(List<CaUserForm> lstItemOnGrid) {
        this.lstItemOnGrid = lstItemOnGrid;
    }

//    public String getForwardPage() {
//        return forwardPage;
//    }
//
//    public void setForwardPage(String forwardPage) {
//        this.forwardPage = forwardPage;
//    }
    public CaUserDAOHE getCategoryTypeDao() {
        return caUserDao;
    }

    public void setCategoryTypeDao(CaUserDAOHE caUserDao) {
        this.caUserDao = caUserDao;
    }

    public String onDelete() throws Exception {
        List resultMessage = new ArrayList();
        try {
            CaUserDAOHE cthe = new CaUserDAOHE();
            for (int i = 0; i < lstItemOnGrid.size(); i++) {
                CaUserForm form = lstItemOnGrid.get(i);
                if (form != null && form.getCaUserId() != null && form.getCaUserId() != 0D) {
                    CaUser bo = cthe.getById("caUserId", form.getCaUserId());
                    if (bo != null) {
                        getSession().delete(bo);
                    }
                }
            }
            resultMessage.add("1");
            resultMessage.add("Xóa danh mục thành công");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            resultMessage.add("3");
            resultMessage.add("Xóa danh mục không thành công");
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    //"F:\SVN\ATTP CBSP\web\WEB-INF\jsp\pages\business\businessRegisterCA.jsp"
    public String toBusinessRegisterCA() {
        try {
            //todo code here
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return businessRegisterCAPage;
    }
}
