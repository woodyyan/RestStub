package com.github.reststub;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StubController {

    private List<User> users;

    public StubController() {
        users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setName("Woody");
        user.setAge(20);
        users.add(user);
    }

    @RequestMapping("/allusers")
    public List<User> getAllUsers() {
        return users;
    }

    @RequestMapping("/users")
    public ResponseEntity<User> findUserByName(@RequestParam(value = "id", defaultValue = "1") String name) {
        User user = users.get(0);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public ResponseEntity create(@RequestBody User user) {
        users.add(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public ResponseEntity<User> update(@PathVariable("id") String id, @RequestBody User item) {
        Integer index = Integer.parseInt(id) - 1;
        User user = users.get(index);
        user.setAge(item.getAge());
        user.setName(item.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        int index = Integer.parseInt(id) - 1;
        users.remove(index);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping("/stub/**")
    public ResponseEntity handle(HttpServletRequest request) {
        System.out.println(request.getRequestURI());
        System.out.println(request.getQueryString());
        return new ResponseEntity(HttpStatus.OK);
    }
}