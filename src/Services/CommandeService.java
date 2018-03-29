/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commande;
import Entity.LineCmd;
import Entity.Produit;
import Entity.Utilisateur;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author escobar
 */
public class CommandeService {
    
    public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;
    private Statement ste1 ;
        private Statement ste2 ;
    private Statement ste3 ;


    public CommandeService(){
 try {
            ste =con.createStatement();
            ste1 =con.createStatement();
                        ste2 =con.createStatement();
            ste3 =con.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AjouterCommande(Commande c,LineCmd linec) throws SQLException{
        String req ="INSERT INTO Commande (dateCmd,montantCmd,dateLivCmd,addLiv,etatLivCmd,etatCmd,idUser) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = ste.executeQuery("SELECT * FROM Utilisateur WHERE id = 1");
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
           ResultSet clef = pre.getGeneratedKeys();
          int cle = 0 ;
          while (clef.next())
              cle=clef.getInt(1);
        String reqln ="INSERT INTO line_cmd (qteAcheter,etatLineCmd,idCmd,idProd) VALUES (?,?,?,?)";
                PreparedStatement preline = con.prepareStatement(reqln);
                
        ResultSet rsp = ste.executeQuery("SELECT * FROM produit WHERE idProd = 1");
       int idp = 0;
       while(rsp.next())
            idp= rsp.getInt("idProd");
       preline.setInt(1, linec.getQteAcheter());
       preline.setString(2, linec.getEtatLineCmd());
       preline.setInt(3, cle);
       preline.setInt(4,idp);
       preline.executeUpdate();


    }
    
    
    public void SupprimerComande(Commande c) throws SQLException{
        String req ="update commande set etatCmd = ? where idCmd = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,"Faux");
        pre.setInt(2, c.getIdCmd());
        pre.executeUpdate();
    }

       public void ModifierEtatCommande(Commande c) throws SQLException{
        String req ="update commande set etatLivCmd = ? where idCmd = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, c.getEtatLivCmd());
        pre.setInt(2, c.getIdCmd());
        
        pre.executeUpdate();
    }
   
        public List<Commande> AfficherCommande() throws SQLException{
        List<Commande> Commandes = new ArrayList<>();
        ResultSet listeCmd = ste.executeQuery("select * from Commande");
        while(listeCmd.next())
        {
            Utilisateur user = new Utilisateur();
            ResultSet cdUser = ste1.executeQuery("select * from utilisateur where id = "+ listeCmd.getInt("idUser"));
            while(cdUser.next())
            {
                user.setId(cdUser.getInt("id"));
                user.setUsername(cdUser.getString("username"));
                user.setEmail(cdUser.getString("email"));
                user.setPhoneNumber(cdUser.getString("phoneNumber"));
                user.setAddresse(cdUser.getString("Addresse"));
                user.setRoles(cdUser.getString("roles"));
            }
            
            List<LineCmd> list = new ArrayList<>();
            ResultSet line = ste2.executeQuery("select * from line_cmd where idCmd = "+ listeCmd.getInt("idCmd"));
            while(line.next())
            {
                LineCmd cd = new LineCmd();
                cd.setQteAcheter(line.getInt("qteAcheter"));
                Produit p = new Produit();
                ResultSet prod = ste3.executeQuery("select * from produit where idProd = "+ line.getInt("idProd"));
                while(prod.next()){
                p.setNomProd(prod.getString("nomProd"));
                }
                list.add(new LineCmd(line.getInt("qteAcheter"),p));
                
            }
            Commandes.add(new Commande(listeCmd.getInt("idCmd"), (Date) listeCmd.getDate("dateCmd"),  (Date) listeCmd.getDate("dateLivCmd"), listeCmd.getString("addLiv"), listeCmd.getString("etatLivCmd"), user, list));
        }
        return Commandes ;
    }
   
}

