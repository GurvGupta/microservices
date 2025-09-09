package com.example.user_service.service;


import com.example.user_service.entity.Login;
import com.example.user_service.entity.Role;
import com.example.user_service.repository.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final LoginRepository repo;

    public AuthService(LoginRepository repo) {
        this.repo = repo;
    }

    public void register(String username, String password, Role role) {
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);  // ⚠️ plain text for demo, don't use in prod
        login.setRole(role);
        repo.save(login);
    }
    
    public void updatePassword(String username, String newPassword) {
        Login login = repo.findByUsername(username).orElseThrow();
        login.setPassword(newPassword);  // plain text for demo (⚠️ should hash in prod)
        repo.save(login);
    }
    
    public void deleteLogin(String username) {
        repo.findByUsername(username).ifPresent(repo::delete);
    }

    public boolean validate(String username, String password) {
        return repo.findByUsername(username)
                .map(l -> l.getPassword().equals(password))
                .orElse(false);
    }
}