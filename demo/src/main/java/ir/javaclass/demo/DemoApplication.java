package ir.javaclass.demo;

import entity.Product;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("entity")
public class DemoApplication  implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(String... args) {
        System.out.println("StartApplication...");
        System.out.println("Start Create Temp Admin and User");

        /// create admin and user
        User admin  = new User("0912xxxxxxx","Admin","admin");
        admin.setPassword("admin_password");
        User user  = new User("0919yyyyyyy","User","user");
        user.setPassword("user_password");
        userRepository.save(admin);
        userRepository.save(user);


        System.out.println("Create test Products...");
        //// start save some product...
        Product product = new Product("Milk",5500);
        productRepository.save(product);

        product = new Product("Ketchup",8800);
        productRepository.save(product);

        product = new Product("Coffee",45000);
        productRepository.save(product);

        product = new Product("Rice",25000);
        productRepository.save(product);

        product = new Product("Apple Juice",12500);
        productRepository.save(product);

        product = new Product("Sugar",7500);
        productRepository.save(product);

        product = new Product("Cake",3500);
        productRepository.save(product);

        product = new Product("Beans",8700);
        productRepository.save(product);

        product = new Product("Fish Tuna",13800);
        productRepository.save(product);

        product = new Product("Toffee",38000);
        productRepository.save(product);

        System.out.println("Finished Create Temp Data. Now Start The App...");

    }


}
