/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Educate;
import Entity.Session;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author FERIEL FADHLOUN
 */
public class ServiceNotification {
     final ObservableList<Session> listeob=FXCollections.observableArrayList();
    private Connection conn=DataSource.getInstance().getCon();
    private Statement st;
    private Statement ste2 ;
    private Statement ste3 ;
    private Statement ste4 ;
   
    public ServiceNotification() 
    {
        try 
        {
            st=conn.createStatement();
            ste2 =conn.createStatement();
            ste3 =conn.createStatement();
            ste4 =conn.createStatement();
        } 
        catch (SQLException ex) 
        {
           ex.printStackTrace();
        }
    }
    
    public void inscriptionClient(Educate e)
    {
        
        String sql="INSERT INTO Notification(dateIscri,etatEduc,idUser,idSes)VALUES(now(),'inscri',1,?);";
        try 
        {
            ;
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,e.getSession().getIdSes());
            ps.executeUpdate();
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
