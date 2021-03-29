/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
/**
 *
 * @author Ninh Giang
 */
public class LogoutAction {

    private final String SUCCESS = "success";

    public LogoutAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String checkLogout() {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        if (session != null) {
            session.remove("FULLNAME");
            session.clear();
        }
        String url = SUCCESS;
        return url;
    }
}
