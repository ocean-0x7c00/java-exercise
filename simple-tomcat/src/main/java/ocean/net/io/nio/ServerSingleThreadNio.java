package ocean.net.io.nio;


import java.io.IOException;
import java.net.InetSocketAddress;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 同步-异步-阻塞-非阻塞
 * 同步、异步关注的是消息的通信机制
 * 阻塞和非阻塞关注的是等待时消息时的状态
 *
 * @author yancy
 * @date 2019/7/21
 */
public class ServerSingleThreadNio {
    public static void main(String[] args) throws IOException {
        //ServerSocketChannel是NIO对ServerSocket的封装
        //ServerSocket只能获取getInputStream和getOutputStream
        //来进行读写。ServerSocketChannel可以直接进行读写

        //1. 监听8888端口
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8888));

        //2. 使用非阻塞模型
        ssc.configureBlocking(false);

        //3. 创建选择器
        Selector selector = Selector.open();

        //4. 在ServerSocketChannel上注册要监听的事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        //5. 轮询
        while (true) {
            //阻塞方法，等待客户端的连接
            selector.select();

            //如果客户端有客户端连接,取出selector的keys
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //循环遍历，处理每个key
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                handle(key);
            }
        }
    }

    private static void handle(SelectionKey key) {
        if (key.isAcceptable()) {
            try {
                //获取ServerSocketChannel
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                //与客户端建立连接
                SocketChannel socketChannel = ssc.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(key.selector(), SelectionKey.OP_READ);
            } catch (IOException e) {
                System.out.println(e.toString());
            } finally {

            }

        } else if (key.isReadable()) {
            //获取socket
            SocketChannel socketChannel = null;
            try {
                socketChannel = (SocketChannel) key.channel();
                //读客户端的数据
                ByteBuffer buffer = ByteBuffer.allocate(512);
                buffer.clear();
                int len = socketChannel.read(buffer);

                if (len != -1) {
                    System.out.println(new String(buffer.array(), 0, len));
                }

                //往客户端写数据
                ByteBuffer writeBuffer = ByteBuffer.wrap("HelloClient".getBytes());
                socketChannel.write(writeBuffer);
            } catch (IOException e) {
                System.out.println(e.toString());
            } finally {
                if (socketChannel != null) {
                    try {
                        //不关闭socket，会报java.io.IOException: Broken pipe
                        socketChannel.close();
                    } catch (IOException e) {
                        System.out.println(e.toString());
                    }
                }
            }
        }
    }
}
