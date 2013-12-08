package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Start extends Application {
    public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tic-Tac-Toe");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        
        
        Button btn = new Button("sag 'Hallo Welt'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button-Klick mit Hallo Welt!");
            }
        });
        
        Button btn2 = new Button("sag 'Hallo Welt'");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button-Klick mit Hallo Welt!");
            }
        });
         
        grid.add(btn, 0, 0);
        grid.add(btn2, 1, 1);
        
        
        primaryStage.show();
    }
}