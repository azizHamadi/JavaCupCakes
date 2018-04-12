/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Commande.listeCommande;

import Views.Client.Produit.ListAllProduit.*;
import Entity.Categorie;
import Entity.Commande;
import Entity.LineCmd;
import Entity.SessionUser;
import Services.CommandeService;
import Views.Client.Commande.CmdSingle.BodyCmdController;
import Views.Client.Commande.CmdSingle.CmdSingleController;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class CommandeController implements Initializable {

    @FXML
    private VBox body;
    @FXML
    private HBox nav_search;
    @FXML
    private VBox BodyVBox;
    @FXML
    private HBox nav_body;
    @FXML
    private VBox section_body;
    private Node[] listePageProduit;
    private int nbrLignePage = 0;
    @FXML
    private Label btnP;
    @FXML
    private Label btnS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            section_body.getChildren().clear();
            CommandeService Cmd = new CommandeService();
            List<Commande> listC = Cmd.AfficherCommandeFront(SessionUser.getId());
            Node[] nodesCategorie = new Node[listC.size()];
            Node[] nodesLigne = new Node[listC.size() ];
            Node[] nodesColonne = new Node[listC.size()];
            if (listC.size() % 6 == 0) {
                listePageProduit = new Node[listC.size() / 6];
            } else {
                listePageProduit = new Node[(listC.size() / 6) + 1];
            }
            int i = 0;
            int j = 0;
            FXMLLoader loaderItems = null;
            Hbox_ItemsController hc = new Hbox_ItemsController();
            Page2CommandeController Cp2r = new Page2CommandeController();
            for (Commande cmd : listC) {
                // parcour des lignes
                if (i % 3 == 0 || i == 0) {
                    loaderItems = new FXMLLoader(getClass().getResource("Hbox_Items.fxml"));
                    nodesLigne[j] = loaderItems.load();
                }
                hc = loaderItems.getController();
                //parcour des colonnes
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CommandeMouraba3.fxml"));
                nodesColonne[i] = loader.load();
                CommandeMouraba3Controller msc = loader.getController();
                msc.setDateCmd(cmd.getDateCmd().toString());
                Text Btafficher = msc.getAfficherProduit();
                Btafficher.setOnMouseClicked(e -> {
                    System.out.println("tekhdem");
                    try {
                        section_body.getChildren().clear();
                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../CmdSingle/CmdSingle.fxml"));
                        Node root = loader1.load();
                        System.out.println("tekhdem 1");
                        CmdSingleController c2 = loader1.getController();
                        c2.setAdr(cmd.getAddLiv());
                        c2.setCmd(cmd);
                        c2.setAdrLiv(cmd.getDateLivCmd().toString());
                        c2.setEtatLiv(cmd.getEtatLivCmd());
                        c2.setMontant(cmd.getMontantCmd().toString());
                        c2.getListeProd().getChildren().clear();
                        System.out.println(cmd.getIdCmd());
                        List<LineCmd> listL = Cmd.AfficherLineCommande(cmd.getIdCmd());
                        for (LineCmd l : listL) {
                            System.out.println("tekhdem 2");
                            FXMLLoader loaderL = new FXMLLoader(getClass().getResource("../CmdSingle/BodyCmd.fxml"));
                            Node pr = loaderL.load();
                            BodyCmdController Bsc = loaderL.getController();
                            Bsc.setNomP(l.getProduit().getNomProd());
                            System.out.println(l.getProduit().getNomProd());
                            Bsc.setQteAcheteer(l.getQteAcheter().toString());
                            Bsc.setImage(l.getProduit().getImageprod());
                            System.out.println(l.getProduit().getImageprod());
                            c2.getListeProd().getChildren().add(pr);
                        }
                        section_body.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
                hc.addColonne(nodesColonne[i]);
                i++;
                if (j == 0 || j % 2 == 0) {
                    FXMLLoader loaderPage2Ligne = new FXMLLoader(getClass().getResource("Page2Commande.fxml"));
                    listePageProduit[nbrLignePage] = loaderPage2Ligne.load();
                    Cp2r = loaderPage2Ligne.getController();
                }
                if (listC.size() > i) {
                    if (i % 3 == 0) {
                        Cp2r.setPage2Ligne(nodesLigne[j]);
                        j++;
                        if (j == 2) {
                            section_body.getChildren().add(listePageProduit[nbrLignePage]);
                        }
                        if (j % 2 == 0) {
                            listePageProduit[nbrLignePage] = Cp2r.getPage2Ligne();
                            nbrLignePage++;
                        }
                    }
                } else {
                    if (j < 2) {
                        section_body.getChildren().add(listePageProduit[nbrLignePage]);
                    }
                    Cp2r.setPage2Ligne(nodesLigne[j]);
                    listePageProduit[nbrLignePage] = Cp2r.getPage2Ligne();
                    nbrLignePage++;
                }
                //55555555555555555
            }
            System.out.println(listePageProduit.length + " hahahah " + nbrLignePage);

        } catch (SQLException | IOException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nbrLignePage = 0;

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
        nbrLignePage = nbrLignePage - 1;
        if (nbrLignePage < 0) {
            nbrLignePage = listePageProduit.length - 1;
        }
        section_body.getChildren().clear();
        System.out.println(nbrLignePage);
        section_body.getChildren().add(listePageProduit[nbrLignePage]);
    }

    @FXML
    private void PageSuivant(MouseEvent event) {
        nbrLignePage++;
        if (nbrLignePage == listePageProduit.length) {
            nbrLignePage = 0;
        }
        section_body.getChildren().clear();
        System.out.println(nbrLignePage);
        section_body.getChildren().add(listePageProduit[nbrLignePage]);
    }

}
