package dk.sw502e18.ssr;

import org.opencv.ml.ANN_MLP;

import java.util.Arrays;

public class Configuration {
    public int activationFunction = ANN_MLP.SIGMOID_SYM;
    public double activationFunctionParam1 = 0.1;
    public double activationFunctionParam2 = 0.1;

    public int trainingMethod = ANN_MLP.RPROP;
    public double trainingMethodParam1 = 0.1;
    public double trainingMethodParam2 = 0.1;

    public int trainingCriteriaLimit = 1500;
    public double trainingCriteriaPrecision = 0.8;

    public int[] layers = new int[]{40 * 40, 20 * 20, 10 * 10, 6};

    public Configuration() {
    }

    public Configuration(double[] params) {
        activationFunction = (int) params[0];
        activationFunctionParam1 = params[1];
        activationFunctionParam2 = params[2];

        trainingMethod = (int) params[3];
        trainingMethodParam1 = params[4];
        trainingMethodParam2 = params[5];

        trainingCriteriaLimit = (int) params[6];
        trainingCriteriaPrecision = params[7];

        layers = new int[params.length - 9];

        layers[0] = (int) params[8] * (int) params[9];
        layers[layers.length - 1] = (int) params[10];

        for (int i = 1; i < layers.length - 1; i++) {
            layers[i] = (int) params[i + 10];
        }
    }

    public String serialize() {
        StringBuilder conf = new StringBuilder();

        conf.append(String.format("%d;%f;%f;%d;%f;%f;%d;%f;%d;%d;",
                activationFunction,
                activationFunctionParam1,
                activationFunctionParam2,
                trainingMethod,
                trainingMethodParam1,
                trainingMethodParam2,
                trainingCriteriaLimit,
                trainingCriteriaPrecision,
                layers[0],
                layers[layers.length - 1]
        ));

        for (int i = 1; i < layers.length - 1; i++) {
            conf.append(layers[i]);
        }

        return conf.toString();
    }

    public static Configuration fromString(String line) {
        String[] p = line.split(";");

        if (line.length() < 13) {
            throw new RuntimeException("Not enough params");
        }

        double[] params = Arrays.stream(p).mapToDouble(Double::parseDouble).toArray();

        return new Configuration(params);
    }
}
