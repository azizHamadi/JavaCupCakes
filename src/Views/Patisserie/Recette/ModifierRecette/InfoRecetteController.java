/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Recette.ModifierRecette;

import Entity.Recette;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import impl.org.controlsfx.skin.RatingSkin;
import  javafx.beans.property.*;
import  javafx.geometry.Orientation;
import  javafx.scene.control.Control;
import  javafx.scene.control.Skin;


/**
 * FXML Controller class
 *
 * @author aziz
 */
public class InfoRecetteController implements Initializable {

    @FXML
    private VBox bodyModif;
    @FXML
    private HBox info;
    @FXML
    private Label username;
    @FXML
    private Label nbrComment;
    @FXML
    private Label username12;
    @FXML
    private Label username11;
    @FXML
    private Label moyenne;
    @FXML
    private Label nomRec;
    @FXML
    private Label CatRec;
    @FXML
    private WebView description;
    @FXML
    private Button Modifier;
    private int idRec ;
    private Recette rec ;
    private ModifierRecetteController CsR ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        WebEngine web = description.getEngine();
        web.setUserStyleSheetLocation(getClass().getResource("../../../../Views/commonPatisserie.css").toString());        
    }    

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public VBox getBodyModif() {
        return bodyModif;
    }

    public void setBodyModif(VBox bodyModif) {
        this.bodyModif = bodyModif;
    }

    public WebView getDescription() {
        return description;
    }

    public void setDescription(String description) {
        WebEngine webEngine = this.description.getEngine();
        webEngine.loadContent(description);
    }

    public Label getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public Label getNbrComment() {
        return nbrComment;
    }

    public void setNbrComment(String nbrComment) {
        this.nbrComment.setText(nbrComment);
    }

    public Label getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(String moyenne) {
        this.moyenne.setText(moyenne);
    }

    public Label getNomRec() {
        return nomRec;
    }

    public void setNomRec(String nomRec) {
        this.nomRec.setText(nomRec);
    }

    public Label getCatRec() {
        return CatRec;
    }

    public void setCatRec(String CatRec) {
        this.CatRec.setText(CatRec);
    }

    public Recette getRec() {
        return rec;
    }

    public void setRec(Recette rec) {
        this.rec = rec;
    }

    public ModifierRecetteController getCsR() {
        return CsR;
    }

    public void setCsR(ModifierRecetteController CsR) {
        this.CsR = CsR;
    }
    
    @FXML
    private void ModifierRecette(ActionEvent event) throws IOException {
        FXMLLoader loaderModifier = new FXMLLoader(getClass().getResource("ModifierRecettePatisserie.fxml"));
        Node RecetteModifier = loaderModifier.load();
        ModifierRecettePatisserieController mcc = loaderModifier.getController();
        mcc.setMcc(mcc);
        mcc.setBodyModif(bodyModif);
        mcc.setRec(rec);
        mcc.setDescription(rec.getDescriptionRec());
        mcc.setNomRec(rec.getNomRec());
        mcc.setUsername(rec.getIdUser().getUsername());
        mcc.setNbrComment(nbrComment.getText());
        mcc.setMoyenne(moyenne.getText());
        mcc.setCatRec(rec.getIdCatRec());
        mcc.setCsR(CsR);
        //mcc.setvCatRec(rec.getIdCatRec().getNomCatRec());
        //mazel ne9es 
        bodyModif.getChildren().clear();
        bodyModif.getChildren().add(RecetteModifier);
    }
    
}
