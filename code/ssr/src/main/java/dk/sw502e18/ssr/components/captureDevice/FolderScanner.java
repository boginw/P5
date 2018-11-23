package dk.sw502e18.ssr.components.captureDevice;

import dk.sw502e18.ssr.pipeline.Input;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;


import javax.management.RuntimeErrorException;
import java.io.File;

public class FolderScanner implements Input<Mat> {

    private static File[] _files = null;
    private static int _counter = 0;
    private static int _length= 0;

    /**
     * Gets all files in dir specified by path.
     * @param input It is assumed that the path is the absolute path and only contains images
     */
    public FolderScanner(String input) {
        File path = new File(input);
        if (path != null) {
            _files = path.listFiles();
            _length = (int) path.length();
        } else {
            throw new RuntimeErrorException(new Error("FolderScanner unable to parse input-path."));
        }
    }

    @Override
    public Mat get() {
        if (_length > 0) {
            int i = _counter++;
            _length--;
            return Imgcodecs.imread(_files[i].getAbsolutePath());
        } else {
            return null;
        }
    }
}