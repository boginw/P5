package dk.sw502e18.ssr;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;

public class Speedsign {
    public static int[] signs = new int[]{20, 30, 50, 60, 70, 80};

    public static ANNResultEntry fromNN(Mat results) {
        Core.MinMaxLocResult mxlr = Core.minMaxLoc(results);

        return new ANNResultEntry(mxlr);
    }

    public static int fromLabel(double label) {
        return signs[(int) label];
    }

    public static class ANNResultEntry {
        public int label;
        public int sign;
        public double certainty;

        private ANNResultEntry(Core.MinMaxLocResult mxlr) {
            this.label = (int) mxlr.maxLoc.x;
            this.sign = signs[label];
            this.certainty = mxlr.maxVal;
        }
    }
}
