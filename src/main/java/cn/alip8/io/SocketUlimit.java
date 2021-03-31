package cn.alip8.io;

import java.io.IOException;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Yao Shuai
 * @date: 2021/3/24 18:46
 */
public class SocketUlimit {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999, 50000);
        while (true) {
            Socket socket = serverSocket.accept();
        }
    }
}
