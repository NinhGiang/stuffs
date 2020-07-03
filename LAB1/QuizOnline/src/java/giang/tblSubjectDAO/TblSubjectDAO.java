/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblSubjectDAO;

import giang.utils.DBUtilities;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Ninh Giang
 */
public class TblSubjectDAO implements Serializable {

    ArrayList<String> list;

    public ArrayList<String> getList() {
        return list;
    }

    public boolean loadSubject() throws SQLException, NamingException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select subjectCode from tblSubject";
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    list.add(rs.getString("subjectCode"));
                    result = true;
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
        return result;
    }
    
    public int getQuantity(String subjectCode) throws SQLException, NamingException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select quantity from tblSubject where subjectCode = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, subjectCode);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    return rs.getInt("quantity");
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
    
    public int getTimer(String subjectCode) throws SQLException, NamingException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select timer from tblSubject where subjectCode = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, subjectCode);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    return rs.getInt("timer");
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
}
