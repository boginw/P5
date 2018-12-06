package dk.sw502e18.ssr;

import org.opencv.core.*;
import org.opencv.ml.ANN_MLP;
import org.opencv.ml.Ml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ANN {
    private ANN_MLP mlp;
    private final int inputLayerSize;
    private final int outputLayerSize;

    private List<float[]> samples = new ArrayList<>();
    private List<float[]> labels = new ArrayList<>();

    public ANN(String path) {
        mlp = ANN_MLP.load(path);

        Mat layers = mlp.getLayerSizes();
        inputLayerSize = (int) layers.get(0,0)[0];
        outputLayerSize = (int) layers.get(layers.rows() - 1,0)[0];
    }

    public ANN(int... layers) {
        mlp = ANN_MLP.create();
        this.inputLayerSize = layers[0];
        this.outputLayerSize = layers[layers.length - 1];

        mlp.setLayerSizes(new MatOfInt(layers));
        mlp.setActivationFunction(ANN_MLP.SIGMOID_SYM, 0.1, 0.15);
        mlp.setTrainMethod(ANN_MLP.RPROP, 0.15, 0.1);
        mlp.setTermCriteria(new TermCriteria(TermCriteria.MAX_ITER, 1500, 0.6));
    }

    public void addSample(Mat sample, int label) {
        if (sample.cols() * sample.rows() != inputLayerSize && label >= 0 && label < outputLayerSize) {
            throw new RuntimeException("wtf?");
        } else {
            float[] labelArr = new float[outputLayerSize];
            Arrays.fill(labelArr, 0);
            labelArr[label] = 1;
            labels.add(labelArr);

            float[] sampleArr = flatten(sample);

            samples.add(sampleArr);
        }
    }

    public void train() {
        Mat samples = new Mat(this.samples.size(), inputLayerSize, CvType.CV_32FC1);
        Mat labels = new Mat(samples.rows(), outputLayerSize, CvType.CV_32FC1);

        for (int i = 0; i < this.samples.size(); i++) {
            samples.put(i, 0, this.samples.get(i));
            labels.put(i, 0, this.labels.get(i));
        }

        mlp.train(samples, Ml.ROW_SAMPLE, labels);
    }

    public boolean isTrained() {
        return mlp.isTrained();
    }

    public float predict(Mat sample) {
        MatOfFloat result = new MatOfFloat();
        float[] sampleArr = flatten(sample);

        Mat m = new Mat(1, inputLayerSize, CvType.CV_32FC1);
        m.put(0,0, sampleArr);

        return mlp.predict(m, result, 0);
    }

    public void save(String path) {
        if (mlp.isTrained()) {
            mlp.save(path);
        }
    }

    public void setActivationFunction(int activationFunction, double par1, double par2) {
        mlp.setActivationFunction(activationFunction, par1, par2); //ANN_MLP.IDENTITY, 0.1, 0.15
    }

    public void setTrainMethod(int trainMethod, double par1, double par2) {
        mlp.setTrainMethod(trainMethod, par1, par2); // ANN_MLP.RPROP, 0.15, 0.1
    }

    public void setTermCriteria(int maxCount, double precision) {
        mlp.setTermCriteria(new TermCriteria(TermCriteria.MAX_ITER, maxCount, precision)); //1500, 0.6
    }

    private float[] flatten(Mat sample) {
        float[] sampleArr = new float[sample.cols() * sample.rows()];

        for (int i = 0; i < sample.rows(); i++) {
            for (int j = 0; j < sample.cols(); j++) {
                sampleArr[i * sample.cols() + j] = (float) sample.get(i, j)[0];
            }
        }

        return sampleArr;
    }
}