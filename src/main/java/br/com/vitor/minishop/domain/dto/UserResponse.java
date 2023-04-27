package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Role;
import br.com.vitor.minishop.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long Id;
    private String Email;
    private List<Role> Roles;

    public UserResponse(User user) {
        this.Id = user.getId();
        this.Email = user.getEmail();
        this.Roles = user.getRoles();
    }
}