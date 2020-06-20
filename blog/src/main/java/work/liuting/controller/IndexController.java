package work.liuting.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.liuting.pojo.Blog;
import work.liuting.pojo.Tag;
import work.liuting.pojo.Type;
import work.liuting.service.BlogService;
import work.liuting.service.TagService;
import work.liuting.service.TypeService;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @RequestMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<Blog> blogs = blogService.queryBlogByUpdateTime(1);
        PageInfo<Blog> blogInfo = new PageInfo<>(blogs);
        List<Type> types = typeService.queryTagByCount(5);
        List<Tag> tags = tagService.queryTagByCount(5);
        List<Blog> blogs1 = blogService.queryBlogByRecommend(1,5,1);
        model.addAttribute("blog1",blogs1);
        model.addAttribute("tag",tags);
        model.addAttribute("type",types);
        model.addAttribute("blogInfo",blogInfo);
        model.addAttribute("blogs",blogs);
        return "Index";
    }
}
