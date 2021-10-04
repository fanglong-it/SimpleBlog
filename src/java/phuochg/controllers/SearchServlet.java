/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuochg.account.AccountDTO;
import phuochg.article.ArticleDAO;
import phuochg.article.ArticleDTO;

/**
 *
 * @author cunpl
 */
public class SearchServlet extends HttpServlet {

    private static final String HOME_PAGE_USER = "homeForUser.jsp";
    private static final String HOME_PAGE_ADMIN = "homeForAdmin.jsp";

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
        String url = HOME_PAGE_USER;
        try {
            String search = request.getParameter("searchValue");
            ArticleDAO articleDao = new ArticleDAO();

            HttpSession session = request.getSession();
            AccountDTO acc = (AccountDTO) session.getAttribute("ACC");
            List<ArticleDTO> list = null;
            String msg = "";
            
            
            //NumberOfPage
            int numberOfPage = articleDao.getNumberOfPage();
            
            //Paging
            int pageLoad;
            if (request.getParameter("page") == null) {
                pageLoad = 1;
            } else {
                pageLoad = Integer.parseInt(request.getParameter("page"));
            }
            
            double page = Math.ceil(articleDao.getCountAllArticle() / 1);
            
            request.setAttribute("page", page);
            if (acc == null) {
                list = articleDao.searchArticleUser(search, pageLoad, numberOfPage);
                msg = "Success";
            } else if (acc.getRoleId().equals("US")) {
                list = articleDao.searchArticleUser(search, pageLoad, numberOfPage);
                msg = "Success";
            } else if (acc.getRoleId().equals("AD")) {
                String option = request.getParameter("option");
                if (option == null || option.equals("")) {
                    list = articleDao.searchArticleAdmin(search, "", pageLoad);
                } else if (option.equals("New")) {
                    list = articleDao.searchArticleAdmin(search, "New", pageLoad);
                } else if (option.equals("Active")) {
                    list = articleDao.searchArticleAdmin(search, "Active", pageLoad);
                } else if (option.equals("Delete")) {
                    list = articleDao.searchArticleAdmin(search, "Delete", pageLoad);
                }
                msg = "Success";
            }
            if (!list.isEmpty()) {
                request.setAttribute("LIST_ARTICLE", list);
                if (acc.getRoleId().equals("AD")) {
                    url = HOME_PAGE_ADMIN;
                } else {
                    url = HOME_PAGE_USER;
                }
            }
            request.setAttribute("SEARCH_MSG", msg);

        } catch (Exception e) {
            log("Error at SearchServlet:" + e.toString());
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
