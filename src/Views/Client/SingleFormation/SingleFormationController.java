/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.SingleFormation;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author FERIEL FADHLOUN
 */
public class SingleFormationController implements Initializable {

   
    
    @FXML
    private Label nomFormation;
    @FXML
    private Label dateFormation;
    private JFXButton retourListeSess;
   
    @FXML
    private ImageView imageFor;
    @FXML
    private WebView txtDescription;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private WebView webviewimage;

    public WebView getWebviewimage() {
        return webviewimage;
    }

    public void setWebviewimage(WebView webviewimage) {
        this.webviewimage = webviewimage;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    


    public JFXButton getRetourListeSess() {
        return retourListeSess;
    }

    public void setRetourListeSess(JFXButton retourListeSess) {
        this.retourListeSess = retourListeSess;
    }

    public Label getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation.setText(nomFormation);
    }

    public Label getDateFormation() {
        return dateFormation;
    }

    public void setDateFormation(String dateFormation) {
        this.dateFormation .setText(dateFormation);
    }

  
    public WebView getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(WebView txtDescription) {
       this.txtDescription=txtDescription;
    }

    public ImageView getImageFor() {
        return imageFor;
    }

    public void setImageFor(String imageFor) {
        File file = new File("C:/wamp64/www/final/web/public/uploads/brochures/Formateur/" + imageFor);
        this.imageFor.setImage(new Image(file.toURI().toString()));
    }

    private void btnRetour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionFormation/ListAllFormations.fxml"));
        Node root = loader.load();
        System.out.println("Views.Client.SingleFormation.SingleFormationController.btnRetour()");
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(root);
    }

    @FXML
    private void btnRetour(MouseEvent event) {
    }
    
}
