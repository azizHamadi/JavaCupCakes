/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.jcpa;

import Entity.CategorieRec;
import Services.CategorieRecetteService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author escobar
 */
public class affichagePane implements Initializable {

  
    @FXML
    private VBox vb;
    @FXML
    private HBox hbo;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            CategorieRecetteService crs = new CategorieRecetteService();
            List<CategorieRec> listeCatR = crs.AfficherCategorieRecette();
            Node [] nodes = new Node[listeCatR.size()];
            int i = 0;
            for (CategorieRec c : listeCatR){
                if (i == 3 )
                    return ;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Kaaboura.fxml"));
                nodes[i] = loader.load() ;
                KaabouraController c1 =loader.getController();
                c1.setLab(c.getNomCatRec());
                hbo.getChildren().add(nodes[i]);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(affichagePane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(affichagePane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
    
}    

