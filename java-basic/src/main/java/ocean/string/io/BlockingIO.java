package ocean.string.io;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;

/**
 * IO要解决的问题：
 * 1.怎么从磁盘上读取数据，怎么往磁盘上写入数据
 * <p>
 * Java IO
 * 特点：以流的形式对数据进行读写，流是单向的
 * 面向流，基于字节的操作，可以直接读写流
 * 分类：
 * 依据数据类型：字节流(inputStream和outputStream)和字符流(read和write)
 * 依据流的方向：输入流和输出流
 * <p>
 * <p>
 * 额外补充
 * 参考链接
 * https://www.cnblogs.com/xrq730/category/743509.html
 * https://blog.csdn.net/duguxiaobiao/article/details/79069593
 * 不同的编码格式所占的字节数是不同的
 * UTF-8编码下一个中文所占字节也是不确定的，可能是2个、3个、4个字节；
 * <p>
 * Question:
 * 跳过第一个员工的信息，其姓名8字节，年龄4字节
 * "zhangsan"共有8个字节,"lisi"有4个字节,"wangwu"有6个字节,为什么加上int的字节数后
 * 都要跳过12个字节？
 * Answer:字节对齐
 *
 * @author ocean
 * @date 2019/8/15
 */
public class BlockingIO {
    static String readFilePath = BlockingIO.class.getClassLoader().getResource("readFile.txt").getPath();
    static String writeFilePath = BlockingIO.class.getClassLoader().getResource("writeFile.txt").getPath();
    static String path = "/Users/ocean/ioFile.txt";
    static String bufferPath = "/Users/ocean/buffFile.txt";
    static String employeePath = "/Users/ocean/employee.txt";

    public static void main(String[] args) throws Exception {
        randomAccessFile();
//        readWriteInByteData();
//        readWriteInCharData();
//        differentBetweenByteStreamAndCharStream();
//        transformByteStreamAndCharStream();
//        bufferReaderAndWriter();
//        piped();
    }

    /**
     * Java语言中功能最为丰富的文件访问类,支持"随机访问"方式，可以跳转到文件的任意位置处读写数据
     * 要访问一个文件的时候，不想把文件从头读到尾，而是希望像访问一个数据库一样地访问一个文本文件，
     * 使用RandomAccessFile类是最佳选择
     * 但该类仅限于操作文件，不能访问其他得IO设备，如网络、内存映像等
     */
    public static void randomAccessFile() throws IOException {
        Employee e1 = new Employee("zhangsan", 23);
        Employee e2 = new Employee("lisi", 24);
        Employee e3 = new Employee("wangwu", 25);


        //往文件中写入数据
//        RandomAccessFile accessFile = new RandomAccessFile(employeePath, "rw");
//        accessFile.writeBytes(e1.getName());
//        accessFile.writeInt(e1.getAge());
//        accessFile.writeBytes(e2.getName());
//        accessFile.writeInt(e2.getAge());
//        accessFile.writeBytes(e3.getName());
//        accessFile.writeInt(e3.getAge());
//        accessFile.close();

        RandomAccessFile readFile = new RandomAccessFile(employeePath, "r");
        // 跳过第一个员工的信息，其姓名8字节，年龄4字节
        //"zhangsan"共有8个字节,"lisi"有4个字节,"wangwu"有6个字节,为什么加上int的字节数后
        // 都要跳过12个字节？
        //字节对齐
        readFile.skipBytes(12);
        System.out.println("第二个员工的信息：");
        String str = "";
        int len = 8;
        for (int i = 0; i < len; i++) {
            str = str + (char) readFile.readByte();
        }
        System.out.println("name：" + str);
        System.out.println("age：" + readFile.readInt());

        System.out.println("===========");
        System.out.println("第一个员工的信息：");

        //移动文件指针
        readFile.seek(0);
        str = "";
        for (int i = 0; i < len; i++) {
            str = str + (char) readFile.readByte();
        }
        System.out.println("name：" + str);
        System.out.println("age：" + readFile.readInt());

        System.out.println("===========");
        System.out.println("第三个员工的信息：");
        //跳过第二个员工的信息
        readFile.skipBytes(12);

        str = "";
        for (int i = 0; i < len; i++) {
            str = str + (char) readFile.readByte();
        }
        System.out.println("name：" + str.trim());
        System.out.println("age：" + readFile.readInt());
        readFile.close();
    }

    /**
     * 使用FileInputStream和FileOutputStream建立一个流
     * read和write从流中读一定数量的字节
     */
    public static void readWriteInByteData() {
        try (FileInputStream inputStream = new FileInputStream(readFilePath);
             FileOutputStream stream = new FileOutputStream(writeFilePath)) {

            //每次只读一个字节，read会将一个字节从流中移出，
//            int i = inputStream.read();
//            int num = inputStream.read();
//            int end = inputStream.read();
//            System.out.println(i);
//            System.out.println(num);
//            System.out.println(end);

            String a = "a";
            byte[] aBytes = a.getBytes();

            stream.write(97 - 32);
            stream.flush();

            System.out.println("文件中的字节数 " + inputStream.available());
            int result;
            while ((result = inputStream.read()) != -1) {
                System.out.println(result);
//                outputStream.write(result);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用FileRead和FileWrite进行读写
     * 使用read和write读取一定数量的字符
     */
    public static void readWriteInCharData() {
        try (FileReader reader = new FileReader(readFilePath);
             FileWriter writer = new FileWriter(writeFilePath)) {
            //读取单个字符
            char a = (char) reader.read();
            System.out.println(a);

            //读取多个字符
            char[] chars = new char[3];
            int charLength = reader.read(chars);
            System.out.println(new String(chars, 0, chars.length));
            for (int i = 0; i < chars.length; i++) {
                System.out.println(chars[i]);
            }

            //写单个字符
            writer.write("m");
            writer.flush();
            //写多个字符
            writer.write("abcdefgh");
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节流和字符流的区别
     * 字节流本身不使用缓冲区，直接操作文件
     * 字符流使用缓冲区，通过缓冲区操作文件
     * <p>
     * 如果去掉 out.close();，文件内容会读不到"文件中无数据"
     * 原因：在关闭字符流的时候会强制将缓冲区的内容输出
     */
    public static void differentBetweenByteStreamAndCharStream() throws IOException {
        File file = new File(path);
        Writer out = new FileWriter(file);
        // 声明一个String类型对象
        String str = "Hello World!!!";
        out.write(str);
        //flush方法将缓冲区中的内容输出
//        out.flush();
        out.close();

        // 读文件操作
        Reader in = new FileReader(file);
        // 开辟一个空间用于接收文件读进来的数据
        char c0[] = new char[1024];
        int i = 0;
        // 将c0的引用传递到read()方法之中，同时此方法返回读入数据的个数
        i = in.read(c0);
        in.close();

        if (-1 == i) {
            System.out.println("文件中无数据");
        } else {
            System.out.println(new String(c0, 0, i));
        }
    }

    /**
     * 字节流和字符流之间如何转换
     * 通过InputStreamReader和OutputStreamWriter
     */
    public static void transformByteStreamAndCharStream() {
        try (FileInputStream inputStream = new FileInputStream(path);) {
            int capicity = 20;
//            byte[] bytes = new byte[capicity];
//            int len = inputStream.read(bytes);
            //System.out.println(len);
            //字节流转字符流
            InputStreamReader reader = new InputStreamReader(inputStream);
            char[] chars = new char[capicity];
            int charLen = reader.read(chars);
            System.out.println(new String(chars, 0, charLen));
            //字符流转字节流
            String msg = "this is byte arrays";
            byte[] bytes = msg.getBytes();
            OutputStream outputStream = new FileOutputStream(path);
            OutputStreamWriter stream = new OutputStreamWriter(outputStream);
            stream.write(msg);
            stream.flush();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 为了达到最高的效率，避免频繁地进行字符与字节之间的相互转换，
     * 最好不要直接使用FileReader和FileWriter这两个类进行读写，
     * 而使用BufferedWriter包装OutputStreamWriter，
     * 使用BufferedReader包装InputStreamReader
     */
    public static void bufferReaderAndWriter() {
        File file = new File(bufferPath);
        try (Writer writer = new FileWriter(file);
             BufferedWriter buffer = new BufferedWriter(writer);
             Reader reader = new FileReader(bufferPath);
             BufferedReader bufferReader = new BufferedReader(reader)) {

//            writer.write("FilerWriter");
//            writer.write("1234\n");
//            writer.write("2345\n");
//            writer.write("3456\n");
//            writer.write("\n");
//            writer.write("4567\n");
//            writer.write("8910\n");
//            writer.flush();


            buffer.write("BufferedWriter\n");
            buffer.write("1234\n");
            buffer.write("2345\n");
            buffer.write("3456\n");
            buffer.write("\n");
            buffer.write("4567\n");
            buffer.write("8910\n");
            buffer.flush();

            char[] chars = new char[41];

            //使用BufferReader读取
//            bufferReader.read(chars);
            //使用FileReader读取
//            reader.read(chars);
            System.out.println(new String(chars, 0, chars.length));
            System.out.println();
            System.out.println(bufferReader.readLine());
            System.out.println("=============");
            String msg;
            while ((msg = bufferReader.readLine()) != null) {
                System.out.println(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管道流
     * 主要用于连接两个线程的通信
     * 管道流也分为字节流（PipedInputStream、PipedOutputStream）和字符流（PipedReader、PipedWriter）
     * 比如一个PipedInputStream必须和一个PipedOutputStream对象进行连接而产生一个通信管道，
     * PipedOutputStream向管道中写入数据，PipedInputStream从管道中读取数据
     */
    public static void piped() throws IOException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver();
        Thread senderThread = new Thread(sender);
        Thread receiverThread = new Thread(receiver);

        // 写入
        PipedOutputStream out = sender.getPipedOutput();
        // 读出
        PipedInputStream in = receiver.getPipedOutput();
        //将输出发送到输入
        out.connect(in);

        senderThread.start();
        receiverThread.start();
    }

    /**
     * 对象流
     * 用于对象的序列化
     */
    public static void objectedStream() {

    }

}

/**
 * 写线程
 */
class Sender implements Runnable {
    private PipedOutputStream pipedOutput = new PipedOutputStream();

    public PipedOutputStream getPipedOutput() {
        return pipedOutput;
    }

    @Override
    public void run() {
        String msg = "Receiver 你好!";
        try {
            pipedOutput.write(msg.getBytes());
            pipedOutput.flush();
            pipedOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

/**
 * 读线程
 */
class Receiver implements Runnable {
    private PipedInputStream pipedInput = new PipedInputStream();

    public PipedInputStream getPipedOutput() {
        return pipedInput;
    }

    @Override
    public void run() {
        byte[] bytes = new byte[1024];
        try {
            int len = pipedInput.read(bytes);
            if (len != -1) {
                System.out.println("收到的信息为 " + new String(bytes, 0, len));
            }
            pipedInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


/**
 * 定义实体类
 */
@Setter
@Getter
@NoArgsConstructor
class Employee {
    private String name;
    /**
     * 四个字节
     */
    private int age;
    private final static int LEN = 8;

    public Employee(String name, int age) {
        if (name.length() > LEN) {
            name = name.substring(0, 8);
        } else {
            while (name.length() < LEN) {
                name = name + "\u0000";
            }
        }
        this.name = name;
        this.age = age;
    }
}