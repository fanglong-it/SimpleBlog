/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.contents;

import java.io.Serializable;

/**
 *
 * @author cunpl
 */
public class ContentDTO implements Serializable{
    private String contentId;
    private String contentName;

    public ContentDTO(String contentId, String contentName) {
        this.contentId = contentId;
        this.contentName = contentName;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }
    
    
}
