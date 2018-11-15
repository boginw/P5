package dk.sw502e18.ssr.modules;

/**
 * Recognizes a number in an image
 */
public interface Recognizer {

    /**
     * Gets the recognizer's locator
     *
     * @return The recognizer's locator
     */
    Locator getLocator();

    /**
     * Gets the recognizer's processor
     *
     * @return The recognizer's processor
     */
    Processor getProcessor();

    /**
     * Sets the recognizer's locator
     *
     * @param locator A locator
     */
    void setLocator(Locator locator);

    /**
     * Sets the recognizer's processor
     *
     * @param processor A processor
     */
    void setProcessor(Processor processor);

    /**
     * Grabs a picture, detects circle, processes it, and recognizes number. If
     * no circle detected, this function returns -1
     *
     * @return Number encoded in image if nothing was found
     */
    int recognize();
}
