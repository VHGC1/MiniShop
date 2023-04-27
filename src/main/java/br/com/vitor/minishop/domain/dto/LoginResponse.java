package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Role;
import br.com.vitor.minishop.domain.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {
    private Long Id;
    private String Email;
    private List<Role> roles;
    private String Token;

    public LoginResponse(String Token, User user) {
        this.Id = user.getId();
        this.Email = user.getEmail();
        this.roles = user.getRoles();
        this.Token = Token;
    }
}
