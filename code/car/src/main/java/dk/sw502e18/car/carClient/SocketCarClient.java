package dk.sw502e18.car.carClient;

import dk.sw502e18.car.CarClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

public class SocketCarClient implements CarClient {
    private List<CarClientMessageListener> listenerList = synchronizedList(new ArrayList<CarClientMessageListener>());
    private SocketReader reader = new SocketReader();

    @Override
    public CarClient addListener(CarClientMessageListener listener) {
        listenerList.add(listener);
        return this;
    }

    @Override
    public void connect() {
        Thread readingThread = new Thread(reader);
        readingThread.start();
    }

    @Override
    public void disconnect() {
        reader.exit = true;
    }

    /**
     * This class is for running a continuous listening of a socket.
     * To close it, set exit = true, and then write anything to the socket.
     */
    private class SocketReader implements Runnable {
        private volatile boolean exit = false;

        public void run() {
            try (ServerSocket listener = new ServerSocket(9090)) {
                while (true) {
                    Socket socket = listener.accept();

                    if (exit) {
                        break;
                    }

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

                    String data;
                    while ((data = in.readLine()) != null) {
                        for (CarClientMessageListener carListener : listenerList) {
                            carListener.onMessage(data);
                        }
                    }

                    in.close();
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
