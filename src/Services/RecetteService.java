/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.CategorieRec;
import Entity.Recette;
import Entity.Utilisateur;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.Date;
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
 * @author aziz
 */
public class RecetteService {

    public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;
    private Statement ste2;
    private Statement ste3;

    public RecetteService() {
        try {
            ste2 =con.createStatement();
            ste3 =con.createStatement();
            ste =con.createStatement();
            } catch (SQLException ex) {
               Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AjouterRecette(Recette recette) throws SQLException{
        String req ="INSERT INTO recette (nomRec,dateRec,etatRec,idUser,idCatRec,descriptionRec,imageRec) VALUES (?,NOW(),'oui',?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, recette.getNomRec());
        pre.setInt(2, recette.getIdUser().getId());
        pre.setInt(3, recette.getIdCatRec().getIdCatRec());
        pre.setString(4, recette.getDescriptionRec());
        pre.setString(5, recette.getImageRec());
        pre.executeUpdate();
    }

    public void ModifierRecette(Recette recette) throws SQLException{
        String req ="update recette set nomRec = ? , etatRec = ? , idUser = ? , idCatRec = ? , descriptionRec = ? where idRec = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, recette.getNomRec());
        pre.setString(2, recette.getEtatRec());
        pre.setInt(3, recette.getIdUser().getId());
        pre.setInt(4, recette.getIdCatRec().getIdCatRec());
        pre.setString(5, recette.getDescriptionRec());
        pre.setInt(6, recette.getIdRec());
        pre.executeUpdate();
    }

    public void SupprimerRecette(Recette recette) throws SQLException{
        String req ="delete from recette where idRec = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, recette.getIdRec());
        pre.executeUpdate();
    }

    public List<Recette> AfficherRecette() throws SQLException{
        List<Recette> recettes = new ArrayList<>();
        ResultSet rsRecette = ste.executeQuery("select * from recette");

        while(rsRecette.next())
        {
            Recette rec = new Recette();
            Utilisateur user = new Utilisateur();
            CategorieRec  categorie = new CategorieRec();
            rec.setDateRec(rsRecette.getDate("dateRec"));
            rec.setDescriptionRec(rsRecette.getString("descriptionRec"));
            rec.setEtatRec(rsRecette.getString("etatRec"));
            rec.setIdRec(rsRecette.getInt("idRec"));            
            rec.setImageRec(rsRecette.getString("imageRec"));
            rec.setNomRec(rsRecette.getString("nomRec"));
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
            ResultSet rsCatRecette = ste3.executeQuery("select * from categorie_rec where idCatRec = "+ rsRecette.getInt("idCatRec"));
            while(rsCatRecette.next())
            {
                categorie.setIdCatRec(rsCatRecette.getInt("idCatRec"));
                categorie.setNomCatRec(rsCatRecette.getString("nomCatRec"));
            }
            rec.setIdCatRec(categorie);
            rec.setIdUser(user);
            recettes.add(rec);

        }
        return recettes ;
    }
    
    public List<Recette> RecetteParCategorie(int idCat) throws SQLException{
        List<Recette> listR = new ArrayList<>();
                ResultSet rsRecette = ste.executeQuery("select * from recette where idCatRec="+idCat);

        while(rsRecette.next())
        {
            Recette rec = new Recette();
            Utilisateur user = new Utilisateur();
            CategorieRec  categorie = new CategorieRec();
            rec.setDateRec(rsRecette.getDate("dateRec"));
            rec.setDescriptionRec(rsRecette.getString("descriptionRec"));
            rec.setEtatRec(rsRecette.getString("etatRec"));
            rec.setIdRec(rsRecette.getInt("idRec"));            
            rec.setImageRec(rsRecette.getString("imageRec"));
            rec.setNomRec(rsRecette.getString("nomRec"));
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
            ResultSet rsCatRecette = ste3.executeQuery("select * from categorie_rec where idCatRec = "+ rsRecette.getInt("idCatRec"));
            while(rsCatRecette.next())
            {
                categorie.setIdCatRec(rsCatRecette.getInt("idCatRec"));
                categorie.setNomCatRec(rsCatRecette.getString("nomCatRec"));
            }
            rec.setIdCatRec(categorie);
            rec.setIdUser(user);
            listR.add(rec);
        }
        return listR;
    }
    
    public List<Recette> RechercheParNomRecette(String nom) throws SQLException{
        List<Recette> listR = new ArrayList<>();
        ResultSet rsRecette = ste.executeQuery("select * from recette where nomRec like '%"+nom+"%'");
        while(rsRecette.next())
        {
            Recette rec = new Recette();
            Utilisateur user = new Utilisateur();
            CategorieRec  categorie = new CategorieRec();
            rec.setDateRec(rsRecette.getDate("dateRec"));
            rec.setDescriptionRec(rsRecette.getString("descriptionRec"));
            rec.setEtatRec(rsRecette.getString("etatRec"));
            rec.setIdRec(rsRecette.getInt("idRec"));            
            rec.setImageRec(rsRecette.getString("imageRec"));
            rec.setNomRec(rsRecette.getString("nomRec"));
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
            ResultSet rsCatRecette = ste3.executeQuery("select * from categorie_rec where idCatRec = "+ rsRecette.getInt("idCatRec"));
            while(rsCatRecette.next())
            {
                categorie.setIdCatRec(rsCatRecette.getInt("idCatRec"));
                categorie.setNomCatRec(rsCatRecette.getString("nomCatRec"));
            }
            rec.setIdCatRec(categorie);
            rec.setIdUser(user);
            listR.add(rec);
        }
        return listR;
    }

}
