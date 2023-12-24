package pl.playwithme.smo.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtGenerator {

    private static final long EXPIRATION_TIME = 900_000; // 15 minutes

    public static String getNewToken(String issuerId) {
        var newToken = JwtGenerator.generateJwtToken(issuerId);
        return "{\"token\":\""+newToken+"\"}";
    }
    private static String generateJwtToken(String issuerId) {
        PrivateKeyLoader loader = new PrivateKeyLoader();
        PrivateKey key = loader.load();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", "wartosc");

        var result = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .setIssuer(issuerId)
                .signWith(key, SignatureAlgorithm.RS256)
                .setHeaderParam("type", "jwt")
                .compact();
        return result;
    }

}
