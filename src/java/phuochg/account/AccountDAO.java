/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuochg.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import phuochg.utils.DBHelper;

/**
 *
 * @author cunpl
 */
public class AccountDAO implements Serializable {

    public AccountDTO login(String Email, String Password) throws NamingException, SQLException {
        AccountDTO acc = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select Email, Password, RoleId, Status\n"
                        + "From tblAccount\n"
                        + "Where Email = ? and Password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, Email);
                pst.setString(2, Password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("Email");
                    String password = rs.getString("Password");
                    String roleId = rs.getString("RoleId");
                    String status = rs.getString("Status");
                    acc = new AccountDTO(email, password, roleId, status);
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
        return acc;
    }

    public boolean checkExistEmail(String Email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select Email, Password, RoleId, Status\n"
                        + "From tblAccount\n"
                        + "Where Email = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, Email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return true;
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
        return false;
    }

    public boolean insertAccount(AccountDTO acc) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "insert into tblAccount values(?,?,?,?);";
                pst = con.prepareStatement(sql);
                pst.setString(1, acc.getEmail());
                pst.setString(2, acc.getPassword());
                pst.setString(3, acc.getRoleId());
                pst.setString(4, acc.getStatus());
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

    public boolean updateAccountStatus(AccountDTO acc) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "update tblAccount \n"
                        + "set Status = ?\n"
                        + "Where Email = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, acc.getStatus());
                pst.setString(2, acc.getEmail());
                
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
}
