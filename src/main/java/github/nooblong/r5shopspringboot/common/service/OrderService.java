package github.nooblong.r5shopspringboot.common.service;

import github.nooblong.r5shopspringboot.common.dao.ProductDao;
import github.nooblong.r5shopspringboot.common.dao.OrderDao;
import github.nooblong.r5shopspringboot.common.dao.UserDao;
import github.nooblong.r5shopspringboot.common.po.Product;
import github.nooblong.r5shopspringboot.common.po.Order;
import github.nooblong.r5shopspringboot.common.po.User;
import github.nooblong.r5shopspringboot.common.vo.OrderSend;
import github.nooblong.r5shopspringboot.common.vo.OrderUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    private OrderDao orderDao;
    private UserDao userDao;
    private ProductDao productDao;

    public Integer addOrder(OrderUp orderUp) {
        Order order = new Order();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.selectUserByUsername(username);
        order.setSubmit_datetime(new Date());
        order.setAddress(orderUp.getRoom());
        order.setCustomer_id(user.getId());
        order.setState(0);
        order.setTele(orderUp.getTele());
        order.setRemark(orderUp.getRemark());
        order.setName(orderUp.getName());
        System.out.println(order);

        orderDao.insertOrder(order);

        Map<Integer, Integer> productMap = orderUp.getProductMap();
        int totalPrice = 0;
        for (Map.Entry<Integer, Integer> entry : productMap.entrySet()) {
            if (entry.getValue() != 0) {
                orderDao.insertOrderExtend(entry.getValue(), entry.getKey(), orderDao.lastOrderId());
                productDao.consume(entry.getKey(), entry.getValue());
                //计算价格
                Product product = productDao.selectProductById(entry.getKey());
                totalPrice += product.getPrice() * entry.getValue();
                orderDao.updatePriceById(totalPrice, orderDao.lastOrderId());
            }
        }
        return 0;
    }

    public OrderSend getOrderListByOrderId(int id) {
        Order order = orderDao.selectOrderByOrderId(id);
        List<Map<String, Integer>> extend = orderDao.selectOrderExtByOrderId(order.getId());
        Map<Integer, Integer> result = new HashMap<>();
        for (Map<String, Integer> map : extend) {
            int rKey = 0, rValue = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                //key:product_id value:count
                if ("key".equals(entry.getKey()))
                    rKey = entry.getValue();
                else if ("value".equals(entry.getKey()))
                    rValue = entry.getValue();
            }
            result.put(rKey, rValue);
        }
        System.out.println("result: " + result);
        OrderSend orderSend = new OrderSend();
        orderSend.setGood(result);
        orderSend.setMode("到付");
        orderSend.setNumber(UUID.randomUUID().toString());
        orderSend.setPrice(order.getPrice());
        orderSend.setState(order.getState());
        orderSend.setTime(order.getSubmit_datetime());
        orderSend.setName(order.getName());
        orderSend.setAddress(order.getAddress());
        orderSend.setTele(order.getTele());
        orderSend.setId(order.getId());
        orderSend.setRemark(order.getRemark());
        return orderSend;
    }

    public List<Integer> getOrderNum() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.selectUserByUsername(username);
        return orderDao.getOrderIdListByCustomerId(user.getId());
    }

    public List<Integer> getAllOrderNum() {
        return orderDao.getAllOrderList();
    }

    public Integer updateOrder(Order order) {
        return orderDao.updateOrderById(order);
    }

    public void delOrder(int id) {
        orderDao.delOrderExtById(id);
        orderDao.delOrderById(id);
    }

    public Integer changeState(int id) {
        Order order = orderDao.selectOrderByOrderId(id);
        if (order.getState() == 0 || order.getState() == 1) {
            orderDao.changeStateById(id);
            return 1;
        } else {
            return -1;
        }
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
