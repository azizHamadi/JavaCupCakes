/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Recette;

import Views.Client.Recette.AjouterRecette.AjouterRecetteClientController;
import Views.Client.Recette.ListAllRecettes.ListAllRecettesController;
import Views.Client.Recette.MesRecettes.MesRecettesController;
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
public class GererRecetteController implements Initializable {

    @FXML
    private VBox nav_bar;
    @FXML
    private Button btnListeRecettes;
    @FXML
    private Button btn;
    @FXML
    private VBox body;
    @FXML
    private Button btn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ListeRecettes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/Recette/ListAllRecettes/ListAllRecettes.fxml"));
        Node root = loader.load();
        ListAllRecettesController Clar = loader.getController();
        body.getChildren().clear();
        body.getChildren().add(Clar.getBody());
    }

    @FXML
    private void AjouterRecette(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/Recette/AjouterRecette/AjouterRecetteClient.fxml"));
        Node root = loader.load();
        AjouterRecetteClientController ac = loader.getController();
        body.getChildren().clear();
        body.getChildren().add(root);

    }

    @FXML
    private void MesRecettes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/Recette/MesRecettes/MesRecettes.fxml"));
        Node root = loader.load();
        MesRecettesController Clar = loader.getController();
        body.getChildren().clear();
        body.getChildren().add(Clar.getBody());

    }

        
}
