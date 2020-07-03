/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblUserDAO;

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
    public boolean checkLogin(String email, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select email, fullName, isRole, isStatus "
                        + "from tblUser "
                        + "where email = ? and password = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, email);
                pstm.setString(2, password);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }
    
    public String getFullnamefromEmail(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select fullName "
                        + "from tblUser "
                        + "where email = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, email);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getString("fullName");
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }
    
    public int getIsRolefromEmail(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select isRole "
                        + "from tblUser "
                        + "where email = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, email);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("isRole");
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return 0;
    }
    
    public boolean signupAccount(TblUserDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "insert into tblUser "
                        + "values(?, ?, ?, ?, ?)";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, dto.getEmail());
                pstm.setString(2, dto.getPassword());
                pstm.setString(3, dto.getFullName());
                pstm.setInt(4, dto.getIsRole());
                pstm.setInt(5, dto.getIsStatus());
                int row = pstm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        }
        return false;
    }
}
