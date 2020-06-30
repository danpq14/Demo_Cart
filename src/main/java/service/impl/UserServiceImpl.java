package service.impl;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.UserRepository;
import service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return (User) userRepository.findOne(id);
    }

    @Override
    public void editUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
