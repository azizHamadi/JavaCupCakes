/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Commande.CmdSingle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class BodyCmdController implements Initializable {

    @FXML
    private AnchorPane prod;
    @FXML
    private Text nomP;
    @FXML
    private Text QteAcheteer;
    @FXML
    private ImageView Image;

    public ImageView getImage() {
        return Image;
    }

    public void setImage(String Image) {
       File file = new File("C:/wamp3/www/CupCakesF/web/public/uploads/brochures/Produit/" + Image);
        this.Image.setImage(new Image(file.toURI().toString()));
    }

    
    
    
    public AnchorPane getProd() {
        return prod;
    }

    public void setProd(AnchorPane prod) {
        this.prod = prod;
    }

    public Text getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP.setText(nomP);
    }

    public Text getQteAcheteer() {
        return QteAcheteer;
    }

    public void setQteAcheteer(String QteAcheteer) {
        this.QteAcheteer.setText(QteAcheteer);
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
