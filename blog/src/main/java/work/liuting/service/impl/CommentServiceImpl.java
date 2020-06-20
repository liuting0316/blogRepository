package work.liuting.service.impl;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.catalina.realm.CombinedRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.liuting.dao.CommentMapper;
import work.liuting.pojo.Comment;
import work.liuting.service.CommentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Transactional
    @Override
    public Integer insertComment(Comment comment) {
        comment.setCreateTime(new Date());
        return commentMapper.insertComment(comment);
    }

    @Transactional
    @Override
    public List<Comment> queryComment() {
        return commentMapper.queryComment();
    }

    @Override
    public List<Comment> queryCommentById(Integer id) {
        return commentMapper.queryCommentById(id);
    }



    @Override
    @Transactional
    public Comment queryById(Integer id) {
        return commentMapper.queryById(id);
    }

}
