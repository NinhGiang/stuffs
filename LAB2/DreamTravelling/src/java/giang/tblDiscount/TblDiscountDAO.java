/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblDiscount;

import giang.utils.DBUtilities;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Ninh Giang
 */
public class TblDiscountDAO {
    public ArrayList<String> getUntilNow(Date now) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ArrayList<String> list = new ArrayList<String>();
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select discountCode "
                        + "from tblDiscount "
                        + "where expiryDate >= ?";
                pstm = con.prepareStatement(sql);
                pstm.setDate(1, now);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString("discountCode"));
                }
                return list;
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
        return null;
    }
    
    public float applyDiscount(String discountCode, float total) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select discountType, amount "
                        + "from tblDiscount "
                        + "where discountCode = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, discountCode);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    int discountType = rs.getInt("discountType");
                    int amount = rs.getInt("amount");
                    switch (discountType) {
                        case 1:
                            if (total < amount) {
                                total = 0;
                            } else {
                                total = total - amount;
                            }
                            break;
                        case 2:
                            total = total - (total*amount)/100;
                            break;
                        default:
                            break;
                    }
                    return total;
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
        return 0;
    }
    
    public boolean checkExistingDiscountCode(String discountCode) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select discountCode "
                        + "from tblDiscount "
                        + "where discountCode = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, discountCode);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return true;
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
        return false;
    }
    
    public boolean checkExpiryDate(String discountCode, Date now) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select discountCode "
                        + "from tblDiscount "
                        + "where discountCode = ? and expiryDate >= ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, discountCode);
                pstm.setDate(2, now);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return true;
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
        return false;
    }
}
