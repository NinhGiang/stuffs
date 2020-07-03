/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblUserDAO;

import giang.sha256.PasswordEncryptor;
import java.io.Serializable;

/**
 *
 * @author Ninh Giang
 */
public class TblUserDTO implements Serializable {
    private String email;
    private String password;
    private String fullName;
    private int isRole;
    private int isStatus;

    public TblUserDTO() {
    }

    public TblUserDTO(String email, String password, String fullName, int isRole, int isStatus) {
        this.email = email;
        this.password = PasswordEncryptor.getSHAHash(password);
        this.fullName = fullName;
        this.isRole = isRole;
        this.isStatus = isStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PasswordEncryptor.getSHAHash(password);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getIsRole() {
        return isRole;
    }

    public void setIsRole(int isRole) {
        this.isRole = isRole;
    }

    public int getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(int isStatus) {
        this.isStatus = isStatus;
    }
    
}
