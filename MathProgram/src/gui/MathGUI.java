package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MathGUI extends Application {
    
    @Override
    public void start(Stage stage) {
        Label label = new Label("Math GUI - Coming Soon!");
        Button button = new Button("Click Me");
        
        VBox root = new VBox(10, label, button);
        Scene scene = new Scene(root, 400, 300);
        
        stage.setTitle("Math Calculator");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}