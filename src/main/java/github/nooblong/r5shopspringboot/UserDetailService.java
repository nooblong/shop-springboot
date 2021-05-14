package github.nooblong.r5shopspringboot;

import github.nooblong.r5shopspringboot.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailService implements UserDetailsService {

    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        github.nooblong.r5shopspringboot.common.po.User user = userService.getUserByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("no name");
        }

        String role;
        if (user.getAdmin())
            role = "ROLE_controller";
        else
            role = "ROLE_customer";

        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(role);
        return new User(user.getUsername(),user.getPassword(),auths);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
