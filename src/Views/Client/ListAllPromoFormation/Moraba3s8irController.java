/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.ListAllPromoFormation;

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
public class Moraba3s8irController implements Initializable {

    
    @FXML
    private Text nom;
    @FXML
    private Text nomFor;
    @FXML
    private Text Prix;
    @FXML
    private Text nv_prix;
    @FXML
    private ImageView image;
    @FXML
    private VBox session;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


  

    public Text getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public Text getNomFor() {
        return nomFor;
    }

    public void setNomFor(String nomFor) {
        this.nomFor.setText(nomFor);
    }

    
    public Text getPrix() {
        return Prix;
    }

    public void setPrix(String Prix) {
        this.Prix.setText(Prix);
    }

    public Text getNv_prix() {
        return nv_prix;
    }

    public void setNv_prix(String nv_prix) {
        this.nv_prix.setText(nv_prix);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(String image) {
        File file = new File("D:/wamp64/www/final/web/public/uploads/brochures/Promotion/" + image);
        this.image.setImage(new Image(file.toURI().toString()));
    }
    
}
