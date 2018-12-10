package dk.sw502e18.ssr;

import org.opencv.core.Mat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.BiConsumer;

public class SSRTrainer {
    private EllipseProcessor cd;
    private String train;
    private String test;
    private Queue<ANN> neuralNetworks;
    private float maxAcc = Float.MIN_VALUE;
    private ANN bestANN = null;

    public SSRTrainer(String train, String test, String param) {
        this.train = train;
        this.test = test;
        neuralNetworks = new LinkedList<>(fromConfigFile(param));
    }

    public ANN train() {
        while (!neuralNetworks.isEmpty()) {
            ANN ann = neuralNetworks.poll();
            cd = new EllipseProcessor(130, 10, ann.getSize());
            addSamples(ann);

            System.out.print("Training...");
            ann.train();

            float acc = testAccuracy(ann);

            if (acc > maxAcc) {
                maxAcc = acc;
                bestANN = ann;
                System.out.println(" New best accuracy: " + maxAcc * 100);
            } else {
                System.out.println();
            }
        }

        return bestANN;
    }

    private int doOnSamples(int i, String path, BiConsumer<Mat, Integer> v) {
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

    private List<ANN> fromConfigFile(String file) {
        List<ANN> nns = new ArrayList<>();
        if (file != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("#")) {
                        continue;
                    }
                    nns.add(new ANN(Configuration.fromString(line)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return nns;
    }

    private void addSamples(ANN ann) {
        for (int i = 1; i < 6; i++) {
            doOnSamples(i, train, ann::addSample);
        }
    }

    private float testAccuracy(ANN ann) {
        float accSum = 0;
        for (int i = 1; i < 6; i++) {
            System.out.print("Testing: " + i);

            List<Boolean> q = new ArrayList<>();

            int samples = doOnSamples(i, test, (mat, label) -> {
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

}
