/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.controller;

import giang.errors.AllErrors;
import giang.tblDiscount.TblDiscountDAO;
import giang.tblInvoice.TblInvoiceDAO;
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
public class ApplyDiscountServlet extends HttpServlet {

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
        String action = request.getParameter("btnAction");
        PrintWriter out = response.getWriter();
        String url = CART_PAGE;
        try {
            HttpSession session = request.getSession(false);
            if (action.equals("Apply Discount Code")) {
                AllErrors error = new AllErrors();
                boolean foundErr = false;
                TblDiscountDAO ddao = new TblDiscountDAO();
                TblInvoiceDAO idao = new TblInvoiceDAO();
                String discountCode = request.getParameter("txtDiscount");
                String username = (String) session.getAttribute("USERNAME");
                Date now = new Date(System.currentTimeMillis());
                if (!discountCode.equals("")) {
                    //error here
                    if (!ddao.checkExistingDiscountCode(discountCode)) {
                        foundErr = true;
                        error.setDiscountCodeNotFound("There is no such discount code as '" + discountCode + "'. Please try another one.");
                    } else if (!ddao.checkExpiryDate(discountCode, now)) {
                        foundErr = true;
                        error.setDiscountCodeNotFound("");
                        error.setDiscountCodeIsExpired("The discount code " + discountCode + " is expired!");
                    } else if (!idao.checkDiscountUsage(username, discountCode)) {
                        foundErr = true;
                        error.setDiscountCodeIsExpired("");
                        error.setDiscountCodeIsUsed("This discount code can be used only once per account!");
                    }
                    if (foundErr) {
                        request.setAttribute("ERRORS", error);
                    } else {
                        float total = (float) session.getAttribute("TOTAL");
                        float newtotal = ddao.applyDiscount(discountCode, total);
                        session.setAttribute("DISCOUNTCODE", discountCode);
                        session.setAttribute("AFTERDISCOUNT", newtotal);
                    }
                }
            } else {
                session.removeAttribute("DISCOUNTCODE");
                session.removeAttribute("AFTERDISCOUNT");
            }
        } catch (NamingException ex) {
            Logger.getLogger(ApplyDiscountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ApplyDiscountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd  = request.getRequestDispatcher(url);
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
