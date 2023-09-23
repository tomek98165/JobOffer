package com.joboffers.domain.login;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryTestImpl implements UserRepository{

    public Map<String, User> users = new ConcurrentHashMap<>();


    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }

    @Override
    public User save(User user) {
        UUID id = UUID.randomUUID();
        User newUser = new User(
                id.toString(),
                user.username(),
                user.password()
        );
        users.put(newUser.username(), user);
        return user;
    }
}
