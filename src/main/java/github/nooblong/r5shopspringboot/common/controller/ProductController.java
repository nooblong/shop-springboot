package github.nooblong.r5shopspringboot.common.controller;

import github.nooblong.r5shopspringboot.common.po.Product;
import github.nooblong.r5shopspringboot.common.service.ProductService;
import github.nooblong.r5shopspringboot.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    @RequestMapping("/api/goods")
    public List<Product> test(){
        return productService.getProductList();
    }

    @GetMapping("/api/good/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PutMapping("/controller/product")
    public Result editAddProduct(Product product){
        try {
            productService.editAddProduct(product);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(400, "添加/修改失败", null);
        }
        return new Result(200, "添加/修改成功", null);
    }

    @GetMapping("/controller/product/maxId")
    public Integer maxId(){
        return productService.maxId();
    }

    @DeleteMapping("/controller/product/{id}")
    public Result deleteById(@PathVariable int id){
        try {
            productService.deleteById(id);
            return new Result(200,"删除成功",null);
        } catch (Exception e){
            e.printStackTrace();
            return new Result(400,"删除失败",null);
        }
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}
