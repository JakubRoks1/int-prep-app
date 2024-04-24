package demo.impl;

import demo.dao.RoleDAO;
import demo.dao.UserDAO;
import demo.exceptions.PasswordChangeException;
import demo.exceptions.UserNotFoundException;
import demo.model.Role;
import demo.model.User;
import demo.role.UserRole;
import demo.service.RegistrationService;
import demo.service.UserService;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userRepository;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;


    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

    @Override
    public List<User> getUsersByRole(UserRole role) {
        return userRepository.findByRole(role);
    }

    @Override
    public void changePassword(String username, String currentPassword, String newPassword) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        User user = userOptional.get(); // Extract the User object from Optional

        // Validate the new password
        if (!RegistrationService.isValidPassword(newPassword)) {
            throw new PasswordChangeException("Invalid new password");
        }
        // Change the password
        user.setPassword((newPassword));
        userRepository.save(user);
    }

    @Override
    public void updateProfile(User user) {
        userRepository.save(user);
    }

    public void changeEmail(String username, String newEmail) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleDAO.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Optional<User> user = userDAO.findByUsername(username);
        Role role = roleDAO.findByName(roleName);
        user.getRole().add(role);
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }
}
