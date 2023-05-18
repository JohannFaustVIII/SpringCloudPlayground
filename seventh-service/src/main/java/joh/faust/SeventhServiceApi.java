package joh.faust;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeventhServiceApi {

    @GetMapping("/")
    public String getHello(@AuthenticationPrincipal Jwt jwt) {
        return "Seventh Service: Hello World! Accessed by: " + jwt.getClaimAsString("preferred_username");
    }
}
