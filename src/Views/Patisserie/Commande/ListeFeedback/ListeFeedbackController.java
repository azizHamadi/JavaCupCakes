/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Commande.ListeFeedback;

import Views.Patisserie.Commande.ListeCommande.*;
import Views.Patisserie.Produit.ListeProduit.*;
import Entity.Categorie;
import Entity.Commande;
import Entity.FeedBack;
import Entity.LineCmd;
import Entity.Produit;
import Services.CategorieService;
import Services.CommandeService;
import Services.FeedbackService;
import Services.ProduitService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class ListeFeedbackController implements Initializable {

    @FXML
    private TableView<FeedBack> TableCommande;
    @FXML
    private Text Sujet;
    @FXML
    private Text Description;
    @FXML
    private TableColumn<FeedBack, String> sujet;
    @FXML
    private JFXTextField idCmd;
    @FXML
    private AnchorPane body;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
       
        try {
            RefreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(ListeFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    
  //vider les champs
    public void ClearFields()
    {
       Description.setText("");
       Sujet.setText("");

    }
    //affichage des champs dans les textfiels a partir du table 
    @FXML
    private void FetcherTable(MouseEvent event) throws SQLException, IOException {
        FeedbackService ser = new FeedbackService();
        List<FeedBack> listeF = ser.AfficherFeedBack();
       Description.setText(TableCommande.getSelectionModel().getSelectedItem().getDescription());
       Sujet.setText(TableCommande.getSelectionModel().getSelectedItem().getSujet());
       idCmd.setText(TableCommande.getSelectionModel().getSelectedItem().getIdCmd().getIdCmd().toString());
       
       
   }
    //afficher le contenu de la table formation dans le tableau
   public void RefreshTable() throws SQLException
   {
        TableCommande.getItems().clear();
        FeedbackService ser = new FeedbackService();
        ObservableList<FeedBack> listF = FXCollections.observableArrayList(ser.AfficherFeedBack());
         sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        TableCommande.setItems(listF);
   }

    @FXML
    private void AffciherCmd(ActionEvent event) throws IOException {
        body.getChildren().clear();
                             System.out.println(TableCommande.getSelectionModel().getSelectedItem().getIdCmd());

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("DetailCmdFeedback.fxml"));
                        Node root = loader1.load();
                        DetailCmdFeedbackController c2 =loader1.getController();
                        c2.setIdcmd(TableCommande.getSelectionModel().getSelectedItem().getIdCmd().getIdCmd());
                        c2.setAdr(TableCommande.getSelectionModel().getSelectedItem().getIdCmd().getAddLiv());
                        c2.setDataLiv(TableCommande.getSelectionModel().getSelectedItem().getIdCmd().getDateLivCmd().toString());
                        c2.setMontant(TableCommande.getSelectionModel().getSelectedItem().getIdCmd().getMontantCmd().toString());
                       // c2.setUser(TableCommande.getSelectionModel().getSelectedItem().getIdCmd().getIdUser().getUsername());
                       c2.afficher();
                       body.getChildren().add(root);
    }
     

    

    
}
