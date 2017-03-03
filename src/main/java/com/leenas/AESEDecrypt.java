package com.leenas;

/**
 * Created by leena on 2/2/17.
 */
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.udf.UDFType;

@Description(
        name = "aesdecrypt",
        value = "_FUNC_(str) - Returns unencrypted string based on AES key.",
        extended = "Example:\n" +
                "  > SELECT aesdecrypt(pii_info) FROM table_name;\n"
)
@UDFType(deterministic = true, stateful = false)
/*
 * A Hive decryption UDF
 */
public class AESEDecrypt extends UDF {
    public String evaluate(String encrypted) {
        String unencrypted = new String(encrypted);
        if(encrypted != null) {
            try {
                unencrypted = CipherUtils.decrypt(encrypted);
            } catch (Exception e) {};
        }
        return unencrypted;
    }
}