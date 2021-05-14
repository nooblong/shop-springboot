package github.nooblong.r5shopspringboot.common.service;

import github.nooblong.r5shopspringboot.common.dao.ProductDao;
import github.nooblong.r5shopspringboot.common.po.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductDao productDao;

    public List<Product> getProductList() {
        return productDao.selectAllProduct();
    }

    public Product getProductById(int id) {
        return productDao.selectProductById(id);
    }

    public void editAddProduct(Product product) {
        Product productById = getProductById(product.getId());
        if (productById != null) {
            productDao.updateProduct(product);
        }else {
            productDao.insertProduct(product);
        }
        System.out.println(product);
    }

    public Integer maxId(){
        return productDao.selectMaxId();
    }

    public void deleteById(int id){
        productDao.deleteProductById(id);
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

}
