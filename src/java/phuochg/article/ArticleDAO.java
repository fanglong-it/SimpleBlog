/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.article;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phuochg.utils.DBHelper;

/**
 *
 * @author cunpl
 */
public class ArticleDAO implements Serializable {

    public List<ArticleDTO> searchArticleUser(String searchValue, int indexPage, int numberOfPage) throws NamingException, SQLException {
        List<ArticleDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnect();
            if (conn != null) {
                String sql = "SELECT tblArticle.TitleID, tblArticle.TitleName, tblArticle.Description, tblArticle.Email, tblArticle.PostDate, tblContent.ContentName\n"
                        + "FROM tblArticle \n"
                        + "LEFT JOIN tblContent\n"
                        + "ON tblArticle.ContentID = tblContent.ContentId \n"
                        + "WHERE tblContent.ContentName LIKE ? and Status = 'Active' \n"
                        + "							order by PostDate\n"
                        + "							offset ? rows \n"
                        + "							FETCH NEXT ? rows only";

                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setInt(2, (indexPage - 1) * numberOfPage);
                stm.setInt(3, numberOfPage);
                //(page- 1) * số sản phẩm/1 trang
                rs = stm.executeQuery();
                while (rs.next()) {
                    int titleId = rs.getInt("TitleId");
                    String titleName = rs.getString("TitleName");
                    String description = rs.getString("Description");
                    String email = rs.getString("Email");
                    String postDate = rs.getString("PostDate");
                    String contentName = rs.getString("ContentName");
                    ArticleDTO article = new ArticleDTO(titleId, titleName, description, email, postDate, contentName);
                    list.add(article);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<ArticleDTO> searchArticleAdmin(String searchValue, String option, int indexPage) throws NamingException, SQLException {
        List<ArticleDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnect();
            if (conn != null) {
                String sql = "SELECT tblArticle.TitleId, tblArticle.TitleName, tblArticle.Description, tblArticle.Email, tblArticle.PostDate, tblContent.ContentName, tblArticle.Status\n"
                        + "FROM tblArticle\n"
                        + "LEFT JOIN tblContent\n"
                        + "ON tblArticle.ContentID = tblContent.ContentId\n"
                        + "WHERE tblArticle.TitleName like ? and tblArticle.Status like ? \n"
                        + "							order by PostDate\n"
                        + "							offset ? rows \n"
                        + "							FETCH NEXT 20 rows only";

                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, "%" + option + "%");
                stm.setInt(3, (indexPage - 1) * 20);
                //(page- 1) * số sản phẩm/1 trang
                rs = stm.executeQuery();
                while (rs.next()) {
                    int titleId = rs.getInt("TitleId");
                    String titleName = rs.getString("TitleName");
                    String description = rs.getString("Description");
                    String email = rs.getString("Email");
                    String postDate = rs.getString("PostDate");
                    String contentName = rs.getString("ContentName");
                    String status = rs.getString("Status");
                    ArticleDTO article = new ArticleDTO(titleId, titleName, description, contentName, email, postDate, status);
                    list.add(article);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public int getCountAllArticle() throws SQLException, NamingException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnect();
            String sql = "Select count(TitleId) as COUNT\n"
                    + "                    From tblArticle";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return count;
    }

    public ArticleDTO getArticleDetail(int titleId) throws SQLException, NamingException {
        ArticleDTO article = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "SELECT tblArticle.TitleId, tblArticle.TitleName, tblArticle.Description, tblArticle.Email, tblArticle.PostDate, tblContent.ContentName\n"
                        + "FROM tblArticle\n"
                        + "LEFT JOIN tblContent\n"
                        + "ON tblArticle.ContentID = tblContent.ContentId\n"
                        + "Where TitleId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, titleId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String titleName = rs.getString("TitleName");
                    String description = rs.getString("Description");
                    String email = rs.getString("Email");
                    String postDate = rs.getString("PostDate");
                    String contentName = rs.getString("ContentName");
                    article = new ArticleDTO(titleId, titleName, description, email, postDate, contentName);

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return article;
    }

    public boolean insertArticle(ArticleDTO article) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "insert into tblArticle values(?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, article.getTitleName());
                pst.setString(2, article.getDescription());
                pst.setString(3, article.getContentId());
                pst.setString(4, article.getEmail());
                //get current time
                Long milis = System.currentTimeMillis();
                Date date = new Date(milis);
                pst.setString(5, date.toString());
                pst.setString(6, article.getStatus());

                if (pst.executeUpdate() > 0) {
                    return true;
                }

            }

        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public List<ArticleDTO> listRequestArticleUser(String username) throws NamingException, SQLException {
        List<ArticleDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnect();
            if (conn != null) {
                String sql = "Select TitleName, Description, ContentId, Email, PostDate, Status \n"
                        + "from tblArticle\n"
                        + "where Email = ?";

                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                //(page- 1) * số sản phẩm/1 trang
                rs = stm.executeQuery();
                while (rs.next()) {
                    String titleName = rs.getString("TitleName");
                    String description = rs.getString("Description");
                    String contentId = rs.getString("ContentId");
                    String email = rs.getString("Email");
                    String postDate = rs.getString("PostDate");
                    String status = rs.getString("Status");
                    ArticleDTO article = new ArticleDTO(titleName, description, contentId, email, postDate, status);
                    list.add(article);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean updateArticle(String action, String titleId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "UPDATE tblArticle \n"
                        + "set Status = ? \n"
                        + "Where TitleId = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, action);
                pst.setString(2, titleId);

                if (pst.executeUpdate() > 0) {
                    return true;
                }

            }

        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public int getNumberOfPage() throws SQLException, NamingException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnect();
            String sql = "Select numberId, numberValue\n"
                    + "from tblNumberOfPage";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("numberValue");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean setNumberOfPage(int value) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBHelper.makeConnect();
            String sql = "update tblNumberOfPage\n"
                    + "set numberValue = ?\n"
                    + "where numberId = 1;";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, value);
            if(stm.executeUpdate() > 0 ){
                return true;
            }
        } finally {
            
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

}
