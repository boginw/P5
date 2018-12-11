package dk.sw502e18.ssr.mode;

import dk.sw502e18.ssr.EllipseProcessor;
import dk.sw502e18.ssr.Mode;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

public class WebCamMode implements Mode {
    public void start(VideoCapture vid, EllipseProcessor processor, Function<Mat, Integer> func) {
        Mat mat = new Mat();

        System.out.println("Camera open");

        ServerSocket ss;
        Socket sock = null;
        String boundary = "Thats it folks!";

        try {
            ss = new ServerSocket(8080);
            sock = ss.accept();
            writeHeader(sock.getOutputStream(), boundary);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            vid.read(mat);
            Mat p;

            if (!mat.empty() && (p = processor.detect(mat)) != null) {
                overlayImage(mat, p, mat.cols() - p.cols(), mat.rows() - p.rows(), p.cols(), p.rows());

                Imgproc.putText(
                        mat,
                        "Detected: " + func.apply(p),
                        new Point(5,15),
                        Core.FONT_HERSHEY_PLAIN,
                        1.0,
                        new Scalar(0,255,255)
                );
            }

            try {
                if (sock != null) {
                    writeJpg(sock.getOutputStream(), mat, boundary);
                }
            } catch (IOException ignore) {
                return;
            }
        }
    }

    private void overlayImage(Mat image, Mat overlay, int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x, y, width, height);
        Imgproc.resize(overlay, overlay, new Size(rect.getWidth(), rect.getHeight()));
        Imgproc.cvtColor(overlay, overlay, Imgproc.COLOR_GRAY2BGR);
        Mat submat = image.submat(new Rect(rect.x, rect.y, overlay.cols(), overlay.rows()));
        overlay.copyTo(submat);
    }

    private void writeHeader(OutputStream stream, String boundary) throws IOException {
        stream.write(("HTTP/1.0 200 OK\r\n" +
                "Connection: close\r\n" +
                "Max-Age: 0\r\n" +
                "Expires: 0\r\n" +
                "Cache-Control: no-store, no-cache, must-revalidate, pre-check=0, post-check=0, max-age=0\r\n" +
                "Pragma: no-cache\r\n" +
                "Content-Type: multipart/x-mixed-replace; " +
                "boundary=" + boundary + "\r\n" +
                "\r\n" +
                "--" + boundary + "\r\n").getBytes());
    }

    private void writeJpg(OutputStream stream, Mat img, String boundary) throws IOException {
        MatOfByte buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", img, buf);
        byte[] imageBytes = buf.toArray();
        stream.write(("Content-type: image/jpeg\r\n" +
                "Content-Length: " + imageBytes.length + "\r\n" +
                "\r\n").getBytes());
        stream.write(imageBytes);
        stream.write(("\r\n--" + boundary + "\r\n").getBytes());
    }

}
