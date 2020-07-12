package ir.javaclass.demo;

import entity.Product;
import exception.ProductNotFoundException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/product/list")
    List<Product> findAll()  {
        return productRepository.findAll();
    }


    @PostMapping(value = "/product/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/product/edit")
    public ResponseEntity< String > editProduct(@RequestBody String data) throws ParseException, ProductNotFoundException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(data);
        Long id = (Long) json.get("id");
        String name = (String) json.get("name");
        Long price = (Long) json.get("price");
        String desc = (String) json.get("desc");
        Optional<Product> optional = productRepository.findById(id.intValue());
        if (!optional.isPresent())
            throw new ProductNotFoundException();
        Product product = optional.get();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(desc);
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PostMapping(value = "/product/delete")
    public ResponseEntity< String > deleteProduct(@RequestBody String data) throws ParseException, ProductNotFoundException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(data);
        Long id = (Long) json.get("id");
        Optional<Product> optional = productRepository.findById(id.intValue());
        if (!optional.isPresent())
            throw new ProductNotFoundException();
        productRepository.delete(optional.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
