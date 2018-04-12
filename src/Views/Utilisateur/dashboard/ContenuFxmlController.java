/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Utilisateur.dashboard;

import Entity.Utilisateur;
import Services.userServices;
import Views.Utilisateur.Profil.ProfilController;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author pc_roua
 */
public class ContenuFxmlController implements Initializable {

    @FXML
    private Label Precedent;
    @FXML
    private VBox vboxC;
    @FXML
    private Label suivant;
    private VBox vbox1;
    private Node[] listePages;
    private int nbrPage = 0;
    @FXML
    private JFXTextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void AfficherUser(List<Utilisateur> User) throws IOException {
        userServices us = new userServices();
        vboxC.getChildren().clear();
        Node[] Items = new Node[(User.size() / 3) + 1];

        if (User.size() % 6 == 0) {
            System.out.println("nbr = " + User.size());
            listePages = new Node[User.size() / 6];
        } else {
            listePages = new Node[(User.size() / 6) + 1];
        }
        Node[] Colones = new Node[User.size()];
        int i = 0;
        int j = 0;
        FXMLLoader LoderItems = null;
        Page3UserController pg = new Page3UserController();
        Hbox_ItemsController hbI = new Hbox_ItemsController();
        for (Utilisateur u : User) {
            if (i % 3 == 0 || i == 0) {
                LoderItems = new FXMLLoader(getClass().getResource("Hbox_Items.fxml"));
                Items[j] = LoderItems.load();
                System.out.println("3mal item " + j);
            }
            hbI = LoderItems.getController();
            FXMLLoader loaderHbox = new FXMLLoader(getClass().getResource("Hbox.fxml"));
            Colones[i] = loaderHbox.load();
            HboxController hr = loaderHbox.getController();
            if(u.getRoles().contains("CLIENT"))
                hr.getRr().setVisible(false);
            else 
                hr.getUs().setVisible(false);
            hr.setEmail(u.getEmail());
            hr.setUsername(u.getUsername());
            hr.setUser(u);
            hr.getUsername().setOnMouseClicked(e -> {
                try {
                    FXMLLoader loaderProfil = new FXMLLoader(getClass().getResource("../Profil/Profil.fxml"));
                    Node profil = loaderProfil.load();
                    ProfilController pfc = loaderProfil.getController();
                    pfc.setUser(u);
                    pfc.setAdresse(u.getAddresse());
                    pfc.setEmail(u.getEmail());
                    pfc.setNom(u.getNom());
                    pfc.setTelephone(u.getPhoneNumber());
                    pfc.setPrenom(u.getPrenom());
                    pfc.setImage(u.getImageProfil());
                    pfc.setBody(vbox1);
                    pfc.button();
                    vbox1.getChildren().clear();
                    vbox1.getChildren().add(profil);
                } catch (IOException ex) {
                    Logger.getLogger(ContenuFxmlController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            Button btn = hr.getBtn();
            if (u.getEnabled() == true) {
                btn.setText("Activer");
            } else {
                btn.setText("Desactiver");

            }
            btn.setOnAction(value -> {

                if (btn.getText() == "Desactiver") {
                    try {
                        us.enabledUtilisateur(u.getId(), 0);
                    } catch (SQLException ex) {
                        Logger.getLogger(ContenuFxmlController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    btn.setText("Activer");
                } else {
                    try {
                        us.enabledUtilisateur(u.getId(), 0);
                    } catch (SQLException ex) {
                        Logger.getLogger(ContenuFxmlController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    btn.setText("Desactiver");
                }
            });

            hbI.addColonne(Colones[i]);
            i++;
            if (j == 0 || j % 2 == 0) {
                FXMLLoader loaderPage3 = new FXMLLoader(getClass().getResource("page3User.fxml"));
                listePages[nbrPage] = loaderPage3.load();
                pg = loaderPage3.getController();
            }
            if (User.size() > i) {
                System.out.println("test1.ContenuFxmlController.AfficherUser() i " + i);
                if (i % 3 == 0) {
                    pg.setVB(Items[j]);
                    j++;
                    System.out.println("test1.ContenuFxmlController.AfficherUser() j " + j);
                    if (j == 2) {
                        vboxC.getChildren().add(listePages[nbrPage]);
                    }
                    if (j % 2 == 0) {
                        listePages[nbrPage] = pg.getVB();
                        nbrPage++;
                    }
                }

            } else {
                if (i <= 6) {
                    vboxC.getChildren().add(listePages[nbrPage]);
                }
                pg.setVB(Items[j]);
                listePages[nbrPage] = pg.getVB();
                nbrPage++;
            }

        }
        nbrPage = 0;

    }

    @FXML
    private void precedent(MouseEvent event) {
        nbrPage -= 1;
        if (nbrPage < 0) {
            nbrPage = listePages.length - 1;
        }
        vboxC.getChildren().clear();
        vboxC.getChildren().add(listePages[nbrPage]);
    }

    @FXML
    private void suivant(MouseEvent event) {
        nbrPage++;
        if (nbrPage == listePages.length) {
            nbrPage = 0;
        }
        vboxC.getChildren().clear();
        vboxC.getChildren().add(listePages[nbrPage]);
    }

    public VBox getVbox1() {
        return vbox1;
    }

    public void setVbox1(VBox vbox1) {
        this.vbox1 = vbox1;
    }

    @FXML
    private void SearchUser(KeyEvent event) throws SQLException, IOException {
        userServices us = new userServices();
        List<Utilisateur> User = us.RechercheParNomUtilisateur(search.getText());
        vboxC.getChildren().clear();
        Node[] Items = new Node[(User.size() / 3) + 1];

        if (User.size() % 6 == 0) {
            listePages = new Node[User.size() / 6];
        } else {
            listePages = new Node[(User.size() / 6) + 1];
        }
        Node[] Colones = new Node[User.size()];
        int i = 0;
        int j = 0;
        FXMLLoader LoderItems = null;
        Page3UserController pg = new Page3UserController();
        Hbox_ItemsController hbI = new Hbox_ItemsController();
        for (Utilisateur u : User) {
            if (i % 3 == 0 || i == 0) {
                LoderItems = new FXMLLoader(getClass().getResource("Hbox_Items.fxml"));
                Items[j] = LoderItems.load();
                System.out.println("3mal item " + j);
            }
            hbI = LoderItems.getController();
            FXMLLoader loaderHbox = new FXMLLoader(getClass().getResource("Hbox.fxml"));
            Colones[i] = loaderHbox.load();
            HboxController hr = loaderHbox.getController();
            hr.setEmail(u.getEmail());
            hr.setUsername(u.getUsername());
            hr.setUser(u);
            hr.getUsername().setOnMouseClicked(e -> {
                try {
                    FXMLLoader loaderProfil = new FXMLLoader(getClass().getResource("../Profil/Profil.fxml"));
                    Node profil = loaderProfil.load();
                    ProfilController pfc = loaderProfil.getController();
                    pfc.setUser(u);
                    pfc.setAdresse(u.getAddresse());
                    pfc.setEmail(u.getEmail());
                    pfc.setNom(u.getNom());
                    pfc.setTelephone(u.getPhoneNumber());
                    pfc.setPrenom(u.getPrenom());
                    pfc.setImage(u.getImageProfil());
                    pfc.setBody(vbox1);
                    pfc.button();
                    vbox1.getChildren().clear();
                    vbox1.getChildren().add(profil);
                } catch (IOException ex) {
                    Logger.getLogger(ContenuFxmlController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            Button btn = hr.getBtn();
            if (u.getEnabled() == true) {
                btn.setText("Activer");
            } else {
                btn.setText("Desactiver");

            }
            btn.setOnAction(value -> {

                if (btn.getText() == "Desactiver") {
                    try {
                        us.enabledUtilisateur(u.getId(), 0);
                    } catch (SQLException ex) {
                        Logger.getLogger(ContenuFxmlController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    btn.setText("Activer");
                } else {
                    try {
                        us.enabledUtilisateur(u.getId(), 0);
                    } catch (SQLException ex) {
                        Logger.getLogger(ContenuFxmlController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    btn.setText("Desactiver");
                }
            });

            hbI.addColonne(Colones[i]);
            i++;
            if (j == 0 || j % 2 == 0) {
                FXMLLoader loaderPage3 = new FXMLLoader(getClass().getResource("page3User.fxml"));
                listePages[nbrPage] = loaderPage3.load();
                pg = loaderPage3.getController();
            }
            if (User.size() > i) {
                System.out.println("test1.ContenuFxmlController.AfficherUser() i " + i);
                if (i % 3 == 0) {
                    pg.setVB(Items[j]);
                    j++;
                    System.out.println("test1.ContenuFxmlController.AfficherUser() j " + j);
                    if (j == 2) {
                        vboxC.getChildren().add(listePages[nbrPage]);
                    }
                    if (j % 2 == 0) {
                        listePages[nbrPage] = pg.getVB();
                        nbrPage++;
                    }
                }

            } else {
                if (i <= 6) {
                    vboxC.getChildren().add(listePages[nbrPage]);
                }
                pg.setVB(Items[j]);
                listePages[nbrPage] = pg.getVB();
                nbrPage++;
            }

        }
        nbrPage = 0;

    }

}
