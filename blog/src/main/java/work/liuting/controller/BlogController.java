package work.liuting.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import work.liuting.pojo.Blog;
import work.liuting.pojo.Tag;
import work.liuting.pojo.Type;
import work.liuting.pojo.User;
import work.liuting.service.BlogService;
import work.liuting.service.TagService;
import work.liuting.service.TypeService;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /**
     * 跳转到博客编辑页面
     * @param model
     * @return
     */
    public void init(Model model){
        List<Type> types = typeService.searchType();
        List<Tag> tags = tagService.searchTag();
        model.addAttribute("tag",tags);
        model.addAttribute("type",types);
    }
    @GetMapping("/BlogInputs")
    public String blogInputs(Model model){
        init(model);
        return "admin/BlogInputs";
    }
//    后台博客列表
    @GetMapping("/BlogManager")
    public String blogManager(Model model,@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,6);
        List<Blog> blogs = blogService.queryBlogManager();
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);
        model.addAttribute("blogInfo",blogPageInfo);
        model.addAttribute("blog",blogs);
        return "admin/BlogManager";
    }
    //修改博客

    /**
     * 跳转至博客编辑页面,并且回显原来数据
     * @return
     */
    @GetMapping("/BlogManager/update/{id}")
    public String update(@PathVariable("id")Integer id,Model model){

        Blog blogs = blogService.queryBlogByIdManager(id);
        model.addAttribute("blog1",blogs);
        return "forward:/admin/BlogInputs";
    }

    /**
     * 修改完成提交
     * @param blog
     * @return
     */
    @PutMapping("/BlogInsert")
    public String updateBlog(Blog blog){
        blogService.updateBlog(blog);
        return "redirect:/admin/BlogManager";
    }
//    删除博客
    @DeleteMapping("/BlogManager/delete/{id}")
    public String deleteBlog(@PathVariable("id")Integer id){
        blogService.deleteBlog(id);
        return "redirect:/admin/BlogManager";
    }
//新增博客
    @PostMapping("/BlogInsert")
    public String addBlog(Blog blog, HttpServletRequest httpServletRequest,Model model){
        if (blog.getContent().length()>0&&blog.getTitle().length()>0){
            blog.setUser((User)httpServletRequest.getSession().getAttribute("user"));
            blogService.addBlog(blog);

        }else {
            httpServletRequest.setAttribute("blogWarning","博客标题和内容不能为空");
            init(model);
            return "admin/BlogInputs";
        }
        return "redirect:/admin/BlogManager";
    }

}
