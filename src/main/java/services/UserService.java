package services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import exceptions.*;
import exceptions.UserNameNotLongEnough;

import exceptions.*;
import model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("Texas Hold'em.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String confirmpassword,String role, String email) throws UsernameAlreadyExistsException, CompleteAllFieldsException, ConfirmPasswordException, UserNameNotLongEnough, PasswordNotLongEnough {
        checkCredentialsAreNotEmpty(username , password , confirmpassword , role, email);
        checkUserNameLength(username);
        checkUserDoesNotAlreadyExist(username);
        checkPasswordLength(password, confirmpassword);
        checkConfirmPassword(password, confirmpassword);
        userRepository.insert(new User(username, encodePassword(username, password), encodePassword(username, confirmpassword) ,role, email, 100000, 0, "member"));
    }

    public static String checkCredentials(String username, String password) throws CompleteLoginDataException {

        checkLoginDataNotEmpty(username, password);

        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()) &&
                    Objects.equals(encodePassword(username , password),user.getPassword()))
                return user.getRole();
        }
        return new String("Nu exista");
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static void checkCredentialsAreNotEmpty(String username , String password , String confirmpassword , String role, String email) throws CompleteAllFieldsException {
        if(username.equals(new String("")) || password.equals(new String("")) || confirmpassword.equals(new String("")) || email.equals(new String("")))
            throw new CompleteAllFieldsException();
    }

    private static void checkConfirmPassword(String password , String confirmpassword) throws ConfirmPasswordException {
        if(password.equals(confirmpassword) == false)
            throw new ConfirmPasswordException(password, confirmpassword);
    }

    private static void checkUserNameLength(String username) throws UserNameNotLongEnough {
        if(username.length() < 6)
            throw new UserNameNotLongEnough();
    }

    private static void checkPasswordLength(String password, String confirmpassword) throws PasswordNotLongEnough {
        if(password.length() < 6 || confirmpassword.length() < 6)
            throw new PasswordNotLongEnough();
    }

    private static void checkLoginDataNotEmpty(String usernameFieldLogin , String passwordFieldLogin) throws CompleteLoginDataException
    {
        if(usernameFieldLogin.equals(new String("")) || passwordFieldLogin.equals(new String("")))
            throw new CompleteLoginDataException();
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static boolean checkUserExist(String s){
        for(User user : userRepository.find()){
            if(Objects.equals(s, user.getUsername())){
                return true;
            }
        }
        return false;
    }

    public static void giveUserMoney(String username, int suma) {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                user.setMoney(suma);
                System.out.println("dap");
                System.out.println(UserService.getUserMoney("alabala"));
            }
        }
    }

    public static void updateUserStatus(String username, String status){
        for(User user : userRepository.find()){
            if(Objects.equals(username, user.getUsername())){
                user.setStatus(status);
            }
        }
    }

    public static void updateWinRate(String username, int i){
        for(User user : userRepository.find()){
            if(Objects.equals(username, user.getUsername())){
                user.setWinrate(i);
            }
        }
    }

    public static int getUserMoney(String username){
        for(User user : userRepository.find()){
            if(Objects.equals(username,user.getUsername())){
                return user.getMoney_db();
            }
        }
        return 0;
    }

    public static int getWinRate(String username){
        for(User user : userRepository.find()){
            if(Objects.equals(username,user.getUsername())){
                return user.getWinrate_db();
            }
        }
        return 0;
    }

    public static String getStatus(String username){
        for(User user : userRepository.find()){
            if(Objects.equals(username,user.getUsername())){
                return user.getStatus_db();
            }
        }
        return null;
    }




}