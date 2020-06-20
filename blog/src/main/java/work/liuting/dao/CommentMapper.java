package work.liuting.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import work.liuting.pojo.Comment;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {

//    添加评论


    @Insert("insert into tb_comment (nickName,mail,comment,avatar,createTime,blog_id,admin_comment,parent_comment_id) values (#{nickName},#{mail},#{comment},#{avatar},#{createTime},#{blogId},#{adminComment},#{parentCommentId})")
    Integer insertComment(Comment comment);

    @Select("select * from tb_Comment order by createTime desc")
    @Results(id = "comment" ,value={
            @Result(id =true,property = "id",column = "id"),
            @Result(property = "nickName",column = "nickName"),
            @Result(property = "mail",column = "mail"),
            @Result(property = "comment",column = "comment"),
            @Result(property = "avatar",column = "avatar"),
            @Result(property = "createTime",column = "createTime"),
            @Result(property = "blogId",column = "blog_id"),
            @Result(property = "parentCommentId",column = "parent_comment_id"),
            @Result(property = "adminComment",column = "admin_comment"),
            @Result(property = "blog",column = "blog_id",one = @One(select = "work.liuting.dao.BlogMapper.queryBlogByIdManager",fetchType = FetchType.EAGER))
    }
            )
    List<Comment> queryComment();



    @Select("select * from tb_comment where blog_id=#{id}")
    @ResultMap("comment")
    List<Comment> queryCommentById(Integer id);


    @Select("select * from tb_comment where id=#{id}")
    @ResultMap("comment")
    Comment queryById(Integer id);


}
