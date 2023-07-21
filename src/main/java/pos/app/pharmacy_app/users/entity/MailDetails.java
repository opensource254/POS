package pos.app.pharmacy_app.users.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
