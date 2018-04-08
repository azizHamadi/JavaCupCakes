/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author escobar
 */
public class Test1 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       /* Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();*/
       primaryStage.setTitle("Popup Example");  
    final Popup popup = new Popup(); popup.setX(300); popup.setY(200);
    popup.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));

    Button show = new Button("Show");
    show.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        popup.show(primaryStage);
      }
    });

    Button hide = new Button("Hide");
    hide.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        popup.hide();
      }
    });

    HBox layout = new HBox(10);
    layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
    layout.getChildren().addAll(show, hide);
    primaryStage.setScene(new Scene(layout));
    primaryStage.show();
  }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        launch(args);
      
    }
    
}
