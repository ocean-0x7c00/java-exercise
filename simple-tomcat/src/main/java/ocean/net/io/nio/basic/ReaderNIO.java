package ocean.net.io.nio.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 *
 * @author yancy
 * @date 2019/7/21
 */
public class ReaderNIO {
    static String path = "/Users/yancy/IdeaProjects/java-exercise/simple-tomcat/src/main/java/ocean/net/io/nio/basic/Java NIO.md";

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //最后，在从输入通道读入缓冲区之前，我们调用 clear() 方法。
        //同样，在将缓冲区写入输出通道之前，我们调用 flip() 方法
        while (true) {
            //clear() 方法重设缓冲区，使它可以接受读入的数据。
            byteBuffer.clear();


            int len = channel.read(byteBuffer);
            if (len == -1) {
                break;
            }

            //flip() 方法让缓冲区可以将新读入的数据写入另一个通道。
            byteBuffer.flip();


        }
        System.out.println();
        channel.close();
        fileInputStream.close();
    }
}
