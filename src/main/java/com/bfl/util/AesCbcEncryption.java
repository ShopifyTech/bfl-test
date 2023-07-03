package com.bfl.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class AesCbcEncryption {
//vX8fLBdF6WIvZhAnMeHP2Q==
    public static void main(String[] args) throws Exception {
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

