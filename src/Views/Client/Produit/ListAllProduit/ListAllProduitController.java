/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Produit.ListAllProduit;

import Entity.Categorie;
import Entity.Produit;
import Services.CategorieService;
import Services.LinePromoService;
import Services.PanierService;
import Services.ProduitService;
import Views.Client.Produit.AfficherProduit.ProduitSingleController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
 * @author aziz
 */
public class ListAllProduitController implements Initializable {

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
    private Node[] listePageProduit ;
    private int nbrLignePage = 0 ;
    @FXML
    private Label btnP;
    @FXML
    private Label btnS;
    @FXML
    private TextField search;
    private VBox vbody;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
             LinePromoService line = new LinePromoService();
                     List<Integer> l =line.afficherProduit();
            nav_cat.getChildren().clear();
            section_body.getChildren().clear();
            CategorieService catProd = new CategorieService();
            List<Categorie> listC = catProd.AfficherCategorie();
            ProduitService ps = new ProduitService();
            List<Produit> listProd = ps.AfficherProduit();
            Node [] nodesCategorie = new Node[listC.size()];
            Node [] nodesLigne ;
            
            //liste des ligne
            if(listProd.size() % 3 == 0)
                nodesLigne = new Node[(listProd.size() /3)];
            else
                nodesLigne = new Node[(listProd.size() /3)+1];
            
            //liste des colonnes
            Node [] nodesColonne = new Node[listProd.size()];
            
            if(listProd.size() % 6 == 0)
                listePageProduit = new Node[listProd.size()/6];
            else
                listePageProduit = new Node[(listProd.size()/6)+1];
            int i = 0 ;
            int j = 0 ;
            for (Categorie catp : listC)
            {
                FXMLLoader loadercat = new FXMLLoader(getClass().getResource("CategorieProdFiltre.fxml"));
                Node catNode = loadercat.load() ;
                CategorieProdFiltreController crfc = loadercat.getController();
                crfc.setNomCat(catp.getNomCat());
                crfc.setNbrProd(String.valueOf(catProd.CountProduiParCat(catp.getIdCat())));
                Text Btafficher = crfc.getAfficherProduit();
                Btafficher.setOnMouseClicked(e->{
                  
                    try {
                        ProduitParCategorie(catp.getIdCat());
                    } catch (SQLException ex) {
                        Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                });
                nav_cat.getChildren().add(catNode);
            }
            FXMLLoader loaderItems = null ;
            Hbox_ItemsController hc = new Hbox_ItemsController();
            Page2ProduitController Cp2r = new Page2ProduitController();
            for(Produit prod : listProd)
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
                msc.setNom(prod.getNomProd());
                msc.setImage(prod.getImageprod());
                msc.setNomCat(prod.getIdCat().getNomCat());
                msc.setNomPat(prod.getIdUser().getUsername());
                msc.setPrix(prod.getPrixProd().toString());
               
                     if(l.contains(prod.getIdProd())){
                msc.setNV(prod.getNvPrix().toString());}
                msc.setP(prod);
                 msc.Controlle();
                ImageView img = msc.getImage();
               
                img.setOnMouseClicked(e->{
                    try {
                         
                        ps.UpdateProduitValeur(prod);
                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../AfficherProduit/ProduitSingle.fxml"));
                        Parent root = loader1.load();
                        ProduitSingleController c2 =loader1.getController();
                        System.out.println("produit " + prod);
                        vbody.getChildren().clear();
                        vbody.getChildren().add(root);
                        c2.setNomProd(prod.getNomProd());
                        c2.setImage(prod.getImageprod());
                        c2.setPrix(prod.getPrixProd().toString());
                        c2.setCategorie(prod.getIdCat().getNomCat());
                        c2.setStock(prod.getQteStockProd().toString());
                       System.out.println(prod);
                        c2.setProd(prod);
                        c2.Controle();
                         c2.Controlle();
                        c2.setNV(prod.getNvPrix().toString());
                        System.out.println("produit valeur" + prod.getValeur());
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                hc.addColonne(nodesColonne[i]);
                i++;
                if(j==0 || j % 2 == 0){
                    FXMLLoader loaderPage2Ligne = new FXMLLoader(getClass().getResource("Page2Produit.fxml"));
                    listePageProduit[nbrLignePage] = loaderPage2Ligne.load();
                    Cp2r = loaderPage2Ligne.getController();
                }
                if( listProd.size() > i)
                {
                    if ( i % 3 == 0 ){
                        Cp2r.setPage2Ligne(nodesLigne[j]);
                        j++;
                        if(j==2)
                            section_body.getChildren().add(listePageProduit[nbrLignePage]);
                        if (j % 2 ==0){
                            listePageProduit[nbrLignePage]=Cp2r.getPage2Ligne();
                            nbrLignePage++;
                        }
                    }
                }
                else
                {
                    if (j<2)
                        section_body.getChildren().add(listePageProduit[nbrLignePage]);
                    Cp2r.setPage2Ligne(nodesLigne[j]);
                    listePageProduit[nbrLignePage]=Cp2r.getPage2Ligne();
                    nbrLignePage++;
                }
            }
            System.out.println(listePageProduit.length + " hahahah "+ nbrLignePage);
        
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    nbrLignePage = 0 ;

    }
        
    public void ProduitParCategorie(int idCat) throws SQLException, IOException{
        section_body.getChildren().clear();
        ProduitService RS = new ProduitService();
        List<Produit> listProd = RS.ProduitParCategorie(idCat);
        Node [] nodesLigne ;

        //liste des ligne
        if(listProd.size() % 3 == 0)
            nodesLigne = new Node[(listProd.size() /3)];
        else
            nodesLigne = new Node[(listProd.size() /3)+1];

        //liste des colonnes
        Node [] nodesColonne = new Node[listProd.size()];
        int i = 0 ;
        int j = 0 ;
        
        FXMLLoader loaderItems = null ;
        Hbox_ItemsController hc = new Hbox_ItemsController();
        for(Produit prod : listProd)
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
            msc.setNom(prod.getNomProd());
            msc.setImage(prod.getImageprod());
            msc.setNomCat(prod.getIdCat().getNomCat());
            msc.setNomPat(prod.getIdUser().getUsername());
            msc.setPrix(prod.getPrixProd().toString());
            ImageView img = msc.getImage();
            img.setOnMouseClicked(e->{
                try {
                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../AfficherProduit/ProduitSingle.fxml"));
                    Parent root = loader1.load();
                    ProduitSingleController c2 =loader1.getController();
                    c2.setProd(prod);
                    System.out.println("produit " + prod);
                    section_body.getChildren().clear();
                    section_body.getChildren().add(root);
                    c2.setNomProd(prod.getNomProd());
                    c2.setImage(prod.getImageprod());
                    c2.setPrix(prod.getPrixProd().toString());
                    c2.setCategorie(prod.getIdCat().getNomCat());
                    c2.setStock(prod.getQteStockProd().toString());
                    System.out.println(prod);
                    c2.Controle();
                } catch (IOException ex) {
                    Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
 });
            hc.addColonne(nodesColonne[i]);
            i++;
            if( listProd.size() > i)
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

    public VBox getVbody() {
        return vbody;
    }

    public void setVbody(VBox vbody) {
        this.vbody = vbody;
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
            nbrLignePage=listePageProduit.length -1;
        section_body.getChildren().clear();
        System.out.println(nbrLignePage);
        section_body.getChildren().add(listePageProduit[nbrLignePage]);
    }

    @FXML
    private void PageSuivant(MouseEvent event) {
        nbrLignePage++;
        if(nbrLignePage== listePageProduit.length)
            nbrLignePage=0 ;
        section_body.getChildren().clear();
        System.out.println(nbrLignePage);
        section_body.getChildren().add(listePageProduit[nbrLignePage]);
    }

    @FXML
    private void searchProduit(KeyEvent event) throws SQLException, IOException {
    nav_cat.getChildren().clear();
            section_body.getChildren().clear();
            CategorieService catProd = new CategorieService();
            List<Categorie> listC = catProd.AfficherCategorie();
            ProduitService ps = new ProduitService();
            List<Produit> listProd = ps.RechercheProduitClient(search.getText());
            Node [] nodesCategorie = new Node[listC.size()];
            Node [] nodesLigne = new Node[listProd.size()];
            Node [] nodesColonne = new Node[listProd.size()];
            if(listProd.size() % 6 == 0)
                listePageProduit = new Node[listProd.size()/6];
            else
                listePageProduit = new Node[(listProd.size()/6)+1];
            int i = 0 ;
            int j = 0 ;
            for (Categorie catp : listC)
            {
                FXMLLoader loadercat = new FXMLLoader(getClass().getResource("CategorieProdFiltre.fxml"));
                Node catNode = loadercat.load() ;
                CategorieProdFiltreController crfc = loadercat.getController();
                crfc.setNomCat(catp.getNomCat());
                crfc.setNbrProd(String.valueOf(catProd.CountProduiParCat(catp.getIdCat())));
                Text Btafficher = crfc.getAfficherProduit();
                Btafficher.setOnMouseClicked(e->{
                  
                    try {
                        ProduitParCategorie(catp.getIdCat());
                    } catch (SQLException ex) {
                        Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                });
                nav_cat.getChildren().add(catNode);
            }
            FXMLLoader loaderItems = null ;
            Hbox_ItemsController hc = new Hbox_ItemsController();
            Page2ProduitController Cp2r = new Page2ProduitController();
            for(Produit prod : listProd)
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
                msc.setNom(prod.getNomProd());
                msc.setImage(prod.getImageprod());
                msc.setNomCat(prod.getIdCat().getNomCat());
                msc.setNomPat(prod.getIdUser().getUsername());
                msc.setPrix(prod.getPrixProd().toString());
                ImageView img = msc.getImage();
               
                img.setOnMouseClicked(e->{
                    try {
                         
                        ps.UpdateProduitValeur(prod);
                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../AfficherProduit/ProduitSingle.fxml"));
                        Parent root = loader1.load();
                        ProduitSingleController c2 =loader1.getController();
                        System.out.println("produit " + prod);
                        section_body.getChildren().clear();
                        section_body.getChildren().add(root);
                        c2.setNomProd(prod.getNomProd());
                        c2.setImage(prod.getImageprod());
                        c2.setPrix(prod.getPrixProd().toString());
                        c2.setCategorie(prod.getIdCat().getNomCat());
                        c2.setStock(prod.getQteStockProd().toString());
                       System.out.println(prod);
                        c2.setProd(prod);
                        c2.Controle();
                        System.out.println("produit valeur" + prod.getValeur());
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                hc.addColonne(nodesColonne[i]);
                i++;
                if(j==0 || j % 2 == 0){
                    FXMLLoader loaderPage2Ligne = new FXMLLoader(getClass().getResource("Page2Produit.fxml"));
                    listePageProduit[nbrLignePage] = loaderPage2Ligne.load();
                    Cp2r = loaderPage2Ligne.getController();
                }
                if( listProd.size() > i)
                {
                    if ( i % 3 == 0 ){
                        Cp2r.setPage2Ligne(nodesLigne[j]);
                        j++;
                        if(j==2)
                            section_body.getChildren().add(listePageProduit[nbrLignePage]);
                        if (j % 2 ==0){
                            listePageProduit[nbrLignePage]=Cp2r.getPage2Ligne();
                            nbrLignePage++;
                        }
                    }
                }
                else
                {
                    if (j<2)
                        section_body.getChildren().add(listePageProduit[nbrLignePage]);
                    Cp2r.setPage2Ligne(nodesLigne[j]);
                    listePageProduit[nbrLignePage]=Cp2r.getPage2Ligne();
                    nbrLignePage++;
                }
            }
            System.out.println(listePageProduit.length + " hahahah "+ nbrLignePage);
        
    nbrLignePage = 0 ;
        
        
    }
    
    
}
