/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblResourceDAO;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Ninh Giang
 */
public class TblResourceDTO implements Serializable {
    private int resourceID;
    private String name;
    private String category;
    private Date fromDate;
    private Date toDate;
    private String color;
    private int quantity;
    private boolean access;

    public TblResourceDTO() {
    }

    public TblResourceDTO(int resourceID, String name, String category, Date fromDate, Date toDate, String color, int quantity, boolean access) {
        this.resourceID = resourceID;
        this.name = name;
        this.category = category;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.color = color;
        this.quantity = quantity;
        this.access = access;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
}
