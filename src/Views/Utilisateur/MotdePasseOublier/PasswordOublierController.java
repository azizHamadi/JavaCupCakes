/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Utilisateur.MotdePasseOublier;

import Entity.Utilisateur;
import Services.userServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author pc_roua
 */
public class PasswordOublierController implements Initializable {

    @FXML
    private JFXButton Enregistrer;
    @FXML
    private JFXButton Login;
    private JFXPasswordField Cpassword;
    private JFXPasswordField password;
    private JFXTextField username;
    @FXML
    private JFXTextField email;
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
    public void Enregistrer (ActionEvent event) throws SQLException,  MessagingException,   IOException{        
        userServices us = new userServices ();
        Utilisateur user = us.verif_email(email.getText());
        if(user != null)
        {
            us.ConfirmerPassword(user);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TokenConfirmer.fxml"));       
            Parent root = loader.load(); 
            TokenConfirmerController tk = loader.getController();
            tk.setUser(user);
            anchorpane.getChildren().clear();
            anchorpane.getChildren().add(root);
        }
        else
        {
            Alert alert  = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Verification email");
            alert.setHeaderText(null);
            alert.setContentText("varifier votre email");
            alert.showAndWait();
        }   
    }
    
         public boolean EqualsPassword (){
            if (password.getText().equals(Cpassword.getText()))
           
                    {
                        
                    return true;}
            else{
          Alert alert  = new Alert(Alert.AlertType.WARNING);
         alert.setTitle("validate  Confirmation Password");
         alert.setHeaderText(null);
         alert.setContentText("please entrer Password  valide");
         alert.showAndWait();
          return false;  } 
           }
         
         @FXML
        private void Login (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));       
        Parent root = loader.load();   
        email.getScene().setRoot(root);
        } 
}
