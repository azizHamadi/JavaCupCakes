/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commande;
import Entity.LineCmd;
import Entity.Produit;
import Entity.SessionUser;
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
    public int AjouterCommande(Commande c) throws SQLException{
        String req ="INSERT INTO Commande (dateCmd,montantCmd,dateLivCmd,addLiv,etatLivCmd,etatCmd,idUser) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
        pre.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
        pre.setDouble(2, c.getMontantCmd());
        pre.setDate(3, (Date) c.getDateLivCmd());
        pre.setString(4, c.getAddLiv());
        pre.setString(5, c.getEtatLivCmd());
        pre.setString(6, c.getEtatCmd());
        pre.setInt(7, SessionUser.getId() );
        pre.executeUpdate();
        ResultSet  clef = pre.getGeneratedKeys();
        while(clef.next())
          return clef.getInt(1);
        return 0;
    }
    
    public void AjouterLine(LineCmd linec,int cmd) throws SQLException{
         
        String reqln ="INSERT INTO line_cmd (qteAcheter,etatLineCmd,idCmd,idProd) VALUES (?,?,?,?)";
                PreparedStatement preline = con.prepareStatement(reqln);
                
        ResultSet rsp = ste.executeQuery("SELECT * FROM produit WHERE idProd = "+ linec.getProduit().getIdProd());
       int idp = 0;
       while(rsp.next())
            idp= rsp.getInt("idProd");
       preline.setInt(1, linec.getQteAcheter());
       preline.setString(2, linec.getEtatLineCmd());
       preline.setInt(3, cmd);
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
       /*"select * from produit ,promotion , line_cmd 
       where produit.idProd=line_cmd.idProd and Commande.idCmd=line_cmd.idCmd and produit.idUser=" 
       + idUser);
*/
        public List<Commande> AfficherCommandeBack(int idUser) throws SQLException{
        List<Commande> Commandes = new ArrayList<>();
        String v = "Vrai";
            try {

        ResultSet listeCmd = ste.executeQuery("select * from produit ,commande , line_cmd where produit.idProd=line_cmd.idProd and Commande.idCmd=line_cmd.idCmd and produit.idUser="  + idUser);
        while(listeCmd.next())
        {
            Utilisateur user = new Utilisateur();
            ResultSet cdUser = ste1.executeQuery("select * from utilisateur where id = "+ idUser);
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
           Commandes.add(new Commande(listeCmd.getInt("idCmd"), (Date) listeCmd.getDate("dateCmd"),(double)listeCmd.getDouble("montantCmd") , (Date) listeCmd.getDate("dateLivCmd"), listeCmd.getString("addLiv"), listeCmd.getString("etatLivCmd"), user, list));
        }}catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);}
         
        return Commandes ;
    }
   public List<Commande> AfficherCommandeFront(int idUser) throws SQLException{
        List<Commande> Commandes = new ArrayList<>();
        String v = "Vrai";
            try {

        ResultSet listeCmd = ste.executeQuery("select * from commande where idUser="  + idUser);
        while(listeCmd.next())
        {
            Utilisateur user = new Utilisateur();
            ResultSet cdUser = ste1.executeQuery("select * from utilisateur where id = "+ idUser);
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
           Commandes.add(new Commande(listeCmd.getInt("idCmd"), (Date) listeCmd.getDate("dateCmd"),(double)listeCmd.getDouble("montantCmd") , (Date) listeCmd.getDate("dateLivCmd"), listeCmd.getString("addLiv"), listeCmd.getString("etatLivCmd"), user, list));
        }}catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);}
         
        return Commandes ;
    }
        
        public List<LineCmd> AfficherLineCommande(int c) throws SQLException{
        List<LineCmd> line = new ArrayList<>();
        String v = "Vrai";
        ResultSet listeline = ste.executeQuery("select * from line_cmd where idCmd = "+c);
        while(listeline.next())
        {
           
                LineCmd cd = new LineCmd();
                cd.setQteAcheter(listeline.getInt("qteAcheter"));
                Produit p = new Produit();
                ResultSet prod = ste3.executeQuery("select * from produit where idProd = "+ listeline.getInt("idProd"));
                while(prod.next()){
                p.setNomProd(prod.getString("nomProd"));
                p.setImageprod(prod.getString("imageprod"));
                }
                line.add(new LineCmd(listeline.getInt("qteAcheter"),p));
                
            }
        return line ;
    }
   
}

