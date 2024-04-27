package demo.controller;

import demo.exceptions.PasswordChangeException;
import demo.exceptions.UserNotFoundException;
import demo.model.ChangeEmail;
import demo.model.ChangePassword;
import demo.model.Role;
import demo.model.User;
import demo.role.UserRole;
import demo.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/adminShow")
    public ResponseEntity<List<User>> getAdminUsers() {
        List<User> adminUsers = userService.getUsersByRole(UserRole.ROLE_ADMIN);
        return ResponseEntity.ok(adminUsers);
    }

    @GetMapping("/userShow")
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


    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/user/save")
    public ResponseEntity<User>saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));

    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String rolename;
}
