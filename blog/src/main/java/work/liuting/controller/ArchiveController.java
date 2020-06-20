package work.liuting.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.codegen.TypeMap;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.liuting.dao.BlogMapper;
import work.liuting.dao.TypeMapper;
import work.liuting.pojo.Blog;
import work.liuting.pojo.Type;
import work.liuting.service.BlogService;
import work.liuting.service.TypeService;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class ArchiveController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/Archive")
    public String archive(Model model){
        List<Blog> blogs = blogService.searchBlog(1);
        Map<String, List<Blog>> map = blogService.querMonth();
        model.addAttribute("map",map);
        model.addAttribute("blog",blogs);
        return "Archives";
    }
}
