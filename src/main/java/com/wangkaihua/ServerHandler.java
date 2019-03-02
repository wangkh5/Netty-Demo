package com.wangkaihua;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @desciption: BIO多线程处理器
 * @author: wangkaihua
 * @date: 2019/2/23 12:38
 */
public class ServerHandler implements Runnable {

    private Socket accept;

    public ServerHandler(Socket accept) {
        this.accept = accept;
    }

    public void run() {
        System.out.println("当前线程的名称："+Thread.currentThread().getName());
        try {
            // 读取客户端数据
            InputStream is = accept.getInputStream();
            byte [] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) > 0) {
                System.out.println(new String(buffer, 0, len));
            }

            // 获取客户端的输出流
            OutputStream os = accept.getOutputStream();
            os.write("hello client, i am server ...".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
