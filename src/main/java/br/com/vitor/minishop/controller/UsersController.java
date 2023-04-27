package br.com.vitor.minishop.controller;

import br.com.vitor.minishop.domain.dto.*;
import br.com.vitor.minishop.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RolesAllowed("ROLE_ADMIN_USER")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserCreateResponse> createUsers(@RequestBody UserCreateRequest user) {
        UserCreateResponse retorno = userService.createUser(user);

        return ResponseEntity.ok(retorno);
    }

    @GetMapping
    public  ResponseEntity<Page<UserResponse>> getUsers(@Valid PaginatedSearchRequest page) {
        ResponseBase<Page<UserResponse>> response = userService.getAllUsers(page);

        return ResponseEntity.ok(response.getObject());
    }
}
