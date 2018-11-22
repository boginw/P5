package dk.sw502e18.ssr.components.dirLocater;

import dk.sw502e18.ssr.components.DirLocator;
import dk.sw502e18.ssr.pipeline.Input;

import java.nio.file.Path;

/**
 * Locates, and returns, next file to be processed by pipeline
 *
 * TODO: Implementation...
 */
public class FileScanner implements DirLocator {
    private static int _counter = 0;
    private static Path _path;

    public FileScanner(Path path) {
        this._path = path;
    }


}
