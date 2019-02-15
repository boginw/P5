package dk.sw502e18.car.carClient;

import dk.sw502e18.car.CarClient;

<<<<<<< HEAD
import java.io.IOException;
=======
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
>>>>>>> master
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

public class SocketCarClient implements CarClient {
    private List<CarClientMessageListener> listenerList = synchronizedList(new ArrayList<CarClientMessageListener>());
<<<<<<< HEAD
    private static final int MAX_CHARACTERS = 8; // fills the width of the EV3 screen
=======
>>>>>>> master

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

<<<<<<< HEAD
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
=======
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                String data;
                while ((data = in.readLine()) != null) {
                    for (CarClientMessageListener carListener : listenerList) {
                        carListener.onMessage(data);
                    }
                }

                in.close();
>>>>>>> master
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
