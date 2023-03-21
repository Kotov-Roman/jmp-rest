package com.epam;

import com.epam.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

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
        return null;
    }

    @Override
    public User create(UserRequestDto userRequestDto) {
        User user = User.builder()
//                .birthday(userRequestDto.getBirthday())
                .name(userRequestDto.getName())
                .surname(userRequestDto.getName())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User update(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
