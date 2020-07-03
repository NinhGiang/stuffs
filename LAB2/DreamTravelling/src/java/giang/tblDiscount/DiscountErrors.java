/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblDiscount;

/**
 *
 * @author Ninh Giang
 */
public class DiscountErrors {
    private String codeNotFound;
    private String expired;
    private String alreadyUsed;

    public DiscountErrors() {
    }

    public String getCodeNotFound() {
        return codeNotFound;
    }

    public void setCodeNotFound(String codeNotFound) {
        this.codeNotFound = codeNotFound;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getAlreadyUsed() {
        return alreadyUsed;
    }

    public void setAlreadyUsed(String alreadyUsed) {
        this.alreadyUsed = alreadyUsed;
    }
    
}
