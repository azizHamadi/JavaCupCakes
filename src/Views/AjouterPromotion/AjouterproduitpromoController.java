/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.AjouterPromotion;

import Entity.LinePromo;
import Entity.Produit;
import Entity.Promotion;
import Services.LinePromoService;
import Services.ProduitService;
import Services.PromotionService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author hamdi fathallah
 */
public class AjouterproduitpromoController implements Initializable {

    @FXML
    private JFXComboBox<String> produit;
    @FXML
    private JFXComboBox<Double> promo;
    @FXML
    private JFXDatePicker datedeb;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXButton valider;
    @FXML
    private Label prodtext;
    @FXML
    private Label promotxt;
    @FXML
    private Label datedebtext;
    @FXML
    private Label datefintext;
    @FXML
    private Label dateacttxt;
    @FXML
    private Label compartxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            promo.getItems().clear();
            PromotionService promos = new PromotionService();
            List<Promotion> listpromo = promos.AfficherPromotion();
            for (Promotion c : listpromo) {
                promo.getItems().add(c.getTauxPromo());
            }
            ProduitService prod = new ProduitService();
            List<Produit> listprod = prod.AfficherNomProduit();
            for (Produit c : listprod) {
                produit.getItems().add(c.getNomProd());
            }
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AjouterpromoproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void validerpromo(ActionEvent event) throws SQLException, MessagingException {
        if (ValidateFields()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention!");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs!");
            alert.showAndWait();
            return;
        } else {
            LinePromoService fs = new LinePromoService();
            PromotionService ps = new PromotionService();
            ProduitService pk = new ProduitService();
            Promotion p = ps.RecherchePromotion(promo.getValue());
            System.out.println(p);
            ProduitService ks = new ProduitService();
            Produit pr = ks.RechercheProduit(produit.getValue());
            System.out.println(pr);
            LinePromo pf = new LinePromo();
            //if (comparedate(pf)== true) {
            LinePromo f = new LinePromo(java.sql.Date.valueOf(datedeb.getValue()), java.sql.Date.valueOf(datefin.getValue()), p, pr);
            if (comparedate(f)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ajout impossible!");
                alert.setHeaderText(null);
                alert.setContentText("le produit est deja sous promotion !");
                alert.showAndWait();
            } else {
                fs.AjouterLinePromo(f);
                fs.calculepromo(pr, p);
                ClearFields();

                return;
            }
            combobox();
        }
    }

    public void combobox() throws SQLException {
        PromotionService promos = new PromotionService();
        List<Promotion> listpromo = promos.AfficherPromotion();
        for (Promotion c : listpromo) {
            promo.getItems().add(c.getTauxPromo());
        }
        ProduitService prod = new ProduitService();
        List<Produit> listprod = prod.AfficherNomProduit();
        for (Produit c : listprod) {
            produit.getItems().add(c.getNomProd());
        }
    }

    public void ClearFields() {
        produit.getItems().clear();
        datedeb.getEditor().setText(null);
        datefin.getEditor().setText(null);
        promo.getItems().clear();

    }

    public boolean comparedate(LinePromo p) throws SQLException {
        LinePromoService pk = new LinePromoService();
        List<LinePromo> listProd = pk.RecherchePromoProduit(p.getIdProd().getIdProd());
        for (LinePromo lp : listProd) {
            System.out.println("line = " + lp);
            if (!p.getDateDeb().after(lp.getDateFin())) {
                return true;
            }
        }
        return false;
    }

    public boolean ValidateFields() {
        System.out.println("date debut mareja" + datedeb.getEditor().getText());
        System.out.println("date actuelle mareja" + LocalDate.now());
        System.out.println("date fin mareja" + datefin.getValue());

        int comboprod = 0, combopromo = 0, datedeb = 0, datefin = 0;

        if (produit.getValue() == null) {
            comboprod = 1;
            prodtext.setVisible(true);
        } else {
            prodtext.setVisible(false);
        }

        //tester la date de debut
        if (this.datedeb.getEditor().getText().length() == 0) {
            datedeb = 1;
            datedebtext.setVisible(true);
            dateacttxt.setVisible(false);

        } else if ((this.datedeb.getValue().isBefore(LocalDate.now()))) {
            datedeb = 1;
            dateacttxt.setVisible(true);
            datedebtext.setVisible(false);
        } else {
            datedebtext.setVisible(false);
            dateacttxt.setVisible(false);
            System.out.println("date deb" + this.datedeb.getValue());
            System.out.println("date fin" + this.datefin.getValue());
            System.out.println(LocalDate.now());
        }
        //tester la date de fin
        if (this.datefin.getEditor().getText().length() == 0) {
            datefin = 1;
            datefintext.setVisible(true);
            compartxt.setVisible(false);

        } else if ((this.datefin.getValue().isBefore(this.datedeb.getValue()))) {
            datefin = 1;
            compartxt.setVisible(true);
            datefintext.setVisible(false);
        } else {
            datefintext.setVisible(false);
            compartxt.setVisible(false);
            System.out.println("date deb" + this.datedeb.getValue());
            System.out.println("date fin" + this.datefin.getValue());
            System.out.println("date actuelle" + LocalDate.now());
        }
        if (promo.getValue() == null) {
            combopromo = 1;
            promotxt.setVisible(true);
        } else {
            promotxt.setVisible(false);
        }

        return (comboprod == 1 || datedeb == 1 || datefin == 1 || combopromo == 1);
    }

    @FXML
    private void AjouterProurcentage(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("taux de promotion");
        dialog.setTitle("pourcentage des promotions");
        dialog.setHeaderText("Ajouter une pourcentage");
        dialog.setContentText("taux de promotion:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(taux -> {
            try {
                PromotionService crs = new PromotionService();
                if (crs.RecherchePromotion((Double.parseDouble(taux))) == null) {
                    crs.AjouterPromotion(new Promotion(Double.parseDouble(taux)));
                    this.initialize(null, null);
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Erreur!");
                    alert.setContentText("pourcentage existe deja!!!");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ajouterproduitpromo.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
