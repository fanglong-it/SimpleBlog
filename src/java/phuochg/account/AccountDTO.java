/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.account;

import java.io.Serializable;

/**
 *
 * @author cunpl
 */
public class AccountDTO implements Serializable{
    private String email;
    private String password;
    private String roleId;
    private String status;

    public AccountDTO(String email, String password, String roleId, String status) {
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
