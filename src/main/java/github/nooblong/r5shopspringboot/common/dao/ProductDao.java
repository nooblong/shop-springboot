package github.nooblong.r5shopspringboot.common.dao;

import github.nooblong.r5shopspringboot.common.po.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductDao {

    @Select("select * from product")
    List<Product> selectAllProduct();

    @Update("update product set stock=stock-#{num} where product.id = #{product_id}")
    Integer consume(int product_id, int num);

    @Select("select * from product where id = #{id}")
    Product selectProductById(int id);

    @Update("update product set name=#{name}, img=#{img}, price=#{price}, stock=#{stock}, `show`=#{show} where id = #{id}")
    void updateProduct(Product product);

    @Insert("insert into product(name, img, price, stock, `show`) VALUES (#{name},#{img},#{price},#{stock},#{show})")
    void insertProduct(Product product);

    @Select("select MAX(id) from product")
    Integer selectMaxId();

    @Delete("delete from product where id = #{id}")
    void deleteProductById(int id);
}
