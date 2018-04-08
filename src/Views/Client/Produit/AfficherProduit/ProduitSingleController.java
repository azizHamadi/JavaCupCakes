/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Produit.AfficherProduit;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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

    public void setImage(String image) {
       File file = new File("C:/wamp64/www/final/web/public/uploads/brochures/Produit/" + image);
        this.image.setImage(new Image(file.toURI().toString()));
    }

    public void setNomProd(String nomProd) {
        this.nomProd.setText(nomProd);    }

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

    /**
     * Initializes the controller class.
     */
    public void setHb(HBox hb) {
        this.hb = hb;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
        }    
    

