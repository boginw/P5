package dk.sw502e18.ssr.pipe;

public interface Step<IN, OUT> {
    OUT process(IN input);
}
