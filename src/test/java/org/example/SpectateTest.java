package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class SpectateTest {

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
        primaryStage.setScene(new Scene(loadFXML("Spectate"), 700, 600));
        primaryStage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Test
    void testSpectateButton1(FxRobot robot){
            robot.clickOn("#spectateButton1");

            assertThat(robot.lookup("#spectateLabel").queryLabeled().getText().equals("You don't have money."));
    }

    @Test
    void testSpectateButton2(FxRobot robot){
        robot.clickOn("#spectateButton2");

        assertThat(robot.lookup("#spectateLabel").queryLabeled().getText().equals("You don't have money."));
    }
    @Test
    void testSpectateButton3(FxRobot robot){
        robot.clickOn("#spectateButton3");

        assertThat(robot.lookup("#spectateLabel").queryLabeled().getText().equals("You don't have money."));
    }
    @Test
    void testSpectateButton4(FxRobot robot){
        robot.clickOn("#spectateButton4");

        assertThat(robot.lookup("#spectateLabel").queryLabeled().getText().equals("You don't have money."));
    }
    @Test
    void testSpectateButton5(FxRobot robot){
        robot.clickOn("#spectateButton5");

        assertThat(robot.lookup("#spectateLabel").queryLabeled().getText().equals("You don't have money."));
    }

    @Test
    void testChoicebox1(FxRobot robot){
        robot.clickOn("#choiceBox1");
        robot.clickOn("#spectateButton1");

        assertThat(robot.lookup("#spectateLabel").queryLabeled().getText().equals("You don't have money."));
    }

    @Test
    void testChoicebox2(FxRobot robot){
        robot.clickOn("#choiceBox2");
        robot.clickOn("#spectateButton1");

        assertThat(robot.lookup("#spectateLabel").queryLabeled().getText().equals("You don't have money."));
    }
    @Test
    void testChoicebox3(FxRobot robot){
        robot.clickOn("#choiceBox3");
        robot.clickOn("#spectateButton1");

        assertThat(robot.lookup("#spectateLabel").queryLabeled().getText().equals("You don't have money."));
    }
    @Test
    void testChoicebox4(FxRobot robot){
        robot.clickOn("#choiceBox4");
        robot.clickOn("#spectateButton1");

        assertThat(robot.lookup("#spectateLabel").queryLabeled().getText().equals("You don't have money."));
    }
    @Test
    void testChoicebox5(FxRobot robot){
        robot.clickOn("#choiceBox5");
        robot.clickOn("#spectateButton1");

        assertThat(robot.lookup("#spectateLabel").queryLabeled().getText().equals("You don't have money."));
    }

    @Test
    void testMenuButton(FxRobot robot){
        robot.clickOn("#spectateButton6");
        assertThat(robot.lookup("#logout").queryButton().getId().equals("logout"));
    }
}