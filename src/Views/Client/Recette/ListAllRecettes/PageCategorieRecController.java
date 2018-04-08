/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Recette.ListAllRecettes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class PageCategorieRecController implements Initializable {

    @FXML
    private HBox section_bodyCategorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public HBox getSection_bodyCategorie() {
        return section_bodyCategorie;
    }

    public void setSection_bodyCategorie(Node section_bodyCategorie) {
        this.section_bodyCategorie.getChildren().add(section_bodyCategorie);
    }

    
}
