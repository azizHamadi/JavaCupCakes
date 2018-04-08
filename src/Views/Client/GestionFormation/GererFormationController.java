/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.GestionFormation;

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
 * @author FERIEL FADHLOUN
 */
public class GererFormationController implements Initializable {

    @FXML
    private VBox nav_bar;
    @FXML
    private Button btnListeFormations;
    @FXML
    private Button btnMesSessionsFinies;
    @FXML
    private Button btnMesSessionsEnCours;
    @FXML
    private VBox body;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

     @FXML
    private void ListeFormations(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAllFormations.fxml"));
        Node root = loader.load();
        body.getChildren().clear();
        body.getChildren().add(root);
    }

   

    @FXML
    private void MesSessionsFinies(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MesSessionsFinies/MesSessionsFinies.fxml"));
        Node root = loader.load();
        body.getChildren().clear();
        body.getChildren().add(root);
    }

    @FXML
    private void MesSessionsEnCours(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MesSessionsEnCours/MesSessionsEnCours.fxml"));
        Node root = loader.load();
        body.getChildren().clear();
        body.getChildren().add(root);
    }
}
