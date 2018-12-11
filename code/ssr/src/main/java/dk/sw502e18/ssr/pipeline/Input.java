package dk.sw502e18.ssr.pipeline;

public interface Input<T> {
    /**
     * Gets a resource
     *
     * @return a resource
     */
    T get();
}