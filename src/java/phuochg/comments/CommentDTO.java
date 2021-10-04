/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.comments;

import java.io.Serializable;

/**
 *
 * @author cunpl
 */
public class CommentDTO implements Serializable {

    private int titleId;
    private String email;
    private String commentDes;

    public CommentDTO(int titleId, String email, String commentDes) {
        this.titleId = titleId;
        this.email = email;
        this.commentDes = commentDes;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommentDes() {
        return commentDes;
    }

    public void setCommentDes(String commentDes) {
        this.commentDes = commentDes;
    }

}
