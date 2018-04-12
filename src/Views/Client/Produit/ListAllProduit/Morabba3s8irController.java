/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Produit.ListAllProduit;

import Entity.Produit;
import Services.LinePromoService;
import Views.Client.Produit.ListAllProduit.*;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class Morabba3s8irController implements Initializable {

    @FXML
    private VBox prod;
    @FXML
    private ImageView image;
    private Produit p;
    @FXML
    private Text nom;
    @FXML
    private Text nomCat;
    private Text nomUser;
    private Text note;
    @FXML
    private Text Prix;
    @FXML
    private Text NomPat;
    @FXML
    private Text Promotion;
    @FXML
    private Text NV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        File file = new File("C:/wamp64/www/final/web/public/uploads/brochures/Produit/a18bd01eef49479340fa39143532b1b2.jpeg");
        Image img = new Image(file.toURI().toString());
        image.setImage(img);

    }    
 
    public void Controlle() throws SQLException{
        LinePromoService line = new LinePromoService();
                     List<Integer> l =line.afficherProduit();
                     if(l.contains(p.getIdProd())){
                          Promotion.setVisible(true);
                          NV.setVisible(true);
                        }
                        else{
                          Promotion.setVisible(false);
                            NV.setVisible(false);

                        }
                    
                     
    }
    public void setImage(String image) {
        File file = new File("C:/wamp64/www/final/web/public/uploads/brochures/Produit/" + image);
        this.image.setImage(new Image(file.toURI().toString()));
    }

    public void setNomCat(String nomCat) {
        this.nomCat.setText(nomCat);
    }

    public Produit getP() {
        return p;
    }

    public void setP(Produit p) {
        this.p = p;
    }

    public Text getPromotion() {
        return Promotion;
    }

    public void setPromotion(String Promotion) {
        this.Promotion.setText(Promotion);
    }

    public Text getNV() {
        return NV;
    }

    public void setNV(String NV) {
        this.NV.setText(NV);
    }

   

    

    public void setNomUser(String nomUser) {
        this.nomUser.setText(nomUser);
    }


    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public VBox getProd() {
        return prod;
    }

    public ImageView getImage() {
        return image;
    }

    public Text getNom() {
        return nom;
    }

    public Text getNomCat() {
        return nomCat;
    }

 
    public Text getNomUser() {
        return nomUser;
    }

    public Text getNote() {
        return note;
    }

    public Text getPrix() {
        return Prix;
    }

    public void setPrix(String Prix) {
        this.Prix.setText(Prix);
    }

    public Text getNomPat() {
        return NomPat;
    }

    public void setNomPat(String NomPat) {
        this.NomPat.setText(NomPat);
    }
    
    
}
