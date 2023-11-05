package com.example.UserLoginService.repositories;

import com.example.UserLoginService.Account.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AccountCustomRepository implements IAccountCustomRepository
{
    @PersistenceContext
    private EntityManager entityManager;

    public Account login(String email, String password) {
        return entityManager.createQuery("SELECT a FROM Account a WHERE a.Email = :email AND a.Password = :password", Account.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }
    @Override
    public List<Account> findByEmailAndPassword(String email, String password) {
        return entityManager.createNamedQuery("Account.findByEmailAndPassword", Account.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();
    }
    @Transactional
    public Account findAccountByEmailAndPassword(String email, String password) {
        // Create and execute a native SQL query
        String sql = "SELECT * FROM account WHERE email = :email AND password = :password";
        List<Account> accounts = entityManager.createNativeQuery(sql, Account.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();

        if (!accounts.isEmpty()) {
            return accounts.get(0); // Assuming the list should contain at most one result
        } else {
            return null; // Handle the case where no matching account is found
        }
    }
}
