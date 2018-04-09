/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Panier.AfficherPanier;

import Entity.Produit;
import Services.PanierService;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class PanierBodyController implements Initializable {

    @FXML
    private ImageView imageP;
    @FXML
    private Text PrixUni;
    @FXML
    private Text Qte;
    @FXML
    private Text total;
    @FXML
    private HBox bodyPan;
    private Produit prod;
    public ImageView getImageP() {
        return imageP;
    }

    public void setImageP(String imageP) {
        File file = new File("C:/wamp3/www/CupCakesF/web/public/uploads/brochures/Produit/" + imageP);
        this.imageP.setImage(new Image(file.toURI().toString()));
    }

    public Text getPrixUni() {
        return PrixUni;
    }

    public void setPrixUni(String PrixUni) {
        this.PrixUni.setText(PrixUni);
    }

    public Text getQte() {
        return Qte;
    }

    public void setQte(String Qte) {
        this.Qte.setText(Qte);
    }

    public Text getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total.setText(total);
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void supprimerpanier(ActionEvent event) {
        PanierService panier = PanierService.getInstance();
        panier.supprimerArticle(prod);
        
    }
    public void addColonne(Node ligne_body) {
        this.bodyPan.getChildren().add(ligne_body);
    }
}
