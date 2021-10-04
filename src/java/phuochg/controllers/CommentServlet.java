/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuochg.account.AccountDTO;
import phuochg.comments.CommentDAO;
import phuochg.comments.CommentDTO;

/**
 *
 * @author cunpl
 */
public class CommentServlet extends HttpServlet {

    private static final String ARTICLE_DETAIL = "articleDetails.jsp";
    private static final String LOGIN_PAGE = "login.jsp";

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
        String url = ARTICLE_DETAIL;
        try {
            HttpSession session = request.getSession();
            AccountDTO acc = (AccountDTO) session.getAttribute("ACC");
            CommentDAO commentDao = new CommentDAO();
            int titleId = Integer.parseInt(request.getParameter("titleId"));

            String commentDes = request.getParameter("CommentDes");
            String msg = "";

            if (acc == null) {
                url = LOGIN_PAGE;
                String loadURL = "ViewArticleDetailServlet?titleId=" + titleId;
                session.setAttribute("LOAD_URL", loadURL);
            } else {
                if (commentDes.equals("")) {
                    msg = "Please fill out the comment!";
                } else if (commentDes == null) {
                    msg = "Please fill out the comment!";
                } else {
                    CommentDTO comment = new CommentDTO(titleId, acc.getEmail(), commentDes);
                    commentDao.insertComment(comment);
                    msg = "Success";
                    url = "ViewArticleDetailServlet?titleId=" +titleId;
                }
            }
            request.setAttribute("COMMENT_MSG", msg);

        } catch (Exception e) {
            log("Error at CommentServlet:" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
