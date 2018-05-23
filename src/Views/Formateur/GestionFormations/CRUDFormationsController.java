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
import Services.SessionService;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    @FXML
    private HTMLEditor txtDescription;
    private VBox vbox ;
    @FXML
    private JFXButton btnRefresh;
    @FXML
    private Button btnAjoutTypeFor;
    @FXML
    private JFXTextField txtlong;
    @FXML
    private JFXTextField txtatitude;
    @FXML
    private Label labellong;
    @FXML
    private Label labellat;
    @FXML
    private TableColumn<Formation, String> columnlong;
    @FXML
    private TableColumn<Formation, String> columnLat;
    @FXML
    private TableColumn<Formation, String> columnDescription;
    
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
        
        txtNom.setText(tableFormation.getSelectionModel().getSelectedItem().getNomFor());
        txtDescription.setHtmlText(tableFormation.getSelectionModel().getSelectedItem().getDescriptionFor());       
        txtatitude.setText(tableFormation.getSelectionModel().getSelectedItem().getAtitude());
        txtlong.setText(tableFormation.getSelectionModel().getSelectedItem().getLongitude());
        txtdate.setValue(LocalDate.parse(tableFormation.getSelectionModel().getSelectedItem().getDateFor().toString()));
       
        combotypeformation.setValue(tableFormation.getSelectionModel().getSelectedItem().getIdTypeFor());
        System.out.println(combotypeformation.getValue().getIdTypeFor());
        imgf=tableFormation.getSelectionModel().getSelectedItem().getImageform();
        if(!imgf.isEmpty())
        { txtimage.setText(imgf);}
        image=new Image("file:///C://wamp64//www//final//public//uploads//brochures//Formateur"+tableFormation.getSelectionModel().getSelectedItem().getImageform(),imageview.getFitWidth(),imageview.getFitHeight(),true,true);
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
                    Formation f=new Formation(txtNom.getText(),txtDescription.getHtmlText().substring(58, txtDescription.getHtmlText().length()-14),java.sql.Date.valueOf(txtdate.getValue()),imgf,combotypeformation.getValue(),txtlong.getText(),txtatitude.getText());
                    service.ModificationFormation(f,tableFormation.getSelectionModel().getSelectedItem().getIdFor());
                    ClearFields();
                    RefreshTable();
                    
               }
            } else {
                alert.close();
            }
            
            
        } catch (SQLException ex)
        {
                ex.printStackTrace();       
        }
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
           fdS1 = ("C://wamp64//www//final//web//public//uploads//brochures//Formateur//"+file.getName());
           //lab_image.setVisible(false);
                 
       }
    }
    
    //controle de saisie des champs
    public boolean ValidateFields()
    {
       int img = 0,nom = 0,combo = 0,desc = 0 ,date = 0,longitude=0,lat=0;
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
        if (txtlong.getText().length()==0)
        {
            longitude = 1 ;
            labellong.setVisible(true);
        }
        else
            labellong.setVisible(false);
        if (txtatitude.getText().length()==0)
        {
            lat = 1 ;
            labellat.setVisible(true);
        }
        else
            labellat.setVisible(false);
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
         
        return ( img==1 || nom==1 || combo==1 || desc==1 || date == 1 || longitude==1 || lat==1);
    }
    //vider les champs
    public void ClearFields()
    {
        txtNom.clear();
        txtatitude.clear();
        txtlong.clear();
        txtDescription.setHtmlText("");
        imageview.setImage(null);
        txtdate.setValue(null);
        combotypeformation.setValue(null);
        txtimage.clear();
    }

    @FXML
    private void AjouterFormation(ActionEvent event) throws IOException, SQLException {
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
                
                    if(validateLalt()&&validateLong())
                    {
                     
                    Formation f=new Formation(txtNom.getText(),txtDescription.getHtmlText().substring(58, txtDescription.getHtmlText().length()-14),java.sql.Date.valueOf(txtdate.getValue()),imgf,combotypeformation.getValue(),txtlong.getText(),txtatitude.getText());

                    Files.copy(f1.getAbsoluteFile().toPath(),f2.getAbsoluteFile().toPath());

                    service.insertFormation(f);
                    RefreshTable();
                    System.out.println("formation ajouté avec succes");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ajout");
                    alert.setHeaderText(null);
                    alert.setContentText("formation ajouté avec succes");
                    alert.showAndWait();
                    ClearFields(); 
                    }
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //afficher le contenu de la table formation dans le tableau
   public void RefreshTable() throws SQLException 
   {
       
        ServiceFormation service=new ServiceFormation();
        tableFormation.getItems().clear();
        columnidFormation.setCellValueFactory(new PropertyValueFactory<>("idFor"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nomFor"));
        columnlong.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        columnLat.setCellValueFactory(new PropertyValueFactory<>("atitude"));
        columndate.setCellValueFactory(new PropertyValueFactory<>("dateFor"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionFor"));
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
            ClearFields();

    }

    @FXML
    private void AutrePage(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionByIDFor.fxml"));       
        Parent root = loader.load();   
        SessionByIDForController sc = loader.getController();
        sc.setVbox(vbox);
        sc.setIdFormation(tableFormation.getSelectionModel().getSelectedItem().getIdFor());
        System.out.println("crudFormations"+sc.getIdFormation());  
        sc.RemplirCombo(sc.getIdFormation());
         SessionService ss=new SessionService();
            if(ss.SessionExists(sc.getIdFormation())!=0)
            {
                sc.RefreshTableByIdFormation();
                
            }
            else
            {
                System.out.println("aucune session trouvée pour cette formation");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention!");
                alert.setHeaderText(null);
                alert.setContentText("aucune session trouvée pour cette formation!!");
                alert.showAndWait();
            }
        vbox.getChildren().clear(); 
        vbox.getChildren().add(root);
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

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    @FXML
    private void btnRefresh(ActionEvent event) throws SQLException {
         ClearFields();
        RefreshTable();
    }

    @FXML
    private void AjouterTypeFormation(ActionEvent event) {
         TextInputDialog dialog = new TextInputDialog("Nom type formationn");
        dialog.setTitle("Type formation");
        dialog.setHeaderText("Ajouter une type de formation");
        dialog.setContentText("Nom du type formation:");
        Optional<String> result = dialog.showAndWait();
        
        result.ifPresent(name -> {
            try {
                ServiceTypeFormation stf = new ServiceTypeFormation();
                if( stf.rechercheTypeFormation(name) == null){
                    stf.AjouterTypeFormation(new TypeFormation(name));
                    this.initialize(null, null);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Erreur!");
                    alert.setContentText("type formation existe deja!");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        });
    }
    
    
     //validation long 
        private boolean validateLong(){
                Pattern  p = Pattern.compile("(([0-9]+)[.]([0-9]+))");
                Matcher m = p.matcher(txtlong.getText());
               if (m.matches())
               {
                   return true;
               }
               else
               {
                Alert alert  = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("validate prix");
                alert.setHeaderText(null);
                alert.setContentText("please une longitude correcte ");
                alert.showAndWait();
               }
               return false;

         
         }
        
         //validation long 
        private boolean validateLalt(){
                Pattern  p = Pattern.compile("(([0-9]+)[.]([0-9]+))");
                Matcher m = p.matcher(txtatitude.getText());
               if (m.matches())
               {
                   return true;
               }
               else
               {
                Alert alert  = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("validate prix");
                alert.setHeaderText(null);
                alert.setContentText("please une altitude correcte ");
                alert.showAndWait();
               }
               return false;
        }
}
