package Vttp2022.nusiss.April8Login.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Vttp2022.nusiss.April8Login.models.User;
import Vttp2022.nusiss.April8Login.services.LoginService;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private LoginService loginSvc;

    @GetMapping
    public String loadIndex() {

        return "index";
    }

    @PostMapping(path="/authenticate")
    public ModelAndView authenticateLogin(@RequestBody MultiValueMap<String, String> form) {

        ModelAndView mvc = new ModelAndView();

        Optional<User> optUser = create(form);
        
        if (optUser.isEmpty()) {
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            mvc.setViewName("error");
            return mvc;
        }


        User user = optUser.get();
        
        User authUser = loginSvc.authenticate(user);
        
        if (authUser==null) {
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            mvc.setViewName("error");
            return mvc;
        }

        mvc.addObject("authUser", authUser);

        mvc.setStatus(HttpStatus.CREATED);
        mvc.setViewName("login");
        return mvc;

    }

    private Optional<User> create(MultiValueMap<String, String> form) {

        User user = new User();
        try {
            user.setUsername(form.getFirst("username"));
            user.setPassword(form.getFirst("password"));

        } catch (Exception ex) {
            // TODO: handle exception
            ex.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(user);
    }

}
