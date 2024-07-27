package com.example.backend.controller;

import com.example.backend.model.AppUser;
import com.example.backend.model.LoginDto;
import com.example.backend.model.RegisterDto;
import com.example.backend.repository.AppUserRepository;
import com.example.backend.service.EmailService;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*")
public class AccountController {

    @Value("${security.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${security.jwt.issuer}")
    private String jwtIssuer;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Object> register (
            @Valid @RequestBody RegisterDto registerDto
            , BindingResult result) {
        if(result.hasErrors()) {
            var errorList = result.getAllErrors();
            var errorsMap = new HashMap<String, String>();
            for(int i=0; i< errorList.size(); i++) {
                var error = (FieldError) errorList.get(i);
                errorsMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorsMap);
        }

        var bCryptEncoder = new BCryptPasswordEncoder();
        AppUser appUser = new AppUser();
        appUser.setUserName(registerDto.getUserName());
        appUser.setEmail(registerDto.getEmail());
        appUser.setPhoneNumber(registerDto.getPhoneNumber());
        appUser.setRole("client");
        appUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));
        appUser.setCreatedAt(new Date());

        try {
            var otherUser = appUserRepository.findByUserName(registerDto.getUserName());
            if(otherUser != null){
                return ResponseEntity.badRequest().body("Username already exists");
            }
            otherUser = appUserRepository.findByEmail(registerDto.getEmail());
            if(otherUser != null){
                return ResponseEntity.badRequest().body("Email address already exists");
            }
            appUserRepository.save(appUser);
            emailService.sendEmail(appUser.getEmail(), "Registration Successful", "Your Registration into WheelsUp was Successfully");
            String jwtToken = createJwtToken(appUser);

            var response = new HashMap<String, Object>();
            response.put("token", jwtToken);
            response.put("user", appUser);

            return ResponseEntity.ok(response);
        }
        catch (Exception ex){
            System.out.println("There is an Exception");
            ex.printStackTrace();
        }

        return ResponseEntity.badRequest().body("Error");
    }
    private String createJwtToken(AppUser appUser) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(2*3600))
                .subject(appUser.getUserName())
                .claim("role", appUser.getRole())
                .build();

        var encoder = new NimbusJwtEncoder(
                new ImmutableSecret<>(jwtSecretKey.getBytes()));
        var params = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(), claims);

        return encoder.encode(params).getTokenValue();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @Valid @RequestBody LoginDto loginDto, BindingResult result) {
        if(result.hasErrors()) {
            var errorList = result.getAllErrors();
            var errorsMap = new HashMap<String, String>();
            for(int i=0; i< errorList.size(); i++) {
                var error = (FieldError) errorList.get(i);
                errorsMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorsMap);
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );
            AppUser appUser = appUserRepository.findByUserName(loginDto.getUsername());
            String jwtToken = createJwtToken(appUser);

            var response = new HashMap<String, Object>();
            response.put("token", jwtToken);
            response.put("user", appUser);

            return ResponseEntity.ok(response);
        }
        catch (Exception ex)
        {
            System.out.println("There is an Exception");
            ex.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Bad username or password");

    }

    @GetMapping("/profile")
    public ResponseEntity<Object> profile(Authentication auth) {
        var response = new HashMap<String, Object>();
        response.put("Username", auth.getName());
        response.put("Authorities", auth.getAuthorities());

        var appUser = appUserRepository.findByUserName(auth.getName());
        response.put("User", appUser);
        response.put("UserId", appUser.getId());
         return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllUsers")
    public Iterable<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }
}
