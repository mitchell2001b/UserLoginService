package com.example.UserLoginService.kafka;

import com.example.UserLoginService.Account.Account;
import com.example.UserLoginService.Account.AccountService;
import com.example.UserLoginService.dtos.AccountLoginDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class RegistrationDeleteConsumer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationDeleteConsumer.class);
    private DateTimeFormatter DateTimeFormatterEvent = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ObjectMapper objectMapper;

    private AccountService accountService;

    @Autowired
    public RegistrationDeleteConsumer(ObjectMapper objectMapper, AccountService service)
    {
        this.objectMapper = objectMapper;
        this.accountService = service;
    }

    @KafkaListener(topics = "${spring.kafka.topic2.name}", groupId = "${spring.kafka.consumer2.group-id}")
    public void consume(String jsonEvent)
    {
        try
        {
            Map<String, Object> eventMap = objectMapper.readValue(jsonEvent, new TypeReference<Map<String, Object>>() {});

            Number accountIdNumber = (Number) eventMap.get("id");
            Long accountId = accountIdNumber.longValue();
            String email = (String) eventMap.get("email");
            String date = (String)  eventMap.get("createdat");
            LocalDateTime createdAt = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
            Date createdDate = java.util.Date.from(createdAt.atZone(java.time.ZoneId.systemDefault()).toInstant());
            LOGGER.info(date);
            AccountLoginDto dto = new AccountLoginDto(accountId, "", email, null);

            LOGGER.info(String.format("User deleted event received in login service => %s", dto.toString()));
            Account accountToDelete = accountService.SelectAccount(accountId, email);
            if(accountToDelete != null)
            {
                accountService.DeleteAccount(accountToDelete);
            }


        }catch (JsonProcessingException e) {


            LOGGER.error("Error deserializing JSON message", e);
        }


    }
}
