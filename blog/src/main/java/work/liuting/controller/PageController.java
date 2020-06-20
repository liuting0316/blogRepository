package work.liuting.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {
    @RequestMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return page;
    }
}
