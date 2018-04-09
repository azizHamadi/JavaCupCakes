/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Patisserie.Recette.Commentaire;

import Entity.Commentaire;
import Services.CommentaireService;
import Views.Patisserie.Recette.SingleRecette.SingleRecetteController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class ModifierCommentaireController implements Initializable {

    @FXML
    private VBox commentaire;
    @FXML
    private VBox vboxReplay;
    @FXML
    private VBox vreplay;
    @FXML
    private Button Enregistrer;
    @FXML
    private TextArea Mcommentaire;
    private int idCmnt;
    private String ancestors ;
    private String idRec;
    private SingleRecetteController sRc ;
    private VBox vbody;
    @FXML
    private Label labCom;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifierCommentaire(ActionEvent event) throws SQLException, IOException {
        CommentaireService cs = new CommentaireService();
        Commentaire com = new Commentaire(Mcommentaire.getText(),idCmnt);
        if (com.getBody().length() < 2){
            labCom.setVisible(true);
            return ;
        }
        cs.UpdateCommentaire(com);
        List<Commentaire> listCom = cs.AfficherCommentaire(idRec);
        vbody.getChildren().clear();
        sRc.AfficherCommentaireReplay(listCom, cs, "", null);
    }

    public int getIdCmnt() {
        return idCmnt;
    }

    public void setIdCmnt(int idCmnt) {
        this.idCmnt = idCmnt;
    }

    public TextArea getMcommentaire() {
        return Mcommentaire;
    }

    public void setMcommentaire(String Mcommentaire) {
        this.Mcommentaire.setText(Mcommentaire);
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
}
