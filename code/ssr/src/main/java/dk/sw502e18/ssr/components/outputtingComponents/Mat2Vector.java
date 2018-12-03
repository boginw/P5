package dk.sw502e18.ssr.components.outputtingComponents;

import dk.sw502e18.ssr.pipeline.Step;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import java.io.*;

public class Mat2Vector implements Step<Mat, Mat> {

    private static float normalizeValue = 255.0f;


    private void output(Mat input){
        MatOfFloat floatImage = new MatOfFloat (CvType.CV_32F);
        input.convertTo(floatImage, CvType.CV_32F);
        int size = (int) floatImage.total() * floatImage.channels();
        float[] data = new float[size];
        floatImage.get(0,0, data);
        for (int i = 0; i < data.length; i++) {
            if (data[i]>=normalizeValue) { data[i] = 1; }
            else data[i] = data[i]/normalizeValue;
        }
    }

    @Override
    public Mat process(Mat input) {
        output(input);
        return input;
    }
}
