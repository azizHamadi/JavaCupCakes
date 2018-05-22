/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Categorie;
import Entity.LinePromo;
import Entity.Produit;
import Entity.Promotion;
import Entity.SendMail;
import Entity.SessionUser;
import Entity.Utilisateur;
import Technique.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JOptionPane;

/**
 *
 * @author hamdi fathallah
 */
public class LinePromoService {

    public Connection con = DataSource.getInstance().getCon();
    private Statement ste;
    private Statement ste1;
    private Statement ste2;
    private ObservableList<LinePromo> Listlinepromo;

    public LinePromoService() {
        try {
            ste = con.createStatement();
            ste1 = con.createStatement();
            ste2 = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(LinePromoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 public void AjouterLinePromo(LinePromo p) throws SQLException, MessagingException {
        String req = "INSERT INTO line_promo (dateDeb,dateFin,idPromo,idProd,etatLinePromo) VALUES (?,?,?,?,'en cours')";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDate(1, new Date(p.getDateDeb().getTime()));
        pre.setDate(2, new Date(p.getDateFin().getTime()));
        pre.setInt(3, p.getIdPromo().getIdPromo());
        pre.setInt(4, p.getIdProd().getIdProd());
        pre.executeUpdate();
        //mail();
        sms();
           
        }
        public List<LinePromo> RecherchePromoProduit(int idProd) throws SQLException{
        ResultSet rs = ste.executeQuery("select * from  line_promo,produit  where produit.idProd=line_promo.idProd and produit.idProd = "+idProd );
        LinePromo pr = new LinePromo(); 
        List<LinePromo> listPromo = new ArrayList<>();
        while(rs.next())
        {
           pr.setId(rs.getInt("id"));
           pr.setDateFin(rs.getDate("dateFin"));  
           listPromo.add(pr);
        } 
         return listPromo ;
        
    }

    public void calculepromo(Produit l, Promotion po) throws SQLException {
        System.out.println("prix prod = " + l.getPrixProd() + " taux = " + po.getTauxPromo());
        float pourcentage = (int) (l.getPrixProd() - ((l.getPrixProd() / 100) * po.getTauxPromo()));
        String req = "update produit set nv_prix= " + pourcentage + " where idProd=" + l.getIdProd();
        PreparedStatement pre = con.prepareStatement(req);
        pre.executeUpdate();
        System.out.println("calcule promo avec succee!");
    }
    
   
    public List<Produit> calculerpromoboutique(Promotion po) throws SQLException {
        List<Produit> produits = new ArrayList<>();
        ResultSet rs = ste.executeQuery("select * from Produit");
        Produit pr = new Produit();
        while (rs.next()) {
            pr.setPrixProd(rs.getInt("prixProd"));
            pr.setIdProd(rs.getInt("idProd"));
            System.out.println("prix" + pr.getPrixProd());
            float pourcentage = (int) (pr.getPrixProd() - ((pr.getPrixProd() / 100) * po.getTauxPromo()));
            System.out.println("prix prod = " + pr.getPrixProd() + " taux = " + po.getTauxPromo() + "id" + pr.getIdProd());
            String req = "update produit set nv_prix= " + pourcentage + " where idProd=" + pr.getIdProd();
            PreparedStatement pre = con.prepareStatement(req);
            pre.executeUpdate();
            System.out.println("nv prix" + pourcentage);
            System.out.println("calcule promo boutique avec succee!");

        }

        return produits;
    }
    
    
    public void sms() {
    try {
   // Construct data
   String apiKey = "apikey=" + "qrJHQSx3Lto-Uwyhudz00LZJ4Up01HvwjCxsB4idY3";
   String message = "&message=" + "un nouvea promotion est ajouter sur un produit consulter notre site pour plus des informations ";
   String sender = "&sender=" + "CupCakes";
    userServices us = new userServices();
        List<Utilisateur> listU = us.listClient("a:1:{i:0;s:11:\"ROLE_CLIENT\";}"); 
        for (Utilisateur user : listU)
        {
   String numbers = "&numbers=" + "+216"+user.getPhoneNumber();
    
   // Send data
   HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
   String data = apiKey + numbers + message + sender;
   conn.setDoOutput(true);
   conn.setRequestMethod("POST");
   conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
   conn.getOutputStream().write(data.getBytes("UTF-8"));
   final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
   final StringBuffer stringBuffer = new StringBuffer();
   String line;
   while ((line = rd.readLine()) != null) {
    //stringBuffer.append(line);
    JOptionPane.showMessageDialog(null, "message"+line);
   }
   rd.close();
        }  
   //return stringBuffer.toString();
  } catch (Exception e) {
   //System.out.println("Error SMS "+e);
    JOptionPane.showMessageDialog(null, e);
   //return "Error "+e;
    }
    }

    public void mail() throws AddressException, MessagingException, SQLException {

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
        String body = "une nouvelle promotion a été ajouter";
        SendMail send = new SendMail();
        send.sendFromGMail(from, pass, to, subject, body);
        }

    }

    /*public void calculepromoboutique(Promotion po) throws SQLException {
        Produit l = new Produit();
        System.out.println("prix prod = "+l.getPrixProd() +" taux = "+po.getTauxPromo());
        float pourcentage = (int) (l.getPrixProd() - ((l.getPrixProd() / 100) * po.getTauxPromo()));
        String req = "update produit set nv_prix= "+ pourcentage +" where idProd="+l.getIdProd();
        PreparedStatement pre = con.prepareStatement(req);
        pre.executeUpdate();
        System.out.println("calcule promo boutique avec succee!");
    }*/
 /* public void calculepromoboutique(Promotion po) throws SQLException {
        ProduitService ps = new ProduitService();
        List<Produit> listProd = ps.AfficherPrixProduit();
        for (Produit prod : listProd)
        {
        System.out.println("prix prod = "+prod.getPrixProd() +" taux = "+po.getTauxPromo());
        float pourcentage = (int) (prod.getPrixProd() - ((prod.getPrixProd() / 100) * po.getTauxPromo()));
        String req = "update produit set nv_prix= "+ pourcentage +" where idProd="+prod.getIdProd();
        PreparedStatement pre = con.prepareStatement(req);
        pre.executeUpdate();
        System.out.println("calcule promo avec succee!");
        }
        System.out.println("calcule promo boutique avec succee!");

    }*/

 /*  public void calculepromo (int prixProd , double tauxPromo) throws SQLException {
      String req ="update produit set nv_prix = "+(prixProd-((prixProd/100)*tauxPromo));
      PreparedStatement pre = con.prepareStatement(req);
      //int pourcentage = 0 ;
      //pourcentage = (int) (l.getPrixProd()-((l.getPrixProd()/100)*k.getTauxPromo()));
      //pre.setInt(1, (int) pourcentage);
      pre.executeUpdate();
      System.out.println("calcule promo avec succee!");
      }*/
    public void ModifierLinePromo(LinePromo p, int id) throws SQLException {
        String req = "update line_promo set dateDeb = ? , dateFin = ? , idPromo = ? , idProd = ? where id=" + id;
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDate(1, new Date(p.getDateDeb().getTime()));
        pre.setDate(2, new Date(p.getDateFin().getTime()));
        pre.setInt(3, p.getIdPromo().getIdPromo());
        pre.setInt(4, p.getIdProd().getIdProd());
        pre.executeUpdate();
        System.out.println("promotion de produit est modifier avec succee!");
    }

    public void SupprimerLinePromo(LinePromo p, int id) throws SQLException {
        String req = "UPDATE line_promo SET etatLinePromo ='finie' where id =" + id;
        PreparedStatement ps = con.prepareStatement(req);

        ps.executeUpdate();

    }

    public List<LinePromo> AfficherLinePromo() throws SQLException {
        List<LinePromo> LinePromos = new ArrayList<>();
        ResultSet rs = ste.executeQuery("select * from line_promo l , produit p , categorie c  WHERE c.idCat=p.idCat and l.idProd=p.idProd and etatLinePromo='en cours' and p.idUser="+SessionUser.getId() );
        while (rs.next()) {
            ResultSet rsp = ste1.executeQuery("select * from produit where idProd ="+rs.getInt("idProd"));
            Produit p = new Produit();
            while (rsp.next()) {
                p.setIdProd(rsp.getInt("idProd"));
                p.setNomProd(rsp.getString("nomProd"));
                p.setPrixProd(rsp.getInt("prixProd"));
                p.setNvPrix(rsp.getInt("nv_prix"));
                p.setImageprod(rsp.getString("imageprod"));
                p.setIdCat(new Categorie(rsp.getInt("idCat")));
            }
            ResultSet rspromo = ste2.executeQuery("select * from promotion where idPromo = " + rs.getInt("idPromo"));
            Promotion promo = new Promotion();
            while (rspromo.next()) {
                promo.setIdPromo(rspromo.getInt("idPromo"));
                promo.setTauxPromo(rspromo.getDouble("tauxPromo"));
            }
            LinePromos.add(new LinePromo(rs.getInt("id"), rs.getDate("dateDeb"), rs.getDate("dateFin"), promo, p));
        }
        return LinePromos;
    }
    
   public List<LinePromo> AfficherLinePromoS() throws SQLException {
        List<LinePromo> LinePromos = new ArrayList<>();
        ResultSet rs = ste.executeQuery("select * from line_promo l , produit p , categorie c  WHERE c.idCat=p.idCat and l.idProd=p.idProd and l.etatLinePromo='en cours' " );
        while (rs.next()) {
            ResultSet rsp = ste1.executeQuery("select * from produit where idProd ="+rs.getInt("idProd"));
            Produit p = new Produit();
            while (rsp.next()) {
                p.setIdProd(rsp.getInt("idProd"));
                p.setNomProd(rsp.getString("nomProd"));
                p.setPrixProd(rsp.getInt("prixProd"));
                p.setNvPrix(rsp.getInt("nv_prix"));
                p.setImageprod(rsp.getString("imageprod"));
                p.setQteStockProd(rsp.getDouble("qteStockProd"));
                p.setIdCat(new Categorie(rsp.getInt("idCat")));
                p.setQteAcheter(rsp.getInt("QteAcheter"));
            }
            ResultSet rspromo = ste2.executeQuery("select * from promotion where idPromo = " + rs.getInt("idPromo"));
            Promotion promo = new Promotion();
            while (rspromo.next()) {
                promo.setIdPromo(rspromo.getInt("idPromo"));
                promo.setTauxPromo(rspromo.getDouble("tauxPromo"));
            }
            LinePromos.add(new LinePromo(rs.getInt("id"), rs.getDate("dateDeb"), rs.getDate("dateFin"), promo, p));
        }
        return LinePromos;
    }
    
  public void ModifierEtatLinePromo(Date datefin) throws SQLException
    {
        System.out.println("modifier date produit");
        System.out.println("UPDATE line_promo SET etatLinePromo	 ='finie' where dateFin='"+datefin+"'");
        String req ="UPDATE line_promo SET etatLinePromo ='finie' where dateFin='"+datefin+"'";
        PreparedStatement ps = con.prepareStatement(req);         
        ps.executeUpdate();
    }

    public ObservableList<LinePromo> getListelinepromo() throws SQLException {
        return FXCollections.observableArrayList(AfficherLinePromo());
    }

    public void setListelinepromo(ObservableList<LinePromo> Listlinepromo) {
        this.Listlinepromo = Listlinepromo;
    }

    public List<Produit> ProduitParCategorie(int idCat) throws SQLException {
        List<Produit> listP = new ArrayList<>();
        ResultSet rsProduit = ste.executeQuery("select * from produit ,promotion , line_promo  where produit.idProd=line_promo.idProd and promotion.idPromo=line_promo.idPromo and line_promo.etatLinePromo='en cours' and  produit.idCat=" + idCat);
        while (rsProduit.next()) {
            Produit pr = new Produit();
            Utilisateur user = new Utilisateur();
            Categorie categorie = new Categorie();
            pr.setNomProd(rsProduit.getString("nomProd"));
            pr.setPrixProd(rsProduit.getInt("prixProd"));
            pr.setNvPrix(rsProduit.getInt("nv_prix"));
            pr.setIdProd(rsProduit.getInt("idProd"));
            pr.setImageprod(rsProduit.getString("imageprod"));
            pr.setQteStockProd(rsProduit.getDouble("qteStockProd"));
            ResultSet rsUser = ste1.executeQuery("select * from utilisateur where id=" + rsProduit.getInt("idUser"));
            while (rsUser.next()) {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }
            ResultSet rsCatRecette = ste2.executeQuery("select * from categorie where idCat = " + rsProduit.getInt("idCat"));
            while (rsCatRecette.next()) {
                categorie.setIdCat(rsCatRecette.getInt("idCat"));
                categorie.setNomCat(rsCatRecette.getString("nomCat"));
            }
            pr.setIdCat(categorie);
            pr.setIdUser(user);
            listP.add(pr);
        }
        return listP;
    }

    public ObservableList<LinePromo> SearchListePromo(String nom) throws SQLException {
        List<LinePromo> listLinePromo = new ArrayList<>();
        ResultSet rssession = ste.executeQuery("select * from  line_promo,produit  where produit.idProd=line_promo.idProd and produit.nomProd LIKE '%" + nom + "%'");
        while (rssession.next()) {
            LinePromo p = new LinePromo();
            Produit pp = new Produit();
            Promotion po = new Promotion();
            p.setDateDeb(rssession.getDate("dateDeb"));
            p.setDateFin(rssession.getDate("dateFin"));
            System.out.println("promotion=" + rssession.getInt("id"));
            ResultSet rs = ste1.executeQuery("select * from produit where idProd=" + rssession.getInt("idProd"));
            while (rs.next()) {
                pp.setIdProd(rs.getInt("idProd"));
                pp.setNomProd(rs.getString("nomProd"));
                pp.setPrixProd(rs.getInt("prixProd"));
                pp.setIdCat(new Categorie(rs.getInt("idCat")));
                pp.setNvPrix(rs.getInt("nv_prix"));
                pp.setImageprod(rs.getString("imageprod"));
            }
            ResultSet rso = ste2.executeQuery("select * from promotion where idPromo=" + rssession.getInt("idPromo"));
            while (rso.next()) {
                po.setIdPromo(rso.getInt("idPromo"));
                po.setTauxPromo(rso.getDouble("tauxPromo"));
            }
            p.setIdProd(pp);
            p.setIdPromo(po);
            p.setId(rssession.getInt("id"));
            System.out.println(pp);
            System.out.println(po);
            System.out.println(p);
            listLinePromo.add(p);
        }
        return FXCollections.observableArrayList(listLinePromo);
    }
    
    public void UpdateProduitValeur (Produit p) throws SQLException{
        String req ="update produit set valeur="+(p.getValeur() + 1)+" where idProd ="+p.getIdProd();
        PreparedStatement pre = con.prepareStatement(req);
         System.out.println("p = "+(p.getValeur()+1) + " id = "+ p.getIdProd());
        pre.executeUpdate();
         System.out.println(p);  
   }
    
    public List<Integer> afficherProduit() throws SQLException{
         List<Integer> Prod = new ArrayList<>();
         String v ="en cours";
        ResultSet rs = ste.executeQuery("select * from line_promo where etatLinePromo= '" + v + "'");
        while(rs.next())
        {
            Prod.add(rs.getInt("idProd"));
        }
        return Prod ;
    }
}
