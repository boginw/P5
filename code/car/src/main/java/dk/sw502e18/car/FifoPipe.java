package dk.sw502e18.car;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

public class FifoPipe {
    private RandomAccessFile pipe;
    private List<PipeListener> listenerList = synchronizedList(new ArrayList<PipeListener>());
    private PipeReader reader = new PipeReader();
    private String path;

    /**
     * Default constructor
     *
     * @param path Path to the pipe to read/write to/from.
     */
    public FifoPipe(String path) {
        this.path = path;
    }

    /**
     * Sets a listener up for receiving messages from the pipe.
     *
     * @param listener The listener to add.
     * @return this, as a way to chain listeners.
     */
    public FifoPipe addListener(PipeListener listener) {
        listenerList.add(listener);
        return this;
    }

    /**
     * Starts reading from the pipe
     */
    public void read() {
        openPipe();
        Thread readingThread = new Thread(reader);
        readingThread.start();
    }

    /**
     * Closes the read and pipe
     */
    public void close() {
        try {
            pipe.close();
            reader.exit = true;
            pipe.write('\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openPipe() {
        try {
            pipe = new RandomAccessFile(path, "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String readLine() {
        try {
            return pipe.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This class is for running a continous listening of a pipe.
     * To close it, set exit = true, and then write anything to the pipe.
     */
    private class PipeReader implements Runnable {
        private volatile boolean exit = false;

        public void run() {
            while (true) {
                String msg = readLine();

                if (exit) {
                    break;
                }

                for (PipeListener listener : listenerList) {
                    listener.onMessage(msg);
                }
            }
        }
    }
}
