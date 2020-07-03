/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ninh Giang
 */
public class DispatcherController extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String START_APP_SERVLET = "StartAppServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String SIGNUP_SERVLET = "SignupServlet"; 
    private final String SEARCH_SERVLET = "SearchServlet";
    private final String UPDATE_SERVLET = "UpdateServlet";
    private final String DELETE_SERVLET = "DeleteServlet";
    private final String ADD_QUESTION_SERVLET = "AddQuestionServlet";
    private final String TAKE_QUIZ_SERVLET = "TakeQuizServlet";
    private final String PAGINATE_SERVLET = "PaginateServlet";
    private final String PAGINATE_PREV_SERVLET = "PaginatePrevServlet";
    private final String FINISH_SERVLET = "FinishServlet";
    private final String QUIZ_PAGE = "quiz.jsp";
    private final String VIEW_HISTORY_SERVLET = "ViewHistoryServlet";
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
        String button = request.getParameter("btnAction");
        String url = LOGIN_PAGE;
        try {
            if (button == null) {
                url = START_APP_SERVLET;
            }
            else if (button.equals("Login")) {
                url = LOGIN_SERVLET;
            }
            else if (button.equals("Logout")) {
                url = LOGOUT_SERVLET;
            }
            else if (button.equals("Signup")) {
                url = SIGNUP_SERVLET;
            }
            else if (button.equals("Search")) {
                url = SEARCH_SERVLET;
            }
            else if (button.equals("Update")) {
                url = UPDATE_SERVLET;
            }
            else if (button.equals("Delete")) {
                url = DELETE_SERVLET;
            }
            else if (button.equals("Add")) {
                url = ADD_QUESTION_SERVLET;
            }
            else if (button.equals("Start")) {
                url = TAKE_QUIZ_SERVLET;
            }
            else if (button.equals("Next")) {
                url = PAGINATE_SERVLET;
            }
            else if (button.equals("Previous")) {
                url = PAGINATE_PREV_SERVLET;
            }
            else if (button.equals("Finish")) {
                url = FINISH_SERVLET;
            }
            else if (button.equals("QuizPage")) {
                url = QUIZ_PAGE;
            }
            else if (button.equals("ViewHistory")) {
                url = VIEW_HISTORY_SERVLET;
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
