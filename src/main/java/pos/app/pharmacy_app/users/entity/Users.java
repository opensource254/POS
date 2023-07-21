package pos.app.pharmacy_app.users.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pos.app.pharmacy_app.Constants;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Table(name = "USERS")
@SQLDelete(sql = "UPDATE USERS SET deleted = true WHERE userId=?")
@Entity
@Data
@NoArgsConstructor(force = true)
@Builder
@AllArgsConstructor
//@FilterDef(name = "deletedUsersFilter", parameters =  @ParamDef(name = "isDeleted", type = "boolean"))
//@Filter(name = "deletedProductFilter", condition = "deleted = :isDeleted")
public class Users implements UserDetails {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    @SequenceGenerator(sequenceName = "users_seq", allocationSize = 1, name = "USERS_SEQ")
    private Long userId;
    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;


    private UserType userType;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    @Nonnull
    @Email(message = "email invalid")
    private String email;
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
    private Instant modifiedAt;

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)

    @JsonIgnore
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;
    private  Boolean deleted=Boolean.FALSE;

    private Boolean locked = false;

    @Size(min = 2, max = 10)
    private String langKey;

    private Boolean enabled = true;
   @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Roles_Junction",joinColumns = {@JoinColumn(name = "USER_ID")},inverseJoinColumns =
            {@JoinColumn(name = "ROLE_ID")})
    private Set<Roles> roles;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
