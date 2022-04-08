package Vttp2022.nusiss.April8Login.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class User {
    private String username;
    private String password;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static User create(SqlRowSet rs){
        User user = new User();

        user.username = rs.getString("username");
        user.password = rs.getString("password");

        return user;
    }
    
}
