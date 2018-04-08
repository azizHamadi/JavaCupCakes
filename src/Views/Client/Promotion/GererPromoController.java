/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Promotion;

import Views.Client.ListAllPromotions.ListAllProduitController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author hamdi fathallah
 */
public class GererPromoController implements Initializable {

    @FXML
    private VBox nav_bar;
    @FXML
    private Button btnListePromoProd;
    @FXML
    private Button btnListePromoSes;
    @FXML
    private VBox body;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void promoproduit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/ListAllPromotions/ListAllProduit.fxml"));
        Node root = loader.load();
        ListAllProduitController Clar = loader.getController();
        body.getChildren().clear();
        body.getChildren().add(Clar.getBody());
    }

    @FXML
    private void promosession(ActionEvent event) {
    }
    
}