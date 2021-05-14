package github.nooblong.r5shopspringboot.common.dao;

import github.nooblong.r5shopspringboot.common.po.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDao {

    @Insert("insert into `order`(submit_datetime, address, customer_id, state, tele, remark, name, price) VALUES (#{submit_datetime}," +
            "#{address}, #{customer_id}, #{state}, #{tele}, #{remark}, #{name}, 0)")
    Integer insertOrder(Order order);

    @Select("select max(id) from `order`")
    Integer lastOrderId();

    @Insert("insert into order_extend(product_id, count, order_id) values(#{product_id},#{count},#{order_id})")
    Integer insertOrderExtend(int count, int product_id, int order_id);

    @Select("select * from `order` where id = #{orderId}")
    Order selectOrderByOrderId(int orderId);

    @Select("select * from `order` where customer_id = #{customer_id}")
    List<Order> selectOrderByCustomerId(int customer_id);

    @Select("select product_id as 'key', `count` as 'value' from order_extend where order_id = #{order_id}")
    List<Map<String,Integer>> selectOrderExtByOrderId(int order_id);

    @Update("update `order` set price = #{price} where id = #{id} ")
    void updatePriceById(int price, int id);

    @Select("select id from `order` where customer_id = #{customer_id}")
    List<Integer> getOrderIdListByCustomerId(int customer_id);

    @Select("select id from `order`")
    List<Integer> getAllOrderList();

    @Update("update `order` set submit_datetime = #{submit_datetime}, address = #{address}," +
            "state = #{state}, tele = #{tele}, remark = #{remark}, name = #{name}, price = #{price} where id = #{id}")
    Integer updateOrderById(Order order);

    @Delete("delete from `order` where id = #{id} ")
    void delOrderById(int id);

    @Delete("delete from order_extend where order_id = #{id}")
    void delOrderExtById(int id);

    @Update("update `order` set state = state + 1 where id = #{id}")
    void changeStateById(int id);
}
