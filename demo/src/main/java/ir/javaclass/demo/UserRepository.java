package ir.javaclass.demo;

import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    List<User> findByName(String name);
    User findByUsername(String username);
    List<User> findByFamily(String family);
    List<User> findByPhone(String phone);
}
