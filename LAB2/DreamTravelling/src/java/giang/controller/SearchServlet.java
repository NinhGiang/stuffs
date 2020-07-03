/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.controller;

import giang.tblTour.TblTourDAO;
import giang.tblTour.TblTourDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SearchServlet extends HttpServlet {

    private final String DASHBOARD_PAGE = "dashboard.jsp";
    private final int ROW_PER_PAGE = 2;

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
        String url = DASHBOARD_PAGE;
        String fromDate = request.getParameter("txtFromDate");
        String toDate = request.getParameter("txtToDate");
        String fromPrice = request.getParameter("txtFromPrice");
        String toPrice = request.getParameter("txtToPrice");
        String place = request.getParameter("txtPlace");
        int pageNum = Integer.parseInt(request.getParameter("txtPageNum"));
        String searchButton = request.getParameter("btnAction");
        try {
            if (!(fromDate.trim().equals("")
                    && toDate.trim().equals("")
                    && fromPrice.trim().equals("")
                    && toPrice.trim().equals("")
                    && place.trim().equals(""))) {
                TblTourDAO dao = new TblTourDAO();
                dao.searchTours(fromDate, toDate, fromPrice, toPrice, place, pageNum, ROW_PER_PAGE);
                ArrayList<TblTourDTO> list = dao.getList();
                int size = dao.getSize(fromDate, toDate, fromPrice, toPrice, place);
                request.setAttribute("SEARCHRESULT", list);
                request.setAttribute("PAGEQUANTITY", ((size % ROW_PER_PAGE == 0) ? (size/ROW_PER_PAGE) : (size/ROW_PER_PAGE + 1)));
            }
        } catch (NamingException ex) {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
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
