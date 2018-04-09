/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Commande.Feedback;

import Entity.Commande;
import Entity.FeedBack;
import Services.FeedbackService;
import Views.Client.Commande.listeCommande.CommandeController;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class FeedbackController implements Initializable {

    @FXML
    private AnchorPane body;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField sujet;
    private Commande cmd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Commande getCmd() {
        return cmd;
    }

    public void setCmd(Commande cmd) {
        this.cmd = cmd;
    }

    @FXML
    private void Enregistrer(ActionEvent event) throws IOException, SQLException {
        body.getChildren().clear();
        FeedbackService ser = new FeedbackService();
        FeedBack f = new FeedBack(sujet.getText(), description.getText());
        ser.AjouterFeedback(f,cmd);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("Feedback Envoyer avec succés!");
        alert.show();
        
        
    }
    
}
