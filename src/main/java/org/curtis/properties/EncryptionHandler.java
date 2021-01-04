package org.curtis.properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.properties.EncryptableProperties;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Random;

public class EncryptionHandler {
    private static EncryptionHandler instance;
    private static StandardPBEStringEncryptor encryptor;
    private static String ENCRYPTOR_KEY = null;
    private static final String DEFAULT_KEY = "musicxml";

    private EncryptionHandler() {
    }

    public static synchronized EncryptionHandler getInstance() {
        if (instance == null) {
            instance = new EncryptionHandler();
            instance.initializeEncryptor();
        }

        return instance;
    }

    private synchronized void initializeEncryptor() {
        encryptor = new StandardPBEStringEncryptor();
        setEncryptorKey();
        encryptor.setPassword(ENCRYPTOR_KEY);
        encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        encryptor.setIvGenerator(new RandomIvGenerator());
    }

    private synchronized void setEncryptorKey() {
        // get the file
        File keyFile = new File(PropertiesHandler.KEY_FILENAME);
        try {
            if (keyFile.exists()) {
                ENCRYPTOR_KEY = Files.readString(Path.of(PropertiesHandler.KEY_FILENAME));
            } else {
                byte[] array = new byte[10];
                new Random().nextBytes(array);
                ENCRYPTOR_KEY = getKeyValue();
                Files.writeString(Path.of(PropertiesHandler.KEY_FILENAME), ENCRYPTOR_KEY);
            }
        } catch (IOException e) {
            ENCRYPTOR_KEY = DEFAULT_KEY;
            System.err.println("ERROR: Key file read/write error: " + e.getMessage());
        }
    }

    private synchronized String getKeyValue() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    protected Properties getProperties(InputStream inputStream) throws IOException {
        Properties properties = new EncryptableProperties(encryptor);
        properties.load(inputStream);

        return properties;
    }

    public String getEncryptedValue(String value) {
        return "ENC(" + encryptor.encrypt(value) + ")";
    }
}
