/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.ws.BO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author E5420
 */
@XmlRootElement(name = "Error")
@XmlAccessorType(XmlAccessType.FIELD)
    public class ErrorWs {

        @XmlElement(name = "ErrorCode")
        private String ErrorCode;
        @XmlElement(name = "ErrorName")
        private String ErrorName;
        @XmlElement(name = "Solution")
        private String Solution;

        public ErrorWs() {

        }

        public String getErrorCode() {
            return ErrorCode;
        }

        public String getErrorName() {
            return ErrorName;
        }

        public String getSolution() {
            return Solution;
        }

        public void setErrorCode(String ErrorCode) {
            this.ErrorCode = ErrorCode;
        }

        public void setErrorName(String ErrorName) {
            this.ErrorName = ErrorName;
        }

        public void setSolution(String Solution) {
            this.Solution = Solution;
        }

    }
