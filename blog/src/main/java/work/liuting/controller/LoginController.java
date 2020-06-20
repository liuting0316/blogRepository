package work.liuting.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import work.liuting.pojo.User;
import work.liuting.service.UserService;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.Map;

/**
 * 登录验证
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }
    @PostMapping("/check")
    public String checked(User user,
                          Map<String,Object> map, HttpSession session) {
        User user1 = userService.checkUser(user);
        if (user1!= null) {
            session.setAttribute("user",user1);
            session.setAttribute("loginUser",user1.getUsername());
            return "redirect:/admin/BlogManager";
        } else {
            map.put("msg", "用户名或密码错误");
            return "admin/login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/";
    }

}
