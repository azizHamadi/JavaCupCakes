/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.GestionFormation;

import Entity.Formation;
import Entity.TypeFormation;

import Services.NoteService;
import Services.ServiceFormation;
import Services.ServiceTypeFormation;
import Views.Client.Produit.ListAllProduit.ListAllProduitController;
import Views.Client.Recette.ListAllRecettes.ListAllRecettesController;
import Views.Client.SingleFormation.SingleFormationController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;

/**
 * FXML Controller class
 *
 * @author FERIEL FADHLOUN
 */
public class ListAllFormationsController implements Initializable {

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
    private Node[] listePageFormation;
    private int nbrLignePage = 0 ;
      /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            nav_cat.getChildren().clear();
            section_body.getChildren().clear();
            ServiceTypeFormation listetypeformation = new ServiceTypeFormation();
            List<TypeFormation> listC = listetypeformation.ListeTypeFormations();
            NoteService ns = new NoteService();
            ServiceFormation rs = new ServiceFormation();
            List<Formation> listRec = rs.AfficherListeFormation();
            
            Node [] nodesCategorie = new Node[listC.size()];
            Node [] nodesLigne = new Node[3];
            Node [] nodesColonne = new Node[9];
            if(listRec.size() % 6 == 0)
                listePageFormation = new Node[listRec.size()/6];
            else
                listePageFormation = new Node[(listRec.size()/6)+1];
            int i = 0 ;
            int j = 0 ;
           
            
            
            for (TypeFormation typef : listC)
            {
                FXMLLoader loadercat = new FXMLLoader(getClass().getResource("TypeFormationFiltre.fxml"));
                Node catNode = loadercat.load() ;
                Views.Client.GestionFormation.TypeFormationFiltreController typeffiltre = loadercat.getController();
                typeffiltre.setNomTypeFor(typef.getNomTypeFor());
                typeffiltre.setNbrTypeFor(String.valueOf(rs.CountFormationParTypeFor(typef.getIdTypeFor())));
                System.out.println("id loula"+typef.getIdTypeFor());
                Text Btafficher = typeffiltre.getAfficherListeFor();
                System.out.println(typef.getIdTypeFor());
                Btafficher.setOnMouseClicked(e->{
                    try 
                    {
                        AfficherListeFormationParTypeFor(typef.getIdTypeFor());
                    } catch (SQLException ex) {
                        Logger.getLogger(ListAllFormationsController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ListAllFormationsController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                });
                nav_cat.getChildren().add(catNode);
            }
            FXMLLoader loaderItems = null ;
            Hbox_ItemsController hc = new Hbox_ItemsController();
            Page2FormationController Cp2r = new Page2FormationController();
            for(Formation rec : listRec)
            {
                // parcour des lignes
                if ( i % 3 == 0 || i == 0)
                {
                    loaderItems = new FXMLLoader(getClass().getResource("Hbox_Items.fxml"));
                    nodesLigne[j] = loaderItems.load() ;
                }
                hc = loaderItems.getController();
                //test aal date bech nbadalha keni fetet
                
                Date dateactuelle = new java.sql.Date((new java.util.Date()).getTime());
                System.out.println("date actuelle"+dateactuelle);
                if(rec.getDateFor().before(dateactuelle))
                {
                    System.out.println("modification date eli kbal date actuelle effectuée");
                    rs.ModifierEtatFormation((Date) rec.getDateFor());
                }
                //parcour des colonnes
                FXMLLoader loader = new FXMLLoader(getClass().getResource("morabba3s8ir.fxml"));
                nodesColonne[i] = loader.load() ;
                Views.Client.GestionFormation.Morabba3s8irController msc = loader.getController();
                
               //id formation qui va etre envoye lel mourabaasghir
                msc.setIdmelListAllFormations(rec.getIdFor());
                System.out.println("after"+msc.getIdmelListAllFormations());
                msc.setNomFormation(rec.getNomFor());
                msc.setImage(rec.getImageform());
                msc.setBody(body);
                msc.setTxtdatefin(rec.getDateFor().toString());
                ImageView img = msc.getImage();
                //afficher les informations de la formation                
                img.setOnMouseClicked(e->{
                    try {
                        System.out.println("fi west el image");
                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../SingleFormation/SingleFormation.fxml"));
                        Parent root = loader1.load();
                        
                        SingleFormationController singlefor =loader1.getController();
                        System.out.println("formation " + rec);
                        BodyVBox.getChildren().clear();
                        BodyVBox.getChildren().add(root);
                       //singlefor.setAnchorPane(BodyVBox);
                        singlefor.setNomFormation(rec.getNomFor());
                        singlefor.setImageFor(rec.getImageform());
                        singlefor.setDateFormation(rec.getDateFor().toString());
                        
                        WebEngine webEngine = singlefor.getTxtDescription().getEngine();
                        webEngine.loadContent(rec.getDescriptionFor());
                       
                       
                    } catch (IOException ex) {
                        Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                });   
                
                hc.addColonne(nodesColonne[i]);
                i++;
                if(j==0 || j % 2 == 0){
                    FXMLLoader loaderPage2Ligne = new FXMLLoader(getClass().getResource("Page2Formation.fxml"));
                    listePageFormation[nbrLignePage] = loaderPage2Ligne.load();
                    Cp2r = loaderPage2Ligne.getController();
                }
                if( listRec.size() > i)
                {
                    if ( i % 3 == 0 ){
                        Cp2r.setPage2Ligne(nodesLigne[j]);
                        j++;
                        if(j==2)
                            section_body.getChildren().add(listePageFormation[nbrLignePage]);
                        if (j % 2 ==0){
                            listePageFormation[nbrLignePage]=Cp2r.getPage2Ligne();
                            nbrLignePage++;
                        }
                    }
                }
                else
                {
                    if (j<2)
                        section_body.getChildren().add(listePageFormation[nbrLignePage]);
                    Cp2r.setPage2Ligne(nodesLigne[j]);
                    listePageFormation[nbrLignePage]=Cp2r.getPage2Ligne();
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

    public void AfficherListeFormationParTypeFor(int idtypefor) throws SQLException, IOException{
        section_body.getChildren().clear();
        ServiceFormation RS = new ServiceFormation();
        List<Formation> listRec = RS.AfficherListeFormationParTypeFor(idtypefor);
        Node [] nodesLigne = new Node[3];
        Node [] nodesColonne = new Node[9];
        int i = 0 ;
        int j = 0 ;
        NoteService ns = new NoteService();
        if(listRec.size() % 6 == 0)
            listePageFormation = new Node[listRec.size()/6];
        else
            listePageFormation = new Node[(listRec.size()/6)+1];
        FXMLLoader loaderItems = null ;
        Views.Client.GestionFormation.Hbox_ItemsController hc = new Views.Client.GestionFormation.Hbox_ItemsController();
        Page2FormationController Cp2r = new Page2FormationController();
        for(Formation rec : listRec)
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
            Views.Client.GestionFormation.Morabba3s8irController msc = loader.getController();
            //id formation qui va etre envoye lel mourabaasghir
            msc.setIdmelListAllFormations(rec.getIdFor());
            System.out.println("after"+msc.getIdmelListAllFormations());
            msc.setNomFormation(rec.getNomFor());
            msc.setImage(rec.getImageform());
            msc.setTxtdatefin(rec.getDateFor().toString());
            msc.setBody(body);
            ImageView img = msc.getImage();
             //afficher les informations de la formation                
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
                        singlefor.setNomFormation(rec.getNomFor());
                        singlefor.setImageFor(rec.getImageform());
                        singlefor.setDateFormation(rec.getDateFor().toString());
                        
                        WebEngine webEngine = singlefor.getTxtDescription().getEngine();
                        webEngine.loadContent(rec.getDescriptionFor());
                        //page el map
                        WebEngine webEnginemap = singlefor.getWebviewimage().getEngine();
                        webEnginemap.load(getClass().getResource("../../../Map/googlemap.html").toString());
                        webEnginemap.getLoadWorker().stateProperty().addListener(
                                new ChangeListener<Worker.State>() {
                            @Override
                            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
                                if (newState == Worker.State.SUCCEEDED) {
                                    System.out.println("map");
                                            webEngine.executeScript("addMarker("+rec.getAtitude()+","+rec.getLongitude()+");");

                                        }


                            }


                        });  
                       
                        
                       
                    } catch (IOException ex) {
                        Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                });   
            hc.addColonne(nodesColonne[i]);
            i++;
                if(j==0 || j % 2 == 0){
                    FXMLLoader loaderPage2Ligne = new FXMLLoader(getClass().getResource("Page2Formation.fxml"));
                    listePageFormation[nbrLignePage] = loaderPage2Ligne.load();
                    Cp2r = loaderPage2Ligne.getController();
                }
                if( listRec.size() > i)
                {
                    if ( i % 3 == 0 ){
                        Cp2r.setPage2Ligne(nodesLigne[j]);
                        j++;
                        if(j==2)
                            section_body.getChildren().add(listePageFormation[nbrLignePage]);
                        if (j % 2 ==0){
                            listePageFormation[nbrLignePage]=Cp2r.getPage2Ligne();
                            nbrLignePage++;
                        }
                    }
                }
                else
                {
                    if (j<2)
                        section_body.getChildren().add(listePageFormation[nbrLignePage]);
                    Cp2r.setPage2Ligne(nodesLigne[j]);
                    listePageFormation[nbrLignePage]=Cp2r.getPage2Ligne();
                    nbrLignePage++;
                }
        }
        nbrLignePage = 0 ;
    }

    @FXML
    private void PagePrecedente(MouseEvent event) {
        nbrLignePage= nbrLignePage-1;
        if(nbrLignePage<0)
            nbrLignePage=listePageFormation.length -1;
        section_body.getChildren().clear();
        section_body.getChildren().add(listePageFormation[nbrLignePage]);
    }

    @FXML
    private void PageSuivant(MouseEvent event) {
        nbrLignePage++;
        if(nbrLignePage== listePageFormation.length)
            nbrLignePage=0 ;
        section_body.getChildren().clear();
        section_body.getChildren().add(listePageFormation[nbrLignePage]);
    }

    @FXML
    private void RechercheFormation(KeyEvent event) throws SQLException, IOException {
        section_body.getChildren().clear();
        ServiceFormation RS = new ServiceFormation();
        List<Formation> listRec = RS.SearchListeFormationClient(recherche.getText());
        Node [] nodesLigne = new Node[3];
        Node [] nodesColonne = new Node[9];
        int i = 0 ;
        int j = 0 ;
        NoteService ns = new NoteService();
        if(listRec.size() % 6 == 0)
            listePageFormation = new Node[listRec.size()/6];
        else
            listePageFormation = new Node[(listRec.size()/6)+1];
        FXMLLoader loaderItems = null ;
        Views.Client.GestionFormation.Hbox_ItemsController hc = new Views.Client.GestionFormation.Hbox_ItemsController();
        Page2FormationController Cp2r = new Page2FormationController();
        for(Formation rec : listRec)
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
            Views.Client.GestionFormation.Morabba3s8irController msc = loader.getController();
            msc.setNomFormation(rec.getNomFor());
            msc.setImage(rec.getImageform());
            msc.setTxtdatefin(rec.getDateFor().toString());
           
          
            hc.addColonne(nodesColonne[i]);
            i++;
                if(j==0 || j % 2 == 0){
                    FXMLLoader loaderPage2Ligne = new FXMLLoader(getClass().getResource("Page2Formation.fxml"));
                    listePageFormation[nbrLignePage] = loaderPage2Ligne.load();
                    Cp2r = loaderPage2Ligne.getController();
                }
                if( listRec.size() > i)
                {
                    if ( i % 3 == 0 ){
                        Cp2r.setPage2Ligne(nodesLigne[j]);
                        j++;
                        if(j==2)
                            section_body.getChildren().add(listePageFormation[nbrLignePage]);
                        if (j % 2 ==0){
                            listePageFormation[nbrLignePage]=Cp2r.getPage2Ligne();
                            nbrLignePage++;
                        }
                    }
                }
                else
                {
                    if (j<2)
                        section_body.getChildren().add(listePageFormation[nbrLignePage]);
                    Cp2r.setPage2Ligne(nodesLigne[j]);
                    listePageFormation[nbrLignePage]=Cp2r.getPage2Ligne();
                    nbrLignePage++;
                }
        }
        nbrLignePage = 0 ;
    }
    
    
}
