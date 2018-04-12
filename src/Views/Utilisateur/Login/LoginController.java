/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Utilisateur.Login;

import Entity.SessionUser;

import Entity.Utilisateur;
import Services.userServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 * FXML Controller class
 *
 * @author pc_roua
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField login;
  
    @FXML
    private Text message;
    @FXML
    private Button btn5;
    @FXML
    private JFXButton Register;
    @FXML
    private JFXPasswordField mdp;

   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void LoginAction(ActionEvent event) throws SQLException, IOException {
        userServices us = new userServices ();
        Utilisateur User=null;
        User = us.Login(login.getText());
 
               if (User!=null && BCrypt.checkpw(mdp.getText(),User.getPassword().substring(0,2)+"a"+User.getPassword().substring(3))){
            SessionUser.setAddresse(User.getAddresse());
            SessionUser.setEmail(User.getEmail());
             SessionUser.setId(User.getId());
              SessionUser.setPassword(User.getPassword());
            SessionUser.setPhoneNumber(User.getPhoneNumber());
            SessionUser.setRoles(User.getRoles());
            SessionUser.setUsername(User.getUsername());
            
            if(User.getRoles().equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}")){
               Parent root = FXMLLoader.load(getClass().getResource("../../test/folder/ClientTemplate.fxml"));
                    Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();        
          

                 }           
            else if(User.getRoles().equals("a:1:{i:0;s:15:\"ROLE_PATISSERIE\";}")) {
                    Parent root = FXMLLoader.load(getClass().getResource("../../test/folder/BackPatTemplate.fxml"));
                    Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();        
          
            } 
            else if (User.getRoles().equals("a:1:{i:0;s:14:\"ROLE_FORMATEUR\";}")) {
                 Parent root = FXMLLoader.load(getClass().getResource("../../test/folder/BackForTemplate.fxml"));
                    Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();        
          
                  
            }
            else{
            Parent root = FXMLLoader.load(getClass().getResource("../dashboard/Acceuil.fxml"));
                    Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();        
          
            }
        }
        
        else {
            Alert alert  = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("validate username et password");
            alert.setHeaderText(null);
            alert.setContentText("please entrer username valide et password valide");
            alert.showAndWait();
        }
    }    
    @FXML
        private void PasswordOublier(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../MotdePasseOublier/PasswordOublier.fxml"));       
        Parent root = loader.load();   
        login.getScene().setRoot(root);
        
        } 
        
            
        @FXML
        private void Register(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../Register/Register.fxml"));       
        Parent root = loader.load();   
        login.getScene().setRoot(root);
        
        } 
    /*@FXML
    private void AuthUser(ActionEvent event) {

    String domain="http://www.facebook.com/";
    String AppId="389176154894240";
    String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+AppId+"&redirect_uri="+domain+"&scope=user_about_me,"
                + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,"
                + "user_events,user_photos,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,"
                + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
                + "manage_notifications,manage_pages,publish_actions,read_friendlists,read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event";
        
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");    
        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
       while(true){
       if ( !driver.getCurrentUrl().contains("facebook.com"))
        {
           String url = driver.getCurrentUrl();
           accessToken = url.replaceAll(".*#access_Token=(.+)&.*", "$1");
           driver.quit();
           FacebookClient fbclient = new DefaultFacebookClient(accessToken);
           Utilisateur User = fbclient.fetchObject("me",Utilisateur.class);
           message.setText(User.getUsername());
       }
       
       }
    }*/
         
}
