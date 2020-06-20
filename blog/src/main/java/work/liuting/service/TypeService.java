package work.liuting.service;

import work.liuting.pojo.Type;

import java.util.List;

public interface TypeService {
    Integer addType(Type type);

    List<Type> searchType();


    Type searchTypeByName(String name);

    Integer editType(Type type);

    Integer deleteType(Integer id);

    Type queryTypeById(Integer id);

    List<Type> queryTagByCount(Integer size);
}
