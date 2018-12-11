package dk.sw502e18.ssr;

import dk.sw502e18.ssr.mode.CarMode;
import dk.sw502e18.ssr.mode.WebCamMode;
import org.apache.commons.cli.*;
import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class Main {
    private static int[] signs = new int[]{20, 30, 50, 60, 70, 80};

    public static void main(String[] args) {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String train = null;
        String test = null;
        String model = null;
        String param = null;
        boolean webcam = false;

        Options options = new Options();
        options.addOption(Option.builder("m").desc("Path to model").hasArg().required().argName("PATH").build());
        options.addOption(Option.builder("t").desc("Path to training dataset").hasArg().argName("PATH").build());
        options.addOption(Option.builder("a").desc("Path to test dataset").hasArg().argName("PATH").build());
        options.addOption(Option.builder("p").desc("Path to parameter file").hasArg().argName("PATH").build());
        options.addOption(Option.builder("w").desc("Webcam mode").build());


        try {
            CommandLine line = new DefaultParser().parse(options, args);
            train = line.getOptionValue("t");
            test = line.getOptionValue("a");
            model = line.getOptionValue("m");
            param = line.getOptionValue("p");
            webcam = line.hasOption("w");
        } catch (ParseException exp) {
            System.out.println("Unexpected exception:" + exp.getMessage());
        }

        // Check if cmd args are valid
        if (model == null || // model must not be null
                // if test is set then train should also be set
                ((train == null) != (test == null)) ||
                // if param is set then train should also be set
                ((param == null) != (train == null))) {
            new HelpFormatter().printHelp("ssr", options);
            return;
        }

        if (train == null) {
            // Setup webcam
            VideoCapture vid = new VideoCapture(0);
            vid.set(Videoio.CV_CAP_PROP_FRAME_WIDTH, 320);
            vid.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT, 240);

            // Setup Neural Network with model
            ANN ann = new ANN(model);
            Mode m;

            if (webcam) {
                m = new WebCamMode();
            } else {
                m = new CarMode();
            }

            // start server or webcam
            m.start(
                    vid,
                    new EllipseProcessor(130, 10, ann.getSize()),
                    (mat) -> signs[(int) ann.predict(mat)]
            );
        } else {
            // create neural network trainer and store the best result
            ANN best = new SSRTrainer(train, test, param, signs).train();

            if (best != null) {
                best.save(model);
            }
        }
    }
}
