package br.com.vitor.minishop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey=@ForeignKey(name="fk_users_roles_user")),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey=@ForeignKey(name="fk_users_roles_role")))
    private List<Role> roles;
}
