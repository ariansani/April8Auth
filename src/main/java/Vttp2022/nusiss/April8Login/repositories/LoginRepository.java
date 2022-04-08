package Vttp2022.nusiss.April8Login.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import Vttp2022.nusiss.April8Login.models.User;

@Repository
public class LoginRepository {

    @Autowired
    private JdbcTemplate template;

    public User authenticate(User userInput){
        
        final SqlRowSet rs = template.queryForRowSet(
            Queries.SQL_SELECT_USER_BY_USERPASS, userInput.getUsername(),userInput.getPassword()
            );
        User userDB = new User();
        if(!rs.next()){
            return null;

        }
        userDB = User.create(rs);
        return userDB;

    }

}
