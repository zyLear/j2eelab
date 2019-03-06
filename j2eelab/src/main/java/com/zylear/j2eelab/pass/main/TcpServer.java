package com.zylear.j2eelab.pass.main;

import com.zylear.j2eelab.pass.Json;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xiezongyu on 2019/2/19.
 */
public class TcpServer {


    private ClientConnection clientConnection;

    public static String ramdom;

    public static void main(String[] args) throws IOException {
        TcpServer tcpServer = new TcpServer();
        tcpServer.process();

    }

    public void process() throws IOException {
        new Thread() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = null;

                    serverSocket = new ServerSocket(ClientConnection.port);

                    while (true) {
//            if (!flag) {
                        final Socket socket = serverSocket.accept();

                        if (socket != null) {

                            clientConnection = new ClientConnection(socket);

                            clientConnection.start();

                            new Thread() {
                                @Override
                                public void run() {
                                    process(socket);
                                }
                            }.start();
//                break;
                        }


                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();


//
//        while (true) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private void process(Socket socket) {
        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            while (true) {
                String string = dataInputStream.readUTF();

                System.out.println("receiver from client:");
                System.out.println(string);
//                String unlock = unlock(string);
                Json json = new Json(string);
                if (json.get("random") != null) {
                    ramdom = json.get("random");
                    System.out.println("random:" + json.get("random"));
                }
                System.out.println(unlock(string));
                System.out.println();

                clientConnection.send(string);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public String unlock(String string) {
        char[] toCharArray = string.toCharArray();
        for (int i = 0; i < toCharArray.length; i++) {
            toCharArray[i] = (char) (toCharArray[i] ^ 2);
        }
        return new String(toCharArray);
    }


}
