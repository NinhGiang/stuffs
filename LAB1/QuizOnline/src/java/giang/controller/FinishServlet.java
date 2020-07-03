/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.controller;

import giang.answersheet.AnswerSheet;
import giang.tblQuestionDAO.TblQuestionDAO;
import giang.tblQuizDAO.TblQuizDAO;
import giang.tblSubjectDAO.TblSubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ninh Giang
 */
public class FinishServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String RESULT_PAGE = "result.jsp";

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
            String timeNow = request.getParameter("txtTimeNow");
            String answer = request.getParameter("txtAnswer");
            String questionCode = request.getParameter("questionCode");
            String subjectCode = request.getParameter("cboSubjectCode");
            HttpSession session = request.getSession(false);
            String email = (String)session.getAttribute("EMAIL");
            if (timeNow != null) {
                request.setAttribute("TIMENOW", 0);
            }
            AnswerSheet as = (AnswerSheet) session.getAttribute("ANSWERSHEET");
            if (as == null) {
                as = new AnswerSheet();
            }
            if (questionCode != null && answer != null) {
                as.addToSheet(Integer.parseInt(questionCode), answer);
            }
            TblQuestionDAO qdao = new TblQuestionDAO();
            TblSubjectDAO sdao = new TblSubjectDAO();
            int correctAnswers = 0;
            float point = 0;
            if (as.getAnswers() != null) {
                for (int question : as.getAnswers().keySet()) {
                    correctAnswers += qdao.gradingSheet(question, as.getAnswers().get(question));
                }
                session.removeAttribute("ANSWERSHEET");
                point = (float) correctAnswers / (float) sdao.getQuantity(subjectCode) * 10;
            }
            request.setAttribute("QUIZRESULT", point);
            request.setAttribute("NUMOFCORRECTANSWER", correctAnswers);
            request.setAttribute("QUANTITY", sdao.getQuantity(subjectCode));
            Timestamp time = new Timestamp(System.currentTimeMillis());
            TblQuizDAO qzdao = new TblQuizDAO();
            qzdao.saveQuiz(sdao.getQuantity(subjectCode), correctAnswers, point, subjectCode, time, email);
            url = RESULT_PAGE;
        } catch (NamingException ex) {
            log("FinishServlet _ Naming _ " + ex.getMessage());
        } catch (SQLException ex) {
            log("FinishServlet _ SQL _ " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
