/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Produit.AjouterProduit;

import Entity.Categorie;
import Entity.Produit;
import Services.CategorieService;
import Services.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private JFXTextField nomProd;
    @FXML
    private JFXTextField prixprod;
    @FXML
    private JFXTextField qteStock;
    @FXML
    private JFXComboBox<String> cat;
    @FXML
    private JFXComboBox<String> typProd;
    @FXML
    private JFXButton image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         typProd.getItems().add("Sucrée");
        typProd.getItems().add("Salée");
          try {
            CategorieService categ = new CategorieService();
            List<Categorie> listCat = categ.AfficherCategorie();
            for(Categorie c : listCat)
            {
                cat.getItems().add(c.getNomCat());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
String imagef="";
    @FXML
    private void AjouterImage(ActionEvent event) throws IOException {
         FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
         fileChooser.getExtensionFilters().addAll(
         new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg" ,"*.jpeg"));
       
        File f = fileChooser.showOpenDialog(null);
         if (f != null){
     System.out.println(f.getName());
                     }
         imagef = f.getName();
         File fd = new File("C:/wamp64/www/final/web/public/uploads/brochures/Produit/"+f.getName());

         Files.copy(f.getAbsoluteFile().toPath(),fd.getAbsoluteFile().toPath());
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException, IOException {
        ProduitService ps = new ProduitService();
        Produit p = new Produit(nomProd.getText(),(double)Integer.parseInt(qteStock.getText()),typProd.getValue(),Integer.parseInt(prixprod.getText()) , 0, "vrai", 0, 0,imagef);
        ps.AjouterProduit(p,cat.getValue());
        System.out.println("c bon");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("Produit insérée avec succés!");
        alert.show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProd.FXML"));
        Parent root = loader.load();
        typProd.getScene().setRoot(root);

    }

    @FXML
    private void AjouterCategorie(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("Nom de categorie");
        dialog.setTitle("Categorie des Produit");
        dialog.setHeaderText("Ajouter une Categorie");
        dialog.setContentText("Nom de la categorie:");
        Optional<String> result = dialog.showAndWait();
        
        result.ifPresent(name -> {
            try {
                CategorieService crs = new CategorieService();
                if( crs.rechercheCategorie(name) == null){
                    crs.AjouterCategorie(new Categorie(name));
                    this.initialize(null, null);
                }
                else
                {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Erreur!");
                    alert.setContentText("Categorie produit existe deja!");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
}
