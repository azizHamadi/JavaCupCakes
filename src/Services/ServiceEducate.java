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
import java.sql.ResultSet;
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
public class ServiceEducate {
    
     final ObservableList<Session> listeob=FXCollections.observableArrayList();
    private Connection conn=DataSource.getInstance().getCon();
    private Statement st;
    private Statement ste2 ;
    private Statement ste3 ;
    private Statement ste4 ;
    public ServiceEducate() 
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
        
        String sql="INSERT INTO Educate(dateIscri,etatEduc,idUser,idSes)VALUES(now(),'inscri',1,?);";
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
    
   
    public boolean ifClientExists(int idUser,int idSes) throws SQLException
    {
        ResultSet rseducate=st.executeQuery("SELECT * FROM Educate WHERE idUser="+idUser+" AND idSes="+idSes);
        System.out.println("SELECT * FROM Educate WHERE idUser="+idUser+" AND idSes"+idSes);
        while(rseducate.next())
        {
            return true;
        }
        return false;
    }
}
