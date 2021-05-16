package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.FileSystemService;
import services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stg;
    @Override
    public void start(Stage primaryStage) throws IOException {
        stg=primaryStage;
        scene = new Scene(loadFXML("LogIn"));
        UserService.initDatabase();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Texas Hold'em");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void setStage(Stage stage){
         stg = stage;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void changeScene(String fxml) throws IOException{
        Parent pane=FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void resizeStage(int w, int h){
        stg.setHeight(h);
        stg.setWidth(w);
    }


    public static void main(String[] args) {
        launch(args);
    }

}