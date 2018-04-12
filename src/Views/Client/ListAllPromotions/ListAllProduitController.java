/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.ListAllPromotions;

import Entity.Categorie;
import Entity.LinePromo;
import Entity.Produit;
import Services.CategorieService;
import Services.LinePromoService;
import Services.ProduitService;
import Views.Client.Produit.AfficherProduit.ProduitSingleController;
import Views.Client.PromoSingle.PromoSingleController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author hamdi fathallah
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
    private Node[] listePagePromotion ;
    private int nbrLignePage = 0 ;
    @FXML
    private Label btnP;
    @FXML
    private VBox section_body;
    @FXML
    private Label btnS;
    private VBox vbody;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            nav_cat.getChildren().clear();
            section_body.getChildren().clear();
            CategorieService catProd = new CategorieService();
            List<Categorie> listC = catProd.AfficherCategorie();
            LinePromoService ps = new LinePromoService();
            List<LinePromo> listPromo = ps.AfficherLinePromoS();
            Node [] nodesCategorie = new Node[listC.size()];
            Node [] nodesLigne = new Node[listPromo.size()];
            Node [] nodesColonne = new Node[listPromo.size()];
            if(listPromo.size() % 6 == 0)
                listePagePromotion  = new Node[listPromo.size()/6];
            else
                listePagePromotion  = new Node[(listPromo.size()/6)+1];
            int i = 0 ;
            int j = 0 ;
            for (Categorie catp : listC)
            {
                FXMLLoader loadercat = new FXMLLoader(getClass().getResource("CategorieProdFiltre.fxml"));
                Node catNode = loadercat.load() ;
                CategorieProdFiltreController crfc = loadercat.getController();
                crfc.setNomCat(catp.getNomCat());
                crfc.setNbrProd(String.valueOf(catProd.CountPromoParCat(catp.getIdCat())));
                Text Btafficher = crfc.getAfficherProduit();
                Btafficher.setOnMouseClicked(e->{
                  
                    try {
                        PromotionParCategorie(catp.getIdCat());
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
            for(LinePromo promo : listPromo)
            {
                // parcour des lignes
                if ( i % 3 == 0 || i == 0)
                {
                    loaderItems = new FXMLLoader(getClass().getResource("Hbox_Items.fxml"));
                    nodesLigne[j] = loaderItems.load() ;
                }
                hc = loaderItems.getController();
               Date dateactuelle = new java.sql.Date((new java.util.Date()).getTime());
                System.out.println("date actuelle"+dateactuelle);
                if(promo.getDateFin().before(dateactuelle))
                {
                    System.out.println("modification date eli kbal date actuelle effectuée");
                    ps.ModifierEtatLinePromo((java.sql.Date) (Date) promo.getDateFin());
                }
                //parcour des colonnes
                FXMLLoader loader = new FXMLLoader(getClass().getResource("morabba3s8ir.fxml"));
                nodesColonne[i] = loader.load() ;
                Morabba3s8irController msc = loader.getController();
                msc.setNom(promo.getIdProd().getNomProd());
                msc.setImage(promo.getIdProd().getImageprod());
                msc.setPrix(promo.getIdProd().getPrixProd().toString());
                msc.setNomPat(promo.getIdProd().getNvPrix().toString());
                //msc.setNomCat(promo.getIdProd().getIdCat().getNomCat());
                //msc.setNomPat(promo.getIdProd().getIdUser().getUsername());
                ImageView img = msc.getImage();
                img.setOnMouseClicked(e->{
                    try {
                        System.out.println("te5dem");
                      
                           
                            ps.UpdateProduitValeur(promo.getIdProd());
                        
                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../PromoSingle/PromoSingle.fxml"));
                        Parent root;
                        root = loader1.load();
                        PromoSingleController c2 =loader1.getController();
                        System.out.println("produit " + promo);
                        BodyVBox.getChildren().clear();
                        BodyVBox.getChildren().add(root);
                        c2.setNomProd(promo.getIdProd().getNomProd());
                        c2.setImage(promo.getIdProd().getImageprod());
                        c2.setPrix(promo.getIdProd().getPrixProd().toString());
                        c2.setNvprix(promo.getIdProd().getPrixProd().toString());
                        c2.setCategorie(promo.getIdProd().getIdCat().getNomCat());
                        c2.setStock(promo.getIdProd().getQteStockProd().toString());
                        System.out.println(promo);
                        c2.setProd(promo.getIdProd());
                        c2.Controle();
                        System.out.println("produit valeur" + promo.getIdProd().getValeur());
                        
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }   catch (SQLException ex) {
                            Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                  
                });
                hc.addColonne(nodesColonne[i]);
                i++;
                if(j==0 || j % 2 == 0){
                    FXMLLoader loaderPage2Ligne = new FXMLLoader(getClass().getResource("Page2Produit.fxml"));
                    listePagePromotion[nbrLignePage] = loaderPage2Ligne.load();
                    Cp2r = loaderPage2Ligne.getController();
                }
                if( listPromo.size() > i)
                {
                    if ( i % 3 == 0 ){
                        Cp2r.setPage2Ligne(nodesLigne[j]);
                        j++;
                        if(j==2)
                            section_body.getChildren().add(listePagePromotion[nbrLignePage]);
                        if (j % 2 ==0){
                            listePagePromotion[nbrLignePage]=Cp2r.getPage2Ligne();
                            nbrLignePage++;
                        }
                    }
                }
                else
                {
                    if (j<2)
                        section_body.getChildren().add(listePagePromotion[nbrLignePage]);
                    Cp2r.setPage2Ligne(nodesLigne[j]);
                    listePagePromotion[nbrLignePage]=Cp2r.getPage2Ligne();
                    nbrLignePage++;
                }
            }
            System.out.println(listePagePromotion.length + " hahahah "+ nbrLignePage);
        
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ListAllProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    nbrLignePage = 0 ;
       
    }   
     public void PromotionParCategorie(int idCat) throws SQLException, IOException{
        section_body.getChildren().clear();
        LinePromoService RS = new LinePromoService();
        List<Produit> listPromo = RS.ProduitParCategorie(idCat);
        Node [] nodesLigne = new Node[listPromo.size()];
        Node [] nodesColonne = new Node[listPromo.size()];
        int i = 0 ;
        int j = 0 ;
        
        FXMLLoader loaderItems = null ;
        Hbox_ItemsController hc = new Hbox_ItemsController();
        for(Produit promo : listPromo)
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
            msc.setNom(promo.getNomProd());
            msc.setImage(promo.getImageprod());
            msc.setNomCat(promo.getIdCat().getNomCat());
            msc.setNomPat(promo.getIdUser().getUsername());
            msc.setPrix(promo.getPrixProd().toString());
            ImageView img = msc.getImage();
            img.setOnMouseClicked(e->{
                System.out.println("te5dem");
            });
            hc.addColonne(nodesColonne[i]);
            i++;
            if( listPromo.size() > i)
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

    public VBox getBody() {
        return body;
    }

    public void setBody(VBox body) {
        this.body = body;
    }

    public HBox getNav_body() {
        return nav_body;
    }

    public void setNav_body(HBox nav_body) {
        this.nav_body = nav_body;
    }

    public HBox getNav_search() {
        return nav_search;
    }

    public void setNav_search(HBox nav_search) {
        this.nav_search = nav_search;
    }

    public HBox getNav_cat() {
        return nav_cat;
    }

    public void setNav_cat(HBox nav_cat) {
        this.nav_cat = nav_cat;
    }

    public VBox getBodyVBox() {
        return BodyVBox;
    }

    public void setBodyVBox(VBox BodyVBox) {
        this.BodyVBox = BodyVBox;
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
            nbrLignePage=listePagePromotion.length -1;
        section_body.getChildren().clear();
        System.out.println(nbrLignePage);
        section_body.getChildren().add(listePagePromotion[nbrLignePage]);
    }

    @FXML
    private void PageSuivant(MouseEvent event) {
          nbrLignePage++;
        if(nbrLignePage== listePagePromotion.length)
            nbrLignePage=0 ;
        section_body.getChildren().clear();
        System.out.println(nbrLignePage);
        section_body.getChildren().add(listePagePromotion[nbrLignePage]);
    }
    
}
