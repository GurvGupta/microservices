package com.example.user_service.controller;

import com.example.user_service.entity.UnderWriter;
import com.example.user_service.service.UnderWriterService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UnderWriterController {
    private final UnderWriterService service;

    public UnderWriterController(UnderWriterService service) {
        this.service = service;
    }

    @PostMapping("/registerUnderWriter")
    public UnderWriter registerUnderWriter(@RequestParam String name,
                                           @RequestParam String dob,
                                           @RequestParam String password) {
        return service.registerUW(name, LocalDate.parse(dob), password);
    }
    
    @GetMapping
    public List<UnderWriter> getAllUnderwriters() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<UnderWriter> searchByName(@RequestParam String name) {
        return service.searchByName(name);
    }

    @PostMapping("/registerAdmin")
    public String registerAdmin(@RequestParam String username, @RequestParam String password) {
        service.registerAdmin(username, password);
        return "Admin registered successfully";
    }

    @GetMapping("/{id}")
    public UnderWriter getById(@PathVariable String id) {
        return service.getById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public UnderWriter updateUW(@PathVariable String id,
                                @RequestParam String name,
                                @RequestParam String dob) {
        return service.updateUW(id, name, LocalDate.parse(dob));
    }

    @DeleteMapping("/{id}")
    public String deleteUW(@PathVariable String id) {
        service.deleteUW(id);
        return "Deleted successfully";
    }
}
