/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblUserDAO;

import giang.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class TblUserDAO implements Serializable {

    public boolean checkDupUserID(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select userID "
                        + "from tblUser "
                        + "where userID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
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

    public boolean checkLogin(String username, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select userID, password, fullname "
                        + "from tblUser "
                        + "where userID = ? and password = ?";
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

    ArrayList<TblUserDTO> list;

    public ArrayList<TblUserDTO> getList() {
        return list;
    }

    public boolean deleteAccount(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "delete "
                        + "from tblUser "
                        + "where username = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
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

    public boolean updateAccount(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update tblUser "
                        + "set role = ? "
                        + "where userID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, 1);
                pstm.setString(2, username);
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

    public String getFullnamefromUserID(String userID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select fullname "
                        + "from tblUser "
                        + "where userID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, userID);
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

    public String getRoleNamefromUserID(String userID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select roleName "
                        + "from tblRole "
                        + "where roleID = ?";
                pstm = con.prepareStatement(sql);
                int role = getRolefromUserID(userID);
                pstm.setInt(1, role);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getString("roleName");
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

    public int getRolefromUserID(String userID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select role "
                        + "from tblUser "
                        + "where userID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, userID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("role");
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

    public boolean signupAccount(TblUserDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into tblUser "
                        + "values(?, ?, ?, ?, ?, ?, ?, ?)";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, dto.getUserID());
                pstm.setString(2, dto.getPassword());
                pstm.setString(3, dto.getFullname());
                if (dto.getPhone() == 0) {
                    pstm.setNull(4, 0);
                } else {
                    pstm.setLong(4, dto.getPhone());
                }
                if (dto.getAddress().equals("")) {
                    pstm.setNull(5, 0);
                } else {
                    pstm.setString(5, dto.getAddress());
                }
                pstm.setInt(6, dto.getRole());
                pstm.setBoolean(7, dto.isStatus());
                pstm.setDate(8, dto.getDate());
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
}
