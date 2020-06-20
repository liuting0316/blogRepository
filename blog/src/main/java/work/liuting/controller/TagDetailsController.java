package work.liuting.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import work.liuting.pojo.Blog;
import work.liuting.pojo.Tag;
import work.liuting.pojo.User;
import work.liuting.service.BlogService;
import work.liuting.service.TagService;

import java.util.List;

@Controller
public class TagDetailsController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/Tag")
    public String tag(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,Model model){
        PageHelper.startPage(pageNum,4);
        List<Blog> blogs = blogService.queryBlogByUpdateTime(1);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);
        List<Tag> tags = tagService.searchTag();
        model.addAttribute("tags",tags);
        model.addAttribute("blog",blogs);
        model.addAttribute("blogPageInfo",blogPageInfo);
        return "Tags";
    }
    @GetMapping("/tagDetails/{id}")
    public String tagDetails(@PathVariable("id")Integer id, Model model,@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<Blog> blog1 = blogService.queryBlogByTagId(id);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blog1);
        Tag tag = tagService.searchTagById(id);
        List<Tag> tags = tagService.searchTag();
        model.addAttribute("tag",tag);
        model.addAttribute("tags",tags);
        model.addAttribute("blog",blog1);
        model.addAttribute("blogPageInfo",blogPageInfo);
        return "Tags";
    }
}
