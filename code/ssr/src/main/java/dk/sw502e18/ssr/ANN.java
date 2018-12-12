package dk.sw502e18.ssr;

import org.opencv.core.*;
import org.opencv.ml.ANN_MLP;
import org.opencv.ml.Ml;
import org.opencv.ml.TrainData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Artificial Neural Network
 */
public class ANN {
    private ANN_MLP mlp; // Artificial Neural Network - Multilayer Perceptron
    private final int outputLayerSize;
    private final Configuration config;

    private List<float[]> samples = new ArrayList<>();
    private List<float[]> labels = new ArrayList<>();

    public ANN(String model) {
        mlp = ANN_MLP.load(model);
        config = new Configuration();
        Mat layers = mlp.getLayerSizes();
        config.layers[0] = (int) layers.get(0, 0)[0];
        outputLayerSize = (int) layers.get(layers.rows() - 1, 0)[0];
    }

    public ANN(Configuration config) {
        mlp = ANN_MLP.create();
        this.config = config;
        outputLayerSize = config.layers[config.layers.length - 1];
        setFromConf();
    }

    public void addSample(Mat sample, int label) {
        if (sample.cols() * sample.rows() != config.layers[0]) {
            String err = String.format(
                    "Invalid sample size (expected: %d, actual: %d)",
                    config.layers[0],
                    sample.cols() * sample.rows()
            );
            throw new RuntimeException(err);
        } else if (label < 0 || label > outputLayerSize-1) {
            String err = String.format(
                    "Invalid sample size (expected: 0<=label<%d, actual: %d)",
                    outputLayerSize,
                    label
            );
            throw new RuntimeException(err);
        } else {
            float[] labelArr = new float[outputLayerSize];
            Arrays.fill(labelArr, -1);
            labelArr[label] = 1;
            labels.add(labelArr);

            float[] sampleArr = flatten(sample);

            samples.add(sampleArr);
        }
    }

    public void train() {
        Mat samples = new Mat(this.samples.size(), config.layers[0], CvType.CV_32FC1);
        Mat labels = new Mat(samples.rows(), outputLayerSize, CvType.CV_32FC1);

        for (int i = 0; i < this.samples.size(); i++) {
            samples.put(i, 0, this.samples.get(i));
            labels.put(i, 0, this.labels.get(i));
        }

        TrainData td = TrainData.create(samples, Ml.ROW_SAMPLE, labels);
        td.shuffleTrainTest();
        mlp.train(td);
    }

    public Size getSize() {
        int s = (int) Math.sqrt(mlp.getLayerSizes().get(0, 0)[0]);
        return new Size(s, s);
    }

    public boolean isTrained() {
        return mlp.isTrained();
    }

    public float predict(Mat sample) {
        MatOfFloat result = new MatOfFloat();
        float[] sampleArr = flatten(sample);

        Mat m = new Mat(1, config.layers[0], CvType.CV_32FC1);
        m.put(0, 0, sampleArr);

        return mlp.predict(m, result, 0);
    }

    public void save(String path) {
        if (mlp.isTrained()) {
            mlp.save(path);
        }
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

    private void setFromConf() {
        mlp.setLayerSizes(new MatOfInt(config.layers));
        mlp.setActivationFunction(
                config.activationFunction,
                config.activationFunctionParam1,
                config.activationFunctionParam2
        );

        mlp.setTrainMethod(
                config.trainingMethod,
                config.trainingMethodParam1,
                config.activationFunctionParam2
        );

        mlp.setTermCriteria(new TermCriteria(
                TermCriteria.MAX_ITER | TermCriteria.EPS,
                config.trainingCriteriaLimit,
                config.trainingCriteriaPrecision
        ));
    }
}