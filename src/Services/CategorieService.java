/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Categorie;
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

/**
 *
 * @author escobar
 */
public class CategorieService {
     public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;

    public CategorieService() {
        try {
            ste =con.createStatement();
            } catch (SQLException ex) {
               Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void ModifierCategorie(Categorie Categorie) throws SQLException{
        String req ="update Categorie set nomCat= ? where idCat= ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, Categorie.getNomCat());
        pre.setInt(2, Categorie.getIdCat());
        pre.executeUpdate();
    }
    
    public void SupprimerCategorie(Categorie Categorie) throws SQLException{
        String req ="delete from Categorie where idCat = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, Categorie.getIdCat());
        pre.executeUpdate();
    }
     public List<Categorie> AfficherCategorie() throws SQLException{
        List<Categorie> Categorie = new ArrayList<>();
        ResultSet rs = ste.executeQuery("select * from Categorie");
        while(rs.next())
        {
            Categorie.add(new Categorie (rs.getInt("idCat"), rs.getString("nomCat")));
        }
        return Categorie ;
    }

    public void AjouterCategorie(Categorie Categorie) throws SQLException {
     String req ="INSERT INTO categorie (nomCat) VALUES (?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, Categorie.getNomCat());
        pre.executeUpdate();
    }
      public int CountProduiParCat(int idCat) throws SQLException{
        int count = 0 ;
        ResultSet rs = ste.executeQuery("select count(*) from produit where idCat="+idCat);
        while(rs.next())
            count = rs.getInt(1);
        return count;

    }
      public int CountPromoParCat(int idCat) throws SQLException{
        int count = 0 ;
        ResultSet rs = ste.executeQuery("select count(*) from produit ,promotion , line_promo  where produit.idProd=line_promo.idProd and promotion.idPromo=line_promo.idPromo and produit.idCat="+idCat);
        while(rs.next())
            count = rs.getInt(1);
        return count;

    }
public Categorie rechercheCategorie (String nom) throws SQLException{
        Categorie cat = null ; 
        ResultSet rs = ste.executeQuery("select * from categorie where nomCat ='"+nom+"'");
        while(rs.next())
            {
                cat = new Categorie(rs.getInt("idCat"), rs.getString("nomCat"));
            }
        return cat ;
    }

}


