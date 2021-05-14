package github.nooblong.r5shopspringboot.common.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.nooblong.r5shopspringboot.common.po.User;
import github.nooblong.r5shopspringboot.common.service.UserService;
import github.nooblong.r5shopspringboot.common.vo.NoPasswordUser;
import github.nooblong.r5shopspringboot.common.vo.Result;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @RequestMapping("/test/isLogin")
    public String isLogin() {
        try {
            return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/test/controller")
    public String controller() {
        return "controller";
    }

    @RequestMapping("/test/customer")
    public String customer() {
        return "customer";
    }

    @PostMapping("/user/add")
    public Result addUser(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        User userByUsername = userService.getUserByUsername(username);
        if (userByUsername != null || username.equals("") || password.equals("||")) {
            response.setStatus(400);
            return new Result(400, "用户存在", null);
        }
        int res = userService.addUser(username, password);
        if (res == -1) {
            response.setStatus(400);
            return new Result(400, "注册失败", null);
        } else {
            return new Result(200, "注册成功", null);
        }
    }

    @GetMapping("/controller/users")
    public Result getUsers() {
        try {
            List<NoPasswordUser> users = userService.getUsers();
            ObjectMapper objectMapper = new ObjectMapper();
            String result = objectMapper.writeValueAsString(users);
            return new Result(200, "获取成功", result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Result(400, "获取失败", null);
    }

    @DeleteMapping("/controller/user/{id}")
    public Result delUser(@PathVariable int id){
        try {
            userService.delUser(id);
            return new Result(200, "删除成功", null);
        }catch (Exception e){
            return new Result(400, "删除失败", null);
        }
    }

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
