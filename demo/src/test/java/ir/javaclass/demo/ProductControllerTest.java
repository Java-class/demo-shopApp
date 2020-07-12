package ir.javaclass.demo;

import entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductControllerTest {


    @Autowired
    private ProductController controller;

    @Test
    void findAll() {
        List<Product> productList = controller.findAll();
        for (Product product : productList) {
            System.out.println( product.toString());
        }
    }

    @Test
    void addProduct() {
        Product product = new Product("Milk",5500);
        controller.addProduct(product);

        product = new Product("Ketchup",8800);
        controller.addProduct(product);

        product = new Product("Coffee",45000);
        controller.addProduct(product);

        product = new Product("Rice",25000);
        controller.addProduct(product);

        product = new Product("Apple Juice",12500);
        controller.addProduct(product);

        product = new Product("Sugar",7500);
        controller.addProduct(product);

        product = new Product("Cake",3500);
        controller.addProduct(product);

        product = new Product("Beans",8700);
        controller.addProduct(product);

        product = new Product("Fish Tuna",13800);
        controller.addProduct(product);

        product = new Product("Toffee",38000);
        controller.addProduct(product);
    }
}