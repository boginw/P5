package dk.sw502e18.ssr;

import org.opencv.core.Mat;
import org.opencv.core.Size;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class SSR {
    private final WebStream ws;
    private final ANN ann;
    private final EllipseProcessor cd;

    private String train;
    private String test;
    private String model;

    private static int[] signs = new int[]{20, 30, 50, 60, 70, 80};

    public SSR(String train, String test, String model) {
        this.train = train;
        this.test = test;
        this.model = model;

        Size size = new Size(30, 30);
        cd = new EllipseProcessor(130, 10, size);

        if (train != null) {
            ann = new ANN((int) size.area(), (int) size.area() / 2, (int) size.area() / 4, 6);
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
}
