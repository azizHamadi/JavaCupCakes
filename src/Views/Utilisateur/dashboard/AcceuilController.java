/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Utilisateur.dashboard;

import Entity.Utilisateur;
import Services.userServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author pc_roua
 */
public class AcceuilController implements Initializable {

    private Node [] listepage ;
    private Node [] listepageF ;
    private Node [] listepageP ;
    private int nbrLignePage = 0 ;
    private int nbrLignePageF = 0 ;
    private int nbrLignePageP = 0 ;
    @FXML
    private JFXButton Client;
    @FXML
    private JFXButton Patisserie;
    @FXML
    private JFXButton Formateur;
    @FXML
    private VBox vbox1;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ContenuFXML.fxml"));
            Node root = loader.load();
            ContenuFxmlController contenu = loader.getController();
            userServices us = new userServices();
            contenu.setVbox1(vbox1);
            contenu.AfficherUser(us.listClient(""));
            vbox1.getChildren().clear();
            vbox1.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
}

    @FXML
    private void AfficherAllUser(MouseEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ContenuFXML.fxml"));
        Node root = loader.load();
        ContenuFxmlController contenu = loader.getController();
        userServices us = new userServices();
        contenu.setVbox1(vbox1);
        contenu.AfficherUser(us.listClient(""));
        vbox1.getChildren().clear();
        vbox1.getChildren().add(root);
    }

    @FXML
    private void AfficherClient(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ContenuFXML.fxml"));
        Node root = loader.load();
        ContenuFxmlController contenu = loader.getController();
        userServices us = new userServices();
        contenu.setVbox1(vbox1);
        contenu.AfficherUser(us.listClient("a:1:{i:0;s:11:\"ROLE_CLIENT\";}"));
        vbox1.getChildren().clear();
        vbox1.getChildren().add(root);
    }

    @FXML
    private void AfficherPatisserie(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ContenuFXML.fxml"));
        Node root = loader.load();
        ContenuFxmlController contenu = loader.getController();
        userServices us = new userServices();
        contenu.setVbox1(vbox1);
        contenu.AfficherUser(us.listClient("a:1:{i:0;s:15:\"ROLE_PATISSERIE\";}"));
        vbox1.getChildren().clear();
        vbox1.getChildren().add(root);
    }

    @FXML
    private void AfficherFormateur(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ContenuFXML.fxml"));
        Node root = loader.load();
        ContenuFxmlController contenu = loader.getController();
        userServices us = new userServices();
        contenu.setVbox1(vbox1);
        contenu.AfficherUser(us.listClient("a:1:{i:0;s:14:\"ROLE_FORMATEUR\";}"));
        vbox1.getChildren().clear();
        vbox1.getChildren().add(root);
    }

    @FXML
    private void Deconnexion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));
        Parent root = loader.load();
        Formateur.getScene().setRoot(root);

    }

   
   
}