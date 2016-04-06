/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAO;

import java.util.List;

/**
 *
 * @author havm2
 */
public class GridResult {
    Long nCount;
    List lstResult;
    
    public GridResult(){
        
    }
    
    public GridResult(Long nCount, List lstResult){
        this.nCount = nCount;
        this.lstResult = lstResult;
    }

    public GridResult(int nCount, List lstResult){
        this.nCount = Long.valueOf(nCount);
        this.lstResult = lstResult;
    }

    public List getLstResult() {
        return lstResult;
    }

    public void setLstResult(List lstResult) {
        this.lstResult = lstResult;
    }

    public Long getnCount() {
        return nCount;
    }

    public void setnCount(Long nCount) {
        this.nCount = nCount;
    }
}
