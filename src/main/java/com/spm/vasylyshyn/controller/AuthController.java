package com.spm.vasylyshyn.controller;


import com.spm.vasylyshyn.request.LoginRequest;
import com.spm.vasylyshyn.request.SignupRequest;
import com.spm.vasylyshyn.validations.ResponseErrorValidation;
import com.spm.vasylyshyn.model.User;
import com.spm.vasylyshyn.response.JWTTokenSuccessResponse;
import com.spm.vasylyshyn.response.MessageResponse;

import com.spm.vasylyshyn.security.JWTTokenProvider;
import com.spm.vasylyshyn.security.SecurityConstants;
import com.spm.vasylyshyn.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {



    private final ResponseErrorValidation responseErrorValidation;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JWTTokenProvider jwtTokenProvider;

    public AuthController(ResponseErrorValidation responseErrorValidation, UserService userService, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.responseErrorValidation = responseErrorValidation;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail()
               , loginRequest.getPassword()

        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) throws IOException {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        User newUser = userService.createUser(signupRequest);
        Path path = Paths.get("./src/main/resources/img/avatar.jpg");
        String name = "avatar.jpg";
        String originalFileName = "avatar.jpg";
        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        return ResponseEntity.ok(new MessageResponse("User registreted successfully"));

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(Principal principal){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
