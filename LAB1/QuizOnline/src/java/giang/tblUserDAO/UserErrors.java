/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblUserDAO;

/**
 *
 * @author ADMIN
 */
public class UserErrors {
    private String emailOutofRange;
    private String passwordOutofRange;
    private String confirmUnmatched;
    private String fullnameOutofRange;
    private String dupUsername;
    private String invalidEmailAddress;

    public UserErrors() {
    }

    public String getEmailOutofRange() {
        return emailOutofRange;
    }

    public void setEmailOutofRange(String usernameOutofRange) {
        this.emailOutofRange = usernameOutofRange;
    }

    public String getInvalidEmailAddress() {
        return invalidEmailAddress;
    }

    public void setInvalidEmailAddress(String invalidEmailAddress) {
        this.invalidEmailAddress = invalidEmailAddress;
    }

    public String getPasswordOutofRange() {
        return passwordOutofRange;
    }

    public void setPasswordOutofRange(String passwordOutofRange) {
        this.passwordOutofRange = passwordOutofRange;
    }

    public String getConfirmUnmatched() {
        return confirmUnmatched;
    }

    public void setConfirmUnmatched(String confirmUnmatched) {
        this.confirmUnmatched = confirmUnmatched;
    }

    public String getFullnameOutofRange() {
        return fullnameOutofRange;
    }

    public void setFullnameOutofRange(String fullnameOutofRange) {
        this.fullnameOutofRange = fullnameOutofRange;
    }

    public String getDupUsername() {
        return dupUsername;
    }

    public void setDupUsername(String dupUsername) {
        this.dupUsername = dupUsername;
    }
    
}
