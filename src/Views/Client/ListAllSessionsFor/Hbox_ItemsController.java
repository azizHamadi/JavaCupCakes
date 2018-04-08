/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.ListAllSessionsFor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author FERIEL FADHLOUN
 */
public class Hbox_ItemsController implements Initializable {

    @FXML
    private HBox ligne_body;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       public void addColonne(Node ligne_body) {
        this.ligne_body.getChildren().add(ligne_body);
    }
}
