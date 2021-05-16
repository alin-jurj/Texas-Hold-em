
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
class LogInTest {

    @BeforeEach
    void setUp() throws IOException {
        FileSystemService.APPLICATION_FOLDER = ".test-login-example";
        cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }
    @Start
    void start(Stage primaryStage) throws Exception {
        App.setStage(primaryStage);
        primaryStage.setTitle("LogIn example");
        primaryStage.setScene(new Scene(loadFXML("LogIn"), 443.0, 635.0));
        primaryStage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    @Test
    void testLogInControllerSuccesfully(FxRobot robot)throws CompleteAllFieldsException, ConfirmPasswordException, PasswordNotLongEnough, UserNameNotLongEnough, UsernameAlreadyExistsException{
        UserService.addUser("admin12", "123456", "123456", "Player", "whatever");
        robot.clickOn("#LoginUsername");
        robot.write("admin12");
        robot.clickOn("#LoginPassword");
        robot.write("123456");
        robot.clickOn("#Loginbutton");

        assertThat(LogIn.getUsername().equals("admin12"));
    }
    @Test
    void testLogInControllerWrong(FxRobot robot)throws CompleteAllFieldsException, ConfirmPasswordException, PasswordNotLongEnough, UserNameNotLongEnough, UsernameAlreadyExistsException{
        UserService.addUser("admin12", "123456", "123456", "Player", "whatever");
        robot.clickOn("#LoginUsername");
        robot.write("admin123");
        robot.clickOn("#LoginPassword");
        robot.write("123456");
        robot.clickOn("#Loginbutton");

        assertThat(robot.lookup("#Loginwrong").queryLabeled().getText()).isEqualTo("Wrong username or password. Please try again!");
    }
    @Test
    void testLogInControllerAllfields(FxRobot robot)throws CompleteAllFieldsException, ConfirmPasswordException, PasswordNotLongEnough, UserNameNotLongEnough, UsernameAlreadyExistsException{
        UserService.addUser("admin12", "123456", "123456", "Player", "whatever");
        robot.clickOn("#LoginUsername");
        robot.write("admin123");
        robot.clickOn("#LoginPassword");

        robot.clickOn("#Loginbutton");

        assertThat(robot.lookup("#Loginwrong").queryLabeled().getText()).isEqualTo("Please complete all log in fields!");
    }

    @AfterEach
    void closeDatabase() throws Exception{
        UserService.getDatabase().close();
    }
}