/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Hp
 */
public class Authorities {
    public boolean validateLogin(String userName, String password)
    {
        boolean success = false;
        String sql = "SELECT * FROM Authorities WHERE AUTH_USER_NAME = ? AND PASSWORD=?";
        
        try{
            ConnectionClass conC = new ConnectionClass();
            Connection con = conC.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userName);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {
                success = true;
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            
        }
        return success;
    }
}
