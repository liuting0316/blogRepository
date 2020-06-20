package work.liuting.service;

import work.liuting.pojo.Blog;
import work.liuting.pojo.Tag;

import java.util.List;

public interface TagService {
    Integer addTag(Tag tag);

    List<Tag> searchTag();

    Tag searchTagById(Integer id);

    Tag searchTagByName(String name);

    Integer deleteTag(Integer id);

    Integer editTag(Tag tag);

    List<Tag> queryTagByCount(Integer size);
}
