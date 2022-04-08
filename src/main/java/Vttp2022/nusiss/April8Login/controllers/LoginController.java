package Vttp2022.nusiss.April8Login.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

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

    @GetMapping("/logout")
    public String getLogout(HttpSession sess){
        sess.invalidate();
        return "index";
    }

    @PostMapping(path="/authenticate")
    public ModelAndView authenticateLogin(@RequestBody MultiValueMap<String, String> form, HttpSession sess) {

        ModelAndView mvc = new ModelAndView();

        User optUser = create(form);
        
        User authUser = loginSvc.authenticate(optUser);
        
        if (authUser==null) {
            mvc.setStatus(HttpStatus.UNAUTHORIZED);
            mvc.setViewName("error");
            return mvc;
        }

        sess.setAttribute("username", authUser.getUsername());
        sess.setAttribute("authUserSess",authUser);

        mvc.addObject("authUser", authUser);
        mvc = new ModelAndView("redirect:/protected/login");
           

        // mvc.setStatus(HttpStatus.OK);
        // mvc.setViewName("login");
        
        return mvc;

    }

    private User create(MultiValueMap<String, String> form) {

        User user = new User();
        try {
            user.setUsername(form.getFirst("username"));
            user.setPassword(form.getFirst("password"));

        } catch (Exception ex) {
            // TODO: handle exception
            ex.printStackTrace();
           
        }
        return user;
    }

}
