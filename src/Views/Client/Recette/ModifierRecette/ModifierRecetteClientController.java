/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Recette.ModifierRecette;

import Entity.CategorieRec;
import Entity.Recette;
import Services.CategorieRecetteService;
import Services.RecetteService;
import Views.Client.Recette.AjouterRecette.AjouterRecetteClientController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class ModifierRecetteClientController implements Initializable {

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
    private ComboBox<CategorieRec> catRec;
    @FXML
    private TextField nomRec;
    @FXML
    private HTMLEditor description;
    @FXML
    private Button Annuler;
    @FXML
    private Button Enregistrer;
    private VBox bodyModif ;
    private ModifierRecetteClientController mcc ;
    private ModifierRecetteController CsR;
    private WebView vdescription;
    private Label vnomRec;
    private Label vCatRec;
    @FXML
    private VBox vbody;
    ObservableList<CategorieRec> listC;
    private Recette rec ;

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

    public ComboBox<CategorieRec> getCatRec() {
        return catRec;
    }

    public void setCatRec(CategorieRec catRec) {
        this.catRec.setValue(catRec);
        this.catRec.getSelectionModel().select(catRec);
    }

    public ModifierRecetteController getCsR() {
        return CsR;
    }

    public void setCsR(ModifierRecetteController CsR) {
        this.CsR = CsR;
    }

    public Recette getRec() {
        return rec;
    }

    public void setRec(Recette rec) {
        this.rec = rec;
    }
    
    public VBox getBodyModif() {
        return bodyModif;
    }

    public void setBodyModif(VBox bodyModif) {
        this.bodyModif = bodyModif;
    }

    public ModifierRecetteClientController getMcc() {
        return mcc;
    }

    public void setMcc(ModifierRecetteClientController mcc) {
        this.mcc = mcc;
    }

    public WebView getVdescription() {
        return vdescription;
    }

    public void setVdescription(WebView vdescription) {
        this.vdescription = vdescription;
    }

    public Label getVnomRec() {
        return vnomRec;
    }

    public void setVnomRec(String vnomRec) {
        this.vnomRec.setText(vnomRec);
    }

    public Label getvCatRec() {
        return vCatRec;
    }

    public void setvCatRec(String vCatRec) {
        this.vCatRec.setText(vCatRec);
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

    public TextField getNomRec() {
        return nomRec;
    }

    public void setNomRec(String nomRec) {
        this.nomRec.setText(nomRec);
    }

    public HTMLEditor getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description.setHtmlText(description);
    }
    
    @FXML
    private void AnnulerModification(ActionEvent event) throws IOException {
        FXMLLoader loaderinfoRecette = new FXMLLoader(getClass().getResource("infoRecette.fxml"));
        Node infoRecette = loaderinfoRecette.load();
        InfoRecetteController ic = loaderinfoRecette.getController();
        
        ic.setCsR(CsR);
        ic.setBodyModif(bodyModif);
        ic.setRec(rec);
        ic.setDescription(rec.getDescriptionRec());
        ic.setNomRec(rec.getNomRec());
        ic.setUsername(rec.getIdUser().getUsername());
        ic.setNbrComment(nbrComment.getText());
        ic.setMoyenne(moyenne.getText());
        ic.setCatRec(rec.getIdCatRec().getNomCatRec());
        ic.setCsR(CsR);
        //mcc.setvCatRec(rec.getIdCatRec().getNomCatRec());
        //mazel ne9es 
        
        mcc.getBodyModif().getChildren().clear();
        mcc.getBodyModif().getChildren().add(infoRecette);
    }

    @FXML
    private void ModifierCommentaire(ActionEvent event) throws SQLException, IOException {
        Recette r = new Recette(rec.getIdRec(), nomRec.getText(), "oui", description.getHtmlText(), rec.getImageRec(), catRec.getValue(), rec.getIdUser());
        RecetteService recS = new RecetteService();
        recS.ModifierRecette(rec);
        
        FXMLLoader loaderinfoRecette = new FXMLLoader(getClass().getResource("infoRecette.fxml"));
        Node infoRecette = loaderinfoRecette.load();
        InfoRecetteController ic = loaderinfoRecette.getController();
        
        ic.setCsR(CsR);
        ic.setBodyModif(bodyModif);
        ic.setRec(r);
        ic.setDescription(r.getDescriptionRec());
        ic.setNomRec(r.getNomRec());
        ic.setUsername(r.getIdUser().getUsername());
        ic.setNbrComment(nbrComment.getText());
        ic.setMoyenne(moyenne.getText());
        ic.setCatRec(r.getIdCatRec().getNomCatRec());
        ic.setCsR(CsR);
        //mcc.setvCatRec(rec.getIdCatRec().getNomCatRec());
        //mazel ne9es 
        
        mcc.getBodyModif().getChildren().clear();
        mcc.getBodyModif().getChildren().add(infoRecette);
        
    }
    
}
