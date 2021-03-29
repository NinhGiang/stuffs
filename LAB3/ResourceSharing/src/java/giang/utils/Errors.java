/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.utils;

/**
 *
 * @author Ninh Giang
 */
public class Errors {

    private String emailOutOfRange;
    private String invalidEmailAddress;
    private String dupEmail;
    private String passwordOutOfRange;
    private String confirmUnmatched;
    private String fullnameOutOfRange;
    private String invalidPhonetype;
    private String otherType;

    public String getEmailOutOfRange() {
        return emailOutOfRange;
    }

    public void setEmailOutOfRange(String emailOutOfRange) {
        this.emailOutOfRange = emailOutOfRange;
    }

    public String getInvalidEmailAddress() {
        return invalidEmailAddress;
    }

    public void setInvalidEmailAddress(String invalidEmailAddress) {
        this.invalidEmailAddress = invalidEmailAddress;
    }

    public String getDupEmail() {
        return dupEmail;
    }

    public void setDupEmail(String dupEmail) {
        this.dupEmail = dupEmail;
    }

    public String getPasswordOutOfRange() {
        return passwordOutOfRange;
    }

    public void setPasswordOutOfRange(String passwordOutOfRange) {
        this.passwordOutOfRange = passwordOutOfRange;
    }

    public String getConfirmUnmatched() {
        return confirmUnmatched;
    }

    public void setConfirmUnmatched(String confirmUnmatched) {
        this.confirmUnmatched = confirmUnmatched;
    }

    public String getFullnameOutOfRange() {
        return fullnameOutOfRange;
    }

    public void setFullnameOutOfRange(String fullnameOutOfRange) {
        this.fullnameOutOfRange = fullnameOutOfRange;
    }

    public String getInvalidPhonetype() {
        return invalidPhonetype;
    }

    public void setInvalidPhonetype(String invalidPhonetype) {
        this.invalidPhonetype = invalidPhonetype;
    }

    public String getOtherType() {
        return otherType;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType;
    }

    public Errors() {
    }
}
