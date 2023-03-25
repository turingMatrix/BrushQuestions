package com.turing.doc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * TODO NIO Client
 *
 * @Description
 * @Author ll.lv
 * @Date 2023/3/18 18:04
 **/
public class NIOClient {
    private int size = 1024;
    private ByteBuffer byteBuffer;
    private SocketChannel socketChannel;
    public void connectServer() throws IOException {
        socketChannel = socketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));
        socketChannel.configureBlocking(false);
        byteBuffer = ByteBuffer.allocate(size);
        receive();
    }
    private void receive() throws IOException {
        while (true) {
            byteBuffer.clear();
            int count;
            // 如果没有数据可读，则read方法一直处于阻塞，直到有读取到新数据为止
            while ((count = socketChannel.read(byteBuffer)) > 0) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.println(byteBuffer.get());
                }
                send2Server("Say Hi".getBytes());
                byteBuffer.clear();
            }
        }
    }
    private void send2Server(byte[] bytes) throws IOException {
        byteBuffer.clear();
        byteBuffer.put(bytes);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
    }

    public static void main(String[] args) throws IOException {
        new NIOClient().connectServer();
    }
}
