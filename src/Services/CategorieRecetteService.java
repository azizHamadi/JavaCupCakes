/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.CategorieRec;
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
 * @author aziz
 */
public class CategorieRecetteService {
    
    public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;
    private ObservableList<CategorieRec> ListeCatRec ;
    
    public CategorieRecetteService() {
        try {
            ste =con.createStatement();
            } catch (SQLException ex) {
               Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public int CountRecetteParCat(int idCat) throws SQLException{
        int count = 0 ;
        ResultSet rs = ste.executeQuery("select count(*) from recette where idCatRec="+idCat);
        while(rs.next())
            count = rs.getInt(1);
        return count;

    }
    
    public void AjouterCategorieRecette(CategorieRec categorieRec) throws SQLException{
        String req ="INSERT INTO categorie_rec (nomCatRec) VALUES (?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, categorieRec.getNomCatRec());
        pre.executeUpdate();
        setListeCatRec(FXCollections.observableArrayList(AfficherCategorieRecette()));
    }

    public void ModifierCategorieRecette(CategorieRec categorieRec) throws SQLException{
        String req ="update categorie_rec set nomCatRec = ? where idCatRec = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, categorieRec.getNomCatRec());
        pre.setInt(2, categorieRec.getIdCatRec());
        pre.executeUpdate();
    }

    public void SupprimerCategorieRecette(CategorieRec categorieRec) throws SQLException{
        String req ="delete from categorie_rec where idCatRec = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, categorieRec.getIdCatRec());
        pre.executeUpdate();
    }

    public List<CategorieRec> AfficherCategorieRecette() throws SQLException{
        List<CategorieRec> CategorieRecs = new ArrayList<>();
        ResultSet rs = ste.executeQuery("select * from categorie_rec");
        while(rs.next())
        {
            CategorieRecs.add(new CategorieRec(rs.getInt("idCatRec"), rs.getString("nomCatRec")));
        }
        return CategorieRecs ;
    }

    public CategorieRec rechercheCategorieRec(String nom) throws SQLException{
        CategorieRec cat = null ; 
        ResultSet rs = ste.executeQuery("select * from categorie_rec where nomCatRec ='"+nom+"'");
        while(rs.next())
            {
                cat = new CategorieRec(rs.getInt("idCatRec"), rs.getString("nomCatRec"));
            }
        return cat ;
    }
    public ObservableList<CategorieRec> getListeCatRec() throws SQLException {
        return FXCollections.observableArrayList(AfficherCategorieRecette());
    }

    public void setListeCatRec(ObservableList<CategorieRec> ListeCatRec) {
        this.ListeCatRec = ListeCatRec;
    }

    
}
