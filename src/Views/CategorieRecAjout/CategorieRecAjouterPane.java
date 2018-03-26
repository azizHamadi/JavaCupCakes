/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.CategorieRecAjout;

import Entity.CategorieRec;
import Services.CategorieRecetteService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author aziz
 */
public class CategorieRecAjouterPane extends AnchorPane implements Initializable{
    
    @FXML
    private Button AjouterBt;
    @FXML
    private Button AnnulerBt;
    @FXML
    private TextField CatRecNom;
    @FXML
    private TableView<CategorieRec> CatRecTable;
    @FXML
    private TableColumn<CategorieRec,String> nomCatRec;
    @FXML
    private ComboBox<String> cb;

    /**
     * Initializes the controller class.
     */
   

    @FXML
    private void AjouterCatRec(ActionEvent event) throws SQLException {
        CategorieRecetteService catRS = new CategorieRecetteService();
        CategorieRec CatRec = new CategorieRec(CatRecNom.getText());
        try {
            catRS.AjouterCategorieRecette(CatRec);
            CatRecTable.setItems(catRS.getListeCatRec());
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRecAjouterPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public TableView<CategorieRec> getCatRecTable() {
        return CatRecTable;
    }

    public void setCatRecTable(TableView<CategorieRec> CatRecTable) {
        this.CatRecTable = CatRecTable;
    }

    public TableColumn<CategorieRec, String> getNomCatRec() {
        return nomCatRec;
    }

    public void setNomCatRec(TableColumn<CategorieRec, String> nomCatRec) {
        this.nomCatRec = nomCatRec;
    }

    @FXML
    private void AnnulerCatRec(ActionEvent event) throws SQLException {
        CategorieRecetteService catRS = new CategorieRecetteService();
        System.out.println("aaaaaaaaaaaaaaaaaaaaaa");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            CategorieRecetteService catRS = new CategorieRecetteService();
            List<CategorieRec> listCat = catRS.AfficherCategorieRecette();
            int i = 0 ;
            for(CategorieRec c : listCat)
            {
                if (i == 2)
                    return ;
                cb.getItems().add(c.getNomCatRec());
                i++;    
            }
        nomCatRec.setCellValueFactory(new PropertyValueFactory<CategorieRec,String>("nomCatRec"));
        CatRecTable.setItems(catRS.getListeCatRec());

        } catch (SQLException ex) {
            Logger.getLogger(CategorieRecAjouterPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
