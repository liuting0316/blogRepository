package work.liuting.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Setter
@Getter
@ToString(exclude = {"blog"})
public class Type {
    private Integer id;
    private String name;
    private List<Blog> blog;

}
