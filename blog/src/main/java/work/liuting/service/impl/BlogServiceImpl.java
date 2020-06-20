package work.liuting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.liuting.dao.BlogMapper;
import work.liuting.pojo.Blog;
import work.liuting.service.BlogService;
import work.liuting.utils.MarkDownUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Transactional
    @Override
    public List<Blog> searchBlog(Integer publish) {
        return blogMapper.queryBlog(publish);
    }

    @Transactional
    @Override
    public Blog searchBlogById(Integer id,Integer publish) {
        return blogMapper.queryBlogById(id,publish);
    }

    @Override
    public Blog queryBlogByIdManager(Integer id) {
        return blogMapper.queryBlogByIdManager(id);
    }

    @Transactional
    @Override
    public Integer addBlog(Blog blog) {
//        初始化blog的属性
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setAppreciateCount(0);
        return blogMapper.insertBlog(blog);
    }

    @Transactional
    @Override
    public Integer updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        return blogMapper.updateBlog(blog);
    }

    @Transactional
    @Override
    public Integer deleteBlog(Integer id) {
        return blogMapper.deleteBlog(id);
    }

    @Transactional
    @Override
    public List<Blog> queryBlogByUpdateTime(Integer publish) {
        return blogMapper.queryBlogByUpdateTime(publish);
    }

    @Transactional
    @Override
    public List<Blog> queryBlogByRecommend(Integer recommend,Integer size,Integer publish) {
        return blogMapper.queryBlogByRecommend(recommend,size,publish);
    }

    @Transactional
    @Override
    public List<Blog> queryBlogByTagId(Integer id) {
        return blogMapper.queryBlogByTagId(id);
    }

    @Transactional
    @Override
    public List<Blog> queryBlogByTypeId(Integer id) {
        return blogMapper.queryBlogByTypeId(id);
    }

    @Transactional
    @Override
    public Map<String,List<Blog>> querMonth() {
        List<String> month = blogMapper.queryMonth(1);
        HashMap<String, List<Blog>> map = new HashMap<>();
        for (String s : month) {
            List<Blog> blogs = blogMapper.queryBlogByMonth(1,s);
            map.put(s,blogs);
        }
        return map;
    }
    @Transactional
    @Override
    public Blog getAndConvert(Integer id,Integer publish) {
        Blog blog = blogMapper.queryBlogById(id,publish);
        String content = blog.getContent();
        blog.setContent(MarkDownUtils.markdownToHtmlExtensions(content));
        return blog;
    }

    @Transactional
    @Override
    public Integer updateBlogView(Integer views,Integer id) {
        return blogMapper.updateBlogView(views,id);
    }

    @Transactional
    @Override
    public Integer queryBlogView(Integer id,Integer publish) {
        return blogMapper.queryBlogView(id,publish);
    }

    @Transactional
    @Override
    public List<Blog> queryBlogManager() {
        return blogMapper.queryBlogManager();
    }

    @Transactional
    @Override
    public List<Blog> queryIndexBlog(String blog) {
        return blogMapper.queryIndexBlog(blog);
    }

    @Transactional
    @Override
    public Integer updateBlogAppreciateCount(Integer appreciateCount, Integer id) {
        return blogMapper.updateBlogAppreciateCount(appreciateCount,id);
    }

    @Override
    public Integer queryBlogAppreciate(Integer id, Integer publish) {
        return blogMapper.queryBlogAppreciate(id,publish);
    }
}
