/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Panier.ConfirmerAdre;

import Entity.Commande;
import Entity.LineCmd;
import Views.Client.Produit.AfficherProduit.*;
import Entity.Produit;
import Services.CommandeService;
import Services.PanierService;
import Services.ProduitService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class CommandeController implements Initializable {

    @FXML
    private JFXDatePicker dateliv;
    @FXML
    private JFXTextField Adrliv;
    @FXML
    private AnchorPane body;
    @FXML
    private Text dateCmd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public boolean Controle() {
        int date = 0;
        if ((this.dateliv.getValue().isBefore(LocalDate.now()))) {
            date = 1;
            dateCmd.setVisible(true);
        } else {
            dateCmd.setVisible(false);
            System.out.println("date deb" + this.dateliv.getValue());
            System.out.println(LocalDate.now());
        }
        return (date == 1);
    }

    @FXML
    private void Confirmer(ActionEvent event) throws SQLException, IOException {
        if (Controle()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Date de livraison invalide");
            alert.show();

        } else {
            PanierService panier = PanierService.getInstance();
            Map<Produit, Integer> ps = panier.Afficher();
            CommandeService cs = new CommandeService();
            ProduitService prods = new ProduitService();

            Date date = java.sql.Date.valueOf(dateliv.getValue());
            String adr = Adrliv.getText();
            Commande c = new Commande(date, Double.valueOf(panier.calculerPanier()), date, adr, "en cour", "vrai");
            int s = cs.AjouterCommande(c);

            System.out.println("id commande = " + s);
            for (Map.Entry<Produit, Integer> p : ps.entrySet()) {
                LineCmd line = new LineCmd(p.getValue(), p.getKey());
                cs.AjouterLine(line, s);
                //Gestion Stock
                System.out.println(p.getKey().getQteStockProd());
                System.out.println(p.getValue());
                Double qte = p.getKey().getQteStockProd() - p.getValue();
                int acht = p.getKey().getQteAcheter() + p.getValue();
                System.out.println(acht);
                Produit pod = new Produit(p.getKey().getIdProd(), qte, acht);
                System.out.println(qte);
                prods.UpdateProduitStock(pod);

            }
            panier.closePanier();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("Commande enregistrer avec succés!");
            alert.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../test/folder/ClientTemplate.FXML"));
            Parent root = loader.load();
            body.getScene().setRoot(root);

        }
    }
}
