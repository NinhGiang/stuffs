/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblRequestDAO;

import giang.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Ninh Giang
 */
public class TblRequestDAO implements Serializable{

    List<TblRequestDTO> list;

    public List<TblRequestDTO> getList() {
        return list;
    }

    public int getLastRequestID(String userID, Date now) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select requestID from tblRequest "
                        + "where userID = ? and date = ? and status != 'Inactive'";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, userID);
                pstm.setDate(2, now);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("requestID");
                    result = id;
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
        return result;
    }

    public boolean updateRequest(int requestID, String status) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update tblRequest "
                        + "set status = ? "
                        + "where requestID = ?";
                System.out.println(sql);
                pstm = con.prepareStatement(sql);
                pstm.setString(1, status);
                pstm.setInt(2, requestID);
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

    public boolean insertRequest(TblRequestDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into tblRequest "
                        + "values(?, ?, ?, ?)";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, dto.getUserID());
                pstm.setString(2, dto.getFullname());
                pstm.setDate(3, dto.getDate());
                pstm.setString(4, dto.getStatus());
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

    public void searchRequests(String from_date, String to_date, String resource_name, String user_name, String status, int pageNum, int row_per_page) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                if (!from_date.equals("")) {
                    from_date = "date >= '" + from_date + "' and ";
                }
                if (!to_date.equals("")) {
                    to_date = "date <= '" + to_date + "' and ";
                }
                if (!resource_name.equals("")) {
                    resource_name = "subquery.resourceName like " + "'%" + resource_name + "%\' and ";
                }
                if (!user_name.equals("")) {
                    user_name = "fullname like " + "'%" + user_name + "%\' and ";
                }
                if (!status.equals("")) {
                    status = "status = '" + status + "' and ";
                }
                String sql = "select distinct tblRequest.requestID, userID, fullname, [date], [status] "
                        + "from tblRequest, (select requestID, resourceName from tblRequestDetail) as subquery "
                        + "where status != 'Inactive' and " + from_date + to_date + status + resource_name + user_name + "tblRequest.requestID = subquery.requestID "
                        + "order by requestID asc "
                        + "offset (? - 1)*? rows "
                        + "fetch next ? rows only";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, pageNum);
                pstm.setInt(2, row_per_page);
                pstm.setInt(3, row_per_page);
                rs = pstm.executeQuery();
                System.out.println(sql);
                while (rs.next()) {
                    int requestID = rs.getInt("requestID");
                    String userID = rs.getString("userID");
                    String fullname = rs.getString("fullname");
                    Date date = rs.getDate("date");
                    String stt = rs.getString("status");
                    TblRequestDTO dto = new TblRequestDTO(requestID, userID, fullname, date, stt);
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

    public void searchRequestByUserID(String from_date, String to_date, String resource_name, String user_id, int pageNum, int row_per_page) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                if (!from_date.equals("")) {
                    from_date = "date >= '" + from_date + "' and ";
                }
                if (!to_date.equals("")) {
                    to_date = "date <= '" + to_date + "' and ";
                }
                if (!resource_name.equals("")) {
                    resource_name = "subquery.resourceName like " + "'%" + resource_name + "%\' and ";
                }
//                if (!user_id.equals("")) {
//                    user_id = "user like " + "'%" + user_name + "%\' and ";
//                }
                if (!user_id.equals("")) {
                    user_id = "userID = '" + user_id + "' and ";
                }
                String sql = "select distinct tblRequest.requestID, userID, fullname, [date], [status] "
                        + "from tblRequest, (select requestID, resourceName from tblRequestDetail) as subquery "
                        + "where status != 'Inactive' and " + from_date + to_date + resource_name + user_id + "tblRequest.requestID = subquery.requestID "
                        + "order by tblRequest.requestID asc "
                        + "offset (? - 1)*? rows "
                        + "fetch next ? rows only";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, pageNum);
                pstm.setInt(2, row_per_page);
                pstm.setInt(3, row_per_page);
                rs = pstm.executeQuery();
                System.out.println(sql);
                while (rs.next()) {
                    int requestID = rs.getInt("requestID");
                    String userID = rs.getString("userID");
                    String fullname = rs.getString("fullname");
                    Date date = rs.getDate("date");
                    String stt = rs.getString("status");
                    TblRequestDTO dto = new TblRequestDTO(requestID, userID, fullname, date, stt);
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

    public int getSize(String from_date, String to_date, String resource_name, String user_name, String status, int pageNum, int row_per_page) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                if (!from_date.equals("")) {
                    from_date = "date >= '" + from_date + "' and ";
                }
                if (!to_date.equals("")) {
                    to_date = "date <= '" + to_date + "' and ";
                }
                if (!resource_name.equals("")) {
                    resource_name = "subquery.resourceName like " + "'%" + resource_name + "%\' and ";
                }
                if (!user_name.equals("")) {
                    user_name = "fullname like " + "'%" + user_name + "%\' and ";
                }
                if (!status.equals("")) {
                    status = "status = '" + status + "' and ";
                }
                String sql = "select distinct tblRequest.requestID, userID, fullname, [date], [status] "
                        + "from tblRequest, (select requestID, resourceName from tblRequestDetail) as subquery "
                        + "where status != 'Inactive' and " + from_date + to_date + status + resource_name + user_name + status + "tblRequest.requestID = subquery.requestID ";
                pstm = con.prepareStatement(sql);
                System.out.println(sql);
                rs = pstm.executeQuery();
                System.out.println(sql);
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

    public int getSizeByUserID(String from_date, String to_date, String resource_name, String user_id, int pageNum, int row_per_page) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                if (!from_date.equals("")) {
                    from_date = "date >= '" + from_date + "' and ";
                }
                if (!to_date.equals("")) {
                    to_date = "date <= '" + to_date + "' and ";
                }
                if (!resource_name.equals("")) {
                    resource_name = "subquery.resourceName like " + "'%" + resource_name + "%\' and ";
                }
//                if (!user_name.equals("")) {
//                    user_name = "fullname like " + "'%" + user_name + "%\' and ";
//                }
                if (!user_id.equals("")) {
                    user_id = "userID = '" + user_id + "' and ";
                }
                String sql = "select distinct tblRequest.requestID, userID, fullname, [date], [status] "
                        + "from tblRequest, (select requestID, resourceName from tblRequestDetail) as subquery "
                        + "where status != 'Inactive' and " + from_date + to_date + resource_name + user_id + "tblRequest.requestID = subquery.requestID ";
                pstm = con.prepareStatement(sql);
                System.out.println(sql);
                rs = pstm.executeQuery();
                System.out.println(sql);
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

    public boolean loadRequests(int pageNum, int row_per_page) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select requestID, userID, fullname, date, status "
                        + "from tblRequest "
                        + "where requestID is not null and status != 'Inactive' "
                        + "order by date asc "
                        + "offset (? - 1)*? rows "
                        + "fetch next ? rows only";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, pageNum);
                pstm.setInt(2, row_per_page);
                pstm.setInt(3, row_per_page);
                rs = pstm.executeQuery();
                System.out.println(sql);
                while (rs.next()) {
                    int requestID = rs.getInt("requestID");
                    String userID = rs.getString("userID");
                    String fullname = rs.getString("fullname");
                    Date date = rs.getDate("date");
                    String stt = rs.getString("status");
                    TblRequestDTO dto = new TblRequestDTO(requestID, userID, fullname, date, stt);
                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    this.list.add(dto);
                }
                return true;
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

    public boolean deleteRequest(int requestID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update tblRequest set status = 'Inactive' "
                        + "where requestID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, requestID);
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
