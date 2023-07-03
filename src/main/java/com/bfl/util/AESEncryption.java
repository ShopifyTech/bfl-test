package com.bfl.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static com.bfl.util.CommonUtility.convertStringToHex;
import static com.bfl.util.CommonUtility.hexStringToByteArray;

@Component
@Slf4j

public class AESEncryption {

    @Value("${bfl.iv}")
    private String iv;

    @Value("${bfl.key}")
    private String key;

    public String encrypt(String inputStr) {
        String encryptedBase64=null;
        try {
            String ivHex = convertStringToHex(iv);
            String keyHex = convertStringToHex(key);
            // Convert the key and IV to byte arrays
            byte[] keyBytes = hexStringToByteArray(keyHex);
            byte[] ivBytes = hexStringToByteArray(ivHex);
            // Create the AES cipher in CBC mode with PKCS5 padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // Initialize the cipher with the key and IV
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            // Encrypt the plaintext
            byte[] encryptedBytes = cipher.doFinal(inputStr.getBytes(StandardCharsets.UTF_8));
            // Encode the encrypted bytes in base64
             encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
        return encryptedBase64;
    }

}
