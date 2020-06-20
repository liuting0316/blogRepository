package work.liuting.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import sun.rmi.log.LogInputStream;
import work.liuting.pojo.Type;

import java.util.List;

/**
 * 添加标签
 */
@Mapper
@Component
public interface TypeMapper {
    @Insert("insert into tb_type (name) values (#{name})")
    Integer insertType(Type type);

    @Select("select * from tb_type")
    @Results(id ="typeResult",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "blog",column = "id",many = @Many(select = "work.liuting.dao.BlogMapper.queryBlogByTypeId",fetchType = FetchType.LAZY)),
    }
            )
    List<Type> queryType();

    @Select("select * from tb_type limit #{size}")
    @ResultMap("typeResult")
    List<Type> queryTagByCount(Integer size);

    @Select("select * from tb_type where id=#{id}")
    @ResultMap("typeResult")
    Type queryTypeById(Integer id);

    @Select("select * from tb_type where name=#{name}")
    @ResultMap("typeResult")
    Type queryByName(String name);

    @Update("update tb_type set name=#{name} where id=#{id}")
    Integer updateType(Type type);

    @Delete("delete from tb_type where id=#{id}")
    Integer deleteType(Integer id);

}
