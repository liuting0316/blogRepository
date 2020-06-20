package work.liuting.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.liuting.pojo.Blog;
import work.liuting.service.BlogService;
import work.liuting.service.TagService;

import java.util.List;

@Controller
public class SearchBlogController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @PostMapping("/search")
    public String searchBlog(@RequestParam("blog")String blog, Model model){
        List<Blog> blogs = blogService.queryIndexBlog(blog);
        model.addAttribute("blog",blogs);
        return "Search";
    }
}
