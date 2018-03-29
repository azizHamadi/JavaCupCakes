/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Produit.ListAllProduit;

import Views.Client.Produit.ListAllProduit.*;
import java.io.File;
import java.net.URL;
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

    public void setImage(String image) {
        File file = new File("C:/wamp3/www/CupCakesF/web/public/uploads/brochures/Produit/" + image);
        this.image.setImage(new Image(file.toURI().toString()));
    }

    public void setNomCat(String nomCat) {
        this.nomCat.setText(nomCat);
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
