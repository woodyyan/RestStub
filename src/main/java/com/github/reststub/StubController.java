package com.github.reststub;

import org.springframework.web.bind.annotation.*;

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
    public User findUserByName(@RequestParam(value = "id", defaultValue = "1") String name) {
        return users.get(0);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public String create(@RequestBody User user) {
        users.add(user);
        return "201";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public User update(@PathVariable("id") String id, @RequestBody User item) {
        Integer index = Integer.parseInt(id) - 1;
        User user = users.get(index);
        user.setAge(item.getAge());
        user.setName(item.getName());
        return user;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public List<User> delete(@PathVariable("id") String id) {
        int index = Integer.parseInt(id) - 1;
        users.remove(index);
        return users;
    }
}