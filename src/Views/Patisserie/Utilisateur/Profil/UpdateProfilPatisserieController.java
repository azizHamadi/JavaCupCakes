/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Utilisateur.Profil;

import Entity.SessionUser;
import Entity.Utilisateur;
import Services.userServices;
import Views.Client.Utilisateur.Profil.ProfilClientController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class UpdateProfilPatisserieController implements Initializable {

 
    
     private Utilisateur User = new Utilisateur();
    @FXML
    private JFXButton tbnUpdate;
    @FXML
    private JFXTextField Nom;
    @FXML
    private JFXTextField Prenom;
    @FXML
    private JFXTextField Adresse;
    @FXML
    private JFXTextField Phone;
    @FXML
    private AnchorPane body;
    @FXML
    private Label labNom;
    @FXML
    private Label labPrenom;
    @FXML
    private Label labAdresse;
    @FXML
    private Label labAdresse1;
    @FXML
    private JFXButton btnAnnuler;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Adresse.setText(SessionUser.getAddresse());
        Nom.setText(SessionUser.getNom());
        Prenom.setText(SessionUser.getPrenom());
        Phone.setText(SessionUser.getPhoneNumber());
    }    

    @FXML
    private void ModifierUser(ActionEvent event) throws SQLException, IOException {
        User = new Utilisateur(SessionUser.getId(), SessionUser.getUsername(),SessionUser.getEmail(), Phone.getText(), Adresse.getText(),Nom.getText(),Prenom.getText(),SessionUser.getImageProfil());
        userServices us = new userServices();
        us.ModifierLineUser(User);
        
        FXMLLoader loaderin = new FXMLLoader(getClass().getResource("ProfilPatisserie.fxml"));
        Node UPDAu = loaderin.load();
        ProfilPatisserieController pc = loaderin.getController();
        SessionUser.setAddresse(User.getAddresse());
        SessionUser.setEmail(User.getEmail());
        SessionUser.setNom(User.getNom());
        SessionUser.setPrenom(User.getPrenom());
        SessionUser.setPhoneNumber(User.getPhoneNumber());
        pc.setAdresse(User.getAddresse());
        pc.setEmail(User.getEmail());
        pc.setNom(User.getNom());
        pc.setPrenom(User.getPrenom());
        pc.setTelephone(User.getPhoneNumber());
        pc.setImage(User.getImageProfil());
        body.getChildren().clear();
        body.getChildren().add(UPDAu);
       // pc.setUser(User);
        pc.setBodProfil(body);
    }

    

  

   

    

    

    public JFXButton getTbnUpdate() {
        return tbnUpdate;
    }

    public void setTbnUpdate(JFXButton tbnUpdate) {
        this.tbnUpdate = tbnUpdate;
    }

    public JFXTextField getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom.setText(Nom);
    }

    public JFXTextField getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom.setText(Prenom);
    }

    public JFXTextField getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse.setText(Adresse);
    }

    public JFXTextField getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone.setText(Phone);
    }

    public AnchorPane getBody() {
        return body;
    }

    public void setBody(AnchorPane body) {
        this.body = body;
    }

    @FXML
    private void Annuler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../test/folder/BackForTemplate.fxml"));       
        Parent root = loader.load(); 
        btnAnnuler.getScene().setRoot(root);
    }

    public Utilisateur getUser() {
        return User;
    }

    public void setUser(Utilisateur User) {
        this.User = User;
    }
    
}
