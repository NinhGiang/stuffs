/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblTour;
import java.sql.Date;

/**
 *
 * @author Ninh Giang
 */
public class TblTourDTO {
    private int tourID;
    private String tourName;
    private String fromPlace;
    private String toPlace;
    private Date fromDate;
    private Date toDate;
    private float price;
    private int quota;
    private String image;
    private Date dateImport;
    private boolean status;

    public TblTourDTO() {
    }

    public TblTourDTO(int tourID, String tourName, String fromPlace, String toPlace, Date fromDate, Date toDate, float price, int quota, String image, Date dateImport, boolean status) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.price = price;
        this.quota = quota;
        this.image = image;
        this.dateImport = dateImport;
        this.status = status;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateImport() {
        return dateImport;
    }

    public void setDateImport(Date dateImport) {
        this.dateImport = dateImport;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
