package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import services.FileSystemService;
import services.UserService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class DonateTest {

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        //cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
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
        primaryStage.setScene(new Scene(loadFXML("Donate"), 700, 600));
        primaryStage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Test
    void testNoDataIntroduced(FxRobot robot){
        robot.clickOn("#donateButton");
        assertThat(robot.lookup("#wrongDonate").queryLabeled().getText()).isEqualTo("Not a valid card number");
    }


    @Test
    void testWrongCardNrDataIntroduced(FxRobot robot){
        robot.clickOn("#cardnumber");
        robot.write("123456");
        robot.clickOn("#donateButton");
        assertThat(robot.lookup("#wrongDonate").queryLabeled().getText()).isEqualTo("Not a valid card number");
    }

    @Test
    void testGoodCardNrDataIntroduced(FxRobot robot){
        robot.clickOn("#cardnumber");
        robot.write("1234567891234567");
        robot.clickOn("#donateButton");
        assertThat(robot.lookup("#wrongDonate").queryLabeled().getText()).isEqualTo("Not a valid CVC");
    }

    @Test
    void testWrongCVCNrDataIntroduced(FxRobot robot){
        robot.clickOn("#cardnumber");
        robot.write("1234567891234567");
        robot.clickOn("#cvc");
        robot.write("1324");
        robot.clickOn("#donateButton");
        assertThat(robot.lookup("#wrongDonate").queryLabeled().getText()).isEqualTo("Not a valid CVC");
    }

    @Test
    void testGoodCVCNrDataIntroduced(FxRobot robot){
        robot.clickOn("#cardnumber");
        robot.write("1234567891234567");
        robot.clickOn("#cvc");
        robot.write("132");
        robot.clickOn("#donateButton");
        assertThat(robot.lookup("#wrongDonate").queryLabeled().getText()).isEqualTo("Not a valid amount");
    }

    @Test
    void testGoodAmount(FxRobot robot){
        robot.clickOn("#cardnumber");
        robot.write("1234567891234567");
        robot.clickOn("#cvc");
        robot.write("132");
        robot.clickOn("#sumtodonate");
        robot.write("5000");
        robot.clickOn("#donateButton");
        assertThat(robot.lookup("#wrongDonate").queryLabeled().getText()).isEqualTo("Not valid date");
    }
}