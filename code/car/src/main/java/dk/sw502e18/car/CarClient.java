package dk.sw502e18.car;

import dk.sw502e18.car.carClient.CarClientMessageListener;

public interface CarClient {
    CarClient addListener(CarClientMessageListener listener);
    void connect();
}
