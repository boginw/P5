package dk.sw502e18.ssr.carServer;

import com.jcraft.jsch.*;
import dk.sw502e18.ssr.CarServer;

import java.util.Properties;

public class PipeCarServer implements CarServer {
    private JSch jsch;
    private String host;
    private String user;
    private String pass = "";
    private Session session;

    public PipeCarServer(String host, String user) {
        jsch = new JSch();
        this.host = host;
        this.user = user;
    }

    @Override
    public boolean connect() {
        try {
            session = jsch.getSession(user, host, 22);
        } catch (JSchException e) {
            return false;
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setPassword(pass);
        try {
            session.connect();
        } catch (JSchException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean send(String message) {
        Channel channel;
        try {
            channel = session.openChannel("exec");
        } catch (JSchException e) {
            return false;
        }
        ((ChannelExec) channel).setCommand("echo " + message + " > /home/lejos/pipes/speedlimit");
        try {
            channel.connect();
        } catch (JSchException e) {
            return false;
        }
        channel.disconnect();

        return true;
    }

    @Override
    public void disconnect() {
        session.disconnect();
    }
}
