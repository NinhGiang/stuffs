/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.cart;

/**
 *
 * @author Ninh Giang
 */
public class ResourceInCart {
    private int resourceID;
    private String resourceName;
    private int quantity;
    private int currentquantity;

    public ResourceInCart(int reourceID, String resourceName, int quantity, int currentquantity) {
        this.resourceID = reourceID;
        this.resourceName = resourceName;
        this.quantity = quantity;
        this.currentquantity = currentquantity;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCurrentquantity() {
        return currentquantity;
    }

    public void setCurrentquantity(int currentquantity) {
        this.currentquantity = currentquantity;
    }
    
}
