/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Technique.DataSource;
import Entity.Formation;
import Entity.TypeFormation;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author FERIEL FADHLOUN
 */
public class ServiceFormation {
    Connection conn=DataSource.getInstance().getCon();
    Statement st=null;
    File file;
    FileInputStream filestream;
    private Connection con=DataSource.getInstance().getCon();
    private Statement ste2 ;
    private Statement ste3 ;
    private ObservableList<Formation> ListeFor ;

    public ObservableList<Formation> getListeFor() throws SQLException {
                return FXCollections.observableArrayList(ListeFormations());
    }
    //pour laffichage du combobox
    public List<Formation> ListeFormations() throws SQLException
    {
        List<Formation> listeformation=new ArrayList<Formation>();
        ResultSet rsformation=st.executeQuery("SELECT * FROM formation ");
     
        while(rsformation.next())
        {   
          listeformation.add(new Formation(rsformation.getInt("idFor"),rsformation.getString("nomFor")) );
            
        }
        
        return listeformation;
       
    }
    public void setListeFor(ObservableList<Formation> ListeFor) {
        this.ListeFor = ListeFor;
    }
    final ObservableList<Formation> listeob=FXCollections.observableArrayList();
    public ServiceFormation() 
    {
        try 
        {
            st=con.createStatement();
             ste2 =con.createStatement();
            ste3 =con.createStatement();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }
    public void insertFormation(Formation f)
    {
        String sql="INSERT INTO Formation(nomFor,place,etatFor,descriptionFor,dateFor,imageForm,idUser,idTypeFor)VALUES(?,?,?,?,?,?,?,?);";
        try 
        {
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,f.getNomFor());
            ps.setString(2,f.getPlace());
            ps.setString(3,"en cours");
            ps.setString(4,f.getDescriptionFor());
            ps.setDate(5,(Date) f.getDateFor());
            ps.setString(6,f.getImageform());
            ps.setString(7,null);
            ps.setInt(8,f.getIdTypeFor().getIdTypeFor());
            
            ps.executeUpdate();
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public ObservableList<Formation> AfficherListeFormation() throws SQLException
    {
        ResultSet rsformation=st.executeQuery("SELECT * FROM Formation where etatFor='en cours'");
     
        while(rsformation.next())
        {   
            Formation formation=new Formation();
            formation.setIdFor(rsformation.getInt("idFor"));
            formation.setNomFor(rsformation.getString("nomFor"));
            formation.setPlace(rsformation.getString("place"));
            formation.setDescriptionFor(rsformation.getString("descriptionFor"));
            formation.setDateFor(rsformation.getDate("dateFor"));
            formation.setImageform(rsformation.getString("imageform"));
            ResultSet rstype= ste2.executeQuery("select * from type_formation where idTypeFor="+ rsformation.getInt("idTypeFor"));
            TypeFormation typeformation=new TypeFormation();
            while(rstype.next())
            {
                typeformation.setIdTypeFor(rsformation.getInt("idTypeFor"));
                typeformation.setNomTypeFor(rstype.getString("nomTypeFor"));
                
            }
            formation.setIdTypeFor(typeformation);
            listeob.add(formation);
        }
        return listeob;
    } 
    public void ModifierFormation(Formation f,int idFor) throws SQLException{
        System.out.println("UPDATE Formation SET nomFor= "+f.getNomFor()+",place="+f.getPlace()+",idTypeFor="+f.getIdTypeFor().getIdTypeFor()+",DescriptionFor="+f.getDescriptionFor()+",dateFor="+ f.getDateFor()+"where idFor="+idFor);
            String req ="UPDATE Formation SET nomFor= ?,place=?,idTypeFor=?,DescriptionFor=?,dateFor=? where idFor="+idFor;
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1,f.getNomFor());
            ps.setString(2,f.getPlace());
            ps.setInt(3, f.getIdTypeFor().getIdTypeFor());
            ps.setString(4,f.getDescriptionFor());
            ps.setDate(5,(Date) f.getDateFor());
            
            ps.executeUpdate();
    }

    public ObservableList<Formation> AfficherListTableau() throws SQLException
    {
        ListView list=new ListView(listeob);
        String req="SELECT * FROM Formation where nomFor=?";
        PreparedStatement ps=conn.prepareStatement(req);
        ps.setString(1, (String) list.getSelectionModel().getSelectedItem());
        ResultSet rsformation=ps.executeQuery();
     
        while(rsformation.next())
        {   
            Formation formation=new Formation();
            formation.setNomFor(rsformation.getString("nomFor"));
            formation.setPlace(rsformation.getString("place"));
            formation.setDescriptionFor(rsformation.getString("descriptionFor"));
            formation.setDateFor(rsformation.getDate("dateFor"));
            
            listeob.add(formation);
        }
        return listeob;
    }
    
    public void SupprimerFormation(Formation f,int idFor) throws SQLException{
            String req ="UPDATE Formation SET etatFor ='finie' where idFor ="+idFor;
            PreparedStatement ps = con.prepareStatement(req);
            
            ps.executeUpdate();
    }

    public ObservableList<Formation> SearchListeFormation(String nomformation) throws SQLException
    {
       
        ResultSet rsformation=st.executeQuery("SELECT * FROM Formation WHERE etatFor='en cours' AND nomFor LIKE '%"+nomformation+"%'");
     
        while(rsformation.next())
        {   
            Formation formation=new Formation();
            formation.setIdFor(rsformation.getInt("idFor"));
            formation.setNomFor(rsformation.getString("nomFor"));
            formation.setPlace(rsformation.getString("place"));
            formation.setDescriptionFor(rsformation.getString("descriptionFor"));
            formation.setDateFor(rsformation.getDate("dateFor"));
            formation.setImageform(rsformation.getString("imageform"));
            
            listeob.add(formation);
        }
        return listeob;
    } 

    public List<Formation> AfficherListeFormationParTypeFor(int idtypefor) throws SQLException
    {
        List<Formation> ListeForPartype = new ArrayList<>();
        ResultSet rsformation=st.executeQuery("SELECT * FROM Formation where etatFor='en cours' and idTypeFor="+idtypefor);
     
        while(rsformation.next())
        {   
            Formation formation=new Formation();
            formation.setIdFor(rsformation.getInt("idFor"));
            formation.setNomFor(rsformation.getString("nomFor"));
            formation.setPlace(rsformation.getString("place"));
            formation.setDescriptionFor(rsformation.getString("descriptionFor"));
            formation.setDateFor(rsformation.getDate("dateFor"));
            formation.setImageform(rsformation.getString("imageform"));
            ResultSet rstype= ste2.executeQuery("select * from type_formation where idTypeFor="+ rsformation.getInt("idTypeFor"));
            TypeFormation typeformation=new TypeFormation();
            while(rstype.next())
            {
                //formation.setIdFor(rsformation.getInt("idTypeFor"));
                typeformation.setNomTypeFor(rstype.getString("nomTypeFor"));
                
            }
            formation.setIdTypeFor(typeformation);
            ListeForPartype.add(formation);
        }
        return ListeForPartype;
    } 
    
    //count le nobre de formation par rapport au type de formation
    public int CountFormationParTypeFor(int idfor) throws SQLException{
        int count = 0 ;
        ResultSet rs = st.executeQuery("select count(*) from formation where idTypeFor="+idfor);
        while(rs.next())
            count = rs.getInt(1);
        return count;

    }
    
    public int ReturnIdtypeForFromBynom(String nomtypefor) throws SQLException
    {
        int idtypefor=0;
        String req="SELECT idTypeFor FROM type_formation where nomTypeFor='"+nomtypefor+"'";
        PreparedStatement ps=conn.prepareStatement(req);
       
        ResultSet rstypeformation=ps.executeQuery();
     
        while(rstypeformation.next())
        {  
            TypeFormation formation=new TypeFormation();
            formation.setIdTypeFor(rstypeformation.getInt("idTypeFor"));
            idtypefor=formation.getIdTypeFor();
        }
        
        return idtypefor;
         
    }
    
    //si date inferieur a date actuelle twali etat finie
   /* public void ModifierEtatFormation(Date datefor) throws SQLException{
        System.out.println("modifier date formation");
        System.out.println("UPDATE Formation SET etatFor ='finie' where dateFor='"+datefor+"'");
        String req ="UPDATE Formation SET etatFor ='finie' where dateFor='"+datefor+"'";
        PreparedStatement ps = con.prepareStatement(req);         
        ps.executeUpdate();
    }*/
}
