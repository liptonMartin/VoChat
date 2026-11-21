package com.lipton.auth;

import com.lipton.auth.app.AuthHandle;
import com.lipton.auth.models.LoginBody;
import com.lipton.auth.models.RegisterBody;
import com.lipton.auth.models.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class Controller {

    private final AuthHandle authHandle;

    public Controller(AuthHandle authHandle) {
        this.authHandle = authHandle;
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterBody body) {
        var username = body.username();
        var password = body.password();
        var confirmPassword = body.confirmPassword();

        var user = authHandle.handleRegister(username, password, confirmPassword);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody LoginBody body) {
        var username = body.username();
        var password = body.password();

        var user = authHandle.handleLogin(username, password);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


}
