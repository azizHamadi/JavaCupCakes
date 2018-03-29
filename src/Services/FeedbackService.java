/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commande;
import Entity.FeedBack;
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

/**
 *
 * @author escobar
 */
public class FeedbackService {
     public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;
    private Statement ste1 ;

    
    public FeedbackService() {
try {
            ste =con.createStatement();
            ste1 =con.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void AjouterFeedback(FeedBack c) throws SQLException{
        String req ="INSERT INTO feed_back (sujet,description,idCmd) VALUES (?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet rs = ste.executeQuery("SELECT * FROM Commande WHERE idCmd = 1");
       int id = 0;
       while(rs.next())
            id= rs.getInt("idCmd");
        pre.setString(1, c.getSujet());
        pre.setString(2, c.getDescription());
        pre.setInt(3, id );
        pre.executeUpdate();
    }
   
    public List<FeedBack> AfficherFeedBack() throws SQLException{
        List<FeedBack> feedback = new ArrayList<>();
        ResultSet listeFeedback = ste.executeQuery("select * from feed_back");
        while(listeFeedback.next())
        {
            Commande Cmd = new Commande();
            ResultSet FeedCmd = ste1.executeQuery("select * from Commande where idCmd = "+ listeFeedback.getInt("idCmd"));
            while(FeedCmd.next())
            {
                Cmd.setIdCmd(FeedCmd.getInt("idCmd"));
                Cmd.setEtatCmd(FeedCmd.getString("etatLivCmd"));
                Cmd.setAddLiv(FeedCmd.getString("addLiv"));
                Cmd.setDateLivCmd(FeedCmd.getDate("dateLivCmd"));
                Cmd.setDateCmd(FeedCmd.getDate("dateCmd"));
                Cmd.setMontantCmd(FeedCmd.getDouble("montantCmd"));
                
            }
            feedback.add(new FeedBack(listeFeedback.getInt("idFeed"),listeFeedback.getString("sujet"), listeFeedback.getString("description"), Cmd));
        }
        return feedback ;
}
}
    
