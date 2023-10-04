package pos.app.pharmacy_app.users.data_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pos.app.pharmacy_app.users.entity.UserType;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private String firstName;
    private String lastName;
    private String Phone;
    public  UserType userType;
    private String email;
    private Date createAt;
    private String createdBy;
    private String deletedBy;
    private String updatedBy;


}
