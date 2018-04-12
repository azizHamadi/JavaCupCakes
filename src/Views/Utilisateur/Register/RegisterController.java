/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Utilisateur.Register;

import Entity.Utilisateur;
import Services.userServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author pc_roua
 */
public class RegisterController implements Initializable {

    @FXML
    private JFXComboBox<String> cb;
    @FXML
    private JFXButton Enregistrer;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField Adresse;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField email;
   
    @FXML
    private JFXTextField prenom;
  
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField Cpassword;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb.getItems().add("CLIENT");
        cb.getItems().add("FORMATEUR");
        cb.getItems().add("PATISSERIE");
    }    

    @FXML
    private void AjouterUtilsateur(ActionEvent event) throws SQLException, IOException, MessagingException {
          userServices us = new userServices ();
        String role = cb.getValue();
        if (role == "CLIENT")
            role = "a:1:{i:0;s:11:\"ROLE_CLIENT\";}";
        if (role == "FORMATEUR")
            role = "a:1:{i:0;s:14:\"ROLE_FORMATEUR\";}";
        if (role == "PATISSERIE")
            role = "a:1:{i:0;s:15:\"ROLE_PATISSERIE\";}";
        if (validateUseraneme() & validateEmail () & validatetel()  & validatePassword() & EqualsPassword ()){
        Utilisateur u = new Utilisateur (username.getText(), email.getText(),password.getText(),Cpassword.getText(),role,tel.getText(),Adresse.getText(),nom.getText(),prenom.getText());
        us.AjouterUtilisateur(u);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/login.fxml"));       
        Parent root = loader.load();   
        email.getScene().setRoot(root);}
    }
    private boolean validateUseraneme (){
         Pattern  p = Pattern.compile("[a-zA-Z]+");
         Matcher m = p.matcher(username.getText());
         if (m.find() && m.group().equals(username.getText())){
         return true;
         }
         else{
         Alert alert  = new Alert(Alert.AlertType.WARNING);
         alert.setTitle("validate username");
         alert.setHeaderText(null);
         alert.setContentText("please entrer username valide");
         alert.showAndWait();
         
         
         }
        return false;
         
         
         }
    
    
     private boolean validateEmail (){
         Pattern  p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
         Matcher m = p.matcher(email.getText());
         if (m.find() && m.group().equals(email.getText())){
         return true;
         }
         else{
         Alert alert  = new Alert(Alert.AlertType.WARNING);
         alert.setTitle("validate Email");
         alert.setHeaderText(null);
         alert.setContentText("please entrer email valide");
         alert.showAndWait();
         
         
         }
        return false;
         
         
         }
     
        private boolean validatePassword(){
         Pattern  p = Pattern.compile("([a-zA-Z0-9].{3,10})");
         Matcher m = p.matcher(password.getText());
         if (m.matches()){
         return true;
         }
         else{
         Alert alert  = new Alert(Alert.AlertType.WARNING);
         alert.setTitle("validate Password");
         alert.setHeaderText(null);
         alert.setContentText("please entrer password valide");
         alert.showAndWait();
         
         
         }
        return false;
         
         
         }
        
         private boolean validatetel(){
         Pattern  p = Pattern.compile("([0-9].{7})");
         Matcher m = p.matcher(tel.getText());
         if (m.matches()){
         return true;
         }
         else{
         Alert alert  = new Alert(Alert.AlertType.WARNING);
         alert.setTitle("validate Telephone");
         alert.setHeaderText(null);
         alert.setContentText("please entrer un nemero telephone valide ");
         alert.showAndWait();
         
         
         }
        return false;
         
        
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

         
}
