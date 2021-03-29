/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.struts2;

import giang.tblUserDAO.TblUserDAO;
import giang.tblUserDAO.TblUserDTO;
import giang.utils.Errors;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ninh Giang
 */
public class RegisterAction {

    private final String SUCCESS = "success";
    private final String ERROR = "error";
    private final String FAIL = "fail";
//    private final String EMAIL_VALIDATION = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
//    private final String PHONE_VALIDATION = "/^\\d{10}$/";
    private String userID;
    private String password;
    private String confirm;
    private String fullname;
    private String phone;
    private String address;

    public RegisterAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String registerAccount() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        Errors errors = new Errors();
        boolean foundErr = false;
        String url = FAIL;
        TblUserDAO dao = new TblUserDAO();
        if (!password.equals(confirm)) {
            foundErr = true;
            errors.setConfirmUnmatched("Password confirmation in unmatched. Please check again!");
        }
        if (dao.checkDupUserID(userID)) {
            foundErr = true;
            errors.setDupEmail("This email address is used for another existing account. Please try another one!");
        }
        if (foundErr) {
            request.setAttribute("ERRORS", errors);
            url = ERROR;
        } else {
            request.removeAttribute("ERRORS");
            Date now = new Date(System.currentTimeMillis());
            long l = 0;
            if (!phone.equals("")) {
                l = Long.parseLong(phone);
            }
            TblUserDTO dto = new TblUserDTO(userID, password, fullname, l, address, 0, true, now);
            boolean result = dao.signupAccount(dto);
            if (result) {
                url = SUCCESS;
            }
        }
        return url;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
