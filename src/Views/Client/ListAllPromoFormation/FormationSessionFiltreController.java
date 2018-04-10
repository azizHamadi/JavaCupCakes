/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.ListAllPromoFormation;

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
public class FormationSessionFiltreController implements Initializable {

    @FXML
    private Text nomFor;
    @FXML
    private Text affichersession;
    @FXML
    private Text NbrSes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Text getNomFor() {
        return nomFor;
    }

    public void setNomFor(String nomFor) {
        this.nomFor.setText(nomFor);
    }

    public Text getAffichersession() {
        return affichersession;
    }

    public void setAffichersession(String affichersession) {
        this.affichersession.setText(affichersession);
    }

    public Text getNbrSes() {
        return NbrSes;
    }

    public void setNbrSes(String NbrSes) {
        this.NbrSes.setText(NbrSes);
    }
    
}
