/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.cart;

import giang.tblResourceDAO.TblResourceDAO;
import giang.tblResourceDAO.TblResourceDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Ninh Giang
 */
public class CartObject {

    private ArrayList<ResourceInCart> resourceInCart;

    public CartObject() {
    }

    public ArrayList<ResourceInCart> getResourceInCart() {
        return resourceInCart;
    }

    public void setResourceInCart(ArrayList<ResourceInCart> resourceInCart) {
        this.resourceInCart = resourceInCart;
    }

    public void addToCart(int resourceID) throws NamingException, SQLException {
        if (this.resourceInCart == null) {
            this.resourceInCart = new ArrayList<ResourceInCart>();
        }
        ArrayList<Integer> id = new ArrayList<Integer>();
        TblResourceDAO dao = new TblResourceDAO();
        for (ResourceInCart resource : this.resourceInCart) {
            id.add(resource.getResourceID());
        }
        if (id.contains(resourceID)) {
            int newquantity = this.resourceInCart.get(id.indexOf(resourceID)).getQuantity() + 1;
            this.resourceInCart.get(id.indexOf(resourceID)).setQuantity(newquantity);
        } else {
            TblResourceDTO dto = dao.getResourcebyID(resourceID);
            this.resourceInCart.add(new ResourceInCart(resourceID, dto.getName(), 1, dto.getQuantity()));
        }
    }

    public void removeFromCart(int resourceID) {
        if (this.resourceInCart != null) {
            ArrayList<Integer> id = new ArrayList<Integer>();
            for (ResourceInCart resource : this.resourceInCart) {
                id.add(resource.getResourceID());
            }
            if (id.contains(resourceID)) {
                this.resourceInCart.remove(id.indexOf(resourceID));
                if (this.resourceInCart.isEmpty()) {
                    this.resourceInCart = null;
                }
            }
        }
    }

    public void updateCart(int resourceID, int newQuantity) throws NamingException, SQLException {
        if (this.resourceInCart != null) {
            ArrayList<Integer> id = new ArrayList<Integer>();
            for (ResourceInCart resource : this.resourceInCart) {
                id.add(resource.getResourceID());
            }
            if (id.contains(resourceID)) {
                this.resourceInCart.get(id.indexOf(resourceID)).setQuantity(newQuantity);
                if (this.resourceInCart.isEmpty()) {
                    this.resourceInCart = null;
                }
            }
        }
    }
}
