/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Panier.AfficherPanier;

import Entity.Produit;
import Services.LinePromoService;
import Services.PanierService;
import Services.ProduitService;
import Views.Client.Panier.ConfirmerAdre.CommandeController;
import Views.Client.Produit.AfficherProduit.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class AfficherPanierController implements Initializable {
    private double Tot;
    private Node[] listeP;
    @FXML
    private HBox Entete;
    @FXML
    private VBox panierpart;
    private int nbrLignePage = 0 ;
    @FXML
    private VBox vv;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             PanierService ps=PanierService.getInstance();
              panierpart.getChildren().clear();
            //PanierService ps = new PanierService();
           Map<Produit,Integer> panier = ps.Afficher();
            Node [] nodesLigne = new Node[3];
             if(panier.size() % 6 == 0)
                listeP = new Node[panier.size()/6];
            else
                listeP = new Node[(panier.size()/6)+1];
            int i = 0 ;
            int j = 0 ;
            FXMLLoader loaderItems = null ;
            //PanierBodyController hc = new PanierBodyController();
            System.out.println(panier.size());
            for(Map.Entry<Produit,Integer> p : panier.entrySet())
            {
                 try {
                     LinePromoService line = new LinePromoService();
                     List<Integer> l =line.afficherProduit();
                     //parcour des colonnes
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("PanierBody.fxml"));
                     Node pr = loader.load();
                     PanierBodyController msc = loader.getController();
                     panierpart.getChildren().add(pr);
                     msc.setImageP(p.getKey().getImageprod());
                     if(l.contains(p.getKey().getIdProd())){
                           msc.setPrixUni(p.getKey().getNvPrix().toString());
                        }
                        else{
                           msc.setPrixUni(p.getKey().getPrixProd().toString());
                        }
                     
                     msc.setQte(p.getValue().toString());
                     if(l.contains(p.getKey().getIdProd())){
                           Tot = p.getKey().getNvPrix()* p.getValue();
                        }
                        else{
                          Tot = p.getKey().getPrixProd() * p.getValue();
                        }
                     
                     msc.setTotal(String.valueOf(Tot));
                     
                     msc.setProd(p.getKey());
                     ImageView img = msc.getImageP();
                     img.setOnMouseClicked(e->{
                         System.out.println("tekhdem");
                     });
                 } catch (IOException ex) {
                     Logger.getLogger(AfficherPanierController.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (SQLException ex) {
                     Logger.getLogger(AfficherPanierController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                                       
            }
                
            System.out.println(panier.size()+","+ps.Afficher());
        
    nbrLignePage = 0 ;

    }   

    public VBox getPanierpart() {
        return panierpart;
    }

    public void setPanierpart(VBox panierpart) {
        this.panierpart = panierpart;
    }
       

    @FXML
    private void Valider(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ConfirmerAdre/Commande.fxml"));
        Node root = loader.load();
        CommandeController Clar = loader.getController();
        vv.getChildren().clear();
        vv.getChildren().add(root);

    }
        }    
    

