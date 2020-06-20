package work.liuting.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString(exclude = {"blog"})
public class Tag {
    private Integer id;
    private String name;
    private List<Blog> blog;

}
