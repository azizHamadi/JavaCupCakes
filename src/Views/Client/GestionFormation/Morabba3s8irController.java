/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.GestionFormation;

import Views.Client.ListAllSessionsFor.ListAllSessionsForController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    private Text nomFormation;
    @FXML
    private Text ListeSession;
    @FXML
    private WebView description;
    @FXML
    private Text txtdatefin;
    @FXML
    private ImageView image;
 private int idmelListAllFormations;
    private VBox body ;

    public VBox getBody() {
        return body;
    }

    public void setBody(VBox body) {
        this.body = body;
    }
    
    public int getIdmelListAllFormations() {
        return idmelListAllFormations;
    }

    public void setIdmelListAllFormations(int idmelListAllFormations) {
        this.idmelListAllFormations = idmelListAllFormations;
    }

    public Text getListeSession() {
        return ListeSession;
    }

    public void setListeSession(Text ListeSession) {
        this.ListeSession = ListeSession;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("ti 3leh"+getIdmelListAllFormations());
        File file = new File("C:/wamp64/www/final/web/public/uploads/brochures/Formateur/a18bd01eef49479340fa39143532b1b2.jpeg");
        Image img = new Image(file.toURI().toString());
        image.setImage(img);
        System.out.println("ti 3leh"+getIdmelListAllFormations());
    }    

    public VBox getProd() {
        return prod;
    }

    public void setProd(VBox prod) {
        this.prod = prod;
    }

    public Text getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation.setText(nomFormation);    }

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
    private void AfficherListeSessionsFor(MouseEvent event) throws IOException, SQLException {
        System.out.println("event mouse clicked");
        System.out.println("id for mel listAllFormations"+idmelListAllFormations);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/ListAllSessionsFor/ListAllSessionsFor.fxml"));       
        Parent root = loader.load();   
        ListAllSessionsForController sc = loader.getController();
        sc.setIdFormation(idmelListAllFormations);
        //sc.setBody(body);
       
        sc.afficherSessionsFormation(idmelListAllFormations);
        System.out.println("morabba3=" +sc.getIdFormation());
        body.getChildren().clear();
        body.getChildren().add(root);
        //txtdatefin.getScene().setRoot(root);
    }
    
}
