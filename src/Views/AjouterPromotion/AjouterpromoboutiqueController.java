/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.AjouterPromotion;

import Entity.LinePromo;
import Entity.Produit;
import Entity.Promotion;
import Services.LinePromoService;
import Services.ProduitService;
import Services.PromotionService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author hamdi fathallah
 */
public class AjouterpromoboutiqueController implements Initializable {

    @FXML
    private JFXComboBox<Double> promo;
    @FXML
    private JFXButton btnajout;
    @FXML
    private JFXDatePicker datedeb;
    @FXML
    private JFXDatePicker datefin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PromotionService promos = new PromotionService();
        try {
            List<Promotion> listpromo = promos.AfficherPromotion();
             for(Promotion c : listpromo)
            {
               promo.getItems().add(c.getTauxPromo());
            }
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AjouterpromoboutiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouterpromo(ActionEvent event) throws SQLException {
        
         LinePromoService fs = new LinePromoService();
         PromotionService ps = new PromotionService();
         Promotion p = ps.RecherchePromotion(promo.getValue());
         System.out.println(promo.getValue()); 
         Produit pr = new Produit ();
         LinePromo f = new LinePromo(java.sql.Date.valueOf(datedeb.getValue()), java.sql.Date.valueOf(datefin.getValue()),p, pr);
         fs.calculerpromoboutique(p);
        
    }
    
}
