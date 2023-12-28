package pl.playwithme.smo.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.security.InvalidParameterException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class JwtTokenFacade {

    private static final long EXPIRATION_TIME_15_MIN = 900_000; // 15 minutes

    public static String getNewToken(String issuerId) {
        var newToken = JwtTokenFacade.generateJwt(issuerId);
        return "{\"token\":\"" + newToken + "\"}";
    }

    public static DecodedJWT validate(String token) {
        try {
            RsaKeyLoader loader = new RsaKeyLoader();
            RSAPublicKey publicKey = loader.loadPublicKey();

            Algorithm algorithm = Algorithm.RSA256(publicKey, null);
            JWTVerifier verifier = JWT.require(algorithm).build();

            final DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (Exception e) {
            throw new InvalidParameterException("JWT validation failed: " + e.getMessage());
        }
    }

    private static String generateJwt(String issuerId) {
        RsaKeyLoader loader = new RsaKeyLoader();
        RSAPrivateKey privateKey = loader.loadPrivateKey();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME_15_MIN);

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", "wartosc");

        Map<String, Object> header = new HashMap<>();
        header.put("type", "jwt");

        JWTCreator.Builder tokenBuilder = JWT.create()
                .withExpiresAt(expiration)
                .withIssuedAt(now);

        return tokenBuilder.sign(Algorithm.RSA256(null, privateKey));
    }

}
