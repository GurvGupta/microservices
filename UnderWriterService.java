package com.example.user_service.service;

import com.example.user_service.entity.Role;
import com.example.user_service.entity.UnderWriter;
import com.example.user_service.repository.UnderWriterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UnderWriterService {
    private final UnderWriterRepository repo;
    private final AuthService auth;

    public UnderWriterService(UnderWriterRepository repo, AuthService auth) {
        this.repo = repo;
        this.auth = auth;
    }

    public UnderWriter registerUW(String name, LocalDate dob, String password) {
        String id = "UW-" + UUID.randomUUID().toString().substring(0, 6);
        UnderWriter uw = new UnderWriter(id, name, dob);
        repo.save(uw);
        auth.register(id, password, Role.UNDERWRITER);
        return uw;
    }
    
    public List<UnderWriter> getAll() {
        return repo.findAll();
    }

    public List<UnderWriter> searchByName(String name) {
        return repo.findAll().stream()
                   .filter(uw -> uw.getName().toLowerCase().contains(name.toLowerCase()))
                   .toList();
    }

    public void registerAdmin(String username, String password) {
        auth.register(username, password, Role.ADMIN);
    }
    
    
    public Optional<UnderWriter> getById(String id) {
        return repo.findById(id);
    }
    
    public void updateUnderWriterPassword(String id, String newPassword) {
        auth.updatePassword(id, newPassword);
    }
    
    public UnderWriter updateUW(String id, String name, LocalDate dob) {
        UnderWriter uw = repo.findById(id).orElseThrow();
        uw.setName(name);
        uw.setDob(dob);
        return repo.save(uw);
    }

    public void deleteUW(String id) {
        repo.deleteById(id);
        auth.deleteLogin(id);
    }
}