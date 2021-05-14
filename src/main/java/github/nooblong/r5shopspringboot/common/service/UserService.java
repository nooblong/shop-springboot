package github.nooblong.r5shopspringboot.common.service;

import github.nooblong.r5shopspringboot.common.dao.UserDao;
import github.nooblong.r5shopspringboot.common.po.User;
import github.nooblong.r5shopspringboot.common.vo.NoPasswordUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserDao userDao;

    public User getUserByUsername(String username){
        return userDao.selectUserByUsername(username);
    }

    public Integer addUser(String username, String password){
        return userDao.addUser(username, password);
    }

    public List<NoPasswordUser> getUsers() {
        return userDao.getUsers();
    }

    public void delUser(int id){
        userDao.delUserById(id);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
