package dk.sw502e18.ssr.components.outputtingComponents;

import dk.sw502e18.ssr.pipeline.Step;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.utils.Converters;
import java.util.ArrayList;
import java.util.List;

/**
 *         Example pipe:
 *         Pipe<Mat> pipe = new Pipe<Mat>()
 *                 .first(new FolderScanner("/home/scarress/Pictures/Space")) \n
 *                 .then(new Grayscale())
 *                 .then(new Mat2Vector());
 */
public class Mat2Vector implements Step<Mat, Mat> {

    private float normalizeValue = 255.0f;

    /**
     * Converts a Mat to a single channel Mat of float with default normalization value
     */
    public Mat2Vector(){
    }

    /**
     * Converts a Mat to a single channel Mat of floats
     * @param normalizeValue sets the normalization value
     */
    public Mat2Vector(float normalizeValue){
        this.normalizeValue = normalizeValue;
    }


    private Mat convertMatToSingleChannel(Mat input){
        MatOfFloat floatImage = new MatOfFloat (CvType.CV_32F);     // ensures the Matrix has float as internal type
        input.convertTo(floatImage, CvType.CV_32F);

        int size = (int) floatImage.total() * floatImage.channels();
        float[] data = new float[size];
        floatImage.get(0,0, data);                        // fetches all the columns from the float Mat

        List<Float> floatValues = new ArrayList<>();
        for (float f : data) {
            if (f >= normalizeValue) {
                floatValues.add(1.0f);
            } else floatValues.add(f / normalizeValue);
        }
        return Converters.vector_float_to_Mat(floatValues);
    }

    @Override
    public Mat process(Mat input) {
        return convertMatToSingleChannel(input);
    }
}
