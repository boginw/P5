package dk.sw502e18.car.carClient;

import dk.sw502e18.car.CarClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

public class SocketCarClient implements CarClient {
    private List<CarClientMessageListener> listenerList = synchronizedList(new ArrayList<CarClientMessageListener>());

    @Override
    public CarClient addListener(CarClientMessageListener listener) {
        listenerList.add(listener);
        return this;
    }

    @Override
    public void connect() {
        try (ServerSocket listener = new ServerSocket(9090)) {
            //noinspection InfiniteLoopStatement
            while (true) {
                Socket socket = listener.accept();

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
