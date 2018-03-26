/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Technique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author escobar
 */
public class DataSource {
    public static DataSource data ;
    public Connection con;
    String jdbcDriver ="com.mysql.jdbc.Driver";
    public String user="root";
    public String password="";
    public String url="jdbc:mysql://localhost:3306/cupcakes";
    private DataSource(){
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(url, user, password);
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static DataSource getInstance(){
    if(data == null)
        data = new DataSource();
    return data;
        }

    public Connection getCon() {
        return con;
    }

}
