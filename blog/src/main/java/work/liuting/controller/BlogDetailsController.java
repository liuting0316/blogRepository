package work.liuting.controller;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import work.liuting.pojo.Blog;
import work.liuting.pojo.Comment;
import work.liuting.pojo.Type;
import work.liuting.pojo.User;
import work.liuting.service.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Random;

@Controller
public class BlogDetailsController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    private Integer integer;

    private Integer appreciateCount;

    @GetMapping("/blogDetails/{id}")
    public String blogDetails(@PathVariable("id")Integer id, Model model){
        Integer views = blogService.queryBlogView(id,1);
        views++;
        blogService.updateBlogView(views,id);
        Blog blog = blogService.getAndConvert(id,1);
        appreciateCount = blogService.queryBlogAppreciate(id, 1);
        model.addAttribute("blog",blog);
        model.addAttribute("appreciateCount",appreciateCount);
        return "Blog";
    }
    @GetMapping("/appreciate")
    public String appreciate(String id,Model model){
        appreciateCount++;
        blogService.updateBlogAppreciateCount(appreciateCount,Integer.parseInt(id));
        appreciateCount = blogService.queryBlogAppreciate(Integer.parseInt(id), 1);
        Blog blog = blogService.getAndConvert(Integer.parseInt(id),1);
        model.addAttribute("blog",blog);
        model.addAttribute("appreciateCount",appreciateCount);
        return "Blog::appreciate";
    }



    @RequestMapping("/reply")
    public void comment(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json; charset=UTF-8");
        String commentId = request.getParameter("commentId");
        integer = Integer.parseInt(commentId);
    }

    /**
     * 评论功能
     * @param session
     * @param comment
     * @param id
     * @param request
     * @return
     */
    @PostMapping("/comment")
    public String insertComment(HttpSession session, Comment comment, String id, HttpServletRequest request,Model model){
        Random random = new Random();
//        生成[1~10之间的随机数，用于选择评论用户的随机头像]
        int i = random.nextInt(10)+1;
        comment.setParentCommentId(integer);
        integer=null;
        comment.setBlogId(Integer.parseInt(id));
        Object loginUser =session.getAttribute("loginUser");
        if (loginUser!=null){
            comment.setAdminComment(true);
            User user =(User) session.getAttribute("user");
            comment.setAvatar(user.getAvatar());
            comment.setNickName(user.getNickName());
        }else {
            comment.setAdminComment(false);
            String avatars=File.separator +"img"+File.separator+"userIcon"+i+".jpg";
            comment.setAvatar(avatars);
        }
//        非空校验
        if (comment.getNickName().length()>0&&comment.getMail().length()>0){
            if (comment.getMail().contains("@")&&comment.getMail().contains("com")){
                String comment1 = comment.getComment();
                if (comment1!=null&&comment1.length()>0){
                    commentService.insertComment(comment);
                }else{
                    request.setAttribute("Warning","评论内容不能为空");
                }
            }else {
                request.setAttribute("Warning","邮箱地址错误");
            }
        }else {
            request.setAttribute("Warning","用户名和邮箱不能为空");
        }
        Blog blog = blogService.getAndConvert(Integer.parseInt(id),1);
        model.addAttribute("blog",blog);
        return "Blog::comment";
    }
}
