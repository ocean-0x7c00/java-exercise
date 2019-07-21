package ocean.net.io.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO效率低（一个字节一个字节的读取或写入），
 * 并发差（每个连上来的客户端都要为它分配一个线程来处理数据）
 * <p>
 * throws IOException 写网络程序一定要处理异常，不能直接抛异常
 *
 * @author yancy
 * @date 2019/7/21
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //监听8888端口
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress("127.0.0.1", 8888));

        while (true) {
            //等待客户端的连接，accept是一个阻塞方法，程序会停在此处
            //连接上之后accept方法会在服务器和客户端之间建立一条通道
            Socket socket = ss.accept();

            //启动一个新的线程来处理数据
            //为什么要起新线程处理客户端的请求？
            //A:如果在accept之后立刻进行数据处理，其他的客户端就连不上了
            //每一个连接启动一个新的线程来处理
            new Thread(() -> {
                handle(socket);
            }).start();
        }
    }

    static void handle(Socket socket) {
        byte[] bytes = new byte[1024];
        try {
            //读取客户端的数据
            //read方法也是阻塞方法.若客服端的数据没有准备好，则发生阻塞
            int len = socket.getInputStream().read(bytes);
            System.out.println(new String(bytes, 0, len));

            //write方法也是阻塞方法
            socket.getOutputStream().write("Hello Client".getBytes());
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
