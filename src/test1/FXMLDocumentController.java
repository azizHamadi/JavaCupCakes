/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import javafx.stage.Stage;
import Entity.SessionUser;
import Entity.Utilisateur;
import Services.UtilisateurService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author escobar
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button bntlog;
    @FXML
    private TextField login;
    @FXML
    private TextField mdp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LoginAction(ActionEvent event) throws SQLException, IOException {
        UtilisateurService us = new UtilisateurService();
        Utilisateur user = us.Login(login.getText());
        System.out.println(user);
        if(user != null && BCrypt.checkpw(mdp.getText(),user.getPassword().substring(0,2)+"a"+user.getPassword().substring(3)))
        {
            SessionUser.setAddresse(user.getAddresse());
            SessionUser.setEmail(user.getEmail());
            SessionUser.setId(user.getId());
            SessionUser.setPassword(user.getPassword());
            SessionUser.setPhoneNumber(user.getPhoneNumber());
            SessionUser.setRoles(user.getRoles());
            SessionUser.setUsername(user.getUsername());
            Parent root = FXMLLoader.load(getClass().getResource("../Views/test/folder/ClientTemplate.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();            
        }
        else
            System.out.println("7aaamdiiiiiiiiiiiiiiiiiiiiii");
    }
    
}
