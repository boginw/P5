package dk.sw502e18.ssr.pipeline;

public interface Step<IN, OUT> {
    /**
     * Processes an input
     *
     * @param input the input to be processed
     * @return the processed input
     */
    OUT process(IN input);
}
