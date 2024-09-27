package com.gmail.alexflanker89.lesson.domain.auth;

import com.fasterxml.jackson.annotation.JsonView;
import com.gmail.alexflanker89.lesson.domain.auth.view.UserView;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Data
@Document
public class User implements UserDetails{
    @JsonView(UserView.Id.class)
    @Id
    private String id;
    @JsonView(UserView.IdName.class)
    @Size(min = 4, max = 20)
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Size(min = 2, max = 20)
    @NotBlank(message = "Password cannot be empty")
    private String password;

    private boolean active;
    @JsonView(UserView.FullProfile.class)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
