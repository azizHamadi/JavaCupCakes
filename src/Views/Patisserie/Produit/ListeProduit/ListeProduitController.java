/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Produit.ListeProduit;

import Entity.Categorie;
import Entity.Produit;
import Services.CategorieService;
import Services.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class ListeProduitController implements Initializable {

    @FXML
    private TableView<Produit> TableProd;
    private TableColumn<Produit, String> nomP;
    private TableColumn<Produit, Integer> QantitéP;
    private TableColumn<Produit, Double> PrixP;
    private TableColumn<Produit, String> TypeP;
    private TableColumn<Produit, String> CatP;
    private JFXTextField NomProd;
    private JFXTextField QteStock;
    private JFXTextField Prix;
    private JFXComboBox<String> type;
    private JFXComboBox<String> Categorie;
    private TableColumn<Produit, Integer> QteAcheter;
    @FXML
    private ImageView imageview;
String   imgp= "";
    private TableColumn<Produit, String> image;
    private TableColumn<Produit, Integer> idProd;
    @FXML
    private TextField id;
    @FXML
    private TableColumn<?, ?> columnNom;
    @FXML
    private TableColumn<?, ?> columnPlace;
    @FXML
    private TableColumn<?, ?> columnDescription;
    @FXML
    private TableColumn<?, ?> columnDate;
    @FXML
    private TableColumn<?, ?> columnId;
    @FXML
    private TableColumn<?, ?> columnImage;
    @FXML
    private TableColumn<?, ?> columnTypeFor;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtPlace;
    @FXML
    private JFXButton btnModifier;
    @FXML
    private JFXComboBox<?> combotypeformation;
    @FXML
    private JFXDatePicker txtdate;
    @FXML
    private JFXButton btnbrowser;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXTextField txtimage;
    @FXML
    private WebView txtDescription;
    @FXML
    private JFXButton btnAjouterFormationn;
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
            for(Categorie c : listCat)
            {
                Categorie.getItems().add(c.getNomCat());
            }
            RefreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(ListeProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
  //vider les champs
    public void ClearFields()
    {
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
        Image image = new Image("file:///c:/wamp3/www/CupCakesF/web/public/uploads/brochures/Produit/" + imgp ,imageview.getFitWidth(),imageview.getFitHeight(),true,true);
        imageview.setImage(image);
   }
    //afficher le contenu de la table formation dans le tableau
   public void RefreshTable() throws SQLException
   {
        TableProd.getItems().clear();
        ProduitService service=new ProduitService();
        ObservableList<Produit> listP = FXCollections.observableArrayList(service.AfficherNomProduit());
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
        private void Modifier(ActionEvent event) throws SQLException {
            ProduitService service = new ProduitService();
         Produit p = new Produit(Integer.parseInt(id.getText()), NomProd.getText(), Double.parseDouble(QteStock.getText()), type.getValue(),Integer.parseInt(Prix.getText()),imgp);
         service.UpdateProduit(p,Categorie.getValue());
         RefreshTable();
    }

    private void SupprimerProd(ActionEvent event) throws SQLException {
         ProduitService service = new ProduitService();
         Produit p = new Produit(Integer.parseInt(id.getText()), "Faux");
         service.SupprimerProduit(p);
         RefreshTable();
    }

    @FXML
    private void ModifierFormation(ActionEvent event) {
    }

    @FXML
    private void SupprimerFormation(ActionEvent event) {
    }

    @FXML
    private void AjouterFormationn(ActionEvent event) {
    }

    @FXML
    private void listedesSessions(ActionEvent event) {
    }
    
}
