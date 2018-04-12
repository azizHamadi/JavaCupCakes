/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Utilisateur.Profil;

import Entity.SessionUser;
import Entity.Utilisateur;
import Services.userServices;
import Views.Utilisateur.Update.UpdateUserController;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author pc_roua
 */
public class ProfilController implements Initializable {

    @FXML
    private Label Nom;
    @FXML
    private Label Prenom;
    @FXML
    private Label Adresse;
    @FXML
    private Label Telephone;
    @FXML
    private Label Email;
    @FXML
    private JFXButton Modifier;
    private Utilisateur User;
    private VBox body ;
    @FXML
    private JFXButton Retour;
    @FXML
    private ImageView Image;
    private String imagef ="";
    @FXML
    private JFXButton ajout;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    public void button(){
         
        if(SessionUser.getRoles().equals("a:2:{i:0;s:11:\"ROLE_CLIENT\";i:1;s:16:\"ROLE_SUPER_ADMIN\";}")){
            Modifier.setVisible(false);
        }
        else{
            Modifier.setVisible(true);
        }
        
         if(User.getImageProfil().equals("")){
            ajout.setVisible(true);}
            else{
                ajout.setVisible(false);
            }
    }
    public Label getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom.setText(Nom);
    }

    public Label getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom.setText(Prenom);
    }

    public Label getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse.setText(Adresse);
    }

    public Label getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone.setText(Telephone);
    }

    public Label getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email.setText(Email);
    }

    public ImageView getImage() {
        return Image;
    }

    public void setImage(String Image) {
        File file = new File ("C:/wamp64/www/final/web/public/uploads/brochures/User/"+Image);
        this.Image.setImage(new Image(file.toURI().toString()));
    }

    public Utilisateur getUser() {
        return User;
    }

    public void setUser(Utilisateur User) {
        this.User = User;
    }

    public VBox getBody() {
        return body;
    }

    public void setBody(VBox body) {
        this.body = body;
    }

    @FXML
    private void ModifierUser(ActionEvent event) throws IOException {
        FXMLLoader loaderin = new FXMLLoader(getClass().getResource("../Update/UpdateUser.fxml"));
        Node loaderUp = loaderin.load();
        UpdateUserController uc = loaderin.getController();
        uc.setUser(User);
        uc.setAdresse(User.getAddresse());
        uc.setEmail(User.getEmail());
        uc.setNom(User.getNom());
        uc.setPrenom(User.getPrenom());
        uc.setUsername(User.getUsername());
        uc.setId(User.getId().toString());
        uc.setImageView(User.getImageProfil());
        uc.setPhone(User.getPhoneNumber());
        body.getChildren().clear();
        body.getChildren().add(loaderUp);
        uc.setBody(body);
    }
     @FXML
        private void Retour(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../dashboard/Acceuil.fxml"));       
        Parent root = loader.load();   
        Nom.getScene().setRoot(root);
        
        } 

      
   

    @FXML
    private void Enregistrer(ActionEvent event) throws SQLException, MessagingException, IOException {
          FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
         fileChooser.getExtensionFilters().addAll(
         new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg" ,"*.jpeg"));
       
        File f = fileChooser.showOpenDialog(null);
         if (f != null){
     System.out.println(f.getName());
                     }
         imagef = f.getName();
         System.out.println(imagef);
         File fd = new File("C:/wamp64/www/final/web/public/uploads/brochures/User/"+f.getName());
         String imaged = "";
         imaged ="file:///"+fd.getAbsolutePath();
         Files.copy(f.getAbsoluteFile().toPath(),fd.getAbsoluteFile().toPath());
         Image.setImage(new Image(imaged));
         
        
         userServices us = new userServices();
         Utilisateur user = new Utilisateur(User.getId(),imagef);
          us.AjouterImage(user);
          System.out.println("test1.ProfilController.Enregistrer()");
    }
    
    
    
    
}

