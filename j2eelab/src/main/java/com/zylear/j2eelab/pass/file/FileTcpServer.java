package com.zylear.j2eelab.pass.file;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xiezongyu on 2019/2/19.
 */
public class FileTcpServer {


    private FileClientConnection fileClientConnection;

    public static String ramdom;

    public static void main(String[] args) throws IOException {
        FileTcpServer fileTcpServer = new FileTcpServer();
        fileTcpServer.process();

    }

    public void process() throws IOException {
        new Thread(){
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(FileClientConnection.port);

                    while (true) {
//            if (!flag) {
                        final Socket socket = serverSocket.accept();

                        if (socket != null) {

                            fileClientConnection = new FileClientConnection(socket);

                            fileClientConnection.start();

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
                } catch (Exception e) {
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

            byte[] bytes = new byte[1024];

            while (true) {
                String string = dataInputStream.readUTF();
                System.out.println(string);
                System.out.println();



//                int read = dataInputStream.read(bytes, 0, bytes.length);
//                if (read > 0) {
//                    System.out.println("receiver from file client:");
//                    System.out.println(read);
//                    System.out.println();
//                    fileClientConnection.send(bytes, 0, read);
//                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


//    public String unlock(String string) {
//        char[] toCharArray = string.toCharArray();
//        for (int i = 0; i < toCharArray.length; i++) {
//            toCharArray[i] = (char) (toCharArray[i] ^ 2);
//        }
//        return new String(toCharArray);
//    }


}
