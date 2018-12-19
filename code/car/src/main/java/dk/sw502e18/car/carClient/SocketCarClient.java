package dk.sw502e18.car.carClient;

import dk.sw502e18.car.CarClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

public class SocketCarClient implements CarClient {
    private List<CarClientMessageListener> listenerList = synchronizedList(new ArrayList<CarClientMessageListener>());
    private static final int MAX_CHARACTERS = 8; // fills the width of the EV3 screen

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

                StringBuilder sb = new StringBuilder();
                int read;

                while ((read = socket.getInputStream().read()) != -1 && // ensure can read character
                        sb.length() < MAX_CHARACTERS // ensure string length less than limit
                ) {
                    // if newline stop and send to listeners
                    if (read == '\n') {
                        break;
                    }

                    sb.append((char) read);
                }

                for (CarClientMessageListener carListener : listenerList) {
                    carListener.onMessage(sb.toString());
                }

                socket.getInputStream().close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
