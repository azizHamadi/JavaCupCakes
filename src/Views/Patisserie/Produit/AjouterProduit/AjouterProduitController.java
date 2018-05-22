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
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
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
    @FXML
    private ImageView Image;
    @FXML
    private Label labNomProd;
    @FXML
    private Label labPrix;
    @FXML
    private Label labQte;
    @FXML
    private Label labCat;
    @FXML
    private Label labTypeProd;
    @FXML
    private Label labImage;
    private VBox vbox; 
    

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
         String imaged = "";
         imaged ="file:///"+fd.getAbsolutePath();
         Files.copy(f.getAbsoluteFile().toPath(),fd.getAbsoluteFile().toPath());
         Image.setImage(new Image(imaged));
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException, IOException {
         if (verif())
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention!");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs!");
            alert.showAndWait();
            return ;
        }
          ProduitService ps = new ProduitService();
        Produit p = new Produit(nomProd.getText(),(double)Integer.parseInt(qteStock.getText()),typProd.getValue(),Integer.parseInt(prixprod.getText()) , 0, "vrai", 0, 0,imagef);
        ps.AjouterProduit(p,cat.getValue());
        System.out.println("c bon");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("Produit insérée avec succés!");
        alert.show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ListeProduit/ListeProduit.FXML"));
        Parent root = loader.load();
        vbox.getChildren().clear();
        vbox.getChildren().add(root);
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
    
    public boolean verif(){
        int img = 0,nom = 0,cata = 0,qte = 0, type = 0, prix=0 ;
        if (imagef == "")
        {
            img = 1;
            labImage.setVisible(true);
        }
        else
            labImage.setVisible(false);
        
        if (nomProd.getText().length()==0)
        {
            nom = 1 ;
            labNomProd.setVisible(true);
        }
        else
            labNomProd.setVisible(false);
        
        if (prixprod.getText().length()==0)
        {
            prix = 1 ;
            labPrix.setVisible(true);
        }
        else
            labPrix.setVisible(false);
        
       /* if (Integer.parseInt(prixprod.getText())>0) {
            prix = 1 ;
            labPrix.setVisible(true);
        } else labPrix.setVisible(false);
        */
        /*
         if (Integer.parseInt(qteStock.getText())<0)
        {
            qte = 1 ;
            labPrix.setVisible(true);
        }
        else
            labPrix.setVisible(false);
*/
        if (qteStock.getText().length()==0 )
        {
            qte = 1 ;
            labQte.setVisible(true);
        }
        else
            labQte.setVisible(false);

        if (cat.getValue() == null)
        {
            cata = 1 ;
            labCat.setVisible(true);
        }
        else
            labCat.setVisible(false);
        
        if (typProd.getValue() == null)
        {
            type = 1 ;
            labTypeProd.setVisible(true);
        }
        else
            labTypeProd.setVisible(false);
        
        return ( img==1 || nom==1 || cata==1 || qte==1 || type ==1 );
    }

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

}
