package dk.sw502e18.ssr.components.outputtingComponents;

import dk.sw502e18.ssr.pipeline.Step;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

public class Outputter implements Step<Mat, Mat> {

    private File path;
    private int counter = 0;

    public Outputter(String input) {
        File path = new File(input);
        if (path.canWrite()) {
            this.path = path;
        } else {
            throw new RuntimeException(new Error("OutPutter unable to write to output-path."));
        }
    }

    private void output(Mat input){
        Imgcodecs.imwrite(path.getAbsolutePath() + (counter++) + ".png", input);
    }

    @Override
    public Mat process(Mat input) {
        output(input);
        return input;
    }
}
