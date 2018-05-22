/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Educate;
import Entity.Session;
import Entity.SessionUser;
import Entity.Utilisateur;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    private Statement ste5 ;

    public ServiceEducate() 
    {
        try 
        {
            st=conn.createStatement();
            ste2 =conn.createStatement();
            ste3 =conn.createStatement();
            ste4 =conn.createStatement();
             ste5 =conn.createStatement();
        } 
        catch (SQLException ex) 
        {
           ex.printStackTrace();
        }
    }
    
    public void inscriptionClient(Educate e)
    {
        
        String sql="INSERT INTO Educate(dateIscri,etatEduc,idUser,idSes,etatNotif)VALUES(now(),'inscri',+"+SessionUser.getId()+",?,'non notifie');";
        try 
        {
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,e.getSession().getIdSes());
            ps.executeUpdate();
            
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        } 
    }
    
   
    public boolean ifClientExists(int idSes) throws SQLException
    {
        ResultSet rseducate=st.executeQuery("SELECT * FROM Educate WHERE idUser="+SessionUser.getId()+" AND idSes="+idSes);
        System.out.println("SELECT * FROM Educate WHERE idUser="+SessionUser.getId()+" AND idSes"+idSes);
        while(rseducate.next())
        {
            return true;
        }
        return false;
    }
    
    //pour selecter la liste des inscription lel client eli connecté
    public List<Educate> ListeInscriSession() throws SQLException 
    {
        ResultSet rseducate=st.executeQuery("SELECT * FROM Educate WHERE idUser="+SessionUser.getId());
        System.out.println("SELECT * FROM Educate WHERE idUser="+SessionUser.getId());
        List<Educate> listeeduacte=new ArrayList<Educate>();
        Educate educate=new Educate();
        while(rseducate.next())
        { 
            educate.setDateIscri(rseducate.getDate("dateIscri"));
            educate.setEtatEduc(rseducate.getString("etatEduc"));
            
            educate.setSession(new Session(rseducate.getInt("idSes")));
            educate.setUtilisateur(new Utilisateur(rseducate.getInt("idUser")));
            listeeduacte.add(educate);
        }
        return listeeduacte;
    }
    
    //chercher pour la notification
     public boolean SearchNonNotifie(int idSes,java.util.Date  date) throws SQLException 
    {
        ResultSet rseducate=ste5.executeQuery(
                "SELECT * FROM Session s,Educate e WHERE s.idSes=e.idSes AND e.etatNotif='non notifie' AND e.idSes="+idSes);
        System.out.println("SELECT * "+
                " FROM Session s,Educate e "+
                " WHERE s.idSes=e.idSes "+
                " AND e.etatNotif='non notifie'"+
                " AND s.dateDebSes="+date+
                " AND e.idSes="+idSes);
        List<Educate> listeeduacte=new ArrayList<Educate>();
        Educate educate=new Educate();
        while(rseducate.next())
        { 
            educate.setDateIscri(rseducate.getDate("dateIscri"));
            educate.setEtatEduc(rseducate.getString("etatEduc"));
            educate.setEtatNotif(rseducate.getString("etatNotif"));
            educate.setSession(new Session(rseducate.getInt("idSes")));
            educate.setUtilisateur(new Utilisateur(rseducate.getInt("idUser")));
            listeeduacte.add(educate);
           return true;
        }
        return false;
    }
     
     //modifier etat notification
     public void ModifierEtatNotif(Educate e,int idSession) {
        try 
        {
            String req ="update Educate set etatNotif='deja notifie' where idSes="+idSession+" AND idUser="+SessionUser.getId() ;
            System.out.println(req);
            PreparedStatement ps = conn.prepareStatement(req);                    
            ps.executeUpdate();
        } 
        catch (SQLException ex) 
        {
           ex.printStackTrace();
        }
        
         
    }
}
