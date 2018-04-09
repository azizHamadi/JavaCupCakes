/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Commande.ListeFeedback;

import Entity.Commande;
import Entity.LineCmd;
import Services.CommandeService;
import Views.Patisserie.Commande.ListeCommande.ProdCmdController;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class DetailCmdFeedbackController implements Initializable {

    @FXML
    private Text DataLiv;
    @FXML
    private Text Adr;
    @FXML
    private Text Etat;
    @FXML
    private Text Montant;
    @FXML
    private Text user;
    @FXML
    private VBox produit;
    private int idcmd ;
    @FXML
    private AnchorPane body;
    
    public Text getDataLiv() {
        return DataLiv;
        // TODO
    }    

    public void setDataLiv(String DataLiv) {
        this.DataLiv.setText(DataLiv);
    }

    public Text getAdr() {
        return Adr;
    }

    public void setAdr(String Adr) {
        this.Adr.setText(Adr);
    }

    public Text getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat.setText(Etat);
    }

    public Text getMontant() {
        return Montant;
    }

    public void setMontant(String Montant) {
        this.Montant.setText(Montant);
    }

    public Text getUser() {
        return user;
    }

    /**
     * Initializes the controller class.
     */
    public void setUser(String user) {    
        this.user.setText(user);
    }

    public int getIdcmd() {
        return idcmd;
    }

    public void setIdcmd(int idcmd) {
        this.idcmd = idcmd;
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void afficher ()
    {
        try {
            CommandeService ser = new CommandeService();
            produit.getChildren().clear();
            List<LineCmd> listeL = ser.AfficherLineCommande(idcmd);
            System.out.println(idcmd);
            for(LineCmd l : listeL){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ListeCommande/ProdCmd.fxml"));
                Node pr = loader.load();
                ProdCmdController msc = loader.getController();
                msc.setNomProduit(l.getProduit().getNomProd());
                msc.setQteAcheter(l.getQteAcheter().toString());
                msc.setImageview(l.getProduit().getImageprod());
                produit.getChildren().add(pr);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailCmdFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetailCmdFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void Retour(ActionEvent event) throws IOException {
        body.getChildren().clear();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ListeFeedback.fxml"));
                        Node root = loader1.load();
                        body.getChildren().add(root);
                        
    }
    
}
