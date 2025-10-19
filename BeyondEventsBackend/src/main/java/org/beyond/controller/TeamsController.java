package org.beyond.controller;

import lombok.extern.slf4j.Slf4j;
import org.beyond.dto.UserDto;
import org.beyond.service.MsTeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class TeamsController {

    @Autowired
    MsTeamsService teamsService;

    @GetMapping("/me")
    public UserDto me(@AuthenticationPrincipal Jwt jwt) {
        log.info("Login commenced");
        String oid = jwt.getClaimAsString("oid");
        String email = jwt.getClaimAsString("preferred_username");
        String name = jwt.getClaimAsString("name");

        return teamsService.teamsLogin(oid, email, name);
    }

    @GetMapping(value = "/me/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> mePhoto(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(teamsService.getTeamsProfileIcon(jwt));
    }
}
