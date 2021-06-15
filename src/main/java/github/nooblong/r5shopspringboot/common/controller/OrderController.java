package github.nooblong.r5shopspringboot.common.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.nooblong.r5shopspringboot.common.po.Order;
import github.nooblong.r5shopspringboot.common.service.OrderService;
import github.nooblong.r5shopspringboot.common.vo.Result;
import github.nooblong.r5shopspringboot.common.vo.OrderSend;
import github.nooblong.r5shopspringboot.common.vo.OrderUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private OrderService orderService;

    @PostMapping("/api/order/add")
    public Result addOrder(@RequestBody String orderUp) {
        System.out.println(orderUp);
        ObjectMapper objectMapper = new ObjectMapper();
        OrderUp result = null;
        try {
            result = objectMapper.readValue(orderUp, OrderUp.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (result != null) {
            orderService.addOrder(result);
            return new Result(200, "提交成功", result.toString());
        }
        return new Result(400, "请求失败",null);
    }

    @GetMapping("/api/order/list/{orderId}")
    public OrderSend getOrderListById(@PathVariable int orderId){
        return orderService.getOrderListByOrderId(orderId);
    }

    @GetMapping("/api/order/num")
    public List<Integer> getOrderNum(){
        return orderService.getOrderNum();
    }

    @GetMapping("/controller/order/num")
    public List<Integer> getAllOrderNum(){
        return orderService.getAllOrderNum();
    }

    @PutMapping("/controller/order")
    public Result updateOrder(@RequestBody Order order){
        orderService.updateOrder(order);
        System.out.println(order);
        return new Result(201,"修改成功",null);
    }

    @DeleteMapping("/controller/order/{id}")
    public Result deleteOrder(@PathVariable int id){
        orderService.delOrder(id);
        return new Result(201, "删除成功", null);
    }

    @PutMapping("/controller/order/changeState/{id}")
    public Result changeState(@PathVariable int id){
        Integer result = orderService.changeState(id);
        if (result != 1){
            return new Result(400, "更改失败",null);
        } else {
            return new Result(200, "更改成功",null);
        }
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
