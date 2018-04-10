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
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.mail.MessagingException;


/**
 * FXML Controller class
 *
 * @author hamdi fathallah
 */
public class AjouterpromoproduitController implements Initializable {

  /*  @FXML
    private Button valider;
    @FXML
    private DatePicker datedeb;
    @FXML
    private DatePicker datefin;
    @FXML
    private ComboBox<String> produit;
    @FXML
    private ComboBox<Double> promo;*/
    @FXML
    private TableView<LinePromo> produitpromo;
    @FXML
    private TableColumn<LinePromo, String> nomProd;
    @FXML
    private TableColumn<LinePromo, String> tauxPromo;
   /* @FXML
    private Button modifier;*/
    @FXML
    private TableColumn<LinePromo, DatePicker> caldate;
    @FXML
    private TableColumn<LinePromo, DatePicker> caldatefin;
    /*@FXML
    private Button sup;*/
    @FXML
    private Label prodtext;
    @FXML
    private Label datedebtext;
    @FXML
    private Label datefintext;
    @FXML
    private Label compartxt;
    @FXML
    private Label promotxt;
    @FXML
    private Label dateacttxt;
    @FXML
    private JFXComboBox<String> produit;
    @FXML
    private JFXComboBox<Double> promo;
    @FXML
    private JFXDatePicker datedeb;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton sup;
    @FXML
    private JFXTextField search;

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
               promo.getItems().add(c.getTauxPromo());
            }
            ProduitService prod = new ProduitService();
            List<Produit> listprod = prod.AfficherNomProduit();
            for(Produit c : listprod)
            {
                produit.getItems().add(c.getNomProd());
            }
            LinePromoService lineS = new LinePromoService();
           
            
            nomProd.setCellValueFactory(new Callback<CellDataFeatures<LinePromo, String>, ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(CellDataFeatures<LinePromo,String> var){
                return new SimpleStringProperty(var.getValue().getIdProd().getNomProd());
                   }                                    
            });         
            
            
            tauxPromo.setCellValueFactory(new Callback<CellDataFeatures<LinePromo, String>, ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(CellDataFeatures<LinePromo,String> var){
                    return new SimpleStringProperty(var.getValue().getIdPromo().getTauxPromo().toString());
                } 
            });
         
            produitpromo.setItems(lineS.getListelinepromo());
            RefreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterpromoproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // TODO
        
        // TODO
    }    

    private void validerpromo(ActionEvent event) throws SQLException, MessagingException {
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
        //double plo =0 ;
        //int pmo = 0 ; 
         LinePromoService fs = new LinePromoService();
         PromotionService ps = new PromotionService();
         ProduitService pk = new ProduitService() ;
         //Promotion pp = new Promotion();
         //Produit po = new Produit();
         //plo = pp.getTauxPromo();
         //pmo = po.getPrixProd() ;
         Promotion p = ps.RecherchePromotion(promo.getValue());
         ProduitService ks = new ProduitService();
         Produit pr = ks.RechercheProduit(produit.getValue());
         System.out.println("jabhom sayeeee");
         //Promotion m = pr.RecherchetauxPromotion(plo);
         //Produit pm = ks.RechercheprixProduit(pmo);
         LinePromo f = new LinePromo(java.sql.Date.valueOf(datedeb.getValue()), java.sql.Date.valueOf(datefin.getValue()), p, pr) ;
         fs.AjouterLinePromo(f);
         System.out.println("zadhaaa cv!!!");
         fs.calculepromo(pr, p);
         System.out.println("7sebhaaaa cv!!!");       
         ClearFields(); 
         RefreshTable();

         }
       /*FXMLLoader loader = new FXMLLoader (getClass().getResource("ajouterpromo.fxml")) ;
       Parent root = loader.load() ;*/
    }

    
     public void RefreshTable() throws SQLException 
   
   {    produitpromo.getItems().clear();
        LinePromoService service=new LinePromoService();
        caldate.setCellValueFactory(new PropertyValueFactory<>("dateDeb"));
        caldatefin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        produitpromo.setItems(service.getListelinepromo());
        
   }
      public void ClearFields()
    {
        produit.getItems().clear();
        datedeb.getEditor().setText(null);
        datefin.getEditor().setText(null);
        promo.getItems().clear();

    }
      public void combobox() throws SQLException {
       PromotionService promos = new PromotionService();
            List<Promotion> listpromo = promos.AfficherPromotion();
            for(Promotion c : listpromo)
            {
               promo.getItems().add(c.getTauxPromo());
            }
            ProduitService prod = new ProduitService();
            List<Produit> listprod = prod.AfficherNomProduit();
            for(Produit c : listprod)
            {
                produit.getItems().add(c.getNomProd());
            }
      }

   @FXML
    private void FetcherTable(MouseEvent event) throws SQLException {
      LinePromoService service=new LinePromoService();
      produit.setValue(produitpromo.getSelectionModel().getSelectedItem().getIdProd().getNomProd());
      promo.setValue(produitpromo.getSelectionModel().getSelectedItem().getIdPromo().getTauxPromo()); 
      //date deb 
        String datesessiondebut=produitpromo.getSelectionModel().getSelectedItem().toString();    
        datedeb.setValue(LocalDate.parse(produitpromo.getSelectionModel().getSelectedItem().getDateDeb().toString()));
        System.out.println("date mel fetch"+datedeb.getValue());
        //date fin
        String datesessionfin=produitpromo.getSelectionModel().getSelectedItem().getDateFin().toString();       
        datefin.setValue(LocalDate.parse( produitpromo.getSelectionModel().getSelectedItem().getDateFin().toString()));
        System.out.println("date mel fetch"+datedeb.getValue());
        


    }

    @FXML
    private void modifierPromo(ActionEvent event) {
        LinePromoService service=new LinePromoService();
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
                        Promotion p = ps.RecherchePromotion(promo.getValue());
                        ProduitService ks = new ProduitService();
                        Produit pr = ks.RechercheProduit(produit.getValue());
                        
                        //date yafficheha bel - w howa el picker yhotha bel /
                        System.out.println("date khra"+datedeb.getEditor().getText());
                        LinePromo s=new LinePromo(java.sql.Date.valueOf(datedeb.getValue()), java.sql.Date.valueOf(datefin.getValue()), p, pr) ;
                        service.ModifierLinePromo(s,produitpromo.getSelectionModel().getSelectedItem().getId());
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
    public boolean ValidateFields()
    {
        System.out.println("date debut mareja"+datedeb.getEditor().getText());
        System.out.println("date actuelle mareja"+LocalDate.now());
        System.out.println("date fin mareja"+datefin.getValue());

       int comboprod = 0,combopromo = 0 , datedeb =0 , datefin = 0;
       
        if (produit.getValue() == null)
        {
            comboprod = 1 ;
            prodtext.setVisible(true);
        }
        else
            prodtext.setVisible(false);
        
       
        //tester la date de debut
        if(this.datedeb.getEditor().getText().length()==0 )
        {
            datedeb = 1 ;
            datedebtext.setVisible(true);
            dateacttxt.setVisible(false);
           
        }
        
        else if( (this.datedeb.getValue().isBefore(LocalDate.now())))
        {
            datedeb = 1 ;
            dateacttxt.setVisible(true);
            datedebtext.setVisible(false); 
        }
        else
        { 
            datedebtext.setVisible(false); 
            dateacttxt.setVisible(false);
            System.out.println("date deb"+this.datedeb.getValue());
            System.out.println("date fin"+this.datefin.getValue());
            System.out.println(LocalDate.now());
        }   
        //tester la date de fin
        if(this.datefin.getEditor().getText().length()==0 )
        {
            datefin = 1 ;
            datefintext.setVisible(true);
            compartxt.setVisible(false);
           
        }
        else if( (this.datefin.getValue().isBefore(this.datedeb.getValue())))
        {
            datefin = 1 ;
            compartxt.setVisible(true);
            datefintext.setVisible(false); 
        }
        else
        { 
            datefintext.setVisible(false); 
            compartxt.setVisible(false);
            System.out.println("date deb"+this.datedeb.getValue());
            System.out.println("date fin"+this.datefin.getValue());
            System.out.println("date actuelle"+LocalDate.now());
        }  
           if (promo.getValue() == null)
        {
            combopromo= 1 ;
            promotxt.setVisible(true);
        }
        else
            promotxt.setVisible(false);
        
        return (comboprod==1 || datedeb == 1||datefin == 1 || combopromo==1);
                }
     

    @FXML
    private void supprimer(ActionEvent event) {
      LinePromoService service=new LinePromoService();
        
        try {
            //date yekhou mel table or howa yelzm yekhou mel textfield
            
            
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Supprimer promo");
                    alert.setHeaderText(null);
                    alert.setContentText("Voulez vous vraiment supprimer cette promo!!");
                    alert.showAndWait();
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                       {
                            LinePromo s=new LinePromo();
                            service.SupprimerLinePromo(s,produitpromo.getSelectionModel().getSelectedItem().getId());
                            RefreshTable();
                            ClearFields();  
                       }
                    } else {
                        alert.close();
                        // ... user chose CANCEL or closed the dialog
                    }
                
        } catch (SQLException ex) {
            Logger.getLogger(AjouterpromoproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchcode(KeyEvent event) {
        LinePromoService service=new LinePromoService();
        try {
            produitpromo.setItems(service.SearchListePromo(search.getText()));
            System.out.println("rechercher");
        } catch (SQLException ex) {
            Logger.getLogger(AjouterpromoproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


    
    
   
        
       


    

