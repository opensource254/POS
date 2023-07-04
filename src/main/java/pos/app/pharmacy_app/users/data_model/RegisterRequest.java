package pos.app.pharmacy_app.users.data_model;

import lombok.*;
import pos.app.pharmacy_app.users.entity.Roles;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String Phone;
    public Roles roleId;
    private String phone;
    private String email;
    private String password;
    private String roleName;
}
