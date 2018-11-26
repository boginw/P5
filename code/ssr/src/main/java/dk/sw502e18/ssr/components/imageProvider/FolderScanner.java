package dk.sw502e18.ssr.components.imageProvider;

import dk.sw502e18.ssr.components.ImageProvider;
import dk.sw502e18.ssr.pipeline.Input;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import java.io.File;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class FolderScanner implements Input<Mat>, ImageProvider {

    private Queue<File> fileQueue = new LinkedBlockingQueue<>();

    /**
     * Gets all files in directory specified by path.
     * @param input It is assumed that the path is the absolute path,
     *              and the directory only contains images.
     */
    public FolderScanner(String input) {
        File path = new File(input);
        File[] files;
        if (path.canRead() && (files = path.listFiles()) != null) {
            fileQueue.addAll(Arrays.asList(files));
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
        if (!fileQueue.isEmpty()) {
            return Imgcodecs.imread(fileQueue.poll().getAbsolutePath());
        } else {
            return null;
        }
    }
}
