/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.struts2;

import giang.tblUserDAO.TblUserDAO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ninh Giang
 */
public class VerifyAccAction {
    private String userID;
    private String txtConfirm;
    private String code;
    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    public VerifyAccAction() {
    }
    
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String verifyAccount() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("CONFIRM", "Your verification code doesn't match with the code we have sent.");
        if (code.equals(txtConfirm)) {
            TblUserDAO dao = new TblUserDAO();
            dao.updateAccount(userID);
            request.setAttribute("CONFIRM", "Confirm successful. Go back to login page to login and continue.");
        }
        return url;
    }
    public String getTxtConfirm() {
        return txtConfirm;
    }

    public void setTxtConfirm(String txtConfirm) {
        this.txtConfirm = txtConfirm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
