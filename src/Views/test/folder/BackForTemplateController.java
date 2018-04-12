/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.test.folder;

import Views.AjouterPromotion.AjoutpromoformationController;
import Views.Formateur.GestionFormations.CRUDFormationsController;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class BackForTemplateController implements Initializable {

    @FXML
    private HBox body;
    @FXML
    private VBox nav_bar;
    @FXML
    private VBox vbox_body;

    public VBox getVbox_body() {
        return vbox_body;
    }

    public void setVbox_body(VBox vbox_body) {
        this.vbox_body = vbox_body;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    @FXML
    private void GestionFormation(ActionEvent event) throws IOException {
        
        System.out.println("gestion formations");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../Views/Formateur/GestionFormations/CRUDFormations.fxml"));
        Node root = loader.load();
        CRUDFormationsController cfc = loader.getController();
        cfc.setVbox(vbox_body);
        vbox_body.getChildren().clear();
        vbox_body.getChildren().add(root);
    }

    @FXML
    private void GestionSession(ActionEvent event) throws IOException {
        System.out.println("gestion sessions");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Formateur/GestionSession/CRUDSessions.fxml"));
        Node root = loader.load();
        vbox_body.getChildren().clear();
        vbox_body.getChildren().add(root);
    }

    @FXML
    private void GestionPromotion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../AjouterPromotion/ajouterpromosession.fxml"));
        Node root = loader.load();
        vbox_body.getChildren().clear();
        vbox_body.getChildren().add(root);
    }

    @FXML
    private void GestionRecette(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Patisserie/Recette/ListAllRecettes/ListAllRecettes.fxml"));
        Node root = loader.load();
        ListAllRecettesController Clar = loader.getController();
        vbox_body.getChildren().clear();
        vbox_body.getChildren().add(Clar.getBody());

    }

    @FXML
    private void AjouterRecette(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Patisserie/Recette/AjouterRecette/AjouterRecettePatisserie.fxml"));
        Node root = loader.load();
        vbox_body.getChildren().clear();
        vbox_body.getChildren().add(root);

    }

    @FXML
    private void MesRecette(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Patisserie/Recette/MesRecettes/MesRecettes.fxml"));
        Node root = loader.load();
        MesRecettesController Clar = loader.getController();
        vbox_body.getChildren().clear();
        vbox_body.getChildren().add(Clar.getBody());

    }

    @FXML
    private void AjouterPromo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../AjouterPromotion/ajoutsessionpromo.fxml"));
        Node root = loader.load();
        vbox_body.getChildren().clear();
        vbox_body.getChildren().add(root);
    }

    @FXML
    private void AjouterPromoFor(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../AjouterPromotion/ajoutpromoformation.fxml"));
        Node root = loader.load();
        vbox_body.getChildren().clear();
        vbox_body.getChildren().add(root);
    }

 
    
}
