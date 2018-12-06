package dk.sw502e18.ssr;

import org.apache.commons.cli.*;
import org.opencv.core.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Options options = new Options();
        options.addOption(
                Option.builder("m")
                        .desc("Path to model")
                        .longOpt("model")
                        .hasArg()
                        .required()
                        .argName("PATH")
                        .build()
        );

        options.addOption(
                Option.builder("t")
                        .desc("Path to training dataset")
                        .longOpt("train")
                        .hasArg()
                        .argName("PATH")
                        .build()
        );

        options.addOption(
                Option.builder("a")
                        .desc("Path to test dataset")
                        .longOpt("test")
                        .hasArg()
                        .argName("PATH")
                        .build()
        );

        CommandLineParser parser = new DefaultParser();
        SSR m;
        String train = null;
        String test = null;
        String model = null;

        try {
            CommandLine line = parser.parse(options, args);
            train = line.getOptionValue("t");
            test = line.getOptionValue("a");
            model = line.getOptionValue("m");
        } catch (ParseException exp) {
            System.out.println("Unexpected exception:" + exp.getMessage());
        }

        if (model == null || ((train == null) != (test == null))) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("ssr", options);
            return;
        }

        m = new SSR(train, test, model);
        m.detect();
    }
}
