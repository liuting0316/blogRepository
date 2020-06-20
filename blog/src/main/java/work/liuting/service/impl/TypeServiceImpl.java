package work.liuting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.liuting.dao.TypeMapper;
import work.liuting.pojo.Type;
import work.liuting.service.TypeService;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Transactional
    @Override
    public Integer addType(Type type) {
        return typeMapper.insertType(type);
    }

    @Transactional
    @Override
    public List<Type> searchType() {
        return typeMapper.queryType();
    }

    @Transactional
    @Override
    public Type searchTypeByName(String name) {
        return typeMapper.queryByName(name);
    }

    @Transactional
    @Override
    public Integer editType(Type type) {
        return typeMapper.updateType(type);
    }

    @Transactional
    @Override
    public Integer deleteType(Integer id) {
        return typeMapper.deleteType(id);
    }

    @Transactional
    @Override
    public Type queryTypeById(Integer id) {
        return typeMapper.queryTypeById(id);
    }

    @Override
    public List<Type> queryTagByCount(Integer size) {
        return typeMapper.queryTagByCount(size);
    }


}
