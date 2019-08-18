package ocean.io;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * 学习使用StringRead
 * <p>
 * 可以使用字节数组、字符数组或字符串做物理节点
 *
 * @author yancy
 * @date 2019/8/18
 */
public class StringReaderTest {
    public static void main(String[] args) throws IOException {
        String src = "可以使用字节数组、字符数组或字符串做物理节点";

        StringReader reader = new StringReader(src);
        char[] buffer = new char[10];
        int hasRead = 0;
        while ((hasRead = reader.read(buffer)) > 0) {
            System.out.print(new String(buffer, 0, hasRead));
        }
        System.out.println();
        System.out.println("---------Writer--------------");
        StringWriter writer = new StringWriter();
        writer.write("学习使用\n");
        writer.write("StringRead");
        System.out.println(writer.toString());
    }
}
