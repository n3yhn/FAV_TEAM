package com.viettel.vsaadmin.database.BO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DEPT_USER_POS")
public class DeptUserPos
        implements Serializable {

    private Long deptUserPosId;
    private Long posId;
    private Long userId;
    private Long deptId;
    private Long status;

    public DeptUserPos() {
    }

    public DeptUserPos(Long deptUserPosId, long posId, Long status) {
        this.deptUserPosId = deptUserPosId;
        this.posId = Long.valueOf(posId);
        this.status = status;
    }

    public DeptUserPos(Long posId, Long userId, Long deptId, Long status) {
        this.posId = posId;
        this.userId = userId;
        this.deptId = deptId;
        this.status = status;
    }

    @SequenceGenerator(name = "generator", sequenceName = "DEPT_USER_POS_SEQ", allocationSize = 1)
    @Id
    @Column(name = "DEPT_USER_POS_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    public Long getDeptUserPosId() {
        return this.deptUserPosId;
    }

    public void setDeptUserPosId(Long deptUserPosId) {
        this.deptUserPosId = deptUserPosId;
    }

    @Column(name = "POS_ID", nullable = false, precision = 10, scale = 0)
    public Long getPosId() {
        return this.posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    @Column(name = "DEPT_ID", nullable = false, precision = 10, scale = 0)
    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Column(name = "USER_ID", nullable = false, precision = 10, scale = 0)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "STATUS", nullable = false, precision = 1, scale = 0)
    public Long getStatus() {
        return this.status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}

/* Location:           C:\Program Files\Apache Software Foundation\TomcatVSA\webapps\vsaadminv3\WEB-INF\classes\
 * Qualified Name:     com.viettel.vsaadmin.database.BO.DeptUserPos
 * JD-Core Version:    0.6.0
 */
