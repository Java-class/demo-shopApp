package ir.javaclass.demo;

import entity.Purchase;
import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {

    List<Purchase> findByUser(User user);

}
