/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Note;
import Entity.Recette;
import Entity.Utilisateur;
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
 * @author aziz
 */
public class NoteService {
    public Connection con = DataSource.getInstance().getCon();
    private Statement ste ;
    private Statement ste2 ;
    private Statement ste3 ;

    public NoteService() {
        try {
            ste =con.createStatement();
            ste2 =con.createStatement();
            ste3 =con.createStatement();
            } catch (SQLException ex) {
               Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public double moyenneRecette(int rec) throws SQLException{
        double note = 0 ;
        ResultSet rs = ste.executeQuery("select avg(note) from note where idRec="+rec);
        while(rs.next())
            note=rs.getDouble(1);
        return note;
    }
    
    public void AjouterNote(Note note) throws SQLException{
        String req ="INSERT INTO note (note,idRec,dateNote,etatNote,idUser) VALUES (?,?,NOW(),'oui',?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDouble(1, note.getNote());
        pre.setInt(2, note.getRecette().getIdRec());
        pre.setInt(3, note.getUtilisateur().getId());
        pre.executeUpdate();
    }
    
    public void ModifierNote(Note note) throws SQLException{
        String req ="update note set note = ? ,dateNote = NOW() where idUser = ? and idRec = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDouble(1, note.getNote());
        pre.setInt(2, note.getUtilisateur().getId());
        pre.setInt(3, note.getRecette().getIdRec());
        pre.executeUpdate();
    }
    
    public void SupprimerNote(Note note) throws SQLException{
        String req ="delete from note where idUser = ? and idRec = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, note.getUtilisateur().getId());
        pre.setInt(2, note.getRecette().getIdRec());
        pre.executeUpdate();
    }
    
    public List<Note> AfficherNote() throws SQLException{
        List<Note> Notes = new ArrayList<>();
        ResultSet rsNote = ste.executeQuery("select * from note");

        while(rsNote.next())
        {
            Note note = new Note();
            Utilisateur user = new Utilisateur();
            Recette recette = new Recette();
            note.setNote(rsNote.getDouble("note"));
            note.setNote(rsNote.getDouble("note"));
            note.setEtatNote(rsNote.getString("etatNote"));
            ResultSet rsUser = ste3.executeQuery("select * from utilisateur where id="+ rsNote.getInt("idUser"));
            while(rsUser.next())
            {
                user.setId(rsUser.getInt("id"));
                user.setUsername(rsUser.getString("username"));
                user.setEmail(rsUser.getString("email"));
                user.setPhoneNumber(rsUser.getString("phoneNumber"));
                user.setAddresse(rsUser.getString("Addresse"));
                user.setRoles(rsUser.getString("roles"));
            }
            ResultSet rsRecette = ste2.executeQuery("select * from recette where id = "+ rsNote.getInt("idRec"));
            while(rsRecette.next())
            {
                recette.setDateRec(rsRecette.getDate("dateRec"));
                recette.setDescriptionRec(rsRecette.getString("descriptionRec"));
                recette.setEtatRec(rsRecette.getString("etatRec"));
                recette.setImageRec(rsRecette.getString("imageRec"));
                recette.setNomRec(rsRecette.getString("nomRec"));
                recette.setIdRec(rsRecette.getInt("idRec"));
            }
            note.setRecette(recette);
            note.setUtilisateur(user);
            Notes.add(note);
        }
        return Notes ;
    }
    
    public Note NoteExiste(Note note) throws SQLException{
        Note Rnote = null ;
        ResultSet rsNote = ste.executeQuery("select * from note where idUser="+note.getUtilisateur().getId()+" and idRec="+note.getRecette().getIdRec());
        while(rsNote.next())
        {
            System.out.println(rsNote.getDouble("note"));
            Rnote = new Note(rsNote.getDouble("note"), new Recette(rsNote.getInt("idRec")), new Utilisateur(rsNote.getInt("idUser")));
            return Rnote;
        }
        return Rnote;
    }

}
