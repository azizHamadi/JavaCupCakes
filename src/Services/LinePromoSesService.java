/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Linepromoses;
import Entity.Promotion;
import Entity.Session;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hamdi fathallah
 */
public class LinePromoSesService {
        public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;
    private Statement ste1 ;
    private Statement ste2 ;
        private ObservableList<Linepromoses> Listlinepromoses;

    
    public LinePromoSesService(){
 try {
            ste =con.createStatement();
            ste1 =con.createStatement();
            ste2 =con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(LinePromoSesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AjouterLinePromoSes(Linepromoses p) throws SQLException{
        String req ="INSERT INTO linepromoses (dateDeb,dateFin,idSes,idPromo) VALUES (?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDate(1, new Date(p.getDateDeb().getTime()));
        pre.setDate(2, new Date(p.getDateFin().getTime()));
        pre.setInt(3,p.getIdSes().getIdSes() );
        pre.setInt(4,p.getIdPromo().getIdPromo() );
        //pre.setInt(2, id );
        pre.executeUpdate();
        System.out.println("session ajouter sous promotion avec succee!");
        
    }
    
    public void ModifierLinePromoSes(Linepromoses p , int id) throws SQLException{
        String req ="update linepromoses set dateDeb = ? , dateFin = ? , idSes = ? , idPromo = ? where idLine=" + id;
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDate(1, new Date(p.getDateDeb().getTime()));
        pre.setDate(2, new Date(p.getDateFin().getTime()));
        pre.setInt(3,p.getIdSes().getIdSes() );
        pre.setInt(4,p.getIdPromo().getIdPromo() );
        pre.executeUpdate();
        System.out.println("promotion de session est modifier avec succee!");

    }  
    public void SupprimerLinePromoSes(Linepromoses p) throws SQLException{
        String req ="delete from linepromoses where idLine = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, p.getIdLine());
        pre.executeUpdate();
        System.out.println("promotion de session est supprimer avec succee!");

    }
    
   public List<Linepromoses> AfficherLinePromoSes() throws SQLException{
        List<Linepromoses> LinePromosess = new ArrayList<>();
        ResultSet rs = ste.executeQuery("select * from linepromoses");
        while(rs.next())
        {
        ResultSet rsp = ste1.executeQuery("select * from promotion where idPromo = "+rs.getInt("idPromo"));
        Promotion p = new Promotion();
        while(rsp.next())
        {
            p.setIdPromo(rsp.getInt("idPromo"));
            p.setTauxPromo(rsp.getDouble("tauxPromo"));
        }
        ResultSet rspromo = ste2.executeQuery("select * from session where idSes = "+rs.getInt("idSes"));
        Session session = new Session();
        while (rspromo.next())
        {
            session.setIdSes(rspromo.getInt("idSes"));
            session.setNomSes(rspromo.getString("nomSes"));
        }
        LinePromosess.add(new Linepromoses(rs.getInt("idLine") ,rs.getDate("dateDeb"), rs.getDate("dateFin") , session , p)) ;
        }
        return LinePromosess ;
    }
    public ObservableList<Linepromoses> getListelinepromoses() throws SQLException {
        return FXCollections.observableArrayList(AfficherLinePromoSes());
    }
        public void setListelinepromo(ObservableList<Linepromoses> Listlinepromoses) {
        this.Listlinepromoses = Listlinepromoses;
    }
    
}
