/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.controller;

import giang.answersheet.AnswerSheet;
import giang.tblQuestionDAO.TblQuestionDTO;
import giang.utils.Pagination;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class PaginatePrevServlet extends HttpServlet {

    private final int ROW_PER_PAGE = 20;
    private final String ERROR_PAGE = "error.html";
    private final String QUIZ_PAGE = "quiz.jsp";
    private final String DASHBOARD_PAGE = "dashboard.jsp";

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
            /* TODO output your page here. You may use following sample code. */
            int currentPage = Integer.parseInt(request.getParameter("pageNum"));
            String answer = request.getParameter("txtAnswer");
            String subjectCode = request.getParameter("cboSubjectCode");
            String questionCode = request.getParameter("questionCode");
            String timeNow = request.getParameter("txtTimeNow");
            HttpSession session = request.getSession(false);
            
            if (subjectCode != null) {
                ArrayList<TblQuestionDTO> dtoList = (ArrayList<TblQuestionDTO>) session.getAttribute("QUESTIONLIST");
                ArrayList<TblQuestionDTO> pagingResult = Pagination.getPagingQuestion(dtoList, 1, currentPage - 1);
                request.setAttribute("PAGINGRESULT", pagingResult);
                request.setAttribute("CURRENTPAGE", currentPage - 1);
                if (timeNow != null) {
                    request.setAttribute("TIMENOW", timeNow);
                }
                AnswerSheet as = (AnswerSheet) session.getAttribute("ANSWERSHEET");
                if (as == null) {
                    as = new AnswerSheet();
                }
                if (questionCode != null && answer != null) {
                    as.addToSheet(Integer.parseInt(questionCode), answer);
                }
                if (as.getAnswers() != null) {
                    for (int question : as.getAnswers().keySet()) {
                        System.out.println(question);
                        if (question == pagingResult.get(0).getQuestionCode()) {
                            System.out.println("quest Next" + question);
                            request.setAttribute("CURRENTOPTION", as.getAnswers().get(question));
                            System.out.println("ans Next" + as.getAnswers().get(question));
                        }
                    }
                }
                session.setAttribute("ANSWERSHEET", as);
                url = QUIZ_PAGE;
            } else {
                ArrayList<TblQuestionDTO> dtoList = (ArrayList<TblQuestionDTO>) session.getAttribute("SEARCHRESULT");
                ArrayList<TblQuestionDTO> pagingResult = Pagination.getPagingQuestion(dtoList, ROW_PER_PAGE, currentPage - 1);
                request.setAttribute("PAGINGRESULT", pagingResult);
                request.setAttribute("CURRENTPAGE", currentPage - 1);
                url = DASHBOARD_PAGE;
            }
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
