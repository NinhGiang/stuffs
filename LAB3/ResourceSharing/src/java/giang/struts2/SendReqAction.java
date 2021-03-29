/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.struts2;

import com.opensymphony.xwork2.ActionContext;
import giang.cart.CartObject;
import giang.cart.ResourceInCart;
import giang.tblRequestDAO.TblRequestDAO;
import giang.tblRequestDAO.TblRequestDTO;
import giang.tblRequestDetailDAO.TblRequestDetailDAO;
import giang.tblRequestDetailDAO.TblRequestDetailDTO;
import giang.tblResourceDAO.TblResourceDAO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ninh Giang
 */
public class SendReqAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public SendReqAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String sendRequest() throws Exception {
        String url = FAIL;
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        CartObject cart = (CartObject) session.get("CART");
        String userID = (String) session.get("USERID");
        String userName = (String) session.get("FULLNAME");
        Date now = new Date(System.currentTimeMillis());
        TblRequestDAO dao = new TblRequestDAO();
        TblRequestDTO dto = new TblRequestDTO(0, userID, userName, now, "New");
        dao.insertRequest(dto);
        int id = dao.getLastRequestID(userID, now);
        ArrayList<ResourceInCart> resourceInCart = cart.getResourceInCart();
        TblRequestDetailDAO ddao = new TblRequestDetailDAO();
        TblResourceDAO rdao = new TblResourceDAO();
        boolean foundErr = false;
        for (ResourceInCart r : resourceInCart) {
            if (rdao.getResourcebyID(r.getResourceID()).getQuantity() < r.getQuantity()) {
                foundErr = true;
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("ERROR", "There are not enough items " + r.getResourceName() + " for you at the moment. Try to reduce the amount!");
            }
        }
        if (!foundErr) {
            for (ResourceInCart r : resourceInCart) {
                TblRequestDetailDTO ddto = new TblRequestDetailDTO(id, r.getResourceID(), r.getResourceName(), r.getQuantity());
                ddao.insertRequestDetail(ddto);
            }
            session.remove("CART");
            url = SUCCESS;
        }
        return url;
    }
}
