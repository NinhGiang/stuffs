/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.struts2;

import com.opensymphony.xwork2.ActionContext;
import giang.tblUserDAO.TblUserDAO;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import giang.utils.VerifyRecaptcha;

/**
 *
 * @author Ninh Giang
 */
public class LoginAction {

    private String txtPassword;
    private String userID;
    private final String SUCCESS = "success";
    private final String REQUIREVERIFY = "requireverify";
    private final String SUCCESS_MANAGER = "successmanager";
    private final String FAIL = "fail";

    public LoginAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String checkLogin() throws Exception {
        //recaptcha
        String url = FAIL;
        HttpServletRequest request = ServletActionContext.getRequest();
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        //login
        TblUserDAO dao = new TblUserDAO();
        boolean result = dao.checkLogin(userID, txtPassword);
        if (result & verify) {
            url = SUCCESS;
            ActionContext ac = ActionContext.getContext();
            Map session = ac.getSession();
            String fullname = dao.getFullnamefromUserID(userID);
            session.put("FULLNAME", fullname);
            session.put("USERID", userID);
            String role = dao.getRoleNamefromUserID(userID);
            if (role.equals("New")) {
                url = REQUIREVERIFY;
            }
            if (role.equals("Manager")) {
                url = SUCCESS_MANAGER;
            }
            session.put("ROLE", role);
        }
        return url;
    }

    public String getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(String txtPassword) {
        this.txtPassword = txtPassword;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
}
