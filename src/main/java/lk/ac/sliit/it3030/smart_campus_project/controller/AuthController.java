package lk.ac.sliit.it3030.smart_campus_project.controller;

import lk.ac.sliit.it3030.smart_campus_project.model.User;
import lk.ac.sliit.it3030.smart_campus_project.repository.UserRepository;
import lk.ac.sliit.it3030.smart_campus_project.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // Allow React to talk to Java
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/me")
    public ResponseEntity<?> getMe(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            String email = jwtUtil.extractEmail(jwt);
            Optional<User> user = userRepository.findByEmail(email);
            return ResponseEntity.ok(user.orElseThrow());
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }
}