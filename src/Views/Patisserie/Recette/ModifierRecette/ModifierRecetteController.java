/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Recette.ModifierRecette;

import Entity.Commentaire;
import Entity.Recette;
import Entity.SessionUser;
import Entity.Thread;
import Entity.Utilisateur;
import Services.CommentaireService;
import Services.RecetteService;
import Views.Patisserie.Recette.MesRecettes.Commentaire.CommentaireController;
import java.io.File;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class ModifierRecetteController implements Initializable {

    private WebView description;
    @FXML
    private ImageView imageRecette;
    private Label username;
    private Label nbrComment;
    private Label moyenne;
    private Label nomRec;
    private Label CatRec;
    @FXML
    private TextArea commentaire;
    @FXML
    private Button Enregistrer;
    @FXML
    private VBox vbody;
    private int idRec ;
    @FXML
    private VBox bodyModif;
    private ModifierRecetteController csr ;
    private Recette rec ;
    @FXML
    private Label labCom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*description = new WebView();
        description.setId("description");
        description.setStyle("description");
        
        WebEngine engine = this.description.getEngine();
        engine.setUserStyleSheetLocation(getClass().getResource("../../../common.css").toExternalForm());*/
    }    

    public Recette getRec() {
        return rec;
    }

    public void setRec(Recette rec) {
        this.rec = rec;
    }

    public WebView getDescription() {
        return description;
    }

    public void setDescription(String description) {
        WebEngine webEngine = this.description.getEngine();
        webEngine.loadContent(description);
    }

    public ImageView getImageRecette() {
        return imageRecette;
    }

    public void setImageRecette(String imageRecette) {
        File file = new File("C:/wamp64/www/final/web/public/uploads/brochures/Recettes/" + imageRecette);
        this.imageRecette.setImage(new Image(file.toURI().toString()));
    }

    public Label getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public Label getNbrComment() {
        return nbrComment;
    }

    public void setNbrComment(String nbrComment) {
        this.nbrComment.setText(nbrComment);
    }

    public Label getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(String moyenne) {
        this.moyenne.setText(moyenne);
    }

    public Label getNomRec() {
        return nomRec;
    }

    public void setNomRec(String nomRec) {
        this.nomRec.setText(nomRec);
    }

    public Label getCatRec() {
        return CatRec;
    }

    public void setCatRec(String CatRec) {
        this.CatRec.setText(CatRec);
    }

    public TextArea getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(TextArea commentaire) {
        this.commentaire = commentaire;
    }

    public VBox getVbody() {
        return vbody;
    }

    public void setVbody(Node vbody) {
        this.vbody.getChildren().add(vbody);
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public VBox getBodyModif() {
        return bodyModif;
    }

    public void setBodyModif(Node bodyModif) {
        this.bodyModif.getChildren().add(bodyModif);
    }

    public ModifierRecetteController getCsr() {
        return csr;
    }

    public void setCsr(ModifierRecetteController csr) {
        this.csr = csr; // cv hehdi tet3abba
    }
    
    @FXML
    private void AjouterCommentaire(ActionEvent event) throws SQLException, IOException {
        CommentaireService cs = new CommentaireService();
        Utilisateur idUser = new Utilisateur(SessionUser.getId());
        Thread thread = new Thread(String.valueOf(idRec));
        System.out.println("idRec = "+idRec);
        Commentaire com = new Commentaire(commentaire.getText(), 0, 0,thread, idUser);
        if (com.getBody().length() < 2){
            labCom.setVisible(true);
            return ;
        }
        else
            labCom.setVisible(false);
        vbody.getChildren().clear();
        cs.AjouterCommentaire(com);
        commentaire.clear();
        List<Commentaire> listCom = cs.AfficherCommentaire(String.valueOf(idRec));
        AfficherCommentaireReplay(listCom, cs, "", null);
    }

    public void AfficherCommentaireReplay(List<Commentaire> listCom,CommentaireService cs, String ancestor,Commentaire com) throws IOException, SQLException{
        
        while(/*listCom.get(0) != null*/ listCom.size() != 0)
        {
            
            if(listCom.get(0).getAncestors().equals(""))
                ancestor= listCom.get(0).getIdCmnt().toString();
            else{
                    ancestor = listCom.get(0).getAncestors()+"/"+listCom.get(0).getIdCmnt().toString();
                }
            List<Commentaire> listC = cs.findAncestors(ancestor);
            for(Commentaire c : listC)
                listCom.remove(c);
            FXMLLoader loaderCommentaire = new FXMLLoader(getClass().getResource("../MesRecettes/Commentaire/Commentaire.fxml"));
            Node Comment = loaderCommentaire.load();
            CommentaireController cc = loaderCommentaire.getController();
            cc.setMrc(csr);
            
            if(SessionUser.getId() != listCom.get(0).getIdUser().getId())
                cc.getModifier().setVisible(false);
            if(SessionUser.getId() != rec.getIdRec() && SessionUser.getId() != listCom.get(0).getIdUser().getId())
                cc.getSupprimer().setVisible(false);
            
            cc.setIdCmnt(listCom.get(0).getIdCmnt());
            cc.getCommentaire().setPadding(new Insets(10, 0, 0, ancestor.split("/").length *15));
            cc.setVbody(vbody);
            cc.setAncestors(ancestor);
            System.out.println("id rec commentaire = "+idRec);
            cc.setIdRec(String.valueOf(idRec));
            cc.setBodyCom(listCom.get(0).getBody());
            cc.setUsername(listCom.get(0).getIdUser().getUsername());
            cc.setDate("- "+listCom.get(0).getCreatedAt().toString());
            setVbody(Comment);
            if (listC.size() != 0)
            {
                AfficherCommentaireReplay(listC, cs,ancestor,listCom.get(0));
            }
            listCom.remove(0);
        }
        
    }

}
