/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commentaire;
import Entity.Utilisateur;
import Technique.DataSource;
import java.sql.Connection;
import Entity.Thread;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aziz
 */
public class CommentaireService {
    
    public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;
    private Statement ste2 ;
    private Statement ste3 ;

    public CommentaireService() {
        try {
            ste =con.createStatement();
            ste2 =con.createStatement();
            ste3 =con.createStatement();
            } catch (SQLException ex) {
               Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AjouterCommentaire(Commentaire commentaire) throws SQLException{
        String req ="INSERT INTO commentaire (idUser,idRec,body,depth,created_at,state,ancestors) VALUES (?,?,?,?,NOW(),0,'')";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, commentaire.getIdUser().getId());
        pre.setInt(2, Integer.parseInt(commentaire.getIdRec().getId()));
        pre.setString(3, commentaire.getBody());
        pre.setInt(4, commentaire.getDepth());
        pre.executeUpdate();
    }

    public void AjouterReplayCommentaire(Commentaire commentaire) throws SQLException{
        String req ="INSERT INTO commentaire (idUser,idRec,body,ancestors,depth,created_at,state) VALUES (?,?,?,?,?,Now(),0)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, commentaire.getIdUser().getId());
        pre.setInt(2, Integer.parseInt(commentaire.getIdRec().getId()));
        pre.setString(3, commentaire.getBody());
        pre.setString(4, commentaire.getAncestors());
        pre.setInt(5, commentaire.getDepth());
        pre.executeUpdate();
    }

    public void UpdateCommentaire(Commentaire commentaire) throws SQLException{
        String req ="update commentaire set body = ? where idCmnt = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, commentaire.getBody());
        pre.setInt(2, commentaire.getIdCmnt());
        pre.executeUpdate();
    }

    public void SupprimerCommentaire(Commentaire commentaire) throws SQLException{
        String req ="update commentaire set state = ? where idCmnt = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, commentaire.getState());
        pre.setInt(2, commentaire.getIdCmnt());
        pre.executeUpdate();
    }
    
    public Commentaire findByIdCom(int idCom) throws SQLException{
        Commentaire c = new Commentaire();
        ResultSet rsCommentaire = ste.executeQuery("select * from commentaire where idCmnt="+idCom);
        while(rsCommentaire.next()){
            ResultSet rsThread = ste2.executeQuery("select * from thread where id="+rsCommentaire.getString("idRec"));
            Thread th = new Thread();
            while (rsThread.next())
                th = new Thread(rsThread.getString("id"), rsThread.getString("permalink"), true, idCom);
            Utilisateur u = new Utilisateur();
            ResultSet rsUser = ste2.executeQuery("select * from utilisateur where id="+rsCommentaire.getString("idUser"));
            while(rsUser.next())
                u = new Utilisateur(rsUser.getInt("id"), rsUser.getString("username"), rsUser.getString("username_Canonical"), rsUser.getString("email"), rsUser.getString("email_Canonical"), true, rsUser.getString("password"), rsUser.getString("roles"));
            c = new Commentaire(idCom, rsCommentaire.getString("body"), rsCommentaire.getString("ancestors"), rsCommentaire.getInt("depth"), rsCommentaire.getDate("created_at"), rsCommentaire.getInt("state"), th, u);}
        return c ;
    }
    
    public List<Commentaire> findAncestors(String ancestor) throws SQLException{
        List<Commentaire> listC = new ArrayList<>();
        ResultSet rsCommentaire = ste.executeQuery("select * from commentaire where ancestors='"+ancestor+"' and state=0");
        while(rsCommentaire.next()){
            ResultSet rsThread = ste2.executeQuery("select * from thread where id="+rsCommentaire.getString("idRec"));
            Thread th = new Thread();
            while (rsThread.next())
                th = new Thread(rsThread.getString("id"), rsThread.getString("permalink"), true, rsCommentaire.getInt("idCmnt"));
            Utilisateur u = new Utilisateur();
            ResultSet rsUser = ste2.executeQuery("select * from utilisateur where id="+rsCommentaire.getString("idUser"));
            while(rsUser.next())
                u = new Utilisateur(rsUser.getInt("id"), rsUser.getString("username"), rsUser.getString("username_Canonical"), rsUser.getString("email"), rsUser.getString("email_Canonical"), true, rsUser.getString("password"), rsUser.getString("roles"));
            listC.add(new Commentaire(rsCommentaire.getInt("idCmnt"), rsCommentaire.getString("body"), rsCommentaire.getString("ancestors"), rsCommentaire.getInt("depth"), rsCommentaire.getDate("created_at"), rsCommentaire.getInt("state"), th, u));}
        return listC;
    }
    
    public List<Commentaire> AfficherCommentaire(String idRec) throws SQLException{
        List<Commentaire> commentaires = new ArrayList<>();
        ResultSet rsCommentaire = ste.executeQuery("select * from commentaire where idRec='"+idRec+"' and ancestors='' and state=0 order by idCmnt DESC");

        while(rsCommentaire.next())
        {
            Commentaire com = new Commentaire();
            Utilisateur user = new Utilisateur();
            Thread Th = new Thread();

            com.setAncestors(rsCommentaire.getString("ancestors"));
            com.setBody(rsCommentaire.getString("body"));
            com.setCreatedAt(rsCommentaire.getDate("created_at"));
            com.setDepth(rsCommentaire.getInt("depth"));
            com.setIdCmnt(rsCommentaire.getInt("idCmnt"));
            com.setState(rsCommentaire.getInt("state"));
            ResultSet rsUser = ste3.executeQuery("select * from utilisateur where id="+ rsCommentaire.getInt("idUser"));
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }
            ResultSet rsThread = ste2.executeQuery("select * from Thread where id = "+ rsCommentaire.getInt("idRec"));
            while(rsThread.next())
            {
                Th.setId(rsThread.getString("id"));
                Th.setIsCommentable(rsThread.getBoolean("is_commentable"));
                Th.setLastCommentAt(rsThread.getDate("last_comment_at"));
                Th.setNumComments(rsThread.getInt("num_comments"));
                Th.setPermalink(rsThread.getString("permalink"));
            }
            com.setIdRec(Th);
            com.setIdUser(user);
            commentaires.add(com);
        }
        
        return commentaires ;
    }
    
    public int CountCommentaireParRecette(int idRec) throws SQLException{
        int count = 0 ;
        ResultSet rsCommentaire = ste.executeQuery("select count(*) from commentaire where idRec="+idRec);
        while(rsCommentaire.next())
        {
            count = rsCommentaire.getInt(1);
        }
        return count ;
    }
    
}
