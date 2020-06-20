package work.liuting.dao;

import com.alibaba.druid.sql.visitor.functions.If;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import work.liuting.pojo.Blog;
import work.liuting.pojo.Type;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface BlogMapper {
    @Select("select * from tb_blog ")
    @Results(id ="blogResult",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "updateTime",column = "updateTime"),
            @Result(property = "content",column = "content"),
            @Result(property = "firstPicture",column = "firstPicture"),
            @Result(property = "flag",column = "flag"),
            @Result(property = "views",column = "views"),
            @Result(property = "appreciate",column = "appreciate"),
            @Result(property = "appreciateCount",column = "appreciateCount"),
            @Result(property = "describe",column = "description"),
            @Result(property = "copyright",column = "copyright"),
            @Result(property = "comments",column = "comments"),
            @Result(property = "publish",column = "publish"),
            @Result(property = "recommend",column = "recommend"),
            @Result(property = "createTime",column = "createTime"),
            @Result(property = "type",column = "type_id",one=@One(select = "work.liuting.dao.TypeMapper.queryTypeById",fetchType = FetchType.EAGER)),
            @Result(property = "tag",column = "tag_id",one=@One(select = "work.liuting.dao.TagMapper.queryTagById",fetchType = FetchType.EAGER)),
            @Result(property = "user",column = "user_id",one=@One(select = "work.liuting.dao.UserMapper.queryUserById",fetchType = FetchType.EAGER)),
            @Result(property = "comment",column = "id",many=@Many(select = "work.liuting.dao.CommentMapper.queryCommentById",fetchType = FetchType.LAZY))
    }
            )
    List<Blog> queryBlog(Integer publish);


    @Select("select * from tb_blog order by updateTime DESC")
    @ResultMap("blogResult")
    List<Blog> queryBlogManager();

    @Select("select * from tb_blog where id=#{id} and publish=#{publish}")
    @ResultMap("blogResult")
    Blog queryBlogById(@Param("id")Integer id,@Param("publish")Integer publish);
//    后台显示全部博客
    @Select("select * from tb_blog where id=#{id}")
    @ResultMap("blogResult")
    Blog queryBlogByIdManager(Integer id);

    @Select("select * from tb_blog where publish='1' and type_id=#{typeId} order by updateTime DESC")
    @ResultMap("blogResult")
    List<Blog> queryBlogByTypeId(@Param("typeId")Integer typeId);

    @Select("select * from tb_blog where publish='1' and tag_id=#{tagId} order by updateTime DESC")
    @ResultMap("blogResult")
    List<Blog> queryBlogByTagId(@Param("tagId")Integer tagId);

    @Select("select * from tb_blog where user_id=#{userId}")
    @ResultMap("blogResult")
    List<Blog> queryBlogByUserId(@Param("userId")Integer userId);

    @Select("select views from tb_blog  where id=#{id} and publish=#{publish}")
    Integer queryBlogView(@Param("id")Integer id,@Param("publish")Integer publish);

    @Select("select appreciateCount from tb_blog  where id=#{id} and publish=#{publish}")
    Integer queryBlogAppreciate(@Param("id")Integer id,@Param("publish")Integer publish);

    @Select("select * from tb_blog where recommend=#{recommend} and publish=#{publish} limit #{size}")
    @ResultMap("blogResult")
    List<Blog> queryBlogByRecommend(@Param("recommend")Integer recommend,@Param("size")Integer size,@Param("publish")Integer publish);

    @Insert("insert into tb_blog (title,content,firstPicture,flag,views,appreciate,copyright,comments,publish,recommend,updateTime,createTime,type_id,user_id,tag_id,description,appreciateCount)values" +
            "(#{title},#{content},#{firstPicture},#{flag},#{views},#{appreciate},#{copyright},#{comments},#{publish},#{recommend},#{updateTime},#{createTime},#{type.id},#{user.id},#{tag.id},#{describe},#{appreciateCount})")
    Integer insertBlog(Blog blog);

    @Update("update tb_blog set title=#{title},publish=#{publish}, content=#{content},firstPicture=#{firstPicture},flag=#{flag},appreciate=#{appreciate},copyright=#{copyright},comments=#{comments},recommend=#{recommend},type_id=#{type.id},tag_id=#{tag.id},description=#{describe},updateTime=#{updateTime} where id=#{id}")
    Integer updateBlog(Blog blog);

    @Update("update tb_blog set views=#{views} where id=#{id}")
    Integer updateBlogView(@Param("views")Integer views,@Param("id")Integer id);

    @Update("update tb_blog set appreciateCount=#{appreciateCount} where id=#{id}")
    Integer updateBlogAppreciateCount(@Param("appreciateCount")Integer appreciateCount,@Param("id")Integer id);

    @Delete("delete from tb_blog where id=#{id}")
    Integer deleteBlog(Integer id);
//    通过更新时间查询博客
    @Select("select * from tb_blog where publish=#{publish} order by updateTime desc")
    @ResultMap("blogResult")
    List<Blog> queryBlogByUpdateTime(Integer publish);

//    通过时间归档博客
    @Select("select DATE_FORMAT(updateTime ,'%Y-%m') from tb_blog where publish=#{publish}  group by DATE_FORMAT(updateTime ,'%Y-%m')")
    List<String> queryMonth(Integer publish);

    @Select("select a.* from (select b.*,t.id tid,t.name from tb_type t left outer join tb_blog b on t.id=b.type_id and publish=#{publish} )as a where DATE_FORMAT(updateTime ,'%Y-%m')=#{month} ")
    @ResultMap("blogResult")
    List<Blog> queryBlogByMonth(@Param("publish")Integer publish,@Param("month")String month);

//首页搜索
    @Select("<script>"
            +"select * from tb_blog where 1=1"
            +"<if test='title!=null'>"
            +"and title like concat('%',#{title},'%')"
            +"</if>"
            +"<if test='content!=null'>"
            +"or content like concat('%',#{content},'%')"
            +"</if>"
            +"</script>")
    @ResultMap("blogResult")
    List<Blog> queryIndexBlog(String blog);

}
