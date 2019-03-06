package com.zylear.j2eelab.hook;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.io.File;

/**
 * Created by xiezongyu on 2018/9/27.
 */
public class FileServer extends Thread {
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
        new FileServer("build.prop").run();
//        new FileServer("init.sh").run();
//        new FileServer("bootanimation-after-first-j2eelab.zip").run();

    }
}