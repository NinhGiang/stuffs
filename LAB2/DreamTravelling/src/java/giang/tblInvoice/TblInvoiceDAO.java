/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblInvoice;

import giang.cart.TourInCart;
import giang.utils.DBUtilities;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Ninh Giang
 */
public class TblInvoiceDAO {

    public boolean checkOut(TourInCart cartObject, String username, String discountCode, float total, Date datePurchase) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "insert into tblInvoice "
                        + "values(?, ?, ?, ?, ?, ?)";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setInt(2, cartObject.getTourID());
                pstm.setInt(3, cartObject.getQuantity());
                pstm.setString(4, discountCode);
                pstm.setFloat(5, total);
                pstm.setDate(6, datePurchase);
                int row = pstm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean checkDiscountUsage(String username, String discountCode) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select invoiceID "
                        + "from tblInvoice "
                        + "where username = ? and discountCode = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setString(2, discountCode);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return false;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return true;
    }
}
