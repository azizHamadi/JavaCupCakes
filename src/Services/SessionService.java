/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Formation;
import Entity.Session;
import Entity.TypeFormation;
import Entity.Utilisateur;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamdi fathallah
 */
public class SessionService {
    
      
    
    private Connection con=DataSource.getInstance().getCon();
    private Statement st;
    private Statement ste2 ;
    private Statement ste3 ;
    private Statement ste4 ;
    public SessionService() 
    {
        try 
        {
            st=con.createStatement();
            ste2 =con.createStatement();
            ste3 =con.createStatement();
            ste4 =con.createStatement();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //ajouter formation
    public void insertSession(Session s)
    {
        String sql="INSERT INTO Session"+
                "(dateDebSes,dateFinSes,capaciteSes,etatSes,imagesess,prix_ses,nv_prix_ses,nomSes,idFor)"+
                "VALUES(?,?,?,?,null,?,?,?,?);";
        try 
        {
            
          
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setDate(1, new Date(s.getDateDebSes().getTime()));
                ps.setDate(2, new Date(s.getDateFinSes().getTime()));
                ps.setInt(3, s.getCapaciteSes());
                ps.setString(4, s.getEtatSes());
                ps.setDouble(5, s.getPrixSes());
                ps.setDouble(6, s.getNvPrixSes());
                ps.setString(7, s.getNomSes());
                ps.setInt(8, s.getIdFor().getIdFor());
                ps.executeUpdate();
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
   //afficher liste formation
    public List<Session> ListeSessions() throws SQLException
    {
        ArrayList<Session> sessions=new ArrayList<Session>();
        ResultSet rssession=st.executeQuery("SELECT * FROM Session");
     
        while(rssession.next())
        {   
            TypeFormation typeformation=new TypeFormation();
            Formation formation = new Formation();
            Session session=new Session();
            Utilisateur user = new Utilisateur();
            session.setIdSes(rssession.getInt("idSes"));
            session.setDateDebSes(rssession.getDate("dateDebSes"));
            session.setDateFinSes(rssession.getDate("dateFinSes"));
            session.setCapaciteSes(rssession.getInt("capaciteSes"));
            session.setEtatSes(rssession.getString("etatSes"));
            session.setPrixSes(rssession.getDouble("prix_ses"));
            session.setPrixSes(rssession.getDouble("nv_prix_ses"));
            session.setNomSes(rssession.getString("nomSes"));
            
            ResultSet rsformation = ste4.executeQuery("select * from formation where idFor="+ rssession.getInt("idFor"));
            
            while(rsformation.next())
            {
                formation.setIdFor(rsformation.getInt("idFor"));
                formation.setPlace(rsformation.getString("place"));
                formation.setEtatFor(rsformation.getString("etatFor"));
                formation.setDescriptionFor(rsformation.getString("descriptionFor"));
                formation.setDateFor(rsformation.getDate("dateFor"));
          
             ResultSet rsUser = ste3.executeQuery("select * from utilisateur where id="+ rsformation.getInt("idUser"));
            
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }
            ResultSet rstypefor = ste2.executeQuery("select * from type_formation where idTypeFor = "+ rsformation.getInt("idTypeFor"));
            while(rstypefor.next())
            {
                typeformation.setIdTypeFor(rstypefor.getInt("idTypeFor"));
                typeformation.setNomTypeFor(rstypefor.getString("nomTypeFor"));
               
            }
              }
            session.setIdFor(formation);
            sessions.add(session);
        }
        return sessions;
    }

    
    public void SupprimerSession(Session s) {
        String req ="delete from Session where idSes = ? ";
        PreparedStatement pre;
        try {
            pre = con.prepareStatement(req);
            pre.setInt(1, s.getIdSes()); 
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
    }
    
    public void ModifierSession(Session s) {
        try 
        {
            String req ="update Session set dateDebSes=?, dateFinSes=?,  capaciteSes=?, etatSes=?,  prix_ses=?,  nv_prix_ses=?,  nomSes=?, idFor=? where idSes=?" ;
            PreparedStatement ps = con.prepareStatement(req);
            ps.setDate(1, new Date(s.getDateDebSes().getTime()));
            ps.setDate(2, new Date(s.getDateFinSes().getTime()));
            ps.setInt(3, s.getCapaciteSes());
            ps.setString(4, s.getEtatSes());
            ps.setDouble(5, s.getPrixSes());
            ps.setDouble(6, s.getNvPrixSes());
            ps.setString(7, s.getNomSes());
            ps.setInt(8, s.getIdFor().getIdFor());
            ps.setInt(9, s.getIdSes());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public Session RechercheSession(String nom) throws SQLException{
        ResultSet rs = st.executeQuery("select * from session where nomSes = '"+nom+"'");
        Session pr = new Session();
        while(rs.next())
        {
            pr.setNomSes(rs.getString("nomSes"));
            pr.setIdSes(rs.getInt("idSes"));
        }
        return pr ;
    }

}

