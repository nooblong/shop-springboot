package github.nooblong.r5shopspringboot;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class R5ShopSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(R5ShopSpringbootApplication.class, args);
    }

    @Bean
    public DataSource druid(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/r5?serverTimezone=UTC");
        druidDataSource.setUsername("root");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return druidDataSource;
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> druidServlet(){
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean =
                new ServletRegistrationBean<>(
                new StatViewServlet(), "/druid/*");//表示进行druid监控的配置处理操作
        servletRegistrationBean.addInitParameter("loginUsername", "root");//用户名
        servletRegistrationBean.addInitParameter("loginPassword", "root");//密码
        servletRegistrationBean.addInitParameter("resetEnable", "false");//是否可以重置数据源
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean(){
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");//所有请求进行监控处理
        filterRegistrationBean.addInitParameter("exclusions", "/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");//排除
        return filterRegistrationBean;
    }

}
