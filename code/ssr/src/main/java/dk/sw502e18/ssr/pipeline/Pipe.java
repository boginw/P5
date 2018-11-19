package dk.sw502e18.ssr.pipeline;

import java.util.ArrayList;
import java.util.List;


public class Pipe<T> {
    private static String FIRST_ERR = "You need to set \"first\"";
    private static String EMPTY_ERR = "No steps to run";

    private Input<T> first;
    private List<Step<T, T>> steps = new ArrayList<>();

    public Pipe<T> first(Input<T> input) {
        first = input;
        return this;
    }

    public Pipe<T> then(Step<T, T> step) {
        if (first == null) {
            throw new RuntimeException(FIRST_ERR);
        }
        steps.add(step);
        return this;
    }

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
            if (value == null){
                break;
            }
        }

        return value;
    }
}
