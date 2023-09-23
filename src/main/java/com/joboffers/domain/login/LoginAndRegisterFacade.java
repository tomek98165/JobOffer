package com.joboffers.domain.login;

import com.joboffers.domain.login.dto.RegisterUserDto;
import com.joboffers.domain.login.dto.RegistrationResultDto;
import com.joboffers.domain.login.dto.UserDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginAndRegisterFacade {

    UserRepository userRepository;


    UserDto findUserByUsername(String username){
        return userRepository.findUserByUsername(username)
                .map(user -> new UserDto(user.id(), user.username(), user.password()))
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    RegistrationResultDto registerUser(RegisterUserDto registerUserDto){
        User user = User.builder()
                .username(registerUserDto.username())
                .password(registerUserDto.password())
                .build();
        User savedUser = userRepository.save(user);
        return new RegistrationResultDto(savedUser.id(), true, savedUser.username());
    }


}
