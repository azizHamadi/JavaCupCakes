/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.AjouterPromotion;

import Entity.Linepromoses;
import Entity.Promotion;
import Entity.Session;
import Services.LinePromoSesService;
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
public class AjoutpromoformationController implements Initializable {

    @FXML
    private JFXComboBox<Double> promo;
    @FXML
    private JFXDatePicker datedeb;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXButton ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PromotionService promos = new PromotionService();
        try {
            List<Promotion> listpromo = promos.AfficherPromotion();
            for (Promotion c : listpromo) {
                promo.getItems().add(c.getTauxPromo());
            }
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AjoutpromoformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void valider(ActionEvent event) throws SQLException {
        LinePromoSesService fs = new LinePromoSesService();
        PromotionService ps = new PromotionService();
        Promotion p = ps.RecherchePromotion(promo.getValue());
        System.out.println(promo.getValue());
        Session pr = new Session();
        Linepromoses f = new Linepromoses(java.sql.Date.valueOf(datedeb.getValue()), java.sql.Date.valueOf(datefin.getValue()), pr, p);
        fs.calculerpromoformation(p);

    }

}
