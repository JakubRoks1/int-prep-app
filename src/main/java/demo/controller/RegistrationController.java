package demo.controller;

import demo.model.User;
import demo.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("register")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            registrationService.registerNewUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping
//    public ResponseEntity<String> getRegisterPage() {
//        return ResponseEntity.ok("Welcome to registration page");
//    }

    @GetMapping
    public ResponseEntity<List<User>> getAllRegisteredUsers() {
        List<User> users = registrationService.getAllRegisteredUsers();
        System.out.println("registration");
        return ResponseEntity.ok(users);
    }
}
