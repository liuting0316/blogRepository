package work.liuting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Author Liuting
 * @Date 2020/5/3 15:30
 */
@Controller
public class AboutMeController {
    @GetMapping("/AboutMe")
    public String aboutMe(){
        return "AboutMe";
    }
}
