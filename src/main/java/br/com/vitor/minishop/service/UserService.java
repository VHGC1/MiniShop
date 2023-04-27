package br.com.vitor.minishop.service;

import br.com.vitor.minishop.domain.dto.*;
import br.com.vitor.minishop.domain.entity.Role;
import br.com.vitor.minishop.domain.entity.User;
import br.com.vitor.minishop.repository.UserRepository;
import br.com.vitor.minishop.util.PasswordGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserCreateResponse createUser(UserCreateRequest user) {
        HashMap<String, Integer> rolesDic = new HashMap<>();
        rolesDic.put("ROLE_BACKOFFICE_USER", 1);
        rolesDic.put("ROLE_ADMIN_USER", 2);


        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já existente.");
        }

        String password = PasswordGenerator.generate();

        String encodedPassword = passwordEncoder.encode(password);

        List<Role> roles = new ArrayList<>();

        if (user.getRoles().size() > 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "É permitido somente um role por usuario");
        }

        for (int i = 0; i < user.getRoles().size(); i++) {
            Role role = new Role();
            if(!rolesDic.containsKey(user.getRoles().get(i).getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role não existente");
            }

            role.setId(rolesDic.get(user.getRoles().get(i).getName()));
            role.setName(user.getRoles().get(i).getName());
            roles.add(role);
        }

        User novoUser = new User();

        novoUser.setPassword(encodedPassword);
        novoUser.setEmail(user.getEmail());
        novoUser.setRoles(roles);

        User userSalvo = this.userRepository.save(novoUser);

        return new UserCreateResponse(
                userSalvo.getId(),
                userSalvo.getEmail(),
                password,
                userSalvo.getRoles()
        );
    }


    public ResponseBase<Page<UserResponse>> getAllUsers(PaginatedSearchRequest page) {
        PageRequest pagination = PageRequest.of(page.getPage(), page.getSize());
        Page<User> userPage = userRepository.findAll(pagination);
        Page<UserResponse> userResponsePage = userPage.map(UserResponse::new);



        return new ResponseBase<>(userResponsePage);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
