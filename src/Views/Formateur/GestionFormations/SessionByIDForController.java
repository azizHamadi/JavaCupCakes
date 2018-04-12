/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Formateur.GestionFormations;

import Entity.Formation;
import Entity.Session;
import Services.ServiceFormation;
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
import javafx.fxml.Initializable;
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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author FERIEL FADHLOUN
 */
public class SessionByIDForController implements Initializable {
 @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtCapacite;
    @FXML
    private JFXTextField txtPrix;
    @FXML
    private JFXComboBox<Formation> comboformation;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField id;
    @FXML
    private JFXDatePicker txtdatedebut;
    @FXML
    private JFXDatePicker txtdatefin;
    @FXML
    private JFXButton btnBrowseImage;
    @FXML
    private JFXTextField txtimage;
    @FXML
    private JFXButton btnRefresh;
    
    String imgf="";
    String fdS ="";
    String fdS1 ="";
    Stage stage;
    File fileinputStream;
 
    private Image image;
    private Desktop desktop=Desktop.getDesktop(); final ObservableList options=FXCollections.observableArrayList();
    ObservableList<Formation> listfor;
    private FileChooser fileChooser;
    private File file;
    final ObservableList liste=FXCollections.observableArrayList(); 
    @FXML
    private TableView<Session> tableSession;
    @FXML
    private TableColumn<Session, String> columnNom;
    @FXML
    private TableColumn<Session, DatePicker> columnDateDebut;
    @FXML
    private TableColumn<Session, DatePicker> columnDateFin;
    @FXML
    private TableColumn<Session, Formation> columnFormation;
    @FXML
    private TableColumn<Session, Double> columnCapacite;
    @FXML
    private TableColumn<Session, Double> columnPrix;
    @FXML
    private TableColumn<Session, String> columnImage;
    @FXML
    private Label labelnom;
    @FXML
    private Label labelcapacite;
    @FXML
    private Label labelprix;
    @FXML
    private Label labelformation;
    @FXML
    private Label labeldatedebut;
    @FXML
    private Label labeldatefin;
    @FXML
    private Label labelimage;
    @FXML
    private Label labeldateFinSup;
    @FXML
    private Label labeldate_actuelle;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private TableColumn<Session, Integer> columnidSession;
    private int idFormation;

    private VBox vbox;
    @FXML
    private JFXButton btnModifierSession;
    @FXML
    private JFXButton btnSupprimerSession;
    @FXML
    private JFXButton btnAjouterSession;
    @FXML
    private Label dateFormationSes;

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }
    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
                btnModifierSession.setVisible(false);
                btnSupprimerSession.setVisible(false);
                btnAjouterSession.setVisible(true);
               
            
            
            fileChooser=new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("images", "*.jpg","*.png","*.jpeg"));
            
           
           
           
        
    }    

    @FXML
    private void FetcherTable(MouseEvent event) {
               
            btnModifierSession.setVisible(true);
            btnSupprimerSession.setVisible(true);
            btnAjouterSession.setVisible(false);
          ClearFields();
        txtNom.setText(tableSession.getSelectionModel().getSelectedItem().getNomSes());
        txtCapacite.setText(tableSession.getSelectionModel().getSelectedItem().getCapaciteSes().toString());
        txtPrix.setText(tableSession.getSelectionModel().getSelectedItem().getPrixSes().toString());
        txtdatedebut.setValue(LocalDate.parse(tableSession.getSelectionModel().getSelectedItem().getDateDebSes().toString()));
        System.out.println("date mel fetch"+txtdatedebut.getValue());
        txtdatefin.setValue(LocalDate.parse(tableSession.getSelectionModel().getSelectedItem().getDateFinSes().toString()));
        comboformation.setValue(tableSession.getSelectionModel().getSelectedItem().getIdFor());
        imgf=tableSession.getSelectionModel().getSelectedItem().getImagesess();
        if(!imgf.isEmpty())
        { txtimage.setText(imgf);}
        //
        file:///C://wamp3//www//CupCakesFweb//public//uploads//brochures//Formateur
        image=new Image("file:///C://wamp3//www//CupCakesF//web//public//uploads//brochures//Formateur"+tableSession.getSelectionModel().getSelectedItem().getImagesess(),imageview.getFitWidth(),imageview.getFitHeight(),true,true);
        imageview.setImage(image);
    }

    @FXML
    private void ModifierSession(ActionEvent event) {
        
        SessionService service=new SessionService();
        try {
             if(validateCapacite()&&validatePrixSession())
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Modification Session");
                    alert.setHeaderText(null);
                    alert.setContentText("Voulez vous vraiment modifier cette session!!");
                    alert.showAndWait();
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        //date yafficheha bel - w howa el picker yhotha bel /
                        System.out.println("date khra"+txtdatedebut.getEditor().getText());
                        Session s=new Session(java.sql.Date.valueOf(txtdatedebut.getValue()), java.sql.Date.valueOf(txtdatefin.getValue()),
                                Integer.parseInt(txtCapacite.getText()),imgf,Double.parseDouble(txtPrix.getText()),
                                txtNom.getText(),comboformation.getValue()) ;
                        service.ModifierSession(s,tableSession.getSelectionModel().getSelectedItem().getIdSes());
                        RefreshTableByIdFormation();
                        ClearFields();
                    } 
                    else 
                    {
                        alert.close();
                    }
                }
        } catch (SQLException ex) {
                ex.printStackTrace();        }
    }

    @FXML
    private void handleFileImage(ActionEvent event) {
        stage=(Stage) anchorPane.getScene().getWindow();
        file=fileChooser.showOpenDialog(stage);
    
       if(file !=null)
       {
           System.out.println("file path="+file.getAbsolutePath());
           System.out.println("file url="+file.getAbsoluteFile().toURI().toString());
           image=new Image(file.getAbsoluteFile().toURI().toString(),imageview.getFitWidth(),imageview.getFitHeight(),true,true);
           imageview.setImage(image);
           txtimage.setText(file.getName());
           imgf = file.getName();
           fdS = file.getAbsolutePath();
           fdS1 = ("C://wamp3//www//CupCakesF//web//public//uploads//brochures//Formateur"+file.getName());
           //lab_image.setVisible(false);
                 
       }
    }

    @FXML
    private void BoutonRefresh(ActionEvent event) {
       tableSession.getItems().clear();
    }
    
    private void SearchSession(ActionEvent event) {
         SessionService service=new SessionService();
        try {
            tableSession.setItems(service.SearchListeSessionsByIDFor(txtSearch.getText(),idFormation));
            System.out.println("rechercher");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void AjouterSession(ActionEvent event) throws SQLException {
           SessionService service=new SessionService();
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
                    if(validateCapacite()&&validatePrixSession())
                    {
                                
                        Session s=new Session(java.sql.Date.valueOf(txtdatedebut.getValue()), java.sql.Date.valueOf(txtdatefin.getValue()), Integer.parseInt(txtCapacite.getText()),imgf,Double.parseDouble(txtPrix.getText()),txtNom.getText(),comboformation.getValue()) ;

                        Files.copy(f1.getAbsoluteFile().toPath(),f2.getAbsoluteFile().toPath());

                        service.insertSession(s);
                        System.out.println("Session ajouté avec succes");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Ajouter Session");
                        alert.setHeaderText(null);
                        alert.setContentText("Session ajouté avec succes");
                        alert.showAndWait();
                        ClearFields(); 
                        RefreshTableByIdFormation();
                    }
                    
                    }
                } 
        catch (IOException ex) {
            ex.printStackTrace();        }
    }
        
    //controle de saisie des champs
    public boolean ValidateFields() throws SQLException
    {
        System.out.println("date debut="+txtdatedebut.getEditor().getText());
        System.out.println("date actuelle ="+LocalDate.now());
        System.out.println("date fin ="+txtdatefin.getValue());
        ServiceFormation sf=new ServiceFormation();
            Formation f=new Formation();
       int img = 0,nom = 0,combo = 0,capacite = 0 , datedeb =0 , datefin = 0,prix=0;
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
        
         if (txtCapacite.getText().length()==0)
        {
            capacite = 1 ;
            labelcapacite.setVisible(true);
        }
        else
        {  
            labelcapacite.setVisible(false);

        }
        if (comboformation.getValue() == null)
        {
            combo = 1 ;
            labelformation.setVisible(true);
        }
        else
        {
            labelformation.setVisible(false);
            //recuperer la date de la formation selected
            
            f=sf.AfficherDateFor(comboformation.getSelectionModel().getSelectedItem().getIdFor());
            System.out.println("la formation selected"+f);
            System.out.println("la date de formation selected"+f.getDateFor());
        }
        
        if (txtPrix.getText().length() == 0)
        {
            prix = 1 ;
            labelprix.setVisible(true);
        }
        else
            labelprix.setVisible(false);
        //tester la date de debut
        if(txtdatedebut.getEditor().getText().length()==0 )
        {
            datedeb = 1 ;
            labeldatedebut.setVisible(true);
            labeldate_actuelle.setVisible(false);
        }
        else 
        /*if((txtdatedebut.getValue().isBefore(LocalDate.now())))
        {
            datedeb = 1 ;
            labeldate_actuelle.setVisible(true);
            labeldatedebut.setVisible(false);
            dateFormationSes.setVisible(false);
        }
        else*/
        if(java.sql.Date.valueOf(txtdatedebut.getValue()).before(f.getDateFor()))
        {
            datedeb = 1;
            System.out.println("date debut session akber mel date formation");
            dateFormationSes.setVisible(true);
            labeldatedebut.setVisible(false); 
            labeldate_actuelle.setVisible(false);
            System.out.println("date deb session"+txtdatedebut.getValue());
            System.out.println("date formation"+(f.getDateFor()));
            System.out.println(LocalDate.now());
        }
        else
        { 
            labeldatedebut.setVisible(false); 
            labeldate_actuelle.setVisible(false);
            dateFormationSes.setVisible(false);
            System.out.println("date deb"+txtdatedebut.getValue());
            System.out.println("date fin"+this.txtdatefin.getValue());
            System.out.println(LocalDate.now());
        }   
        //tester la date de fin
        if(txtdatefin.getEditor().getText().length()==0 )
        {
            datedeb = 1 ;
            labeldatefin.setVisible(true);
            labeldateFinSup.setVisible(false);
           
        }
        else if( (this.txtdatefin.getValue().isBefore(txtdatedebut.getValue())))
        {
            datedeb = 1 ;
            labeldateFinSup.setVisible(true);
            labeldatefin.setVisible(false);
        }
        else
        { 
            labeldatefin.setVisible(false); 
            labeldateFinSup.setVisible(false);
            
            System.out.println("date deb"+txtdatedebut.getValue());
            System.out.println("date fin"+this.txtdatefin.getValue());
            System.out.println("date actuelle"+LocalDate.now());
        }  
        return ( img==1 || nom==1 || combo==1 || prix==1 || datedeb == 1||datefin == 1 || capacite==1);
                }
     
    //vider les champs
    public void ClearFields()
    {
        txtNom.clear();
        txtCapacite.clear();
        txtPrix.clear();
        txtimage.clear();
        imageview.setImage(null);
        txtdatedebut.setValue(null);
        txtdatefin.setValue(null);
        comboformation.setValue(null);
        txtimage.clear();
    }

      @FXML
    private void SupprimerSession(ActionEvent event) {
          SessionService service=new SessionService();
        
        try {
            //date yekhou mel table or howa yelzm yekhou mel textfield
            if(validateCapacite()&&validatePrixSession())
                {
            
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Supprimer Session");
                    alert.setHeaderText(null);
                    alert.setContentText("Voulez vous vraiment supprimer cette session!!");
                    alert.showAndWait();
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                       {
                            Session s=new Session();
                            service.SupprimerSession(s,tableSession.getSelectionModel().getSelectedItem().getIdSes());
                            RefreshTableByIdFormation();
                            ClearFields();  
                       }
                    } else {
                        alert.close();
                        // ... user chose CANCEL or closed the dialog
                    }
                }
        } catch (SQLException ex) {
ex.printStackTrace();        }
    }
    
    //afficher le contenu de la table formation dans le tableau
   public void RefreshTableByIdFormation() throws SQLException 
   {
       System.out.println("id formation fel fetch page thenia="+idFormation);
        SessionService service=new SessionService();
        tableSession.getItems().clear();
        columnidSession.setCellValueFactory(new PropertyValueFactory<>("idSes"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nomSes"));
        columnCapacite.setCellValueFactory(new PropertyValueFactory<>("capaciteSes"));
        columnPrix.setCellValueFactory(new PropertyValueFactory<>("prixSes"));
        columnImage.setCellValueFactory(new PropertyValueFactory<>("imagesess"));
        columnDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebSes"));
        columnDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFinSes"));
        columnFormation.setCellValueFactory(new PropertyValueFactory<>("idFor"));
        tableSession.setItems(service.ListeSessions(idFormation));
        
   }
   
  
     //validation capacite 
    private boolean validateCapacite(){
         Pattern  p = Pattern.compile("([0-9]+)");
         Matcher m = p.matcher(txtCapacite.getText());
        if (m.matches())
        {
            return true;
        }
        else
        {
         Alert alert  = new Alert(Alert.AlertType.WARNING);
         alert.setTitle("validate capacite");
         alert.setHeaderText(null);
         alert.setContentText("please entrer un nombre valide ");
         alert.showAndWait();
        }
        return false;
         
         
         }
        //validation capacite 
    private boolean validatePrixSession(){
         Pattern  p = Pattern.compile("(([0-9]+)[.]([0-9]+))");
         Matcher m = p.matcher(txtPrix.getText());
        if (m.matches())
        {
            return true;
        }
        else
        {
         Alert alert  = new Alert(Alert.AlertType.WARNING);
         alert.setTitle("validate prix");
         alert.setHeaderText(null);
         alert.setContentText("please entrer un prix valide ");
         alert.showAndWait();
        }
        return false;
         
         
         }
    
    @FXML
    private void SearchSession(KeyEvent event) {
          SessionService service=new SessionService();
        try {
            tableSession.setItems(service.SearchListeSessions(txtSearch.getText()));
            System.out.println("rechercher session");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void InvisibleBouton(MouseEvent event) {
            ClearFields();
            btnModifierSession.setVisible(false);
            btnSupprimerSession.setVisible(false);
            btnAjouterSession.setVisible(true);
    }

   
    //ajouter dans le combo la formation eli khtheha mel page loula kahawwww
    public void RemplirCombo(int idfor) throws SQLException
    {
         Formation format=new Formation();
            comboformation.getItems().clear();
            ServiceFormation serviceFor = new ServiceFormation();
            System.out.println("id fel page thenia "+idFormation);
            format = serviceFor.ComboFormationsById(idfor);
            //combobox typeformation
            
                comboformation.getItems().add(format);
                comboformation.setConverter(new StringConverter<Formation>() {
                    @Override
                    public String toString(Formation object) {
                        return object.getNomFor();
                    }

                    @Override
                    public Formation fromString(String string) {
                        return comboformation.getItems().stream().filter(ap -> 
                                    ap.getNomFor().equals(string)).findFirst().orElse(null);}

                    
                });
    }
}