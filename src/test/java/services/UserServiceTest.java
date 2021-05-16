package services;

import exceptions.*;
import model.User;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import static org.apache.commons.io.FileUtils.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    public static final String ADMIN = "admincusasecaractere";

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.getDatabase().close();
        System.out.println("After each");
    }


    @Test
    @DisplayName("Database is initialized, and there are users")
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
    }

    @Test
    @DisplayName("User is successfully persisted to Database")
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException, ConfirmPasswordException, PasswordNotLongEnough, UserNameNotLongEnough, CompleteAllFieldsException {
        UserService.addUser(ADMIN, ADMIN, ADMIN, "player", "whatever");
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(ADMIN);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(ADMIN, ADMIN));
        assertThat(user.getRole()).isEqualTo("player");
    }

    @Test
    @DisplayName("User can not be added twice")
    void testUserCanNotBeAddedTwice() {
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser(ADMIN, ADMIN, ADMIN, "player", "whatever");
            UserService.addUser(ADMIN, ADMIN, ADMIN, "player", "whatever");
        });
    }

    @Test
    @DisplayName("Confirmpassword has to be the same with password")
    void testConfirmPassword() {
        assertThrows(ConfirmPasswordException.class, () -> {
            UserService.addUser(ADMIN, ADMIN, "differentpassword", "player", "whatever");
        });
    }

    @Test
    @DisplayName("Username has to have at least 6 chareacters")
    void checkUsernameLength(){
        assertThrows(UserNameNotLongEnough.class, () ->{
           UserService.addUser("user", ADMIN, ADMIN, ADMIN, ADMIN);
        });
    }

    @Test
    @DisplayName("Password has to have at least 6 chareacters")
    void checkPasswordLength(){
        assertThrows(PasswordNotLongEnough.class, () ->{
            UserService.addUser(ADMIN,"pass", "pass", ADMIN, ADMIN);
        });
    }

    @Test
    @DisplayName("Username and password fields must not be empty")
    void checkFieldsFilled(){
        assertThrows(CompleteAllFieldsException.class, () -> {
           UserService.addUser("", "", "", ADMIN, ADMIN);
        });
    }

}