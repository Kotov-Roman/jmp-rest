package com.epam;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User get(Long id);

    User create(User User);

    User update(User User);

    void delete(Long id);
}
