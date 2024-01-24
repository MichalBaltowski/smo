package pl.playwithme.quiz.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.security.InvalidParameterException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

public final class JwtTokenFacade {

    private static final long EXPIRATION_TIME_15_MIN = 900_000; // 15 minutes
    private static final String CLAIM_ISSUER_VALUE = "localhost:8080";
    private static final String CLAIM_SUBJECT_NAME = "sub";

    public static String getNewToken(String userId) {
        var newToken = JwtTokenFacade.generateJwt(userId);
        return "{\"token\":\"" + newToken + "\"}";
    }

    public static DecodedJWT validate(String token) {
        try {
            RsaKeyLoader loader = new RsaKeyLoader();
            RSAPublicKey publicKey = loader.loadPublicKey();

            Algorithm algorithm = Algorithm.RSA256(publicKey, null);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(CLAIM_ISSUER_VALUE)
                    .withClaimPresence(CLAIM_SUBJECT_NAME)
                    .build();

            final DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (Exception e) {
            throw new InvalidParameterException("JWT validation failed: " + e.getMessage());
        }
    }

    private static String generateJwt(String userId) {
        RsaKeyLoader loader = new RsaKeyLoader();
        RSAPrivateKey privateKey = loader.loadPrivateKey();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME_15_MIN);

        JWTCreator.Builder tokenBuilder = JWT.create()
                .withExpiresAt(expiration)
                .withIssuer(CLAIM_ISSUER_VALUE)
                .withSubject(userId)
                .withIssuedAt(now);

        return tokenBuilder.sign(Algorithm.RSA256(null, privateKey));
    }

}
