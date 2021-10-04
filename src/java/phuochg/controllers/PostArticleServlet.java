/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phuochg.article.ArticleDAO;
import phuochg.article.ArticleDTO;

/**
 *
 * @author cunpl
 */
public class PostArticleServlet extends HttpServlet {
    
    private static final String HOME_PAGE_USER = "SearchServlet?searchValue=";
    private static final String POST_ARTICLE = "postArticle.jsp";
    
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
            String titleName = request.getParameter("titleName");
            String email = request.getParameter("email");
            String contentId = request.getParameter("contentId");
            String description = request.getParameter("description");
            ArticleDTO article = new ArticleDTO(titleName, description, contentId, email, "", "New");
            ArticleDAO articleDao = new ArticleDAO();
            String msg = "";
            if(articleDao.insertArticle(article)){
                msg = "Post Success You can view process at View Request";
                url = HOME_PAGE_USER;
            }else{
                msg = "Post Fail try again";
                url = POST_ARTICLE;
            }
        } catch (Exception e) {
            log("Error at PostArticleServlet:"+e.toString());
        }finally{
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
