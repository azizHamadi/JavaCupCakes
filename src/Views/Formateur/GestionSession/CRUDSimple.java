/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Formateur.GestionSession;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author FERIEL FADHLOUN
 */
public class CRUDSimple extends Application{

     @Override
    public void start(Stage primaryStage) throws Exception {
        
        Parent root;
        try 
        {
            root = FXMLLoader.load(getClass().getResource("CRUDSessions.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Gestion Session");
            primaryStage.setScene(scene);
            primaryStage.show();
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       /* Utilisateur idUser = new Utilisateur(1,"fafi", "feriel.fadhloun@esprit.tn", "fafi", "fafi", "5555", "fafi");
        TypeFormation typefor=new TypeFormation(1,"gateau");
        Formation formation1=new Formation("fafi","fafi","fafi","fafi", new Date(65165169684L),idUser, typefor);
        Formation supp=new Formation(5);
        FormationServiceCRUD service=new FormationServiceCRUD();
        try 
        {
            //ajouter une formation
            service.insertFormation(formation1);
            System.out.println("ajouté avec succes");
            //affichage formation
                System.out.println("Liste des formations : ");
                List<Formation> listF = service.ListeFormations();
                for (Formation f : listF)
                    System.out.println(f);
            //supprimer formation
            service.SupprimerFormation(supp);
            System.out.println("supprimer formation");
            //modification formation
            Formation formationmodif=new Formation(9,"fafi","badou","fafi","fafi", new Date(),idUser, typefor);
            service.ModifierFormation(formationmodif);
            System.out.println("modification");        
        } 
        catch (SQLException e) 
        {
            System.out.println("erreur");
            e.printStackTrace();
                    
        }
        
        
        //ajouter session
        Formation formationsession=new Formation(10);
        Session session=new Session(new Date(65165169684L), new Date(65165169684L),5, "encours", null, 22.0, 25.5, "fafi",formationsession );
        
        SERVICES.SessionService servicesession=new SessionService();
        try {
            servicesession.insertSession(session);
            System.out.println("ajout session");
            //afficher sessions
            //affichage formation
                System.out.println("Liste des sessions : ");
                List<Session> listS = servicesession.ListeSessions();
                for (Session s : listS)
                    System.out.println(s);
                
                //supprimer session
                System.out.println("supprimer session");
               
                Session sess=new Session(9);
                servicesession.SupprimerSession(sess);
                
                //update session
                Formation forma=new Formation(4);
                Session sessionsupp=new Session (4,new Date(65165169684L), new Date(65165169684L), 44, "finie", null, 5.0, 5.5, "fafiiiii",forma );
                servicesession.ModifierSession(sessionsupp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       */
    }

   
    
}
