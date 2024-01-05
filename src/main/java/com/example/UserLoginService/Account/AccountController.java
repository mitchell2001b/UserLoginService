package com.example.UserLoginService.Account;

import com.example.UserLoginService.AzureServices.KeyVaultService;
import com.example.UserLoginService.dtos.AccountLoginDto;
import com.example.UserLoginService.kafka.RegistrationConsumer;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping(path = "api/accounts")
public class AccountController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationConsumer.class);
    AccountService accountService;
    KeyVaultService keyVaultService;


    @Autowired
    public AccountController(AccountService service, KeyVaultService keyService)
    {
        this.accountService = service;
        this.keyVaultService = keyService;
    }

    private String secretKey;

    @GetMapping(value = "/lop")
    public String testr()
    {
        secretKey = keyVaultService.getSecretValue("semester6key");

        LOGGER.info("Secret Key from Azure Key Vault: " + secretKey);
        return "hello world";
    }
    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, String>> Login(@RequestBody AccountLoginDto loginData)
    {

        AccountLoginDto dto = accountService.Login(loginData.getPassWord(), loginData.getEmail());

        if(dto != null)
        {
            Instant expirationDate = Instant.now().plusSeconds(3600); // Expires 1 hour from now
            Instant refreshTokenExpirationDate = Instant.now().plusSeconds(7 * 24 * 60 * 60); // Expires in 7 days


            SecretKey signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());

            String jwt = Jwts.builder()
                    .claim("email", dto.getEmail())
                    .claim("id", dto.getId())
                    .claim("roleId", dto.getRoleDto().getId())
                    .claim("roleName", dto.getRoleDto().getName())
                    .setExpiration(Date.from(expirationDate))
                    .signWith(signingKey)
                    .compact();

            String refreshToken = Jwts.builder()
                    .setExpiration(Date.from(refreshTokenExpirationDate))
                    .signWith(signingKey)
                    .compact();


            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", jwt);
            tokens.put("refresh_token", refreshToken);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Access-Control-Allow-Origin", "*")
                    .body(tokens);


        }
        else
        {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .header("Access-Control-Allow-Origin", "*")
                    .body(Collections.singletonMap("error", "Wrong email or password"));
        }

    }
}
