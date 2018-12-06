package dk.sw502e18.car;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;


public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat mat = new Mat();
        VideoCapture vid = new VideoCapture(0);
        vid.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, 320);
        vid.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, 240);
        vid.open(0);
    }
}

