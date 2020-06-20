package work.liuting.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString(exclude = {"blog"})
public class User {
    private Integer id;
    private String nickName;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Date createDate;
    private Date updateTime;
    private List<Blog> blog;

}
