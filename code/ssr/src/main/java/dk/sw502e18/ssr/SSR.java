package dk.sw502e18.ssr;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.opencv.core.Mat;
import org.opencv.core.Size;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class SSR {
    private final WebStream ws;
    private final ANN ann;
    private final EllipseProcessor cd;

    private String train;
    private String test;
    private String model;
    private String param;

    private static int[] signs = new int[]{20, 30, 50, 60, 70, 80};

    public SSR(String train, String test, String model, String param) {
        this.train = train;
        this.test = test;
        this.model = model;
        this.param = param;

        double[] annParams = new double[] {
                1, 0.1, 0.1,
                1, 0.1, 0.1,
                1500, 0.6,
                40, 40, 6
        };

        int[] layers = new int[]{40*40, 20*20, 10*10, 6};

        Size size = new Size(40, 40);
        cd = new EllipseProcessor(130, 10, size);




        if (train != null) {
            ann = new ANN(layers);
            ann.setActivationFunction((int) annParams[0], annParams[1], annParams[2]);
            ann.setTrainMethod((int) annParams[3], annParams[4], annParams[5]);
            ann.setTermCriteria((int) annParams[6], annParams[7]);
        } else {
            ann = new ANN(model);
        }

        ws = new WebStream(signs, cd, ann::predict);
    }

    public void detect() throws IOException {
        if (!ann.isTrained()) {
            addSamples();
            System.out.println("\nTraining...");
            ann.train();
            System.out.println("done!\nSaving model");
            ann.save(model);
        }

        if (test != null) {
            System.out.println("Avg accuracy: " + testAccuracy() * 100);
        }

        System.out.println("OK");

        ws.stream();
    }

    private void addSamples() {
        for (int i = 1; i < 6; i++) {
            System.out.print("Adding: " + i);
            System.out.println(", Number of Samples: " +
                    doOnSample(i, train, ann::addSample));
        }
    }

    private float testAccuracy() {
        System.out.println("\nTesting accuracy");

        float accSum = 0;
        for (int i = 1; i < 6; i++) {
            System.out.print("Testing: " + i);

            List<Boolean> q = new ArrayList<>();

            int samples = doOnSample(i, test, (mat, label) -> {
                if (ann.predict(mat) == label) {
                    q.add(true);
                }
            });

            System.out.print(", Samples: " + samples);

            float acc = (float) q.size() / samples;
            accSum += acc;
            System.out.println(", Accuracy: " + acc * 100 + "%");
        }

        return accSum / 6;
    }

    private int doOnSample(int i, String path, BiConsumer<Mat, Integer> v) {
        File[] files = new File(new File(path), "0000" + i).listFiles();
        files = files == null ? new File[]{} : files;
        int s = 0;

        for (File file : files) {
            if (!file.getName().contains(".ppm")) {
                continue;
            }

            Mat c = cd.detect(file);

            if (c != null) {
                v.accept(c, i);
                s++;
            }
        }

        return s;
    }

    private Pair<double[], int[]> parseParams(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                if (line.length() < 13) {
                    throw new RuntimeException("Not enough params");
                }
                double[] annParams = Arrays.stream(p).mapToDouble(Double::parseDouble).toArray();
                int[] layers = new int[annParams.length - 9];

                //Size img = new Size(Integer.valueOf(p[8]), Integer.valueOf(p[9]));
                layers[0] = (int) annParams[8] * (int) annParams[9];
                layers[layers.length - 1] = (int) annParams[10];

                for(int i = 1; i < layers.length - 1; i++) {
                    layers[i] = (int) annParams[i + 10];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
