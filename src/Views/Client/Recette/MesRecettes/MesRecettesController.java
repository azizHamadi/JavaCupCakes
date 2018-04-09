/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Recette.MesRecettes;

import Entity.CategorieRec;
import Entity.Commentaire;
import Entity.Recette;
import Entity.SessionUser;
import Entity.Utilisateur;
import Services.CategorieRecetteService;
import Services.CommentaireService;
import Services.NoteService;
import Services.RecetteService;
import Services.ThreadService;
import Views.Client.Recette.Commentaire.CommentaireController;
import Views.Client.Recette.ModifierRecette.ModifierRecetteController;
import Views.Client.Recette.ModifierRecette.InfoRecetteController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class MesRecettesController implements Initializable {

    @FXML
    private VBox body;
    @FXML
    private HBox nav_search;
    @FXML
    private VBox BodyVBox;
    @FXML
    private HBox nav_cat;
    @FXML
    private HBox nav_body;
    @FXML
    private VBox section_body;
    private Node[] listePageRecette ;
    private int nbrLignePage = 0 ;
    private Node[] listePageCategorie ;
    private int nbrPageCat = 0 ;
    @FXML
    private Label btnP;
    @FXML
    private Label btnS;
    @FXML
    private TextField recherche;
    @FXML
    private Label btnPCat;
    @FXML
    private VBox section_bodyCategorie;
    @FXML
    private Label btnSCat;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            section_bodyCategorie.getChildren().clear();
            section_body.getChildren().clear();
            CategorieRecetteService catRS = new CategorieRecetteService();
            List<CategorieRec> listC = catRS.AfficherCategorieRecette();
            NoteService ns = new NoteService();
            RecetteService rs = new RecetteService();
            List<Recette> listRec = rs.AfficherRecette(SessionUser.getId());
            Node [] nodesCategorie = new Node[listC.size()];
            Node [] nodesLigne = new Node[listRec.size()];
            Node [] nodesColonne = new Node[listRec.size()];
            
            if(listC.size() % 4 ==0)
                listePageCategorie = new Node[listC.size()/4];
            else
                listePageCategorie=new Node[(listC.size()/4)+1];
            
            if(listRec.size() % 6 == 0)
                listePageRecette = new Node[listRec.size()/6];
            else
                listePageRecette = new Node[(listRec.size()/6)+1];
            int i = 0 ;
            int j = 0 ;
            
            FXMLLoader loaderPageCat = null ;
            Node PageCat = null ;
            PageCategorieRecController pcec = null ; 
            
            for (CategorieRec catr : listC)
            {
                if(i == 0 || i % 4 == 0 ){
                    loaderPageCat = new FXMLLoader(getClass().getResource("PageCategorieRec.fxml"));
                    PageCat = loaderPageCat.load();
                }
                pcec = loaderPageCat.getController();
                
                FXMLLoader loadercat = new FXMLLoader(getClass().getResource("CategorieRecFiltre.fxml"));
                Node catNode = loadercat.load() ;
                CategorieRecFiltreController crfc = loadercat.getController();
                crfc.setNomCat(catr.getNomCatRec());
                crfc.setNbrRec(String.valueOf(catRS.CountRecetteParCat(catr.getIdCatRec(),SessionUser.getId())));
                Text Btafficher = crfc.getAfficherRec();
                Btafficher.setOnMouseClicked(e->{
                    try {
                        RecetteParCategorie(catr.getIdCatRec());
                    } catch (SQLException ex) {
                        Logger.getLogger(MesRecettesController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MesRecettesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                pcec.setSection_bodyCategorie(catNode);
                i++;
                if(listC.size() > i){
                    if(i % 4 == 0 ){
                        listePageCategorie[nbrPageCat] = pcec.getSection_bodyCategorie();
                        nbrPageCat++;
                    }
                    if( i == 4 )
                        section_bodyCategorie.getChildren().add(pcec.getSection_bodyCategorie());
                }
                else{
                    if(i<4)
                        section_bodyCategorie.getChildren().add(pcec.getSection_bodyCategorie());
                    listePageCategorie[nbrPageCat] = pcec.getSection_bodyCategorie();
                    nbrPageCat++;
                }
            }
            nbrPageCat=0;
            
            i=0 ;
            FXMLLoader loaderItems = null ;
            Hbox_ItemsController hc = new Hbox_ItemsController();
            Page2RecetteController Cp2r = new Page2RecetteController();
            for(Recette rec : listRec)
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
                Morabba3s8irController msc = loader.getController();
                msc.setNom(rec.getNomRec());
                msc.setImage(rec.getImageRec());
                msc.setNomCat(rec.getIdCatRec().getNomCatRec());
                msc.setDescription(rec.getDescriptionRec().substring(0,30)+"...");
                msc.setNomUser(rec.getIdUser().getUsername());
                String moyenne = String.valueOf(ns.moyenneRecette(rec.getIdRec()));
                msc.setNote("Note : "+moyenne+" /5");
                ImageView img = msc.getImage();
                img.setOnMouseClicked(e->{
                    try {
                        ThreadService ts = new ThreadService();
                        if (ts.ThreadExiste(rec.getIdRec().toString()) == null )
                            ts.AjouterThread(new Entity.Thread(rec.getIdRec().toString()));
                        FXMLLoader loaderSingleRecette = new FXMLLoader(getClass().getResource("../../../Client/Recette/ModifierRecette/ModifierRecette.fxml"));
                        Node SingleRecette = loaderSingleRecette.load();
                        ModifierRecetteController CsR = loaderSingleRecette.getController();
                        
                        CsR.setCsr(CsR);
                        
                        FXMLLoader loaderinfoRecette = new FXMLLoader(getClass().getResource("../../../Client/Recette/ModifierRecette/infoRecette.fxml"));
                        Node infoRecette = loaderinfoRecette.load();
                        InfoRecetteController ic = loaderinfoRecette.getController();
                        ic.setRec(rec);
                        CsR.setIdRec(rec.getIdRec());
                        ic.setIdRec(rec.getIdRec());
                        ic.setCatRec(rec.getIdCatRec().getNomCatRec());
                        ic.setDescription(rec.getDescriptionRec());
                        CsR.setImageRecette(rec.getImageRec());
                        ic.setNomRec(rec.getNomRec());
                        ic.setUsername(rec.getIdUser().getUsername());
                        ic.setMoyenne(moyenne);
                        
                        ic.setCsR(CsR);
                        
                        CsR.getBodyModif().getChildren().clear();
                        CsR.setBodyModif(infoRecette);
                        CommentaireService ComS = new CommentaireService();
                        ic.setNbrComment(String.valueOf(ComS.CountCommentaireParRecette(rec.getIdRec())));

                        CommentaireService cs = new CommentaireService();
                        List<Commentaire> listCom = cs.AfficherCommentaire(String.valueOf(rec.getIdRec()));
                        CsR.setRec(rec);

                        CsR.AfficherCommentaireReplay(listCom, cs,"",null);
                        body.getChildren().clear();
                        body.getChildren().add(SingleRecette);
                    } catch (IOException ex) {
                        Logger.getLogger(MesRecettesController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(MesRecettesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                hc.addColonne(nodesColonne[i]);
                i++;
                if(j==0 || j % 2 == 0){
                    FXMLLoader loaderPage2Ligne = new FXMLLoader(getClass().getResource("Page2Recette.fxml"));
                    listePageRecette[nbrLignePage] = loaderPage2Ligne.load();
                    Cp2r = loaderPage2Ligne.getController();
                }
                if( listRec.size() > i)
                {
                    if ( i % 3 == 0 ){
                        Cp2r.setPage2Ligne(nodesLigne[j]);
                        j++;
                        if(j==2)
                            section_body.getChildren().add(listePageRecette[nbrLignePage]);
                        if (j % 2 ==0){
                            listePageRecette[nbrLignePage]=Cp2r.getPage2Ligne();
                            nbrLignePage++;
                        }
                    }
                }
                else
                {
                    if (j<2)
                        section_body.getChildren().add(listePageRecette[nbrLignePage]);
                    Cp2r.setPage2Ligne(nodesLigne[j]);
                    listePageRecette[nbrLignePage]=Cp2r.getPage2Ligne();
                    nbrLignePage++;
                }
            }
        
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MesRecettesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    nbrLignePage = 0 ;

    }   
    
    
    public void RecetteParCategorie(int idCat) throws SQLException, IOException{
        section_body.getChildren().clear();
        RecetteService RS = new RecetteService();
        List<Recette> listRec = RS.RecetteParCategorie(idCat,SessionUser.getId());
        Node [] nodesLigne = new Node[listRec.size()];
        Node [] nodesColonne = new Node[listRec.size()];
        int i = 0 ;
        int j = 0 ;
        NoteService ns = new NoteService();
        if(listRec.size() % 6 == 0)
            listePageRecette = new Node[listRec.size()/6];
        else
            listePageRecette = new Node[(listRec.size()/6)+1];
        FXMLLoader loaderItems = null ;
        Hbox_ItemsController hc = new Hbox_ItemsController();
        Page2RecetteController Cp2r = new Page2RecetteController();
        for(Recette rec : listRec)
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
            Morabba3s8irController msc = loader.getController();
            msc.setNom(rec.getNomRec());
            msc.setImage(rec.getImageRec());
            msc.setNomCat(rec.getIdCatRec().getNomCatRec());
            msc.setDescription(rec.getDescriptionRec().substring(0,30)+"...");
            msc.setNomUser(rec.getIdUser().getUsername());
            msc.setNote("Note : "+String.valueOf(ns.moyenneRecette(rec.getIdRec()))+" /5");
            ImageView img = msc.getImage();
            img.setOnMouseClicked(e->{
                try {
                    ThreadService ts = new ThreadService();
                        if (ts.ThreadExiste(rec.getIdRec().toString()) == null )
                            ts.AjouterThread(new Entity.Thread(rec.getIdRec().toString()));
                    FXMLLoader loaderSingleRecette = new FXMLLoader(getClass().getResource("../../Client/Recette/SingleRecette/SingleRecette.fxml"));
                    Node SingleRecette = loaderSingleRecette.load();
                    body.getChildren().clear();
                    body.getChildren().add(SingleRecette);
                } catch (IOException ex) {
                    Logger.getLogger(MesRecettesController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MesRecettesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            hc.addColonne(nodesColonne[i]);
            i++;
                if(j==0 || j % 2 == 0){
                    FXMLLoader loaderPage2Ligne = new FXMLLoader(getClass().getResource("Page2Recette.fxml"));
                    listePageRecette[nbrLignePage] = loaderPage2Ligne.load();
                    Cp2r = loaderPage2Ligne.getController();
                }
                if( listRec.size() > i)
                {
                    if ( i % 3 == 0 ){
                        Cp2r.setPage2Ligne(nodesLigne[j]);
                        j++;
                        if(j==2)
                            section_body.getChildren().add(listePageRecette[nbrLignePage]);
                        if (j % 2 ==0){
                            listePageRecette[nbrLignePage]=Cp2r.getPage2Ligne();
                            nbrLignePage++;
                        }
                    }
                }
                else
                {
                    if (j<2)
                        section_body.getChildren().add(listePageRecette[nbrLignePage]);
                    Cp2r.setPage2Ligne(nodesLigne[j]);
                    listePageRecette[nbrLignePage]=Cp2r.getPage2Ligne();
                    nbrLignePage++;
                }
        }
        nbrLignePage = 0 ;
    }

    
    public VBox getBody() {
        return body;
    }

    public void setBody(VBox body) {
        this.body = body;
    }

    public HBox getNav_search() {
        return nav_search;
    }

    public void setNav_search(HBox nav_search) {
        this.nav_search = nav_search;
    }

    public VBox getBodyVBox() {
        return BodyVBox;
    }

    public void setBodyVBox(VBox BodyVBox) {
        this.BodyVBox = BodyVBox;
    }

    public HBox getNav_cat() {
        return nav_cat;
    }

    public void setNav_cat(HBox nav_cat) {
        this.nav_cat = nav_cat;
    }

    public HBox getNav_body() {
        return nav_body;
    }

    public void setNav_body(HBox nav_body) {
        this.nav_body = nav_body;
    }

    public VBox getSection_body() {
        return section_body;
    }

    public void setSection_body(VBox section_body) {
        this.section_body = section_body;
    }

    @FXML
    private void PagePrecedente(MouseEvent event) {
        nbrLignePage= nbrLignePage-1;
        if(nbrLignePage<0)
            nbrLignePage=listePageRecette.length -1;
        section_body.getChildren().clear();
        section_body.getChildren().add(listePageRecette[nbrLignePage]);
    }

    @FXML
    private void PageSuivant(MouseEvent event) {
        nbrLignePage++;
        if(nbrLignePage== listePageRecette.length)
            nbrLignePage=0 ;
        section_body.getChildren().clear();
        section_body.getChildren().add(listePageRecette[nbrLignePage]);
    }

    @FXML
    private void RechercheRecette(KeyEvent event) throws SQLException, IOException {
        section_body.getChildren().clear();
        RecetteService RS = new RecetteService();
        List<Recette> listRec = RS.RechercheParNomRecette(recherche.getText(),SessionUser.getId());
        Node [] nodesLigne = new Node[listRec.size()];
        Node [] nodesColonne = new Node[listRec.size()];
        int i = 0 ;
        int j = 0 ;
        NoteService ns = new NoteService();
        if(listRec.size() % 6 == 0)
            listePageRecette = new Node[listRec.size()/6];
        else
            listePageRecette = new Node[(listRec.size()/6)+1];
        FXMLLoader loaderItems = null ;
        Hbox_ItemsController hc = new Hbox_ItemsController();
        Page2RecetteController Cp2r = new Page2RecetteController();
        for(Recette rec : listRec)
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
            Morabba3s8irController msc = loader.getController();
            msc.setNom(rec.getNomRec());
            msc.setImage(rec.getImageRec());
            msc.setNomCat(rec.getIdCatRec().getNomCatRec());
            msc.setDescription(rec.getDescriptionRec().substring(0,30)+"...");
            msc.setNomUser(rec.getIdUser().getUsername());
            msc.setNote("Note : "+String.valueOf(ns.moyenneRecette(rec.getIdRec()))+" /5");
            ImageView img = msc.getImage();
            img.setOnMouseClicked(e->{
                try {
                    ThreadService ts = new ThreadService();
                        if (ts.ThreadExiste(rec.getIdRec().toString()) == null )
                            ts.AjouterThread(new Entity.Thread(rec.getIdRec().toString()));
                    FXMLLoader loaderSingleRecette = new FXMLLoader(getClass().getResource("../../Client/Recette/SingleRecette/SingleRecette.fxml"));
                    Node SingleRecette = loaderSingleRecette.load();
                    body.getChildren().clear();
                    body.getChildren().add(SingleRecette);
                } catch (IOException ex) {
                    Logger.getLogger(MesRecettesController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MesRecettesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            hc.addColonne(nodesColonne[i]);
            i++;
                if(j==0 || j % 2 == 0){
                    FXMLLoader loaderPage2Ligne = new FXMLLoader(getClass().getResource("Page2Recette.fxml"));
                    listePageRecette[nbrLignePage] = loaderPage2Ligne.load();
                    Cp2r = loaderPage2Ligne.getController();
                }
                if( listRec.size() > i)
                {
                    if ( i % 3 == 0 ){
                        Cp2r.setPage2Ligne(nodesLigne[j]);
                        j++;
                        if(j==2)
                            section_body.getChildren().add(listePageRecette[nbrLignePage]);
                        if (j % 2 ==0){
                            listePageRecette[nbrLignePage]=Cp2r.getPage2Ligne();
                            nbrLignePage++;
                        }
                    }
                }
                else
                {
                    if (j<2)
                        section_body.getChildren().add(listePageRecette[nbrLignePage]);
                    Cp2r.setPage2Ligne(nodesLigne[j]);
                    listePageRecette[nbrLignePage]=Cp2r.getPage2Ligne();
                    nbrLignePage++;
                }
        }
        nbrLignePage = 0 ;
    }

    @FXML
    private void PagePrecedenteCategorie(MouseEvent event) {
        nbrPageCat -= 1;
        if(nbrPageCat<0)
            nbrPageCat =listePageCategorie.length -1;
        section_bodyCategorie.getChildren().clear();
        section_bodyCategorie.getChildren().add(listePageCategorie[nbrPageCat]);
    }

    @FXML
    private void PageSuivantCategorie(MouseEvent event) {
        nbrPageCat++;
        if(nbrPageCat== listePageCategorie.length)
            nbrPageCat=0 ;
        section_bodyCategorie.getChildren().clear();
        section_bodyCategorie.getChildren().add(listePageCategorie[nbrPageCat]);
    }

    
    
}
