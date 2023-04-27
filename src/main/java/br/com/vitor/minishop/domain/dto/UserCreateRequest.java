package br.com.vitor.minishop.domain.dto;

import br.com.vitor.minishop.domain.entity.Role;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class UserCreateRequest {
    @NotEmpty(message = "Username can't be empty")
    @Size(min = 1, max = 50, message = "Username must have less than 50 characters")
    private String email;
    private List<Role> roles;
}
