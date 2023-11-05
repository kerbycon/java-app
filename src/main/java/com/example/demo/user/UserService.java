package com.example.demo.user;

import com.example.demo.code.Code;
import com.example.demo.code.CodeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final CodeService codeService;

    @Autowired
    UserService(UserRepository userRepository, CodeService codeService) {
        this.userRepository = userRepository;
        this.codeService = codeService;
    }

    public Optional<User> findUserByEmail(String email) {
       return userRepository.findUserByEmail(email);
    }

    public void createUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        codeService.saveCode(savedUser);
    }

    @Transactional
    public void emailConfirm(EmailConfirmDto emailConfirmDto) {
        String email = emailConfirmDto.getEmail();
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Integer userId = user.getId();
            Optional<Code> optionalCode = codeService.getCodeByUserId(userId);

            if(optionalCode.isPresent()) {
                Code foundCode = optionalCode.get();
                String codeValue = foundCode.getCode();
                String code = emailConfirmDto.getCode();

                if (code.equals(codeValue)) {
                    user.setIsActive(true);
                    Integer codeId = foundCode.getId();
                    codeService.deleteCodeById(codeId);
                }
            }
        }
    }
}
