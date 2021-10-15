/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class ConnectionClass
{

    private String username;
    private String password;
    private final String CONN_STRING = "jdbc:oracle:thin:@localhost:1521:GLOBALDB";
    public Connection connection = null;

    public ConnectionClass()
    {
        this.username = "RHTonoy";
        this.password = "Tonoy123";
    }

    public ConnectionClass(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(CONN_STRING, username, password);
            } catch (SQLException e)
            {
                System.out.println("Connection Failed! Check it from console");
                e.printStackTrace();
            }
        }

        return connection;
    }

    public void closeConnection()
    {
        try
        {
            if (connection != null)
            {
                connection.close();
                connection = null;
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
