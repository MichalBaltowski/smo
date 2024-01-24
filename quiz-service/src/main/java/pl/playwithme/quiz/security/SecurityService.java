package pl.playwithme.quiz.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private final int HEADER_LENGHT = 7;

    public DecodedJWT validateJwt(String authorizationHeader) {
        return JwtTokenFacade.validate(getToken(authorizationHeader));
    }

    private String getToken(String authorizationHeader) {
        return authorizationHeader.substring(HEADER_LENGHT);
    }

}
