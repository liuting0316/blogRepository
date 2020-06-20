package work.liuting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.liuting.dao.UserMapper;
import work.liuting.pojo.User;
import work.liuting.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(User user) {
        return userMapper.findUser(user);
    }
    /**
     * 根据用户名查询用户全部信息
     * @param username
     * @return
     */
    @Override
    public User queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.queryUserById(id);
    }
}
