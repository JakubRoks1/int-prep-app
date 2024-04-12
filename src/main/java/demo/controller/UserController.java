package demo.controller;

import demo.exceptions.PasswordChangeException;
import demo.exceptions.UserNotFoundException;
import demo.model.ChangeEmail;
import demo.model.ChangePassword;
import demo.model.User;
import demo.role.UserRole;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePassword request) {
        try {
            userService.changePassword(request.getUsername(), request.getCurrentPassword(), request.getNewPassword());
            return ResponseEntity.ok("Password changed successfully");
        } catch (PasswordChangeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/change-email")
    public ResponseEntity<String> changeEmail(@RequestBody ChangeEmail request) {
        try {
            userService.changeEmail(request.getUsername(), request.getNewEmail());
            return ResponseEntity.ok("Email changed successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
