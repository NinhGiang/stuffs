/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.cart;

import giang.tblTour.TblTourDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.NamingException;

/**
 *
 * @author Ninh Giang
 */
public class TourInCart {
    private int tourID;
    private String tourName;
    private float price;
    private int quantity;

    public TourInCart(int tourID, String tourName, float price, int quantity) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.price = price;
        this.quantity = quantity;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
