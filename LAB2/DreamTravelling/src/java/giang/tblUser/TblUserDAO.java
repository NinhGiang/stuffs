/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblUser;

import giang.utils.DBUtilities;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Ninh Giang
 */
public class TblUserDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select username, fullName, role "
                        + "from tblUser "
                        + "where username = ? and password = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setString(2, password);
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

    public String getFullnamefromUsername(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select fullname "
                        + "from tblUser "
                        + "where username = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getString("fullname");
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
        return null;
    }

    public boolean getRolefromUsername(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select role "
                        + "from tblUser "
                        + "where username = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getBoolean("role");
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
