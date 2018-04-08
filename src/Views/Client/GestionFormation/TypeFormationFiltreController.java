/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.GestionFormation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author FERIEL FADHLOUN
 */
public class TypeFormationFiltreController implements Initializable {

    @FXML
    private Text nomTypeFor;
    @FXML
    private Text afficherListeFor;
    @FXML
    private Text NbrTypeFor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public Text getNbrTypeFor() {
        return NbrTypeFor;
    }

    public void setNbrTypeFor(String NbrTypeFor) {
       this.NbrTypeFor.setText(NbrTypeFor);
    }

    
    public Text getNomTypeFor() {
        return nomTypeFor;
        // TODO
    }    

    public void setNomTypeFor(String nomTypeFor) {
         this.nomTypeFor.setText(nomTypeFor);
    }

    public Text getAfficherListeFor() {
        return afficherListeFor;
    }

    /**
     * Initializes the controller class.
     */
    public void setAfficherListeFor(Text afficherListeFor) {    
        this.afficherListeFor = afficherListeFor;
    }

   
    
}
