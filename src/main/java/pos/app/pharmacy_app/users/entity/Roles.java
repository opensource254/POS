package pos.app.pharmacy_app.users.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;


@Data
@AllArgsConstructor
@Table(name = "ROLES")
@Entity

public class Roles implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long roleId;
    @Column(name = "ROLE_NAME")
    //private UserType roleNames;
    private String roleName;
    @Column(name = "Authorities")
    private String authorities;
    public Roles() {
        super();
    }

    @ManyToMany(mappedBy="roles")
    private List<Users> users;
    @Override
    public String getAuthority() {
        return authorities;
    }
}
