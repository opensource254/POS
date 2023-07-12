//package pos.app.pharmacy_app.email;
//
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.MessageSource;
//import org.springframework.scheduling.annotation.Async;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import pos.app.pharmacy_app.users.entity.Users;
//
//import java.util.Locale;
//
//@RequiredArgsConstructor
//public class MailService {
//    private static final String USER = "user";
//
//    private static final String BASE_URL = "baseUrl";
//
//    private final JHipsterProperties jHipsterProperties;
//
//
//    private final JavaMailSender javaMailSender;
//
//    private final MessageSource messageSource;
//
//    private final SpringTemplateEngine templateEngine;
//    private final Logger log = LoggerFactory.getLogger(MailService.class);
//
//    @Async
//    public void sendEmailFromTemplate(Users user, String templateName, String titleKey) {
//        if (user.getEmail() == null) {
//            log.debug("Email doesn't exist for user '{}'", user.getLogin());
//            return;
//        }
//        Locale locale = Locale.forLanguageTag(user.getLangKey());
//        Context context = new Context(locale);
//        context.setVariable(USER, user);
//        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
//        String content = templateEngine.process(templateName, context);
//        String subject = messageSource.getMessage(titleKey, null, locale);
//        sendEmail(user.getEmail(), subject, content, false, true);
//    }
//
//    @Async
//    public void sendActivationEmail(Users user) {
//        log.debug("Sending activation email to '{}'", user.getEmail());
//        sendEmailFromTemplate(user, "mail/activationEmail", "email.activation.title");
//    }
//
//    @Async
//    public void sendCreationEmail(Users user) {
//        log.debug("Sending creation email to '{}'", user.getEmail());
//        sendEmailFromTemplate(user, "mail/creationEmail", "email.activation.title");
//    }
//
//    @Async
//public void sendPasswordResetMail(Users user) {
//    log.debug("Sending password reset email to '{}'", user.getEmail());
//    sendEmailFromTemplate(user, "mail/passwordResetEmail", "email.reset.title");
//}
//}
