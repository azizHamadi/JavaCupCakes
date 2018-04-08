/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Formateur.GestionFormations;

import Entity.Formation;
import Entity.TypeFormation;
import Services.ServiceFormation;
import Services.ServiceTypeFormation;
import Views.test.folder.BackForTemplateController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author FERIEL FADHLOUN
 */
public class CRUDFormationsController implements Initializable {
  
    @FXML
    private TableColumn<Formation, String> columnImage;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtplace;
    @FXML
    private JFXComboBox<TypeFormation> combotypeformation;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField id;
    @FXML
    private JFXButton btnBrowseImage;
    @FXML
    private JFXTextField txtimage;
    @FXML
    private JFXButton btnListeSession;
    @FXML
    private Label labelnom;
    @FXML
    private Label labelplace;
    @FXML
    private Label labeltypeformation;
    @FXML
    private Label labeldatedebut;
    @FXML
    private Label labelimage;
    @FXML
    private Label labeldate_actuelle;
    @FXML
    private Label labeldescription;
    @FXML
    private TableView<Formation> tableFormation;
    @FXML
    private TableColumn<Formation, String> columnPays;
    @FXML
    private TableColumn<Formation, TypeFormation> columntypeformation;
    @FXML
    private TableColumn<Formation, DatePicker> columndate;
    @FXML
    private TableColumn<Formation, Integer> columnidFormation;
    @FXML
    private TableColumn<Formation, String> columnNom;
     private Image image;
Stage stage;
    File fileinputStream;
    ObservableList<TypeFormation> listtypefor;
        final ObservableList options=FXCollections.observableArrayList();

    String imgf="";
    String fdS ="";
    String fdS1 ="";
    private FileChooser fileChooser;
    private File file;
    private Desktop desktop=Desktop.getDesktop();
    @FXML
    private JFXDatePicker txtdate;
    
    @FXML
    private JFXButton btnModifierFormation;
    @FXML
    private JFXButton btnSuprrimerFormation;
    @FXML
    private JFXButton btnAjouterFormatino;
    private HTMLEditor txtDescription;
    @FXML
    private JFXButton RefreshTable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
                btnListeSession.setVisible(false);
                btnModifierFormation.setVisible(false);
                btnSuprrimerFormation.setVisible(false);
                btnAjouterFormatino.setVisible(true);
            combotypeformation.getItems().clear();
            ServiceTypeFormation serviceTypeFor = new ServiceTypeFormation();
            listtypefor = serviceTypeFor.getListeTypeFor();
            //combobox typeformation
            for(TypeFormation c : listtypefor)
            {
                combotypeformation.getItems().add(c);
                combotypeformation.setConverter(new StringConverter<TypeFormation>() {
                    @Override
                    public String toString(TypeFormation object) {
                        return object.getNomTypeFor();
                    }

                    @Override
                    public TypeFormation fromString(String string) {
                        return combotypeformation.getItems().stream().filter(ap -> 
                                    ap.getNomTypeFor().equals(string)).findFirst().orElse(null);}
                });
            }
            fileChooser=new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("images", "*.jpg","*.png","*.jpeg"));
            RefreshTable();
              
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }    

    @FXML
    private void FetcherTable(MouseEvent event) {
                btnListeSession.setVisible(true);
                btnModifierFormation.setVisible(true);
                btnSuprrimerFormation.setVisible(true);
                btnAjouterFormatino.setVisible(false);
        ClearFields();
        System.out.println("hello");
        txtNom.setText(tableFormation.getSelectionModel().getSelectedItem().getNomFor());
        txtplace.setText(tableFormation.getSelectionModel().getSelectedItem().getPlace());
        txtDescription.setHtmlText(tableFormation.getSelectionModel().getSelectedItem().getDescriptionFor());
        
        String dateformation=tableFormation.getSelectionModel().getSelectedItem().getDateFor().toString();
        ServiceFormation sf=new ServiceFormation();
        String year=dateformation.substring(0,4);
        String month=dateformation.substring(5,7);
        String date=dateformation.substring(8,10);
        System.out.println("year="+year);
        System.out.println("month="+month);
        System.out.println("day="+date);
        //System.out.println("id mel type for "+sf.ReturnIdtypeForFromBynom(taableView.getSelectionModel().getSelectedItem().getIdTypeFor().toString()));
        txtdate.getEditor().setText(date+"/"+month+"/"+year);
        txtdate.setValue(LocalDate.parse(tableFormation.getSelectionModel().getSelectedItem().getDateFor().toString()));
        System.out.println("ahaya el type formatiion"+tableFormation.getSelectionModel().getSelectedItem().getIdTypeFor());
       
        combotypeformation.setValue(tableFormation.getSelectionModel().getSelectedItem().getIdTypeFor());
        System.out.println(combotypeformation.getValue().getIdTypeFor());
        imgf=tableFormation.getSelectionModel().getSelectedItem().getImageform();
        if(!imgf.isEmpty())
        { txtimage.setText(imgf);}
        image=new Image("file:///C:/wamp64/www/final/web/public/uploads/brochures/Formateur/"+tableFormation.getSelectionModel().getSelectedItem().getImageform(),imageview.getFitWidth(),imageview.getFitHeight(),true,true);
        imageview.setImage(image);
        btnListeSession.setVisible(true);
    }

    @FXML
    private void ModifierFormation(ActionEvent event) {
         ServiceFormation service=new ServiceFormation();
        try {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modification Formation");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous vraiment modifier cette formation!!");
            alert.showAndWait();
            System.out.println("id formation mel tableau"+tableFormation.getSelectionModel().getSelectedItem().getIdFor());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
               {
                    Formation f=new Formation(txtNom.getText(),txtplace.getText(),txtDescription.getHtmlText().substring(58, txtDescription.getHtmlText().length()-14),java.sql.Date.valueOf(txtdate.getValue()),imgf,combotypeformation.getValue());
                    service.ModifierFormation(f,tableFormation.getSelectionModel().getSelectedItem().getIdFor());
                    RefreshTable();
                    ClearFields();
               }
            } else {
                alert.close();
            }
            
            
        } catch (SQLException ex) {
ex.printStackTrace();        }
    }

    @FXML
    private void SupprimerFormation(ActionEvent event) {
         ServiceFormation service=new ServiceFormation();
        
        try {
            //date yekhou mel table or howa yelzm yekhou mel textfield
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer Formation");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous vraiment supprimer cette formation!!");
            alert.showAndWait();
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
               {
                    Formation f=new Formation();
                    service.SupprimerFormation(f,tableFormation.getSelectionModel().getSelectedItem().getIdFor());
                    RefreshTable();
                    ClearFields();  
               }
            } else {
                alert.close();
               
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleFileImage(ActionEvent event) {
         stage=(Stage) anchorPane.getScene().getWindow();
        file=fileChooser.showOpenDialog(stage);
       /* try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(AjoutFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
       if(file !=null)
       {
           System.out.println("file path="+file.getAbsolutePath());
           System.out.println("file url="+file.getAbsoluteFile().toURI().toString());
           image=new Image(file.getAbsoluteFile().toURI().toString(),imageview.getFitWidth(),imageview.getFitHeight(),true,true);
           imageview.setImage(image);
           txtimage.setText(file.getName());
           imgf = file.getName();
           fdS = file.getAbsolutePath();
           fdS1 = ("C:/wamp64/www/final/web/public/uploads/brochures/Formateur/"+file.getName());
           //lab_image.setVisible(false);
                 
       }
    }
    
    //controle de saisie des champs
    public boolean ValidateFields()
    {
       int img = 0,nom = 0,combo = 0,desc = 0 ,date = 0,place=0;
        if (imgf.isEmpty())
        {
            img = 1;
            labelimage.setVisible(true);
        }
        else
            labelimage.setVisible(false);
        
        if (txtNom.getText().length()==0)
        {
            nom = 1 ;
            labelnom.setVisible(true);
        }
        else
            labelnom.setVisible(false);
        if (txtplace.getText().length()==0)
        {
            place = 1 ;
            labelplace.setVisible(true);
        }
        else
            labelplace.setVisible(false);
        if (combotypeformation.getValue() == null)
        {
            combo = 1 ;
            labeltypeformation.setVisible(true);
        }
        else
            labeltypeformation.setVisible(false);
        
        if (txtDescription.getHtmlText().length() == 72)
        {
            desc = 1 ;
            labeldescription.setVisible(true);
        }
        else
            labeldescription.setVisible(false);
        
        if(txtdate.getEditor().getText().length()==0 )
        {
            date = 1 ;
            labeldatedebut.setVisible(true);
            labeldate_actuelle.setVisible(false);
           
        }
        else if( (txtdate.getValue().isBefore(LocalDate.now())))
        {
            System.out.println("erreur khater date kbal date lyoum");
            date = 1 ;
            labeldate_actuelle.setVisible(true);
            labeldatedebut.setVisible(false); 
        }
        else
        { 
            labeldatedebut.setVisible(false); 
            labeldate_actuelle.setVisible(false);
            System.out.println(txtdate.getValue());
            System.out.println(LocalDate.now());
        }   
         
        return ( img==1 || nom==1 || combo==1 || desc==1 || date == 1 || place==1);
    }
    //vider les champs
    public void ClearFields()
    {
        txtNom.clear();
        txtplace.clear();
        txtDescription.setHtmlText(null);
        imageview.setImage(null);
        txtdate.setValue(null);
        combotypeformation.setValue(null);
        txtimage.clear();
    }

    @FXML
    private void AjouterFormation(ActionEvent event) {
        ServiceFormation service=new ServiceFormation();
        try {
            File f1 = new File(fdS);
            File f2 = new File(fdS1);
            if (ValidateFields())
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Attention!");
                    alert.setHeaderText(null);
                    alert.setContentText("veuillez remplir tous les champs!");
                    alert.showAndWait();
                   return;
                }
            else
                {
                    Formation f=new Formation(txtNom.getText(),txtplace.getText(),txtDescription.getHtmlText().substring(58, txtDescription.getHtmlText().length()-14),java.sql.Date.valueOf(txtdate.getValue()),imgf,combotypeformation.getValue());

                    Files.copy(f1.getAbsoluteFile().toPath(),f2.getAbsoluteFile().toPath());

                    service.insertFormation(f);
                    System.out.println("formation ajouté avec succes");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ajout");
                    alert.setHeaderText(null);
                    alert.setContentText("formation ajouté avec succes");
                    alert.showAndWait();
                    ClearFields(); 
                    RefreshTable();
                }
           /* Scene sc = new Scene(root);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //afficher le contenu de la table formation dans le tableau
    @FXML
   public void RefreshTable() throws SQLException 
   {
       
        ServiceFormation service=new ServiceFormation();

        columnidFormation.setCellValueFactory(new PropertyValueFactory<>("idFor"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nomFor"));
        columnPays.setCellValueFactory(new PropertyValueFactory<>("place"));
        //ColumnDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionFor"));
        columndate.setCellValueFactory(new PropertyValueFactory<>("dateFor"));
        columnImage.setCellValueFactory(new PropertyValueFactory<>("imageform"));
        columntypeformation.setCellValueFactory(new PropertyValueFactory<>("idTypeFor"));
        tableFormation.setItems(service.AfficherListeFormation());
   }
   

    @FXML
    private void InvisibleBouton(MouseEvent event) {
            btnListeSession.setVisible(false);
            btnModifierFormation.setVisible(false);
            btnSuprrimerFormation.setVisible(false);
            btnAjouterFormatino.setVisible(true);
    }

    @FXML
    private void AutrePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionByIDFor.fxml"));       
        Parent root = loader.load();   
        SessionByIDForController sc = loader.getController();
        sc.setIdFormation(tableFormation.getSelectionModel().getSelectedItem().getIdFor());
        //sc.setVbox(root);       
        txtNom.getScene().setRoot(root);
    }

    @FXML
    private void SearchFormation(KeyEvent event) {
           ServiceFormation service=new ServiceFormation();
        try {
            tableFormation.setItems(service.SearchListeFormation(txtSearch.getText()));
            System.out.println("rechercher");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}

    