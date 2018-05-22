/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Utilisateur.MotdePasseOublier;

import Entity.Utilisateur;
import Services.userServices;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class ChangerMotDePasseController implements Initializable {

    private Utilisateur user;
    @FXML
    private JFXTextField Motdepasse;
    @FXML
    private JFXButton Enregistrer;
    @FXML
    private JFXButton Annuler;
    @FXML
    private JFXTextField CMotdepasse;
    @FXML
    private AnchorPane anchorpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    
    @FXML
    private void Enregistrer(ActionEvent event) throws SQLException, MessagingException, IOException {
        userServices us = new userServices ();
        user.setPassword(Motdepasse.getText());
        if(Motdepasse.getText().equals(CMotdepasse.getText()) && Motdepasse.getText().length()>2)
        {
            us.PasswordUtilisateur(user);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/login.fxml"));       
            Parent root = loader.load();  
            
            Motdepasse.getScene().setRoot(root);
        }
        else
        {
            Alert alert  = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Verification mot de passe");
            alert.setHeaderText(null);
            alert.setContentText("please entrer un mot de passe valide");
            alert.showAndWait();
        }
            
    }

    @FXML
    private void Annuler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));       
        Parent root = loader.load();   
        Annuler.getScene().setRoot(root);
    }
    
}
