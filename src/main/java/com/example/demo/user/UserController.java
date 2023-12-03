package com.example.demo.user;

import com.example.demo.util.CodeValidator;
import com.example.demo.util.EmailValidator;
import com.example.demo.util.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path="/authorization")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody User user) {
        if (!EmailValidator.isPresentedEmail(user.getEmail())) {
            return new ResponseEntity<>("Email is required", HttpStatus.BAD_REQUEST);
        }

        if (!EmailValidator.isValidEmail(user.getEmail())) {
            return new ResponseEntity<>("Email is not valid", HttpStatus.BAD_REQUEST);
        }

        Optional<User> optionalUser = userService.findUserByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            return new ResponseEntity<>("Email is taken", HttpStatus.BAD_REQUEST);
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return new ResponseEntity<>("Password is required", HttpStatus.BAD_REQUEST);
        }

        if (!PasswordValidator.isValidPassword(user.getPassword())) {
            return new ResponseEntity<>("Password is not valid", HttpStatus.BAD_REQUEST);
        }

        userService.createUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path="/email-confirm", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity emailConfirm(@RequestBody EmailConfirmDto emailConfirmDto) {
        if (!EmailValidator.isPresentedEmail(emailConfirmDto.getEmail())) {
            return new ResponseEntity<>("Email is required", HttpStatus.BAD_REQUEST);
        }

        if (!EmailValidator.isValidEmail(emailConfirmDto.getEmail())) {
            return new ResponseEntity<>("Email is not valid", HttpStatus.BAD_REQUEST);
        }

        Optional<User> optionalUser = userService.findUserByEmail(emailConfirmDto.getEmail());

        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>("Email is not presented", HttpStatus.BAD_REQUEST);
        }

        if (emailConfirmDto.getCode() == null || emailConfirmDto.getCode().isEmpty()) {
            return new ResponseEntity<>("Code is required", HttpStatus.BAD_REQUEST);
        }

        if (emailConfirmDto.getCode().length() != 6) {
            return new ResponseEntity<>("Code length must be 6 digits", HttpStatus.BAD_REQUEST);
        }

        if (!CodeValidator.containsOnlyDigits(emailConfirmDto.getCode())) {
            return new ResponseEntity<>("Code must contains only digits", HttpStatus.BAD_REQUEST);
        }

        userService.emailConfirm(emailConfirmDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
