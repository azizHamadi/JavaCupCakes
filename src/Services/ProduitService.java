/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Categorie;
import Entity.FeedBack;
import Entity.Produit;
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
        ResultSet rs = ste.executeQuery("SELECT * FROM Utilisateur WHERE id ="+SessionUser.getId());
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
            ResultSet rs =ste.executeQuery("SELECT * FROM Produit where  idUser = "+SessionUser.getId()+" and etatProd='" + v + "'" ) ;
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
                           Prod.add(new Produit(rs.getInt("IdProd"),rs.getString("nomProd"),(double)rs.getInt("qteStockProd"),rs.getString("typeProd"),rs.getInt("prixProd"),rs.getString("imageprod"),rs.getInt("QteAcheter"),cd,user,rs.getInt("valeur")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Prod;
    }

        public List<Produit> AfficherProduit() throws SQLException{
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
                System.out.println(rs.getString("imageprod"));
                           Prod.add(new Produit(rs.getInt("IdProd"),rs.getString("nomProd"),(double)rs.getInt("qteStockProd"),rs.getString("typeProd"),rs.getInt("prixProd"),rs.getString("imageprod"),rs.getInt("QteAcheter"),cd,user,rs.getInt("valeur")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Prod;
    }

    
    public List<Produit> AfficherProduitBack(int idUser) throws SQLException{
        List<Produit> Prod = new ArrayList();
        String v="Vrai";
        String req = "select * from produit where etatProd ='" + v + "'"  ;
        if (idUser != 0)
            req += "and idUser =" + idUser ;
        ResultSet rsProduit = ste.executeQuery(req);

        while(rsProduit.next())
        {
            Produit pr = new Produit();
            Utilisateur user = new Utilisateur();
            Categorie  categorie = new Categorie();
            pr.setImageprod(rsProduit.getString("imageprod"));
            pr.setNomProd(rsProduit.getString("nomProd"));
            pr.setPrixProd(rsProduit.getInt("prixProd"));
            pr.setIdProd(rsProduit.getInt("idProd"));            
            pr.setQteStockProd(rsProduit.getDouble("qteStockProd"));
            pr.setQteAcheter(rsProduit.getInt("QteAcheter"));
            pr.setValeur(rsProduit.getInt("valeur"));
            pr.setTypeProd(rsProduit.getString("typeProd"));
            int idU = rsProduit.getInt("idUser");
            ResultSet rsUser = ste2.executeQuery("select * from utilisateur where id="+ idU);
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }
            ResultSet rsCat = ste3.executeQuery("select * from categorie where idCat = "+ rsProduit.getInt("idCat"));
            while(rsCat.next())
            {
                categorie.setIdCat(rsCat.getInt("idCat"));
                categorie.setNomCat(rsCat.getString("nomCat"));
            }
            pr.setIdCat(categorie);
            pr.setIdUser(user);
            Prod.add(pr);

        }
        return Prod ;
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
              pr.setQteStockProd(rsRecette.getDouble("qteStockProd")); 
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
   }
   public void SupprimerProduit(Produit p) throws SQLException{
        String req ="update produit set etatProd=? where idProd = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, p.getEtatProd());
        pre.setInt(2, p.getIdProd());
        pre.executeUpdate();
   }
    public void UpdateProduitStock (Produit p) throws SQLException{
        String req ="update Produit set qteStockProd=?,qteAcheter=? where idProd = ?";
        PreparedStatement pre = con.prepareStatement(req); 
        pre.setDouble(1,p.getQteStockProd());
        pre.setInt(2, p.getQteAcheter());
        pre.setInt(3, p.getIdProd());
        pre.executeUpdate();
   }
     public void UpdateProduitValeur (Produit p) throws SQLException{
        String req ="update produit set valeur="+(p.getValeur() + 1)+" where idProd ="+p.getIdProd();
        PreparedStatement pre = con.prepareStatement(req);
         System.out.println("p = "+(p.getValeur()+1) + " id = "+ p.getIdProd());
        pre.executeUpdate();
         System.out.println(p);  
   }


    public List<String> AfficherNomP() throws SQLException{
        List<String> Prod = new ArrayList();
        String v="Vrai";
            try {
            ResultSet rs =ste.executeQuery("SELECT nomProd FROM Produit where etatProd='" + v + "'" ) ;
            while(rs.next()){
                Prod.add(rs.getString("NomProd"));
           }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Prod;
    }
    
       public List<Produit> AfficherPrixProduit() throws SQLException{
        List<Produit> produits = new ArrayList<>();
        ResultSet rs = ste.executeQuery("select * from Produit");
        Produit pr = new Produit();
        while(rs.next())
        {
            pr.setPrixProd(rs.getInt("prixProd"));
        }
        return produits ;
    }
    public Produit RechercheProduit(String nom) throws SQLException{
        ResultSet rs = ste.executeQuery("select * from produit where nomProd = '"+nom+"'");
        Produit pr = new Produit();
        while(rs.next())
        {
            pr.setNomProd(rs.getString("nomProd"));
            pr.setIdProd(rs.getInt("idProd"));
            pr.setPrixProd(rs.getInt("prixProd"));
        }
        return pr ;
    }
     
    public List<Produit> RechercheProduitClient(String nom) throws SQLException{
        String req = "select * from produit where etatProd = 'vrai' ";
        if(nom != "" )
            req+= " and nomProd like '%"+nom+"%'";
        ResultSet rs = ste.executeQuery(req);
        List<Produit> listProd = new ArrayList<>();
        while(rs.next())
        {
            listProd.add( new Produit(rs.getInt("idProd"), rs.getString("nomProd"), rs.getDouble("qteStockProd"), rs.getString("typeProd"), rs.getInt("prixProd"), rs.getString("imageProd"), rs.getInt("QteAcheter"), new Categorie(rs.getInt("idCat")), new Utilisateur(rs.getInt("idUser")), rs.getInt("valeur")));
            
        }
        return listProd ;
    }
}
