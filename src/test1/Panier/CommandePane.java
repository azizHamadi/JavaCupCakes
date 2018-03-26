/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1.Panier;

import Services.CommandeService;
import Entity.Commande;
import Entity.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author escobar
 */
public class CommandePane extends AnchorPane {
    @FXML
    private DatePicker DateLiv;
    @FXML
    private TextField AdrLiv;
    @FXML
    private Button validerLiv;

  
    public CommandePane() {
      
    }
    
    private void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void Valider(ActionEvent event) {
        CommandeService commande = new CommandeService();
       
        Date date = java.sql.Date.valueOf(DateLiv.getValue());
        String adr = AdrLiv.getText();
        Commande com = new Commande(date,(double)15.22,date,adr,"en cours","vrai");
        try {
            commande.AjouterCommande(com);
        } catch (SQLException ex) {
            Logger.getLogger(CommandePane.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
