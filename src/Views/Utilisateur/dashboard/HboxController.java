/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Utilisateur.dashboard;

import Entity.Utilisateur;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author pc_roua
 */
public class HboxController implements Initializable {

    private HBox hbox;
    private ImageView image;
    @FXML
    private Label username;
    @FXML
    private Label email;
    @FXML
    private Button btn;
    private Utilisateur user ;
    @FXML
    private FontAwesomeIconView Us;
    @FXML
    private MaterialDesignIconView rr;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public FontAwesomeIconView getUs() {
        return Us;
    }

    public void setUs(FontAwesomeIconView Us) {
        this.Us = Us;
    }

    public MaterialDesignIconView getRr() {
        return rr;
    }

    public void setRr(MaterialDesignIconView rr) {
        this.rr = rr;
    }

    public HBox getHbox() {
        return hbox;
    }

    public void setHbox(HBox hbox) {
        this.hbox = hbox;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(String image) {
        File file = new File( "C:/PiJava/CupCakesJava/src/" + image);
        this.image.setImage(new Image(file.toURI().toString()));
    }

    public Label getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public Label getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
    
}
