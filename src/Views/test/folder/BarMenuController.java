/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.test.folder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
public class BarMenuController implements Initializable {

    @FXML
    private Button btnListeRecettes;
    @FXML
    private Button btn;
    private VBox vbo ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ListeRecettes(ActionEvent event) {
        
    }

    @FXML
    private void AjouterRecette(ActionEvent event) throws IOException {
        vbo.getChildren().clear();
        FXMLLoader loaderNavBar = new FXMLLoader(getClass().getResource("../../Client/Recette/AjouterRecette/AjouterRecetteClient.fxml"));
        vbo.getChildren().add(loaderNavBar.load());

    }

    public VBox getHbo() {
        return vbo;
    }

    public void setVbo(VBox vbo) {
        this.vbo = vbo;
    }
    
    
}
