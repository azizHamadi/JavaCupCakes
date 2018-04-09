/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Commande.listeCommande;

import Views.Client.Produit.ListAllProduit.*;
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
public class CommandeMouraba3Controller implements Initializable {

  
    @FXML
    private Text afficherProduit;
    @FXML
    private Text DateCmd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Text getAfficherProduit() {
        return afficherProduit;
    }

    public void setAfficherProduit(String afficherProduit) {
        this.afficherProduit.setText(afficherProduit);
    }

    public Text getDateCmd() {
        return DateCmd;
    }

    public void setDateCmd(String DateCmd) {
        this.DateCmd.setText(DateCmd);
    }

    
}
