package pos.app.pharmacy_app.users.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.*;

@Table(name = "USERS")
@Entity
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE ORDERS SET deleted = true WHERE userId=?")
@FilterDef(name = "deletedUsersFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedUsersFilter", condition = "deleted = :isDeleted")
public class Users {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    @SequenceGenerator(sequenceName = "users_seq", allocationSize = 1, name = "USERS_SEQ")
    private Long userId;

    private UserType userType;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    @NonNull
    @NotBlank
    @Email(message = "email invalid")
    private String email;
    @NotBlank
    @Pattern(regexp = "(^[0-9]+$|^$)", message = "number only")
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASWORD")
    private String password;
    @Column(name = "CREATED_AT")
    private Date createdAt;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "DELETED_BY")
    private String deletedBy;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_AT")
    private Date updatedAt;
    private  Boolean deleted=Boolean.FALSE;

    private Boolean locked = false;


    private Boolean enabled = true;
    @ManyToMany
    @JoinColumn(name = "USER_ROLES", referencedColumnName = "roleId")
    private Set<Roles>roles=new HashSet<>();

    public Users(UserType userType, String firstName, String lastName,
                 String email, String phone, String username,
                 String password, Boolean deleted) {
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.deleted = deleted;
    }
}
