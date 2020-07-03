/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.errors;

import giang.tblTour.TblTourDAO;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Ninh Giang
 */
public class AllErrors {

    private String tourNameOutofRange;
    private String fromPlaceOutofRange;
    private String toPlaceOutofRange;
    private String toBeforeFrom;
    private String invalidPriceType;
    private String itemOutOfStock;
    private String discountCodeNotFound;
    private String discountCodeIsExpired;
    private String discountCodeIsUsed;

    public AllErrors() {
    }

    public String getTourNameOutofRange() {
        return tourNameOutofRange;
    }

    public void setTourNameOutofRange(String tourNameOutofRange) {
        this.tourNameOutofRange = tourNameOutofRange;
    }

    public String getFromPlaceOutofRange() {
        return fromPlaceOutofRange;
    }

    public void setFromPlaceOutofRange(String fromPlaceOutofRange) {
        this.fromPlaceOutofRange = fromPlaceOutofRange;
    }

    public String getToPlaceOutofRange() {
        return toPlaceOutofRange;
    }

    public void setToPlaceOutofRange(String toPlaceOutofRange) {
        this.toPlaceOutofRange = toPlaceOutofRange;
    }

    public String getToBeforeFrom() {
        return toBeforeFrom;
    }

    public void setToBeforeFrom(String toBeforeFrom) {
        this.toBeforeFrom = toBeforeFrom;
    }

    public String getInvalidPriceType() {
        return invalidPriceType;
    }

    public void setInvalidPriceType(String invalidPriceType) {
        this.invalidPriceType = invalidPriceType;
    }

    public String getItemOutOfStock() {
        return itemOutOfStock;
    }

    public void setItemOutOfStock(String itemOutOfStock) {
        this.itemOutOfStock = itemOutOfStock;
    }

    public String getDiscountCodeNotFound() {
        return discountCodeNotFound;
    }

    public void setDiscountCodeNotFound(String discountCodeNotFound) {
        this.discountCodeNotFound = discountCodeNotFound;
    }

    public String getDiscountCodeIsExpired() {
        return discountCodeIsExpired;
    }

    public void setDiscountCodeIsExpired(String discountCodeIsExpired) {
        this.discountCodeIsExpired = discountCodeIsExpired;
    }

    public String getDiscountCodeIsUsed() {
        return discountCodeIsUsed;
    }

    public void setDiscountCodeIsUsed(String discountCodeIsUsed) {
        this.discountCodeIsUsed = discountCodeIsUsed;
    }

    public static boolean isNumber(String price) {
        try {
            float result = Float.parseFloat(price);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public static int checkOutOfStock(int tourID, int quantity) throws NamingException, SQLException {
        TblTourDAO dao = new TblTourDAO();
        if (dao.getQuotafromTourID(tourID) < quantity) {
            return -1;
        } else {
            return dao.getQuotafromTourID(tourID) - quantity;
        }
    }
}
