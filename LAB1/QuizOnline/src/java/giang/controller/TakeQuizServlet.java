/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.controller;

import giang.tblQuestionDAO.TblQuestionDAO;
import giang.tblQuestionDAO.TblQuestionDTO;
import giang.tblSubjectDAO.TblSubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ninh Giang
 */
public class TakeQuizServlet extends HttpServlet {
    private final String ERROR_PAGE = "error.html";
    private final String QUIZ_PAGE = "quiz.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ERROR_PAGE;
        try {
            String subjectCode = request.getParameter("cboSubjectCode");
            System.out.println(subjectCode);
            TblSubjectDAO sdao = new TblSubjectDAO();
            int quantity = sdao.getQuantity(subjectCode);
            System.out.println(quantity);
            int timer  = sdao.getTimer(subjectCode);
            System.out.println(timer);
            if (quantity != 0 && timer != 0) {
                TblQuestionDAO qdao = new TblQuestionDAO();
                qdao.getQuiz(subjectCode, quantity);
                ArrayList<TblQuestionDTO> questionList = qdao.getList();
                HttpSession session = request.getSession(false);
                session.setAttribute("QUESTIONLIST", questionList);
                session.setAttribute("QUIZSIZE", questionList.size());
                url = "DispatcherController?txtTimeNow=" + (timer*1000) + "&pageNum=0&cboSubjectCode=" + subjectCode + "&btnAction=Next";
            }
        } catch (SQLException ex) {
            log("TakeQuizServlet _ SQL _ " + ex.getMessage());
        } catch (NamingException ex) {
            log("TakeQuizServlet _ SQL _ " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
