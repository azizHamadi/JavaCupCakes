/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Produit.AfficherProduit;

import Entity.Produit;
import Services.LinePromoService;
import Services.PanierService;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class ProduitSingleController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label nomProd;
    @FXML
    private Label categorie;
    @FXML
    private Label prix;
    @FXML
    private Label stock;
    private HBox hb ;
    @FXML
    private Button BtAjouter;
    @FXML
    private JFXTextField QteAcheter;
    private Produit prod ;
    @FXML
    private Text Promotion;
    @FXML
    private Label NV;
    @FXML
    private Label labPromo;
 
    
   public void Controlle() throws SQLException{
        LinePromoService line = new LinePromoService();
                     List<Integer> l =line.afficherProduit();
                     if(l.contains(prod.getIdProd())){
                          Promotion.setVisible(true);
                          NV.setVisible(true);
                          labPromo.setVisible(true);
                        }
                        else{
                          Promotion.setVisible(false);
                            NV.setVisible(false);
                            labPromo.setVisible(false);

                        }
                    
                     
    }
    public void setImage(String image) {
       File file = new File("C:/wamp64/www/final/web/public/uploads/brochures/Produit/" + image);
        this.image.setImage(new Image(file.toURI().toString()));
    }

    public void setNomProd(String nomProd) {
        this.nomProd.setText(nomProd);    
    }

    public void setCategorie(String categorie) {
        this.categorie.setText(categorie);
    }

    public void setPrix(String prix) {
        this.prix.setText(prix);
    }

    public void setStock(String stock) {
        this.stock.setText(stock);
    }


    public HBox getHb() {
        return hb;
    }    
   public Produit getProd() {
        return prod;
    }

    public void setProd(Produit prod) {
        this.prod = prod;
    }
    /**
     * Initializes the controller class.
     */
    public void setHb(HBox hb) {
        this.hb = hb;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //   Controle();
    }    

    public void Controle(){
          if(prod.getQteStockProd()>0){
           BtAjouter.setVisible(true);
            }
    }
    public JFXTextField getQteAcheter() {
        return QteAcheter;
    }

    public void setQteAcheter(JFXTextField QteAcheter) {
        this.QteAcheter = QteAcheter;
    }

    public Button getBtAjouter() {
        return BtAjouter;
    }

    public void setBtAjouter(Button BtAjouter) {
        this.BtAjouter = BtAjouter;
    }

    public Label getNV() {
        return NV;
    }

    public void setNV(String NV) {
        this.NV.setText(NV);
    }

 

    @FXML
    private void ajouterPanier(ActionEvent event) {
             PanierService panier =PanierService.getInstance();
       // System.out.println("tekhdem");
      // PanierService panier = new PanierService();
      
        if(QteAcheter.getText().isEmpty()){
             Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Preciser la quantité a acheter");
        alert1.show();
        } 
        else
        if(Integer.parseInt(QteAcheter.getText())> prod.getQteStockProd()){
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Quantité disponible est : "+prod.getQteStockProd().toString());
        alert1.show();
                }
        else{
                panier.ajouterArticle(prod, Integer.parseInt(QteAcheter.getText()));
                          
        System.out.println(panier.Afficher());
        }
        }
    }

   
        
    

