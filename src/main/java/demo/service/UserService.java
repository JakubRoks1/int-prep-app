package demo.service;

import demo.model.User;
import demo.role.UserRole;

import java.util.List;

public interface UserService {

    User getUserByUsername(String username);
    List<User> getUsersByRole(UserRole role);
    void changePassword(String username, String currentPassword, String newPassword);
    void updateProfile(User user);
    void changeEmail(String username, String newEmail);
}
