package demo.service;

import demo.model.Role;
import demo.model.User;
import demo.role.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User getUserByUsername(String username);
    List<User> getUsersByRole(UserRole role);
    void changePassword(String username, String currentPassword, String newPassword);
    void updateProfile(User user);
    void changeEmail(String username, String newEmail);
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    Optional<User> getUser(String username);
    List<User>getUsers();
}
