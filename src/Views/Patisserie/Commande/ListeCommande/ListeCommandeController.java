/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Commande.ListeCommande;


import Entity.Commande;
import Entity.LineCmd;
import Entity.Session;
import Entity.SessionUser;
import Services.CommandeService;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.SeekableByteChannel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class ListeCommandeController implements Initializable {

    @FXML
    private TableView<Commande> TableCommande;
    @FXML
    private TableColumn<Commande, Date> DateL;
    @FXML
    private TableColumn<Commande, String> adrLiv;
    @FXML
    private TableColumn<Commande, String> Etat;
    @FXML
    private TableColumn<Commande, Integer> idCmd;
    @FXML
    private JFXComboBox<String> EtatLiv;
    private ImageView imageview;
    @FXML
    private TextField id;
    private Text NomProduit;
    private Text QteAcheter;
    @FXML
    private Text NomUtilisateur;
    @FXML
    private TableColumn<Commande, Double> Montant;
    String   imgp= "";
    @FXML
    private VBox Produit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
        try {
             EtatLiv.getItems().add("En cours");
        EtatLiv.getItems().add("Prete");
        EtatLiv.getItems().add("Livrée");
            RefreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(ListeCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
  //vider les champs
    public void ClearFields()
    {
        id.clear();
        Produit.getChildren().clear();
        imageview.setImage(null);
        EtatLiv.getItems().clear();
    }
    //affichage des champs dans les textfiels a partir du table 
    @FXML
    private void FetcherTable(MouseEvent event) throws SQLException, IOException {
        CommandeService ser = new CommandeService();
                Produit.getChildren().clear();
        List<LineCmd> listeL = ser.AfficherLineCommande(TableCommande.getSelectionModel().getSelectedItem().getIdCmd());
        EtatLiv.setValue(TableCommande.getSelectionModel().getSelectedItem().getEtatLivCmd());
        for(LineCmd l : listeL){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProdCmd.fxml"));
            Node pr = loader.load();
            ProdCmdController msc = loader.getController();
            Produit.getChildren().add(pr);
             msc.setNomProduit(l.getProduit().getNomProd());
              msc.setQteAcheter(l.getQteAcheter().toString());
              msc.setImageview(l.getProduit().getImageprod());
     
        }
        id.setText(TableCommande.getSelectionModel().getSelectedItem().getIdCmd().toString());
      NomUtilisateur.setText(TableCommande.getSelectionModel().getSelectedItem().getIdUser().getUsername());
   }
    //afficher le contenu de la table formation dans le tableau
   public void RefreshTable() throws SQLException
   {/*SessionUser.getId()*/
        TableCommande.getItems().clear();
        CommandeService service=new CommandeService();
        ObservableList<Commande> listC = FXCollections.observableArrayList(service.AfficherCommandeBack(SessionUser.getId()));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etatLivCmd"));
        adrLiv.setCellValueFactory(new PropertyValueFactory<>("addLiv"));
        idCmd.setCellValueFactory(new PropertyValueFactory<>("idCmd"));
        Montant.setCellValueFactory(new PropertyValueFactory<>("montantCmd"));
        DateL.setCellValueFactory(new PropertyValueFactory<>("dateLivCmd"));
        TableCommande.setItems(listC);
   }
    @FXML
        private void Modifier(ActionEvent event) throws SQLException {
         CommandeService service = new CommandeService();
         Commande c = new Commande(Integer.parseInt(id.getText()),EtatLiv.getValue());
         service.ModifierEtatCommande(c);
         RefreshTable();
    }

    

    @FXML
    private void SupprimerCommande(ActionEvent event) throws SQLException {
          CommandeService service = new CommandeService();
         Commande c = new Commande(Integer.parseInt(id.getText()));
         service.SupprimerComande(c);
         RefreshTable();
    }

    
}
