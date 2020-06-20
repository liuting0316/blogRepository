package work.liuting.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import work.liuting.pojo.Tag;

import java.util.List;

/**
 * 添加标签
 */
@Mapper
@Component
public interface TagMapper {
    @Insert("insert into tb_tag (name) values (#{name})")
    Integer insertTag(Tag tag);

    @Select("select * from tb_tag")
    @Results(id ="tagResult",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "blog",column = "id",many = @Many(select = "work.liuting.dao.BlogMapper.queryBlogByTagId",fetchType = FetchType.LAZY))
    }
            )
    List<Tag> queryTag();

    @Select("select * from tb_tag where id=#{id}")
    @ResultMap("tagResult")
    Tag queryTagById(Integer id);

    @Select("select * from tb_tag limit #{size}")
    @ResultMap("tagResult")
    List<Tag> queryTagByCount(Integer size);

    @Select("select * from tb_tag where name=#{name}")
    @ResultMap("tagResult")
    Tag queryTagByName(String name);

    @Update("update tb_tag set name=#{name} where id=#{id}")
    Integer updateTag(Tag tag);

    @Delete("delete from tb_tag where id=#{id}")
    Integer deleteTag(Integer id);

}
