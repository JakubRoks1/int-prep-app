package demo.service;

import demo.dao.UserDAO;
import demo.model.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private UserDAO userDAO;

    public void registerNewUser(User user) {
        boolean isFirstUser = userDAO.count() == 0;

        if (userDAO.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userDAO.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        String password = user.getPassword();

        if (password.contains(user.getUsername()) || password.contains(user.getFirstName())) {
            throw new RuntimeException("Password cannot contain username or first name");
        }

        if (!isValidPassword(password)) {
            throw new RuntimeException("Password must contain uppercase letters, lowercase letters, non-alphanumeric characters, and numeric characters");
        }

        userDAO.save(user);

        if (isFirstUser) {
            createDatabaseTable();
        }

    }

        public static boolean isValidPassword(String password){
            boolean hasUppercase = false;
            boolean hasLowercase = false;
            boolean hasNonAlphanumeric = false;
            boolean hasNumeric = false;
            String specialCharacters = "~!@#$%^&*_-+=`|(){}[]:;\"'<>,.?/";

            for (char c : password.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    hasUppercase = true;
                } else if (Character.isLowerCase(c)) {
                    hasLowercase = true;
                } else if (Character.isDigit(c)) {
                    hasNumeric = true;
                } else if (specialCharacters.contains(String.valueOf(c))) {
                    hasNonAlphanumeric = true;
                }
            }

            return hasUppercase && hasLowercase && hasNumeric && hasNonAlphanumeric;
        }

    public List<User> getAllRegisteredUsers() {
        return userDAO.findAll();
    }

    private void createDatabaseTable() {
        userDAO.createExampleTable();
    }


    }

