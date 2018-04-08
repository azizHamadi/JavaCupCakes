/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Recette.AjouterRecette;

import Entity.CategorieRec;
import Entity.Recette;
import Entity.SessionUser;
import Entity.Utilisateur;
import Services.CategorieRecetteService;
import Services.RecetteService;
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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
/**
 * FXML Controller class
 *
 * @author aziz
 */
public class AjouterRecetteClientController implements Initializable {

    @FXML
    private ComboBox<CategorieRec> catRec;
    @FXML
    private TextField nomRec;
    @FXML
    private HTMLEditor description;
    @FXML
    private Button image;
    @FXML
    private TextField textImage;
    
    String imgf="";
    String fdS ="";
    String fdS1 ="";
    
    @FXML
    private Button Enregistrer;
    @FXML
    private Button Annuler;
    @FXML
    private Label lab_CatRec;
    @FXML
    private Label lab_nomRec;
    @FXML
    private Label lab_DescRec;
    @FXML
    private Label lab_image;
    @FXML
    private Button AjouterCategorie;
    
    ObservableList<CategorieRec> listC;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            catRec.getItems().clear();
            CategorieRecetteService catRS = new CategorieRecetteService();
            listC = catRS.getListeCatRec();
            //combobox categorieRecette
            for(CategorieRec c : listC)
            {
                catRec.getItems().add(c);
                catRec.setConverter(new StringConverter<CategorieRec>() {
                    @Override
                    public String toString(CategorieRec object) {
                        return object.getNomCatRec();
                    }

                    @Override
                    public CategorieRec fromString(String string) {
                        return catRec.getItems().stream().filter(ap -> 
                                    ap.getNomCatRec().equals(string)).findFirst().orElse(null);}
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterRecetteClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    

    @FXML
    private void UploadImage(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg" ,"*.jpeg"));
       
        File fd1 = fileChooser.showOpenDialog(catRec.getScene().getWindow());
        if (fd1 != null){
            textImage.setText(fd1.getName());
        imgf = fd1.getName();
        fdS = fd1.getAbsolutePath();
        fdS1 = ("C:/wamp64/www/final/web/public/uploads/brochures/Recettes/"+fd1.getName());
        lab_image.setVisible(false);
        }
        //macopitch l'image 5ater matetcopa ken maya3mel enregistrer w ykounou les conditions lkol s7a7 
        //Files.copy(f.getAbsoluteFile().toPath(),fd.getAbsoluteFile().toPath());
    }

    @FXML
    private void AjouterRecetteClient(ActionEvent event) throws SQLException, IOException {
        if (verif())
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention!");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs!");
            alert.showAndWait();
            return ;
        }
        RecetteService rs = new RecetteService();
        File f1 = new File(fdS);
        File f2 = new File(fdS1);
        Files.copy(f1.getAbsoluteFile().toPath(),f2.getAbsoluteFile().toPath());
        rs.AjouterRecette(new Recette(nomRec.getText(), description.getHtmlText().substring(58, description.getHtmlText().length()-14), imgf, catRec.getValue(), new Utilisateur(SessionUser.getId())));
        
    }
    
    public boolean verif(){
        int img = 0,nom = 0,cat = 0,desc = 0 ;
        if (imgf == "")
        {
            img = 1;
            lab_image.setVisible(true);
        }
        else
            lab_image.setVisible(false);
        
        if (nomRec.getText().length()==0)
        {
            nom = 1 ;
            lab_nomRec.setVisible(true);
        }
        else
            lab_nomRec.setVisible(false);

        if (catRec.getValue() == null)
        {
            cat = 1 ;
            lab_CatRec.setVisible(true);
        }
        else
            lab_CatRec.setVisible(false);
        
        if (description.getHtmlText().length() == 72)
        {
            desc = 1 ;
            lab_DescRec.setVisible(true);
        }
        else
            lab_DescRec.setVisible(false);
        
        return ( img==1 || nom==1 || cat==1 || desc==1 );
    }

    @FXML
    private void AnnulerAjoutRecetteClient(ActionEvent event) {
    }

    @FXML
    private void UploadImageB(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg" ,"*.jpeg"));
       
        File fd1 = fileChooser.showOpenDialog(catRec.getScene().getWindow());
        if (fd1 != null){
            textImage.setText(fd1.getName());
        imgf = fd1.getName();
        fdS = fd1.getAbsolutePath();
        fdS1 = ("C:/wamp64/www/final/web/public/uploads/brochures/Recettes/"+fd1.getName());
        //Files.copy(f.getAbsoluteFile().toPath(),fd.getAbsoluteFile().toPath());
        lab_image.setVisible(false);
        }

    }

    @FXML
    private void AjouterCategorie(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("Nom de categorie");
        dialog.setTitle("Categorie des recettes");
        dialog.setHeaderText("Ajouter une Categorie");
        dialog.setContentText("Nom de la categorie:");
        Optional<String> result = dialog.showAndWait();
        
        result.ifPresent(name -> {
            try {
                CategorieRecetteService crs = new CategorieRecetteService();
                if( crs.rechercheCategorieRec(name) == null){
                    crs.AjouterCategorieRecette(new CategorieRec(name));
                    this.initialize(null, null);
                }
                else
                {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Erreur!");
                    alert.setContentText("Categorie recette existe deja!");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AjouterRecetteClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
        
}
