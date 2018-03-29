/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Produit.ListAllProduit;

import Views.Client.Recette.ListAllRecettes.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class CategorieProdFiltreController implements Initializable {

    @FXML
    private Text nomCat;
    
    private Text NbrRec;
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

    public Text getNbrRec() {
        return NbrRec;
    }

    public void setNbrRec(String NbrRec) {
        this.NbrRec.setText(NbrRec);
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
