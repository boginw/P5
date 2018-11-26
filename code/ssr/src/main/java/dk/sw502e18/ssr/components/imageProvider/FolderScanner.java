package dk.sw502e18.ssr.components.imageProvider;

import dk.sw502e18.ssr.components.ImageProvider;
import dk.sw502e18.ssr.pipeline.Input;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;


import java.io.File;

public class FolderScanner implements Input<Mat>, ImageProvider {

    private File[] files = null;
    private int counter = 0;
    private int length = 0;

    /**
     * Gets all files in directory specified by path.
     * @param input It is assumed that the path is the absolute path,
     *              and the directory only contains images.
     */
    public FolderScanner(String input) {
        File path = new File(input);
        if (path.canRead()) {
            files = path.listFiles();
            length = (int) path.length();
        } else {
            throw new RuntimeException(new Error("FolderScanner unable to read from input-path."));
        }
    }

    @Override
    public Mat get() {
        return capture();
    }

    @Override
    public Mat capture() {
        if (length > 0) {
            int i = counter++;
            length--;
            return Imgcodecs.imread(files[i].getAbsolutePath());
        } else {
            return null;
        }
    }
}
