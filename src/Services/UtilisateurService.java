/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Utilisateur;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aziz
 */
public class UtilisateurService {
    public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;
    private Statement ste2;
    private Statement ste3;

    public UtilisateurService() {
        try {
            ste2 =con.createStatement();
            ste3 =con.createStatement();
            ste =con.createStatement();
            } catch (SQLException ex) {
               Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Utilisateur Login(String username) throws SQLException
    {
        Utilisateur user = null;
        ResultSet rsUtilisateur = ste.executeQuery("select * from utilisateur where username='"+username+"'");
        while(rsUtilisateur.next())
        {
            user = new Utilisateur(rsUtilisateur.getInt("id"), username, rsUtilisateur.getString("email"), rsUtilisateur.getString("password"), rsUtilisateur.getString("roles"), rsUtilisateur.getString("phoneNumber"), rsUtilisateur.getString("addresse"));
        }
        return user ;
    }
    
    
}
