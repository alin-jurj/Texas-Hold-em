package model;
import org.dizitart.no2.objects.Id;

public class User {
    @Id
    private String usernameField;
    private String passwordField;
    private String confirmpasswordField;
    private String role;
    private String email;

    private int money;
    private int winrate;
    private String status;

    public User(String usernameField, String passwordField, String confirmpasswordField,String role, String email, int money, int winrate, String status) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.confirmpasswordField = confirmpasswordField;
        this.role=role;
        this.email = email;

        this.money = money;
        this.winrate = winrate;
        this.status = status;
    }
    public User() {
    }

    public String getUsername()
    {
        return usernameField;
    }

    public void setUsername(String username)
    {
        this.usernameField = username;
    }

    public String getPassword()
    {
        return passwordField;
    }

    public void setPassword(String password)
    {
        this.passwordField = password;
    }

    public String getConfirmPassword()
    {
        return confirmpasswordField;
    }

    public void setConfirmPassword(String confirmpassword)
    {
        this.confirmpasswordField = confirmpassword;
    }
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getRole()
    {
        return role;
    }
    public void setRole(String role)
    {
        this.role = role;
    }

    public int getMoney_db(){
        return money_db;
    }
    public void setMoney_db(int suma){
        System.out.println("Set: " + money_db);
        money_db = money_db + suma;
        System.out.println(money_db);
    }
    public String getStatus_db(){
        return status_db;
    }
    public void setStatus_db(String status){
        status_db = status;
    }
    public int getWinrate_db(){
        return winrate_db;
    }
    public void setWinrate_db(int i){
        winrate_db = winrate_db + i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (usernameField != null ? !usernameField.equals(user.usernameField) : user.usernameField != null) return false;
        if (passwordField != null ? !passwordField.equals(user.passwordField) : user.passwordField != null) return false;
        return role != null ? role.equals(user.role) : user.role == null;

    }

    @Override
    public int hashCode() {
        int result = usernameField != null ? usernameField.hashCode() : 0;
        result = 31 * result + (passwordField != null ? passwordField.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}