/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Formation;
import Entity.Linepromoses;
import Entity.Promotion;
import Entity.SendMail;
import Entity.Session;
import Entity.SessionUser;
import Entity.TypeFormation;
import Entity.Utilisateur;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.MessagingException;

/**
 *
 * @author hamdi fathallah
 */
public class LinePromoSesService {
        public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;
    private Statement ste1 ;
    private Statement ste2 ;
    private Statement ste3 ;
        final ObservableList<Session> listeob=FXCollections.observableArrayList();
        private ObservableList<Linepromoses> Listlinepromoses;

    
    public LinePromoSesService(){
 try {
            ste =con.createStatement();
            ste1 =con.createStatement();
            ste2 =con.createStatement();
            ste3 =con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(LinePromoSesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public void AjouterLinePromoSes(Linepromoses p) throws SQLException, MessagingException {
        String req = "INSERT INTO linepromoses (dateDeb,dateFin,idSes,idPromo,etatLinePromosession) VALUES (?,?,?,?,'en cours')";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDate(1, new Date(p.getDateDeb().getTime()));
        pre.setDate(2, new Date(p.getDateFin().getTime()));
        pre.setInt(3, p.getIdSes().getIdSes());
        pre.setInt(4, p.getIdPromo().getIdPromo());
        //pre.setInt(2, id );
        mail();
        pre.executeUpdate();
        System.out.println("session ajouter sous promotion avec succee!");

    }

    public void ModifierLinePromoSes(Linepromoses p, int id) throws SQLException {
        String req = "update linepromoses set dateDeb = ? , dateFin = ? , idSes = ? , idPromo = ? where idLine=" + id;
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDate(1, new Date(p.getDateDeb().getTime()));
        pre.setDate(2, new Date(p.getDateFin().getTime()));
        pre.setInt(3, p.getIdSes().getIdSes());
        pre.setInt(4, p.getIdPromo().getIdPromo());
        pre.executeUpdate();
        System.out.println("promotion de session est modifier avec succee!");

    }

    public void SupprimerLinePromoSes(Linepromoses p, int id) throws SQLException {
        String req = "UPDATE linepromoses SET etatLinePromosession ='finie' where idLine =" + id;
        PreparedStatement ps = con.prepareStatement(req);
        ps.executeUpdate();

    }
    
    public List<Linepromoses> AfficherLinePromoSesClient() throws SQLException {
        List<Linepromoses> LinePromosess = new ArrayList<>();
        ResultSet rs = ste.executeQuery("select * from linepromoses l , session s, formation f where s.idFor=f.idFor and l.idSes=s.idSes");
        while (rs.next()) {
            ResultSet rsp = ste1.executeQuery("select * from promotion where idPromo = " + rs.getInt("idPromo"));
            Promotion p = new Promotion();
            while (rsp.next()) {
                p.setIdPromo(rsp.getInt("idPromo"));
                p.setTauxPromo(rsp.getDouble("tauxPromo"));
                
            }
            ResultSet rspromo = ste2.executeQuery("select * from session where idSes = " + rs.getInt("idSes"));
            Session session = new Session();
            while (rspromo.next()) {
                session.setIdSes(rspromo.getInt("idSes"));
                session.setNomSes(rspromo.getString("nomSes"));
                session.setPrixSes(rspromo.getDouble("prix_ses"));
                session.setNvPrixSes(rspromo.getDouble("nv_prix_ses"));
                session.setImagesess(rspromo.getString("imagesess"));
                session.setCapaciteSes(rspromo.getInt("capaciteSes"));
                ResultSet rspfor = ste3.executeQuery("select * from formation where idFor = " + rs.getInt("idFor"));
                Formation form = new Formation();
                while(rspfor.next())
                {form.setIdFor(rspfor.getInt("idFor"));
                form.setNomFor(rspfor.getString("nomFor"));
                }
                session.setIdFor(form);
            }
            LinePromosess.add(new Linepromoses(rs.getInt("idLine"), rs.getDate("dateDeb"), rs.getDate("dateFin"), session, p));
        }
        return LinePromosess;
    }

    public List<Linepromoses> AfficherLinePromoSes() throws SQLException {
        List<Linepromoses> LinePromosess = new ArrayList<>();
        ResultSet rs = ste.executeQuery("select * from linepromoses l , session s, formation f where s.idFor=f.idFor and l.idSes=s.idSes and f.idUser="+SessionUser.getId());
        while (rs.next()) {
            ResultSet rsp = ste1.executeQuery("select * from promotion where idPromo = " + rs.getInt("idPromo"));
            Promotion p = new Promotion();
            while (rsp.next()) {
                p.setIdPromo(rsp.getInt("idPromo"));
                p.setTauxPromo(rsp.getDouble("tauxPromo"));
                
            }
            ResultSet rspromo = ste2.executeQuery("select * from session where idSes = " + rs.getInt("idSes"));
            Session session = new Session();
            while (rspromo.next()) {
                session.setIdSes(rspromo.getInt("idSes"));
                session.setNomSes(rspromo.getString("nomSes"));
                session.setPrixSes(rspromo.getDouble("prix_ses"));
                session.setNvPrixSes(rspromo.getDouble("nv_prix_ses"));

            }
            LinePromosess.add(new Linepromoses(rs.getInt("idLine"), rs.getDate("dateDeb"), rs.getDate("dateFin"), session, p));
        }
        return LinePromosess;
    }
    
    public void ModifierEtatLinePromoses(Date datefin) throws SQLException
    {
        System.out.println("modifier date session");
        System.out.println("UPDATE linepromoses SET etatLinePromosession ='finie' where dateFin='"+datefin+"'");
        String req ="UPDATE linepromoses SET etatLinePromosession ='finie' where dateFin='"+datefin+"'";
        PreparedStatement ps = con.prepareStatement(req);         
        ps.executeUpdate();
    }


    public ObservableList<Linepromoses> getListelinepromoses() throws SQLException {
        return FXCollections.observableArrayList(AfficherLinePromoSes());
    }

    public void setListelinepromo(ObservableList<Linepromoses> Listlinepromoses) {
        this.Listlinepromoses = Listlinepromoses;
    }

    public void calculepromo(Session l, Promotion po) throws SQLException {
        System.out.println("prix session = " + l.getPrixSes()+ " taux = " + po.getTauxPromo());
        float pourcentage =  (float) (l.getPrixSes() - ((l.getPrixSes() / 100) * po.getTauxPromo()));
        String req = "update session set nv_prix_ses= " + pourcentage + " where idSes=" + l.getIdSes();
        PreparedStatement pre = con.prepareStatement(req);
        pre.executeUpdate();
        System.out.println("calcule promo avec succee!");
    }
    
     public List<Session> calculerpromoformation(Promotion po) throws SQLException {
        List<Session> sessions = new ArrayList<>();
        ResultSet rs = ste.executeQuery("select * from session");
        Session pr = new Session();
        while (rs.next()) {
            pr.setPrixSes(rs.getDouble("prix_ses"));
            pr.setIdSes(rs.getInt("idSes"));
            System.out.println("prix" + pr.getPrixSes());
            float pourcentage = (int) (pr.getPrixSes()- ((pr.getPrixSes()/ 100) * po.getTauxPromo()));
            System.out.println("prix session = " + pr.getPrixSes()+ " taux = " + po.getTauxPromo() + "id" + pr.getIdSes());
            String req = "update session set nv_prix_ses= " + pourcentage + " where idSes=" + pr.getIdSes();
            PreparedStatement pre = con.prepareStatement(req);
            pre.executeUpdate();
            System.out.println("nv prix" + pourcentage);
            System.out.println("calcule promo boutique avec succee!");
        }

        return sessions;
    }

    public List<Linepromoses> RecherchePromosession(int idSes) throws SQLException {
        ResultSet rs = ste.executeQuery("select * from  linepromoses,session  where session.idSes=linepromoses.idSes and session.idSes="+idSes);
        Linepromoses pr = new Linepromoses();
        List<Linepromoses> listPromoses = new ArrayList<>();
        while (rs.next()) {
            pr.setIdLine(rs.getInt("idLine"));
            pr.setDateFin(rs.getDate("dateFin"));
            listPromoses.add(pr);
        }
        return listPromoses;

    }
    
     public ObservableList<Linepromoses> SearchListePromo(String nom) throws SQLException {
        List<Linepromoses> listLinePromo = new ArrayList<>();
        ResultSet rssession = ste.executeQuery("select * from  linepromoses,session  where session.idSes=linepromoses.idSes and session.nomSes LIKE '%" + nom + "%'");
        while (rssession.next()) {
            Linepromoses p = new Linepromoses();
            Session pp = new Session();
            Promotion po = new Promotion();
            p.setDateDeb(rssession.getDate("dateDeb"));
            p.setDateFin(rssession.getDate("dateFin"));
            System.out.println("promotion=" + rssession.getInt("idLine"));
            ResultSet rs = ste1.executeQuery("select * from session where idSes=" + rssession.getInt("idSes"));
            while (rs.next()) {
                pp.setIdSes(rs.getInt("idSes"));
                pp.setNomSes(rs.getString("nomSes"));
                pp.setImagesess(rs.getString("imagesess"));
                pp.setNvPrixSes(rs.getDouble("nv_prix_ses"));
                pp.setPrixSes(rs.getDouble("prix_ses"));
            }
            ResultSet rso = ste2.executeQuery("select * from promotion where idPromo=" + rssession.getInt("idPromo"));
            while (rso.next()) {
                po.setIdPromo(rso.getInt("idPromo"));
                po.setTauxPromo(rso.getDouble("tauxPromo"));
            }
            p.setIdSes(pp);
            p.setIdPromo(po);
            p.setIdLine(rssession.getInt("idLine"));
            System.out.println(pp);
            System.out.println(po);
            System.out.println(p);
            listLinePromo.add(p);
        }
        return FXCollections.observableArrayList(listLinePromo);
    }
     
    public List<Session> SessionParFormation(int idFor) throws SQLException {
        List<Session> listS = new ArrayList<>();
        ResultSet rsProduit = ste.executeQuery("select * from formation ,session , linepromoses  where session.idFor=formation.idFor and linepromoses.idSes=session.idSes and formation.idFor=" + idFor);
        while (rsProduit.next()) {
            Session se = new Session();
            Utilisateur user = new Utilisateur();
            Formation formation = new Formation();
            se.setNomSes(rsProduit.getString("nomSes"));
            se.setPrixSes(rsProduit.getDouble("prix_ses"));
            se.setNvPrixSes(rsProduit.getDouble("nv_prix_ses"));
            se.setIdSes(rsProduit.getInt("idSes"));
            se.setImagesess(rsProduit.getString("imagesess"));
            se.setCapaciteSes(rsProduit.getInt("capaciteSes"));
          /*  ResultSet rsUser = ste1.executeQuery("select * from utilisateur where id=" + rsProduit.getInt("idUser"));
            while (rsUser.next()) {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }*/
            ResultSet rsCatRecette = ste2.executeQuery("select * from formation where idFor = " +idFor);
            while (rsCatRecette.next()) {
                formation.setIdFor(rsCatRecette.getInt("idFor"));
                formation.setNomFor(rsCatRecette.getString("nomFor"));
            }
            se.setIdFor(formation);
            listS.add(se);
        }
        return listS;
    }
     
      public ObservableList<Session> ListePromoSession(int idformation) throws SQLException
    {
        System.out.println("SELECT * FROM Session WHERE etatSes='en cours' AND nv_prix_ses<> 'NULL' AND idFor="+idformation);
        ResultSet rssession=ste.executeQuery("SELECT * FROM Session WHERE etatSes='en cours' AND idFor="+idformation);
     
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
            session.setNvPrixSes(rssession.getDouble("nv_prix_ses"));
            session.setNomSes(rssession.getString("nomSes"));
            System.out.println("formation de session="+rssession.getInt("idFor"));
            
            ResultSet rsformation = ste1.executeQuery("select * from formation where idFor="+ rssession.getInt("idFor"));
            
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
      
      public int ReturnCapaciteSes(int idSes) throws SQLException
    {
        int capaciteses=0;
        ResultSet rssession=ste.executeQuery("SELECT capaciteSes FROM Session WHERE  idSes="+idSes);
        //System.out.println("SELECT idFor FROM Session WHERE idSes="+idSes); 
        Session session=new Session();
        while(rssession.next())
        {  
            
            
            session.setCapaciteSes(rssession.getInt("capaciteSes"));
            capaciteses=session.getCapaciteSes();
        }
         return capaciteses;    
    }
      
       public void ModifSessionInscription(Session s,int idSession) {
        try 
        {
            String req ="update Session set capaciteSes=? where idSes="+idSession ;
            PreparedStatement ps = con.prepareStatement(req);
            System.out.println("capacite ancienne="+s.getCapaciteSes());
            int nouvCapacite=s.getCapaciteSes()+1;
            System.out.println("capacite new="+nouvCapacite);
            ps.setInt(1, nouvCapacite);       
            ps.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();        

}}
         public void mail() throws MessagingException, SQLException {

        String USER_NAME = "hamdi.fathallah.1@esprit.tn";
        String PASSWORD = "hamdifathallah";
        userServices us = new userServices();
        List<Utilisateur> listU = us.listClient("a:1:{i:0;s:11:\"ROLE_CLIENT\";}"); 
        for (Utilisateur user : listU)
        {
        String RECIPIENT = user.getEmail() ;
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = {RECIPIENT}; // list of recipient email addresses
        String subject = "promotion";
        String body = "une nouvelle session a été ajouter sous promotion consulter notre application pour consulter plus des details  ";
        SendMail send = new SendMail();
        send.sendFromGMail(from, pass, to, subject, body);
        }

    } 
         
   
    
}
