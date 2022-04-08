package Vttp2022.nusiss.April8Login.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Vttp2022.nusiss.April8Login.models.User;

@Controller
@RequestMapping(path="/protected/{view}")
public class ProtectedController {

    @GetMapping
    @PostMapping
    public ModelAndView post(@PathVariable String view,HttpSession sess){

       

        ModelAndView mvc = new ModelAndView();
        mvc.setViewName(view);
        
        //Customized to suit my method
        User authUser = new User();
        authUser.setUsername("ariansani");
        authUser.setPassword("arian");
        //


        String username = sess.getAttribute("username").toString();
        User authUserSess = (User) sess.getAttribute("authUserSess");

        mvc.addObject("authUserSess", authUserSess);
        mvc.addObject("username",username);


        mvc.addObject("authUser", authUser);
        mvc.setStatus(HttpStatus.OK);   

        return mvc;

    }
    
}
