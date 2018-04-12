/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Produit.ListeProduit;

import Entity.Categorie;
import Entity.Produit;
import Entity.SessionUser;
import Services.CategorieService;
import Services.ProduitService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class ListeProduitController implements Initializable {

    @FXML
    private TableView<Produit> TableProd;
    @FXML
    private TableColumn<Produit, String> nomP;
    @FXML
    private TableColumn<Produit, Integer> QantitéP;
    @FXML
    private TableColumn<Produit, Double> PrixP;
    @FXML
    private TableColumn<Produit, String> TypeP;
    @FXML
    private TableColumn<Produit, String> CatP;
    @FXML
    private JFXTextField NomProd;
    @FXML
    private JFXTextField QteStock;
    @FXML
    private JFXTextField Prix;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXComboBox<String> Categorie;
    @FXML
    private TableColumn<Produit, Integer> QteAcheter;
    @FXML
    private ImageView imageview;
    String imgp = "";
    @FXML
    private TableColumn<Produit, String> image;
    @FXML
    private TableColumn<Produit, Integer> idProd;
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            type.getItems().add("Sucrée");
            type.getItems().add("Salée");
            CategorieService categ = new CategorieService();
            List<Categorie> listCat = categ.AfficherCategorie();
            for (Categorie c : listCat) {
                Categorie.getItems().add(c.getNomCat());
            }
            RefreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(ListeProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //vider les champs

    public void ClearFields() {
        NomProd.clear();
        QteStock.clear();
        Prix.clear();
        id.clear();
        imageview.setImage(null);
        type.getItems().clear();
        Categorie.getItems().clear();
    }

    //affichage des champs dans les textfiels a partir du table 
    @FXML
    private void FetcherTable(MouseEvent event) {

        NomProd.setText(TableProd.getSelectionModel().getSelectedItem().getNomProd());
        QteStock.setText(TableProd.getSelectionModel().getSelectedItem().getQteStockProd().toString());
        Prix.setText(TableProd.getSelectionModel().getSelectedItem().getPrixProd().toString());
        Categorie.setValue(TableProd.getSelectionModel().getSelectedItem().getIdCat().getNomCat());
        type.setValue(TableProd.getSelectionModel().getSelectedItem().getTypeProd());
        id.setText(TableProd.getSelectionModel().getSelectedItem().getIdProd().toString());
        imgp = TableProd.getSelectionModel().getSelectedItem().getImageprod();
        Image image = new Image("file:///c:/wamp64/www/final/web/public/uploads/brochures/Produit/" + imgp, imageview.getFitWidth(), imageview.getFitHeight(), true, true);
        imageview.setImage(image);
    }

    //afficher le contenu de la table formation dans le tableau
    public void RefreshTable() throws SQLException {
        TableProd.getItems().clear();
        ProduitService service = new ProduitService();
        ObservableList<Produit> listP = FXCollections.observableArrayList(service.AfficherProduitBack(SessionUser.getId()));
        nomP.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        QantitéP.setCellValueFactory(new PropertyValueFactory<>("qteStockProd"));
        PrixP.setCellValueFactory(new PropertyValueFactory<>("prixProd"));
        TypeP.setCellValueFactory(new PropertyValueFactory<>("typeProd"));
        CatP.setCellValueFactory(new PropertyValueFactory<>("idCat"));
        image.setCellValueFactory(new PropertyValueFactory<>("imageprod"));
        QteAcheter.setCellValueFactory(new PropertyValueFactory<>("QteAcheter"));
        idProd.setCellValueFactory(new PropertyValueFactory<>("idProd"));
        TableProd.setItems(listP);
    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        ProduitService service = new ProduitService();
        Produit p = new Produit(Integer.parseInt(id.getText()), NomProd.getText(), Double.parseDouble(QteStock.getText()), type.getValue(), Integer.parseInt(Prix.getText()), imgp);
        service.UpdateProduit(p, Categorie.getValue());
        RefreshTable();
    }

    @FXML
    private void SupprimerProd(ActionEvent event) throws SQLException {
        ProduitService service = new ProduitService();
        Produit p = new Produit(Integer.parseInt(id.getText()), "Faux");
        service.SupprimerProduit(p);
        RefreshTable();
    }

}
