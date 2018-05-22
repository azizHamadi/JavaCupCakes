/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.test.folder;

import Views.Client.Utilisateur.Profil.ProfilClientController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class ClientTemplateController implements Initializable {

    @FXML
    private Button btnRecette;
    @FXML
    private HBox body;
    @FXML
    private Button GestionFormations;
    @FXML
    private Button btnPromotion;
    @FXML
    private Button Deconnexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void GestionRecette(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/Recette/GererRecette.fxml"));
        try {
            body.getChildren().clear();
            body.getChildren().add(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(ClientTemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void GestionProduit(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/Produit/GererProduit.fxml"));
        try {
            body.getChildren().clear();
            body.getChildren().add(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(ClientTemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GestionCommande(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/Commande/GererCommande.fxml"));
        try {
            body.getChildren().clear();
            body.getChildren().add(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(ClientTemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GestionFormations(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/GestionFormation/GererFormation.fxml"));
        try {
            body.getChildren().clear();
            body.getChildren().add(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(ClientTemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GestionPromotion(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/Promotion/GererPromo.fxml"));
        try {
            body.getChildren().clear();
            body.getChildren().add(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(ClientTemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Profil(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/Utilisateur/Profil/ProfilClient.fxml"));
        ProfilClientController pc = loader.getController();
        //pc.setBody(body);
        try {
            body.getChildren().clear();
            body.getChildren().add(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(ClientTemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Deconnexion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Utilisateur/Login/Login.fxml"));
        Parent root = loader.load();
        body.getScene().setRoot(root);
    }
}
