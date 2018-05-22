/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Utilisateur.Profil;

import Entity.Utilisateur;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author pc_roua
 */
public class ProfilController implements Initializable {

    @FXML
    private Label Nom;
    @FXML
    private Label Prenom;
    @FXML
    private Label Adresse;
    @FXML
    private Label Telephone;
    @FXML
    private Label Email;
    private Utilisateur User;
    private VBox body ;
    @FXML
    private JFXButton Retour;
    @FXML
    private ImageView Image;
    private String imagef ="";
    private JFXButton ajout;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    
    public Label getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom.setText(Nom);
    }

    public Label getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom.setText(Prenom);
    }

    public Label getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse.setText(Adresse);
    }

    public Label getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone.setText(Telephone);
    }

    public Label getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email.setText(Email);
    }

    public ImageView getImage() {
        return Image;
    }

    public void setImage(String Image) {
        File file = new File ("C:/wamp64/www/final/web/public/uploads/brochures/User/"+Image);
        this.Image.setImage(new Image(file.toURI().toString()));
    }

    public Utilisateur getUser() {
        return User;
    }

    public void setUser(Utilisateur User) {
        this.User = User;
    }

    public VBox getBody() {
        return body;
    }

    public void setBody(VBox body) {
        this.body = body;
    }

   
    @FXML
        private void Retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../dashboard/Acceuil.fxml"));       
        Parent root = loader.load();   
        Nom.getScene().setRoot(root);
    } 

      
   

    
    
}

