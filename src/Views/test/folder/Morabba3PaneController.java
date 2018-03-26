/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.test.folder;

import Entity.CategorieRec;
import Entity.Recette;
import Services.CategorieRecetteService;
import Services.NoteService;
import Services.RecetteService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class Morabba3PaneController implements Initializable {

    @FXML
    private VBox body;
    @FXML
    private HBox nav_search;
    @FXML
    private HBox nav_cat;
    @FXML
    private HBox nav_body;
    @FXML
    private VBox section_body;
    @FXML
    private VBox nav_bar;
    @FXML
    private Button btnRecette;
    @FXML
    private VBox BodyVBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void GestionRecette(ActionEvent event) throws IOException, SQLException {
        nav_bar.getChildren().clear();
        nav_cat.getChildren().clear();
        section_body.getChildren().clear();
        CategorieRecetteService catRS = new CategorieRecetteService();
        List<CategorieRec> listC = catRS.AfficherCategorieRecette();
        FXMLLoader loaderNavBar = new FXMLLoader(getClass().getResource("barMenu.fxml"));
        nav_bar.getChildren().add(loaderNavBar.load());
        NoteService ns = new NoteService();
        RecetteService rs = new RecetteService();
        List<Recette> listRec = rs.AfficherRecette();
        Node [] nodesCategorie = new Node[listC.size()];
        Node [] nodesLigne = new Node[3];
        Node [] nodesColonne = new Node[9];
        int i = 0 ;
        int j = 0 ;
        for (CategorieRec catr : listC)
        {
            FXMLLoader loadercat = new FXMLLoader(getClass().getResource("CategorieRecFiltre.fxml"));
            Node catNode = loadercat.load() ;
            CategorieRecFiltreController crfc = loadercat.getController();
            crfc.setNomCat(catr.getNomCatRec());
            crfc.setNbrRec(String.valueOf(catRS.CountRecetteParCat(catr.getIdCatRec())));
            Text Btafficher = crfc.getAfficherRec();
            Btafficher.setOnMouseClicked(e->{
                try {
                    RecetteParCategorie(catr.getIdCatRec());
                } catch (SQLException ex) {
                    Logger.getLogger(Morabba3PaneController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Morabba3PaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            nav_cat.getChildren().add(catNode);
        }
        FXMLLoader loaderItems = null ;
        Hbox_ItemsController hc = new Hbox_ItemsController();
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
                System.out.println("te5dem");
            });
            hc.addColonne(nodesColonne[i]);
            i++;
            if( listRec.size() >= i)
            {
                if ( i % 3 == 0 ){
                    section_body.getChildren().add(nodesLigne[j]);
                    j++;
                }
            }
            else
            {
                section_body.getChildren().add(nodesLigne[j]);
            }
        }
    BarMenuController barC = loaderNavBar.getController();
    barC.setVbo(BodyVBox);
    }
    
    public void RecetteParCategorie(int idCat) throws SQLException, IOException{
        section_body.getChildren().clear();
        RecetteService RS = new RecetteService();
        List<Recette> listRec = RS.RecetteParCategorie(idCat);
        Node [] nodesLigne = new Node[3];
        Node [] nodesColonne = new Node[9];
        int i = 0 ;
        int j = 0 ;
        NoteService ns = new NoteService();

        FXMLLoader loaderItems = null ;
        Hbox_ItemsController hc = new Hbox_ItemsController();
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
                System.out.println("te5dem");
            });
            hc.addColonne(nodesColonne[i]);
            i++;
            if( listRec.size() >= i)
            {
                if ( i % 3 == 0 ){
                    section_body.getChildren().add(nodesLigne[j]);
                    j++;
                }
            }
            else
            {
                section_body.getChildren().add(nodesLigne[j]);
            }
        }
    }
}
