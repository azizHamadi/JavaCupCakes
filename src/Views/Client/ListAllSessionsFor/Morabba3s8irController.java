/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.ListAllSessionsFor;

import Services.ServiceEducate;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author FERIEL FADHLOUN
 */
public class Morabba3s8irController implements Initializable {

    @FXML
    private VBox prod;
    @FXML
    private Text nom_session;
    @FXML
    private Text txtinscription;
    @FXML
    private WebView description;
    @FXML
    private Text txtdatedebut;
    @FXML
    private Text txtdatefin;
    @FXML
    private ImageView image;

    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public VBox getProd() {
        return prod;
    }

    public void setProd(VBox prod) {
        this.prod = prod;
    }

    public Text getNomFormation() {
        return nom_session;
    }

    public void setNomFormation(String nomSession) {
        this.nom_session.setText(nomSession);    }

    public Text getNom_session() {
        return nom_session;
    }

    public void setNom_session(Text nom_session) {
        this.nom_session = nom_session;
    }

    public Text getTxtinscription() {
        return txtinscription;
    }

    public void setTxtinscription(Text txtinscription) {
        this.txtinscription = txtinscription;
    }

    public Text getTxtdatedebut() {
        return txtdatedebut;
    }

    public void setTxtdatedebut(String txtdatedebut) {
        this.txtdatedebut.setText( txtdatedebut);
    }

    public Text getTxtdatefin() {
        return txtdatefin;
    }

    public void setTxtdatefin(String txtdate) {
       this.txtdatefin.setText( txtdate);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(String image) {
        File file = new File("C:/wamp64/www/final/web/public/uploads/brochures/Formateur/" + image);
        this.image.setImage(new Image(file.toURI().toString()));
    }

    @FXML
    private void InscriptionClient(MouseEvent event) {
        ServiceEducate se=new ServiceEducate();
       // se.inscriptionClient(idSessionpourinscri);
    }

      
    
    
}
