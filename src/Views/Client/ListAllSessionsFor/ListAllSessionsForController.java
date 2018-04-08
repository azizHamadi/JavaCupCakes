/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.ListAllSessionsFor;

import Entity.Educate;
import Entity.Session;
import Services.NoteService;
import Services.ServiceEducate;
import Services.SessionService;
import Views.Client.Recette.ListAllRecettes.ListAllRecettesController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author FERIEL FADHLOUN
 */
public class ListAllSessionsForController implements Initializable {

    @FXML
    private VBox body;
    @FXML
    private HBox nav_search;
    @FXML
    private Label labelRetourPageprecedente;
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
    private Label labelaucunesessiondispo;
    @FXML
    private Label btnS;

     private int idFormation;
    private Node[] listePageFormation;
     private int nbrLignePage = 0 ;
    private VBox vbody ;
    

    private int idSessionpourinscri;
    

    public int getIdSessionpourinscri() {
        return idSessionpourinscri;
    }

    public void setIdSessionpourinscri(int idSessionpourinscri) {
        this.idSessionpourinscri = idSessionpourinscri;
    }
    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }
    public VBox getBody() {
        return body;
    }

    public void setBody(VBox body) {
        this.body = body;
    }
    
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void afficherSessionsFormation(int idSessionpourinscri)
    {
        SessionService ss=new SessionService();
        try {
            nav_cat.getChildren().clear();
            section_body.getChildren().clear();

            System.out.println("if for mel methode exists"+ss.SessionExists(idFormation));
            System.out.println("etat mel methode returnEtats"+ss.ReturnEtatSes(idFormation));
            if(ss.SessionExists(idFormation)==0 || ss.ReturnEtatSes(idFormation).equals("finie"))
            {
                System.out.println("session mouch mawjouda wala etat finie");
                labelaucunesessiondispo.setVisible(true);
                section_body.getChildren().add(labelaucunesessiondispo);
            }
            else
            {
                labelaucunesessiondispo.setVisible(false);
            }
            System.out.println("morabaa3 theni"+idFormation);
            
            //List<Session> listC = listetypeformation.ListeTypeFormations();
            NoteService ns = new NoteService();
            SessionService rs = new SessionService();
            List<Session> listSession= rs.ListeSessions(idFormation);
            //Node [] nodesCategorie = new Node[listC.size()];
            Node [] nodesLigne = new Node[3];
            Node [] nodesColonne = new Node[9];
            if(listSession.size() % 6 == 0)
                listePageFormation = new Node[listSession.size()/6];
            else
                listePageFormation = new Node[(listSession.size()/6)+1];
            int i = 0 ;
            int j = 0 ;
            
            FXMLLoader loaderItems = null ;
            Views.Client.ListAllSessionsFor.Hbox_ItemsController hc = new Views.Client.ListAllSessionsFor.Hbox_ItemsController();
            Views.Client.ListAllSessionsFor.Page2FormationController Cp2r = new Views.Client.ListAllSessionsFor.Page2FormationController();
            for(Session rec : listSession)
            {
                // parcour des lignes
                if ( i % 3 == 0 || i == 0)
                {
                    loaderItems = new FXMLLoader(getClass().getResource("Hbox_Items.fxml"));
                    nodesLigne[j] = loaderItems.load() ;
                }
                hc = loaderItems.getController();
                if(rs.ReturnCapaciteSes(rec.getIdSes())==0)
                {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Attention!");
                        alert.setHeaderText(null);
                        alert.setContentText("Session pleine !! veuillez choisir une autre session !");
                        alert.showAndWait();
                        //return;
                }
                else
                {
                      //test aal date bech nbadalha keni fetet
                
                Date dateactuelle = new java.sql.Date((new java.util.Date()).getTime());
                System.out.println("date actuelle"+dateactuelle);
                //if(rec.getDateFinSes().before(dateactuelle))
                //{
                 //   rs.ModifierEtatFormation((Date) rec.getDateFinSes());
                
                //parcour des colonnes
                FXMLLoader loader = new FXMLLoader(getClass().getResource("morabba3s8ir.fxml"));
                nodesColonne[i] = loader.load() ;
                Views.Client.ListAllSessionsFor.Morabba3s8irController msc = loader.getController();
                
                msc.setNomFormation(rec.getNomSes());
                msc.setImage(rec.getImagesess());
                msc.setTxtdatedebut(rec.getDateDebSes().toString());
                msc.setTxtdatefin(rec.getDateFinSes().toString());
                ImageView img = msc.getImage();
                ServiceEducate se=new ServiceEducate();
                
                Educate educate=new Educate(rec);
                if(se.ifClientExists(1, rec.getIdSes()))
                {
                    msc.getTxtinscription().setVisible(false);
                    System.out.println("client deja inscrit");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Inscription!");
                    alert.setHeaderText(null);
                    alert.setContentText("client deja inscrit !!");
                    alert.showAndWait();
                    
                }
                else
                {
                    Text inscription=msc.getTxtinscription();
                    inscription.setOnMouseClicked(e->{
                        System.out.println("fel clique bouton inscription");
                        se.inscriptionClient(educate);
                        //nmodifiw el capacite 
                        rs.ModifSessionInscription(rec, rec.getIdSes());
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Inscription!");
                        alert.setHeaderText(null);
                        alert.setContentText("client inscrit avec succes !!");
                        alert.showAndWait();
                     return;
                    });
                }
                    
                
                
                hc.addColonne(nodesColonne[i]);
                i++;
                if(j==0 || j % 2 == 0){
                    FXMLLoader loaderPage2Ligne = new FXMLLoader(getClass().getResource("Page2Formation.fxml"));
                    listePageFormation[nbrLignePage] = loaderPage2Ligne.load();
                    Cp2r = loaderPage2Ligne.getController();
                }
                if( listSession.size() > i)
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
                //}
                }
            }
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
    private void RechercheSession(KeyEvent event) throws SQLException, IOException {
          section_body.getChildren().clear();
        SessionService RS = new SessionService();
        List<Session> listRec = RS.SearchListeSessions(recherche.getText());
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
        Views.Client.ListAllSessionsFor.Hbox_ItemsController hc = new Views.Client.ListAllSessionsFor.Hbox_ItemsController();
        Views.Client.ListAllSessionsFor.Page2FormationController Cp2r = new Views.Client.ListAllSessionsFor.Page2FormationController();
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
            Views.Client.ListAllSessionsFor.Morabba3s8irController msc = loader.getController();
            msc.setNomFormation(rec.getNomSes());
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
    private void labelRetour(MouseEvent event) throws IOException {
         System.out.println("retourner");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/GestionFormation/ListAllFormations.fxml"));
        Node root = loader.load();
        body.getChildren().clear();
        body.getChildren().add(root);
    }

   
    
}
