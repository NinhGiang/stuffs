/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.controller;

import giang.errors.AllErrors;
import giang.tblTour.TblTourDAO;
import giang.tblTour.TblTourDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
public class InsertTourServlet extends HttpServlet {

    private final String INSERT_PAGE = "addtour.jsp";
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
        String url = INSERT_PAGE;
        AllErrors error = new AllErrors();
        boolean foundErr = false;
        String tourName = request.getParameter("txtTourName");
        String fromPlace = request.getParameter("txtFromPlace");
        String toPlace = request.getParameter("txtToPlace");
        String fromDate = request.getParameter("txtFromDate");
        String toDate = request.getParameter("txtToDate");
        String price = request.getParameter("txtPrice");
        String quota = request.getParameter("txtQuota");
        String image = request.getParameter("txtImage");
        try {
            if (tourName.length() > 50) {
                foundErr = true;
                error.setTourNameOutofRange("The tour name must have no more than 50 characters!");
            }
            if (fromPlace.length() > 50) {
                foundErr = true;
                error.setFromPlaceOutofRange("The location name must have no more than 50 characters!");
            }
            if (toPlace.length() > 50) {
                foundErr = true;
                error.setToPlaceOutofRange("The location name must have no more than 50 characters!");
            }
            Date from = Date.valueOf(fromDate);
            Date to = Date.valueOf(toDate);
            if (to.before(from)) {
                foundErr = true;
                error.setToBeforeFrom("Incorrect date logic setup! Try to reverse the dates.");
            }
            if (!AllErrors.isNumber(price)) {
                foundErr = true;
                error.setInvalidPriceType("Price must be a decimal number!");
            } else if (Float.parseFloat(price) <= 0) {
                foundErr = true;
                error.setInvalidPriceType("Price must be a positive number!");
            }
            if (foundErr) {
                request.setAttribute("ERRORS", error);
            } else {
                Date date = new Date(System.currentTimeMillis());
                TblTourDAO dao = new TblTourDAO();
                TblTourDTO dto = new TblTourDTO(0, tourName, fromPlace, toPlace, from, to, Float.parseFloat(price), Integer.parseInt(quota), image, date, true);
                boolean result = dao.insertTour(dto);
                if (result) {
                    url = DASHBOARD_PAGE;
                }
            }
        } catch (NamingException ex) {
            log("InsertTourServlet _ Naming _ " + ex.getMessage());
        } catch (SQLException ex) {
            log("InsertTourServlet _ SQL _ " + ex.getMessage());
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
