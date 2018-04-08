/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Technique.DataSource;
import Entity.TypeFormation;
import java.sql.Connection;
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
        Connection conn=DataSource.getInstance().getCon();
        private ObservableList<TypeFormation> ListeTypeFor ;
        
        public ServiceTypeFormation() {
         try {
            st =conn.createStatement();
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

    
    
}
