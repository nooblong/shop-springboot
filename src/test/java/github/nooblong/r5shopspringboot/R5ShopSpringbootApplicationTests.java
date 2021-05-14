package github.nooblong.r5shopspringboot;

import github.nooblong.r5shopspringboot.common.dao.ProductDao;
import github.nooblong.r5shopspringboot.common.dao.OrderDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class R5ShopSpringbootApplicationTests {

    @Autowired
    ProductDao productDao;
    @Autowired
    OrderDao orderDao;

    @Test
    void contextLoads() {

//        System.out.println(productDao.selectAllProduct());

//        Map<Integer,Integer> map = new HashMap<>();
//        map.put(1,1);
//        map.put(2,3);
//        map.put(3,2);
//        System.out.println(map);
        List<Map<String, Integer>> maps = orderDao.selectOrderExtByOrderId(8);
        System.out.println(maps);

    }

}
