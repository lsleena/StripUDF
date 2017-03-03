import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


/**
 * Created by leena on 1/20/17.
 */
public class Decryption extends UDF{
    private Text result = new Text();

    public String evaluate(Text str) throws UnsupportedEncodingException, java.io.IOException {
        try{

            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();

            Cipher desCipher;

            // Create the cipher
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // Initialize the cipher for encryption

            //sensitive information
         //   byte[] text = "No body can see me".getBytes();

           // System.out.println("Text [Byte Format] : " + text);
            //System.out.println("Text : " + new String(text));

            // Encrypt the text
           byte[] textEncrypted =str.getBytes();


            // Initialize the same cipher for decryption
            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            // Decrypt the text
            byte[] textDecrypted = desCipher.doFinal(str.getBytes());
            result.set(textDecrypted);
            return String.valueOf(result);


//return textDecrypted.toString();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch(NoSuchPaddingException e){
            e.printStackTrace();
        }catch(InvalidKeyException e){
            e.printStackTrace();
        }catch(IllegalBlockSizeException e){
            e.printStackTrace();
        }catch(BadPaddingException e){
            e.printStackTrace();
        }

    return null;
    }


}
