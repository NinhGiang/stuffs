/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giang.controller;

import giang.tblQuestionDAO.TblQuestionDAO;
import giang.tblQuestionDAO.TblQuestionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ninh Giang
 */
public class UpdateServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";

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
            int questionCode = Integer.parseInt(request.getParameter("questionCode"));
            String questionContent = request.getParameter("questionContent");
            String optionA = request.getParameter("optionA");
            String optionB = request.getParameter("optionB");
            String optionC = request.getParameter("optionC");
            String optionD = request.getParameter("optionD");
            String answerchoice = request.getParameter("answerchoice");
            String subjectCode = request.getParameter("subjectCode");
            String lastSearchValue = request.getParameter("lastSearchValue");
            String pageNum = request.getParameter("pageNum");
            String answer = null;
            switch (answerchoice) {
                case "Option A":
                    answer = optionA;
                    break;
                case "Option B":
                    answer = optionB;
                    break;
                case "Option C":
                    answer = optionC;
                    break;
                case "Option D":
                    answer = optionD;
                    break;
                default:
                    break;
            }
            if (!(questionContent.trim().isEmpty() || optionA.trim().isEmpty() || optionB.trim().isEmpty()
                    || optionC.trim().isEmpty() || optionD.trim().isEmpty() || answer.trim().isEmpty() || subjectCode.trim().isEmpty())) {
                TblQuestionDAO dao = new TblQuestionDAO();
                TblQuestionDTO dto = new TblQuestionDTO(questionCode, questionContent, optionA, optionB, optionC, optionD, answer, subjectCode, true);
                boolean result = dao.updateQuestion(dto);
                if (result) {
                    if (pageNum != null) {
                        url = "DispatcherController?"
                                + "btnAction=Search&txtSearchValue="
                                + lastSearchValue + "&pageNum=" + pageNum;
                    } else {
                        url = "DispatcherController?"
                                + "btnAction=Search&txtSearchValue="
                                + lastSearchValue;
                    }
                }
            } else {
                url = "DispatcherController?"
                        + "btnAction=Search&"
                        + "txtSearchValue="
                        + lastSearchValue;
            }
        } catch (NamingException ex) {
            log("UpdateServlet _ Naming _ " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateServlet _ SQL _ " + ex.getMessage());
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
