package com.zylear.j2eelab.pass;

import com.zylear.j2eelab.pass.file.FileTcpServer;
import com.zylear.j2eelab.pass.main.TcpServer;

/**
 * Created by xiezongyu on 2019/2/20.
 */
public class Starter {

    public static void main(String[] args) throws Exception {
        TcpServer tcpServer = new TcpServer();
        tcpServer.process();
        FileTcpServer fileTcpServer = new FileTcpServer();
        fileTcpServer.process();
        while (true) {
            Thread.sleep(3000);
        }
    }


}
