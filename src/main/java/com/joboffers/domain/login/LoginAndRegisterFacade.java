package com.joboffers.domain.login;

import com.joboffers.domain.login.dto.RegisterUserDto;
import com.joboffers.domain.login.dto.RegistrationResultDto;
import com.joboffers.domain.login.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LoginAndRegisterFacade {

    UserRepository userRepository;


    public UserDto findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .map(user -> new UserDto(user.id(), user.username(), user.password()))
                .orElseThrow(() -> new BadCredentialsException("User not found"));
    }

    public RegistrationResultDto registerUser(RegisterUserDto registerUserDto){
        User user = User.builder()
                .username(registerUserDto.username())
                .password(registerUserDto.password())
                .build();
        User savedUser = userRepository.save(user);
        return new RegistrationResultDto(savedUser.id(), true, savedUser.username());
    }


}
