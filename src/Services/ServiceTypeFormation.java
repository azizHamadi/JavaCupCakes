/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Technique.DataSource;
import Entity.TypeFormation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author FERIEL FADHLOUN
 */
public class ServiceTypeFormation {
        private Statement st;
         private Statement st1;
        Connection conn=DataSource.getInstance().getCon();
        private ObservableList<TypeFormation> ListeTypeFor ;
        
        public ServiceTypeFormation() {
         try {
            st =conn.createStatement();
            st1 =conn.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();  
             System.out.println("erreur");}
    }

    public ObservableList<TypeFormation> getListeTypeFor() throws SQLException {
              
                return FXCollections.observableArrayList(ListeTypeFormations());
            
    }

    public void setListeTypeFor(ObservableList<TypeFormation> ListeTypeFor) {
        this.ListeTypeFor = ListeTypeFor;
    }

    public List<TypeFormation> ListeTypeFormations() throws SQLException
    {
        List<TypeFormation> listetypeformation=new ArrayList<TypeFormation>();
        ResultSet rsformation=st.executeQuery("SELECT * FROM type_formation");
     
        while(rsformation.next())
        {   
          listetypeformation.add(new TypeFormation(rsformation.getInt("idTypeFor"),rsformation.getString("nomTypeFor")) );
            
        }
        
        return listetypeformation;
       
    }

    public ServiceTypeFormation(ObservableList<TypeFormation> ListeTypeFor) {
        this.ListeTypeFor = ListeTypeFor;
    }

    //pour chercher si le type formation existe deja ou non 
    public TypeFormation rechercheTypeFormation(String nomtypefor) throws SQLException{
        TypeFormation typef = null ; 
        ResultSet rs = st1.executeQuery("select * from type_formation where nomTypeFor ='"+nomtypefor+"'");
        while(rs.next())
            {
                typef = new TypeFormation(rs.getInt("idTypeFor"), rs.getString("nomTypeFor"));
            }
        return typef ;
    }
    
    public void AjouterTypeFormation(TypeFormation typeformation) throws SQLException{
        String req ="INSERT INTO type_formation (nomTypeFor) VALUES (?)";
        PreparedStatement pre = conn.prepareStatement(req);
        pre.setString(1, typeformation.getNomTypeFor());
        pre.executeUpdate();
        setListeTypeFor(FXCollections.observableArrayList(ListeTypeFormations()));
    }
}
