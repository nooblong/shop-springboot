package github.nooblong.r5shopspringboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.nooblong.r5shopspringboot.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder password(){
        return NoOpPasswordEncoder.getInstance();
    }

    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginProcessingUrl("/user/login")//登录访问路径
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json");
                    response.getWriter().write("success login");
                    response.getWriter().flush();
                    response.getWriter().close();
                })
                .failureHandler((request, response, exception) -> {
                    response.setStatus(401);
                    response.setContentType("application/json");
                    response.getWriter().write("fail login");
                    response.getWriter().flush();
                    response.getWriter().close();
                })
                .and().authorizeRequests()
                .antMatchers("/","/api/goods","/user/login","/user/add").permitAll()//可以直接访问
                .antMatchers("/test/controller").hasRole("controller")
                .antMatchers("/test/customer").hasRole("customer")
                .antMatchers("/controller/**").hasRole("controller")
                .anyRequest().authenticated()//所有请求需要认证
                .and().logout().logoutUrl("/user/logout")
                .and().csrf().disable().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                Result result = new Result(400, "请先登录", null);
                out.write(new ObjectMapper().writeValueAsString(result));
                out.flush();
                out.close();
            }
        });
    }

    @Qualifier("userDetailService")
    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
