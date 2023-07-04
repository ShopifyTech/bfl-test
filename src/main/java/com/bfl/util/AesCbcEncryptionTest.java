package com.bfl.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.bfl.util.CommonUtility.convertStringToHex;

public class AesCbcEncryptionTest {
//vX8fLBdF6WIvZhAnMeHP2Q==
    public static void main(String[] args) throws Exception {
           String en="STNLzUwl00lp+Y4psc7UyYcXf+mhzGJPF03yPgG3KwGtgYD9qRmZbLH4+ZyHxDzQSDh5lekGkNyJMq0UjneqeODc76e4gD3N4yItDoDjW73soO8mlZ1x2iMbeKsDPXSA0dz/V18vWJqMKbIXQxZxpRYx4wQfUF2HXaN+RYEqXgeCK4JwXQ6utkEhFH3xNFzCST5Jx+8Fe3NQXCEAvURhhy7U9NzrN45etT4zoSucutbsVHWFr/9Bpsvgef+6WYpZyp21yJBf8CxgSmpAZfoIdw==";
        String encrypt1="P4xcEYbE7iSSc4Yo0zzfHC+gU9aQRzqnrqRJlTlQQWDSKFr\\/Fr+5otNGkODiPfIlEZIne1u3oW668iC6e7RX5N7BeGL4mga8Aqos\\/gE7BE4M0rQmdcx8mt1+Pa8CIvzCdObn32VNsJMr4ZGMXBWyw6961xgo9QAoetBEdD6x5NQMUYKVsg\\/alRf7kWXBLakuwmMO+j0GR6Be4\\/R0iSo0ReDbQgRHvPUdATA0LHZK1UQ=|4a3595aa8f8f9580974822cde1a11026";
        String encrypt="P4xcEYbE7iSSc4Yo0zzfHC+gU9aQRzqnrqRJlTlQQWDSKFr\\/Fr+5otNGkODiPfIlEZIne1u3oW668iC6e7RX5N7BeGL4mga8Aqos\\/gE7BE4M0rQmdcx8mt1+Pa8CIvzCdObn32VNsJMr4ZGMXBWyw6961xgo9QAoetBEdD6x5NQMUYKVsg\\/alRf7kWXBLakuwmMO+j0GR6Be4\\/R0iSo0ReDbQgRHvPUdATA0LHZK1UQ=";
         String sample="k1Tn3Ylya82DWPfFhnicsrNFryOor1hk6N0IkUATRoVoO6TSCf3qzoD+MAhC7f5m32ftELlpG7BZEw MLsoRHWPA27zV5io535cO7ri+cJ3KHOkmgvLNFrZD5hQcGaM1zDd2Z/Y0ccUKodaW7C3bZ1D6V 8iKnGwGtP0c574Bo8L3ulfOYurn9HQZ+o7Vt65Hz+Ue/9eLC+7WQJKm5SzE358u6K1MOcj7Ht4u4 mOn15tYsy4F5uxANu5iXLnGbHBnZGZKhq1sIj/pb7CGi4ezg5kMMU8gQhFi3T15KC28jUxRqFOzuTi B1aTrQ3fr/EOjHJKIk9vtYk3aqPahGrSxZrCRGF+YARJFUJGbOQhl3tyjqi0Pq1b0S0VPfMdcwFl0nQN2 86UriWryFVMaGlkDZ7g==";
        //DecryPt(sample);
        //encryptAndDecCrypt();
        decrypt1();
    }

    private static void encryptAndDecCrypt() throws Exception {
        //String plainText = "Hello, World!";
        String plainText =createCustomerRequest().toString();
        String key = "1OY67PYHXN210322121936X6KCG559YT";
        String iv = "1234567887654321";
        String ivx = stringToHex(iv);
        String keyx = stringToHex(key);
        System.out.println("ivx"+ivx);
        // Convert the key and IV to byte arrays
        byte[] keyBytes = hexStringToByteArray(keyx);
        byte[] ivBytes = hexStringToByteArray(ivx);

        // Create the AES cipher in CBC mode with PKCS5 padding
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Initialize the cipher with the key and IV
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        // Encrypt the plaintext
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // Encode the encrypted bytes in base64
        String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);
        String finalEncrpt = encryptedBase64 + key;
        String sealValue = getMD5Hash(finalEncrpt);
        System.out.println("sealValue: " + sealValue);
        System.out.println("Encrypted text (Base64): " + encryptedBase64);

        DecryPt(encryptedBase64);
        // System.out.println("Decrypted text (Base64): " + decypted);
    }

    public static String getMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static CustomerSearchRequest createCustomerRequest() {
        CustomerSearchRequest obj = CustomerSearchRequest.builder()
                .txnType("BILSRCH")
                .rrn("BILSdf4545001")
                .requestDateTime(20052021212121l)
                .dealerCode(687763)
                .dealerValidationKey(4499162128466537l)
                .cardNumber(2030400309579250l)   //This one we will get from post request
                .acqChannel(22)
                .build();
        System.out.println("request"+ obj.toString());
        return obj;
    }

    public static String stringToHex(String input) {
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            String hex = Integer.toHexString(ch);
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static void decrypt() throws Exception
    {
        // Provide the public key and IV
        String publicKey = "1OY67PYHXN210322121936X6KCG559YT";
        String iv = "1234567887654321";

        // Encrypted string to decrypt
        String encryptedString = "k1Tn3Ylya82DWPfFhnicsrNFryOor1hk6N0IkUATRoVoO6TSCf3qzoD+MAhC7f5m32ftELlpG7BZEwMLsoRHWPA27zV5io535cO7ri+cJ3KHOkmgvLNFrZD5hQcGaM1zDd2Z/Y0ccUKodaW7C3bZ1D6V8iKnGwGtP0c574Bo8L3ulfOYurn9HQZ+o7Vt65Hz+Ue/9eLC+7WQJKm5SzE358u6K1MOcj7Ht4u4mOn15tYsy4F5uxANu5iXLnGbHBnZGZKhq1sIj/pb7CGi4ezg5kMMU8gQhFi3T15KC28jUxRqFOzuTiB1aTrQ3fr/EOjHJKIk9vtYk3aqPahGrSxZrCRGF+YARJFUJGbOQhl3tyjqi0Pq1b0S0VPfMdcwFl0nQN286UriWryFVMaGlkDZ7g==";
      //  String encryptedString="P4xcEYbE7iSSc4Yo0zzfHC+gU9aQRzqnrqRJlTlQQWDSKFr\\/Fr+5otNGkODiPfIlEZIne1u3oW668iC6e7RX5N7BeGL4mga8Aqos\\/gE7BE4M0rQmdcx8mt1+Pa8CIvzCdObn32VNsJMr4ZGMXBWyw6961xgo9QAoetBEdD6x5NQMUYKVsg\\/alRf7kWXBLakuwmMO+j0GR6Be4\\/R0iSo0ReDbQgRHvPUdATA0LHZK1UQ=";
        // Remove whitespace and newline characters from the encrypted string
       // encryptedString = encryptedString.replaceAll("\\s+", "");
        // Base64 decode the encrypted string
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedString);

        // Create the SecretKeySpec using the public key and AES algorithm
        SecretKeySpec secretKeySpec = new SecretKeySpec(publicKey.getBytes(StandardCharsets.UTF_8), "AES");

        // Create the IvParameterSpec using the IV
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

        // Create the Cipher instance and initialize it for decryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        // Decrypt the encrypted bytes
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Convert the decrypted bytes to a string
        String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);

        // Print the decrypted string
        System.out.println(decryptedString);
    }

    public static String decrypt1() {
        String decryptText=null;
        try {
            String encryptedBase64 = "k1Tn3Ylya82DWPfFhnicsrNFryOor1hk6N0IkUATRoVoO6TSCf3qzoD+MAhC7f5m32ftELlpG7BZEwMLsoRHWPA27zV5io535cO7ri+cJ3KHOkmgvLNFrZD5hQcGaM1zDd2Z/Y0ccUKodaW7C3bZ1D6V8iKnGwGtP0c574Bo8L3ulfOYurn9HQZ+o7Vt65Hz+Ue/9eLC+7WQJKm5SzE358u6K1MOcj7Ht4u4mOn15tYsy4F5uxANu5iXLnGbHBnZGZKhq1sIj/pb7CGi4ezg5kMMU8gQhFi3T15KC28jUxRqFOzuTiB1aTrQ3fr/EOjHJKIk9vtYk3aqPahGrSxZrCRGF+YARJFUJGbOQhl3tyjqi0Pq1b0S0VPfMdcwFl0nQN286UriWryFVMaGlkDZ7g==";
            String key = "1OY67PYHXN210322121936X6KCG559YT";
            String iv = "1234567887654321";
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
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            // Decode the base64 encrypted text
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);
            // Decrypt the ciphertext
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            // Convert the decrypted bytes to a string
            decryptText = new String(decryptedBytes, StandardCharsets.UTF_8);
           System.out.println("decryptText "+decryptText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                 InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return decryptText;
    }
    public static void  DecryPt(String encryptedBase64) throws Exception
    {
        //String encryptedBase64 = "EncryptedTextHere";
        String key = "1OY67PYHXN210322121936X6KCG559YT";
        String iv = "1234567887654321";
        String ivx = stringToHex(iv);
        String keyx = stringToHex(key);

        // Convert the key and IV to byte arrays
        byte[] keyBytes = hexStringToByteArray(keyx);
        byte[] ivBytes = hexStringToByteArray(ivx);

        // Create the AES cipher in CBC mode with PKCS5 padding
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Initialize the cipher with the key and IV
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        // Decode the base64 encrypted text

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);

        // Decrypt the ciphertext
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Convert the decrypted bytes to a string
        String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);

        System.out.println("Decrypted text: " + decryptedText);
    }


    // Helper method to convert a hexadecimal string to a byte array
    private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] byteArray = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            byteArray[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return byteArray;
    }
}

