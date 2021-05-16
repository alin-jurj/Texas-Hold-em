
package org.example;

import static org.apache.commons.io.FileUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import exceptions.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.example.App;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import services.FileSystemService;
import services.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.awt.*;
import java.io.IOException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class RegisterTest {
    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        //cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }
    @Start
    void start(Stage primaryStage) throws Exception {
        App.setStage(primaryStage);
        primaryStage.setTitle("Register example");
        primaryStage.setScene(new Scene(loadFXML("Register"), 463.0, 658.0));
        primaryStage.show();
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    @Test
    void testRegisterfields(FxRobot robot){
        robot.clickOn("#Registerbutton");
        assertThat(robot.lookup("#Registermessage").queryLabeled().getText()).isEqualTo("Please complete all fields");
    }
    @Test
    void testRegisterUsername(FxRobot robot){
        robot.clickOn("#Registerusername");
        robot.write("123");
        robot.clickOn("#Registerpassword");
        robot.write("123");
        robot.clickOn("#Registerconfirmpassword");
        robot.write("123");
        robot.clickOn("#Registeremail");
        robot.write("email");


        robot.clickOn("#Registerbutton");
        assertThat(robot.lookup("#Registermessage").queryLabeled().getText()).isEqualTo("Username must contain at least 6 characters!");
    }
    @Test
    void testRegisterPassword(FxRobot robot){
        robot.clickOn("#Registerusername");
        robot.write("administrator");
        robot.clickOn("#Registerpassword");
        robot.write("123");
        robot.clickOn("#Registerconfirmpassword");
        robot.write("123");
        robot.clickOn("#Registeremail");
        robot.write("email");


        robot.clickOn("#Registerbutton");
        assertThat(robot.lookup("#Registermessage").queryLabeled().getText()).isEqualTo("Password must contain at least 6 characters!");
    }
    @Test
    void testRegisterSamePassword(FxRobot robot){
        robot.clickOn("#Registerusername");
        robot.write("administrator");
        robot.clickOn("#Registerpassword");
        robot.write("1234567");
        robot.clickOn("#Registerconfirmpassword");
        robot.write("123678942");
        robot.clickOn("#Registeremail");
        robot.write("email");


        robot.clickOn("#Registerbutton");
        assertThat(robot.lookup("#Registermessage").queryLabeled().getText()).isEqualTo("Please enter same password twice!");
    }
    @AfterEach
    void closeDatabase() throws Exception{
        UserService.getDatabase().close();
    }

}