/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Commande.ListeCommande;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class ProdCmdController implements Initializable {

    @FXML
    private ImageView imageview;
    @FXML
    private Text NomProduit;
    @FXML
    private Text QteAcheter;

    public ImageView getImageview() {
        return imageview;
    }

    public void setImageview(String imageview) {
  File file = new File("C:/wamp3/www/CupCakesF/web/public/uploads/brochures/Produit/" + imageview);
        this.imageview.setImage(new Image(file.toURI().toString()));
        }

    public Text getNomProduit() {
        return NomProduit;
    }

    public void setNomProduit(String NomProduit) {
        this.NomProduit.setText(NomProduit);
    }

    public Text getQteAcheter() {
        return QteAcheter;
    }

    public void setQteAcheter(String QteAcheter) {
        this.QteAcheter.setText(QteAcheter);
    }

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
