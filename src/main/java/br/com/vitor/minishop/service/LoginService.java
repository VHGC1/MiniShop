package br.com.vitor.minishop.service;

import br.com.vitor.minishop.domain.dto.LoginRequest;
import br.com.vitor.minishop.domain.dto.LoginResponse;
import br.com.vitor.minishop.domain.entity.User;
import br.com.vitor.minishop.exception.AppError;
import br.com.vitor.minishop.exception.AppException;
import br.com.vitor.minishop.repository.UserRepository;
import br.com.vitor.minishop.security.JwtHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final JwtHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public LoginService(JwtHelper jwtHelper, PasswordEncoder passwordEncoder, UserService userService, UserRepository userRepository) {
        this.jwtHelper = jwtHelper;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public String tokenReturn(LoginRequest loginRequest){
        Optional<User> user = userService.findByEmail(loginRequest.email());

        if(!user.isPresent())
            throw AppException.of(AppError.INVALID_CREDENTIALS);

        boolean passwordIsValid = passwordEncoder.matches(loginRequest.password(), user.get().getPassword());
        if(!passwordIsValid) throw AppException.of(AppError.INVALID_CREDENTIALS);

        return jwtHelper.createJwt(user.get());
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.email()).get();

        return new LoginResponse(tokenReturn(loginRequest), user);
    }
}
