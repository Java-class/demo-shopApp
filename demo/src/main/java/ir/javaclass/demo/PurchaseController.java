package ir.javaclass.demo;

import entity.Product;
import entity.Purchase;
import entity.User;
import exception.InsufficientBalanceException;
import exception.ProductNotFoundException;
import exception.UserNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class PurchaseController {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/purchase/list")
    public ResponseEntity<String> getAll(){
        JSONArray data = new JSONArray();
        List<Purchase> list = purchaseRepository.findAll();
        for (Purchase purchase :list) {
            data.add(purchase.toJSON());
        }
       return new ResponseEntity<>(data.toJSONString(),HttpStatus.OK);
    }

    @GetMapping(value = "/purchase/{username}")
    public ResponseEntity<String> getListUserPurchase(@PathVariable final String username) throws UserNotFoundException {
        JSONArray data = new JSONArray();
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UserNotFoundException();
        List<Purchase> list = purchaseRepository.findByUser(user);
        for (Purchase purchase : list) {
            data.add(purchase.toJSON());
        }
        return new ResponseEntity<>(data.toJSONString(), HttpStatus.OK);
    }

    @PostMapping(value = "/purchase/buy")
    public ResponseEntity<String> buy(@RequestBody String data) throws ParseException, UserNotFoundException, ProductNotFoundException, InsufficientBalanceException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(data);
        String username = (String)json.get("username");
        String productId = (String) json.get("pr_id");
        int count = ((Long) json.get("count")).intValue();

        User user = userRepository.findByUsername(username);
        if(user==null)
            throw new UserNotFoundException();

        Optional<Product> optional = productRepository.findById(Integer.parseInt(productId));
        if(!optional.isPresent())
            throw new ProductNotFoundException();

        Product product = optional.get();
        double price = count * product.getPrice();

        if(user.getBalance()<price)
            throw new InsufficientBalanceException();

        Purchase purchase = new Purchase(user, product, count,price);
        purchaseRepository.save(purchase);
        user.setBalance(user.getBalance() - price);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
