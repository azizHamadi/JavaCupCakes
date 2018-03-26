/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commande;
import Entity.Utilisateur;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author escobar
 */
public class CommandeService {
    
    public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;
    
    public CommandeService(){
    try {
        ste =con.createStatement();
        } catch (SQLException ex) {
           Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AjouterCommande(Commande c) throws SQLException{
        String req ="INSERT INTO Commande (dateCmd,montantCmd,dateLivCmd,addLiv,etatLivCmd,etatCmd,idUser) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
       //String reqe ="SELECT a FROM Utilisateur WHERE a.id = 2" ;
       // PreparedStatement pree = con.prepareStatement(reqe);
        ResultSet rs = ste.executeQuery("SELECT * FROM Utilisateur WHERE id = 2");
        int id = 0;
        while(rs.next())
            id= rs.getInt("id");
        pre.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
        pre.setDouble(2, c.getMontantCmd());
        pre.setDate(3, (Date) c.getDateLivCmd());
        pre.setString(4, c.getAddLiv());
        pre.setString(5, c.getEtatLivCmd());
        pre.setString(6, c.getEtatCmd());
        pre.setInt(7, id );
        pre.executeUpdate();
    }
   
   
}

