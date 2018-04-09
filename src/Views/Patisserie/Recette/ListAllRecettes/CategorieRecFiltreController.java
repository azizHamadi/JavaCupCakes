/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Recette.ListAllRecettes;

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
public class CategorieRecFiltreController implements Initializable {

    @FXML
    private Text nomCat;
    @FXML
    private Text afficherRec;
    @FXML
    private Text NbrRec;

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

    public Text getAfficherRec() {
        return afficherRec;
    }

    public void setAfficherRec(Text afficherRec) {
        this.afficherRec = afficherRec;
    }

    public Text getNbrRec() {
        return NbrRec;
    }

    public void setNbrRec(String NbrRec) {
        this.NbrRec.setText(NbrRec);
    }
    
}
