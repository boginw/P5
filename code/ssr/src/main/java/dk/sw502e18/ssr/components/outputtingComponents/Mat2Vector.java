package dk.sw502e18.ssr.components.outputtingComponents;

import dk.sw502e18.ssr.pipeline.Step;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.*;
import java.util.Arrays;

public class Mat2Vector implements Step<Mat, Mat> {

    private File path;
    private int counter = 0;

    public Mat2Vector(String input) {
        File path = new File(input);
        if (!path.exists()){
            try {
                path.createNewFile();
            }catch (IOException e){
                throw new RuntimeException("Unable to create file");
            }
        }
        if (path.canWrite()) {
            this.path = path;
        } else {
            throw new RuntimeException("OutPutter unable to write to output-path.");
        }
    }

    private void output(Mat input){
        MatOfInt rgb = new MatOfInt(CvType.CV_32S);
        input.convertTo(rgb, CvType.CV_32S);
        int size = (int) rgb.total() * rgb.channels();
        int[] data = new int[size];
        rgb.get(0,0, data);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(path.getAbsolutePath()));
            bw.write(Arrays.toString(data));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Mat process(Mat input) {
        output(input);
        return input;
    }
}
