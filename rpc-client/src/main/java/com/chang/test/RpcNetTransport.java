package com.chang.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcNetTransport {
    String host;
    int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket() {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return socket;
    }

    public Object send(RpcRequest rpcRequest) {
        Socket socket = null;
        try {
            socket = newSocket();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object result = objectInputStream.readObject();
            objectInputStream.close();
            objectOutputStream.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
