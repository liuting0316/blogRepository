package work.liuting.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import work.liuting.pojo.Blog;
import work.liuting.pojo.Tag;
import work.liuting.service.TagService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    public void check(@RequestParam("name")String name, HttpServletRequest request) {
        Tag tag1 = tagService.searchTagByName(name);
        if (name.length()==0){
            request.setAttribute("message","您所添加的内容为空！");
        }else if (tag1!=null){
            request.setAttribute("message","标签已存在！");
        }
    }
    @RequestMapping("/TagManager")
    public String tag(Model model,@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum) {
        PageHelper.startPage(pageNum,6);
        List<Tag> tag = tagService.searchTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(tag);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("tags", tag);
        return "admin/TagManager";
    }

    //    跳转到添加标签页
    @GetMapping("/TagInputs")
    public String addTag() {
        return "admin/TagInputs";
    }

    //    新增标签
    @PostMapping("/TagCommit")
    public String commitTag(Tag tag, @RequestParam("name")String name, HttpServletRequest request) {
        check(name,request);
        Object message = request.getAttribute("message");
        if (message!=null){
            return "admin/TagInputs";
        }else {
            tagService.addTag(tag);
            return "redirect:/admin/TagManager";
        }
    }

    //    修改标签
    @GetMapping("/TagManager/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model) {
        Tag tagById = tagService.searchTagById(id);
        model.addAttribute("tag1", tagById);
        return "forward:/admin/TagInputs";
    }

    //    修改并提交
    @PutMapping("/TagCommit")
    public String updateTag(Tag tag,@RequestParam("name")String name, HttpServletRequest request) {
        check(name,request);
        Object message = request.getAttribute("message");
        if (message!=null){
            return "admin/TagInputs";
        }else {
            tagService.editTag(tag);
            return "redirect:/admin/TagManager";
        }
    }

    //    删除标签
    @DeleteMapping("/TagManager/delete/{id}")
    public String delete(@PathVariable("id") Integer id,Model model,HttpServletRequest request,@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum) {
        Tag tag = tagService.searchTagById(id);
        List<Blog> blog = tag.getBlog();
        if (blog.size()>0){
            request.setAttribute("tagMesg","标签正在被占用！");
            tag(model,pageNum);
            return "admin/TagManager";
        }else{
            tagService.deleteTag(id);
            return "redirect:/admin/TagManager";
        }
    }

}
