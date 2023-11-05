package com.example.demo.code;

import com.example.demo.user.User;
import com.example.demo.util.DigitGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public void sendEmail(SendEmailDto emailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailDto.getTo());
        simpleMailMessage.setSubject(emailDto.getSubject());
        simpleMailMessage.setText(emailDto.getText());

        javaMailSender.send(simpleMailMessage);
    }

    public void saveCode(User savedUser) {
        String code = DigitGenerator.generateSixDigitString();
        Code newCode = new Code(code, savedUser);
        codeRepository.save(newCode);
        String email = savedUser.getEmail();
        SendEmailDto emailDto = new SendEmailDto(email, "Registration", code);
        sendEmail(emailDto);
    }

    public Optional<Code> getCodeByUserId(Integer userId) {
        return codeRepository.findCodeByUserId(userId);
    }

    public void deleteCodeById(Integer codeId) {
        codeRepository.deleteCodeById(codeId);
    }
}
