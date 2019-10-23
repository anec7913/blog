package blog02.demo.controller.admin;

import blog02.demo.pojo.User;
import blog02.demo.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImp userServiceImp;



    @RequestMapping("/admin")
    public String login(){
        return "admin/login";
    }


    @RequestMapping("/admin/login")
    public String authlogin(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            RedirectAttributes attributes){
        User u = userServiceImp.UserAuth(username,password);
        if (u!=null){
            u.setPassword(null);
            session.setAttribute("user",u);
            return "admin/index";
        }
        attributes.addFlashAttribute("message", "用户名和密码错误");
        return "redirect:/admin";
    }

    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
//