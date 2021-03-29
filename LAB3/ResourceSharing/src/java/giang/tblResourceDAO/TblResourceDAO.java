/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblResourceDAO;

import giang.utils.DBHelper;
import java.io.Serializable;
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
public class TblResourceDAO implements Serializable {

    ArrayList<TblResourceDTO> list;

    public ArrayList<TblResourceDTO> getList() {
        return list;
    }

    public TblResourceDTO getResourcebyID(int resource_id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select resourceID, name, category, "
                        + "fromDate, toDate, color, quantity, access "
                        + "from tblResource "
                        + "where isActive = 'true' and resourceID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, resource_id);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    int resourceID = rs.getInt("resourceID");
                    String resourceName = rs.getString("name");
                    String resourceCategory = rs.getString("category");
                    Date fromDate = rs.getDate("fromDate");
                    Date toDate = rs.getDate("toDate");
                    String color = rs.getString("color");
                    int quantity = rs.getInt("quantity");
                    boolean access = rs.getBoolean("access");
                    TblResourceDTO dto = new TblResourceDTO(resourceID, resourceName, resourceCategory, fromDate, toDate, color, quantity, access);
                    return dto;
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

    public void searchResources(String category, String name, String from_date, String to_date, int pageNum, int row_per_page) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                if (!from_date.equals("")) {
                    from_date = "fromDate >= '" + from_date + "' and ";
                }
                if (!to_date.equals("")) {
                    to_date = "toDate <= '" + to_date + "' and ";
                }
                if (!category.equals("")) {
                    category = "category like " + "'%" + category + "%\' and ";
                }
                if (!name.equals("")) {
                    name = "name like " + "'%" + name + "%\' and ";
                }
                String sql = "select resourceID, name, category, "
                        + "fromDate, toDate, color, quantity, access "
                        + "from tblResource "
                        + "where " + from_date + to_date + category + name + "isActive = 'true' and quantity > 0 "
                        + "order by resourceID asc "
                        + "offset (? - 1)*? rows "
                        + "fetch next ? rows only";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, pageNum);
                pstm.setInt(2, row_per_page);
                pstm.setInt(3, row_per_page);
                rs = pstm.executeQuery();
                System.out.println(sql);
                while (rs.next()) {
                    int resourceID = rs.getInt("resourceID");
                    String resourceName = rs.getString("name");
                    String resourceCategory = rs.getString("category");
                    Date fromDate = rs.getDate("fromDate");
                    Date toDate = rs.getDate("toDate");
                    String color = rs.getString("color");
                    int quantity = rs.getInt("quantity");
                    boolean access = rs.getBoolean("access");
                    TblResourceDTO dto = new TblResourceDTO(resourceID, resourceName, resourceCategory, fromDate, toDate, color, quantity, access);
                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    this.list.add(dto);
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
    }

    public int getSize(String category, String name, String from_date, String to_date, int pageNum, int row_per_page) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                if (!from_date.equals("")) {
                    from_date = "fromDate >= '" + from_date + "' and ";
                }
                if (!to_date.equals("")) {
                    to_date = "toDate <= '" + to_date + "' and ";
                }
                if (!category.equals("")) {
                    category = "category like " + "'%" + category + "%\' and ";
                }
                if (!name.equals("")) {
                    name = "name like " + "'%" + name + "%\' and ";
                }
                String sql = "select resourceID "
                        + "from tblResource "
                        + "where " + from_date + to_date + category + name + "isActive = 'true' and quantity > 0 ";
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    count++;
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
        return count;
    }

    public boolean updateResource(int resourceID, int quan) throws NamingException, SQLException {
        TblResourceDTO dto = this.getResourcebyID(resourceID);
        int old = dto.getQuantity();
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update tblResource "
                        + "set quantity = ? "
                        + "where resourceID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, (old - quan));
                pstm.setInt(2, resourceID);
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
