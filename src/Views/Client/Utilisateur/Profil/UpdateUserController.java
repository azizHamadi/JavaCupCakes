/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Utilisateur.Profil;

import Entity.SessionUser;
import Entity.Utilisateur;
import Services.userServices;
import Views.Utilisateur.Profil.ProfilController;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import java.nio.file.Files;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author pc_roua
 */
public class UpdateUserController implements Initializable {
 
    
    private Utilisateur User = new Utilisateur();
     @FXML
    private JFXTextField Nom;
    @FXML
    private JFXTextField Prenom;
    @FXML
    private JFXTextField Adresse;
    private JFXTextField username;
    private JFXTextField Email;
    private JFXTextField Paswword;
    @FXML
    private Label labNom;
    @FXML
    private Label labPrenom;
    @FXML
    private Label labAdresse;
    //private VBox body ;
    @FXML
    private JFXButton tbnUpdate;
    @FXML
    private ImageView ImageView;
    private String imagef ="";
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField Phone;
    @FXML
    private Label labAdresse1;
    @FXML
    private AnchorPane body;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Adresse.setText(SessionUser.getAddresse());
        Nom.setText(SessionUser.getNom());
        Prenom.setText(SessionUser.getPrenom());
        Phone.setText(SessionUser.getPhoneNumber());
        setImageView(SessionUser.getImageProfil());
    } 
    
    @FXML
    private void ModifierUser(ActionEvent event) throws SQLException, IOException {
        User = new Utilisateur(SessionUser.getId(), SessionUser.getUsername(),SessionUser.getEmail(), Phone.getText(), Adresse.getText(),Nom.getText(),Prenom.getText(),SessionUser.getImageProfil());
        userServices us = new userServices();
        us.ModifierLineUser(User);
        FXMLLoader loaderin = new FXMLLoader(getClass().getResource("ProfilClient.fxml"));
        Node UPDAu = loaderin.load();
        ProfilClientController pc = loaderin.getController();
        pc.setAdresse(User.getAddresse());
        pc.setEmail(User.getEmail());
        pc.setNom(User.getNom());
        pc.setPrenom(User.getPrenom());
        pc.setTelephone(User.getPhoneNumber());
        pc.setImage(User.getImageProfil());
        body.getChildren().clear();
        body.getChildren().add(UPDAu);
       // pc.setUser(User);
        pc.setBodyprofil(body);
        
     
    }

    public JFXTextField getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone.setText(Phone);
    }

    public JFXTextField getId() {
        return id;
    }

    public void setId(String id) {
        this.id.setText(id);
    }

    public Utilisateur getUser() {
        return User;
    }

    public void setUser(Utilisateur User) {
        this.User = User;
    }

    public JFXTextField getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom.setText(Nom);
    }

    public JFXTextField getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom.setText(Prenom);
    }

    public JFXTextField getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse.setText(Adresse);
    }

    public JFXTextField getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public JFXTextField getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email.setText(Email);
    }

    public AnchorPane getBody() {
        return body;
    }

    public void setBody(AnchorPane body) {
        this.body = body;
    }

    

    public ImageView getImageView() {
        return ImageView;
    }

    public void setImageView(String ImageView) {
        File file = new File ("C:/wamp64/www/final/web/public/uploads/brochures/User/"+ImageView);
        this.ImageView.setImage(new Image(file.toURI().toString()));
    }

    @FXML
    private void AjouterImage(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
         fileChooser.getExtensionFilters().addAll(
         new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg" ,"*.jpeg"));
       
        File f = fileChooser.showOpenDialog(null);
         if (f != null){
     System.out.println(f.getName());
                     }
         imagef = f.getName();
         File fd = new File("C:/wamp64/www/final/web/public/uploads/brochures/User/"+f.getName());
         String imaged = "";
         imaged ="file:///"+fd.getAbsolutePath();
         Files.copy(f.getAbsoluteFile().toPath(),fd.getAbsoluteFile().toPath());
         ImageView.setImage(new Image(imaged));
    }
    
}
