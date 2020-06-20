package work.liuting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.liuting.dao.TagMapper;
import work.liuting.pojo.Tag;
import work.liuting.service.TagService;

import java.util.List;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Transactional
    @Override
    public Integer addTag(Tag tag) {
        return tagMapper.insertTag(tag);
    }

    @Transactional
    @Override
    public List<Tag> searchTag() {
        return tagMapper.queryTag();
    }

    @Transactional
    @Override
    public Tag searchTagById(Integer id) {
        return tagMapper.queryTagById(id);
    }

    @Transactional
    @Override
    public Tag searchTagByName(String name) {
        return tagMapper.queryTagByName(name);
    }

    @Transactional
    @Override
    public Integer deleteTag(Integer id) {
        return tagMapper.deleteTag(id);
    }

    @Transactional
    @Override
    public Integer editTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Transactional
    @Override
    public List<Tag> queryTagByCount(Integer size) {
        return tagMapper.queryTagByCount(size);
    }
}
