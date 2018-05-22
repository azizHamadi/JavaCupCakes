/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Utilisateur.Profil;

import Entity.SessionUser;
import Entity.Utilisateur;
import Services.userServices;
import Views.Client.Utilisateur.Profil.UpdateUserController;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class ProfilClientController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label Email;
    @FXML
    private Label Telephone;
    @FXML
    private Label Adresse;
    @FXML
    private Label Prenom;
    @FXML
    private Label Nom;
    @FXML
    private JFXButton Retour;
    @FXML
    private JFXButton Modifier;
    @FXML
    private JFXButton ajout;
     private String imagef ="";
      private Utilisateur User;
      private VBox body ;
    @FXML
    private AnchorPane bodyprofil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File ("C:/wamp64/www/final/web/public/uploads/brochures/User/"+SessionUser.getImageProfil());
        image.setImage(new Image(file.toURI().toString()));
        if (SessionUser.getImageProfil().length() != 0)
            ajout.setVisible(false);
        Email.setText(SessionUser.getEmail());
        Telephone.setText(SessionUser.getPhoneNumber());
        Adresse.setText(SessionUser.getAddresse());
        Nom.setText(SessionUser.getNom());
        Prenom.setText(SessionUser.getPrenom());
    }    

    public void Button(){
    if(SessionUser.getImageProfil() == null){
            ajout.setVisible(true);}
            else{
                ajout.setVisible(false);
            }
    }
    public AnchorPane getBodyprofil() {
        return bodyprofil;
    }

    public void setBodyprofil(AnchorPane bodyprofil) {
        this.bodyprofil = bodyprofil;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(String image) {
        File file = new File ("C:/wamp64/www/final/web/public/uploads/brochures/User/"+image);
        this.image.setImage(new Image(file.toURI().toString()));
    }

    public Label getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email.setText(Email);
    }

    public Label getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone.setText(Telephone);
    }

    public Label getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse.setText(Adresse);
    }

    public Label getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom.setText(Prenom);
    }

    public Label getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom.setText(Nom);
    }

    public JFXButton getRetour() {
        return Retour;
    }

    public void setRetour(JFXButton Retour) {
        this.Retour = Retour;
    }

    public JFXButton getModifier() {
        return Modifier;
    }

    public void setModifier(JFXButton Modifier) {
        this.Modifier = Modifier;
    }

    public JFXButton getAjout() {
        return ajout;
    }

    public void setAjout(JFXButton ajout) {
        this.ajout = ajout;
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../test/folder/ClientTemplate.fxml"));       
        Parent root = loader.load();   
        Nom.getScene().setRoot(root);
    }

    @FXML
    private void ModifierUser(ActionEvent event) throws IOException {
        FXMLLoader loaderin = new FXMLLoader(getClass().getResource("UpdateUser.fxml"));
        Node loaderUp = loaderin.load();
        UpdateUserController uc = loaderin.getController();
        bodyprofil.getChildren().clear();
        bodyprofil.getChildren().add(loaderUp);
        uc.setBody(bodyprofil);
    }

    @FXML
    private void Enregistrer(ActionEvent event) throws IOException, SQLException, MessagingException {
        FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
         fileChooser.getExtensionFilters().addAll(
         new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg" ,"*.jpeg"));
       
        File f = fileChooser.showOpenDialog(null);
        if (f != null){
            System.out.println(f.getName());
        }
        imagef = f.getName();
        System.out.println(imagef);
        File fd = new File("C:/wamp64/www/final/web/public/uploads/brochures/User/"+f.getName());
        String imaged = "";
        imaged ="file:///"+fd.getAbsolutePath();
        Files.copy(f.getAbsoluteFile().toPath(),fd.getAbsoluteFile().toPath());
        image.setImage(new Image(imaged));

        userServices us = new userServices();
        Utilisateur user = new Utilisateur(SessionUser.getId(),imagef);
        us.AjouterImage(user);
        System.out.println("test1.ProfilController.Enregistrer()");
    }

    public VBox getBody() {
        return body;
    }

    public void setBody(VBox body) {
        this.body = body;
    }
    
}
