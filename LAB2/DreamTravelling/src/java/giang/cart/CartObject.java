/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.cart;

import giang.tblTour.TblTourDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.NamingException;

/**
 *
 * @author Ninh Giang
 */
public class CartObject {

    private ArrayList<TourInCart> tourInCart;

    public CartObject() {
    }

    public ArrayList<TourInCart> getTourInCart() {
        return tourInCart;
    }

    public void setTourInCart(ArrayList<TourInCart> tourInCart) {
        this.tourInCart = tourInCart;
    }

    public void addToCart(int tourID) throws NamingException, SQLException {
        if (this.tourInCart == null) {
            this.tourInCart = new ArrayList<TourInCart>();
        }
        ArrayList<Integer> id = new ArrayList<Integer>();
        TblTourDAO dao = new TblTourDAO();
        for (TourInCart tour : this.tourInCart) {
            id.add(tour.getTourID());
        }
        if (id.contains(tourID)) {
            int newquantity = this.tourInCart.get(id.indexOf(tourID)).getQuantity() + 1;
            float newprice = dao.getPricefromTourID(tourID) * newquantity;
            this.tourInCart.get(id.indexOf(tourID)).setQuantity(newquantity);
            this.tourInCart.get(id.indexOf(tourID)).setPrice(newprice);
        } else {
            this.tourInCart.add(new TourInCart(tourID, dao.getTourNamefromTourID(tourID), dao.getPricefromTourID(tourID), 1));
        }
    }

    public void removeFromCart(int tourID) {
        if (this.tourInCart != null) {
            ArrayList<Integer> id = new ArrayList<Integer>();
            for (TourInCart tour : this.tourInCart) {
                id.add(tour.getTourID());
            }
            if (id.contains(tourID)) {
                this.tourInCart.remove(id.indexOf(tourID));
                if (this.tourInCart.isEmpty()) {
                    this.tourInCart = null;
                }
            }
        }
    }

    public void updateCart(int[] quantities) throws NamingException, SQLException {
        if (this.tourInCart != null) {
            TblTourDAO dao = new TblTourDAO();
            for (TourInCart tour : this.tourInCart) {
                tour.setQuantity(quantities[this.tourInCart.indexOf(tour)]);
                tour.setPrice(quantities[this.tourInCart.indexOf(tour)] * dao.getPricefromTourID(tour.getTourID()));
            }
        }
    }
}
