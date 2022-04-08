package Vttp2022.nusiss.April8Login.repositories;

public interface Queries {
    
    public static final String SQL_SELECT_USER_BY_USERPASS = "SELECT * FROM user WHERE username = ? AND password = sha1(?)";
    

}
