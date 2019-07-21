package ocean.net.io.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yancy
 * @date 2019/7/21
 */
public class PoolServerNio {
    ExecutorService service = Executors.newFixedThreadPool(50);
    private Selector selector;

    public static void main(String[] args) throws IOException {
        PoolServerNio server = new PoolServerNio();
        //服务器连接的建立
        server.initServer(8888);

        //处理连接操作
        server.listen();
    }

    private void initServer(int port) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.configureBlocking(false);

        ssc.bind(new InetSocketAddress("127.0.0.1", port));

        this.selector = Selector.open();

        ssc.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务器启动成功...");

    }

    private void listen() throws IOException {
        //轮询selector
        while (true) {
            //阻塞方法
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel socket = ssc.accept();
                    socket.configureBlocking(false);
                    socket.register(key.selector(), SelectionKey.OP_READ);

                } else if (key.isReadable()) {
                    //todo
                    key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));

                    service.submit(new ThreadHandlerChannel(key));
                }
            }
        }
    }


    private class ThreadHandlerChannel implements Runnable {
        private SelectionKey key;

        public ThreadHandlerChannel(SelectionKey key) {
            this.key = key;

        }

        /**
         * 完成读写操作
         */
        @Override
        public void run() {
            //获取socke
            SocketChannel sockets = (SocketChannel) key.channel();


            try {
                //读数据
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                ByteArrayOutputStream baso = new ByteArrayOutputStream();

                int size = 0;
                while ((size = sockets.read(byteBuffer)) > 0) {
                    byteBuffer.flip();
                    baso.write(byteBuffer.array(), 0, size);
                    byteBuffer.clear();
                }
                baso.close();
                //打印客服端的信息
                System.out.println(baso);


                //写数据
                byte[] content = baso.toByteArray();
                ByteBuffer write = ByteBuffer.allocate(content.length);
                write.put(content);
                write.flip();
                sockets.write(write);

                //
                if (size == -1) {
                    sockets.close();
                } else {
                    //todo
                    key.interestOps(key.interestOps() | SelectionKey.OP_READ);
                    key.selector().wakeup();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


        }
    }
}
