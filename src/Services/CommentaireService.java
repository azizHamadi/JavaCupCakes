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
        String req ="INSERT INTO commentaire (idUser,idRec,body,depth,created_at,state,ancestors) VALUES (?,?,?,?,NOW(),?,'')";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, commentaire.getIdUser().getId());
        pre.setInt(2, Integer.parseInt(commentaire.getIdRec().getId()));
        pre.setString(3, commentaire.getBody());
        pre.setInt(4, commentaire.getDepth());
        pre.setInt(5, commentaire.getState());
        pre.executeUpdate();
    }

    public void AjouterReplayCommentaire(Commentaire commentaire) throws SQLException{
        String req ="INSERT INTO commentaire (idUser,idRec,body,ancestors,depth,created_at,state) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, commentaire.getIdUser().getId());
        pre.setInt(2, Integer.parseInt(commentaire.getIdRec().getId()));
        pre.setString(3, commentaire.getBody());
        pre.setString(4, commentaire.getAncestors());
        pre.setInt(5, commentaire.getDepth());
        pre.setDate(6, (Date) commentaire.getCreatedAt());
        pre.setInt(7, commentaire.getState());
        pre.executeUpdate();
    }

    public void UpdateCommentaire(Commentaire commentaire) throws SQLException{
        String req ="update commentaire set body = ? , created_at = ? where idCmnt = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, commentaire.getBody());
        pre.setDate(2, new Date(commentaire.getCreatedAt().getTime()));
        pre.setInt(3, commentaire.getIdCmnt());
        pre.executeUpdate();
    }

    public void SupprimerCommentaire(Commentaire commentaire) throws SQLException{
        String req ="update commentaire set state = ? where idCmnt = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, commentaire.getState());
        pre.setInt(2, commentaire.getIdCmnt());
        pre.executeUpdate();
    }
    
    public List<Commentaire> AfficherCommentaire() throws SQLException{
        List<Commentaire> commentaires = new ArrayList<>();
        ResultSet rsCommentaire = ste.executeQuery("select * from commentaire");

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
    
}
