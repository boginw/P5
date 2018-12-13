package dk.sw502e18.ssr.carServer;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.junit.Assert;
import org.junit.Test;


public class SocketCarServerTest {

  @Test
  public void returnsFalseWhenUnableToConnect() {
    SocketCarServer scs = new SocketCarServer("not-valid-ip", 9000);

    boolean actual = scs.connect();

    Assert.assertFalse(actual);
  }

  @Test
  public void canSendAMessage() throws IOException {
    mockSocketCarServer scs = new mockSocketCarServer("", 900);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Socket spy = scs.getSocket();

    doReturn(baos).when(spy).getOutputStream();

    scs.send("Hello World");

    Assert.assertEquals("Hello World\n", baos.toString());
  }

  private class mockSocketCarServer extends SocketCarServer {

    public mockSocketCarServer(String address, int port) {
      super(address, port);
      socket = spy(Socket.class);
    }

    @Override
    public boolean connect(){
      return true;
    }

    @Override
    public void disconnect(){

    }

    public Socket getSocket(){
      return this.socket;
    }
  }

}
