/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.controller;

import giang.cart.CartObject;
import giang.cart.TourInCart;
import giang.errors.AllErrors;
import giang.tblInvoice.TblInvoiceDAO;
import giang.tblTour.TblTourDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
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
public class CheckOutServlet extends HttpServlet {

    private final String CART_PAGE = "cart.jsp";

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
        String url = CART_PAGE;
        try {
            HttpSession session = request.getSession(false);
            String username = (String) session.getAttribute("USERNAME");
            CartObject cart = (CartObject) session.getAttribute("CART");
            String discountCode = (String) session.getAttribute("DISCOUNTCODE");
            Float total = (Float) session.getAttribute("AFTERDISCOUNT");
            if (total == null) {
                total = (float) session.getAttribute("TOTAL");
            }
            AllErrors error = new AllErrors();
            boolean foundErr = false;

            for (TourInCart tour : cart.getTourInCart()) {
                if (AllErrors.checkOutOfStock(tour.getTourID(), tour.getQuantity()) == -1) {
                    foundErr = true;
                    error.setItemOutOfStock("The tour " + tour.getTourName() + "'s quota is now less than " + tour.getQuantity() + " items. "
                            + "Try to reduce the quantity!");
                }
            }
            if (foundErr) {
                request.setAttribute("ERRORS", error);
            } else {
                Date date = new Date(System.currentTimeMillis());
                TblInvoiceDAO idao = new TblInvoiceDAO();
                TblTourDAO tdao = new TblTourDAO();
                for (TourInCart tour : cart.getTourInCart()) {
                    idao.checkOut(tour, username, discountCode, total, date);
                    tdao.updateQuota(tour.getTourID(), AllErrors.checkOutOfStock(tour.getTourID(), tour.getQuantity()));
                }
                String confirm = "Successful Order!";
                session.removeAttribute("CART");
                session.removeAttribute("DISCOUNTCODE");
                session.removeAttribute("TOTAL");
                session.removeAttribute("AFTERDISCOUNT");
                request.setAttribute("CONFIRM", confirm);
            }
        } catch (NamingException ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
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
