/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Promotion.DetailSession;

import Entity.Session;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author hamdi fathallah
 */
public class SessionSingleController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label nomSes;
    @FXML
    private Label Formation;
    @FXML
    private Label prix;
    @FXML
    private Label capacoté;
    @FXML
    private Label nvprix;
    private HBox hb;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public ImageView getImage() {
        return image;
    }

    public Label getNomSes() {
        return nomSes;
    }

    public Label getFormation() {
        return Formation;
    }

    public Label getPrix() {
        return prix;
    }

    public Label getCapacoté() {
        return capacoté;
    }

    public Label getNvprix() {
        return nvprix;
    }

    public HBox getHb() {
        return hb;
    }

    public void setImage(String image) {
         File file = new File("D:/wamp64/www/final/web/public/uploads/brochures/Formateur/" + image);
        this.image.setImage(new Image(file.toURI().toString()));    }

    public void setNomSes(String nomSes) {
        this.nomSes.setText(nomSes);
    }

    public void setFormation(String Formation) {
        this.Formation.setText(Formation);
    }

    public void setPrix(String prix) {
        this.prix.setText(prix);
    }

    public void setCapacoté(String capacoté) {
        this.capacoté.setText(capacoté);
    }

    public void setNvprix(String nvprix) {
        this.nvprix.setText(nvprix);
    }

    public void setHb(HBox hb) {
        this.hb = hb;
    }
    private Session sess;

    public Session getSess() {
        return sess;
    }

    public void setSess(Session sess) {
        this.sess = sess;
    }
    
    
    
}
