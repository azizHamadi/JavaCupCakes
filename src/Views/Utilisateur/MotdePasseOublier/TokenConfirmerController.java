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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class TokenConfirmerController implements Initializable {

    @FXML
    private JFXTextField token;
    @FXML
    private JFXButton Envoyer;
    @FXML
    private JFXButton Annuler;
    private Utilisateur user ;
    @FXML
    private AnchorPane anchorpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Envoyer(ActionEvent event) throws SQLException, IOException {
        userServices us = new userServices ();
        user.setConfirmationToken(token.getText());
        if(us.verif_token(user))
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangerMotDePasse.fxml"));       
            Parent root = loader.load(); 
            ChangerMotDePasseController cmp = loader.getController();
            cmp.setUser(user);
            anchorpane.getChildren().clear();
            anchorpane.getChildren().add(root);
        }
            
    }

    @FXML
    private void Annuler(ActionEvent event) {
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
    
}
