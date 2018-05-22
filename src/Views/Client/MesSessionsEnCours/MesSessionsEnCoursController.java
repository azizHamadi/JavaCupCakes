/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.MesSessionsEnCours;

import Entity.Session;
import Services.NoteService;
import Services.SessionService;
import Views.Client.Produit.ListAllProduit.ListAllProduitController;
import Views.Client.Recette.ListAllRecettes.ListAllRecettesController;
import Views.Client.SingleFormation.SingleFormationController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;

/**
 * FXML Controller class
 *
 * @author FERIEL FADHLOUN
 */
public class MesSessionsEnCoursController implements Initializable {

    @FXML
    private VBox body;
    @FXML
    private HBox nav_search;
    @FXML
    private TextField recherche;
    @FXML
    private VBox BodyVBox;
    @FXML
    private HBox nav_cat;
    @FXML
    private HBox nav_body;
    @FXML
    private Label btnP;
    @FXML
    private VBox section_body;
    @FXML
    private Label btnS;
 private Node[] listePageSession;
    private int nbrLignePage = 0 ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         try {
            nav_cat.getChildren().clear();
            section_body.getChildren().clear();
            SessionService servicesession = new SessionService();
            List<Session> listC = servicesession.ListeSessionsClientEnCoursFinies("en cours");
            NoteService ns = new NoteService();
           
            Node [] nodesCategorie = new Node[listC.size()];
            Node [] nodesLigne = new Node[3];
            Node [] nodesColonne = new Node[9];
            if(listC.size() % 6 == 0)
                listePageSession = new Node[listC.size()/6];
            else
                listePageSession = new Node[(listC.size()/6)+1];
            int i = 0 ;
            int j = 0 ;
            
            FXMLLoader loaderItems = null ;
            Views.Client.MesSessionsEnCours.Hbox_ItemsController hc = new Views.Client.MesSessionsEnCours.Hbox_ItemsController();
            Views.Client.MesSessionsEnCours.Page2FormationController Cp2r = new Views.Client.MesSessionsEnCours.Page2FormationController();
            for(Session rec : listC)
            {
                // parcour des lignes
                if ( i % 3 == 0 || i == 0)
                {
                    loaderItems = new FXMLLoader(getClass().getResource("Hbox_Items.fxml"));
                    nodesLigne[j] = loaderItems.load() ;
                }
                hc = loaderItems.getController();
                
                //parcour des colonnes
                FXMLLoader loader = new FXMLLoader(getClass().getResource("morabba3s8ir.fxml"));
                nodesColonne[i] = loader.load() ;
                Views.Client.MesSessionsEnCours.Morabba3s8irController msc = loader.getController();
                
             
                msc.setNom_session(rec.getNomSes());
                msc.setImage(rec.getImagesess());
                msc.setTxtdatedebut(rec.getDateDebSes().toString());
                msc.setTxtdatefin(rec.getDateFinSes().toString());
                msc.setImage(rec.getImagesess());
               // msc.setBody(body);
                msc.setTxtdatefin(rec.getDateDebSes().toString());
                ImageView img = msc.getImage();
                
                img.setOnMouseClicked(e->{
                    try {
                        System.out.println("fi west el image");
                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../SingleFormation/SingleFormation.fxml"));
                        Parent root = loader1.load();
                        SingleFormationController singlefor =loader1.getController();
                        System.out.println("formation " + rec);
                        //singlefor.setBody(BodyVBox);
                        BodyVBox.getChildren().clear();
                        BodyVBox.getChildren().add(root);
                        singlefor.setNomFormation(rec.getNomSes());
                        singlefor.setImageFor(rec.getImagesess());
                        singlefor.setDateFormation(rec.getDateDebSes().toString());
                       
                    } catch (IOException ex) {
                        Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                });                 
                hc.addColonne(nodesColonne[i]);
                i++;
                if(j==0 || j % 2 == 0){
                    FXMLLoader loaderPage2Ligne = new FXMLLoader(getClass().getResource("Page2Formation.fxml"));
                    listePageSession[nbrLignePage] = loaderPage2Ligne.load();
                    Cp2r = loaderPage2Ligne.getController();
                }
                if( listC.size() > i)
                {
                    if ( i % 3 == 0 ){
                        Cp2r.setPage2Ligne(nodesLigne[j]);
                        j++;
                        if(j==2)
                            section_body.getChildren().add(listePageSession[nbrLignePage]);
                        if (j % 2 ==0){
                            listePageSession[nbrLignePage]=Cp2r.getPage2Ligne();
                            nbrLignePage++;
                        }
                    }
                }
                else
                {
                    if (j<2)
                        section_body.getChildren().add(listePageSession[nbrLignePage]);
                    Cp2r.setPage2Ligne(nodesLigne[j]);
                    listePageSession[nbrLignePage]=Cp2r.getPage2Ligne();
                    nbrLignePage++;
                }
                FXMLLoader loaderlistS = new FXMLLoader(getClass().getResource("ListAllSessionsFor.fxml"));
                Views.Client.ListAllSessionsFor.ListAllSessionsForController msclistSess = loaderlistS.getController();
                System.out.println("id formation"+rec.getIdFor());
                //System.out.println("id formation fi listAllFormations="+rec.getIdFor());
                //ystem.out.println("hethi lid melowl"+msclistSess.getIdSessionpourinscri());
                //msclistSess.setIdSessionpourinscri(msc.getIdmelListAllFormations());
                //msclistSess.setBody(body);
            }
        
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ListAllRecettesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    nbrLignePage = 0 ;
    }

    @FXML
    private void PagePrecedente(MouseEvent event) {
        nbrLignePage= nbrLignePage-1;
        if(nbrLignePage<0)
            nbrLignePage=listePageSession.length -1;
        section_body.getChildren().clear();
        section_body.getChildren().add(listePageSession[nbrLignePage]);
    }

    @FXML
    private void PageSuivant(MouseEvent event) {
         nbrLignePage++;
        if(nbrLignePage== listePageSession.length)
            nbrLignePage=0 ;
        section_body.getChildren().clear();
        section_body.getChildren().add(listePageSession[nbrLignePage]);
    }

    @FXML
    private void RechercheSession(KeyEvent event) throws SQLException, IOException {
         section_body.getChildren().clear();
        SessionService RS = new SessionService();
        List<Session> listRec = RS.SearchListeSessionsClientEnCoursouFinie(recherche.getText(),"en cours");
        Node [] nodesLigne = new Node[3];
        Node [] nodesColonne = new Node[9];
        int i = 0 ;
        int j = 0 ;
        NoteService ns = new NoteService();
        if(listRec.size() % 6 == 0)
            listePageSession = new Node[listRec.size()/6];
        else
            listePageSession = new Node[(listRec.size()/6)+1];
        FXMLLoader loaderItems = null ;
        Views.Client.MesSessionsEnCours.Hbox_ItemsController hc = new Views.Client.MesSessionsEnCours.Hbox_ItemsController();
        Views.Client.MesSessionsEnCours.Page2FormationController Cp2r = new Views.Client.MesSessionsEnCours.Page2FormationController();
        for(Session rec : listRec)
        {
            // parcour des lignes 
            if ( i % 3 == 0 || i == 0)
            {
                loaderItems = new FXMLLoader(getClass().getResource("Hbox_Items.fxml"));
                nodesLigne[j] = loaderItems.load() ;
            }
            hc = loaderItems.getController();

            //parcour des colonnes
            FXMLLoader loader = new FXMLLoader(getClass().getResource("morabba3s8ir.fxml"));
            nodesColonne[i] = loader.load() ;
            Views.Client.MesSessionsEnCours.Morabba3s8irController msc = loader.getController();
            msc.setNom_session(rec.getNomSes());
            msc.setImage(rec.getImagesess());
            msc.setTxtdatedebut(rec.getDateDebSes().toString());
            msc.setTxtdatefin(rec.getDateFinSes().toString());

            ImageView img = msc.getImage();
            img.setOnMouseClicked(e->{
                System.out.println("te5dem");
            });
            hc.addColonne(nodesColonne[i]);
            i++;
                if(j==0 || j % 2 == 0){
                    FXMLLoader loaderPage2Ligne = new FXMLLoader(getClass().getResource("Page2Formation.fxml"));
                    listePageSession[nbrLignePage] = loaderPage2Ligne.load();
                    Cp2r = loaderPage2Ligne.getController();
                }
                if( listRec.size() > i)
                {
                    if ( i % 3 == 0 ){
                        Cp2r.setPage2Ligne(nodesLigne[j]);
                        j++;
                        if(j==2)
                            section_body.getChildren().add(listePageSession[nbrLignePage]);
                        if (j % 2 ==0){
                            listePageSession[nbrLignePage]=Cp2r.getPage2Ligne();
                            nbrLignePage++;
                        }
                    }
                }
                else
                {
                    if (j<2)
                        section_body.getChildren().add(listePageSession[nbrLignePage]);
                    Cp2r.setPage2Ligne(nodesLigne[j]);
                    listePageSession[nbrLignePage]=Cp2r.getPage2Ligne();
                    nbrLignePage++;
                }
        }
        nbrLignePage = 0 ;
    }
    
}
