package dk.sw502e18.ssr.pipeline;

public interface Step<IN, OUT> {
    OUT process(IN input);
}
