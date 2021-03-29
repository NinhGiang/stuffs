/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.struts2;

import com.opensymphony.xwork2.ActionContext;
import giang.cart.CartObject;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ninh Giang
 */
public class UpdateCartAction {

    private List<Integer> checkDelete;
    private List<Integer> resID;
    private List<Integer> newQuantity;
    private final String SUCCESS = "success";

    public UpdateCartAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String updateCart() throws Exception {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        CartObject cart = (CartObject) session.get("CART");
        if (cart == null) {
            cart = new CartObject();
        }
        if (!newQuantity.isEmpty()) {
            for (int i = 0; i < resID.size(); i++) {
                cart.updateCart(resID.get(i), newQuantity.get(i));
            }
        }
        if (checkDelete != null) {
            for (int i : resID) {
                if (checkDelete.contains(i)) {
                    cart.removeFromCart(i);
                }
            }
            this.checkDelete.clear();
        }
        if (cart.getResourceInCart() == null) {
            session.remove("CART");
        } else {
            session.put("CART", cart);
        }
        String url = SUCCESS;
        return url;
    }

    public List<Integer> getCheckDelete() {
        return checkDelete;
    }

    public void setCheckDelete(List<Integer> checkDelete) {
        this.checkDelete = checkDelete;
    }

    public List<Integer> getResID() {
        return resID;
    }

    public void setResID(List<Integer> resID) {
        this.resID = resID;
    }

    public List<Integer> getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(List<Integer> newQuantity) {
        this.newQuantity = newQuantity;
    }

}
