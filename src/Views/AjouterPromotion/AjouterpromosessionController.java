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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author hamdi fathallah
 */
public class AjouterpromosessionController implements Initializable {


    @FXML
    private TableView<Linepromoses> tableses;
    @FXML
    private TableColumn<Linepromoses, String> promoses;
    @FXML
    private TableColumn<Linepromoses, String> tauxpromo;
 
    @FXML
    private TableColumn<Linepromoses, DatePicker> caldatedeb;
    @FXML
    private TableColumn<Linepromoses, DatePicker> caldatefin;
    @FXML
    private JFXComboBox<Double> pourcentage;
    @FXML
    private JFXComboBox<String> session;
    @FXML
    private JFXDatePicker datedeb;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton update;
    @FXML
    private Label prodtext;
    @FXML
    private Label datedebtext;
    @FXML
    private Label datefintext;
    @FXML
    private Label promotxt;
    @FXML
    private Label compartxt;
    @FXML
    private Label dateacttxt;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
            PromotionService promos = new PromotionService();
            List<Promotion> listpromo = promos.AfficherPromotion();
            for(Promotion c : listpromo)
            {
                pourcentage.getItems().add(c.getTauxPromo());
            }
            SessionService sess = new SessionService();
            List<Session> listprod = sess.ListeSessions();
            for(Session c : listprod)
            {
                session.getItems().add(c.getNomSes());
            }
            LinePromoSesService lineses = new LinePromoSesService();
            promoses.setCellValueFactory(new Callback<CellDataFeatures<Linepromoses, String>, ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(CellDataFeatures<Linepromoses,String> var){
                    return new SimpleStringProperty(var.getValue().getIdSes().getNomSes());
                } 
            });         
            tauxpromo.setCellValueFactory(new Callback<CellDataFeatures<Linepromoses, String>, ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(CellDataFeatures<Linepromoses,String> var){
                    return new SimpleStringProperty(var.getValue().getIdPromo().getTauxPromo().toString());
                } 
            });

            tableses.setItems(lineses.getListelinepromoses());
            RefreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterpromoproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // TODO
    }    

    @FXML
    private void ajoutses(ActionEvent event) throws SQLException {
        if (ValidateFields())
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Attention!");
                    alert.setHeaderText(null);
                    alert.setContentText("veuillez remplir tous les champs!");
                    alert.showAndWait();
                   return;
                }
         else {
         LinePromoSesService fs = new LinePromoSesService();
         PromotionService ps = new PromotionService();
         Promotion p = ps.RecherchePromotion(pourcentage.getValue());
         SessionService ks = new SessionService();
         Session pr = ks.RechercheSession(session.getValue());
         Linepromoses f = new Linepromoses(java.sql.Date.valueOf(datedeb.getValue()), java.sql.Date.valueOf(datefin.getValue()), pr, p) ;
         fs.AjouterLinePromoSes(f);
         RefreshTable();
         ClearFields();
    }
    }

 public void RefreshTable() throws SQLException
   {    tableses.getItems().clear();
        LinePromoSesService service=new LinePromoSesService();
        caldatedeb.setCellValueFactory(new PropertyValueFactory<>("dateDeb"));
        caldatefin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        tableses.setItems(service.getListelinepromoses());
        
   }

     public void ClearFields()
    {
        session.getItems().clear();
        datedeb.getEditor().setText(null);
        datefin.getEditor().setText(null);
        pourcentage.getItems().clear();

    }
     
     public void combobox() throws SQLException{
          PromotionService promos = new PromotionService();
            List<Promotion> listpromo = promos.AfficherPromotion();
            for(Promotion c : listpromo)
            {
                pourcentage.getItems().add(c.getTauxPromo());
            }
            SessionService sess = new SessionService();
            List<Session> listprod = sess.ListeSessions();
            for(Session c : listprod)
            {
                session.getItems().add(c.getNomSes());
            }
     }

    @FXML
    private void FetcherTable(MouseEvent event) throws SQLException {
         LinePromoSesService service=new LinePromoSesService();
        session.setValue(tableses.getSelectionModel().getSelectedItem().getIdSes().getNomSes());
        pourcentage.setValue(tableses.getSelectionModel().getSelectedItem().getIdPromo().getTauxPromo()); 
      //date deb 
        String datesessiondebut=tableses.getSelectionModel().getSelectedItem().toString();    
        datedeb.setValue(LocalDate.parse(tableses.getSelectionModel().getSelectedItem().getDateDeb().toString()));
        System.out.println("date mel fetch"+datedeb.getValue());
        //date fin
        String datesessionfin=tableses.getSelectionModel().getSelectedItem().getDateFin().toString();       
        datefin.setValue(LocalDate.parse( tableses.getSelectionModel().getSelectedItem().getDateFin().toString()));
        System.out.println("date mel fetch"+datedeb.getValue());
        
    }

    @FXML
    private void modifier(ActionEvent event) {
         LinePromoSesService service=new LinePromoSesService();
        try {
            // if(validateCapacite()&&validatePrixSession())
               // {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Modification promo");
                    alert.setHeaderText(null);
                    alert.setContentText("Voulez vous vraiment modifier cette promo!!");
                    alert.showAndWait();
                    Optional<ButtonType> result = alert.showAndWait();
                        
                    if (result.get() == ButtonType.OK){
                      PromotionService ps = new PromotionService();
                        Promotion p = ps.RecherchePromotion(pourcentage.getValue());
                        SessionService ks = new SessionService();
                        Session pr = ks.RechercheSession(session.getValue());
                        
                        //date yafficheha bel - w howa el picker yhotha bel /
                        System.out.println("date khra"+datedeb.getEditor().getText());
                        Linepromoses s=new Linepromoses(java.sql.Date.valueOf(datedeb.getValue()), java.sql.Date.valueOf(datefin.getValue()),pr,p) ;
                        service.ModifierLinePromoSes(s,tableses.getSelectionModel().getSelectedItem().getIdLine());
                        RefreshTable();
                        ClearFields();
                        combobox();
                    } else {
                        alert.close();
                        // ... user chose CANCEL or closed the dialog
                    }
                    RefreshTable();
             //   }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterpromoproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public boolean ValidateFields() {
        System.out.println("date debut mareja" + datedeb.getEditor().getText());
        System.out.println("date actuelle mareja" + LocalDate.now());
        System.out.println("date fin mareja" + datefin.getValue());

        int comboprod = 0, combopromo = 0, datedeb = 0, datefin = 0;

        if (session.getValue() == null) {
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
        if (pourcentage.getValue() == null) {
            combopromo = 1;
            promotxt.setVisible(true);
        } else {
            promotxt.setVisible(false);
        }

        return (comboprod == 1 || datedeb == 1 || datefin == 1 || combopromo == 1);
    }

}


