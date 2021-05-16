package org.example;

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

import static org.apache.commons.io.FileUtils.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class AdminControllerTest {
    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void closeDatabase() throws Exception{
        UserService.getDatabase().close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        App.setStage(primaryStage);
        primaryStage.setTitle("Registration Example");
        primaryStage.setScene(new Scene(loadFXML("Admin"), 700, 600));
        primaryStage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Test
    void testAdminControllerCase1(FxRobot robot){
        robot.clickOn("#adminConfirm");

        assertThat(robot.lookup("#adminError").queryLabeled().getText()).isEqualTo("Introduce a username!");
    }

    @Test
    void testAdminControllerCase2(FxRobot robot){
        robot.clickOn("#adminUsername");
        robot.write("alabala");
        robot.clickOn("#adminConfirm");

        assertThat(robot.lookup("#adminError").queryLabeled().getText()).isEqualTo("Introduce a valid value!");
    }

    @Test
    void testAdminControllerCase3(FxRobot robot){
        robot.clickOn("#adminUsername");
        robot.write("alabala");
        robot.clickOn("#adminSuma");
        robot.write("thisshouldbenumber");
        robot.clickOn("#adminConfirm");

        assertThat(robot.lookup("#adminError").queryLabeled().getText()).isEqualTo("Introduce a valid value!");
    }

    @Test
    void testAdminControllerCase4(FxRobot robot){
        robot.clickOn("#adminUsername");
        robot.write("thisuserdoesnotexist");
        robot.clickOn("#adminSuma");
        robot.write("500");
        robot.clickOn("#adminConfirm");

        assertThat(robot.lookup("#adminError").queryLabeled().getText()).isEqualTo("There is no user with this username!");
    }

    @Test
    void testAdminControllerCase5(FxRobot robot){
        robot.clickOn("#adminUsername");
        robot.write("thisuserdoesnotexist");
        robot.clickOn("#adminSuma");
        robot.write("500");
        robot.clickOn("#adminConfirm");

        assertThat(robot.lookup("#adminError").queryLabeled().getText()).isEqualTo("There is no user with this username!");
    }

    @Test
    void testAdminControllerCase6(FxRobot robot){
        robot.clickOn("#adminUsername");
        robot.write("alabala");
        robot.clickOn("#adminSuma");
        robot.write("500");
        robot.clickOn("#adminConfirm");
        assertThat(robot.lookup("#adminError").queryLabeled().getText()).isEqualTo("There is no user with this username!");
    }

    @Test
    void testAdminControllerCase7(FxRobot robot) throws CompleteAllFieldsException, ConfirmPasswordException, PasswordNotLongEnough, UserNameNotLongEnough, UsernameAlreadyExistsException {
        UserService.addUser("alabala", "password", "password", "player", "whatever");
        robot.clickOn("#adminUsername");
        robot.write("alabala");
        robot.clickOn("#adminSuma");
        robot.write("500");
        robot.clickOn("#adminConfirm");
        assertThat(robot.lookup("#adminError").queryLabeled().getText()).isEqualTo("Succesful transaction!");
    }

    @Test
    void testClose(FxRobot robot){
        robot.clickOn("#adminClose");

        assertThat(robot.lookup("#Loginbutton").queryButton().getId().equals("Loginbutton"));
    }
}