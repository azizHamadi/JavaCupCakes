/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Promotion;
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
 * @author hamdi fathallah
 */
public class PromotionService {
     public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;
    
    public PromotionService(){
 try {
            ste =con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AjouterPromotion(Promotion p) throws SQLException{
        String req ="INSERT INTO Promotion (tauxPromo, etatPromo ) VALUES (?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDouble(1, p.getTauxPromo());
        pre.setString(2, null);
        //pre.setInt(2, id );
        pre.executeUpdate();
        System.out.println("pourcentage ajouter!");   
    }
     public void ModifierPromotion(Promotion p) throws SQLException{
        String req ="update Promotion set tauxPromo = ? , etatPromo = ? ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDouble(1, p.getTauxPromo());
        pre.setString(2, null );
        
        pre.executeUpdate();
                System.out.println("pourcentage Modifier!");

    }   
        public void SupprimerPromotion(Promotion p) throws SQLException{
        String req ="delete from Promotion where idPromo = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, p.getIdPromo());
        pre.executeUpdate();
    }
 
    public List<Promotion> AfficherPromotion() throws SQLException{
        List<Promotion> promotions = new ArrayList<>();
        ResultSet rs = ste.executeQuery("select * from Promotion");
        while(rs.next())
        {
        promotions.add(new Promotion(rs.getDouble("tauxPromo"))) ;
        }
        return promotions ;
    }

    public Promotion RecherchePromotion(Double taux) throws SQLException{
        ResultSet rs = ste.executeQuery("select * from Promotion where tauxPromo = "+taux);
        Promotion p = new Promotion();
        while(rs.next())
        {
            p.setTauxPromo(rs.getDouble("tauxPromo"));
            p.setIdPromo(rs.getInt("idPromo"));
        }
        return p ;
    }
    
  

       

  

  
   
   
}
