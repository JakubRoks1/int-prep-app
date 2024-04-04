package demo.dao;

import demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query(value = "CREATE TABLE users (id SERIAL PRIMARY KEY, "
            + "username VARCHAR(255) UNIQUE, "
            + "email VARCHAR(255) UNIQUE, "
            + "password VARCHAR(255), "
            + "first_name VARCHAR(255), "
            + "last_name VARCHAR(255))", nativeQuery = true)
    void createExampleTable();
}
