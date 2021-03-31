package cn.alip8.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author: Yao Shuai
 * @date: 2021/3/24 18:58
 */
public class SocketClientUlimit {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress("123.57.42.241", 9999));
                if(socket.isConnected()) {
                    System.out.println(i + "链接成功");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
