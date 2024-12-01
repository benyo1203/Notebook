package com.example.notebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    // Felhasználók lekérdezése
    @GetMapping
    public List<UserDto> getAllUsers() {
        return ((List<User>) userRepo.findAll()).stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(user.getId());
                    userDto.setUsername(user.getUsername());
                    userDto.setEmail(user.getEmail());
                    userDto.setRole(user.getRole());
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    // Felhasználó keresése
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        return userRepo.findById(id)
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(user.getId());
                    userDto.setUsername(user.getUsername());
                    userDto.setEmail(user.getEmail());
                    userDto.setRole(user.getRole());
                    return new ResponseEntity<>(userDto, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Felhasználó létrehozása
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        user.setRole("USER");  // Alapértelmezett szerep
        userRepo.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    // Felhasználó frissítése
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable int id, @RequestBody User userDetails) {
        return userRepo.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setEmail(userDetails.getEmail());
                    user.setRole(userDetails.getRole());
                    userRepo.save(user);

                    UserDto userDto = new UserDto();
                    userDto.setId(user.getId());
                    userDto.setUsername(user.getUsername());
                    userDto.setEmail(user.getEmail());
                    userDto.setRole(user.getRole());

                    return new ResponseEntity<>(userDto, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Felhasználó törlése
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        return userRepo.findById(id)
                .map(user -> {
                    userRepo.delete(user);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
