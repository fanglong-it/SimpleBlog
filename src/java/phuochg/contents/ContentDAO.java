/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.contents;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phuochg.comments.CommentDTO;
import phuochg.utils.DBHelper;

/**
 *
 * @author cunpl
 */
public class ContentDAO implements Serializable {

    public List<ContentDTO> listContent() throws NamingException, SQLException {
        List<ContentDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnect();
            if (conn != null) {
                String sql = "Select ContentId, ContentName\n"
                        + "from tblContent";

                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String contentId = rs.getString("ContentId");
                    String contentName = rs.getString("contentName");
                    ContentDTO content = new ContentDTO(contentId, contentName);
                    list.add(content);
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
}
