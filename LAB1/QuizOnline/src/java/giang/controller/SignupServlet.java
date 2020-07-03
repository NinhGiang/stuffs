/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.controller;

import giang.tblUserDAO.TblUserDAO;
import giang.tblUserDAO.TblUserDTO;
import giang.tblUserDAO.UserErrors;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ninh Giang
 */
public class SignupServlet extends HttpServlet {

    private final String SIGNUP_PAGE = "signup.jsp";
    private final String LOGIN_PAGE = "login.html";
    private final String EMAIL_VALIDATION = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
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
        String url = SIGNUP_PAGE;
        UserErrors error = new UserErrors();
        boolean foundErr = false;
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        try {
            if (email.length() < 6 || email.length() > 30) {
                foundErr = true;
                error.setEmailOutofRange("The username must be from 6 to 30 characters!");
            }
            if (!email.matches(EMAIL_VALIDATION)) {
                foundErr = true;
                error.setInvalidEmailAddress("The username must be a valid email address!");
            }
            if (password.length() < 6 || password.length() > 20) {
                foundErr = true;
                error.setPasswordOutofRange("The password must be from 6 to 20 characters!");
            } else if (!confirm.equals(password)) {
                foundErr = true;
                error.setConfirmUnmatched("Password confirmation is unmatched!");
            }
            if (fullname.length() < 2 || fullname.length() > 50) {
                foundErr = true;
                error.setFullnameOutofRange("The fullname must be from 2 to 50 characters!");
            }
            if (foundErr) {
                request.setAttribute("ERRORS", error);
            } else {
                TblUserDAO dao = new TblUserDAO();
                TblUserDTO dto = new TblUserDTO(email, password, fullname, 2, 1);
                boolean result = dao.signupAccount(dto);
                if (result) {
                    url = LOGIN_PAGE;
                }
            }
        } catch (NamingException ex) {
            log("SignupServlet _ Naming _ " + ex.getMessage());
        } catch (SQLException ex) {
            log("SignupServlet _ SQL _ " + ex.getMessage());
            if (ex.getMessage().contains("duplicate")) {
                error.setDupUsername("Email " + email + " is existed!");
                request.setAttribute("ERRORS", error);
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
