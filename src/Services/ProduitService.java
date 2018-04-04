/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Categorie;
import Entity.FeedBack;
import Entity.Produit;
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
 * @author escobar
 */
public class ProduitService {
     public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;
        private Statement ste1 ;   
        private Statement ste2 ;
        private Statement ste3 ;


    
    public ProduitService(){
        try {
            ste =con.createStatement();
            ste1 =con.createStatement();
            ste2 =con.createStatement();
            ste3 =con.createStatement();



        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void AjouterProduit(Produit p,String NomCat) throws SQLException{
        String req ="INSERT INTO produit (nomProd,qteStockProd,typeProd,prixProd,etatProd,imageprod,QteAcheter,valeur,idUser,idCat) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet rs = ste.executeQuery("SELECT * FROM Utilisateur WHERE id = 1");
       int id = 0;
       while(rs.next())
            id= rs.getInt("id");
        ResultSet rs1 = ste1.executeQuery("SELECT * FROM Categorie where nomCat ='" + NomCat + "'" );
       int idC = 0;
       while(rs1.next())
            idC= rs1.getInt("idCat");
        pre.setString(1, p.getNomProd());
        pre.setDouble(2, p.getQteStockProd());
        pre.setString(3, p.getTypeProd());
        pre.setInt(4, p.getPrixProd());
        pre.setString(5, p.getEtatProd());
        pre.setString(6, p.getImageprod());
        pre.setDouble(7, 0);
        pre.setDouble(8, 0);
        pre.setInt(9, id );
        pre.setInt(10, idC );
        pre.executeUpdate();
        System.out.println("ajouter");
    }
    
    public List<Produit> AfficherNomProduit() throws SQLException{
        List<Produit> Prod = new ArrayList();
        String v="Vrai";
            try {
            ResultSet rs =ste.executeQuery("SELECT * FROM Produit where etatProd='" + v + "'" ) ;
            while(rs.next()){
                Utilisateur user = new Utilisateur();
                ResultSet cdUser = ste2.executeQuery("select * from utilisateur where id = "+ rs.getInt("idUser"));
                while(cdUser.next())
                {
                    user.setUsername(cdUser.getString("username"));
                 }
                Categorie cd = new Categorie();
                ResultSet line = ste3.executeQuery("select * from Categorie where idCat = "+ rs.getInt("idCat"));
                while(line.next())
                {
                    cd.setNomCat(line.getString("nomCat"));
                    }
                           Prod.add(new Produit(rs.getInt("IdProd"),rs.getString("nomProd"),(double)rs.getInt("qteStockProd"),rs.getString("typeProd"),rs.getInt("prixProd"),rs.getString("imageprod"),rs.getInt("QteAcheter"),cd,user));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Prod;
    }
    public List<Produit> ProduitParCategorie(int idCat) throws SQLException{
        List<Produit> listP = new ArrayList<>();
                ResultSet rsRecette = ste.executeQuery("select * from produit where idCat="+idCat);

        while(rsRecette.next())
        {
            Produit pr = new Produit();
            Utilisateur user = new Utilisateur();
            Categorie categorie = new Categorie();
            pr.setNomProd(rsRecette.getString("NomProd"));
            pr.setPrixProd(rsRecette.getInt("PrixProd"));
            pr.setIdProd(rsRecette.getInt("idProd"));            
            pr.setImageprod(rsRecette.getString("imageprod"));
            ResultSet rsUser = ste2.executeQuery("select * from utilisateur where id="+ rsRecette.getInt("idUser"));
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }
            ResultSet rsCatRecette = ste3.executeQuery("select * from categorie where idCat = "+ rsRecette.getInt("idCat"));
            while(rsCatRecette.next())
            {
                categorie.setIdCat(rsCatRecette.getInt("idCat"));
                categorie.setNomCat(rsCatRecette.getString("nomCat"));
            }
            pr.setIdCat(categorie);
            pr.setIdUser(user);
            listP.add(pr);
        }
        return listP;
    }
   public void UpdateProduit(Produit p,String NomCat) throws SQLException{
        String req ="update Produit set nomProd = ?  , idCat = ? , qteStockProd=?,prixProd=?,typeProd=? where idProd = ?";
        PreparedStatement pre = con.prepareStatement(req); 
        ResultSet rs1 = ste1.executeQuery("SELECT * FROM Categorie where nomCat ='" + NomCat + "'" );
       int idC = 0;
       while(rs1.next())
            idC= rs1.getInt("idCat");
        pre.setString(1, p.getNomProd());
        pre.setInt(2, idC);
        pre.setDouble(3, p.getQteStockProd());
        pre.setInt(4, p.getPrixProd());
        pre.setString(5, p.getTypeProd());
        pre.setInt(6, p.getIdProd());
        pre.executeUpdate();
                System.out.println("khra mchet");

   }
   public void SupprimerProduit(Produit p) throws SQLException{
        String req ="update produit set etatProd=? where idProd = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, p.getEtatProd());
        pre.setInt(2, p.getIdProd());
        pre.executeUpdate();
   }
   
   
     public Produit RechercheProduit(String nom) throws SQLException{
        ResultSet rs = ste.executeQuery("select * from produit where nomProd = '"+nom+"'");
        Produit pr = new Produit();
        while(rs.next())
        {
            pr.setNomProd(rs.getString("nomProd"));
            pr.setIdProd(rs.getInt("idProd"));
        }
        return pr ;
    }
}
