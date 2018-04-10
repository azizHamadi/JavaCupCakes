/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.SingleSession;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author FERIEL FADHLOUN
 */
public class SingleSessionController implements Initializable {
@FXML
    private ImageView imageSes;
    @FXML
    private Label nomSess;
    @FXML
    private Label capaciteSes;
    @FXML
    private Label prixSes;
    @FXML
    private Label datedebses;
    @FXML
    private Label datefinses;
    @FXML
    private JFXButton retourListeSess;

    public JFXButton getRetourListeSess() {
        return retourListeSess;
    }

    public void setRetourListeSess(JFXButton retourListeSess) {
        this.retourListeSess = retourListeSess;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
  public ImageView getImageSes() {
        return imageSes;
    }

    public void setImageSes(String imageSes) {
         File file = new File("C:/wamp64/www/final/web/public/uploads/brochures/Formateur/" + imageSes);
        this.imageSes.setImage(new Image(file.toURI().toString()));
    }

    public Label getNomSess() {
        return nomSess;
    }

    public void setNomSess(String nomSess) {
        this.nomSess .setText(nomSess); 
    }

    public Label getCapaciteSes() {
        return capaciteSes;
    }

    public void setCapaciteSes(String capaciteSes) {
        this.capaciteSes .setText(capaciteSes);
    }

    public Label getPrixSes() {
        return prixSes;
    }

    public void setPrixSes(String prixSes) {
        this.prixSes .setText(prixSes); 
    }

    public Label getDatedebses() {
        return datedebses;
    }

    public void setDatedebses(String datedebses) {
        this.datedebses .setText(datedebses);
    }

    public Label getDatefinses() {
        return datefinses;
    }

    public void setDatefinses(String datefinses) {
        this.datefinses .setText(datefinses);
    }

    @FXML
    private void btnRetour(MouseEvent event) throws IOException {
       
                
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../ListAllSessionsFor/ListAllSessionsFor.fxml"));
       Node root = loader.load();
       
    }

    
    

}
