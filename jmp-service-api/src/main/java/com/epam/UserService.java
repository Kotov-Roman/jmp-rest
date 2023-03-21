package com.epam;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User get(Long id);

    User create(UserRequestDto userRequestDto);

    User update(UserRequestDto userRequestDto);

    void delete(Long id);
}
