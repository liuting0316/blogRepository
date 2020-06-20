package work.liuting.service;

import work.liuting.pojo.Comment;

import java.util.List;

public interface CommentService {

    Integer insertComment(Comment comment);

    List<Comment> queryComment();

    List<Comment> queryCommentById(Integer id);

//    List<Comment> queryReply(Integer id);

    Comment queryById(Integer id);

//    List<Comment> queryParentComment(Integer id);
}
