package github.nooblong.r5shopspringboot.common.dao;

import github.nooblong.r5shopspringboot.common.po.User;
import github.nooblong.r5shopspringboot.common.vo.NoPasswordUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from user where username = #{username}")
    User selectUserByUsername(String username);

    @Insert("insert into user(username, password) VALUES(#{username}, #{password})")
    Integer addUser(String username, String password);

    @Select("select username, isAdmin, id from user")
    List<NoPasswordUser> getUsers();

    @Delete("delete from user where id = #{id}")
    void delUserById(int id);

}
