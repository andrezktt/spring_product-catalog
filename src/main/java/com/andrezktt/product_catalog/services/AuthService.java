package com.andrezktt.product_catalog.services;

import com.andrezktt.product_catalog.dto.EmailDTO;
import com.andrezktt.product_catalog.dto.NewPasswordDTO;
import com.andrezktt.product_catalog.entities.PasswordRecover;
import com.andrezktt.product_catalog.entities.User;
import com.andrezktt.product_catalog.repositories.PasswordRecoverRepository;
import com.andrezktt.product_catalog.repositories.UserRepository;
import com.andrezktt.product_catalog.services.exceptions.EmailException;
import com.andrezktt.product_catalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;

    @Value("${email.password-recover.uri}")
    private String recoverUri;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void createRecoverToken(EmailDTO body) {
        User user = userRepository.findByEmail(body.getEmail());
        if (user == null) {
            throw new EmailException("Email não encontrado");
        }

        PasswordRecover entity = new PasswordRecover();
        entity.setEmail(body.getEmail());
        String token = UUID.randomUUID().toString();
        entity.setToken(token);
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));
        entity = passwordRecoverRepository.save(entity);

        String text = "Acesse o link abaixo para definir uma nova senha:\n\n"
                + recoverUri + token
                + "\n\nCom validade de " + tokenMinutes + " minutos.";

        emailService.sendEmail(body.getEmail(), "Recuperação de Senha", text);
    }

    @Transactional
    public void saveNewPassword(NewPasswordDTO body) {
        List<PasswordRecover> result = passwordRecoverRepository.searchValidTokens(body.getToken(), Instant.now());
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Token inválido");
        }
        User user = userRepository.findByEmail(result.getFirst().getEmail());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        user = userRepository.save(user);
    }
}
