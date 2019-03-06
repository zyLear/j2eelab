package com.zylear.j2eelab.pass.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by xiezongyu on 2019/2/19.
 */
public class ClientConnection {

    private String ip = "94.191.71.203";

    public static Integer port = 38947;

    private Socket socket;

    private Socket subSocket;

    DataOutputStream dataOutputStream;

    public ClientConnection(Socket socket) {

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

    public void send(String string) throws IOException {


        dataOutputStream.writeUTF(string);
    }

    private void receive() {

        new Thread() {
            @Override
            public void run() {
                try {
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream dataOutputStream = new DataOutputStream(subSocket.getOutputStream());
                    while (true) {
                        String string = dataInputStream.readUTF();

                        System.out.println("receiver from server:");
                        System.out.println(string);
                        String unlock = unlock(string);
                        System.out.println(unlock);
                        System.out.println();

//                        if (unlock.contains("您的VIP已过期，请充值您的VIP")) {
////
////                            System.out.println(s);
////                            String s = "flag%!%signIn%!!%random%!%12%!!%value%!%true%!!%echo%!%yes";
//                            dataOutputStream.writeUTF(lock(unlock.replace("%!%false", "%!%true")));
//                        } else {
                            dataOutputStream.writeUTF(string);
//                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }


    public static String unlock(String string) {
        char[] toCharArray = string.toCharArray();
        for (int i = 0; i < toCharArray.length; i++) {
            toCharArray[i] = (char) (toCharArray[i] ^ 2);
        }
        return new String(toCharArray);
    }

    public static String lock(String string) {

        char[] toCharArray = string.toCharArray();
        for (int i = 0; i < toCharArray.length; i++) {
            toCharArray[i] = (char) (toCharArray[i] ^ 2);
        }
        return new String(toCharArray);
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
