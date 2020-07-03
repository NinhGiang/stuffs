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
public class AddQuestionServlet extends HttpServlet {

    private final String DASHBOARD_PAGE = "dashboard.jsp";
    private final String ADD_QUESTION_PAGE = "addquestion.jsp";

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
        String url = ADD_QUESTION_PAGE;
        String questionContent = request.getParameter("txtQuestionContent");
        String optionA = request.getParameter("txtOptionA");
        String optionB = request.getParameter("txtOptionB");
        String optionC = request.getParameter("txtOptionC");
        String optionD = request.getParameter("txtOptionD");
        String answerchoice = request.getParameter("cboAnswer");
        String subjectCode = request.getParameter("cboSubject");
        HttpSession session = request.getSession(false);
        try {
            String role = (String) session.getAttribute("ROLE");
            if (role.equals("Admin")) {
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
                TblQuestionDAO dao = new TblQuestionDAO();
                TblQuestionDTO dto = new TblQuestionDTO(0, questionContent, optionA, optionB, optionC, optionD, answer, subjectCode, true);
                boolean result = dao.addQuestion(dto);
                if (result) {
                    url = DASHBOARD_PAGE;
                }
            } else if (role.equals("Student")) {
                url = "addquestion.jsp";
            }
        } catch (NamingException ex) {
            log("AddQuestionServlet _ Naming _ " + ex.getMessage());
        } catch (SQLException ex) {
            log("AddQuestionServlet _ SQL _ " + ex.getMessage());
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
