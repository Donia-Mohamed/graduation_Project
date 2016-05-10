/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author dono
 */
public class DesEncrypter {

    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue
            = new byte[]{'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

    public String encrypt(String valueToEnc) {
        String encryptedValue = null;
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encValue = c.doFinal(valueToEnc.getBytes());
            encryptedValue = new BASE64Encoder().encode(encValue);
        } catch (Exception ex) {
            Logger.getLogger(DesEncrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encryptedValue;
    }

    public String decrypt(String encryptedValue) {
        String decryptedValue = null;
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedValue);
            byte[] decValue = c.doFinal(decordedValue);
            decryptedValue = new String(decValue);

        } catch (Exception ex) {
            Logger.getLogger(DesEncrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return decryptedValue;
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }
}
