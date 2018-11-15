package dk.sw502e18.ssr.modules;

import dk.sw502e18.ssr.components.Grayscaler;
import dk.sw502e18.ssr.components.ImageResizer;
import dk.sw502e18.ssr.units.Image;

import java.text.Normalizer;

/**
 * Preprocesses the image for an Neural Network
 */
public interface Processor {

    /**
     * Gets the Processor's image resizer
     *
     * @return The Processor's image resizer
     */
    ImageResizer getImageResizer();

    /**
     * Gets the Processor's grayscaler
     *
     * @return The Processor's grayscaler
     */
    Grayscaler getGrayscaler();

    /**
     * Gets the Processor's normalizer
     *
     * @return The Processor's normalizer
     */
    Normalizer getNormalizer();

    /**
     * Sets the Processor's ImageResizer
     *
     * @param resizer A ImageResizer
     */
    void setImageResizer(ImageResizer resizer);

    /**
     * Sets the Processor's Grayscaler
     *
     * @param grayscaler A Grayscaler
     */
    void setGrayscaler(Grayscaler grayscaler);

    /**
     * Sets the Processor's Normalizer
     *
     * @param normalizer A Normalizer
     */
    void setNormalizer(Normalizer normalizer);

    /**
     * Resizes to fixed size, grayscales image, and normalizes it.
     *
     * @return Resized and Normalized floating-point image
     */
    float[][] normalize(Image image);
}
