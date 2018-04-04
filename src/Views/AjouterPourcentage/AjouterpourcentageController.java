/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.AjouterPourcentage;

import Entity.Promotion;
import Services.PromotionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hamdi fathallah
 */
public class AjouterpourcentageController implements Initializable {

    @FXML
    private TextField pourcentage;
    @FXML
    private Button envpour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutpromo(ActionEvent event) throws SQLException, IOException {
       PromotionService fs = new PromotionService();
       Promotion f = new Promotion(Double.parseDouble(pourcentage.getText())) ;
       fs.AjouterPromotion(f);
       FXMLLoader loader = new FXMLLoader (getClass().getResource("ajouterpourcentage.fxml")) ;
       Parent root = loader.load() ; 
    }
    
}
