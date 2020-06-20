package work.liuting.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.liuting.pojo.Blog;
import work.liuting.pojo.Type;
import work.liuting.service.BlogService;
import work.liuting.service.TypeService;

import java.util.List;

@Controller
public class TypeDetailsController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/Type")
    public String tag(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,Model model){
        PageHelper.startPage(pageNum,4);
        List<Blog> blogs = blogService.queryBlogByUpdateTime(1);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);
        List<Type> types = typeService.searchType();
        model.addAttribute("types",types);
        model.addAttribute("blog",blogs);
        model.addAttribute("blogPageInfo",blogPageInfo);
        return "Types";
    }
    @RequestMapping("/typeDetails/{id}")
    public String typeDetails(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@PathVariable("id")Integer id, Model model){
        Type type = typeService.queryTypeById(id);
        List<Blog> blogs = blogService.queryBlogByTypeId(id);
        PageHelper.startPage(pageNum,4);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);
        List<Type> types = typeService.searchType();
        model.addAttribute("blogPageInfo",blogPageInfo);
        model.addAttribute("type",type);
        model.addAttribute("types",types);
        model.addAttribute("blog",blogs);
        return "Types";
    }
}
