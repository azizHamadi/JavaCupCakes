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
import Entity.Utilisateur;
import Technique.DataSource;
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

    public void AjouterLinePromo(LinePromo p) throws SQLException {
        String req = "INSERT INTO line_promo (dateDeb,dateFin,idPromo,idProd,etatLinePromo) VALUES (?,?,?,?,'en cours')";
        PreparedStatement pre = con.prepareStatement(req);
        //String rep ="INSERT INTO produit (nv_prix) VALUES (?)";
        //PreparedStatement prr = con.prepareStatement(rep);
        pre.setDate(1, new Date(p.getDateDeb().getTime()));
        pre.setDate(2, new Date(p.getDateFin().getTime()));
        pre.setInt(3, p.getIdPromo().getIdPromo());
        pre.setInt(4, p.getIdProd().getIdProd());
        //float pourcentage ;    
        //pourcentage = (int) (l.getPrixProd()-((l.getPrixProd()/100)*po.getTauxPromo()));
        //prr.setInt(1, (int) pourcentage);
        //prr.executeUpdate();
        pre.executeUpdate();
        //System.out.println("calcule promo avec succee!");
        System.out.println("produit ajouter sous promotion avec succee!");

    }

    public void calculepromo(Produit l, Promotion po) throws SQLException {
        String req = "INSERT INTO produit (nv_prix) VALUES (?)";
        PreparedStatement pre = con.prepareStatement(req);
        float pourcentage;
        pourcentage = (int) (l.getPrixProd() - ((l.getPrixProd() / 100) * po.getTauxPromo()));
        pre.setInt(1, (int) pourcentage);
        System.out.println("calcule promo avec succee!");
    }

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
        ResultSet rs = ste.executeQuery("select * from line_promo WHERE etatLinePromo='en cours'");
        while (rs.next()) {
            ResultSet rsp = ste1.executeQuery("select * from produit where idProd = " + rs.getInt("idProd"));
            Produit p = new Produit();
            while (rsp.next()) {
                p.setIdProd(rsp.getInt("idProd"));
                p.setNomProd(rsp.getString("nomProd"));
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

    public ObservableList<LinePromo> getListelinepromo() throws SQLException {
        return FXCollections.observableArrayList(AfficherLinePromo());
    }

    public void setListelinepromo(ObservableList<LinePromo> Listlinepromo) {
        this.Listlinepromo = Listlinepromo;
    }

    public List<Produit> ProduitParCategorie(int idCat) throws SQLException {
        List<Produit> listP = new ArrayList<>();
        ResultSet rsProduit = ste.executeQuery("select * from produit ,promotion , line_promo  where produit.idProd=line_promo.idProd and promotion.idPromo=line_promo.idPromo and produit.idCat=" + idCat);
        while (rsProduit.next()) {
            Produit pr = new Produit();
            Utilisateur user = new Utilisateur();
            Categorie categorie = new Categorie();
            pr.setNomProd(rsProduit.getString("nomProd"));
            pr.setPrixProd(rsProduit.getInt("prixProd"));
            pr.setIdProd(rsProduit.getInt("idProd"));
            pr.setImageprod(rsProduit.getString("imageprod"));
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
        ResultSet rssession = ste.executeQuery("SELECT * FROM line_promo p ,produit pr WHERE p.etatLinePromo='en cours' AND pr.nomProd LIKE '%" + nom + "%'");

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
            }
            ResultSet rso = ste1.executeQuery("select * from promotion where idPromo=" + rssession.getInt("idPromo"));
            while (rso.next()) {
                po.setIdPromo(rso.getInt("idPromo"));
                po.setTauxPromo(rso.getDouble("tauxPromo"));
            }
            p.setIdProd(pp);
            p.setIdPromo(po);
            Listlinepromo.add(p);
        }
        return Listlinepromo;
    }

}
