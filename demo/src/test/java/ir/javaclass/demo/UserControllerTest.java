
package ir.javaclass.demo;

import entity.User;
import exception.UserNotFoundException;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJacksonValue;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    UserController controller;

    @Test
    void findAll() {
        try {
            MappingJacksonValue all = controller.findAll();
            List<User> list = (List <User>)all.getValue();
            for (User user:list) {
                System.out.println(user.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    void findUser() {
        try {
            /// test condition for exist user
            assertNotNull(controller.findUser("0912xxxxxxx"));

            //test condition for not exist user
            assertNull(controller.findUser("0912bbbbbbb"));
        } catch (UserNotFoundException uex) {
            assertTrue(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    void findByName() {
        try {
            /// test condition for exist user
            assertNotNull(controller.findUser("Mostafa"));

            //test condition for not exist user
            assertNull(controller.findUser("NotFoundName"));
        } catch (UserNotFoundException uex) {
            assertTrue(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    void addUser() {
        try {
            User user1 = new User("0910xxxxxxx", "Mostafa", "Anbarmoo");
            controller.addUser(user1);
            User user2 = new User("0935xxxxxxx", "Alireza", "Mohammadi");
            controller.addUser(user2);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    void editUser() {
        try {
            String username = "0912xxxxxxx";
            User user =(User) controller.findUser(username).getValue();
            if (user != null) {
                System.out.println("before update: " + user.toString());
                JSONObject data = new JSONObject();
                data.put("username", username);
                data.put("name", "new Name " + System.currentTimeMillis());
                data.put("family", "new family " + System.currentTimeMillis());
                data.put("phone", "new phone" + System.currentTimeMillis());
                controller.editUser(data.toJSONString());
                user = (User) controller.findUser(username).getValue();
                if (user != null) {
                    System.out.println("after update: " + user.toString());
                } else
                    fail();

            } else
                fail();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }

    }

    @Test
    void deleteUser() {
        try {
            String username = "0912wwwwwww";
            User user = new User(username, "deleted User", "test");
            controller.addUser(user);
            user = (User) controller.findUser(username).getValue();
            if (user != null) {
                JSONObject data = new JSONObject();
                data.put("username", username);
                controller.deleteUser(data.toJSONString());
            } else
                fail();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    void addBalance() {
        try {
            String username = "0912xxxxxxx";
            User user = (User)controller.findUser(username).getValue();
            if (user != null) {
                System.out.println("###### before update: " + user.getBalance());
                double extraBalance = 200_000;
                JSONObject data = new JSONObject();
                data.put("username", username);
                data.put("credit", String.valueOf(extraBalance));
                controller.addBalance(data.toJSONString());
                user = (User) controller.findUser(username).getValue();
                System.out.println("##### After Update: " + user.getBalance());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
