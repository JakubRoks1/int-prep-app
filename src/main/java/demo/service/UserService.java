package demo.service;

import demo.model.User;
import demo.role.UserRole;

import java.util.List;

public interface UserService {
    User getUserByUsername(String username);
    List<User> getUsersByRole(UserRole role);
}
