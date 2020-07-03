/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblQuizDAO;

import giang.utils.DBUtilities;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Ninh Giang
 */
public class TblQuizDAO implements Serializable {

    ArrayList<TblQuizDTO> list;

    public ArrayList<TblQuizDTO> getList() {
        return list;
    }

    public boolean saveQuiz(int quantity, int correctAnswers, float point, String subjectCode, Timestamp time, String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "insert into tblQuiz "
                        + "values(?, ?, ?, ?, ?, ?)";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, quantity);
                pstm.setInt(2, correctAnswers);
                pstm.setFloat(3, point);
                pstm.setString(4, subjectCode);
                pstm.setTimestamp(5, time);
                pstm.setString(6, email);
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
    
     public void searchHistory(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select quizCode, quantity, correctAnswers, point, "
                        + "subjectCode, takenTime "
                        + "from tblQuiz "
                        + "where email = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, email);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int quizCode = rs.getInt("quizCode");
                    int quantity = rs.getInt("quantity");
                    int correctAnswers = rs.getInt("correctAnswers");
                    float point = rs.getFloat("point");
                    String subjectCode = rs.getString("subjectCode");
                    Timestamp takenTime = rs.getTimestamp("takenTime");
                    TblQuizDTO dto = new TblQuizDTO(quizCode, quantity, correctAnswers, point, subjectCode, takenTime, email);
                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    list.add(dto);
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
    }

}
