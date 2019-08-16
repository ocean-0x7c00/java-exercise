package ocean.string.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
    public static void main(String[] args) throws Exception {
//        readFileNIO();
//        bufferStatusNIO();
//        directBuffer();
//        channel();
//        useDirectMemoryToCopyFile();
    }


}

/**
 * 文件IO
 */
class FileNIO {
    static String readFilePath = NIO.class.getClassLoader().getResource("readFile.txt").getPath();
    static String path = "/Users/ocean/ioFile.txt";
    static String path2 = "/Users/ocean/ioFile2.txt";
    static String path3 = "/Users/ocean/ioFile3.txt";
    //    static String sourceFilePath = "/Users/ocean/install-x86-minimal-20190712T214502Z.iso";
//    static String targetFilePath = "/Users/ocean/targetFile.iso";
    static String sourceFilePath = "/Users/ocean/ioFile.txt";
    static String targetFilePath = "/Users/ocean/result1/ioFileCopy.txt";

    public FileNIO() throws IOException {
    }

    public static void main(String[] args) throws Exception {
//        readFileNIO();
//        bufferStatusNIO();
//        directBuffer();
//        channel();
        useDirectMemoryToCopyFile();
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

    /**
     * 一、缓冲区（Buffer）：在 Java NIO 中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
     * <p>
     * 根据数据类型不同（boolean 除外），提供了相应类型的缓冲区：
     * ByteBuffer
     * CharBuffer
     * ShortBuffer
     * IntBuffer
     * LongBuffer
     * FloatBuffer
     * DoubleBuffer
     * <p>
     * 上述缓冲区的管理方式几乎一致，通过 allocate() 获取缓冲区
     * <p>
     * 二、缓冲区存取数据的两个核心方法：
     * put() : 存入数据到缓冲区中
     * get() : 获取缓冲区中的数据
     * <p>
     * 三、缓冲区中的四个核心属性：
     * capacity : 容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
     * limit : 界限，表示缓冲区中可以操作数据的大小。（limit 后数据不能进行读写）
     * position : 位置，表示缓冲区中正在操作数据的位置。
     * <p>
     * mark : 标记，表示记录当前 position 的位置。可以通过 reset() 恢复到 mark 的位置
     * <p>
     * 0 <= mark <= position <= limit <= capacity
     * <p>
     * 四、直接缓冲区与非直接缓冲区：
     * 非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中
     * 直接缓冲区：通过 allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
     */
    public static void bufferStatusNIO() {

        //创建一个buffer只有position:0 limit=capacity
        //写时：position记录下一个可写入数据的位置，limit和capacity相等.position:0,limit=capacity=5
        IntBuffer buffer = IntBuffer.allocate(5);
        System.out.println(String.format("此时的[init] position %s ;limit %s", buffer.position(), buffer.limit()));

        //先往一个buffer中写入几个数
        buffer.put(2);
        buffer.put(3);
        buffer.put(4);
        //position:3 limit:5
        System.out.println(String.format("此时的[write] position %s ;limit %s", buffer.position(), buffer.limit()));

        //读出之前写入的数，需要调用方法flip将position清零
        //读时：position置为0，limit记录buffer中存放的元素个数
        buffer.flip();
        //position:0 limit:3
        System.out.println(String.format("此时的[flip] position %s ;limit %s", buffer.position(), buffer.limit()));

        //开始读数据,读完数据 position:3 limit:3
        int[] result = new int[2];
        buffer.get(result);
        System.out.println(String.format("此时的[read] position %s ;limit %s", buffer.position(), buffer.limit()));
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + "\t");
        }
        System.out.println();
        //调用clear方法，但buffer中的数据还存在，只是将position置为0，limit=capacity，以便写入数据
        buffer.clear();
        System.out.println("剩余" + buffer.remaining());
        System.out.println(buffer.get(2));
        System.out.println(String.format("此时的[clear] position %s ;limit %s", buffer.position(), buffer.limit()));

        //调用compact方法，将数据移到序号为0的位置,将position置为0，limit=capacity，以便写入数据
//        buffer.compact();
//        System.out.println(String.format("此时的[compact] position %s ;limit %s", buffer.position(), buffer.limit()));

    }

    /**
     * 直接缓冲区
     * 只有ByteBuffer可以分配直接内存
     */
    public static void directBuffer() {
        //分配直接内存
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer.isDirect());
    }

    /**
     * Channel表示与IO的连接，类似流，但是channel不能直接访问数据，只能通过buffer访问数据
     * 1.Channel的主要实现类
     * •FileChannel：用于读取、写入、映射和操作文件的通道。
     * •DatagramChannel：通过 UDP 读写网络中的数据通道。
     * •SocketChannel：通过 TCP 读写网络中的数据。
     * •ServerSocketChannel：可以监听新进来的 TCP 连接，对每一个新进来的连接都会创建一个 SocketChannel。
     * <p>
     * 2.如何获取channel
     * 调用支持通道对象的getChannel方法或通过Files的静态方法newByteChannel()或通过通道的静态方法open(),打开指定通道
     * 支持通道的类有
     * FileInputStream
     * FileOutputStream
     * RandomAccessFile
     * DatagramSocket
     * Socket
     * ServerSocket
     * <p>
     * 3.读写Buffer的数据
     * 通过read和write方法
     * <p>
     * 4.分散和聚集
     * 分散读取：将channel中的数据依次写入到多个不同buffer中
     * 聚集写入：将多个不同的buffer中的数据依次写入channel中
     * <p>
     * 5.通道之间的数据传输
     * NIO提供了两个方法transferFrom和transferTo
     * 有两个通道A和B，如果想把A通道的数据转给B通道，有如下两种方法
     * (1) B.transferFrom(A,position,length)
     * (2) A.transferTo(B,position,length)
     * <p>
     * 一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
     * <p>
     * 二、通道的主要实现类
     * java.nio.channels.Channel 接口：
     * |--FileChannel
     * |--SocketChannel
     * |--ServerSocketChannel
     * |--DatagramChannel
     * <p>
     * 三、获取通道
     * 1. Java 针对支持通道的类提供了 getChannel() 方法
     * 本地 IO：
     * FileInputStream/FileOutputStream
     * RandomAccessFile
     * <p>
     * 网络IO：
     * Socket
     * ServerSocket
     * DatagramSocket
     * <p>
     * 2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
     * 3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
     * <p>
     * 四、通道之间的数据传输
     * transferFrom()
     * transferTo()
     * <p>
     * 五、分散(Scatter)与聚集(Gather)
     * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
     * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
     * <p>
     * 六、字符集：Charset
     * 编码：字符串 -> 字节数组
     * 解码：字节数组  -> 字符串
     */
    public static void channel() throws Exception {

        try (RandomAccessFile file = new RandomAccessFile(path, "rw");
             RandomAccessFile result = new RandomAccessFile(path2, "rw");
             RandomAccessFile target = new RandomAccessFile(path3, "rw")) {

            FileChannel channel = file.getChannel();

            ByteBuffer b1 = ByteBuffer.allocate(12);
            ByteBuffer b2 = ByteBuffer.allocate(4);
            ByteBuffer b3 = ByteBuffer.allocate(12);

            //分散
            ByteBuffer[] byteBuffers = {b1, b2, b3};
            channel.read(byteBuffers);
            for (int i = 0; i < byteBuffers.length; i++) {
                byteBuffers[i].flip();
            }

            //读取buffer中的值
            for (int i = 0; i < byteBuffers.length; i++) {
                System.out.print(new String(byteBuffers[i].array(), 0, byteBuffers[i].limit()));
            }

            FileChannel channel1 = result.getChannel();
//            channel1.write(byteBuffers);

            FileChannel channel2 = target.getChannel();
            channel1.transferTo(0, channel1.size(), channel1);
        }
    }

    /**
     * 利用通道完成文件的复制（非直接缓冲区）
     *
     * @throws IOException
     */
    public static void useDirectMemoryToCopyFile() throws IOException {

        try (RandomAccessFile sourceFile = new RandomAccessFile(sourceFilePath, "rw");
             RandomAccessFile targetFile = new RandomAccessFile(targetFilePath, "rw")) {

            FileChannel srcChannel = sourceFile.getChannel();
            FileChannel fileChannel = targetFile.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            System.out.println("start.....");
            long start = System.currentTimeMillis();
            //buffer中没数据是返回-1
            while ((srcChannel.read(buffer)) != -1) {
                buffer.flip();
                System.out.println(new String(buffer.array(), 0, 1));
                fileChannel.write(buffer);
                buffer.clear();
            }
            long end = System.currentTimeMillis();
            System.out.println("end.....");
            System.out.println(String.format("time %s", end - start));


            //使用直接内存复制

        }
    }


    /**
     * 使用直接缓冲区完成文件的复制(内存映射文件)
     *
     * @throws IOException
     */
    public static void method() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get("d:/1.mkv"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("d:/2.mkv"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);
        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));
    }
}

/**
 * 网络客户端
 */
class Client {
    public void client() throws Exception {
        String path = "/Users/ocean/ioFile.txt";
        //1. 获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        FileChannel inChannel = new RandomAccessFile(path, "r").getChannel();

        //2. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //3. 读取本地文件，并发送到服务端
        while (inChannel.read(buf) != -1) {
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }

        //4. 关闭通道
        inChannel.close();
        sChannel.close();
    }

    public static void main(String[] args) throws Exception {
        new Client().client();
    }
}

/**
 * 网络IO
 * <p>
 * <p>
 * 一、使用 NIO 完成网络通信的三个核心：
 * <p>
 * 1. 通道（Channel）：负责连接
 * <p>
 * java.nio.channels.Channel 接口：
 * |--SelectableChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * <p>
 * |--Pipe.SinkChannel
 * |--Pipe.SourceChannel
 * <p>
 * 2. 缓冲区（Buffer）：负责数据的存取
 * <p>
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 */
class Server {
    static String path = "/Users/ocean/ioFileResult.txt";

    public static void main(String[] args) throws Exception {
        new Server().server();
    }

    public void server() throws Exception {
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        FileChannel outChannel = file.getChannel();

        //2. 绑定连接
        ssChannel.bind(new InetSocketAddress(9898));

        //3. 获取客户端连接的通道
        SocketChannel sChannel = ssChannel.accept();

        //4. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //5. 接收客户端的数据，并保存到本地
        while (sChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }
        //6. 关闭通道
        sChannel.close();
        outChannel.close();
        ssChannel.close();
    }
}