/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.controller;

import giang.tblUserDAO.TblUserDAO;
import giang.tblUserDAO.TblUserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ninh Giang
 */
public class StartAppServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
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
        String url = LOGIN_PAGE;
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                Cookie lastCookie = cookies[cookies.length - 1];
                String email = lastCookie.getName();
                String password = lastCookie.getValue();
                TblUserDAO dao = new TblUserDAO();
                TblUserDTO dto = new TblUserDTO();
                dto.setEmail(email);
                dto.setPassword(password);
                boolean result = dao.checkLogin(dto.getEmail(), dto.getPassword());
                if (result) {
                    HttpSession session = request.getSession();
                    String fullName = dao.getFullnamefromEmail(email);
                    session.setAttribute("FULLNAME", fullName);
                    session.setAttribute("EMAIL", dto.getEmail());
                    session.setAttribute("ROLE", dao.getIsRolefromEmail(email));
                    int roleCode = dao.getIsRolefromEmail(email);
                    if (roleCode == 1) {
                        session.setAttribute("ROLE", "Admin");
                    } else if (roleCode == 2) {
                        session.setAttribute("ROLE", "Student");
                    }
                    url = DASHBOARD_PAGE;
                }
            }
        } catch (SQLException ex) {
            log("StartAppServlet _ SQL _" + ex.getMessage());
        } catch (NamingException ex) {
            log("StartAppServlet _ Naming _ " + ex.getMessage());
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
