/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.comments;

import java.io.Serializable;
import java.sql.Connection;
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
public class CommentDAO implements Serializable {

    public List<CommentDTO> listComment(int titleId) throws NamingException, SQLException {
        List<CommentDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnect();
            if (conn != null) {
                String sql = "select TitleId, Email, CommentDes\n"
                        + "from tblComment\n"
                        + "where TitleId = ?";

                stm = conn.prepareStatement(sql);
                stm.setInt(1, titleId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("email");
                    String commentDes = rs.getString("commentDes");
                    CommentDTO comment = new CommentDTO(titleId, email, commentDes);
                    list.add(comment);
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
    
    public boolean insertComment(CommentDTO com) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if(con != null){
                String sql = "insert into tblComment values( ?, ?, ?);";
                pst = con.prepareStatement(sql);
                pst.setInt(1, com.getTitleId());
                pst.setString(2, com.getEmail());
                pst.setString(3, com.getCommentDes());
                
                if(pst.executeUpdate() > 0){
                    return true;
                }
                
            }
            
        }finally{
            if(pst != null){
                pst.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }
}
