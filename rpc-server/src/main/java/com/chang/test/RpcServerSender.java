package com.chang.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RpcServerSender {

    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 20, 1, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(), (r, runnable) -> System.out.println("server is error, queue full"));

    public void sender(Object service, int port) {
        ServerSocket serverSocket = null;
        try {

            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executor.execute(() -> {
                            System.out.println(Thread.currentThread().getName());
                            ObjectInputStream objectInputStream = null;
                            try {
                                objectInputStream = new ObjectInputStream(socket.getInputStream());
                                RpcRequest rpcRequest = null;

                                rpcRequest = (RpcRequest) objectInputStream.readObject();

                                Object[] args = rpcRequest.getParams();
                                Class<?>[] types = new Class[args.length];
                                for (int i = 0; i < args.length; i++) {
                                    types[i] = args[i].getClass();
                                }

                                Method method = service.getClass().getMethod(rpcRequest.getMethodName(), types);
                                System.out.println(rpcRequest.getMethodName());

                                Object result = method.invoke(service, args);
                                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                objectOutputStream.writeObject(result);

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } finally {
                                if (objectInputStream != null) {
                                    try {
                                        objectInputStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
