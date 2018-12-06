package dk.sw502e18.ssr;

public interface CarServer {
    boolean connect();
    void disconnect();
    boolean send(String message);
}
