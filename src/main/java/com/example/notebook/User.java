package com.example.notebook;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity 
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Implementáció a UserDetails interfészhez
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Az alábbi sor biztosítja, hogy a szerep (role) hozzá legyen rendelve a felhasználóhoz
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Az account nem jár le
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Az account nem zárt
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // A jelszó nem járt le
    }

    @Override
    public boolean isEnabled() {
        return true;  // A felhasználó engedélyezve van
    }
}
