/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Recette.SingleRecette;

import Entity.Recette;
import Services.RecetteService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class SingleRecetteController implements Initializable {

    @FXML
    private WebView description;
    @FXML
    private HTMLEditor ckeditor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            RecetteService rs = new RecetteService();
            List<Recette> listR = rs.AfficherRecette();
            WebEngine webEngine = description.getEngine();
            webEngine.loadContent(listR.get(1).getDescriptionRec());
        } catch (SQLException ex) {
            Logger.getLogger(SingleRecetteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void afficher(KeyEvent event) {
        description.getEngine().loadContent(ckeditor.getHtmlText());
    }
    
}
