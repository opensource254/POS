package pos.app.pharmacy_app.users.data_model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthenticationRequest {
    private String userName;
    private String password;
}
