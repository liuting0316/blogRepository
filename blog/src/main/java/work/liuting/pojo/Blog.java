package work.liuting.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString(exclude = {"type","tag","user","comment"})
public class Blog {
    private Integer id;
    private String title;
    private String content;
    private String describe;
    private String firstPicture;
    private String flag;//标签
    private Integer views;
    public Integer appreciateCount;
    private boolean appreciate;
    private boolean copyright;
    private boolean comments;
    private boolean publish;
    private boolean recommend;
    private Date createTime;
    private Date updateTime;
    private Type type;
    private Tag tag;
    private User user;
    private List<Comment> comment;

}
