import org.apache.hadoop.hive.ql.exec.UDF;

import java.io.UnsupportedEncodingException;
import java.lang.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.apache.hadoop.io.Text;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Created by leena on 1/19/17.
 */
public class Encode extends UDF {
    private static Cipher ecipher;
    private static Cipher dcipher;
    private static SecretKey key;
    private Text result = new Text();

    public String evaluate(Text str) throws UnsupportedEncodingException, java.io.IOException {


        try {
            // generate secret key using DES algorithm
            key = KeyGenerator.getInstance("DES").generateKey();

            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");

            // initialize the ciphers with the given key

            ecipher.init(Cipher.ENCRYPT_MODE, key);

            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes();

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);
         String strig = new BASE64Encoder().encode(enc) ;
result.set(strig);
            return String.valueOf(result);
            // Encode bytes to base64 to get a string
           // return new BASE64Encoder().encode(enc);


        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

}
