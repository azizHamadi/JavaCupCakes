/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Produit.ListeCategorie;

import Entity.Categorie;
import Services.CategorieService;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class ListeCategoriePController implements Initializable {

    @FXML
    private TableView<Categorie> Categorie;
    @FXML
    private TableColumn<Categorie, String> NomC;
    @FXML
    private JFXTextField Nom;
    @FXML
    private Text Nbr;
    @FXML
    private TableColumn<Categorie, Integer> IdC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            RefreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(ListeCategoriePController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }    
 public void ClearFields()
    {
        Nom.clear();
        
    }

    //affichage des champs dans les textfiels a partir du table 
    @FXML
    private void FetcherTable(MouseEvent event) throws SQLException {
               CategorieService service=new CategorieService();
        Nom.setText(Categorie.getSelectionModel().getSelectedItem().getNomCat());
        Nbr.setText(String.valueOf(service.CountProduiParCat(Categorie.getSelectionModel().getSelectedItem().getIdCat())));
        
   }
    //afficher le contenu de la table formation dans le tableau
   public void RefreshTable() throws SQLException 
   {
        Categorie.getItems().clear();
        CategorieService service=new CategorieService();
        ObservableList<Categorie> listC = FXCollections.observableArrayList(service.AfficherCategorie());
        NomC.setCellValueFactory(new PropertyValueFactory<>("nomCat"));
        IdC.setCellValueFactory(new PropertyValueFactory<>("idCat"));
        Categorie.setItems(listC);
   }


    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
          CategorieService service = new CategorieService();
         Categorie p = new Categorie(Categorie.getSelectionModel().getSelectedItem().getIdCat(),Nom.getText());
         service.ModifierCategorie(p);
         RefreshTable();
    }
    
}
