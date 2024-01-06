package com.example.UserLoginService.configuration;

import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyVaultConfig {

    /*@Bean
    public SecretClient secretClient() {
        return new SecretClientBuilder()
                .vaultUrl("https://semeter6kluis.vault.azure.net/")
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }*/
    @Value("${azure.keyvault.client-id}")
    private String clientId;

    @Value("${azure.keyvault.client-secret}")
    private String clientSecret;

    @Value("${azure.keyvault.tenant-id}")
    private String tenantId;

    @Bean
    public SecretClient secretClient() {
        return new SecretClientBuilder()
                .vaultUrl("https://semeter6kluis.vault.azure.net/")
                .credential(new ClientSecretCredentialBuilder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .tenantId(tenantId)
                        .build())
                .buildClient();
    }
}