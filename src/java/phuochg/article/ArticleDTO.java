/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.article;

import java.io.Serializable;

/**
 *
 * @author cunpl
 */
public class ArticleDTO implements Serializable {

    private int titleId;
    private String titleName;
    private String description;
    private String contentId;
    private String contentName;
    private String email;
    private String postDate;
    private String status;

    public ArticleDTO(int titleId, String titleName, String description, String contentId, String email, String postDate, String status) {
        this.titleId = titleId;
        this.titleName = titleName;
        this.description = description;
        this.contentId = contentId;
        this.email = email;
        this.postDate = postDate;
        this.status = status;
    }

    public ArticleDTO(int titleId, String titleName, String description, String email, String postDate, String contentName) {
        this.titleId = titleId;
        this.titleName = titleName;
        this.description = description;
        this.email = email;
        this.postDate = postDate;
        this.contentName = contentName;
    }

    public ArticleDTO(String titleName, String description, String contentId, String email, String postDate, String status) {
        this.titleName = titleName;
        this.description = description;
        this.contentId = contentId;
        this.email = email;
        this.postDate = postDate;
        this.status = status;
    }
    
    

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
