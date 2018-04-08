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
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author FERIEL FADHLOUN
 */
public class Page2FormationController implements Initializable {

    @FXML
    private VBox page2Ligne;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public VBox getPage2Ligne() {
        return page2Ligne;
    }

    public void setPage2Ligne(Node page2Ligne) {
        this.page2Ligne.getChildren().add(page2Ligne);
    }
}
