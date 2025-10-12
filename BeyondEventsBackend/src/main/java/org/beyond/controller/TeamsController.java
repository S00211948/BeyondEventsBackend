package org.beyond.controller;

import org.beyond.dto.UserDto;
import org.beyond.model.User;
import org.beyond.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/teams")
public class TeamsController {

    @Autowired
    private UserRepository users;

    @GetMapping("/me")
    public UserDto me(@AuthenticationPrincipal Jwt jwt) {
        String oid = jwt.getClaimAsString("oid");
        String email = jwt.getClaimAsString("preferred_username");
        String name = jwt.getClaimAsString("name");

        User user = users.findById(oid).orElse(null);
        if (isNull(user)) {
            User u = new User();
            u.setId(oid);
            u.setEmail(email);
            u.setName(name);
            u.setCreatedAt(Instant.now());
            users.save(u);
        }
        // Upsert email/name if changed
        boolean changed = false;
        if (email != null && !email.equals(user.getEmail())) {
            user.setEmail(email);
            changed = true;
        }
        if (name != null && !name.equals(user.getName())) {
            user.setName(name);
            changed = true;
        }
        if (changed) users.save(user);

        return new UserDto(user.getId(), user.getEmail(), user.getName());
    }
}
