package dk.sw502e18.ssr.carServer;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;


public class SocketCarServerTest {

    @Test
    public void returnsFalseWhenUnableToConnect() {
        SocketCarServer scs = new SocketCarServer("not-valid-ip", 9000);

        boolean actual = scs.connect();

        Assert.assertFalse(actual);
    }

    @Test
    public void canSendAMessage() throws IOException {
        MockSocketCarServer scs = new MockSocketCarServer("", 900);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Socket spy = scs.getSocket();

        doReturn(baos).when(spy).getOutputStream();

        scs.send("Hello World");

        Assert.assertEquals("Hello World\n", baos.toString());
    }

    private class MockSocketCarServer extends SocketCarServer {

        private MockSocketCarServer(String address, int port) {
            super(address, port);
            socket = spy(Socket.class);
        }

        @Override
        public boolean connect() {
            return true;
        }

        @Override
        public void disconnect() {

        }

        private Socket getSocket() {
            return this.socket;
        }
    }

}
