package work.liuting.service;

import org.apache.ibatis.annotations.ResultMap;
import work.liuting.pojo.Blog;
import work.liuting.pojo.Type;

import javax.management.Query;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BlogService {
    List<Blog> searchBlog(Integer publish);

    Blog searchBlogById(Integer id,Integer publish);

    Blog queryBlogByIdManager(Integer id);

    Integer addBlog(Blog blog);

    Integer updateBlog(Blog blog);

    Integer deleteBlog(Integer id);

    List<Blog> queryBlogByUpdateTime(Integer publish);

    List<Blog> queryBlogByRecommend(Integer recommend,Integer size,Integer publish);

    List<Blog> queryBlogByTagId(Integer id);

    List<Blog> queryBlogByTypeId(Integer id);

    Map<String,List<Blog>> querMonth();

    Blog getAndConvert(Integer id,Integer publish);

    Integer updateBlogView(Integer views,Integer id);

    Integer queryBlogView(Integer id,Integer publish);

    List<Blog> queryBlogManager();

    List<Blog> queryIndexBlog(String blog);

    Integer updateBlogAppreciateCount(Integer appreciateCount,Integer id);

    Integer queryBlogAppreciate(Integer id,Integer publish);
}
