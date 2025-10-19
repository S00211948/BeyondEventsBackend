package org.beyond.service;

import lombok.extern.slf4j.Slf4j;
import org.beyond.dto.UserDto;
import org.beyond.model.User;
import org.beyond.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.util.Map;

@Service
@Slf4j
public class MsTeamsService {
    @Autowired
    private UserRepository users;

    @Value("${AZURE_AD_TENANT_ID}")
    String tenant;

    @Value("${AZURE_AD_CLIENT_ID}")
    String clientId;

    @Value("${AZURE_AD_CLIENT_SECRET}")
    String clientSecret;

    private final RestClient http = RestClient.create();

    public UserDto teamsLogin(String oid, String email, String name) {
        User user = users.findById(oid).orElseGet(() -> {
            User u = new User();
            u.setId(oid);
            u.setEmail(email);
            u.setName(name);
            u.setCreatedAt(Instant.now());
            log.info("User saved");
            return users.save(u);
        });

        // Upsert if email/name if changed
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

        log.info("Upsert finished {}", user);
        return new UserDto(user.getId(), user.getEmail(), user.getName());
    }

    public byte[] getTeamsProfileIcon(Jwt jwt) {
        String graphToken = exchangeForGraphToken(jwt.getTokenValue(), "User.Read");
        return http.get()
                .uri("https://graph.microsoft.com/v1.0/me/photos/64x64/$value")
                .header("Authorization", "Bearer " + graphToken)
                .retrieve()
                .body(byte[].class);
    }

    private String exchangeForGraphToken(String userAccessToken, String scope) {
        String tokenEndpoint = "https://login.microsoftonline.com/" + tenant + "/oauth2/v2.0/token";
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:jwt-bearer");
        form.add("requested_token_use", "on_behalf_of");
        form.add("scope", scope);
        form.add("assertion", userAccessToken);

        Map<?, ?> resp = http.post()
                .uri(tokenEndpoint)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve().body(Map.class);

        return (String) resp.get("access_token");
    }
}
