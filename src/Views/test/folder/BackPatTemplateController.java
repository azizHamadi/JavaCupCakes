/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.test.folder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class BackPatTemplateController implements Initializable {

    @FXML
    private Button btnRecette;
    @FXML
    private HBox body;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GestionProduit(ActionEvent event) {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Patisserie/Produit/GererProduit.fxml"));
        try {
            body.getChildren().clear();
            body.getChildren().add(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(BackPatTemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GestionCommande(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Patisserie/Commande/GererCommande.fxml"));
        try {
            body.getChildren().clear();
            body.getChildren().add(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(BackPatTemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GestionPromotion(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Patisserie/Promotion/GererPromotion.fxml"));
        try {
            body.getChildren().clear();
            body.getChildren().add(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(BackPatTemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GestionRecette(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Patisserie/Recette/GererRecette.fxml"));
        try {
            body.getChildren().clear();
            body.getChildren().add(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(BackPatTemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
