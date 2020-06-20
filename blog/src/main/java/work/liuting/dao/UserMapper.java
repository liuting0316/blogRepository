package work.liuting.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import work.liuting.pojo.User;


@Mapper
@Component
public interface UserMapper {
    @Select("select * from tb_user where username=#{username} and password=#{password}")
    @Results(id = "userResult",value={
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "avatar",column = "avatar"),
            @Result(property = "createDate",column = "createDate"),
            @Result(property = "email",column = "email"),
            @Result(property = "nickName",column = "nickname"),
            @Result(property = "password",column = "password"),
            @Result(property = "type",column = "type"),
            @Result(property = "updateTime",column = "updateTime"),
            @Result(property = "username",column = "username"),
            @Result(property = "blog",column = "id" ,many = @Many(select = "work.liuting.dao.BlogMapper.queryBlogByUserId",fetchType = FetchType.LAZY))
    })
    User findUser(User user);

    /**
     * 根据已经登陆的用户名查询此用户的所有信息
     * @param username
     * @return
     */
    @Select("select * from tb_user where username=#{username}")
    @ResultMap("userResult")
    User queryUserByName(String username);
    @Select("select * from tb_user where id=#{id}")
    @ResultMap("userResult")
    User queryUserById(Integer id);
}
