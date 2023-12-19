package pl.playwithme.smo.Security;

import java.security.PrivateKey;

public class JwtTokenManager {

    private static final long EXPIRATION_TIME = 900_000; // 15 minutes

    public static void generateToken() throws Exception {
        PrivateKeyLoader loader = new PrivateKeyLoader();
        PrivateKey key = loader.load();
        System.out.println(key.toString());
    }

}
