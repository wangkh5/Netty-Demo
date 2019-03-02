package com.wangkaihua;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @desciption: 传统BIO演示
 * @author: wangkaihua
 * @date: 2019/2/23 12:03
 */
public class BIOServer {

    public static void main(String[] args) {
//        server1();
//        server2();
        server3();
    }

    /**
     * 单线程
     */
    private static void server1() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("server start success, waiting client connect...");
            while (true) {
                // 等待客户端连接，是阻塞的
                Socket accept = serverSocket.accept();
                // 获取客户端的输入流
                InputStream is = accept.getInputStream();
                byte [] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    System.out.println(new String(buffer, 0, len));
                }
                // 获取客户端的输出流
                OutputStream os = accept.getOutputStream();
                os.write("hello client, i am server ...".getBytes());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 多线程
     */
    private static void server2() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("server start success, waiting connect...");
            while (true) {
                // 阻塞
                Socket accept = serverSocket.accept();
                new Thread(new ServerHandler(accept)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程池
     */
    private static void server3() {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            System.out.println("server start success, waiting connect...");
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                // 阻塞
                Socket accept = serverSocket.accept();
                executorService.execute(new ServerHandler(accept));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
