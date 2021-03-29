/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblRequestDetailDAO;

import giang.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
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
public class TblRequestDetailDAO implements Serializable{

    List<TblRequestDetailDTO> list;

    public List<TblRequestDetailDTO> getList() {
        return list;
    }

    public boolean insertRequestDetail(TblRequestDetailDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into tblRequestDetail "
                        + "values(?, ?, ?, ?)";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, dto.getRequestID());
                pstm.setInt(2, dto.getResourceID());
                pstm.setString(3, dto.getResourceName());
                pstm.setInt(4, dto.getQuantity());
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

    public boolean getRequestDetails() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select requestID, resourceID, resourceName, quantity "
                        + "from tblRequestDetail ";
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int requestID = rs.getInt("requestID");
                    int resourceID = rs.getInt("resourceID");
                    String resourceName = rs.getString("resourceName");
                    int quantity = rs.getInt("quantity");
                    TblRequestDetailDTO dto = new TblRequestDetailDTO(requestID, resourceID, resourceName, quantity);
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
}
