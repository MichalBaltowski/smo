package pl.playwithme.smo.Security;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class PrivateKeyLoader {

    private static final String FILE_PATH = "rsakey.pem";

    public PrivateKey load() {
        return loadPemRsaPrivateKey();
    }

    /**
     * Loads a file from the classpath and returns it as String
     * File generated by commad "openssl genpkey -algorithm RSA -out private_key.pem"
     *
     * @return
     * @throws IOException
     */
    private String readFile() throws IOException {
        var filePath = getClass()
                .getClassLoader()
                .getResource(FILE_PATH)
                .getFile();
        final File file = new File(filePath);

        return new String(Files.readAllBytes(file.toPath()));
    }

    /**
     * Loads the RSA private key from a PKCS#8 PEM file
     *
     * @return
     * @throws Exception
     */
    private PrivateKey loadPemRsaPrivateKey() {
        try {
            String pemString = readFile();

            String privateKeyPEM = pemString.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replaceAll(System.lineSeparator(), "")
                    .replace("-----END PRIVATE KEY-----", "");

            byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            return null;
        }
    }
}
