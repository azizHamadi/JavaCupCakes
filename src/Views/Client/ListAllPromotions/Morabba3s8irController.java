/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.ListAllPromotions;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author hamdi fathallah
 */
public class Morabba3s8irController implements Initializable {

    @FXML
    private VBox prod;
    @FXML
    private Text nom;
    @FXML
    private Text nomCat;
    @FXML
    private Text Prix;
    private Text nomUser;
    private Text note;
    private Text NomPat;
    @FXML
    private ImageView image;
    @FXML
    private Text Nv_prix;

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
         System.out.println(image);
        File file = new File("C:/wamp64/www/final/web/public/uploads/brochures/Produit/" + image);
        this.image.setImage(new Image(file.toURI().toString()));
    }

    public void setNomCat(String nomCat) {
        this.nomCat.setText(nomCat);
    }

    public Text getNv_prix() {
        return Nv_prix;
    }

    public void setNv_prix(String Nv_prix) {
        this.Nv_prix.setText(Nv_prix);
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
