package ocean.net.io.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @author yancy
 * @date 2019/7/21
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        //直接连到localhost的8888端口
        Socket socket = new Socket("127.0.0.1", 8888);

        socket.getOutputStream().write("HelloServer from client".getBytes());
        socket.getOutputStream().flush();
        System.out.println("write over,waiting for message back..");

        byte[] bytes = new byte[1024];
        int len = socket.getInputStream().read(bytes);
        System.out.println(new String(bytes, 0, len));

//        while (true) {
//            Thread.sleep(1000);
//        }
        socket.close();
    }
}
