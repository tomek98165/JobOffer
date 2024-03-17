package com.joboffers.domain.loginandregister;

import com.joboffers.domain.loginandregister.dto.RegisterUserDto;
import com.joboffers.domain.loginandregister.dto.RegistrationResultDto;
import com.joboffers.domain.loginandregister.dto.UserDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class LoginAndRegisterFacadeTest {

    private final LoginAndRegisterFacade loginAndRegisterFacade = new LoginAndRegisterFacade(new UserRepositoryTestImpl());

    @Test
    public void should_register_user(){

        RegistrationResultDto registrationResultDto = loginAndRegisterFacade.registerUser(
                new RegisterUserDto(
                        "username",
                        "Pa$$word"));

        assertThat(registrationResultDto).isEqualTo(new RegistrationResultDto(registrationResultDto.id(), true, "username"));
    }
    @Test
    public void should_find_user_in_memory_by_username(){

        RegisterUserDto registerUserDto = new RegisterUserDto("username", "Pa$$word");
        RegistrationResultDto registeredUser = loginAndRegisterFacade.registerUser(registerUserDto);

        UserDto foundUser = loginAndRegisterFacade.findUserByUsername("username");

        assertThat(foundUser).isEqualTo(new UserDto(registeredUser.id(), "username", "Pa$$word"));

    }

    @Test
    public void should_throw_exception_when_user_doesnt_exist(){

        Throwable thrown = catchThrowable(() -> loginAndRegisterFacade.findUserByUsername("username"));


        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("User not found");


    }

}