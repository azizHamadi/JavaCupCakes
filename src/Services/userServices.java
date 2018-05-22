/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.SentMail;
import Entity.Utilisateur;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author pc_roua
 */
public class userServices {

    public Connection con = DataSource.getInstance().getCon();
    private Statement ste;
    Utilisateur User = new Utilisateur();

    public userServices() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(userServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void AjouterUtilisateur(Utilisateur User) throws SQLException, MessagingException {

        String req = "INSERT INTO utilisateur (username,email,password,roles,phoneNumber,Addresse,nom,prenom,username_canonical,last_login,enabled,email_canonical) VALUES (?,?,?,?,?,?,?,?,?,NOW(),1,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, User.getUsername());
        pre.setString(2, User.getEmail());
        String pw = BCrypt.hashpw(User.getPassword(), BCrypt.gensalt(13));
        pre.setString(3, pw.substring(0, 2) + "y" + pw.substring(3));
        pre.setString(4, User.getRoles());
        pre.setString(5, User.getPhoneNumber());
        pre.setString(6, User.getAddresse());
        pre.setString(7, User.getNom());
        pre.setString(8, User.getPrenom());
        pre.setString(9, User.getUsername());
        pre.setString(10, User.getEmail());
              //  mail();

        pre.executeUpdate();

    }

    public Boolean verif_username(String username) {

        try {
            Statement ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from utilisateur   where username ='" + username + "'");

            while (rs.next()) {

                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(userServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Utilisateur verif_email(String email) {

        Utilisateur user = null ;
        try {
            Statement ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from utilisateur where email='" + email + "'");
            System.out.println(email);
            while (rs.next()) {
                user = new Utilisateur(rs.getInt("id"));
                user.setEmail(email);
            }

        } catch (SQLException ex) {
            Logger.getLogger(userServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public Utilisateur Login(String username) throws SQLException {
        Utilisateur user = null;
        ResultSet rsUtilisateur = ste.executeQuery("select * from utilisateur where username='" + username + "'");
        while (rsUtilisateur.next()) {
            user = new Utilisateur(rsUtilisateur.getInt("id"), rsUtilisateur.getString("email"), username, rsUtilisateur.getString("password"), rsUtilisateur.getString("roles"), rsUtilisateur.getString("phoneNumber"), rsUtilisateur.getString("addresse"), rsUtilisateur.getString("nom"), rsUtilisateur.getString("prenom"));
            user.setImageProfil(rsUtilisateur.getString("imageProfil"));
        }
        return user;
    }

    public List<Utilisateur> listClient(String role) throws SQLException {
        List<Utilisateur> Client = new ArrayList<>();
        Utilisateur u = new Utilisateur();
        String req = "SELECT * FROM utilisateur where roles not like '%ROLE_SUPER_ADMIN%' ";
        if (!role.equals("")) {
            req += "and roles='" + role + "'";
        }
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            Client.add(new Utilisateur(rs.getInt("id"), rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("roles"), rs.getString("phoneNumber"), rs.getString("Addresse"), rs.getString("nom"), rs.getString("prenom"),rs.getString("imageProfil")));
        }
        return Client;

    }

    public boolean enabledUtilisateur(int id, int enabled) throws SQLException {

        String req = "Update  utilisateur Set enabled = " + enabled + " where id =?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);

        pre.executeUpdate();
        return true;

    }

    public boolean PasswordUtilisateur(Utilisateur user) throws SQLException, MessagingException {

        String req = "Update utilisateur Set password =? where id =?";
        PreparedStatement pre = con.prepareStatement(req);
        String pw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(13));
        pre.setString(1, pw.substring(0, 2) + "y" + pw.substring(3));
        
        pre.setInt(2, user.getId());

        System.out.println(pw.substring(0, 2) + "y" + pw.substring(3));
        pre.executeUpdate();
        return true;

    }

    public Utilisateur getUserById(int id) {
        try {
            User = null;
            String req = "SELECT * FROM utilisateur WHERE id=?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {

                User = new Utilisateur(rs.getInt("id"), rs.getString("email"), rs.getString("username"), rs.getString("nom"), rs.getString("prenom"), rs.getString("Addresse"), rs.getString("phoneNumber"),rs.getString("imageProfil"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(userServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return User;
    }

    public void ModifierLineUser(Utilisateur User) throws SQLException {
        String req = "update utilisateur set nom =?, prenom=?, Addresse=? , phoneNumber = ? where id=" + User.getId();
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, User.getNom());
        pre.setString(2, User.getPrenom());
        pre.setString(3, User.getAddresse());
         pre.setString(4, User.getPhoneNumber());

        pre.executeUpdate();
        System.out.println("User est modifier avec succee!");
    }

    public void mail(Utilisateur user) throws AddressException, MessagingException {
        String USER_NAME = "hamdi.fathallah.1@esprit.tn";
        String PASSWORD = "hamdifathallah";
        String RECIPIENT = user.getEmail();

        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = {RECIPIENT}; // list of recipient email addresses
        String subject = "Confirmation de compte";
        String body = "Votre token de confirmation est : "+user.getConfirmationToken();
        SentMail send = new SentMail();
        send.sendFromGMail(from, pass, to, subject, body);
    }
    
    public List<Utilisateur> RechercheParNomUtilisateur(String nom) throws SQLException{
        List<Utilisateur> listU = new ArrayList<>();
        ResultSet rsUser = ste.executeQuery("select * from utilisateur where username like '%"+nom+"%'");
       while(rsUser.next())
            {
                Utilisateur user = new Utilisateur();
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
                listU.add(user);
            }
            
            
        
        return listU;
    }
     public void AjouterImage(Utilisateur user) throws SQLException, MessagingException {

        String req = "update  utilisateur set imageProfil=? where id=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,user.getImageProfil());
          pre.setInt(2,user.getId());
              //  mail();

        pre.executeUpdate();

    }
     
     public String generate(int length)
        {
	    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    String pass = "";
	    for(int x=0;x<length;x++)
	    {
	       int i = (int)Math.floor(Math.random() * 62);
	       pass += chars.charAt(i);
	    }
	    System.out.println(pass);
	    return pass;
        }
     
    public void ConfirmerPassword(Utilisateur user) throws SQLException, MessagingException{
        String req = "update utilisateur set confirmation_token=? where id=?";
        PreparedStatement pre = con.prepareStatement(req);
        user.setConfirmationToken(generate(4));
        pre.setString(1,user.getConfirmationToken() );
        pre.setInt(2,user.getId());
        pre.executeUpdate();
        mail(user);
    }
    
    public boolean verif_token(Utilisateur user) throws SQLException
    {
        ResultSet rsUser = ste.executeQuery("select * from utilisateur where id ="+user.getId() +" and confirmation_token='"+user.getConfirmationToken()+"'");
        if(rsUser.next())
            return true;
        return false;
    }
   
}
