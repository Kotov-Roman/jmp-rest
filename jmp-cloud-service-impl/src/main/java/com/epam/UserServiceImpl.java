package com.epam;

import com.epam.exceptions.ResourceNotFoundException;
import com.epam.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User userFromDb = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), user.getId()));

        userFromDb.setName(user.getName());
        userFromDb.setSurname(user.getSurname());

        return userRepository.save(userFromDb);
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(User.class.getSimpleName(), id);
        }
        userRepository.deleteById(id);
    }
}
