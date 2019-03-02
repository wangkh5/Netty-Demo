package com.wangkaihua;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @desciption: BIO客户端
 * @author: wangkaihua
 * @date: 2019/2/23 13:19
 */
public class BIOClient {
    public static void main(String[] args) throws IOException {
//        test();
        testThread();
    }
    public static void test() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8080);
            OutputStream os = socket.getOutputStream();
            os.write("hello server, i am client...".getBytes());
            // 不关闭的话服务端的is.read会阻塞，详解请查看博文：关于java socket中的read方法阻塞问题
            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            int len = 0;
            byte [] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testThread() {
        // 多线程模拟五个客户端
        for (int i = 0; i < 5; i++) {
            new Thread(new ClientHandler()).start();
        }
    }
}
