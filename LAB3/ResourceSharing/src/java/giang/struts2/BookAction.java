/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.struts2;

import com.opensymphony.xwork2.ActionContext;
import giang.cart.CartObject;
import java.util.Map;

/**
 *
 * @author Ninh Giang
 */
public class BookAction {

    private String txtFullname;
    private int txtResourceID;
    private String txtResourceName;
    private int txtQuantity;
    private int pageNum;
    private String txtCategory;
    private String txtName;
    private String dateFrom;
    private String dateTo;
    private final String SUCCESS = "success";

    public BookAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String bookResource() throws Exception {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        CartObject cart = (CartObject) session.get("CART");
        if (cart == null) {
            cart = new CartObject();
        }
        if (txtQuantity != 0) {
            for (int i = 0; i < txtQuantity; i++) {
                cart.addToCart(txtResourceID);
            }
        }
        session.put("CART", cart);
        String url = SUCCESS;
        return url;
    }

    public String getTxtFullname() {
        return txtFullname;
    }

    public void setTxtFullname(String txtFullname) {
        this.txtFullname = txtFullname;
    }

    public int getTxtResourceID() {
        return txtResourceID;
    }

    public void setTxtResourceID(int txtResourceID) {
        this.txtResourceID = txtResourceID;
    }

    public String getTxtResourceName() {
        return txtResourceName;
    }

    public void setTxtResourceName(String txtResourceName) {
        this.txtResourceName = txtResourceName;
    }

    public int getTxtQuantity() {
        return txtQuantity;
    }

    public void setTxtQuantity(int txtQuantity) {
        this.txtQuantity = txtQuantity;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getTxtCategory() {
        return txtCategory;
    }

    public void setTxtCategory(String txtCategory) {
        this.txtCategory = txtCategory;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

}
