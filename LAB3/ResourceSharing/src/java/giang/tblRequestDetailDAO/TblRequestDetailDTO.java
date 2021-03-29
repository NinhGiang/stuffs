/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblRequestDetailDAO;

import java.io.Serializable;

/**
 *
 * @author Ninh Giang
 */
public class TblRequestDetailDTO implements Serializable{

    private int requestID;
    private int resourceID;
    private String resourceName;
    private int quantity;

    public TblRequestDetailDTO() {
    }

    public TblRequestDetailDTO(int requestID, int resourceID, String resourceName, int quantity) {
        this.requestID = requestID;
        this.resourceID = resourceID;
        this.resourceName = resourceName;
        this.quantity = quantity;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
