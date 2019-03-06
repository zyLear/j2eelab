package com.zylear.j2eelab.hook;

import java.io.*;
import java.net.Socket;

/**
 * Created by xiezongyu on 2018/9/27.
 */
public class MainServer {

    public static class FileServer extends Thread {
        public String fileName;
        public FileOutputStream fileOut;
        public InputStream in;
        public DataOutputStream out;
        public Socket socket;

        public FileServer(String str) {
            this.fileName = str;
        }

        public void run() {
            try {
                this.socket = new Socket("112.74.167.101", 8661);
                this.out = new DataOutputStream(this.socket.getOutputStream());
                this.in = this.socket.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("/sdcard/");
                stringBuilder.append(this.fileName);
                File file = new File("C:\\Users\\xiezongyu\\Documents\\" + this.fileName);
                file.createNewFile();
                this.fileOut = new FileOutputStream(file);
                this.out.writeUTF(this.fileName);
                byte[] bArr = new byte[1024];
                while (true) {
                    this.fileOut.write(bArr, 0, this.in.read(bArr, 0, bArr.length));
                }
            } catch (Exception e) {
                try {
                    this.fileOut.close();
                } catch (Exception e2) {
                }
            }
        }

        public static void main(String[] args) {
            new FileServer("init.sh").run();
        }
    }


    static class C02342 extends Thread {
        C02342() {
        }

        public void run() {
            try {
                Socket socket = new Socket("112.74.167.101", 9453);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF("shell|凤凰过检测2");
                String s = dataInputStream.readUTF();
                System.out.println(s.split("\\|")[1]);
                ;

                dataInputStream.close();
                dataOutputStream.close();
                Thread.sleep(2000);
                File file = new File("/system/lib/libhoudini.so");
                File file2 = new File("/system/lib64/libhoudini.so");
                if (file.exists() && file2.exists()) {
                }
            } catch (Exception e) {
            }
        }
    }


    static class C02331 extends Thread {
        C02331() {
        }

        public void run() {
            try {
                Socket socket = new Socket("112.74.167.101", 9453);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF("shell|凤凰过检测1");//shell|雷电开启过检测
                String s = dataInputStream.readUTF();
//                System.out.println(s);
//                System.out.println();
                System.out.println(s.split("\\|")[1]);

                dataInputStream.close();
                dataOutputStream.close();
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }

    static class C02333 extends Thread {
        C02333() {
        }

        public void run() {
            try {
                Socket socket = new Socket("112.74.167.101", 9453);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF("shell|雷电开启过检测");//
                String s = dataInputStream.readUTF();
//                System.out.println(s);
                System.out.println(s.split("\\|")[1]);
                dataInputStream.close();
                dataOutputStream.close();
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }

    static class C02334 extends Thread {
        C02334() {
        }

        public void run() {
            try {
                Socket socket = new Socket("112.74.167.101", 9453);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF("shell|雷电关闭过检测");//
                String s = dataInputStream.readUTF();
//                System.out.println(s);
                System.out.println(s.split("\\|")[1]);
                dataInputStream.close();
                dataOutputStream.close();
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        new C02331().run();
//        new stopPassCheck().run();
//        new C02333().run();
//        new C02334().run();

    }
}
