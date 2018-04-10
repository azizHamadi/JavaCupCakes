/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Formation;
import Entity.Session;
import Entity.SessionUser;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hamdi fathallah
 */
public class SessionService {
     final ObservableList<Session> listeob=FXCollections.observableArrayList();
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
            ex.printStackTrace();
        }
    }
    
         
    
    
    
    //ajouter formation
    public void insertSession(Session s)
    {
        String sql="INSERT INTO Session"+
                "(dateDebSes,dateFinSes,capaciteSes,etatSes,imagesess,prix_ses,nomSes,idFor)"+
                "VALUES(?,?,?,'en cours',?,?,?,?);";
        try 
        {
            
          
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setDate(1, new Date(s.getDateDebSes().getTime()));
                ps.setDate(2, new Date(s.getDateFinSes().getTime()));
                ps.setInt(3, s.getCapaciteSes());
                ps.setString(4,s.getImagesess());
                ps.setDouble(5, s.getPrixSes());
                ps.setString(6, s.getNomSes());
                ps.setInt(7, s.getIdFor().getIdFor());
                ps.executeUpdate();
            
        } 
        catch (SQLException ex) 
        {
           ex.printStackTrace();
        }
    }
    
     //afficher toutes les sessions
    //houni zeda yelzm nzid iduser bech yaffichilou les sessions mteou
    //SELECT * FROM Session,Formation WHERE etatSes='en cours' AND Session.idFor=Formatin.idFor AND Formation.iduser=eli bech nzidou
    public ObservableList<Session> ListeToutesLesSessions() throws SQLException
    {
        ResultSet rssession=st.executeQuery("SELECT * FROM Session,Formation WHERE etatSes='en cours' AND etatFor='en cours' AND Session.idFor=Formation.idFor AND Formation.idUser="+SessionUser.getId());
        System.out.println("SELECT * FROM Session,Formation WHERE etatSes='en cours' AND Session.idFor=Formation.idFor AND Formation.idUser="+SessionUser.getId());
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
            session.setImagesess(rssession.getString("imagesess"));
            session.setPrixSes(rssession.getDouble("prix_ses"));
            session.setNomSes(rssession.getString("nomSes"));
            System.out.println("formation de session="+rssession.getInt("idFor"));
            
            ResultSet rsformation = ste4.executeQuery("select * from formation where idFor="+ rssession.getInt("idFor"));
            
            while(rsformation.next())
            {
                formation.setIdFor(rsformation.getInt("idFor"));
                formation.setNomFor(rsformation.getString("nomFor"));
                
          /*
            ResultSet rsUser = ste3.executeQuery("select * from utilisateur where id="+ rsformation.getInt("idUser"));
            
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }*/
           
            }
            session.setIdFor(formation);
            listeob.add(session);
        }
        return listeob;
    }
    
   //afficher lister sessions par rapport formation
    public ObservableList<Session> ListeSessions(int idformation) throws SQLException
    {
        System.out.println("SELECT * FROM Session WHERE etatSes='en cours' AND idFor="+idformation);
        ResultSet rssession=st.executeQuery("SELECT * FROM Session WHERE etatSes='en cours' AND idFor="+idformation);
     
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
            session.setImagesess(rssession.getString("imagesess"));
            session.setPrixSes(rssession.getDouble("prix_ses"));
            session.setNomSes(rssession.getString("nomSes"));
            System.out.println("formation de session="+rssession.getInt("idFor"));
            
            ResultSet rsformation = ste4.executeQuery("select * from formation where idFor="+ rssession.getInt("idFor"));
            
            while(rsformation.next())
            {
                formation.setIdFor(rsformation.getInt("idFor"));
                formation.setNomFor(rsformation.getString("nomFor"));
                
          /*
            ResultSet rsUser = ste3.executeQuery("select * from utilisateur where id="+ rsformation.getInt("idUser"));
            
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }*/
           
            }
            session.setIdFor(formation);
            listeob.add(session);
        }
        return listeob;
    }

     //afficher lister sessions par rapport formation
    public ObservableList<Session> ListeSessionsFormateur() throws SQLException
    {
        System.out.println("SELECT * FROM Session WHERE etatSes='en cours' ");
        ResultSet rssession=st.executeQuery("SELECT * FROM Session WHERE etatSes='en cours' AND idFor=");
     
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
            session.setImagesess(rssession.getString("imagesess"));
            session.setPrixSes(rssession.getDouble("prix_ses"));
            session.setNomSes(rssession.getString("nomSes"));
            System.out.println("formation de session="+rssession.getInt("idFor"));
            
            ResultSet rsformation = ste4.executeQuery("select * from formation where idFor="+ rssession.getInt("idFor"));
            
            while(rsformation.next())
            {
                formation.setIdFor(rsformation.getInt("idFor"));
                formation.setNomFor(rsformation.getString("nomFor"));
                
          /*
            ResultSet rsUser = ste3.executeQuery("select * from utilisateur where id="+ rsformation.getInt("idUser"));
            
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }*/
           
            }
            session.setIdFor(formation);
            listeob.add(session);
        }
        return listeob;
    }
    public void SupprimerSession(Session s, int idSes) {
        try {
            String req ="UPDATE Session SET etatSes ='finie' where idSes="+idSes;
            PreparedStatement ps = con.prepareStatement(req);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SessionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void ModifierSession(Session s,int idSession) {
        try 
        {
            String req ="update Session set dateDebSes=?, dateFinSes=?,nomSes=?,capaciteSes=?, prix_ses=?, imagesess=?,idFor=? where idSes="+idSession ;
            PreparedStatement ps = con.prepareStatement(req);
            ps.setDate(1, (Date)s.getDateDebSes());
            ps.setDate(2, (Date) s.getDateFinSes());
            ps.setString(3, s.getNomSes());
            ps.setInt(4, s.getCapaciteSes());
            ps.setDouble(5, s.getPrixSes());
            ps.setString(6,s.getImagesess() );
            ps.setInt(7, s.getIdFor().getIdFor());
           
            ps.executeUpdate();
        } 
        catch (SQLException ex) 
        {
           ex.printStackTrace();
        }
        
         
    }

     //search Session 
    //yelzem nzid fazet el iduser
    public ObservableList<Session> SearchListeSessions(String nomSession) throws SQLException
    {
         ResultSet rssession=st.executeQuery("SELECT * FROM Session WHERE etatSes='en cours' AND nomSes LIKE '%"+nomSession+"%'");
     
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
            session.setImagesess(rssession.getString("imagesess"));
            session.setPrixSes(rssession.getDouble("prix_ses"));
            session.setNomSes(rssession.getString("nomSes"));
            System.out.println("formation de session="+rssession.getInt("idFor"));
            
            ResultSet rsformation = ste4.executeQuery("select * from formation where idFor="+ rssession.getInt("idFor"));
            
            while(rsformation.next())
            {
                formation.setIdFor(rsformation.getInt("idFor"));
                formation.setNomFor(rsformation.getString("nomFor"));
                
          /*
            ResultSet rsUser = ste3.executeQuery("select * from utilisateur where id="+ rsformation.getInt("idUser"));
            
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }*/
           
            }
            session.setIdFor(formation);
            listeob.add(session);
        }
        return listeob;
    }
    
    public ObservableList<Session> SearchListeSessionsClient(String nomSession) throws SQLException
    {
         ResultSet rssession=st.executeQuery("SELECT * FROM Session WHERE etatSes='en cours' AND nomSes LIKE '%"+nomSession+"%'");
     
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
            session.setImagesess(rssession.getString("imagesess"));
            session.setPrixSes(rssession.getDouble("prix_ses"));
            session.setNomSes(rssession.getString("nomSes"));
            System.out.println("formation de session="+rssession.getInt("idFor"));
            
            ResultSet rsformation = ste4.executeQuery("select * from formation where idFor="+ rssession.getInt("idFor"));
            
            while(rsformation.next())
            {
                formation.setIdFor(rsformation.getInt("idFor"));
                formation.setNomFor(rsformation.getString("nomFor"));
                
          /*
            ResultSet rsUser = ste3.executeQuery("select * from utilisateur where id="+ rsformation.getInt("idUser"));
            
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }*/
           
            }
            session.setIdFor(formation);
            listeob.add(session);
        }
        return listeob;
    }


    //liste client session en cours
    public ObservableList<Session> ListeSessionsClientEnCoursFinies(String etatSes) throws SQLException
    {
        ResultSet rssession=st.executeQuery("SELECT * FROM Session,Educate WHERE etatSes='"+etatSes+"' AND Session.idSes=Educate.idSes AND idUser="+SessionUser.getId());
     
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
            session.setImagesess(rssession.getString("imagesess"));
            session.setPrixSes(rssession.getDouble("prix_ses"));
            session.setNomSes(rssession.getString("nomSes"));
            System.out.println("formation de session="+rssession.getInt("idFor"));
            
            ResultSet rsformation = ste4.executeQuery("select * from formation where idFor="+ rssession.getInt("idFor"));
            
            while(rsformation.next())
            {
                formation.setIdFor(rsformation.getInt("idFor"));
                formation.setNomFor(rsformation.getString("nomFor"));
                
          /*
            ResultSet rsUser = ste3.executeQuery("select * from utilisateur where id="+ rsformation.getInt("idUser"));
            
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }*/
           
            }
            session.setIdFor(formation);
            listeob.add(session);
        }
        return listeob;
    }
    
    
    //liste client session finie
    public ObservableList<Session> ListeSessionsClientFinie(int idclient,String etatSes) throws SQLException
    {
        ResultSet rssession=st.executeQuery("SELECT dateDebSes,dateFinSes,imagesess,nomSes FROM Session,Educate WHERE etatSes='"+etatSes+"' AND Session.idSes=Educate.idSes AND idUser="+idclient);
     
        while(rssession.next())
        {   
           
            Formation formation = new Formation();
            Session session=new Session();
            Utilisateur user = new Utilisateur();
            
            session.setDateDebSes(rssession.getDate("dateDebSes"));
            session.setDateFinSes(rssession.getDate("dateFinSes"));
            
            session.setImagesess(rssession.getString("imagesess"));
            //session.setPrixSes(rssession.getDouble("prix_ses"));
            session.setNomSes(rssession.getString("nomSes"));
              
          /*
            ResultSet rsUser = ste3.executeQuery("select * from utilisateur where id="+ rsformation.getInt("idUser"));
            
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }*/
           
            
            listeob.add(session);
        }
        return listeob;
    }
    
    //bch ntesti beha est ce que el formation mawjouda wala baed
    public int SessionExists(int idFor) throws SQLException
    {
        int idfor=0;
        ResultSet rssession=st.executeQuery("SELECT idFor,etatSes FROM Session WHERE  idFor="+idFor);
        //System.out.println("SELECT idFor FROM Session WHERE idSes="+idSes); 
        Session session=new Session();
        while(rssession.next())
        {  
            
             //System.out.println("SELECT"+(Formation) rssession.getObject("idFor")+" idFor FROM Session WHERE idFor="+idFor); 
            idfor=rssession.getInt("idFor");
            session.setEtatSes(rssession.getString("etatSes"));
             System.out.println("formation de session="+rssession.getInt("idFor"));
            Formation formation=new Formation();
            ResultSet rsformation = ste4.executeQuery("select * from formation where idFor="+ rssession.getInt("idFor"));
            
            while(rsformation.next())
            {
                formation.setIdFor(rsformation.getInt("idFor"));
                formation.setNomFor(rsformation.getString("nomFor"));
                
            }
            System.out.println("id formation mel Session exists="+idfor);
        }
         return idfor;    
    }
    
    //retourner letat de la session w baed nhotouh finie bch ntestiw bih
    public String ReturnEtatSes(int idFor) throws SQLException
    {
        String etatses=null;
        ResultSet rssession=st.executeQuery("SELECT etatSes FROM Session WHERE  idFor="+idFor);
        //System.out.println("SELECT idFor FROM Session WHERE idSes="+idSes); 
        Session session=new Session();
        while(rssession.next())
        {  
            
            
            session.setEtatSes(rssession.getString("etatSes"));
            etatses=session.getEtatSes();
        }
         return etatses;    
    }
    
    //bech nmodifiw el capacite fel session ki client yaaml inscri 
    public void ModifSessionInscription(Session s,int idSession) {
        try 
        {
            String req ="update Session set capaciteSes=? where idSes="+idSession ;
            PreparedStatement ps = con.prepareStatement(req);
            System.out.println("capacite ancienne="+s.getCapaciteSes());
            int nouvCapacite=s.getCapaciteSes()-1;
            System.out.println("capacite new="+nouvCapacite);
            ps.setInt(1, nouvCapacite);       
            ps.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();        }
        
         
    }

    //trajaa3li el capacite bech ntesti aleha kenha 0 mayjnajamch yajouti
    public int ReturnCapaciteSes(int idSes) throws SQLException
    {
        int capaciteses=0;
        ResultSet rssession=ste4.executeQuery("SELECT capaciteSes FROM Session WHERE  idSes="+idSes);
        //System.out.println("SELECT idFor FROM Session WHERE idSes="+idSes); 
        Session session=new Session();
        while(rssession.next())
        {  
            
            
            session.setCapaciteSes(rssession.getInt("capaciteSes"));
            capaciteses=session.getCapaciteSes();
        }
         return capaciteses;    
    }

     public ObservableList<Session> SearchListeSessionsClientEnCoursouFinie(String nomSession,String etatSes) throws SQLException
    {
         ResultSet rssession=st.executeQuery("SELECT * FROM Session,Educate WHERE  Session.idSes=Educate.idSes AND etatSes='"+etatSes+"' AND nomSes LIKE '%"+nomSession+"%'");
     
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
            session.setImagesess(rssession.getString("imagesess"));
            session.setPrixSes(rssession.getDouble("prix_ses"));
            session.setNomSes(rssession.getString("nomSes"));
            System.out.println("formation de session="+rssession.getInt("idFor"));
            
            ResultSet rsformation = ste4.executeQuery("select * from formation where idFor="+ rssession.getInt("idFor"));
            
            while(rsformation.next())
            {
                formation.setIdFor(rsformation.getInt("idFor"));
                formation.setNomFor(rsformation.getString("nomFor"));
                
          /*
            ResultSet rsUser = ste3.executeQuery("select * from utilisateur where id="+ rsformation.getInt("idUser"));
            
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }*/
           
            }
            session.setIdFor(formation);
            listeob.add(session);
        }
        return listeob;
    }

     //si date fin de session  inferieur a date actuelle twali etat finie
    public void ModifierEtatFormation(Date datefor) throws SQLException{
        System.out.println("modifier date session");
        System.out.println("UPDATE Session SET etatSes='finie' WHERE dateFinSes='"+datefor+"'");
        String req ="UPDATE Session SET etatSes='finie' where dateFinSes='"+datefor+"'";
        PreparedStatement ps = con.prepareStatement(req);         
        ps.executeUpdate();
    }
    //ajouter formation
 
    
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
           ex.printStackTrace();
        }
    }
    
    //a voir
    public Session RechercheSession(String nom) throws SQLException{
        ResultSet rs = st.executeQuery("select * from session where nomSes ='"+nom+"'");
        Session pr = new Session();
        while(rs.next())
        {
            pr.setNomSes(rs.getString("nomSes"));
            pr.setIdSes(rs.getInt("idSes"));
        }
        return pr ;
    }
     
     
      //search Session 
    //yelzem nzid fazet el iduser
    public ObservableList<Session> SearchListeSessionsByIDFor(String nomSession,int idfor) throws SQLException
    {
         ResultSet rssession=st.executeQuery("SELECT * FROM Session WHERE etatSes='en cours' AND idFor="+idfor+"AND nomSes LIKE '%"+nomSession+"%'");
     
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
            session.setImagesess(rssession.getString("imagesess"));
            session.setPrixSes(rssession.getDouble("prix_ses"));
            session.setNomSes(rssession.getString("nomSes"));
            System.out.println("formation de session="+rssession.getInt("idFor"));
            
            ResultSet rsformation = ste4.executeQuery("select * from formation where idFor="+ rssession.getInt("idFor"));
            
            while(rsformation.next())
            {
                formation.setIdFor(rsformation.getInt("idFor"));
                formation.setNomFor(rsformation.getString("nomFor"));
                
          /*
            ResultSet rsUser = ste3.executeQuery("select * from utilisateur where id="+ rsformation.getInt("idUser"));
            
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }*/
           
            }
            session.setIdFor(formation);
            listeob.add(session);
        }
        return listeob;
    }
}

