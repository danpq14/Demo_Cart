package service;

import model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    Product findProductByID(Long id);
    void editProduct(Product product);
    void deleteProduct(Long id);
}
