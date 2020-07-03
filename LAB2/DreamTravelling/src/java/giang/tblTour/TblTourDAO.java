/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblTour;

import giang.utils.DBUtilities;
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
public class TblTourDAO implements Serializable {

    ArrayList<TblTourDTO> list;

    public ArrayList<TblTourDTO> getList() {
        return list;
    }

    public String getTourNamefromTourID(int tourID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select tourName "
                        + "from tblTour "
                        + "where tourID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, tourID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getString("tourName");
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

    public float getPricefromTourID(int tourID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select price "
                        + "from tblTour "
                        + "where tourID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, tourID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getFloat("price");
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

    public void searchTours(String from_date, String to_date, String from_price, String to_price, String place, int pageNum, int row_per_page) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                if (!from_date.equals("")) {
                    from_date = "fromDate >= '" + from_date + "' and ";
                }
                System.out.println(from_date);
                if (!to_date.equals("")) {
                    to_date = "toDate <= '" + to_date + "' and ";
                }
                System.out.println(to_date);
                if (!from_price.equals("")) {
                    from_price = "price >= " + from_price + " and ";
                }
                System.out.println(from_price);
                if (!to_price.equals("")) {
                    to_price = "price <= " + to_price + " and ";
                }
                System.out.println(to_price);
                if (!place.equals("")) {
                    place = "toPlace like " + "'%" + place + "%\' and ";
                }
                System.out.println(place);
                String sql = "select tourID, tourName, fromPlace, toPlace, "
                        + "fromDate, toDate, price, quota, image, dateImport, status "
                        + "from tblTour "
                        + "where " + from_date + to_date + from_price + to_price + place + "status = 'true' "
                        + "order by tourID asc "
                        + "offset (? - 1)*? rows "
                        + "fetch next ? rows only";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, pageNum);
                pstm.setInt(2, row_per_page);
                pstm.setInt(3, row_per_page);
                rs = pstm.executeQuery();
                System.out.println(sql);
                while (rs.next()) {
                    int tourID = rs.getInt("tourID");
                    String tourName = rs.getString("tourName");
                    String fromPlace = rs.getString("fromPlace");
                    String toPlace = rs.getString("toPlace");
                    Date fromDate = rs.getDate("fromDate");
                    Date toDate = rs.getDate("toDate");
                    float price = rs.getFloat("price");
                    int quota = rs.getInt("quota");
                    String image = rs.getString("image");
                    Date dateImport = rs.getDate("dateImport");
                    boolean status = rs.getBoolean("status");
                    TblTourDTO dto = new TblTourDTO(tourID, tourName, fromPlace, toPlace, fromDate, toDate, price, quota, image, dateImport, status);
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

    public int getSize(String from_date, String to_date, String from_price, String to_price, String place) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                if (!from_date.equals("")) {
                    from_date = "fromDate >= '" + from_date + "' and ";
                }
                System.out.println(from_date);
                if (!to_date.equals("")) {
                    to_date = "toDate <= '" + to_date + "' and ";
                }
                System.out.println(to_date);
                if (!from_price.equals("")) {
                    from_price = "price >= " + from_price + " and ";
                }
                System.out.println(from_price);
                if (!to_price.equals("")) {
                    to_price = "price <= " + to_price + " and ";
                }
                System.out.println(to_price);
                if (!place.equals("")) {
                    place = "toPlace like " + "'%" + place + "%\' and ";
                }
                System.out.println(place);
                String sql = "select tourID, tourName, fromPlace, toPlace, "
                        + "fromDate, toDate, price, quota, image, dateImport, status "
                        + "from tblTour "
                        + "where " + from_date + to_date + from_price + to_price + place + "status = 'true' ";
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    count++;
                }
                return count;
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

    public boolean insertTour(TblTourDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "insert into tblTour "
                        + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, dto.getTourName());
                pstm.setString(2, dto.getFromPlace());
                pstm.setString(3, dto.getToPlace());
                pstm.setDate(4, dto.getFromDate());
                pstm.setDate(5, dto.getToDate());
                pstm.setFloat(6, dto.getPrice());
                pstm.setInt(7, dto.getQuota());
                pstm.setString(8, dto.getImage());
                pstm.setDate(9, dto.getDateImport());
                pstm.setBoolean(10, true);
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
    
    public int getQuotafromTourID(int tourID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select quota "
                        + "from tblTour "
                        + "where tourID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, tourID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("quota");
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
    
    public boolean updateQuota(int tourID, int quota) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "update tblTour "
                        + "set quota = ? "
                        + "where tourID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, quota);
                pstm.setInt(2, tourID);
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
