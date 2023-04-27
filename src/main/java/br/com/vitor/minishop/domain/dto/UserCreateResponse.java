package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateResponse {
    private Long Id;
    private String email;
    private String password;
    private List<Role> Roles;
}
