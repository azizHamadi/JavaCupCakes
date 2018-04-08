/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Technique.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entity.Thread;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author aziz
 */
public class ThreadService {
    public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;
    private Statement ste2 ;
    private Statement ste3 ;

    public ThreadService() {
        try {
            ste =con.createStatement();
            ste2 =con.createStatement();
            ste3 =con.createStatement();
            } catch (SQLException ex) {
               Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AjouterThread(Thread thread) throws SQLException{
        String req ="INSERT INTO thread (id,permalink,is_commentable,num_comments) VALUES (?,'',1,1)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, thread.getId());
        pre.executeUpdate();    
    }
    
    public Thread ThreadExiste(String idRec) throws SQLException{
        Thread thread = null ;
        ResultSet rs = ste.executeQuery("select * from thread where id='"+idRec+"'");
        while(rs.next()){
            thread= new Thread(rs.getString("id"));
        }
        return thread ;
    }
    
}
