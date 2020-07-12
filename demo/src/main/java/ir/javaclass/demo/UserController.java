package ir.javaclass.demo;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import entity.User;
import exception.UserNotFoundException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/list")
    MappingJacksonValue findAll()  {
        List<User> userList = userRepository.findAll();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userList);
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("entity.User", SimpleBeanPropertyFilter
                        .filterOutAllExcept("username","name","family","addDate","phone","balance"));
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/user/{username}")
    MappingJacksonValue findUser(@PathVariable final String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null)
            throw new UserNotFoundException();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("entity.User", SimpleBeanPropertyFilter
                        .filterOutAllExcept("username","name","family","addDate","phone","balance"));
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/users/search")
    List<User> findByName(@RequestParam(name = "pattern")String searchPattern) {
        List<User> result = null;
        System.out.println("serach pattern is: " + searchPattern);
        String pattern[] = searchPattern.split(":");
        String type = pattern[0];
        String keyword = pattern[1];
        switch (type) {
            case "name":
                result = userRepository.findByName(keyword);
                break;
            case "family":
                result = userRepository.findByFamily(keyword);
                break;
            case "phone":
                result = userRepository.findByPhone(keyword);
                break;
        }
        return result;
    }

    @PostMapping(value = "/user/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/user/edit")
    public ResponseEntity< String > editUser(@RequestBody String data) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(data);
        String username = (String)json.get("username");
        String name = (String)json.get("name");
        String family = (String) json.get("family");
        String phone = (String)json.get("phone");
        User user = userRepository.findByUsername(username);
        if(user!=null){
            user.setName(name);
            user.setFamily(family);
            user.setPhone(phone);
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else
            return new ResponseEntity<>("کاربر مورد نظر یافت نشد", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/user/delete")
    public ResponseEntity< String > deleteUser(@RequestBody String data) throws ParseException, UserNotFoundException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(data);
        String username = (String)json.get("username");
        User user = userRepository.findByUsername(username);
        if(user!=null){
            userRepository.delete(user);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else
            throw new UserNotFoundException();
    }

    @PostMapping(value = "/user/balance/add")
    public ResponseEntity<String> addBalance(@RequestBody String data) throws ParseException, UserNotFoundException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(data);
        String username = (String)json.get("username");
        String b = (String)json.get("credit");
        double credit = Double.parseDouble(b);
        User user = userRepository.findByUsername(username);
        if(user!=null){
            user.setBalance(user.getBalance() + credit);
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            throw new UserNotFoundException();
    }


}
