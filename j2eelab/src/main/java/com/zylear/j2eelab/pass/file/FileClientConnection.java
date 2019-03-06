package com.zylear.j2eelab.pass.file;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by xiezongyu on 2019/2/19.
 */


public class FileClientConnection {

    private String ip = "94.191.71.203";

    public static Integer port = 38948;

    private Socket socket;

    private Socket subSocket;

    DataOutputStream dataOutputStream;

    public FileClientConnection(Socket socket) {

        subSocket = socket;

    }


    public void start() {

        try {
            socket = new Socket(ip, port);

            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            receive();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

//    public void send(String string) throws IOException {
//
//
//        dataOutputStream.writeUTF(string);
//    }

    private void receive() {

        new Thread() {
            @Override
            public void run() {
                try {
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream dataOutputStream = new DataOutputStream(subSocket.getOutputStream());

                    byte[] bytes = new byte[1024];

                    while (true) {
                        int read = dataInputStream.read(bytes, 0, bytes.length);
                        if (read > 0) {
                            System.out.println("receiver from file server:");
                            System.out.println(read);
                            System.out.println();
                            dataOutputStream.write(bytes, 0, read);
                        }

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }


//    public static String unlock(String string) {
//        char[] toCharArray = string.toCharArray();
//        for (int i = 0; i < toCharArray.length; i++) {
//            toCharArray[i] = (char) (toCharArray[i] ^ 2);
//        }
//        return new String(toCharArray);
//    }
//
//    public static String lock(String string) {
//
//        char[] toCharArray = string.toCharArray();
//        for (int i = 0; i < toCharArray.length; i++) {
//            toCharArray[i] = (char) (toCharArray[i] ^ 2);
//        }
//        return new String(toCharArray);
//    }

    public void send(byte[] bytes, int offset, int length) throws IOException {

        dataOutputStream.write(bytes, offset, length);

    }

//    public static void main(String[] args) {
//        Json json = new Json(lock("flag%!%signIn%!!%random%!%12%!!%value%!%true%!!%echo%!%yes"));
//        System.out.println(json.get("value"));
//        System.out.println(json.get("echo"));
//        System.out.println(json.get("random"));
//        System.out.println(json.get("flag"));
//        //dnce'#'qkelKl'##'gajm'#'贤叵丏孚圪'##'tcnwg'#'dcnqg
//        //flag%!%signIn%!!%echo%!%登录成功123456%!!%value%!%false
//    }
}

