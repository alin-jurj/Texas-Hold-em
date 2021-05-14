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
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stg;
    private static FXMLLoader path;

    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        stg=primaryStage;
        scene = new Scene(loadFXML("LogIn"));
         initDirectory();
         UserService.initDatabase();

        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Texas Hold'em");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void changeScene(String fxml) throws IOException{
        Parent pane=FXMLLoader.load(Objects.requireNonNull(App.class.getResource(fxml)));
        stg.getScene().setRoot(pane);
    }
    public static FXMLLoader getPath() {
        return path;
    }

    public static void main(String[] args) {
        launch(args);
    }

}