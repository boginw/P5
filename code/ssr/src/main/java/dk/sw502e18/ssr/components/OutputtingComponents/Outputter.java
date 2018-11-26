package dk.sw502e18.ssr.components.OutputtingComponents;

import dk.sw502e18.ssr.pipeline.Step;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.management.RuntimeErrorException;
import java.io.File;

public class Outputter implements Step<Mat, Mat> {

    private static File _path;
    private static int _counter = 0;

    public Outputter(String input) {
        File path = new File(input);
        if (path.canWrite()) {
            _path = path;
        } else {
            throw new RuntimeException(new Error("OutPutter unable to write to output-path."));
        }
    }

    private void output(Mat input){
        Imgcodecs.imwrite(_path.getAbsolutePath() + (_counter++) + ".jpg", input);
    }

    @Override
    public Mat process(Mat input) {
        output(input);
        return input;
    }
}
