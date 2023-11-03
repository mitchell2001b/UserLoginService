package com.example.UserLoginService.kafka;

import com.example.UserLoginService.Account.Account;
import com.example.UserLoginService.Account.AccountService;
import com.example.UserLoginService.Events.UserCreatedEvent;
import com.example.UserLoginService.dtos.AccountLoginDto;
import com.example.UserLoginService.dtos.AccountRoleDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class RegistrationConsumer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationConsumer.class);
    private DateTimeFormatter DateTimeFormatterEvent = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ObjectMapper objectMapper;

    private AccountService accountService;

    @Autowired
    public RegistrationConsumer(ObjectMapper objectMapper, AccountService service)
    {
        this.objectMapper = objectMapper;
        this.accountService = service;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String jsonEvent)
    {

        try {

            Map<String, Object> eventMap = objectMapper.readValue(jsonEvent, new TypeReference<Map<String, Object>>() {});

            // Extract specific fields like "email" and "passWord" from the map

            int accountId = (Integer) eventMap.get("id");


            String email = (String) eventMap.get("email");
            String passWord = (String) eventMap.get("passWord");


            int roleId = (Integer) eventMap.get("roleId");

            String roleName = (String) eventMap.get("roleName");

            String createdAtString = (String) eventMap.get("createdAt");
            LocalDate createdAt = LocalDate.parse(createdAtString, DateTimeFormatterEvent);
            AccountRoleDto roleDto = new AccountRoleDto(roleId, roleName);
            AccountLoginDto loginDto = new AccountLoginDto(accountId, passWord, email, roleDto);
            Account newAccount = new Account(passWord, email, roleId, roleName);
            accountService.AddAccount(newAccount);
            LOGGER.info(String.format("User created event received in login service => %s", loginDto.toString()));

            // Process the extracted fields as needed
        } catch (JsonProcessingException e) {
            // Handle JSON deserialization exception
            LOGGER.error("Error deserializing JSON message", e);
        }
    }
}
