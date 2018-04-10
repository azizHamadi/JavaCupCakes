/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.AjouterPromotion;

import Entity.Linepromoses;
import Entity.Promotion;
import Entity.Session;
import Services.LinePromoSesService;
import Services.PromotionService;
import Services.SessionService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextInputDialog;
import javafx.util.Callback;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author hamdi fathallah
 */
public class AjoutsessionpromoController implements Initializable {

    @FXML
    private JFXComboBox<String> session;
    @FXML
    private JFXComboBox<Double> promo;
    @FXML
    private JFXDatePicker datedeb;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXButton valider;
    @FXML
    private Label sesstext;
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
            SessionService sess = new SessionService();
            List<Session> listprod = sess.ListeSessions();
            for (Session c : listprod) {
                session.getItems().add(c.getNomSes());
            }

        } catch (SQLException ex) {
            Logger.getLogger(AjoutsessionpromoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
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
            LinePromoSesService fs = new LinePromoSesService();
            PromotionService ps = new PromotionService();
            Promotion p = ps.RecherchePromotion(promo.getValue());
            SessionService ks = new SessionService();
            Session pr = ks.RechercheSession(session.getValue());
            Linepromoses f = new Linepromoses(java.sql.Date.valueOf(datedeb.getValue()), java.sql.Date.valueOf(datefin.getValue()), pr, p);
               if (comparedate(f)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ajout impossible!");
                    alert.setHeaderText(null);
                    alert.setContentText("la session est deja sous promotion !");
                    alert.showAndWait();

                } else {
                fs.AjouterLinePromoSes(f);
                System.out.println("zadha");
                fs.calculepromo(pr, p);
                System.out.println("7sebhaaa");
                ClearFields();
                
            return;

            }
            combobox();

        }
    }

    public void ClearFields() {
        session.getItems().clear();
        datedeb.getEditor().setText(null);
        datefin.getEditor().setText(null);
        promo.getItems().clear();

    }
    
     public void combobox() throws SQLException {
       PromotionService promos = new PromotionService();
            List<Promotion> listpromo = promos.AfficherPromotion();
            for (Promotion c : listpromo) {
                promo.getItems().add(c.getTauxPromo());
            }
            SessionService sess = new SessionService();
            List<Session> listprod = sess.ListeSessions();
            for (Session c : listprod) {
                session.getItems().add(c.getNomSes());
            }

      }

    public boolean comparedate(Linepromoses p) throws SQLException {
        LinePromoSesService pk = new LinePromoSesService();
        List<Linepromoses> listses = pk.RecherchePromosession(p.getIdSes().getIdSes());
        for (Linepromoses lp : listses) {
            System.out.println("line = " + lp.getDateFin().toString() + " deb = " + p.getDateDeb().toString());
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

        if (session.getValue() == null) {
            comboprod = 1;
            sesstext.setVisible(true);
        } else {
            sesstext.setVisible(false);
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
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Erreur!");
                    alert.setContentText("pourcentage existe deja!!!");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ajoutsessionpromo.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
