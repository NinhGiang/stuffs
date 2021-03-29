/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblRequestDAO;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Ninh Giang
 */
public class TblRequestDTO implements Serializable {
    private int requestID;
    private String userID;
    private String fullname;
    private Date date;
    private String status;

    public TblRequestDTO() {
    }

    public TblRequestDTO(int requestID, String userID, String fullname, Date date, String status) {
        this.requestID = requestID;
        this.userID = userID;
        this.fullname = fullname;
        this.date = date;
        this.status = status;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
