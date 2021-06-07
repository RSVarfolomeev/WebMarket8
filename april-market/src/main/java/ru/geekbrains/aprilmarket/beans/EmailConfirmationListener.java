package ru.geekbrains.aprilmarket.beans;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.geekbrains.aprilmarket.entities.User;
import ru.geekbrains.aprilmarket.services.EmailVerificationTokenService;
import ru.geekbrains.aprilmarket.util.OnEmailVerificationCompleteEvent;

import java.util.UUID;

@Component
public class EmailConfirmationListener implements ApplicationListener<OnEmailVerificationCompleteEvent> {
    private  EmailVerificationTokenService emailVerificationTokenService;
    private  JavaMailSender javaMailSender;

    @Value("${email.verify.url: path-to-our-app/register/confirm?token=}")
    private String confirmationUrl;

    @Value("${spring.mail.username: aaaa}")
    private String emailUsername;

    @Value("${spring.mail.password: aaaa}")
    private String emailPassword;

    @Autowired
    public void setEmailVerificationTokenService(EmailVerificationTokenService emailVerificationTokenService) {
        this.emailVerificationTokenService = emailVerificationTokenService;
    }
    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void onApplicationEvent(OnEmailVerificationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnEmailVerificationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        emailVerificationTokenService.createToken(user, token);

        String recipientAddress = user.getEmail();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailUsername);
        message.setTo(recipientAddress);
        message.setSubject("Confirm your email");
        message.setText("Please verify your email on april-market \r\n" + confirmationUrl + token);
        javaMailSender.send(message);
    }
}
