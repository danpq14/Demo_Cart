package service;

import model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Iterable<User> findAll();
    User findUserById(Long id);
    void editUser(User user);
    void deleteUser(Long id);
}
