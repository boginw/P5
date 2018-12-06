package dk.sw502e18.ssr.carServer;

import dk.sw502e18.ssr.CarServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketCarServer implements CarServer {
    private String address;
    private int port;
    private Socket socket;

    public SocketCarServer(String address, int port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public boolean connect() {
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    @Override
    public void disconnect() {
        try {
            socket.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean send(String message) {
        PrintWriter out;

        // Ensure car is ready to receive
        disconnect();

        while (!connect()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return false;
            }
        }

        try {
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            return false;
        }

        out.println(message);
        out.close();
        return true;
    }
}
