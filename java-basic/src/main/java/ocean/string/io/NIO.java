package ocean.string.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * 线程池的参数起什么作用
 * concurrentHaspMap
 * 分段锁
 * 读写锁
 * synchronized与前两种锁有什么差别
 * <p>
 * Java NIO的特点
 * 支持面向缓冲区，基于通道的操作,通道是双向的;不能直接读写通道
 *
 * @author ocean
 * @date 2019/8/15
 */
public class NIO {
    static String readFilePath = NIO.class.getClassLoader().getResource("readFile.txt").getPath();

    public static void main(String[] args) throws Exception {
//        readFileNIO();
        bufferStatusNIO();
    }

    public static void readFileNIO() throws IOException {
        FileInputStream stream = new FileInputStream(readFilePath);
        FileChannel fileChannel = stream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(3);
        int a = fileChannel.read(buffer);
        //3
        System.out.println("下一个可被读取的位置 " + buffer.position());

        //3
        System.out.println("可以被读到的数据位置 " + buffer.limit());


        //由读模式切换到写模式
        buffer.clear();
//        buffer.flip();


        buffer.putChar('a');
        fileChannel.write(buffer);

        //1
        System.out.println("下一个可被写入的位置 " + buffer.position());

        //0
        System.out.println("已写入数据位置 " + buffer.limit());

//        System.out.println(new String(buffer.array(), 0, a));

//        System.out.println("========");
//        int b = fileChannel.read(buffer);
//        System.out.println(new String(buffer.array(), 0, b));

    }

    public static void bufferStatusNIO() {
        IntBuffer buffer = IntBuffer.allocate(5);

        //先往一个buffer中写入几个数
        buffer.put(2);
        buffer.put(3);
        buffer.put(4);
        //position:3 limit:5
        System.out.println(String.format("此时的 position %s ;limit %s", buffer.position(), buffer.limit()));

        //读出之前写入的数，需要调用方法flip将position清零
        buffer.flip();
        //position:0 limit:3
        System.out.println(String.format("此时的 position %s ;limit %s", buffer.position(), buffer.limit()));

    }
}
