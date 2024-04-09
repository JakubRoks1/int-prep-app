package demo.controller;

import demo.model.User;
import demo.role.UserRole;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAdminUsers() {
        List<User> adminUsers = userService.getUsersByRole(UserRole.ROLE_ADMIN);
        return ResponseEntity.ok(adminUsers);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUserUsers() {
        List<User> userUsers = userService.getUsersByRole(UserRole.ROLE_USER);
        return ResponseEntity.ok(userUsers);
    }
}
