package com.joboffers.domain.login;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByUsername(String username);

    User save(User user);
}
