import org.apache.hadoop.io.Text;
import antlr.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
import org.mortbay.util.StringUtil;
/**
 * Created by leena on 1/31/17.
 */
public class Remove_String extends UDF {
    private Text result = new Text();

    public Text evaluate(Text str) {
        if (str == null) {
            return null;

        }
        int x =0;
        x = str.find("_str");

        result.set(str.toString().substring(0,x));
        System.out.print(result);

        return result;

    }

}
