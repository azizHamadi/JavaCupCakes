/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Client.Recette.Commentaire;

import Entity.Commentaire;
import Services.CommentaireService;
import Views.Client.Recette.ModifierRecette.ModifierRecetteController;
import Views.Client.Recette.SingleRecette.SingleRecetteController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class CommentaireController implements Initializable {

    @FXML
    private VBox commentaire;
    @FXML
    private Label date;
    @FXML
    private Label username;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    @FXML
    private Label bodyCom;
    @FXML
    private Button replay;
    @FXML
    private AnchorPane anchorComment;
    @FXML
    private VBox vboxReplay;
    private String ancestors ;
    private String idRec;
    @FXML
    private VBox vreplay;
    private SingleRecetteController sRc ;
    private ModifierRecetteController mrc ;
    private VBox vbody ;
    private int idCmnt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public VBox getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(VBox commentaire) {
        this.commentaire = commentaire;
    }

    public Label getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date.setText(date);
    }

    public Label getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public Button getModifier() {
        return Modifier;
    }

    public void setModifier(Button Modifier) {
        this.Modifier = Modifier;
    }

    public Button getSupprimer() {
        return Supprimer;
    }

    public void setSupprimer(Button Supprimer) {
        this.Supprimer = Supprimer;
    }

    public Label getBodyCom() {
        return bodyCom;
    }

    public void setBodyCom(String bodyCom) {
        this.bodyCom.setText(bodyCom);
    }

    public Button getReplay() {
        return replay;
    }

    public void setReplay(Button replay) {
        this.replay = replay;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getIdRec() {
        return idRec;
    }

    public void setIdRec(String idRec) {
        this.idRec = idRec;
    }

    public SingleRecetteController getsRc() {
        return sRc;
    }

    public void setsRc(SingleRecetteController sRc) {
        this.sRc = sRc;
    }

    public VBox getVbody() {
        return vbody;
    }

    public void setVbody(VBox vbody) {
        this.vbody = vbody;
    }

    public int getIdCmnt() {
        return idCmnt;
    }

    public void setIdCmnt(int idCmnt) {
        this.idCmnt = idCmnt;
    }

    public ModifierRecetteController getMrc() {
        return mrc;
    }

    public void setMrc(ModifierRecetteController mrc) {
        this.mrc = mrc;
    }

    @FXML
    private void ModifierCommentaire(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loaderReplay = new FXMLLoader(getClass().getResource("ModifierCommentaire.fxml"));
        Parent root = loaderReplay.load();
        ModifierCommentaireController vc = loaderReplay.getController();
        CommentaireService cs = new CommentaireService();
        Commentaire com = cs.findByIdCom(idCmnt);
        vc.setMcommentaire(com.getBody());
        vc.setIdCmnt(idCmnt);
        vc.setsRc(sRc);
        vc.setAncestors(ancestors);
        vc.setIdRec(idRec);
        vc.setVbody(vbody);
        vboxReplay.getChildren().add(root);
    }

    @FXML
    private void SupprimerCommentaire(ActionEvent event) throws SQLException, IOException {
        CommentaireService cs = new CommentaireService();
        Commentaire com = new Commentaire(idCmnt,1);
        cs.SupprimerCommentaire(com);
        vbody.getChildren().clear();
        sRc.AfficherCommentaireReplay(cs.AfficherCommentaire(idRec), cs, "", null);
    }

    @FXML
    private void AfficherCommentaire(ActionEvent event) throws IOException {
        FXMLLoader loaderReplay = new FXMLLoader(getClass().getResource("Vcommentaire.fxml"));
        Parent root = loaderReplay.load();
        VcommentaireController vc = loaderReplay.getController();
        vc.setsRc(sRc);
        vc.setAncestors(ancestors);
        vc.setIdRec(idRec);
        vc.setVbody(vbody);
        vboxReplay.getChildren().add(root);

    }
}
