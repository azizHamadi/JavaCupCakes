/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Recette;


import Views.Patisserie.Recette.ListAllRecettes.ListAllRecettesController;
import Views.Patisserie.Recette.MesRecettes.MesRecettesController;
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
 * @author aziz
 */
public class GererRecetteController implements Initializable {

    @FXML
    private VBox nav_bar;
    @FXML
    private VBox body;
    @FXML
    private Button btnAjouterProduit;
    @FXML
    private Button ListeCategoriebtn;
    @FXML
    private Button btnListeProduit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void ListeCategorie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Patisserie/Recette/MesRecettes/MesRecettes.fxml"));
        Node root = loader.load();
        MesRecettesController Clar = loader.getController();
        body.getChildren().clear();
        body.getChildren().add(Clar.getBody());
    }

    @FXML
    private void ListeRecette(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Patisserie/Recette/ListAllRecettes/ListAllRecettes.fxml"));
        Node root = loader.load();
        ListAllRecettesController Clar = loader.getController();
        body.getChildren().clear();
        body.getChildren().add(Clar.getBody());
    }

    @FXML
    private void AjouterRecette(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterRecette/AjouterRecettePatisserie.fxml"));
        Node root = loader.load();
        body.getChildren().clear();
        body.getChildren().add(root);
    }

        
}
