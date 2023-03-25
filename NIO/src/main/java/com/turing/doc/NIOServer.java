package com.turing.doc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * TODO Java NIO Server
 *
 * @Description
 * @Author ll.lv
 * @Date 2023/3/18 17:21
 **/
public class NIOServer {
    private int size =1024;
    private ServerSocketChannel serverSocketChannel;
    private ByteBuffer byteBuffer;
    private Selector selector;
    private int remoteClientNum = 0;

    public NIOServer(int port){
        try {
            // 在构造函数中初始化Channel监听
            initChannel(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    // Channel 初始化
    public void initChannel(int port) throws IOException {
        // 打开Channel
        serverSocketChannel = ServerSocketChannel.open();
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 绑定端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.println("Listener on port :" + port);
        // 创建选择器
        selector = Selector.open();
        // 向选择器注册通道
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 分配缓存区大小
        byteBuffer = ByteBuffer.allocate(size);

    }


    // 监听器，用于监听Channel上的数据变化
    private void listener() throws IOException {
        while (true){
            // 返回的int值表示总共有多少个channel处于就绪状态
            int n = selector.select();
            if (n == 0) {
                continue;
            }

            // 每个selector对应多个selectionKey，每个selectionKey对应一个channel
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 如果selectionKey处于就绪状态，则开衫接收客户端的连接
                if (key.isAcceptable()){
                    // 获取channel
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    // channel接收链接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // channel注册
                    registerChannel(selector,socketChannel, SelectionKey.OP_READ);
                    // 远程客户端的连接数
                    remoteClientNum ++;
                    System.out.println("Online Client Num :" +remoteClientNum);
                    write (socketChannel,"Hello, Client".getBytes());
                }
                // 如果通道处于读就绪状态
                if (key.isReadable()) {
                    read(key);
                }
                iterator.remove();
            }
        }
    }

    // 读操作
    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        byteBuffer.clear();
        // 从通道中读取数据到缓存区
        while ((count = socketChannel.read(byteBuffer)) > 0 ){
            // byteBuffer 写操作变为读模式
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.println(byteBuffer.get());
            }
            byteBuffer.clear();
        }
        if (count < 0) {
            socketChannel.close();
        }
    }

    // 写操作
    private void write(SocketChannel channel,byte[] writeData) throws IOException {
        byteBuffer.clear();
        byteBuffer.put(writeData);
        // byteBuffer从写模式变为读模式
        byteBuffer.flip();
        // 将缓存区的数据写入通道
        channel.write(byteBuffer);
    }

    // Channel 注册
    private void registerChannel(Selector selector, SocketChannel channel, int opRead) throws IOException {
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector,opRead);
    }

    public static void main(String[] args) {
        try {
            NIOServer server = new NIOServer(9999);
            server.listener();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
