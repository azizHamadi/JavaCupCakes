/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Commande.CmdSingle;

import Entity.Commande;
import Entity.LineCmd;
import Entity.Produit;
import Services.CommandeService;
import Services.PanierService;
import Views.Client.Commande.Feedback.FeedbackController;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author escobar
 */
public class CmdSingleController implements Initializable {

    @FXML
    private VBox ListeProd;
    @FXML
    private Text Adr;
    @FXML
    private Text adrLiv;
    @FXML
    private Text Montant;
    @FXML
    private Text etatLiv;
    private Commande cmd;
    private LineCmd line;
    @FXML
    private AnchorPane body;
    public VBox getListeProd() {
        return ListeProd;
    }

    public void setListeProd(VBox ListeProd) {
        this.ListeProd = ListeProd;
    }

    public Text getAdr() {
        return Adr;
    }

    public void setAdr(String Adr) {
        this.Adr.setText(Adr);
    }

    public Text getAdrLiv() {
        return adrLiv;
    }

    public void setAdrLiv(String adrLiv) {
        this.adrLiv.setText(adrLiv);
    }

    public Text getMontant() {
        return Montant;
    }

    public void setMontant(String Montant) {
        this.Montant.setText(Montant);
    }

    public Text getEtatLiv() {
        return etatLiv;
    }

    public void setEtatLiv(String etatLiv) {
        this.etatLiv.setText(etatLiv);
    }

    public Commande getCmd() {
        return cmd;
    }

    public void setCmd(Commande cmd) {
        this.cmd = cmd;
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //   Controle();
    }    

    @FXML
    private void Pdf(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
         new FileChooser.ExtensionFilter( "PDF file","*.pdf"));
            fileChooser.setInitialFileName("Facture"); 
        File f = fileChooser.showSaveDialog(null);
         if (f != null){
             String s = f.getAbsolutePath();
             Document documet = new Document(PageSize.A4);
            documet.addAuthor("CupCakes");
            documet.addTitle("Facture de la commande");
            try{
                PdfWriter.getInstance(documet, new FileOutputStream(f));
                documet.open();
                Paragraph para = new Paragraph("La facture de votre Commande");
                documet.add(para);
                Paragraph p1 = new Paragraph("Le montant de votre Commande est"+Montant.getText()+"la date de Livraison est"+Adr.getText()+"l'adresse de livraison est "+adrLiv.getText());
                documet.add(p1);
                Paragraph p2 = new Paragraph("les Produit acheter sont:");
                documet.add(p2);
                CommandeService Cmd = new CommandeService();
                List<LineCmd> listL = Cmd.AfficherLineCommande(cmd.getIdCmd());         
                            for(LineCmd l : listL){
                            Paragraph p3 = new Paragraph("Nom du Produit "+l.getProduit().getNomProd()+" la quantité est "+l.getQteAcheter());
                documet.add(p3);
                            }
            } 
            catch(Exception e){
                System.out.println(e);
            }
            documet.close();
     System.out.println(f.getName());
                     }
            
    }

    @FXML
    private void Feedback(ActionEvent event) throws IOException {
        body.getChildren().clear();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../Feedback/Feedback.fxml"));
                        Node root = loader1.load();
                        FeedbackController c2 =loader1.getController();
                        body.getChildren().add(root);
                        c2.setCmd(cmd);
    }

    }

   
        
    

