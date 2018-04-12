/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Utilisateur.Update;

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
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField Email;
    private JFXTextField Paswword;
    @FXML
    private Label labNom;
    @FXML
    private Label labPrenom;
    @FXML
    private Label labAdresse;
    private VBox body ;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void ModifierUser(ActionEvent event) throws SQLException, IOException {//modif mafhemtech pk plutot tsakkert wallah w jo3t :p hhhhhhh 5alleha l'2m1 plllzzzz !!! w matansech tna77i email w mp w username 5ater mayetbadlouch 
     
        User = new Utilisateur(Integer.parseInt(id.getText()), username.getText(), Email.getText(), Nom.getText(), Prenom.getText(),Adresse.getText(),imagef,Phone.getText());
        userServices us = new userServices();
        us.ModifierLineUser(User);
        
        FXMLLoader loaderin = new FXMLLoader(getClass().getResource("../Profil/Profil.fxml"));
        Node UPDAu = loaderin.load();
        ProfilController pc = loaderin.getController();
        pc.setAdresse(User.getAddresse());
        pc.setEmail(User.getEmail());
        pc.setNom(User.getNom());
        pc.setPrenom(User.getPrenom());
        pc.setTelephone(User.getPhoneNumber());
        pc.setImage(imagef);
        body.getChildren().clear();
        body.getChildren().add(UPDAu);
        pc.setUser(User);
        pc.setBody(body);
        
        
        /*ic.setCsR(CsR);
        ic.setBodyModif(bodyModif);
        ic.setRec(r);
        ic.setDescription(r.getDescriptionRec());
        ic.setNomRec(r.getNomRec());
        ic.setUsername(r.getIdUser().getUsername());
        ic.setNbrComment(nbrComment.getText());
        ic.setMoyenne(moyenne.getText());
        ic.setCatRec(r.getIdCatRec().getNomCatRec());
        ic.setCsR(CsR);*/
        //mcc.setvCatRec(rec.getIdCatRec().getNomCatRec());
        //mazel ne9es 
        
        /*mcc.getBodyModif().getChildren().clear();
        mcc.getBodyModif().getChildren().add(infoRecette);*/
        
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

    public VBox getBody() {
        return body;
    }

    public void setBody(VBox body) {
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
