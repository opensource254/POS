package pos.app.pharmacy_app.users.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pos.app.pharmacy_app.users.data_model.KeyAndPasswordVM;
import pos.app.pharmacy_app.users.entity.Users;
import pos.app.pharmacy_app.users.services.UsersService;

import java.util.Optional;
@RequiredArgsConstructor
public class UserAccountResource {

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }
    private final UsersService usersService;
    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    private final Logger log = LoggerFactory.getLogger(UserAccountResource.class);
    @PostMapping(path = "/account/reset-password/init")
    public void requestPasswordReset(@RequestBody String mail) {
        Optional<Users> user = usersService.requestPasswordReset(mail);
        if (user.isPresent()) {
            //TODO write a function to implement email sending for password reset
        //    mailService.sendPasswordResetMail(user.get());
        } else {
            // Pretend the request has been successful to prevent checking which emails really exist
            // but log that an invalid attempt has been made
            log.warn("Password reset requested for non existing mail '{}'", mail);
        }
    }
    @PostMapping(path = "/account/reset-password/finish")
    public void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
        if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
            throw new IllegalArgumentException();
        }
        Optional<Users> user =
                usersService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());

        if (!user.isPresent()) {
            throw new AccountResourceException("No user was found for this reset key");
        }
    }
    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= PASSWORD_MIN_LENGTH &&
                password.length() <=PASSWORD_MAX_LENGTH;
    }



}
