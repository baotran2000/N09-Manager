package io.ecommerce.BUS;

import io.ecommerce.DAL.ProductDAL;
import io.ecommerce.DTO.Product;

import java.util.ArrayList;

public class ProductBUS {
    private ProductDAL _productDAL = new ProductDAL();

    public ArrayList<Product> getAllProducts() {
        return _productDAL.getAllProducts();
    }

    public Product getProductByIdOrName(String key) {
        return _productDAL.getProductByIdOrName(key);
    }

    public boolean addProduct(Product product) {
        return _productDAL.addProduct(product);
    }

    public boolean updateProductById(Product product) {
        return _productDAL.updateProductById(product);
    }

    public boolean deleteProductById(String id) {
        return _productDAL.deleteProductById(id);
    }
}