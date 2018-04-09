/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Produit.StatVue;

import Views.Patisserie.Produit.StatVente.*;
import Services.ProduitService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import Entity.Produit;
import java.util.List;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class StateVueController implements Initializable {

    @FXML
    private BarChart<String, Integer> barchar;
    @FXML
    private CategoryAxis xAxis;
    private ObservableList<String> nomp = FXCollections.observableArrayList();
    @FXML
    private NumberAxis yAxis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            ProduitService ps = new ProduitService();
            ObservableList<String> nomp = FXCollections.observableArrayList(ps.AfficherNomP());
            xAxis.setCategories(nomp);
            xAxis.setLabel("Nom Produit");
            yAxis.setLabel("Vue");
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            List<Produit> produit = ps.AfficherNomProduit();
            int[] pp = new int[12];
            for (Produit p : produit) {
                int month = p.getValeur();
                           series.getData().add(new XYChart.Data<>(p.getNomProd(), p.getValeur()));

            }
                                barchar.getData().add(series);

        } catch (SQLException ex) {
            Logger.getLogger(StateVueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
