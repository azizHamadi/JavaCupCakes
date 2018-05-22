/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Recette.MesRecettes;

import Entity.Recette;
import Services.RecetteService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class Morabba3s8irController implements Initializable {

    @FXML
    private VBox prod;
    @FXML
    private ImageView image;
    @FXML
    private Text nom;
    @FXML
    private Text nomCat;
    @FXML
    private WebView description;
    @FXML
    private Text nomUser;
    @FXML
    private Text note;
    private int idRec ;
    private VBox vbox;
    @FXML
    private Label supprimer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        File file = new File("C:/wamp64/www/final/web/public/uploads/brochures/Produit/a18bd01eef49479340fa39143532b1b2.jpeg");
        Image img = new Image(file.toURI().toString());
        image.setImage(img);

    }    

    public void setImage(String image) {
        File file = new File("C:/wamp64/www/final/web/public/uploads/brochures/Recettes/" + image);
        this.image.setImage(new Image(file.toURI().toString()));
    }

    public void setNomCat(String nomCat) {
        this.nomCat.setText(nomCat);
    }

    public void setDescription(String description) {
        WebEngine webEngine = this.description.getEngine();
        webEngine.loadContent(description);
    }

    public void setNomUser(String nomUser) {
        this.nomUser.setText(nomUser);
    }

    public void setNote(String note) {
        this.note.setText(note);
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public VBox getProd() {
        return prod;
    }

    public ImageView getImage() {
        return image;
    }

    public Text getNom() {
        return nom;
    }

    public Text getNomCat() {
        return nomCat;
    }

    public WebView getDescription() {
        return description;
    }

    public Text getNomUser() {
        return nomUser;
    }

    public Text getNote() {
        return note;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    public Label getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Label supprimer) {
        this.supprimer = supprimer;
    }
    
    @FXML
    private void supprimer(MouseEvent event) throws SQLException, IOException {
        RecetteService recService = new RecetteService();
        recService.SupprimerRecette(new Recette(idRec));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/Recette/ListAllRecettes/ListAllRecettes.fxml"));
        Node root = loader.load();
        vbox.getChildren().clear();
        vbox.getChildren().add(root);
        
    }
    
}
