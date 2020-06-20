package work.liuting.service;

import work.liuting.pojo.User;

public interface UserService {

    User checkUser(User user);

    User queryUserByName(String username);

    User findUserById(Integer id);
}
