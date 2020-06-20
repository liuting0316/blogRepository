package work.liuting.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString(exclude = {"blog","replyComment","parentComment"})
public class Comment {
    private Integer id;
    private Integer blogId;
    private Integer parentCommentId;
    private String nickName;
    private String mail;
    private String comment;
    private String avatar;
    private Date createTime;
    private boolean adminComment;
    private Blog blog;
    private List<Comment> replyComment;
    private Comment parentComment;

}
