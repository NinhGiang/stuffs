/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.tblQuestionDAO;

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
public class TblQuestionDAO implements Serializable {

    ArrayList<TblQuestionDTO> list;

    public ArrayList<TblQuestionDTO> getList() {
        return list;
    }

    public void searchKeyword(String searchValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select questionCode, questionContent, optionA, optionB, "
                        + "optionC, optionD, answer, subjectCode, isActive "
                        + "from tblQuestion "
                        + "where (questionContent like ? or questionCode like ?) and isActive = 1";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, "%" + searchValue + "%");
                pstm.setString(2, "%" + searchValue + "%");
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int questionCode = rs.getInt("questionCode");
                    String questionContent = rs.getString("questionContent");
                    String optionA = rs.getString("optionA");
                    String optionB = rs.getString("optionB");
                    String optionC = rs.getString("optionC");
                    String optionD = rs.getString("optionD");
                    String answer = rs.getString("answer");
                    String subjectCode = rs.getString("subjectCode");
                    boolean isActive = rs.getBoolean("isActive");
                    TblQuestionDTO dto = new TblQuestionDTO(questionCode, questionContent, optionA, optionB, optionC, optionD, answer, subjectCode, isActive);
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

    public boolean updateQuestion(TblQuestionDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "Update tblQuestion "
                        + "set questionContent = ?, optionA = ?, optionB = ?, "
                        + "optionC = ?, optionD = ?, answer = ?, subjectCode = ? "
                        + "where questionCode = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, dto.getQuestionContent());
                pstm.setString(2, dto.getOptionA());
                pstm.setString(3, dto.getOptionB());
                pstm.setString(4, dto.getOptionC());
                pstm.setString(5, dto.getOptionD());
                pstm.setString(6, dto.getAnswer());
                pstm.setString(7, dto.getSubjectCode());
                pstm.setInt(8, dto.getQuestionCode());
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

    public boolean deleteQuestion(int questionCode) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "update tblQuestion "
                        + "set isActive = 0 "
                        + "where questionCode = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, questionCode);
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

    public boolean addQuestion(TblQuestionDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "insert into tblQuestion "
                        + "values(?, ?, ?, ?, ?, ?, ?, ?)";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, dto.getQuestionContent());
                pstm.setString(2, dto.getOptionA());
                pstm.setString(3, dto.getOptionB());
                pstm.setString(4, dto.getOptionC());
                pstm.setString(5, dto.getOptionD());
                pstm.setString(6, dto.getAnswer());
                pstm.setString(7, dto.getSubjectCode());
                pstm.setBoolean(8, dto.isIsActive());
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

    public void getQuiz(String subjectCode, int quantity) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select top(?) questionCode, questionContent, optionA, optionB, "
                        + "optionC, optionD, answer "
                        + "from tblQuestion "
                        + "where subjectCode = ? and isActive = 1 "
                        + "order by NEWID()";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, quantity);
                pstm.setString(2, subjectCode);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int questionCode = rs.getInt("questionCode");
                    String questionContent = rs.getString("questionContent");
                    String optionA = rs.getString("optionA");
                    String optionB = rs.getString("optionB");
                    String optionC = rs.getString("optionC");
                    String optionD = rs.getString("optionD");
                    String answer = rs.getString("answer");
                    TblQuestionDTO dto = new TblQuestionDTO(questionCode, questionContent, optionA, optionB, optionC, optionD, answer, subjectCode, true);
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

    public int gradingSheet(int questionCode, String answer) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select questionCode "
                        + "from tblQuestion "
                        + "where questionCode = ? and answer = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, questionCode);
                pstm.setString(2, answer);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    count++;
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
        return count;
    }
}
