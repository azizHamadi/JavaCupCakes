/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Promotion;

import Views.Patisserie.Produit.*;
import Views.Client.Recette.*;
import Views.Client.Recette.ListAllRecettes.ListAllRecettesController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class GererPromotionController implements Initializable {

    @FXML
    private VBox nav_bar;
    @FXML
    private VBox body;
    @FXML
    private Button btnListePromotion;
    @FXML
    private Button btnAjouterPromotion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void ListePromotion(ActionEvent event) {
    }

    @FXML
    private void AjouterPromotion(ActionEvent event) {
    }

        
}
