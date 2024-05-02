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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Data
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

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
        log.info("Save new user {} to the database", user.getUsername());
        return userDAO.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Save new role {} to the database", role.getName());
        return roleDAO.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        Optional<User> userOptional = userDAO.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Role role = roleDAO.findByName(roleName);
            user.getRoles().add(role);
            userDAO.save(user);
        } else {

        }
    }

    @Override
    public Optional<User> getUser(String username) {
        log.info("Fetching user {}", username);
        return userDAO.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userDAO.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDAO.findByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
    }
}
