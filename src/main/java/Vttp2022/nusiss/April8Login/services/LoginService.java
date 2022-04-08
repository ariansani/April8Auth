package Vttp2022.nusiss.April8Login.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Vttp2022.nusiss.April8Login.models.User;
import Vttp2022.nusiss.April8Login.repositories.LoginRepository;

@Service
public class LoginService {
    
    @Autowired
    private LoginRepository loginRepo;

    public User authenticate(User user){
        return loginRepo.authenticate(user);
    }


}
