//package com.example.a28944.wechat;
//
//import java.io.Closeable;
//import java.io.DataInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.InetAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.UnknownHostException;
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class PhoenixOSVPNServer {
//    private static final Integer MAX_CLIENT_NUM = Integer.valueOf(99999999);
//    private static final byte RSV = (byte) 0;
//    private static String SERVER_IP_ADDRESS = null;
//    private static Integer SERVICE_LISTENER_PORT = Integer.valueOf(6666);
//    private static final byte VERSION = (byte) 5;
//    private static AtomicInteger clientNumCount = new AtomicInteger();
//    public static DataInputStream in;
//
//    public enum ADDRESS_TYPE {
//        IPV4((byte) 1, "the address is a version-4 IP address, with a length of 4 octets"),
//        DOMAIN((byte) 3, "the address field contains a fully-qualified domain name.  The first\n   octet of the address field contains the number of octets of name that\n   follow, there is no terminating NUL octet."),
//        IPV6((byte) 4, "the address is a version-6 IP address, with a length of 16 octets.");
//
//        String description;
//        byte value;
//
//        private ADDRESS_TYPE(byte b, String str) {
//            this.value = b;
//            this.description = str;
//        }
//
//        public static ADDRESS_TYPE convertToAddressType(byte b) {
//            for (ADDRESS_TYPE address_type : values()) {
//                if (address_type.value == b) {
//                    return address_type;
//                }
//            }
//            return null;
//        }
//    }
//
//    public enum COMMAND {
//        CONNECT((byte) 1, "CONNECT"),
//        BIND((byte) 2, "BIND"),
//        UDP_ASSOCIATE((byte) 3, "UDP ASSOCIATE");
//
//        String description;
//        byte value;
//
//        private COMMAND(byte b, String str) {
//            this.value = b;
//            this.description = str;
//        }
//
//        public static COMMAND convertToCmd(byte b) {
//            for (COMMAND command : values()) {
//                if (command.value == b) {
//                    return command;
//                }
//            }
//            return null;
//        }
//    }
//
//    public enum COMMAND_STATUS {
//        SUCCEEDED(PhoenixOSVPNServer.RSV, PhoenixOSVPNServer.RSV, "succeeded"),
//        GENERAL_SOCKS_SERVER_FAILURE((byte) 1, (byte) 1, "general SOCKS server failure"),
//        CONNECTION_NOT_ALLOWED_BY_RULESET((byte) 2, (byte) 2, "connection not allowed by ruleset"),
//        NETWORK_UNREACHABLE((byte) 3, (byte) 3, "Network unreachable"),
//        HOST_UNREACHABLE((byte) 4, (byte) 4, "Host unreachable"),
//        CONNECTION_REFUSED(PhoenixOSVPNServer.VERSION, PhoenixOSVPNServer.VERSION, "Connection refused"),
//        TTL_EXPIRED((byte) 6, (byte) 6, "TTL expired"),
//        COMMAND_NOT_SUPPORTED((byte) 7, (byte) 7, "Command not supported"),
//        ADDRESS_TYPE_NOT_SUPPORTED((byte) 8, (byte) 8, "Address type not supported"),
//        UNASSIGNED((byte) 9, (byte) -1, "unassigned");
//
//        private String description;
//        private byte rangeEnd;
//        private byte rangeStart;
//
//        private COMMAND_STATUS(byte b, byte b2, String str) {
//            this.rangeStart = b;
//            this.rangeEnd = b2;
//            this.description = str;
//        }
//    }
//
//    public static class ClientHandler implements Runnable {
//        private String clientIp;
//        private int clientPort;
//        private Socket clientSocket;
//
//        public ClientHandler(Socket socket) {
//            this.clientSocket = socket;
//            this.clientIp = socket.getInetAddress().getHostAddress();
//            this.clientPort = socket.getPort();
//        }
//
//        public void run() {
//            try {
//                negotiationCertificationMethod();
//                handleClientCommand();
//            } catch (Exception unused) {
//            } catch (Throwable th) {
//                PhoenixOSVPNServer.close(this.clientSocket);
//            }
//            PhoenixOSVPNServer.close(this.clientSocket);
//        }
//
//        private void negotiationCertificationMethod() throws IOException {
//            InputStream inputStream = this.clientSocket.getInputStream();
//            OutputStream outputStream = this.clientSocket.getOutputStream();
//            byte[] bArr = new byte[255];
//            inputStream.read(bArr, 0, 2);
//            byte b = bArr[0];
//            byte b2 = bArr[1];
//            if (b != PhoenixOSVPNServer.VERSION) {
//                throw new RuntimeException("version must 0X05");
//            } else if (b2 >= (byte) 1) {
//                inputStream.read(bArr, 0, b2);
//                METHOD.convertToMethod(Arrays.copyOfRange(bArr, 0, b2));
//                bArr[0] = PhoenixOSVPNServer.VERSION;
//                bArr[1] = METHOD.NO_AUTHENTICATION_REQUIRED.rangeStart;
//                outputStream.write(bArr, 0, 2);
//                outputStream.flush();
//            } else {
//                throw new RuntimeException("method num must gt 0");
//            }
//        }
//
//        private void handleClientCommand() throws IOException {
//            InputStream inputStream = this.clientSocket.getInputStream();
//            this.clientSocket.getOutputStream();
//            byte[] bArr = new byte[255];
//            inputStream.read(bArr, 0, 4);
//            byte b = bArr[0];
//            COMMAND convertToCmd = COMMAND.convertToCmd(bArr[1]);
//            byte b2 = bArr[2];
//            ADDRESS_TYPE convertToAddressType = ADDRESS_TYPE.convertToAddressType(bArr[3]);
//            if (b2 != (byte) 0) {
//                throw new RuntimeException("RSV must 0X05");
//            } else if (b != PhoenixOSVPNServer.VERSION) {
//                throw new RuntimeException("VERSION must 0X05");
//            } else if (convertToCmd == null) {
//                sendCommandResponse(COMMAND_STATUS.COMMAND_NOT_SUPPORTED);
//            } else if (convertToAddressType == null) {
//                sendCommandResponse(COMMAND_STATUS.ADDRESS_TYPE_NOT_SUPPORTED);
//            } else {
//                String str = BuildConfig.FLAVOR;
//                switch (convertToAddressType) {
//                    case DOMAIN:
//                        inputStream.read(bArr, 0, 1);
//                        byte b3 = bArr[0];
//                        inputStream.read(bArr, 0, b3);
//                        str = new String(Arrays.copyOfRange(bArr, 0, b3));
//                        break;
//                    case IPV4:
//                        inputStream.read(bArr, 0, 4);
//                        str = ipAddressBytesToString(bArr);
//                        break;
//                    case IPV6:
//                        throw new RuntimeException("not support ipv6.");
//                }
//                inputStream.read(bArr, 0, 2);
//                int i = ((bArr[0] & 255) << 8) | (255 & bArr[1]);
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("version=");
//                stringBuilder.append(b);
//                stringBuilder.append(", cmd=");
//                stringBuilder.append(convertToCmd.name());
//                stringBuilder.append(", addressType=");
//                stringBuilder.append(convertToAddressType.name());
//                stringBuilder.append(", domain=");
//                stringBuilder.append(str);
//                stringBuilder.append(", port=");
//                stringBuilder.append(i);
//                handleLog(stringBuilder.toString(), new Object[0]);
//                switch (convertToCmd) {
//                    case CONNECT:
//                        handleConnectCommand(str, i);
//                        break;
//                    case BIND:
//                        break;
//                    case UDP_ASSOCIATE:
//                        throw new RuntimeException("not support command UDP_ASSOCIATE");
//                    default:
//                        return;
//                }
//                throw new RuntimeException("not support command BIND");
//            }
//        }
//
//        private String ipAddressBytesToString(byte[] bArr) {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(bArr[0] & 255);
//            stringBuilder.append(".");
//            stringBuilder.append(bArr[1] & 255);
//            stringBuilder.append(".");
//            stringBuilder.append(bArr[2] & 255);
//            stringBuilder.append(".");
//            stringBuilder.append(bArr[3] & 255);
//            return stringBuilder.toString();
//        }
//
//        private void handleConnectCommand(String str, int i) throws IOException {
//            try {
//                Socket socket = new Socket(str, i);
//                sendCommandResponse(COMMAND_STATUS.SUCCEEDED);
//                new SocketForwarding(this.clientSocket, socket).start();
//            } catch (IOException unused) {
//                sendCommandResponse(COMMAND_STATUS.GENERAL_SOCKS_SERVER_FAILURE);
//            }
//        }
//
//        private void sendCommandResponse(COMMAND_STATUS command_status) throws IOException {
//            OutputStream outputStream = this.clientSocket.getOutputStream();
//            outputStream.write(buildCommandResponse(command_status.rangeStart));
//            outputStream.flush();
//        }
//
//        private byte[] buildCommandResponse(byte b) {
//            ByteBuffer allocate = ByteBuffer.allocate(100);
//            allocate.put(PhoenixOSVPNServer.VERSION);
//            allocate.put(b);
//            allocate.put(PhoenixOSVPNServer.RSV);
//            allocate.put(ADDRESS_TYPE.DOMAIN.value);
//            byte[] bytes = PhoenixOSVPNServer.SERVER_IP_ADDRESS.getBytes();
//            allocate.put((byte) bytes.length);
//            allocate.put(bytes);
//            allocate.put((byte) ((PhoenixOSVPNServer.SERVICE_LISTENER_PORT.intValue() /*& MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8*/));
//            allocate.put((byte) (PhoenixOSVPNServer.SERVICE_LISTENER_PORT.intValue() & 255));
//            bytes = new byte[allocate.position()];
//            allocate.flip();
//            allocate.get(bytes);
//            return bytes;
//        }
//
//        private void handleLog(String str, Object... objArr) {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("handle, clientIp=");
//            stringBuilder.append(this.clientIp);
//            stringBuilder.append(", port=");
//            stringBuilder.append(this.clientPort);
//            stringBuilder.append(", ");
//            stringBuilder.append(str);
//            PhoenixOSVPNServer.log(stringBuilder.toString(), objArr);
//        }
//    }
//
//    public enum METHOD {
//        NO_AUTHENTICATION_REQUIRED(PhoenixOSVPNServer.RSV, PhoenixOSVPNServer.RSV, "NO AUTHENTICATION REQUIRED"),
//        GSSAPI((byte) 1, (byte) 1, "GSSAPI"),
//        USERNAME_PASSWORD((byte) 2, (byte) 2, " USERNAME/PASSWORD"),
//        IANA_ASSIGNED((byte) 3, (byte) 7, "IANA ASSIGNED"),
//        RESERVED_FOR_PRIVATE_METHODS(Byte.MIN_VALUE, (byte) -2, "RESERVED FOR PRIVATE METHODS"),
//        NO_ACCEPTABLE_METHODS((byte) -1, (byte) -1, "NO ACCEPTABLE METHODS");
//
//        private String description;
//        private byte rangeEnd;
//        private byte rangeStart;
//
//        private METHOD(byte b, byte b2, String str) {
//            this.rangeStart = b;
//            this.rangeEnd = b2;
//            this.description = str;
//        }
//
//        public boolean isMe(byte b) {
//            return b >= this.rangeStart && b <= this.rangeEnd;
//        }
//
//        public static List<METHOD> convertToMethod(byte[] bArr) {
//            ArrayList arrayList = new ArrayList();
//            for (byte b : bArr) {
//                for (METHOD method : values()) {
//                    if (method.isMe(b)) {
//                        arrayList.add(method);
//                        break;
//                    }
//                }
//            }
//            return arrayList;
//        }
//    }
//
//    public static class SocketForwarding {
//        private String clientIp;
//        private Socket clientSocket;
//        private String targetAddress;
//        private int targetPort;
//        private Socket targetSocket;
//
//        private void transientLog(String str, Object... objArr) {
//        }
//
//        public SocketForwarding(Socket socket, Socket socket2) {
//            this.clientSocket = socket;
//            this.clientIp = socket.getInetAddress().getHostAddress();
//            this.targetSocket = socket2;
//            this.targetAddress = socket2.getInetAddress().getHostAddress();
//            this.targetPort = socket2.getPort();
//        }
//
//        /* JADX WARNING: Removed duplicated region for block: B:21:0x004c A:{SYNTHETIC, Splitter:B:21:0x004c} */
//        /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0044 */
//        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
//        /* Code decompiled incorrectly, please refer to instructions dump. */
//        public void start() {
//            Closeable outputStream;
//            Closeable inputStream;
//            Closeable closeable;
//            Closeable closeable2;
//            Throwable th;
//            long currentTimeMillis = System.currentTimeMillis();
//            try {
//                outputStream = this.clientSocket.getOutputStream();
//                try {
//                    inputStream = this.clientSocket.getInputStream();
//                } catch (IOException unused) {
//                    inputStream = null;
//                    closeable = inputStream;
//                    closeable2 = closeable;
//                    PhoenixOSVPNServer.close(inputStream);
//                    PhoenixOSVPNServer.close(outputStream);
//                    PhoenixOSVPNServer.close(closeable2);
//                    PhoenixOSVPNServer.close(closeable);
//                    PhoenixOSVPNServer.close(this.clientSocket);
//                    PhoenixOSVPNServer.close(this.targetSocket);
//                } catch (Throwable th2) {
//                    th = th2;
//                    inputStream = null;
//                    closeable = inputStream;
//                    closeable2 = closeable;
//                    PhoenixOSVPNServer.close(inputStream);
//                    PhoenixOSVPNServer.close(outputStream);
//                    PhoenixOSVPNServer.close(closeable2);
//                    PhoenixOSVPNServer.close(closeable);
//                    PhoenixOSVPNServer.close(this.clientSocket);
//                    PhoenixOSVPNServer.close(this.targetSocket);
//                    throw th;
//                }
//                try {
//                    closeable = this.targetSocket.getOutputStream();
//                    try {
//                        closeable2 = this.targetSocket.getInputStream();
//                        try {
//                            byte[] bArr = new byte[524288];
//                            while (true) {
//                                Object obj = 1;
//                                while (inputStream.available() != 0) {
//                                    int read = inputStream.read(bArr);
//                                    int port = this.targetSocket.getPort();
//                                    Message obtainMessage = MainActivity.f8VPN.obtainMessage();
//                                    obtainMessage.obj = Integer.valueOf(read);
//                                    MainActivity.f8VPN.sendMessage(obtainMessage);
//                                    if (Controler.isLogined.booleanValue()) {
//                                        try {
//                                            MainActivity.f3.sendEmptyMessage(0);
//                                        } catch (Exception unused2) {
//                                        }
//                                        if (port != 443 || read < 200 || read > 800) {
//                                            if (port != 443 || read < 1200) {
//                                                if (!(port == 17500 || port == 443)) {
//                                                }
//                                            }
//                                        }
//                                    }
//                                    try {
//                                        closeable.write(bArr, 0, read);
//                                    } catch (Exception unused3) {
//                                    }
//                                    obj = null;
//                                }
//                                while (closeable2.available() != 0) {
//                                    outputStream.write(bArr, 0, closeable2.read(bArr));
//                                    obj = null;
//                                }
//                                if (this.clientSocket.isClosed()) {
//                                    break;
//                                } else if (System.currentTimeMillis() - currentTimeMillis > 30000) {
//                                    break;
//                                } else if (obj != null) {
//                                    try {
//                                        TimeUnit.MILLISECONDS.sleep(1);
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        } catch (IOException unused4) {
//                        } catch (Throwable th3) {
//                            th = th3;
//                            PhoenixOSVPNServer.close(inputStream);
//                            PhoenixOSVPNServer.close(outputStream);
//                            PhoenixOSVPNServer.close(closeable2);
//                            PhoenixOSVPNServer.close(closeable);
//                            PhoenixOSVPNServer.close(this.clientSocket);
//                            PhoenixOSVPNServer.close(this.targetSocket);
//                            throw th;
//                        }
//                    } catch (IOException unused5) {
//                        closeable2 = null;
//                    } catch (Throwable th4) {
//                        th = th4;
//                        closeable2 = null;
//                        PhoenixOSVPNServer.close(inputStream);
//                        PhoenixOSVPNServer.close(outputStream);
//                        PhoenixOSVPNServer.close(closeable2);
//                        PhoenixOSVPNServer.close(closeable);
//                        PhoenixOSVPNServer.close(this.clientSocket);
//                        PhoenixOSVPNServer.close(this.targetSocket);
//                        throw th;
//                    }
//                } catch (IOException unused6) {
//                    closeable = null;
//                    closeable2 = closeable;
//                    PhoenixOSVPNServer.close(inputStream);
//                    PhoenixOSVPNServer.close(outputStream);
//                    PhoenixOSVPNServer.close(closeable2);
//                    PhoenixOSVPNServer.close(closeable);
//                    PhoenixOSVPNServer.close(this.clientSocket);
//                    PhoenixOSVPNServer.close(this.targetSocket);
//                } catch (Throwable th5) {
//                    th = th5;
//                    closeable = null;
//                    closeable2 = closeable;
//                    PhoenixOSVPNServer.close(inputStream);
//                    PhoenixOSVPNServer.close(outputStream);
//                    PhoenixOSVPNServer.close(closeable2);
//                    PhoenixOSVPNServer.close(closeable);
//                    PhoenixOSVPNServer.close(this.clientSocket);
//                    PhoenixOSVPNServer.close(this.targetSocket);
//                    throw th;
//                }
//            } catch (IOException unused7) {
//                outputStream = null;
//                inputStream = outputStream;
//                closeable = inputStream;
//                closeable2 = closeable;
//                PhoenixOSVPNServer.close(inputStream);
//                PhoenixOSVPNServer.close(outputStream);
//                PhoenixOSVPNServer.close(closeable2);
//                PhoenixOSVPNServer.close(closeable);
//                PhoenixOSVPNServer.close(this.clientSocket);
//                PhoenixOSVPNServer.close(this.targetSocket);
//            } catch (Throwable th6) {
//                th = th6;
//                outputStream = null;
//                inputStream = outputStream;
//                closeable = inputStream;
//                closeable2 = closeable;
//                PhoenixOSVPNServer.close(inputStream);
//                PhoenixOSVPNServer.close(outputStream);
//                PhoenixOSVPNServer.close(closeable2);
//                PhoenixOSVPNServer.close(closeable);
//                PhoenixOSVPNServer.close(this.clientSocket);
//                PhoenixOSVPNServer.close(this.targetSocket);
//                throw th;
//            }
//            PhoenixOSVPNServer.close(inputStream);
//            PhoenixOSVPNServer.close(outputStream);
//            PhoenixOSVPNServer.close(closeable2);
//            PhoenixOSVPNServer.close(closeable);
//            PhoenixOSVPNServer.close(this.clientSocket);
//            PhoenixOSVPNServer.close(this.targetSocket);
//        }
//    }
//
//    static {
//        try {
//            SERVER_IP_ADDRESS = InetAddress.getLocalHost().getHostAddress();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static synchronized void log(String str, Object... objArr) {
//        synchronized (PhoenixOSVPNServer.class) {
//        }
//    }
//
//    private static void close(Closeable closeable) {
//        if (closeable != null) {
//            try {
//                closeable.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void main() {
//        try {
//            ServerSocket serverSocket = new ServerSocket(SERVICE_LISTENER_PORT.intValue());
//            while (true) {
//                Socket accept = serverSocket.accept();
//                if (clientNumCount.get() >= MAX_CLIENT_NUM.intValue()) {
//                    log("client num run out.", new Object[0]);
//                } else {
//                    log("new client, ip=%s:%d, current client count=%s", accept.getInetAddress(), Integer.valueOf(accept.getPort()), Integer.valueOf(clientNumCount.get()));
//                    clientNumCount.incrementAndGet();
//                    ClientHandler clientHandler = new ClientHandler(accept);
//                    StringBuilder stringBuilder = new StringBuilder();
//                    stringBuilder.append("client-handler-");
//                    stringBuilder.append(UUID.randomUUID().toString());
//                    new Thread(clientHandler, stringBuilder.toString()).start();
//                }
//            }
//        } catch (Exception unused) {
//            new PhoenixOSVPNServer().main();
//        }
//    }
//}