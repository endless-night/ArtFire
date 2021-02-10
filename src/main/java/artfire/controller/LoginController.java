package artfire.controller;


import artfire.dao.mapper.UserMapper;
import artfire.entity.User;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        User user = userMapper.checkUser(username,password);
        if (user != null){
            user.setPassword(null);
            return "index";
        }
        else{
            return "login";
        }
    }
}
