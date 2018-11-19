package dk.sw502e18.ssr.pipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * Pipeline for dynamically chaining steps, and running the whole pipeline multiple times
 *
 * Example: new Pipe\<T\>()
 *              .first(new Input())
 *              .add(new Step_1())
 *              ...
 *              .add(new Step_n())
 *              .run()
 *
 * @param <T> Input and output of each step
 */
public class Pipe<T> {
    private static String FIRST_ERR = "You need to set \"first\"";
    private static String EMPTY_ERR = "No steps to run";

    private Input<T> first;
    private List<Step<T, T>> steps = new ArrayList<>();

    /**
     * Sets the initial step of the pipeline, which runs first
     *
     * @param input Initial step
     * @return the pipe with initial set
     */
    public Pipe<T> first(Input<T> input) {
        first = input;
        return this;
    }

    /**
     * Adds a step to the pipeline
     *
     * @param step step to be added
     * @return the pipe with the step added
     */
    public Pipe<T> then(Step<T, T> step) {
        if (first == null) {
            throw new RuntimeException(FIRST_ERR);
        }
        steps.add(step);
        return this;
    }

    /**
     * Executes the entire pipeline in the order which each step was added
     *
     * @return Returns the output of the final step
     */
    public T run() {
        if (first == null) {
            throw new RuntimeException(FIRST_ERR);
        }

        if (steps.size() == 0) {
            throw new RuntimeException(EMPTY_ERR);
        }

        T value = first.get();

        for (Step<T, T> step : steps) {
            value = step.process(value);
            if (value == null) {
                break;
            }
        }

        return value;
    }
}
