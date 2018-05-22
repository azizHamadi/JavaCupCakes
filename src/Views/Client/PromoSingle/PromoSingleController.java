/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.PromoSingle;

import Entity.Produit;
import Services.PanierService;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author hamdi fathallah
 */
public class PromoSingleController implements Initializable {

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
    @FXML
    private Button BtAjouter;
    @FXML
    private JFXTextField QteAcheter;
    @FXML
    private Label nvprix;
    private HBox hb;
    @FXML
    private Label labPromo;

    public HBox getHb() {
        return hb;
    }

    public void setHb(HBox hb) {
        this.hb = hb;
    }

    public Produit getProd() {
        return prod;
    }

    public void setProd(Produit prod) {
        this.prod = prod;
    }
    private Produit prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterPanier(ActionEvent event) {
        PanierService panier = PanierService.getInstance();
        // System.out.println("tekhdem");
        // PanierService panier = new PanierService();

        if (QteAcheter.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Preciser la quantité a acheter");
            alert1.show();
        } else if (Integer.parseInt(QteAcheter.getText()) > prod.getQteStockProd()) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Quantité disponible est : " + prod.getQteStockProd().toString());
            alert1.show();
        } else {
            panier.ajouterArticle(prod, Integer.parseInt(QteAcheter.getText()));

            System.out.println(panier.Afficher());
        }
    }

    public ImageView getImage() {
        return image;
    }

    public Label getNomProd() {
        return nomProd;
    }

    public Label getCategorie() {
        return categorie;
    }

    public Label getPrix() {
        return prix;
    }

    public Label getStock() {
        return stock;
    }

    public Button getBtAjouter() {
        return BtAjouter;
    }

    public JFXTextField getQteAcheter() {
        return QteAcheter;
    }

    public Label getNvprix() {
        return nvprix;
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

    public void setBtAjouter(Button BtAjouter) {
        this.BtAjouter = BtAjouter;
    }

    public void setNvprix(String nvprix) {
        this.nvprix.setText(nvprix);
    }

    public void Controle() {
        if (prod.getQteStockProd() > 0) {
            BtAjouter.setVisible(true);
        }
    }

    public void setQteAcheter(JFXTextField QteAcheter) {
        this.QteAcheter = QteAcheter;
    }

}
