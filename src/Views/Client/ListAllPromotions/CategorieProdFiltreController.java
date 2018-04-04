/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.ListAllPromotions;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author hamdi fathallah
 */
public class CategorieProdFiltreController implements Initializable {

    @FXML
    private Text nomCat;
    @FXML
    private Text afficherProduit;
    @FXML
    private Text NbrProd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    public Text getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat.setText(nomCat);
    }

    public Text getAfficherProduit() {
        return afficherProduit;
    }

    public void setAfficherProduit(String afficherProduit) {
        this.afficherProduit.setText(afficherProduit);
    }

    public Text getNbrProd() {
        return NbrProd;
    }

    public void setNbrProd(String NbrProd) {
        this.NbrProd.setText(NbrProd);
    }
    
}
