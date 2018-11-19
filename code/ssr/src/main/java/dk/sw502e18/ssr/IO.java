package dk.sw502e18.ssr;

public interface IO<IN, OUT> {
    OUT process(IN input);
}
