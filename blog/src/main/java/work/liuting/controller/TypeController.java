package work.liuting.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import work.liuting.pojo.Blog;
import work.liuting.pojo.Type;
import work.liuting.service.TypeService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    public void check(@RequestParam("name")String name, HttpServletRequest request) {
        Type type1 = typeService.searchTypeByName(name);
        if (name.length()==0){
            request.setAttribute("message","您所添加的内容为空！");
        }else if (type1!=null){
            request.setAttribute("message","标签已存在！");
        }
    }
    @GetMapping("/TypeManager")
    public String type(Model model,@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum) {
        PageHelper.startPage(pageNum,6);
        List<Type> type = typeService.searchType();
        PageInfo<Type> pageInfo = new PageInfo<>(type);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types", type);
        return "admin/TypeManager";
    }

    //    跳转到添加标签页
    @GetMapping("TypeInputs")
    public String addType() {
        return "admin/TypeInputs";
    }

    //    新增标签
    @PostMapping("/TypeCommit")
    public String commitType(Type type, @RequestParam("name")String name, HttpServletRequest request) {
        check(name,request);
        Object message = request.getAttribute("message");
        System.out.println(message);
        if (message!=null){
            return "admin/TypeInputs";
        }else {
            typeService.addType(type);
            return "redirect:/admin/TypeManager";
        }
    }

    //    修改标签
    @GetMapping("/TypeManager/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model) {
        Type typeById = typeService.queryTypeById(id);
        model.addAttribute("type1", typeById);
        return "forward:/admin/TypeInputs";
    }

    //    修改并提交
    @PutMapping("/TypeCommit")
    public String updateType(Type type,@RequestParam("name")String name,HttpServletRequest request) {
        check(name,request);
        Object message = request.getAttribute("message");
        if (message!=null){
            return "admin/TypeInputs";
        }else {
            typeService.editType(type);
            return "redirect:/admin/TypeManager";
        }
    }

    //    删除标签
    @DeleteMapping("/TypeManager/delete/{id}")
    public String delete(@PathVariable("id") Integer id,HttpServletRequest request,Model model,@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum) {
        Type type2 = typeService.queryTypeById(id);
        List<Blog> blog = type2.getBlog();
        if (blog.size()>0){
            request.setAttribute("mesg","此分类正在被占用！");
            type(model,pageNum);
            return "admin/TypeManager";
        }else {
            typeService.deleteType(id);
            return "redirect:/admin/TypeManager";
        }
    }

}
